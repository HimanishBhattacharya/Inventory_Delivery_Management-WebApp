package com.inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class InventoryDAO {
	public ResultSet getDatas() throws ClassNotFoundException, SQLException {
		ResultSet res = null;
		try{Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		PreparedStatement st=con.prepareStatement("select * from inventory");
		ResultSet rs=st.executeQuery();
		res=rs;
		}catch(Exception e){
		
		}
		return res;
	}
	
	public boolean putDatas(String item_id, String brand, String category, String item_name, double c_price, double s_price, boolean perish) throws ClassNotFoundException, SQLException {
		try{
		boolean expiry=false,damage=false;	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
		PreparedStatement pst=con.prepareStatement("insert into inventory values(?,?,?,?,?,?,?,"+expiry+","+damage+",true)");
		pst.setString(1,item_id);
		pst.setString(2,brand);
		pst.setString(3,category);
		pst.setString(4,item_name);
		pst.setDouble(5,c_price);
		pst.setDouble(6,s_price);
		pst.setBoolean(7,perish);
		pst.execute();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public boolean deletedata(String id) throws ClassNotFoundException, SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
			PreparedStatement st=con.prepareStatement("delete from inventory where Product_ID=?");
			st.setString(1, id);
			st.execute();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public ResultSet getdamageddata() {

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory where damage=true");
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
	}
	public ResultSet getnotdamageddata() {

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory where damage=false");
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
	}
	public ResultSet getexpireddata() {

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory where expiry=true");
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
	}
	public ResultSet getperishabledata() {

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory where perishable=true");
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
	}
	
	public ResultSet getsolddata() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory where available=false");
			
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
		
		
	}
	public ResultSet getsearchediddata(String id) {

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory where Product_ID=?");
			st.setString(1,id);
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
	}
	public ResultSet getsearchedcatdata(String cat) {

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory where category=?");
			st.setString(1,cat);
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
	}
	public ResultSet getavailabledata() {

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory where available=true");
			
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
	}
	public ResultSet getdlvrytransit() {

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory\r\n" + 
					"where Product_ID in(\r\n" + 
					"select Product_ID from customer inner join delivery_assign\r\n" + 
					"on customer.customer_ID=delivery_assign.customer_ID)");
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
	}
	public ResultSet getdmgedtransit(){

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		
			PreparedStatement st=con.prepareStatement("select * from inventory\r\n" + 
					"where Product_ID in(\r\n" + 
					"select Product_ID from customer inner join delivery_assign\r\n" + 
					"on customer.customer_ID=delivery_assign.customer_ID) and damage=true");
			ResultSet res=st.executeQuery();
			return res;
		}catch(Exception e) {
			
		}
		return null;
	}
}
