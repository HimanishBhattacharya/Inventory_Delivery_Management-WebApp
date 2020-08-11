package com.doorlocked;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delivery.DeliveryDAO;

@WebServlet("/doorlocked")
public class DoorLockedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custid=request.getParameter("custid");
		if(custid.equals(""))
			response.sendRedirect("error.jsp");
		DeliveryDAO ddao=new DeliveryDAO();
		if(ddao.doorlock(custid))
			response.sendRedirect("dlvry.jsp");
		else
			response.sendRedirect("error.jsp");
	}

	

}
