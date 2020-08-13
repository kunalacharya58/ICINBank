package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		boolean authenticated = true;
		boolean allowed = true;
		User user = null;
		user = service.authenticate(cred);
		
		if(user == null) {
			// user name verification
			authenticated = false;
			map = new LinkedMultiValueMap<>();
			map.add("message", "username not found");
			return new ResponseEntity<User>(null, map, HttpStatus.NOT_FOUND);
		}
		
		String userPass = user.getPassword();
		if (!userPass.equals(cred.getPassword())) {
			// password verification
			authenticated = false;
			map = new LinkedMultiValueMap<>();
			map.add("message", "invalid password");
			return new ResponseEntity<User>(null, map, HttpStatus.UNAUTHORIZED);
		}
		
		if (!user.isEnabled()) {
			// disabled account login attempt
			allowed = false;
			map = new LinkedMultiValueMap<>();
			map.add("message", "your account is not enabled");
			return new ResponseEntity<User>(null, map, HttpStatus.UNAUTHORIZED);
		}
		
		if(allowed && authenticated) {
			// authentication complete
			map = new LinkedMultiValueMap<>();
			map.add("userID", String.valueOf(user.getId()));
			map.add("message", "user active");
			return new ResponseEntity<User>(user, map, HttpStatus.OK);
		} else {
			// authentication failed
			map = new LinkedMultiValueMap<>();
			map.add("message", "login failed");
			return new ResponseEntity<User>(null, map, HttpStatus.NO_CONTENT);
		}
	}

}
