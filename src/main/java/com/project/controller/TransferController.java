package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.FundTransferSlip;
import com.project.model.User;
import com.project.service.TransferService;
import com.project.service.UserService;

@RestController
@RequestMapping(path="/transfer")
public class TransferController {
	
	@Autowired
	TransferService transferService;
	
	@Autowired
	UserService userService;
	
	MultiValueMap<String, String> map;

	@PostMapping
	public ResponseEntity<String> fundTransfer(@RequestBody FundTransferSlip slip) {
		
		// check user
		User user = userService.getUserById(slip.getUserID());
		if(user == null) {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", "user not found");
			return new ResponseEntity<>(null, map, HttpStatus.NOT_FOUND); 
		}
		
		// check user balance before initiating the transfer
		double balance = 0;
		double amount = slip.getAmount();
		if(slip.getFromAccount().equalsIgnoreCase("primary")) {
			balance = user.getPrimaryAccount().getBalance();
		} else {
			balance = user.getSavingsAccount().getBalance();
		}
		if(amount > balance) {
			map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Expose-Headers", "message");
			map.add("message", "insufficient balance");
			return new ResponseEntity<>(null, map, HttpStatus.FORBIDDEN); 
		}
	
		String transferType = slip.getTransferType();
		
		// same bank transfer
		if(transferType.equalsIgnoreCase("same")) {
			// check recipient
			User recipient = userService.getUserById(slip.getUserID());
			if(recipient == null) {
				map = new LinkedMultiValueMap<>();
				map.add("Access-Control-Expose-Headers", "message");
				map.add("message", "recipient not found");
				return new ResponseEntity<>(null, map, HttpStatus.NOT_FOUND); 
			}
			transferService.sameBankTransfer(slip);
			
		}
		
		//other bank transfer
		else if(transferType.equalsIgnoreCase("other")) {
			transferService.otherBankTransfer(slip);
		}
		
		return null;
	}
}
