package com.delivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryDAO {
	public ResultSet gettable(String agent) throws ClassNotFoundException, SQLException {
		
		String query="select customer.customer_ID,"
					+ "customer.customer_name,"
					+ "customer.address,customer.phone_number,"
					+ "inventory.Brand,"
					+ "inventory.Product_ID,"
					+ "inventory.Item_name,"
					+ "inventory.retail_price"
					+ " from customer inner join inventory "
					+ "on customer.Product_ID=inventory.Product_ID"
					+ " where customer.customer_ID in ("
					+ "select customer_ID from delivery_assign where delivery_agent=?)";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,agent);
		ResultSet res=pst.executeQuery();
		return res;		
	}
	public ResultSet getcustomers(String name) throws ClassNotFoundException, SQLException {
		
		String query="select customer.customer_ID,"
					+ "customer.customer_name,"
					+ "customer.address,"
					+ "inventory.Brand,"
					+ "inventory.Product_ID,"
					+ "inventory.Item_name,"
					+ "inventory.retail_price"
					+ " from customer inner join inventory "
					+ "on customer.Product_ID=inventory.Product_ID"
					+ " where customer.customer_name =?";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,name);
		ResultSet res=pst.executeQuery();
		return res;		
	}
	public ResultSet gettodays(String name) throws ClassNotFoundException, SQLException {
		
		String query="select customer.customer_ID,\r\n" + 
				"		customer.customer_name,\r\n" + 
				"		customer.address,\r\n" + 
				"        inventory.Brand,\r\n" + 
				"		inventory.Product_ID,\r\n" + 
				"        inventory.Item_name,\r\n" + 
				"        inventory.retail_price\r\n" + 
				"from customer inner join inventory on customer.Product_ID=inventory.Product_ID\r\n" + 
				"where customer.customer_ID in\r\n" + 
				"(select delivery_assign.customer_ID \r\n" + 
				"from delivery_assign inner join delivery_items  on delivery_assign.customer_ID=delivery_items.customer_ID \r\n" + 
				"where delivery_assign.delivery_agent=? and delivery_items.today=true)";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,name);
		ResultSet res=pst.executeQuery();
		return res;		
	}
	public ResultSet getpendings(String name) throws ClassNotFoundException, SQLException {
		
		String query="select customer.customer_id,\r\n" + 
				"		customer.customer_name,\r\n" + 
				"        customer.address,\r\n" + 
				"        inventory.brand,\r\n" + 
				"        inventory.product_id,\r\n" + 
				"        inventory.item_name,\r\n" + 
				"        inventory.retail_price,\r\n" + 
				"        delivery_items.door_locked\r\n" + 
				"from delivery_items natural join customer\r\n" + 
				"natural join inventory\r\n" + 
				"where customer.customer_id in(\r\n" + 
				"select customer_id from delivery_assign where delivery_agent=?\r\n" + 
				") and delivery_items.pending=true";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,name);
		ResultSet res=pst.executeQuery();
		return res;		
	}
	public ResultSet getpriority(String name) throws ClassNotFoundException, SQLException {
		
		String query="select customer.customer_ID,\r\n" + 
				"		customer.customer_name,\r\n" + 
				"		customer.address,customer.phone_number,\r\n" + 
				"        inventory.Brand,\r\n" + 
				"		inventory.Product_ID,\r\n" + 
				"        inventory.Item_name,\r\n" + 
				"        inventory.retail_price\r\n" + 
				"from customer inner join inventory on customer.Product_ID=inventory.Product_ID\r\n" + 
				"where customer.customer_ID in\r\n" + 
				"(select delivery_assign.customer_ID \r\n" + 
				"from delivery_assign inner join delivery_items  on delivery_assign.customer_ID=delivery_items.customer_ID \r\n" + 
				"where delivery_assign.delivery_agent=? and delivery_items.priority=true)";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,name);
		ResultSet res=pst.executeQuery();
		return res;
	}
	public boolean condlvry(Object attribute, Object attribute2, Object attribute3) {
		try {
			
			if((String)attribute==null || (String)attribute2==null || (String)attribute3==null)
				return false;
			String query1="delete from delivery_assign where customer_ID=?";
			String query2="update inventory set available=false where Product_ID=?";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
			PreparedStatement pst=con.prepareStatement(query1);
			pst.setString(1,(String)attribute);
			pst.execute();
			pst=con.prepareStatement(query2);
			pst.setString(1,(String)attribute3);
			pst.execute();
			
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public boolean returnlvry(String bool, String id,String cid){
		try {
			
			String query1="delete from delivery_assign where customer_ID=?";
			String query2="";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
			PreparedStatement pst=con.prepareStatement(query1);
			pst.setString(1,cid);
			pst.execute();
			if(bool.equals("true")) {
				query2="update inventory set damage=true where Product_ID=?";
			}
			else query2="update inventory set damage=false where Product_ID=?";
			pst=con.prepareStatement(query2);
			pst.setString(1,id);
			pst.execute();
			
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public boolean doorlock(String cid) {
		
		try {
			
			String query1="update delivery_items set door_locked=true,pending=true,today=false where customer_ID=?";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
			PreparedStatement pst=con.prepareStatement(query1);
			pst.setString(1,cid);
			pst.execute();
			
			
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public ResultSet gettrack(String custid) throws ClassNotFoundException, SQLException {
		
		String query="select customer.customer_id,\r\n" + 
				"		customer.customer_name,customer.phone_number,\r\n" + 
				"        inventory.brand,\r\n" + 
				"        inventory.product_id,\r\n" + 
				"        inventory.item_name,\r\n" + 
				"        inventory.retail_price,\r\n" + 
				"        delivery_assign.current_location,\r\n" + 
				"        customer.address\r\n" + 
				"from delivery_assign natural join customer natural join inventory\r\n" + 
				"where customer.customer_id =?";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,custid);
		ResultSet res=pst.executeQuery();
		return res;
	}
}

