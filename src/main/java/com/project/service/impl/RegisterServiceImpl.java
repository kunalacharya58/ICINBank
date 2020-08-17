package com.project.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exception.BusinessException;
import com.project.model.User;
import com.project.service.AccountService;
import com.project.service.RegisterService;
import com.project.service.UserService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@Override
	public User createUser(User user) throws BusinessException, NoSuchAlgorithmException {
		User newUser = null;

		newUser = userService.getUserByUsername(user.getUsername());
		if (newUser != null) {
			throw new BusinessException("username exists");
		}
		newUser = userService.getUserByEmail(user.getEmail());
		if (newUser != null) {
			throw new BusinessException("email exists");
		}

		MessageDigest md = MessageDigest.getInstance("md5");

		byte[] messageDigest = md.digest(user.getPassword().getBytes());
		BigInteger no = new BigInteger(1, messageDigest);
		String hashtext = no.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		String hashedPassword = hashtext;
		
		newUser = userService.saveUser(user);
		newUser.setPassword(hashedPassword);
		newUser.setPrimaryAccount(accountService.createPrimaryAccount(newUser));
		newUser.setSavingsAccount(accountService.createSavingsAccount(newUser));
		newUser = userService.saveUser(newUser);

		return newUser;
	}

}
