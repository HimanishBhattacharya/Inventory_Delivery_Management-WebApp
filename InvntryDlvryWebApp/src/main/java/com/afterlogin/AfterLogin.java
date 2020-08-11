package com.afterlogin;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logindao.*;

@WebServlet("/aftrlogin")
public class AfterLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype=(String)request.getParameter("usertype");
		String username=(String)request.getParameter("username");
		String password=(String)request.getParameter("password");
		
		
		LoginDAO login=new LoginDAO();
		try {
			if(login.check(usertype,username,password)) {
				HttpSession session=request.getSession();
				session.setAttribute("usertype",usertype);
				session.setAttribute("username",username);
				session.setAttribute("password",password);
				
				if(usertype.equals("inventory admin"))
					response.sendRedirect("invntry.jsp");
				else	
					response.sendRedirect("dlvry.jsp");
			}
			else
				response.sendRedirect("index.jsp");
			
		} catch (Exception e) {
			response.sendRedirect("error.jsp");
		} 
		
	}

	

}
