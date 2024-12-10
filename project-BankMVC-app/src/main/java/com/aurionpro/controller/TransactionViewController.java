package com.aurionpro.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.entity.Transactions;
import com.aurionpro.exception.TransactionNotFound;
import com.aurionpro.service.TransactionService;


@WebServlet("/TransactionViewController")
public class TransactionViewController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String customerIdParam = request.getParameter("customerId");
		  TransactionService service = new TransactionService();
		  List<Transactions> transactions;
		   

		    try {
		       

		       
		        if (customerIdParam != null && !customerIdParam.isEmpty()) {
		           
		            int customerId = Integer.parseInt(customerIdParam); 
		            transactions = service.getTransaction(customerId);
		            
		        } else {
		           
		        	 transactions = service.getAllTransaction();
		        }
		        request.setAttribute("ViewTransaction", "viewTransaction");
		        request.setAttribute("transactions", transactions);
		        request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);

		    } catch (TransactionNotFound e) {
		        e.printStackTrace();
		        request.setAttribute("errorMessage", "Unable to fetch transactions. Please try again.");
		        request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
		    }
		}

		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}