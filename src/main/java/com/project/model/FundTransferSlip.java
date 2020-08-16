package com.project.model;

public class FundTransferSlip {
	
	private long id;
	private long userID;
	private String fromAccount;
	private String toUsername;
	private String toAccount;
	private double amount;
	private String transferType;
	
	public FundTransferSlip() {
		// TODO Auto-generated constructor stub
	}

	public FundTransferSlip(long userID, String fromAccount, String toUsername, String toAccount, double amount,
			String transferType) {
		super();
		this.userID = userID;
		this.fromAccount = fromAccount;
		this.toUsername = toUsername;
		this.toAccount = toAccount;
		this.amount = amount;
		this.transferType = transferType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToUsername() {
		return toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

}
