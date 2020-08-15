package com.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ChequeBookRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String accountType;
	private Date requestDate = null;
	private boolean confirmed = false;
	private Date confirmDate = null;
	private Long requestedBy = null;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	public ChequeBookRequest() {
		// TODO Auto-generated constructor stub
	}

	public ChequeBookRequest(String accountType, Date requestDate, boolean confirmed, Date confirmDate, User user) {
		super();
		this.accountType = accountType;
		this.requestDate = requestDate;
		this.confirmed = confirmed;
		this.confirmDate = confirmDate;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.requestedBy = user.getId();
	}

	public Long getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(Long requestedBy) {
		this.requestedBy = requestedBy;
	}

}
