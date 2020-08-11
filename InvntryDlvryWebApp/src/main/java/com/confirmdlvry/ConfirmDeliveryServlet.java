package com.confirmdlvry;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delivery.DeliveryDAO;

@WebServlet("/confirmdlvry")
public class ConfirmDeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String cid=request.getParameter("customerid");
			String name=request.getParameter("customername");
			String pid=request.getParameter("productid");
			if(cid.equals("") || name.equals("") || pid.equals(""))
				response.sendRedirect("error.jsp");
			DeliveryDAO ddao=new DeliveryDAO();
		if(ddao.condlvry(cid,name,pid)) {
			response.sendRedirect("dlvry.jsp");
		}
		else
			response.sendRedirect("error.jsp");
		}catch(Exception e) {
			response.sendRedirect("error.jsp");
		}
	}

}


