package com.project.service.impl;

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
	public User login(Login cred) throws BusinessException {
		User user = null;
		user = dao.findByUsername(cred.getUsername());
		if(user == null) {
			throw new BusinessException("user not found");
		}
		else {
			if( !user.getPassword().equals(cred.getPassword()) ){
				throw new BusinessException("invalid password");
			}
			else if( !user.isEnabled()  ) {
				throw new BusinessException("your account is not enabled");
			}
		}
		return user;
	}

}
