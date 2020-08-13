package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.model.SavingsAccount;

@Repository
public interface SavingsAccountDAO extends CrudRepository<SavingsAccount, Long> {
	
	SavingsAccount findByNumber(long number);

}
