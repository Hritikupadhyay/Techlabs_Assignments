package com.aurionpro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileService {
	private PreparedStatement preparedStatement;
	private  Connection connection;
	public ProfileService() {
		try {
			connection = DbConnect.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int editProfile(String email,String FirstName , String Lastname ,String password,String username) throws SQLException {
		
		String query = "Select * from customers where Email = ? ";
		int rowsAffected = 0;
		int customerId = 0;
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			 
			ResultSet customerProfile = preparedStatement.executeQuery();
			if(customerProfile.next()) { 
			 customerId = customerProfile.getInt("CustomerId");
			}

			 if (FirstName != null && !FirstName.trim().isEmpty()) {
				query = "UPDATE customers SET FirstName = ? WHERE CustomerId = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, FirstName);
				preparedStatement.setInt(2, customerId );
		        rowsAffected += preparedStatement.executeUpdate();
			}
			 if (Lastname != null && !Lastname.trim().isEmpty()) {
				query = "UPDATE customers SET LastName = ? WHERE CustomerId = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, Lastname);
				preparedStatement.setInt(2, customerId );
		        rowsAffected += preparedStatement.executeUpdate();
			}
			  if (password != null && !password.trim().isEmpty()) {
				query = "UPDATE customers SET Password = ? WHERE CustomerId = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, password);
				preparedStatement.setInt(2, customerId );
		        rowsAffected += preparedStatement.executeUpdate();
			}
			  if (email != null && !email.trim().isEmpty()) {
				query = "UPDATE customers SET Email = ? WHERE CustomerId = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, email);
				preparedStatement.setInt(2, customerId );
		        rowsAffected += preparedStatement.executeUpdate();
			}
			
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		
		return rowsAffected;
		
	}
}