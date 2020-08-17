package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.BusinessException;
import com.project.model.Login;
import com.project.model.User;
import com.project.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService service;
	
	MultiValueMap<String, String> map;
	
	@PostMapping("/login")
	public ResponseEntity<User> authenticate(@RequestBody Login cred) {

		User user = null;
		
		try {
			user = service.login(cred);
			
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message, userID");
			map.add("userID", String.valueOf(user.getId()));
			map.add("message", "user active");
			return new ResponseEntity<User>(user, map, HttpStatus.OK);
			
		} catch(BusinessException e) {
			
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", e.getMessage());
			return new ResponseEntity<User>(null, map, HttpStatus.UNAUTHORIZED);
		}
		
	}

}
