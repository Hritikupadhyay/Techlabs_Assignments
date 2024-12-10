package com.aurionpro.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.service.ProfileService;


@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileService service =  new ProfileService();
		String username =  (String) request.getSession().getAttribute("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
             if ((firstName == null || firstName.trim().isEmpty()) &&
        	    (lastName == null || lastName.trim().isEmpty()) &&
        	    (email == null || email.trim().isEmpty()) &&
        	    (password == null || password.trim().isEmpty())) {

            	 request.setAttribute("errorMessage", "A Field is requried.");
                 request.getRequestDispatcher("/UserDashboard.jsp").forward(request, response);
        	    return;
        	}
		try {
			
			int rowEffected = service.editProfile(email, firstName, lastName, password,username);
			 if (rowEffected > 0) {
                 request.setAttribute("successMessage", "ProfilebEdit Succefully");
                 request.getRequestDispatcher("/LogoutController").forward(request, response);
                
             } else {
             	
                 request.setAttribute("errorMessage", "Error adding customer.");
                
             }
			    request.setAttribute("profile", "profile");
	            request.getRequestDispatcher("/UserDashboard.jsp").forward(request, response);
		}catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "A Field is requried.");
            request.getRequestDispatcher("/UserDashboard.jsp").forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
