package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.PrimaryAccount;
import com.project.model.SavingsAccount;
import com.project.model.TransferSlip;
import com.project.model.User;
import com.project.service.AccountService;
import com.project.service.UserService;

@RestController
@CrossOrigin(origins = "https://localhost:4200")
public class AccountController {
	
	private MultiValueMap<String, String> map;
	private double transactionLimit = 20000;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/primary/{userID}")
	public ResponseEntity<PrimaryAccount> findPrimaryAccount(@PathVariable long userID) {
		User user = userService.getUserById(userID);
		if(user == null) {
			map = new LinkedMultiValueMap<>();
			map.add("message", "user not found");
			map.add("Access-Control-Expose-Headers", "message");
			return new ResponseEntity<>(null, map, HttpStatus.NOT_FOUND);
		}
		map = new LinkedMultiValueMap<>();
		map.add("message", "success");
		map.add("Access-Control-Expose-Headers", "message");
		PrimaryAccount primaryAccount = accountService.findPrimaryAccount(userID);
		
		return new ResponseEntity<>(primaryAccount, map, HttpStatus.OK);
	}
	
	@GetMapping("/savings/{userID}")
	public ResponseEntity<SavingsAccount> findSavingsAccount(@PathVariable long userID) {
		User user = userService.getUserById(userID);
		if(user == null) {
			map = new LinkedMultiValueMap<>();
			map.add("message", "user not found");
			map.add("Access-Control-Expose-Headers", "message");
			return new ResponseEntity<>(null, map, HttpStatus.NOT_FOUND);
		}
		map = new LinkedMultiValueMap<>();
		map.add("message", "success");
		map.add("Access-Control-Expose-Headers", "message");
		SavingsAccount savingsAccount = accountService.findSavingsAccount(userID);
		
		return new ResponseEntity<>(savingsAccount, map, HttpStatus.OK);
	}

	@PostMapping("/deposit")
	public ResponseEntity<String> deposit(@RequestBody TransferSlip slip) {
		// nothing to validate, everything is allowed
		accountService.deposit(slip.getAmount(), slip.getAccountType(), slip.getUserID());
		
		map = new LinkedMultiValueMap<>();
		map.add("message", "success");
		map.add("Access-Control-Expose-Headers", "message");
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<String> withdraw(@RequestBody TransferSlip slip) {
		double amount = slip.getAmount();
		String accountType = slip.getAccountType();
		User user = userService.getUserById(slip.getUserID());
		
		// transaction limit
		if (amount > transactionLimit) {
			map = new LinkedMultiValueMap<>();
			map.add("message", "transaction limit exceeded");
			map.add("Access-Control-Expose-Headers", "message");
			
			return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
		}
		
		// balance check, no underflow allowed
		double accountBalance = 0;
		if(accountType.equalsIgnoreCase("Primary")) {
			accountBalance = user.getPrimaryAccount().getBalance();
		}
		if(accountType.equalsIgnoreCase("Savings")) {
			accountBalance = user.getSavingsAccount().getBalance();
		}
		if(accountBalance < amount) {
			map = new LinkedMultiValueMap<>();
			map.add("message", "insufficient balance");
			map.add("Access-Control-Expose-Headers", "message");
			
			return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
		}
		
		// everything goes through smoothly
		accountService.withdraw(amount, accountType, user.getId());
		map = new LinkedMultiValueMap<>();
		map.add("message", "success");
		map.add("Access-Control-Expose-Headers", "message");
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
}
