package com.project.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.model.ChequeBookRequest;

@Repository
public interface ChequeBookRequestDAO extends CrudRepository<ChequeBookRequest, Long> {
	
	Iterable<ChequeBookRequest> findByConfirmed(boolean confirmed);
	Iterable<ChequeBookRequest> findByRequestedBy(long requestedBy);
	
	Iterable<ChequeBookRequest> findByConfirmedAndRequestedBy(boolean confirmed, long requestedBy);

}
