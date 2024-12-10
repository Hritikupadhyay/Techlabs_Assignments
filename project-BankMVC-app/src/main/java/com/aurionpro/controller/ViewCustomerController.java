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

@WebServlet("/ViewCustomerController")
public class ViewCustomerController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerIdParam = request.getParameter("customerId");
		CustomerService service = new CustomerService();
		List<Customer> customers = null;

		try {

			if (customerIdParam != null && !customerIdParam.isEmpty()) {
				
				int customerId = Integer.parseInt(customerIdParam);
				
				customers = service.getCustomer(customerId);
				
			} else {
              
				customers = service.getAllCustomer();
				
				
			}
			request.setAttribute("ViewCustomer", "ViewCustomer");
			request.setAttribute("customers", customers);
			request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
		} catch (CustomerNotFounded e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "An error occurred while fetching customers.");
			request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}