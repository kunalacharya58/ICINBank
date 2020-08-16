package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		long id = user.getId();
		User newUser = getUserById(id);
		if (newUser == null) {
			MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(null, map, HttpStatus.NOT_FOUND);
		}
		newUser = service.updateUser(user);
		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Headers", "message");
		map.add("message", "success");
		return new ResponseEntity<>(newUser, map, HttpStatus.OK); 
	}

	@GetMapping("/all")
	public Iterable<User> getAllUsers() {
		// TODO Auto-generated method stub
		return service.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public  User getUserById(@PathVariable("id") long id) {
		// TODO Auto-generated method stub
		return service.getUserById(id);
	}
	@PostMapping("/enable/{id}")
	public ResponseEntity<String> enableUser(@PathVariable long id ) {
		if (getUserById(id) == null) {
			MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(null, map, HttpStatus.NOT_FOUND);
		}
		service.enableUser(id);
		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Headers", "message");
		map.add("message", "success");
		return new ResponseEntity<>(null, map, HttpStatus.OK);
	}
	
	@PostMapping("/disable/{id}")
	public ResponseEntity<String> disableUser(@PathVariable long id ) {
		if (getUserById(id) == null) {
			MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(null, map, HttpStatus.NOT_FOUND);
		}
		service.disableUser(id);
		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Headers", "message");
		map.add("message", "success");
		return new ResponseEntity<>(null, map, HttpStatus.OK);
	}
	
	@PostMapping("/enable/{id}")
	public ResponseEntity<String> enableUser(@PathVariable long id ) {
		if (getUserById(id) == null) {
			MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(null, map, HttpStatus.NOT_FOUND);
		}
		service.enableUser(id);
		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Headers", "message");
		map.add("message", "success");
		return new ResponseEntity<>(null, map, HttpStatus.OK);
	}
	
	@PostMapping("/disable/{id}")
	public ResponseEntity<String> disableUser(@PathVariable long id ) {
		if (getUserById(id) == null) {
			MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(null, map, HttpStatus.NOT_FOUND);
		}
		service.disableUser(id);
		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Headers", "message");
		map.add("message", "success");
		return new ResponseEntity<>(null, map, HttpStatus.OK);
	}

}
