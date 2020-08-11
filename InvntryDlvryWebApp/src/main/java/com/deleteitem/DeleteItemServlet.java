package com.deleteitem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.inventory.*;

@WebServlet("/deleteitem")
public class DeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id.equals(""))
			response.sendRedirect("error.jsp");
		InventoryDAO invn=new InventoryDAO();
		try {
			if(invn.deletedata(id))
				response.sendRedirect("invntry.jsp");
			else
				response.sendRedirect("error.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
