package com.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.model.SavingsTransaction;

public interface SavingsTransactionDAO extends CrudRepository<SavingsTransaction, Long> {

}
