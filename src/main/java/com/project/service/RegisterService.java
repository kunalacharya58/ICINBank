package com.project.service;

import java.security.NoSuchAlgorithmException;

import com.project.exception.BusinessException;
import com.project.model.User;

public interface RegisterService {
	
	public User createUser(User user) throws BusinessException, NoSuchAlgorithmException;

}
