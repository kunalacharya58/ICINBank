package com.project.service;

import com.project.model.Login;
import com.project.model.User;

public interface LoginService {
	
	public User authenticate(Login cred);

}
