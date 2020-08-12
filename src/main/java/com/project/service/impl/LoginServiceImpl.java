package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserDAO;
import com.project.model.Login;
import com.project.model.User;
import com.project.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	UserDAO dao;

	@Override
	public User authenticate(Login cred) {
		User user = null;
		user = dao.findByUsername(cred.getUsername());
		return user;
	}

}
