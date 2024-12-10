package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.exception.LoginExcepetion;
import com.aurionpro.service.DbConnect;
import com.aurionpro.service.LoginService;

@WebServlet("/LoginAllController")
public class LoginAllController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	       LoginService login =  new LoginService();
				try {
					boolean isValid = false;
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String role = request.getParameter("role");
			
			if ((role).equalsIgnoreCase("Customer")) {
			     isValid = login.userLogin(username, password);
			
			}
			
			if ((role).equalsIgnoreCase("Admin")) {
				isValid = login.adminLogin(username, password);
				
			}
			if (isValid) {

				if ((role).equalsIgnoreCase("Customer")) {
					HttpSession session = request.getSession(true);
		             session.setAttribute("username", username);
					response.sendRedirect("UserDashboard.jsp"); 
				}else if ((role).equalsIgnoreCase("Admin")) {
					HttpSession session = request.getSession(true);
		             session.setAttribute("username", username);
					request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
                  }
				}else {
			        	 request.setAttribute("errorMessage", "Invalid username or password.");
			             request.getRequestDispatcher("/Login.jsp").forward(request, response);
				}

			
		} catch ( LoginExcepetion e) {
			 request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}