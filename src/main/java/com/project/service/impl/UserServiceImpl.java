package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO dao;

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return dao.save(user);
	}

	@Override
	public void deleteUserbyId(long id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public Iterable<User> getAllUsers() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).orElse(null);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}

	@Override
	public void enableUser(long id) {
		// TODO Auto-generated method stub
		User user = getUserById(id);
		user.setEnabled(true);
		dao.save(user);
	}

	@Override
	public void disableUser(long id) {
		// TODO Auto-generated method stub
		User user = getUserById(id);
		user.setEnabled(false);
		dao.save(user);
	}

	@Override
	public User saveUser(User user) {
		return dao.save(user);
	}

}
