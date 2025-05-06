package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.account;
import com.example.demo.service.accountservice;

@RestController
@RequestMapping("/account")
public class accountcontroller {
	//after service class created then it is used to create account
	@Autowired
	accountservice service;
// to create the account
	@PostMapping("/create")
	// that account details we will be passing through through a request body
	public ResponseEntity<account> createaccount(@RequestBody account account1 )
	{
		//now use that service layer to create the account 
		account createaccount=service.createaccount(account1);
//whatever account you created that will be return here
		return ResponseEntity.status(HttpStatus.CREATED).body(createaccount);
}
	
	
	
	//now after create account to get the account details based on account
	//in that url is the  account id pass it
	//pathvariable is used because of passing the account number in url pathvariable
	@GetMapping("/{accountnumber}")//curly braces are used for spring treat like it is a  part of url
	public account getaccountbyaccountnumber(@PathVariable("accountnumber") Long accountnumber)
	{
		account account1=service.getaccountdetailsbyaccountnumber(accountnumber);
		return account1;//if i pass account number should return account details
	}
	
	@GetMapping("/getallaccounts")
	//it returns the list of  account so w create
	public List<account> getallaccountdetails()
	{
		List<account> allaccountdetails=service.getallaccountdetails();
		return allaccountdetails;
	}
	
	
	//to create one race point to deposit the  money
	//once you deposit a money it will show the details of the account
	
	
	@PutMapping("/deposit/{accountnumber}/{amount}")
	//in this case must have pass the url name in pathvariable because we use gradle tool instead of maven tool
	public account depositaccount(@PathVariable("accountnumber") Long accountnumber, @PathVariable("amount") Double amount)
	{
		account account1=service.depositamount(accountnumber,amount);
		return account1;
	}
	
	
	@PutMapping("/withdraw/{accountnumber}/{amount}")
	public account withdrawamount(@PathVariable("accountnumber") Long accountnumber, @PathVariable("amount") Double amount)
	{
		account account1=service.withdrawamount(accountnumber,amount);
		return account1;
	}
	
	
	@DeleteMapping("/delete/{accountnumber}")
	public ResponseEntity<String> deleteaccount(@PathVariable("accountnumber") Long accountnumber)
	{
		service.closeaccount(accountnumber);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("accountclosed");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


//in the postman save response also changed whenever we create a new account
//like 1st account is 200 then 2nd account is 201
//so we used response entity instead of account