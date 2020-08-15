package com.project;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.model.User;
import com.project.service.AccountService;
import com.project.service.RegisterService;

@SpringBootApplication
public class IcinBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcinBankBackendApplication.class, args);
	}
	
//	@Autowired
//	RegisterService regServ;
//	
//	@Autowired
//	AccountService accServ;
//
//	@Override
//	public void run(String... args) throws Exception {
//		LocalDate date = LocalDate.now();
//		User user = new User("John", "Doe", "0987654321", "johndoe@gmail.com", "johndoe", "secretpass", date, "street");
//		
//		regServ.createUser(user);
//		
//		accServ.deposit(2500, "primary", user.getId());
//		accServ.deposit(3000, "savings", user.getId());
//		
//		accServ.withdraw(300, "primary", user.getId());
//		accServ.withdraw(500, "savings", user.getId());
//	}

}
