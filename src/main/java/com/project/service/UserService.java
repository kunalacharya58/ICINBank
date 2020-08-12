package com.project.service;

import com.project.model.User;

public interface UserService {
	
	public User updateUser(User user);
	public void deleteUserbyId(long id);
	
	public Iterable<User> getAllUsers();
	public User getUserById(long id);
	public User getUserByEmail(String email);
	public User getUserByUsername(String username);

}
