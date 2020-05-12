package com.otp.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.otp.exception.ResourceNotFoundException;
import com.otp.model.Otp;
import com.otp.model.donnees.OtpStatus;
import com.otp.repo.OtpRepository;

//@EnableAsync

@Service
public class OtpService implements OtpServiceInterface {

	@Autowired
	OtpRepository repository;


	@Value("${valuesOfOTP}")
	String values;
	@Value("${langeurDeOtp}")
	private int len;


	@Value("${duree}")
	private int duree;
	@Value("${physicDelete}")
	private boolean physicDelete;

	// generate function


	@Override
	public Otp generateOtpService() {
		Otp otp = new Otp();
		otp.setDateExpiration(new Timestamp(System.currentTimeMillis() + this.duree * 60 * 1000));

		otp.setStatus(OtpStatus.WAITING);

		otp.setOtpNum(generateOTP(len));
		createOrUpdateOtp(otp);
		return otp;
	}


//	validation

	@Override
	public boolean validateOtp(char[] otpnum) throws ResourceNotFoundException {

		Optional<Otp> otp = Optional.ofNullable(getOtpByValue(otpnum));
		if (otp.isPresent()) {

			if (otp.get().getStatus() == OtpStatus.WAITING
					&& otp.get().getDateExpiration().after(new Timestamp(System.currentTimeMillis()))) {
					otp.get().setStatus(OtpStatus.USED);
					createOrUpdateOtp(otp.get());

					return true;
				}
			else {

				return false;
			}

		} else {
			throw new ResourceNotFoundException("OTP incorrect, Pleaase Retry");

		}

	}

	// methode programmée pour supp les otps qui ne sont pas utilisées

	@Async
	@Scheduled(fixedRate = 5 * 60 * 1000)
	public void run() {
		if (this.physicDelete) {
			deletePhysique();

		}
		deletelLogique(); // supp logique
		// deleteOtpExpiress(); // suppression physique

	}

	// random

	public char[] generateOTP(int len) {

		Random rndm_method = new Random();

		char[] otpnum = new char[len];

		for (int i = 0; i < len; i++) {
			otpnum[i] = values.charAt(rndm_method.nextInt(values.length()));
		}
		return otpnum;
	}

	// CRUD OTP
	// find all otp in db (liste des Otps)//
	public List<Otp> getAll() {
		return repository.findAll();
	}
	// get otps by status

	@Override
	public List<Otp> getOtpBySatuss(OtpStatus status) {

		return repository.getOtpByStatus(status);
	}
	
	// get otp by id //

	@Override
	public Optional<Otp> getById(long id) {
		return repository.findById(id);
	}
	// function get otp by OTP value //

	@Override
	public Otp getOtpByValue(char[] otpnum) throws ResourceNotFoundException {

		return repository.findOtpByValue(otpnum);

	}

	// delete otp by otp value *//

	@Override
	public void deleteOtpByValue(long id) throws ResourceNotFoundException {

		repository.deleteById(id);
	}

	// create or upade otp //

	@Override
	public Otp createOrUpdateOtp(Otp otp) {
		Optional<Otp> otpp = repository.findById(otp.getId());

		if (otpp.isPresent()) {
			Otp newEntity = otpp.get();
			newEntity.setDateExpiration(otp.getDateExpiration());
			newEntity.setDateGenetation(otp.getDateGenetation());
			newEntity.setOtpNum(otp.getOtpNum());
			newEntity.setStatus(otp.getStatus());

			newEntity = repository.save(newEntity);

			return newEntity;
		} else {
			otp = repository.save(otp);

			return otp;
		}
	}

	// isExist function pour testet l'existence de l'otp

	@Override
	public boolean isExistOtp(char[] otpnum) {

		Optional<Otp> otpOptional = Optional.ofNullable(repository.findOtpByValue(otpnum));

		if (otpOptional.isPresent()) {
			return true;
		}

		return false;

	}

	// supprimer les otps expirés
	// physique
	@Override
	public void deletePhysique() {
		repository.deletePhysic(new Timestamp(System.currentTimeMillis()));


	}
	// logic
	@Override
	public void deletelLogique() {
		Optional<List<Otp>> otpp = Optional.ofNullable(this.getOtpBySatuss(OtpStatus.WAITING));
		if (otpp.isPresent()) {
			for (Otp o : otpp.get()) {
				System.out.println(o.getOtpNum());
				if (o.getDateExpiration().before(new Timestamp(System.currentTimeMillis()))) {
					o.setStatus(OtpStatus.DELETED);
					createOrUpdateOtp(o);

				}
			}

		}

	}
}
