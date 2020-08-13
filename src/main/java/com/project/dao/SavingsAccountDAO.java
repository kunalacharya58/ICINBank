package com.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.model.SavingsAccount;

public interface SavingsAccountDAO extends CrudRepository<SavingsAccount, Long> {
	
	SavingsAccount findByNumber(long number);

}
