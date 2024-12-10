package com.aurionpro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.entity.Customer;
import com.aurionpro.entity.Transactions;
import com.aurionpro.exception.CustomerNotFounded;
import com.aurionpro.exception.InsufficientBalanceException;
import com.aurionpro.exception.TransactionNotFound;

public class TransactionService {
	private PreparedStatement preparedStatement;
	private  Connection connection;
	private List<Transactions> transactions;
	public TransactionService() {
		try {
			connection = DbConnect.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Transactions> getAllTransaction() throws  TransactionNotFound{
		 String query = "SELECT  t.accountId, t.transactionId, t.type, t.amount, t.toAccount, t.date "
                 + "FROM Transactions t "
                 + "INNER JOIN Accounts a ON t.AccountId = a.AccountNumber "
                 + "ORDER BY t.date DESC";
        try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
	        transactions = new ArrayList<>();

	       
	        while (resultSet.next()) {
	            Transactions transaction = new Transactions(
	                    resultSet.getInt("transactionId"),
	                    resultSet.getString("accountId"),
	                    resultSet.getString("type"),
	                    resultSet.getDouble("amount"),
	                    resultSet.getString("toAccount"),
	                    resultSet.getTimestamp("date")
	            );
	            transactions.add(transaction);
	        }
	     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		return transactions;
	
	}
	
	public List<Transactions> getTransaction(int customerId) throws  TransactionNotFound{
        try {
        	  String query = "SELECT  t.accountId, t.transactionId, t.type, t.amount, t.toAccount, t.date "
                      + "FROM Accounts a "
                      + "INNER JOIN Transactions t ON t.Accountid = a.AccountNumber "
                      + "WHERE a.customerId = ? "
                      + "ORDER BY t.date DESC";
         
        	
                 preparedStatement = connection.prepareStatement(query);
                 preparedStatement.setInt(1, customerId);
                 
			     ResultSet resultSet = preparedStatement.executeQuery();
			     transactions = new ArrayList<>();

	       
			     while (resultSet.next()) {
			            Transactions transaction = new Transactions(
			                    resultSet.getInt("transactionId"),
			                    resultSet.getString("accountId"),
			                    resultSet.getString("type"),
			                    resultSet.getDouble("amount"),
			                    resultSet.getString("toAccount"),
			                    resultSet.getTimestamp("date")
			            );
			            transactions.add(transaction);
			        }
		
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return transactions;
	
	}
	public int credit(double amount,String accountNumber,String username) {
	     int rowsAffected = 0;
	     String AccountNumber =null;
		  try {
			 connection.setAutoCommit(false);
			 String query00 ="SELECT a.AccountNumber, c.Email " +
		               "FROM accounts a " +
		               "INNER JOIN customers c ON c.CustomerID = a.CustomerID " +
		               "WHERE a.AccountNumber = ? And c.Email = ?";
			 preparedStatement = connection.prepareStatement(query00);
			 preparedStatement.setString(1, accountNumber);
			 preparedStatement.setString(2, username);
			 ResultSet validAccountNumber = preparedStatement.executeQuery();
			 if (!validAccountNumber.next()) {
		            return rowsAffected;
		        }
			 
			 if(validAccountNumber.next()) {
				 AccountNumber = validAccountNumber.getString("AccountNumber");
				 if(!accountNumber.equals(AccountNumber)) {
					 return rowsAffected;
				 }
			 }
			 
			 
			 String query = "UPDATE accounts SET balance = balance + ? WHERE AccountNumber = ?";
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.setDouble(1, amount);
	         preparedStatement.setString(2, accountNumber);
	         rowsAffected += preparedStatement.executeUpdate();
	         
	         if (rowsAffected >= 1) { 
	                String insertTransactionQuery = "INSERT INTO transactions (AccountID, Type, Amount, ToAccount, Date) VALUES (?, ?, ?, ?, ?)";
	                preparedStatement = connection.prepareStatement(insertTransactionQuery);
	                preparedStatement.setString(1, accountNumber);
	                preparedStatement.setString(2, "Transfer");
	                preparedStatement.setDouble(3, amount);
	                preparedStatement.setString(4, "self");
	                preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
	                preparedStatement.executeUpdate();
	            }

	          
	         connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
 
			return rowsAffected;
	}
	
	public int dedit(double amount,String accountNumber, String username) throws InsufficientBalanceException{
	     int rowsAffected = 0;
	     double balance = 0; 
	     String AccountNumber =null;
		  try {
			 connection.setAutoCommit(false);
			 String query00 ="SELECT a.AccountNumber, c.Email " +
		               "FROM accounts a " +
		               "INNER JOIN customers c ON c.CustomerID = a.CustomerID " +
		               "WHERE a.AccountNumber = ? And Email = ?";
			 preparedStatement = connection.prepareStatement(query00);
			 preparedStatement.setString(1, accountNumber);
			 preparedStatement.setString(2, username);
			 ResultSet validAccountNumber = preparedStatement.executeQuery();
			 if (!validAccountNumber.next()) {
		            return rowsAffected;
		        }
			 
			 if(validAccountNumber.next()) {
				 AccountNumber = validAccountNumber.getString("AccountNumber");
				 if(!accountNumber.equals(AccountNumber)) {
					 return rowsAffected;
				 }
			 }
			 
			 
			 String  query1 = "select * from accounts where AccountNumber = ?";
			 preparedStatement = connection.prepareStatement(query1);
			 preparedStatement.setString(1, accountNumber);
			 ResultSet accountStatement = preparedStatement.executeQuery();
			 if (accountStatement.next()) {
	                 balance = accountStatement.getDouble("Balance");
	                
			 }
			 
			 if(balance >= amount) {
				 
				 String query = "UPDATE accounts SET balance = balance - ? WHERE AccountNumber = ?";
				 preparedStatement = connection.prepareStatement(query);
				 preparedStatement.setDouble(1, amount);
		         preparedStatement.setString(2, accountNumber);
		         rowsAffected = preparedStatement.executeUpdate();
		         
		         
		            if (rowsAffected > 0) {
		            	 String insertTransactionQuery = "INSERT INTO transactions (AccountID, Type, Amount, ToAccount, Date) VALUES (?, ?, ?, ?, ?)";
		                 preparedStatement = connection.prepareStatement(insertTransactionQuery);
		                 preparedStatement.setString(1, accountNumber);
		                 preparedStatement.setString(2, "Debit");
		                 preparedStatement.setDouble(3, amount);
		                 preparedStatement.setString(4, "Self");
		                 preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
		                 preparedStatement.executeUpdate();
		            }

			 }
	         connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

			return rowsAffected;
	}
	
	public int transfer(double amount,String accountNumber,String toAccount ,String username) throws InsufficientBalanceException{
	     int rowsAffected = 0;
	     double balance = 0; 
	     String AccountNumber =null;
		  try {
			 connection.setAutoCommit(false);
			 String query00 ="SELECT a.AccountNumber, c.Email " +
		               "FROM accounts a " +
		               "INNER JOIN customers c ON c.CustomerID = a.CustomerID " +
		               "WHERE a.AccountNumber = ? And Email = ?";
			 preparedStatement = connection.prepareStatement(query00);
			 preparedStatement.setString(1, accountNumber);
			 preparedStatement.setString(2, username);
			 ResultSet validAccountNumber = preparedStatement.executeQuery();
			 
			 
			 if (!validAccountNumber.next()) {
		            return rowsAffected;
		        }
			 
			
				 AccountNumber = validAccountNumber.getString("AccountNumber");
				 if(!accountNumber.equals(AccountNumber)) {
					 return rowsAffected;
				 }
			 
			 
			 String query01 = "SELECT * FROM accounts WHERE AccountNumber = ?";
			 preparedStatement = connection.prepareStatement(query01);
			 preparedStatement.setString(1, toAccount);
			 ResultSet toAccountExist = preparedStatement.executeQuery();
			 
			 if(toAccountExist.next()) {
			 
			 String  query0 = "select * from accounts where AccountNumber = ?";
			 preparedStatement = connection.prepareStatement(query0);
			 preparedStatement.setString(1, accountNumber);
			 ResultSet accountStatement = preparedStatement.executeQuery();
			 
			 
			 if (accountStatement.next()) {
	                 balance = accountStatement.getDouble("Balance");
	                
			 }
			 if(balance >= amount) {
			 String query1 = "UPDATE accounts SET balance = balance - ? WHERE AccountNumber = ?";
			 String query2 = "UPDATE accounts SET balance = balance + ? WHERE AccountNumber = ?";
			 preparedStatement = connection.prepareStatement(query1);
			 preparedStatement.setDouble(1, amount);
	         preparedStatement.setString(2, accountNumber);
	         rowsAffected += preparedStatement.executeUpdate();
	         
	         preparedStatement = connection.prepareStatement(query2);
			 preparedStatement.setDouble(1, amount);
	         preparedStatement.setString(2, toAccount);
	         rowsAffected += preparedStatement.executeUpdate();
	          
	            if (rowsAffected > 1) { 
	                String insertTransactionQuery = "INSERT INTO transactions (AccountID, Type, Amount, ToAccount, Date) VALUES (?, ?, ?, ?, ?)";
	                preparedStatement = connection.prepareStatement(insertTransactionQuery);
	                preparedStatement.setString(1, accountNumber);
	                preparedStatement.setString(2, "Transfer");
	                preparedStatement.setDouble(3, amount);
	                preparedStatement.setString(4, toAccount);
	                preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
	                preparedStatement.executeUpdate();
	            }

        
			 }
			 }
			 
	         connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

			return rowsAffected;
	}
	
	
	public List<Transactions> getCustomerTransaction(String username)throws TransactionNotFound{
		
		  String query = "SELECT t.TransactionId, t.AccountId, t.Type, t.Amount, t.ToAccount, t.Date " +
                  "FROM customers c " +
                  "INNER JOIN accounts a ON c.CustomerId = a.CustomerId " +
                  "INNER JOIN transactions t ON a.AccountNumber = t.AccountId " +
                  "WHERE c.Email = ?";
			 
			 try {
				 
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, username);
				ResultSet resultSet = preparedStatement.executeQuery();
			    transactions = new ArrayList<>();

	       
			     while (resultSet.next()) {
			            Transactions transaction = new Transactions(
			                    resultSet.getInt("transactionId"),
			                    resultSet.getString("accountId"),
			                    resultSet.getString("type"),
			                    resultSet.getDouble("amount"),
			                    resultSet.getString("toAccount"),
			                    resultSet.getTimestamp("date")
			            );
			            transactions.add(transaction);
			        }
				System.out.println(transactions);
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 
			 
	
		
		return transactions;
		
	}
	public List<Transactions> getCustomerTransactionByAccountNumber(String username , String accountNumber)
	 throws TransactionNotFound{
		
		String query = "SELECT t.TransactionId, t.AccountId, t.Type, t.Amount, t.ToAccount, t.Date " +
                "FROM customers c " +
                "INNER JOIN accounts a ON c.CustomerId = a.CustomerId " +
                "INNER JOIN transactions t ON a.AccountNumber = t.AccountId " +
                "WHERE c.Email = ? AND a.AccountNumber = ?";
		 
		 try {
			 
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
		    transactions = new ArrayList<>();

      
		     while (resultSet.next()) {
		            Transactions transaction = new Transactions(
		                    resultSet.getInt("transactionId"),
		                    resultSet.getString("accountId"),
		                    resultSet.getString("type"),
		                    resultSet.getDouble("amount"),
		                    resultSet.getString("toAccount"),
		                    resultSet.getTimestamp("date")
		            );
		            transactions.add(transaction);
		        }
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		 

	
	return transactions;
	
}
	public List<Transactions> getCustomerTransactionType(String username , String type)
			 throws TransactionNotFound{
				
				String query = "SELECT t.TransactionId, t.AccountId, t.Type, t.Amount, t.ToAccount, t.Date " +
		                "FROM customers c " +
		                "INNER JOIN accounts a ON c.CustomerId = a.CustomerId " +
		                "INNER JOIN transactions t ON a.AccountNumber = t.AccountId " +
		                "WHERE c.Email = ? AND t.type = ?";
				 
				 try {
					 
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, type);
					ResultSet resultSet = preparedStatement.executeQuery();
				    transactions = new ArrayList<>();

		      
				     while (resultSet.next()) {
				            Transactions transaction = new Transactions(
				                    resultSet.getInt("transactionId"),
				                    resultSet.getString("accountId"),
				                    resultSet.getString("type"),
				                    resultSet.getDouble("amount"),
				                    resultSet.getString("toAccount"),
				                    resultSet.getTimestamp("date")
				            );
				            transactions.add(transaction);
				        }
					
					 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				 
				 

			
			return transactions;
			
		}
	public List<Transactions> getCustomerTransactionByTypeAndAccountNumber(String username , String type,String accountNumber)
			 throws TransactionNotFound{
				
				String query = "SELECT t.TransactionId, t.AccountId, t.Type, t.Amount, t.ToAccount, t.Date " +
		                "FROM customers c " +
		                "INNER JOIN accounts a ON c.CustomerId = a.CustomerId " +
		                "INNER JOIN transactions t ON a.AccountNumber = t.AccountId " +
		                "WHERE c.Email = ? AND a.AccountNumber = ? AND t.type = ?";
				 
				 try {
					 
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, accountNumber);
					preparedStatement.setString(3, type);
					ResultSet resultSet = preparedStatement.executeQuery();
				    transactions = new ArrayList<>();

		      
				     while (resultSet.next()) {
				            Transactions transaction = new Transactions(
				                    resultSet.getInt("transactionId"),
				                    resultSet.getString("accountId"),
				                    resultSet.getString("type"),
				                    resultSet.getDouble("amount"),
				                    resultSet.getString("toAccount"),
				                    resultSet.getTimestamp("date")
				            );
				            transactions.add(transaction);
				        }
					
					 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				 
				 

			
			return transactions;
			
		}
}