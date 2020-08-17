package com.project.service;

import java.security.NoSuchAlgorithmException;

import com.project.exception.BusinessException;
import com.project.model.Login;
import com.project.model.User;

public interface LoginService {
	
	public User authenticate(Login cred) throws BusinessException, NoSuchAlgorithmException;

}
