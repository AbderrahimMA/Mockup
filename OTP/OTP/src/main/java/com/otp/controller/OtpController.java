package com.otp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.otp.exception.ResourceNotFoundException;
import com.otp.model.Otp;
import com.otp.model.donnees.OtpStatus;
import com.otp.service.OtpService;

@RestController
@RequestMapping(value = "/otp")
public class OtpController {


	@Autowired
	public OtpService otpService;


	/* generate OTP */

	@GetMapping("/generer")

	public @ResponseBody Otp generateOtpp() {
		return otpService.generateOtpService();

	}

//	 OTP VALIDATION FUNCTION

	@RequestMapping(value = "/valider/{otpnum}", method = RequestMethod.GET)
	public @ResponseBody boolean validateOtp(@PathVariable("otpnum") char[] otpnum) {
		return otpService.validateOtp(otpnum);

	}

	// CRUD OTP
	// FIND ALL OTP

	@GetMapping("/getAll")

	public List<Otp> findallotp() {
		return otpService.getAll();
	}

	@GetMapping("/getOtpsByStatus/{status}")

	public List<Otp> findByStatus(@PathVariable("status") OtpStatus status) {
		return otpService.getOtpBySatuss(status);
	}

	// get OTP By OTP Value //

	@RequestMapping(value = "/get/{otp}", method = RequestMethod.GET)
	public Otp getOtp(@PathVariable("otp") char[] otpp) throws ResourceNotFoundException {

		Optional<Otp> otpnum = Optional.ofNullable(otpService.getOtpByValue(otpp));

		if (!otpnum.isPresent()) {
			throw new ResourceNotFoundException("Otp not found ... ");
		}
		return otpnum.get();

	}
	// FIND OTP By OTP id
	@GetMapping("/{id}")
	public Optional<Otp> getOtpByIdd(@PathVariable long id) {
		if (!otpService.getById(id).isPresent()) {
			throw new ResourceNotFoundException("Otp not found ..id error. ");

		}
		return otpService.getById(id);

	}

	@DeleteMapping("/{otpnum}")
	public ResponseEntity<Object> deleteOtp(@PathVariable("otpnum") char[] otpnum)
			throws ResourceNotFoundException {
		Optional<Otp> isOtpExist = Optional.ofNullable(otpService.getOtpByValue(otpnum));
		if (isOtpExist.isPresent()) {
			otpService.deleteOtpByValue(isOtpExist.get().getId());
			return new ResponseEntity<>("OTP is deleted successsfully", HttpStatus.OK);
		}

		throw new ResourceNotFoundException("NOT deleted ,  OTP not found");

	}

	
	// test keycloak

	@RequestMapping(path = { "/", "/unsecured" })
	public String noSecuredEndpoint() {
		return "This is an unsecured endpoint payload";
	}
	@RequestMapping("/admin")
	public String adminSecuredEndpoint() {
		return "This is an ADMIN endpoint payload";
	}
	@RequestMapping("/user")
	public String userSecuredEndpoint() {
		return "This is an USER endpoint payload";
	}
}
