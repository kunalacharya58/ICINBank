package com.project.service;

import com.project.model.User;

public interface UserService {
	
	public User saveUser(User user);
	public User updateUser(User user);
	public void deleteUserbyId(long id);
	
	public void enableUser(long id);
	public void disableUser(long id);
	
	public Iterable<User> getAllUsers();
	public User getUserById(long id);
	public User getUserByEmail(String email);
	public User getUserByUsername(String username);

}
