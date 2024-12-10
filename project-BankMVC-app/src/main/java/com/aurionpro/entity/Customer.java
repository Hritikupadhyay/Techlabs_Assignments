package com.aurionpro.entity;

public class Customer {
	private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String accountNumber;
    private double balance;

    public Customer(int customerId, String firstName, String lastName, String email,String accountNumber, double balance) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

  
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public String getAccountNumber() {
		return accountNumber;
	}

    public double getBalance() {
		return balance;
	}


	@Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}