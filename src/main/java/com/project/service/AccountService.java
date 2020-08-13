package com.project.service;

import com.project.model.PrimaryAccount;
import com.project.model.SavingsAccount;

public interface AccountService {
	
	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();

}
