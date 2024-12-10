package com.aurionpro.entity;

public class Account {

	
	private int customerId;
	private String firstName;
	private String lastName;
	private String accountNumber;
	private double balance ;
	
	public Account(int customerId, String firstName, String lastName, String accountNumber, double balance) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	public int getCustomerId() {
		return customerId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	@Override
	public String toString() {
		return "Account [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}
	
	
	
	
	
}