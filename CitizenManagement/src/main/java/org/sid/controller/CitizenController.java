package org.sid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.sid.exception.ResourceNotFoundException;
import org.sid.model.Citizen;
import org.sid.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/citizenmanagement")
public class CitizenController {
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@GetMapping("/citizens")
	public List<Citizen> getAllCitizen() {
		return citizenRepository.findAll();
	}
	
	@GetMapping("/citizens/{id}")
	public ResponseEntity<Citizen> getCitizenById(@PathVariable(value = "id") Long citizenId)
			throws ResourceNotFoundException {
		Citizen citizen = citizenRepository.findById(citizenId)
				.orElseThrow(() -> new ResourceNotFoundException("Citizen not found with this id :: "+citizenId));
		
		return ResponseEntity.ok().body(citizen);
	}
	
	
	@PostMapping("/citizens/add")
	public ResponseEntity<Citizen> addCitizen(@Valid @RequestBody Citizen citizenDetails) 
			throws ResourceNotFoundException{
		
		Citizen citizen = new Citizen();
		
		citizen.setId(citizenDetails.getId());
		citizen.setFirstName(citizenDetails.getFirstName());
		citizen.setLastName(citizenDetails.getLastName());
		citizen.setEmail(citizenDetails.getEmail());
		
		citizenRepository.save(citizen);
		
		return ResponseEntity.ok(citizen);
		
	}
	
	@PostMapping("/citizens/update")
	public ResponseEntity<Citizen> updateCitizen(@PathVariable(value = "id") Long citizenId,
			@Valid @RequestBody Citizen citizenDetails) throws ResourceNotFoundException{
		Citizen citizen = citizenRepository.findById(citizenId)
				.orElseThrow(() -> new ResourceNotFoundException("Citizen not found with this id :: "+ citizenId));
		
		citizen.setId(citizenDetails.getId());
		citizen.setFirstName(citizenDetails.getFirstName());
		citizen.setLastName(citizenDetails.getLastName());
		citizen.setEmail(citizenDetails.getEmail());
		
		final Citizen updateCitizen= citizenRepository.save(citizen);
		return ResponseEntity.ok(updateCitizen);
	}
	
	@GetMapping("/citizens/delete/{id}")
	public Map<String, Boolean> deleteCitizen(@PathVariable(value = "id") Long citizenId)
				throws ResourceNotFoundException{
		Citizen citizen = citizenRepository.findById(citizenId)
				.orElseThrow(() -> new ResourceNotFoundException("Citizen not found with this id :: "+ citizenId));
		citizenRepository.delete(citizen);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
