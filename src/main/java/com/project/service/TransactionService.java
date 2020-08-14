package com.project.service;

import java.util.List;

import com.project.model.PrimaryTransaction;
import com.project.model.SavingsTransaction;

public interface TransactionService {
	
	void savePrimaryTransaction(PrimaryTransaction primaryTransaction);
	void saveSavingsTransaction(SavingsTransaction savingsTransaction);
	
	List<PrimaryTransaction> getPrimaryTransactionList(String username);
	List<SavingsTransaction> getSavingsTransactionList(String username);

}
