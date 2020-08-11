package com.logindao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAO{
	public boolean check(String usertype, String username, String password) throws ClassNotFoundException, SQLException{
		if(usertype.charAt(0)=='i')
			 usertype="inventory_admin";
		else
			usertype="delivery_agent";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa_inventory","root","");
		PreparedStatement st=con.prepareStatement("select * from users where usertype=? and username=? and passwrd=?");
		st.setString(1, usertype);
		st.setString(2, username);
		st.setString(3, password);
		ResultSet res=st.executeQuery();
		
		if(res.next()) {
			System.out.print(res.getString(1));
			return true;}
		else
			return false;
		
	}
}
