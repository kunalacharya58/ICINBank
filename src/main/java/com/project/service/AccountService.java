package com.project.service;

import com.project.model.PrimaryAccount;
import com.project.model.User;

public interface AccountService {
	
	PrimaryAccount createPrimaryAccount(User user);

}
