package com.project.service;

import com.project.model.FundTransferSlip;
import com.project.model.PrimaryAccount;
import com.project.model.SavingsAccount;

public interface TransferService {
	
	public void sameBankTransfer(FundTransferSlip slip);
	public void otherBankTransfer(FundTransferSlip slip);
	
	public void transferPrimaryToPrimary(PrimaryAccount sender, PrimaryAccount receiver, double amount);
	public void transferPrimaryToSavings(PrimaryAccount sender, SavingsAccount receiver, double amount);
	public void transferSavingsToPrimary(SavingsAccount sender, PrimaryAccount receiver, double amount);
	public void transferSavingsToSavings(SavingsAccount sender, SavingsAccount receiver, double amount);

}
