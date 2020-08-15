package com.project.service;

import com.project.model.ChequeBookRequest;

public interface ChequeBookRequestService {
	
	ChequeBookRequest createRequest(long userId, String accountType);
	ChequeBookRequest saveRequest(ChequeBookRequest chequeBookRequest);
	
	ChequeBookRequest getById(long cbrId);
	ChequeBookRequest confirmRequest(long cbrId);
	
	Iterable<ChequeBookRequest> getAllRequests();
	Iterable<ChequeBookRequest> getAllRequestsByRequester(long requestedById);
	Iterable<ChequeBookRequest> getAllPendingRequests();
	Iterable<ChequeBookRequest> getAllPendingRequestsByRequester(long requestedById);

}
