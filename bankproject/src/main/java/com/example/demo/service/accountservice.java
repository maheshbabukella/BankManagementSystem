//business logic write in the service layer
package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.account;

public interface accountservice {
	//this is to create the account
public account createaccount(account account1);
//if i pass the account number this method will give
public account getaccountdetailsbyaccountnumber(long accountnumber);
//this method will give all  account details
public List<account> getallaccountdetails();

//now you deposit some money,and which account you deposit
//account number and how much money you want to deposit
public account depositamount(long accountnumber,double amount);

//now withdraw the amount
public account withdrawamount(long accountnumberr,double amount);

//close the account
public void closeaccount(long accountnumber);
}
