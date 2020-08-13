package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.model.PrimaryTransaction;

@Repository
public interface PrimaryTransactionDAO extends CrudRepository<PrimaryTransaction, Long> {

}
