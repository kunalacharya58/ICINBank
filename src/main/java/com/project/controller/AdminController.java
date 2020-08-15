package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Admin;
import com.project.service.AdminService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	MultiValueMap<String, String> map;

	@PostMapping("/login")
	public ResponseEntity<Admin> login(@RequestBody Admin adminCred) {
		Admin admin = adminService.getAdminByUsername(adminCred.getUsername());
		if(admin == null) {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", "invalid username");
			return new ResponseEntity<Admin>(null, map, HttpStatus.NOT_FOUND);
		}
		if(admin.getPassword().compareTo(adminCred.getPassword())==0) {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message, adminID");
			map.add("message", "admin active");
			map.add("adminID", String.valueOf(admin.getId()));
			return new ResponseEntity<Admin>(admin, map, HttpStatus.OK);
		} else {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", "invalid password");
			return new ResponseEntity<Admin>(null, map, HttpStatus.UNAUTHORIZED);
		}
	}
}
