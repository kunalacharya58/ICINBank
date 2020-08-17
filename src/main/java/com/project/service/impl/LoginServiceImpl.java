package com.project.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserDAO;
import com.project.exception.BusinessException;
import com.project.model.Login;
import com.project.model.User;
import com.project.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	UserDAO dao;

	@Override
	public User authenticate(Login cred) throws BusinessException, NoSuchAlgorithmException {
		User user = null;
		user = dao.findByUsername(cred.getUsername());
		if(user == null) {
			// check if user exists
			throw new BusinessException("user not found");
		} 
		else if( !user.isEnabled()  ) {
			// check if user account is enabled
			throw new BusinessException("your account is not enabled");
		} 
		else {
			// check password match
			String hashedPassword = user.getPassword();
			String password = cred.getPassword();
			
			// Hashing
			MessageDigest md = MessageDigest.getInstance("md5");
			
			byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            password = hashtext;
            
            // password check
			if( !password.equals(hashedPassword) ){
				throw new BusinessException("invalid password");
			}
		}
		return user;
	}

}
