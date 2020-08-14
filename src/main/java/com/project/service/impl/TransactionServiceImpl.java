package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PrimaryTransactionDAO;
import com.project.dao.SavingsTransactionDAO;
import com.project.model.PrimaryTransaction;
import com.project.model.SavingsTransaction;
import com.project.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	PrimaryTransactionDAO primaryTransactionDao;
	
	@Autowired
	SavingsTransactionDAO savingsTransactionDao;

	@Override
	public void savePrimaryTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	@Override
	public void saveSavingsTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
	}

}
