package com.additem;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.inventory.*;
@WebServlet("/addingitem")
public class AddItemServLet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
    	try{
    		String product_id="";
    		product_id+=(String)req.getParameter("item_id");
    		String brand="";
    		brand+=(String)req.getParameter("brand");
    		String category="";
    		category+=(String)req.getParameter("category");
    		String name="";
    		name+=(String)req.getParameter("name");
    		double price=Double.parseDouble(req.getParameter("price"));
    		double sell_price=Double.parseDouble(req.getParameter("s_price"));
    		boolean perishable=Boolean.parseBoolean(req.getParameter("perish"));
    		
    		InventoryDAO invntry=new InventoryDAO();
    		if(invntry.putDatas(product_id, brand, category, name, price, sell_price, perishable))
    			res.sendRedirect("invntry.jsp");
    		else
    			res.sendRedirect("error.jsp");
    	}catch(Exception e) {
    		res.sendRedirect("error.jsp");
    	}
    }

}
