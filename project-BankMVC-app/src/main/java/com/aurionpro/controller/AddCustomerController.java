package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.exception.CantAddCustomer;
import com.aurionpro.service.CustomerService;
import com.aurionpro.service.DbConnect;


@WebServlet("/AddCustomerController")
public class AddCustomerController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CustomerService service = new CustomerService();
		try {
		
			
			String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        
	        if (firstName == null || lastName == null || email == null || password == null || firstName.trim().isEmpty() || lastName.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
	        	
	        	request.setAttribute("AddCustomer", "AddCustomer");
	        	request.setAttribute("errorMessage", "All fields are required.");
	            request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
	            return;
	        }

      
                int rowsAffected =  service.AddCustomer( firstName, lastName, email, password);
        	
	                if (rowsAffected > 0) {
	                    request.setAttribute("successMessage", "Customer added successfully!");
	                   
	                } else {
	                	
	                    request.setAttribute("errorMessage", "Error adding customer.");
	                   
	                }
	                request.setAttribute("AddCustomer", "AddCustomer");
	                request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
        } catch (CantAddCustomer e) {
				request.setAttribute("errorMessage", "An error occurred. Please try again later.");
	            request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
			
        }
    }
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}