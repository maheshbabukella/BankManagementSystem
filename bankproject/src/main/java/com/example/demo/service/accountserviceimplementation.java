//this is the service interface implmentation is provided here
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.account;
import com.example.demo.repo.accountrepository;
@Service  //because this is the service layer
public class accountserviceimplementation implements accountservice {

//you want to use that repository
	@Autowired
	accountrepository repo;
	
	
	@Override
	public account createaccount(account account1) {
		//now account1 will save the repository or in the database
		account account_saved=repo.save(account1);
		return account_saved;
	}

	@Override
	public account getaccountdetailsbyaccountnumber(long accountnumber) {
		//findbyid will check the account number in our  system or not and also it returns optional
		//The method findById() in Spring Data JPA returns an Optional<T>
		//Handle the case where it is not found (empty), without crashing the app.

		Optional<account> account1=repo.findById(accountnumber);
		if(account1.isEmpty())
		{
			throw new RuntimeException("account does not present");
		}
		//if account is not empty
		account account_found=account1.get();
		return account_found;
	}

	@Override
	public List<account> getallaccountdetails() {
//it will return list of account
		List<account> listofaccounts= repo.findAll();
//whatever add list of accounts just return it
		return listofaccounts;
	}

	@Override
	public account depositamount(long accountnumber, double amount) {
		//check the account has present or not
		Optional<account> account1=repo.findById(accountnumber);
		if(account1.isEmpty())
		{
			throw new RuntimeException("account is not present");
		}
		account accountpresent=account1.get();
		//to deposit the amount
		Double totalbalance=accountpresent.getAccount_balance()+amount;
		accountpresent.setAccount_balance(totalbalance);
		//total amount will save on database
		repo.save(accountpresent);
		return accountpresent;
	}

	@Override
	public account withdrawamount(long accountnumber, double amount) {
		Optional<account> account1=repo.findById(accountnumber);
		if(account1.isEmpty())
		{
			throw new RuntimeException("account is not present");
		}
		account accountpresent=account1.get();
		//to withdraw money
		Double accountbalance=accountpresent.getAccount_balance()-amount;
		accountpresent.setAccount_balance(accountbalance);
		repo.save(accountpresent);
		return accountpresent;
	}

	@Override
	public void closeaccount(long accountnumber) {
		//if check account is present or not 
		getaccountdetailsbyaccountnumber(accountnumber);
		repo.deleteById(accountnumber);
		
	}

}
