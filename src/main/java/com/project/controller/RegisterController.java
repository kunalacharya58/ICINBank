package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.User;
import com.project.service.RegisterService;
import com.project.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {

	@Autowired
	RegisterService regService;
	
	@Autowired
	UserService userService;
	
	MultiValueMap<String, String> map;

	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		User newUser = null;
		map = new LinkedMultiValueMap<>();
		
		// check for duplicate email
		if (userService.getUserByEmail(user.getEmail()) != null) {
			map.add("message", "email exists");
			map.add("Access-Control-Expose-Headers", "message");
			return new ResponseEntity<String>(null, map, HttpStatus.CONFLICT);
		}
		
		// check for duplicate user name
		if (userService.getUserByUsername(user.getUsername()) != null) {
			map.add("message", "username exists");
			map.add("Access-Control-Expose-Headers", "message");
			return new ResponseEntity<String>(null, map, HttpStatus.CONFLICT);
		}
		
		// check if the user was successfully registered.
		newUser = regService.createUser(user);
		if (newUser == null) {			
			map.add("message", "registration failed, check fields");
			map.add("Access-Control-Expose-Headers", "message");
			return new ResponseEntity<String>(null, map, HttpStatus.BAD_REQUEST);
		} else {			
			map.add("message", "success");
			map.add("Access-Control-Expose-Headers", "message");
			return new ResponseEntity<String>(null, map, HttpStatus.OK);
		}
		
	}

}
