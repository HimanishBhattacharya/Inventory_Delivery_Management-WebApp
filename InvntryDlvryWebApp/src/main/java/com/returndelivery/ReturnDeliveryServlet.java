package com.returndelivery;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delivery.DeliveryDAO;

@WebServlet("/returndlvry")
public class ReturnDeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bool=(String)request.getParameter("item_damage");
		
		String cid=(String)request.getParameter("cid");
		String id=(String)request.getParameter("id");
		DeliveryDAO ddao=new DeliveryDAO();
		if(ddao.returnlvry( bool, id, cid))
			response.sendRedirect("dlvry.jsp");
		else
			response.sendRedirect("error.jsp");
	}

	

}
