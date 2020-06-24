package org.sid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.sid.exception.ResourceNotFoundException;
import org.sid.model.Appointment;
import org.sid.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/appointmentmanagement")
public class AppointmentController {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@GetMapping("/appointments")
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}
	
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable(value = "id") Long appointmentId)
			throws ResourceNotFoundException {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with this id :: "+appointmentId));
		
		return ResponseEntity.ok().body(appointment);
	}
	
	
	@PostMapping("/appointments/add")
	public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody Appointment appointmentDetails) 
			throws ResourceNotFoundException{
		
		Appointment appointment = new Appointment();
		
		appointment.setId(appointmentDetails.getId());
		appointment.setType(appointmentDetails.getType());
		appointment.setDescription(appointmentDetails.getDescription());
		appointment.setDate(appointmentDetails.getDate());
		
		appointmentRepository.save(appointment);
		
		return ResponseEntity.ok(appointment);
		
	}
	
	@PostMapping("/appointments/update/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "id") Long appointmentId,
			@Valid @RequestBody Appointment appointmentDetails) throws ResourceNotFoundException{
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with this id :: "+ appointmentId));
		
		appointment.setId(appointmentDetails.getId());
		appointment.setType(appointmentDetails.getType());
		appointment.setDescription(appointmentDetails.getDescription());
		appointment.setDate(appointmentDetails.getDate());
		
		final Appointment updateAppointment= appointmentRepository.save(appointment);
		return ResponseEntity.ok(updateAppointment);
	}
	
	@GetMapping("/appointments/delete/{id}")
	public Map<String, Boolean> deleteAppointment(@PathVariable(value = "id") Long appointmentId)
				throws ResourceNotFoundException{
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with this id :: "+ appointmentId));
		appointmentRepository.delete(appointment);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
