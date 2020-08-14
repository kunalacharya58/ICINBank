package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.PrimaryTransaction;
import com.project.model.SavingsTransaction;
import com.project.service.TransactionService;
import com.project.service.UserService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	MultiValueMap<String, String> map;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransactionService transactionService;
	
	@GetMapping("/primary/{username}")
	public ResponseEntity<List<PrimaryTransaction>> getAllPrimaryTransactions(@PathVariable String username) {
		List<PrimaryTransaction> primaryTransactionList = null;
		
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Headers", "message");
		
		if(userService.getUserByUsername(username) == null) {
			map.add("message", "username not found");
			return new ResponseEntity<List<PrimaryTransaction>>(null, map, HttpStatus.NOT_FOUND);
		}
		
		primaryTransactionList = transactionService.getPrimaryTransactionList(username);
		map.add("message", "success");
		
		return new ResponseEntity<List<PrimaryTransaction>>(primaryTransactionList, map, HttpStatus.OK);
	}
	
	@GetMapping("/savings/{username}")
	public ResponseEntity<List<SavingsTransaction>> getAllSavingsTransactions(@PathVariable String username) {
		List<SavingsTransaction> savingsTransactionList = null;
		
		map = new LinkedMultiValueMap<>();
		map.add("Access-Control-Expose-Headers", "message");
		
		if(userService.getUserByUsername(username) == null) {
			map.add("message", "username not found");
			return new ResponseEntity<List<SavingsTransaction>>(null, map, HttpStatus.NOT_FOUND);
		}
		
		savingsTransactionList = transactionService.getSavingsTransactionList(username);
		map.add("message", "success");
		
		return new ResponseEntity<List<SavingsTransaction>>(savingsTransactionList, map, HttpStatus.OK);
	}

}
