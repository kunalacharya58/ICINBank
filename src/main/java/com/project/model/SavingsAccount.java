package com.project.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SavingsAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long number;
	private double balance;
	
	@OneToOne
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SavingsTransaction> savingsTransactionList;
	
	public SavingsAccount() {
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(long number, double balance) {
		super();
		this.number = number;
		this.balance = balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SavingsTransaction> getSavingsTransactionList() {
		return savingsTransactionList;
	}

	public void setSavingsTransactionList(List<SavingsTransaction> savingsTransactionList) {
		this.savingsTransactionList = savingsTransactionList;
	}

}
