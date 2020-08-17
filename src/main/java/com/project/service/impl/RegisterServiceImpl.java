package com.project.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			byte[] messageDigest = md.digest(user.getPassword().getBytes());
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            user.setPassword(hashtext);
            newUser = dao.save(user);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newUser = dao.findByUsername(user.getUsername());
		newUser.setPrimaryAccount(accountService.createPrimaryAccount(newUser));
		newUser.setSavingsAccount(accountService.createSavingsAccount(newUser));
		
		newUser = dao.save(newUser);
		
		return newUser;
	}
	

}
