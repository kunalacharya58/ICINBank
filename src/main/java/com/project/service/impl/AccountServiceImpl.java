package com.project.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PrimaryAccountDAO;
import com.project.dao.SavingsAccountDAO;
import com.project.model.PrimaryAccount;
import com.project.model.PrimaryTransaction;
import com.project.model.SavingsAccount;
import com.project.model.User;
import com.project.service.AccountService;
import com.project.service.TransactionService;
import com.project.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private double minimumBalance = 0;
	private static long nextId = 11223300;
	
	@Autowired
	PrimaryAccountDAO primaryAccountDao;
	@Autowired
	SavingsAccountDAO savingsAccountDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransactionService transactionService;
	
	private long nextIdGen() {
		return ++nextId;
	}

	@Override
	public PrimaryAccount createPrimaryAccount() {
		PrimaryAccount primaryAccount = new PrimaryAccount();
		primaryAccount.setBalance(minimumBalance);
		primaryAccount.setNumber(nextIdGen());
		
		primaryAccount = primaryAccountDao.save(primaryAccount);
		
		return primaryAccountDao.findByNumber(primaryAccount.getNumber());
	}

	@Override
	public SavingsAccount createSavingsAccount() {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setBalance(minimumBalance);
		savingsAccount.setNumber(nextIdGen());
		
		savingsAccount = savingsAccountDao.save(savingsAccount);
		
		return savingsAccountDao.findByNumber(savingsAccount.getNumber());
	}

	@Override
	public void deposit(double amount, String accountType, long userId) {
		User user = userService.getUserById(userId);
		
		if(accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setBalance(primaryAccount.getBalance() + amount);
			primaryAccountDao.save(primaryAccount);
			
			Date date = new Date();
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit To Primary Account", "Deposit", amount, primaryAccount.getBalance(), primaryAccount);
			transactionService.savePrimaryTransaction(primaryTransaction);
		}
		
		if(accountType.equalsIgnoreCase("savings")) {
			
		}
		
	}

	@Override
	public void withdraw(double amount, String accountType, long userId) {
		// TODO Auto-generated method stub
		
	}

}
