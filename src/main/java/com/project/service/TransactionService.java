package com.project.service;

import com.project.model.PrimaryTransaction;
import com.project.model.SavingsTransaction;

public interface TransactionService {
	
	void savePrimaryTransaction(PrimaryTransaction primaryTransaction);
	void saveSavingsTransaction(SavingsTransaction savingsTransaction);

}
