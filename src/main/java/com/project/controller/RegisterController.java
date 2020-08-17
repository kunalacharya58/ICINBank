package com.project.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.BusinessException;
import com.project.model.User;
import com.project.service.RegisterService;
import com.project.service.UserService;

@RestController
public class RegisterController {

	@Autowired
	RegisterService regService;
	
	@Autowired
	UserService userService;
	
	MultiValueMap<String, String> map;

	@PostMapping("/register")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User newUser = null;
		
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Headers", "message");
		
		try {
			newUser = regService.createUser(user);
			map.add("message", "success");
			return new ResponseEntity<User>(newUser, map, HttpStatus.OK);
			
		} catch(BusinessException | NoSuchAlgorithmException e) {
			
			map.add("message", e.getMessage());
			return new ResponseEntity<User>(null, map, HttpStatus.CONFLICT);
		}
	}

}
