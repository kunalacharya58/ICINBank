package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.model.PrimaryAccount;

@Repository
public interface PrimaryAccountDAO extends CrudRepository<PrimaryAccount, Long> {
	
	public PrimaryAccount findByNumber(long number);

}
