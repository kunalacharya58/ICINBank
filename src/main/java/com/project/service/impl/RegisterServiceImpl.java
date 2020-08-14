package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.service.AccountService;
import com.project.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	UserDAO dao;
	
	@Autowired
	AccountService accountService;

	@Override
	public User createUser(User user) {
		User newUser = null;
		newUser = dao.save(user);
		
		newUser = dao.findByUsername(user.getUsername());
		newUser.setPrimaryAccount(accountService.createPrimaryAccount(newUser));
		newUser.setSavingsAccount(accountService.createSavingsAccount(newUser));
		
		newUser = dao.save(newUser);
		
		return newUser;
	}
	

}
