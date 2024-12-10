package com.aurionpro.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.entity.Account;


public class AccountService {
	private PreparedStatement preparedStatement;
	private  Connection connection;
	private List<Account> accounts;
	public AccountService() {
		try {
			connection = DbConnect.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Account> getAccountOfCustomer(String username){
		List<Account> accounts = new ArrayList<>();
		
		String query = "select c.CustomerId , c.FirstName ,c.LastName , a.AccountNumber ,a.balance from customers c "
				      +"inner join accounts a on a.CustomerId = c.CustomerId "
				      +"where Email = ?";
		
		
		try {
			
			
	        preparedStatement = connection.prepareStatement(query);
	        
	       
	        preparedStatement.setString(1, username);
	        
	      
	        ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account account = new Account(
						resultSet.getInt("CustomerId"),
						resultSet.getString("FirstName"),
						resultSet.getNString("LastName"),
						resultSet.getNString("AccountNumber"),
						resultSet.getDouble("balance")				
						);
				accounts.add(account);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
		
	}
}