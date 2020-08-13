package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PrimaryAccountDAO;
import com.project.model.PrimaryAccount;
import com.project.model.User;
import com.project.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private double minimumBalance = 0;
	private static long nextId = 11223300;
	
	@Autowired
	PrimaryAccountDAO primaryAccountDao;

	@Override
	public PrimaryAccount createPrimaryAccount(User user) {
		PrimaryAccount primaryAccount = new PrimaryAccount();
		primaryAccount.setBalance(minimumBalance);
		primaryAccount.setNumber(nextIdGen());
		primaryAccount.setUser(user);
		
		primaryAccountDao.save(primaryAccount);
		
		return primaryAccountDao.findByNumber(primaryAccount.getNumber());
	}
	
	private long nextIdGen() {
		return ++nextId;
	}

}
