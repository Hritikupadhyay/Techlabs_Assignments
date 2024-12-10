package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.exception.InsufficientBalanceException;
import com.aurionpro.exception.TransactionNotFound;
import com.aurionpro.service.DbConnect;
import com.aurionpro.service.TransactionService;


@WebServlet("/TransactionController")
public class TransactionController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		 String username =  (String) request.getSession().getAttribute("username");
		try {
           
            String transactionType = request.getParameter("transactionType");
            String accountNumber = request.getParameter("accountNumber");
            String amountParam = request.getParameter("amount");
            String toAccount = request.getParameter("toAccount");
            TransactionService service = new TransactionService();

          
            if (transactionType == null || accountNumber == null || amountParam == null ||
                Double.parseDouble(amountParam) <= 0 || 
                (transactionType.equals("Transfer") && (toAccount == null || toAccount.isEmpty()))) {
                request.setAttribute("errorMessage", "All fields are required and must be valid.");
                request.getRequestDispatcher("/new_transaction.jsp").forward(request, response);
                return;
            }

            double amount = Double.parseDouble(amountParam);

           
            if ("Credit".equals(transactionType)) {
                int rowsAffected = service.credit(amount, accountNumber,username);
                if (rowsAffected > 0) {
                	
                    request.setAttribute("successMessage", "Credit transaction successful.");
                } else {
                	
                    request.setAttribute("errorMessage", "Credit transaction failed. Please try again.");
                }
            } else if ("Debit".equals(transactionType)) {
                int rowsAffected = service.dedit(amount, accountNumber,username);
                if (rowsAffected > 0) {
                   
                    request.setAttribute("successMessage", "Debit transaction successful.");
                } else {
                   
                    request.setAttribute("errorMessage", "Debit transaction failed. Please try again.");
                }
            } else if ("Transfer".equals(transactionType)) {
                int rowsAffected = service.transfer(amount, accountNumber, toAccount,username);
                if (rowsAffected > 0) {
                	
                    request.setAttribute("successMessage", "Transfer transaction successful.");
                } else {
                
                    request.setAttribute("errorMessage", "Transfer transaction failed. Please try again.");
                }
            }

          
            request.getRequestDispatcher("/new_transaction.jsp").forward(request, response);

        } catch (TransactionNotFound e) {
            e.printStackTrace();
          
            request.setAttribute("errorMessage", "An error occurred. Please try again later.");
            request.getRequestDispatcher("/new_transaction.jsp").forward(request, response);
        } catch (NumberFormatException e) {
        	
            request.setAttribute("errorMessage", "Invalid input for amount. Please enter a valid number.");
            request.getRequestDispatcher("/new_transaction.jsp").forward(request, response);
        }catch(InsufficientBalanceException e) {
        	request.setAttribute("errorMessage", "InSufficient Balance ");
            request.getRequestDispatcher("/new_transaction.jsp").forward(request, response);

        }

	    }
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}