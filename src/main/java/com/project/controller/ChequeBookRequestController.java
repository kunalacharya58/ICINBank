package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.ChequeBookRequest;
import com.project.model.User;
import com.project.service.ChequeBookRequestService;
import com.project.service.UserService;

@RestController
@RequestMapping(path = "/chequebookrequest")
public class ChequeBookRequestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ChequeBookRequestService chequeBookRequestService;
	
	MultiValueMap<String, String> map;
	
	// for user to send request
	@PostMapping("/primary/{userId}")
	public ResponseEntity<ChequeBookRequest> createRequestPrimary(@PathVariable long userId) {
		User user = userService.getUserById(userId);
		ChequeBookRequest chequeBookRequest = null;
		if (user == null) {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Header", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(chequeBookRequest, map, HttpStatus.NOT_FOUND);
		}
		chequeBookRequest = chequeBookRequestService.createRequest(userId, "primary");
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Header", "message");
		map.add("message", "success");
		return new ResponseEntity<>(chequeBookRequest, map, HttpStatus.OK);
	}
	
	// for user to send request
	@PostMapping("/savings/{userId}")
	public ResponseEntity<ChequeBookRequest> createRequestSavings(@PathVariable long userId) {
		User user = userService.getUserById(userId);
		ChequeBookRequest chequeBookRequest = null;
		if (user == null) {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Header", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(chequeBookRequest, map, HttpStatus.NOT_FOUND);
		}
		chequeBookRequest = chequeBookRequestService.createRequest(userId, "savings");
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Header", "message");
		map.add("message", "success");
		return new ResponseEntity<>(chequeBookRequest, map, HttpStatus.OK);
	}
	
	// for admin portal - all requests currently held in DB (confirmed+pending)
	@GetMapping("/all")
	public ResponseEntity<Iterable<ChequeBookRequest>> getAllRequests() {
		Iterable<ChequeBookRequest> requestsList = chequeBookRequestService.getAllRequests();
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Header", "message");
		map.add("message", "success");
		return new ResponseEntity<>(requestsList, map, HttpStatus.OK);
	}
	
	// only Admin has the authority to confirm. Takes ChequeBookRequestID as pathVariable.
	@PostMapping("/confirm/{cbrId}")
	public ResponseEntity<ChequeBookRequest> confirmRequest(@PathVariable long cbrId) {
		ChequeBookRequest chequeBookRequest = null;
		chequeBookRequest = chequeBookRequestService.getById(cbrId);
		if (chequeBookRequest == null) {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Header", "message");
			map.add("message", "request not found");
			return new ResponseEntity<>(chequeBookRequest, map, HttpStatus.NOT_FOUND);
		}
		chequeBookRequest = chequeBookRequestService.confirmRequest(cbrId);
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Header", "message");
		map.add("message", "success");
		return new ResponseEntity<>(chequeBookRequest, map, HttpStatus.OK);
	}
	
	// for admin portal - all pending requests in DB yet to be confirmed
	@GetMapping("/pending/all")
	public ResponseEntity<Iterable<ChequeBookRequest>> getAllPendingRequests() {
		Iterable<ChequeBookRequest> pendingRequestsList = chequeBookRequestService.getAllPendingRequests();
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Header", "message");
		map.add("message", "success");
		return new ResponseEntity<>(pendingRequestsList, map, HttpStatus.OK);
	}
	
	// for admin portal - all pending requests by a specific user id
	@GetMapping("/pending/requestedBy/{id}")
	public ResponseEntity<Iterable<ChequeBookRequest>> getAllPendingRequestsByRequester(@PathVariable long id) {
		User user = userService.getUserById(id);
		Iterable<ChequeBookRequest> pendingRequestsList = null;
		if (user == null) {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Header", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(pendingRequestsList, map, HttpStatus.NOT_FOUND);
		}
		pendingRequestsList = chequeBookRequestService.getAllPendingRequestsByRequester(id);
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Header", "message");
		map.add("message", "success");
		return new ResponseEntity<>(pendingRequestsList, map, HttpStatus.OK);
	}
	
	// for admin portal - all requests (confirmed + pending) for a specific user
	@GetMapping("/requestedBy/{id}")
	public ResponseEntity<Iterable<ChequeBookRequest>> getAllRequestsByRequester(@PathVariable long id) {
		User user = userService.getUserById(id);
		Iterable<ChequeBookRequest> requestsList = null;
		if (user == null) {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Header", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(requestsList, map, HttpStatus.NOT_FOUND);
		}
		requestsList = chequeBookRequestService.getAllRequestsByRequester(id);
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Header", "message");
		map.add("message", "success");
		return new ResponseEntity<>(requestsList, map, HttpStatus.OK);
	}
	
}
