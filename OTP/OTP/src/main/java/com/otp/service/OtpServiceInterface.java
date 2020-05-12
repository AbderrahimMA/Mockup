package com.otp.service;

import java.util.List;
import java.util.Optional;

import com.otp.exception.ResourceNotFoundException;
import com.otp.model.Otp;
import com.otp.model.donnees.OtpStatus;

public interface OtpServiceInterface {

	public Otp generateOtpService();
	public Otp createOrUpdateOtp(Otp otp);

	public boolean validateOtp(char[] otpnum) throws ResourceNotFoundException;

	public void deletePhysique();

	public boolean isExistOtp(char[] otpnum);

	public void deleteOtpByValue(long id) throws ResourceNotFoundException;

	public Otp getOtpByValue(char[] otpnum) throws ResourceNotFoundException;

	public void deletelLogique();

	public Optional<Otp> getById(long id);

	public List<Otp> getOtpBySatuss(OtpStatus status);

}
