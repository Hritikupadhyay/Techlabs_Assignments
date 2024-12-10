package com.aurionpro.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.entity.Customer;
import com.aurionpro.exception.CustomerNotFounded;
import com.aurionpro.service.CustomerService;
import com.aurionpro.service.GenerateBankAccount;

@WebServlet("/GenerateAccount")
public class GenerateAccount extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerService service = new CustomerService();
		List<Customer> customers;
		String customerIdParam = request.getParameter("customerId");
		String action = request.getParameter("action");
		try {

			boolean accountGenearted = false;
			GenerateBankAccount generateAccount = new GenerateBankAccount();

			if (customerIdParam == null || customerIdParam.trim().isEmpty() || !customerIdParam.matches("\\d+")) {
				request.setAttribute("AddAccount", "AddAccount");
				request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
				return;
			}
			int customerId = Integer.parseInt(customerIdParam);

			if ("filter".equals(action)) {
				
				customers = service.getCustomer(customerId);

				if (customers.isEmpty()) {
					request.setAttribute("errorMessage", "No customer found with the given ID.");
				}
				request.setAttribute("customers", customers);
			} else if ("generate".equals(action)) {
				
				GenerateBankAccount generateAccount1 = new GenerateBankAccount();
				boolean accountGenerated = generateAccount1.GenerateAccount(customerIdParam);

				if (accountGenerated) {
					request.setAttribute("successMessage", "Account created successfully!");
				} else {
					request.setAttribute("errorMessage", "Error creating account.");
				}
			}
			request.setAttribute("AddAccount", "AddAccount");
			request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
		} catch (CustomerNotFounded e) {
			request.setAttribute("errorMessage", "Customer Does not Exist.");
			request.setAttribute("AddAccount", "AddAccount");
			request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("errorMessage", "Error creating account.");
			request.setAttribute("AddAccount", "AddAccount");
			request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}