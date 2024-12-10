package com.aurionpro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aurionpro.exception.LoginExcepetion;

public class LoginService {
	private PreparedStatement preparedStatement;
	private  Connection connection;
	
	public LoginService() {
		try {
			connection = DbConnect.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean adminLogin(String name , String password) throws LoginExcepetion{
		String query = "SELECT * FROM admin WHERE name = ?";
		boolean isValid = false ;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (password.equals(resultSet.getString("password"))) {
					 return   isValid = true;
					 
				}
	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isValid;
		
		
	}
	
	public boolean userLogin(String username , String password) throws LoginExcepetion {
		String query = "SELECT * FROM customers WHERE email = ?";
		boolean isValid = false ;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (password.equals(resultSet.getString("password"))) {
					 return   isValid = true;
					 
				}
	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isValid;
		
		
		}
	
}