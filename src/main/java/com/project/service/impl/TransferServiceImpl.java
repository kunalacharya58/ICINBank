package com.project.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.FundTransferSlip;
import com.project.model.PrimaryAccount;
import com.project.model.PrimaryTransaction;
import com.project.model.SavingsAccount;
import com.project.model.SavingsTransaction;
import com.project.model.User;
import com.project.service.AccountService;
import com.project.service.TransactionService;
import com.project.service.TransferService;
import com.project.service.UserService;

@Service
public class TransferServiceImpl implements TransferService {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransactionService transactionService;

	@Override
	public void sameBankTransfer(FundTransferSlip slip) {
		String fromAccount = slip.getFromAccount();
		String toAccount = slip.getToAccount();
		double amount = slip.getAmount();
		
		User recipient = userService.getUserByUsername(slip.getToUsername());
		
		if(fromAccount.equalsIgnoreCase("primary")) {
			PrimaryAccount sender = accountService.findPrimaryAccount(slip.getUserID());
			if(toAccount.equalsIgnoreCase("primary")) {
				PrimaryAccount receiver = recipient.getPrimaryAccount();
				transferPrimaryToPrimary(sender, receiver, amount);
			} else {
				SavingsAccount receiver = recipient.getSavingsAccount();
				transferPrimaryToSavings(sender, receiver, amount);
			}
		} 
		else if(fromAccount.equalsIgnoreCase("savings")) {
			SavingsAccount sender = accountService.findSavingsAccount(slip.getUserID());
			if(toAccount.equalsIgnoreCase("primary")) {
				PrimaryAccount receiver = recipient.getPrimaryAccount();
				transferSavingsToPrimary(sender, receiver, amount);
			} else {
				SavingsAccount receiver = recipient.getSavingsAccount();
				transferSavingsToSavings(sender, receiver, amount);
			}
		}
	}

	@Override
	public void otherBankTransfer(FundTransferSlip slip) {
		String fromAccount = slip.getFromAccount();
		double amount = slip.getAmount();
		
		String accountNumber = slip.getToUsername();
		String bankName = slip.getToAccount();
		
		String description = "Sent to ACC-"+accountNumber+"|BK-"+bankName;
		
		if(fromAccount.equalsIgnoreCase("primary")) {
			PrimaryAccount primaryAccount = userService.getUserById(slip.getUserID()).getPrimaryAccount();
			primaryAccount.setBalance(primaryAccount.getBalance() - amount);
			
			PrimaryTransaction primaryTransaction
				= new PrimaryTransaction(new Date(), description, "Fund Transfer", amount, primaryAccount.getBalance(), primaryAccount);
			
			accountService.savePrimaryAccount(primaryAccount);
			transactionService.savePrimaryTransaction(primaryTransaction);
			
		} else {
			SavingsAccount savingsAccount = userService.getUserById(slip.getUserID()).getSavingsAccount();
			savingsAccount.setBalance(savingsAccount.getBalance() - amount);
			
			SavingsTransaction savingsTransaction
				= new SavingsTransaction(new Date(), description, "Fund Transfer", amount, savingsAccount.getBalance(), savingsAccount);
			
			accountService.saveSavingsAccount(savingsAccount);
			transactionService.saveSavingsTransaction(savingsTransaction);
			
		}
	}

	@Override
	public void transferPrimaryToPrimary(PrimaryAccount sender, PrimaryAccount receiver, double amount) {
		
		String sName = sender.getUser().getUsername();
		String rName = receiver.getUser().getUsername();
		String type = "Fund Transfer";
		
		sender.setBalance(sender.getBalance() - amount);
		receiver.setBalance(receiver.getBalance() + amount);
		
		PrimaryTransaction primaryTransactionSender 
			= new PrimaryTransaction(new Date(), "Sent to "+rName, type, amount, sender.getBalance(), sender);
		
		PrimaryTransaction primaryTransactionReceiver
			= new PrimaryTransaction(new Date(), "Received from "+sName, type, amount, receiver.getBalance(), receiver);
		
		accountService.savePrimaryAccount(sender);
		accountService.savePrimaryAccount(receiver);
		transactionService.savePrimaryTransaction(primaryTransactionSender);
		transactionService.savePrimaryTransaction(primaryTransactionReceiver);
	}

	@Override
	public void transferPrimaryToSavings(PrimaryAccount sender, SavingsAccount receiver, double amount) {
		
		String sName = sender.getUser().getUsername();
		String rName = receiver.getUser().getUsername();
		String type = "Fund Transfer";
		
		sender.setBalance(sender.getBalance() - amount);
		receiver.setBalance(receiver.getBalance() + amount);
		
		PrimaryTransaction primaryTransactionSender 
			= new PrimaryTransaction(new Date(), "Sent to "+rName, type, amount, sender.getBalance(), sender);
		
		SavingsTransaction savingsTransactionReceiver
			= new SavingsTransaction(new Date(), "Received from "+sName, type, amount, receiver.getBalance(), receiver);
		
		accountService.savePrimaryAccount(sender);
		accountService.saveSavingsAccount(receiver);
		transactionService.savePrimaryTransaction(primaryTransactionSender);
		transactionService.saveSavingsTransaction(savingsTransactionReceiver);
	}

	@Override
	public void transferSavingsToPrimary(SavingsAccount sender, PrimaryAccount receiver, double amount) {
		
		String sName = sender.getUser().getUsername();
		String rName = receiver.getUser().getUsername();
		String type = "Fund Transfer";
		
		sender.setBalance(sender.getBalance() - amount);
		receiver.setBalance(receiver.getBalance() + amount);
		
		SavingsTransaction savingsTransactionSender 
			= new SavingsTransaction(new Date(), "Sent to "+rName, type, amount, sender.getBalance(), sender);
		
		PrimaryTransaction primaryTransactionReceiver
			= new PrimaryTransaction(new Date(), "Received from "+sName, type, amount, receiver.getBalance(), receiver);
		
		accountService.saveSavingsAccount(sender);
		accountService.savePrimaryAccount(receiver);
		transactionService.saveSavingsTransaction(savingsTransactionSender);
		transactionService.savePrimaryTransaction(primaryTransactionReceiver);
	}

	@Override
	public void transferSavingsToSavings(SavingsAccount sender, SavingsAccount receiver, double amount) {
		
		String sName = sender.getUser().getUsername();
		String rName = receiver.getUser().getUsername();
		String type = "Fund Transfer";
		
		sender.setBalance(sender.getBalance() - amount);
		receiver.setBalance(receiver.getBalance() + amount);
		
		SavingsTransaction savingsTransactionSender 
			= new SavingsTransaction(new Date(), "Sent to "+rName, type, amount, sender.getBalance(), sender);
		
		SavingsTransaction savingsTransactionReceiver
			= new SavingsTransaction(new Date(), "Received from "+sName, type, amount, receiver.getBalance(), receiver);
		
		accountService.saveSavingsAccount(sender);
		accountService.saveSavingsAccount(receiver);
		transactionService.saveSavingsTransaction(savingsTransactionSender);
		transactionService.saveSavingsTransaction(savingsTransactionReceiver);
	}

}
