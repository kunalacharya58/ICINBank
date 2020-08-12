package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	UserDAO dao;

	@Override
	public User createUser(User user) {
		User newUser = null;
		newUser = dao.save(user);
		return newUser;
	}
	

}
