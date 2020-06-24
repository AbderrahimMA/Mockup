package org.sid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.sid.exception.ResourceNotFoundException;
import org.sid.model.Request;
import org.sid.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/requestmanagement")
public class RequestController {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@GetMapping("/requests")
	public List<Request> getAllRequest() {
		return requestRepository.findAll();
	}
	
	@GetMapping("/requests/{id}")
	public ResponseEntity<Request> getRequestById(@PathVariable(value = "id") Long requestId)
			throws ResourceNotFoundException {
		Request request = requestRepository.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("Request not found with this id :: "+requestId));
		
		return ResponseEntity.ok().body(request);
	}
	
	
	@PostMapping("/requests/add")
	public ResponseEntity<Request> addRequest(@Valid @RequestBody Request requestDetails) 
			throws ResourceNotFoundException{
		
		Request request = new Request();
		
		request.setId(requestDetails.getId());
		request.setType(requestDetails.getType());
		request.setReason(requestDetails.getReason());
		request.setRequesterEmail(requestDetails.getRequesterEmail());
		
		requestRepository.save(request);
		
		return ResponseEntity.ok(request);
		
	}
	
	@PostMapping("/requests/update/{id}")
	public ResponseEntity<Request> updateRequest(@PathVariable(value = "id") Long requestId,
			@Valid @RequestBody Request requestDetails) throws ResourceNotFoundException{
		Request request = requestRepository.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("Request not found with this id :: "+ requestId));
		
		request.setId(requestDetails.getId());
		request.setReason(requestDetails.getReason());
		request.setType(requestDetails.getType());
		request.setRequesterEmail(requestDetails.getRequesterEmail());
		
		final Request updateRequest= requestRepository.save(request);
		return ResponseEntity.ok(updateRequest);
	}
	
	@GetMapping("/requests/delete/{id}")
	public Map<String, Boolean> deleteRequest(@PathVariable(value = "id") Long requestId)
				throws ResourceNotFoundException{
		Request request = requestRepository.findById(requestId)
				.orElseThrow(() -> new ResourceNotFoundException("Request not found with this id :: "+ requestId));
		requestRepository.delete(request);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete", Boolean.TRUE);
		return response;
	}

}
