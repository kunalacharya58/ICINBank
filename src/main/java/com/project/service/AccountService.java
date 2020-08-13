package com.project.service;

import com.project.model.PrimaryAccount;
import com.project.model.SavingsAccount;

public interface AccountService {
	
	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();
	
	void deposit(double amount, String accountType, long userId);
	void withdraw(double amount, String accountType, long userId);

}
