package com.project.service;

import com.project.model.PrimaryAccount;
import com.project.model.SavingsAccount;
import com.project.model.User;

public interface AccountService {
	
	PrimaryAccount createPrimaryAccount(User user);
	SavingsAccount createSavingsAccount(User user);
	
	void deposit(double amount, String accountType, long userId);
	void withdraw(double amount, String accountType, long userId);
	
	void exchangePrimaryToSavings(double amount, long userId);
	void exchangeSavingsToPrimary(double amount, long userId);
	
	PrimaryAccount findPrimaryAccount(long userID);
	SavingsAccount findSavingsAccount(long userID);
	
	void savePrimaryAccount(PrimaryAccount primaryAccount);
	void saveSavingsAccount(SavingsAccount savingsAccount);

}
