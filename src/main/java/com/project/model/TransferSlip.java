package com.project.model;

// This class is not an entity, this will be used as a helper object to convert JSON body to Object;
public class TransferSlip {
	
	private double amount;
	private String accountType;
	private long userID;
	
	public TransferSlip() {
		// TODO Auto-generated constructor stub
	}

	public TransferSlip(double amount, String accountType, long userID) {
		super();
		this.amount = amount;
		this.accountType = accountType;
		this.userID = userID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "TransferSlip [amount=" + amount + ", accountType=" + accountType + ", userID=" + userID + "]";
	}

}
