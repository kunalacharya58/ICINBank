package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PrimaryAccountDAO;
import com.project.dao.SavingsAccountDAO;
import com.project.model.PrimaryAccount;
import com.project.model.SavingsAccount;
import com.project.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private double minimumBalance = 0;
	private static long nextId = 11223300;
	
	@Autowired
	PrimaryAccountDAO primaryAccountDao;
	
	@Autowired
	SavingsAccountDAO savingsAccountDao;
	
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

}
