package com.aurionpro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.aurionpro.entity.Customer;
import com.aurionpro.exception.CantAddCustomer;
import com.aurionpro.exception.CustomerNotFounded;

public class CustomerService {
	Random random = new Random();
	private PreparedStatement preparedStatement;
	private  Connection connection;
	private List<Customer> customers;
	public CustomerService() {
		try {
			connection = DbConnect.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Customer> getAllCustomer() throws  CustomerNotFounded{
		String query = "SELECT c.CustomerId, c.FirstName, c.LastName, c.Email, a.AccountNumber,a.balance " +
	               "FROM customers c " +
	               "LEFT JOIN accounts a ON c.customerId = a.customerId ";
        try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
	        customers = new ArrayList<>();

	       
	        while (resultSet.next()) {
	            Customer customer = new Customer(
	                    resultSet.getInt("CustomerId"),
	                    resultSet.getString("FirstName"),
	                    resultSet.getString("LastName"),
	                    resultSet.getString("Email"),
	                    resultSet.getString("AccountNumber"),
	                    resultSet.getDouble("balance")
	            );
	            customers.add(customer);
	        }
	     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		return customers;
	
	}
	
	public List<Customer> getCustomer(int customerId) throws  CustomerNotFounded{
        try {
        	String query = "SELECT c.CustomerId, c.FirstName, c.LastName, c.Email, a.AccountNumber,a.balance " +
                    "FROM customers c " +
                    "LEFT JOIN accounts a ON c.customerId = a.customerId " +
                    "WHERE c.customerId = ?";
                 preparedStatement = connection.prepareStatement(query);
                 preparedStatement.setInt(1, customerId);
                 
			     ResultSet resultSet = preparedStatement.executeQuery();
			     customers = new ArrayList<>();

	       
			     while (resultSet.next()) {
			            Customer customer = new Customer(
			                    resultSet.getInt("CustomerId"),
			                    resultSet.getString("FirstName"),
			                    resultSet.getString("LastName"),
			                    resultSet.getString("Email"),
			                    resultSet.getString("AccountNumber"),
			                    resultSet.getDouble("balance")
			            );
			            customers.add(customer);
			        }
		
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return customers;
	
	}
	
	
	public int AddCustomer( String firstName, String lastName, String email ,String password)  throws  CustomerNotFounded {
		int rowsAffected = 0;
                
           try {
        	   connection.setAutoCommit(false);
        	 
        	   int id = random.nextInt(10000)+1;
               String query = "INSERT INTO customers VALUES (?, ?, ?, ?, ?)";
                   preparedStatement = connection.prepareStatement(query);
                   preparedStatement.setInt(1, id);
                   preparedStatement.setString(2, firstName);
                   preparedStatement.setString(3, lastName);
                   preparedStatement.setString(4, email);
                   preparedStatement.setString(5, password);


               
             rowsAffected=  preparedStatement.executeUpdate();
                       
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return rowsAffected;
		
	}
	
	
}