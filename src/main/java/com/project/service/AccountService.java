package com.project.service;

import com.project.model.PrimaryAccount;
import com.project.model.SavingsAccount;
import com.project.model.User;

public interface AccountService {
	
	PrimaryAccount createPrimaryAccount(User user);
	SavingsAccount createSavingsAccount(User user);
	
	void deposit(double amount, String accountType, long userId);
	void withdraw(double amount, String accountType, long userId);
	
	PrimaryAccount findPrimaryAccount(long userID);
	SavingsAccount findSavingsAccount(long userID);

}
