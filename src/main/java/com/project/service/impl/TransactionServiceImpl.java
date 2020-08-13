package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PrimaryTransactionDAO;
import com.project.model.PrimaryTransaction;
import com.project.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	PrimaryTransactionDAO primaryTransactionDao;

	@Override
	public void savePrimaryTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

}
