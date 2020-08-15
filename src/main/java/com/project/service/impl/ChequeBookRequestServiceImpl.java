package com.project.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.ChequeBookRequestDAO;
import com.project.model.ChequeBookRequest;
import com.project.model.User;
import com.project.service.ChequeBookRequestService;
import com.project.service.UserService;

@Service
public class ChequeBookRequestServiceImpl implements ChequeBookRequestService {
	
	@Autowired
	ChequeBookRequestDAO cbrDao;
	
	@Autowired
	UserService userService;

	@Override
	public ChequeBookRequest saveRequest(ChequeBookRequest chequeBookRequest) {
		return cbrDao.save(chequeBookRequest);
	}

	@Override
	public ChequeBookRequest createRequest(long userId, String accountType) {
		User user = userService.getUserById(userId);
		Date reqDate = new Date();
		String accType = null;
		if(accountType.equalsIgnoreCase("primary")) {
			accType = "Primary";
		} else if(accountType.equalsIgnoreCase("savings")) {
			accType = "Savings";
		}
		ChequeBookRequest chequeBookRequest = new ChequeBookRequest(accType, reqDate, false, null, user);
		chequeBookRequest.setUser(user);
		
		return cbrDao.save(chequeBookRequest);
	}

	@Override
	public ChequeBookRequest confirmRequest(long cbrId) {
		ChequeBookRequest chequeBookRequest = getById(cbrId);
		chequeBookRequest.setConfirmed(true);
		chequeBookRequest.setConfirmDate(new Date());
		
		return cbrDao.save(chequeBookRequest);
	}

	@Override
	public ChequeBookRequest getById(long cbrId) {
		return cbrDao.findById(cbrId).orElse(null);
	}

	@Override
	public Iterable<ChequeBookRequest> getAllRequests() {
		return cbrDao.findAll();
	}

	@Override
	public Iterable<ChequeBookRequest> getAllPendingRequests() {
		return cbrDao.findByConfirmed(false);
	}

	@Override
	public Iterable<ChequeBookRequest> getAllRequestsByRequester(long requestedById) {
		return cbrDao.findByRequestedBy(requestedById);
	}

	@Override
	public Iterable<ChequeBookRequest> getAllPendingRequestsByRequester(long requestedById) {
		return cbrDao.findByConfirmedAndRequestedBy(false, requestedById);
	}

}
