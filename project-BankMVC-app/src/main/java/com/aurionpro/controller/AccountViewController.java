package com.aurionpro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.entity.Account;
import com.aurionpro.service.AccountService;


@WebServlet("/AccountViewController")
public class AccountViewController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String username =  (String) request.getSession().getAttribute("username");
		AccountService service =  new AccountService();
		
		List<Account> accounts = service.getAccountOfCustomer(username);
		
		if(accounts.isEmpty()) {
			request.setAttribute("ViewAccounts", "ViewAccounts");
			request.setAttribute("errorMessage", "No Account Exist");
			request.getRequestDispatcher("/UserDashboard.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("ViewAccounts", "ViewAccounts");
		request.setAttribute("accounts", accounts);
		request.getRequestDispatcher("/UserDashboard.jsp").forward(request, response);
		
	}catch(ServletException e) {
		request.setAttribute("errorMessage", "Something Went Wrong!.Please try agian ");
		request.getRequestDispatcher("/UserDashboard.jsp").forward(request, response);
	}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
