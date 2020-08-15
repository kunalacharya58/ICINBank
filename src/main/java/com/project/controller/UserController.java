package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.model.User;
import com.project.service.UserService;

@RestController
@RequestMapping(path="/user")
@CrossOrigin(origins = "https://localhost:4200")
public class UserController {
	
	@Autowired
	UserService service;
	
	@DeleteMapping("/{id}")
	public void deleteUserbyId(@PathVariable("id") long id) {
		// TODO Auto-generated method stub
		service.deleteUserbyId(id);
	}

	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		// TODO Auto-generated method stub
		return service.updateUser(user);
	}

	@GetMapping("/all")
	public Iterable<User> getAllUsers() {
		// TODO Auto-generated method stub
		return service.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		// TODO Auto-generated method stub
		return new ResponseEntity<User>(service.getUserById(id),HttpStatus.OK);
	}

}
