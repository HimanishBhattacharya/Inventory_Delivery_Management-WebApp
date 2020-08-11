<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ page import="com.inventory.*,java.sql.ResultSet"%>
	<% 
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
      				String username=(String)session.getAttribute("username");
      				String usertypr=(String)session.getAttribute("usertype");
      				String password=(String)session.getAttribute("password");
      				
      				if(username==null){
      					response.sendRedirect("index.jsp");
      				}
     %>
     
  <head>
    <title>Inventory</title>
    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> 
    <script src="dlvryjs.js"></script> 
  </head>
    <body>
     <div class="container col-sm-12 text-center" style="position:absolute; background-color: #0077ff; color:antiquewhite"><h2 class="pt-2 pb-2">Inventory</h2></div>
     <div class="container col-sm-12 text-right" style="position:relative;">
        <form action="logout"><button class="btn btn-success mt-2" >Log out</button></form>
    </div>
        <div class="container-fluid mt-5" style="position: relative;">
            <div class="row">
                <div class="col-sm-6 text-center mb-5">
                    <form action="searchid.jsp"  class="ml-5" ><input style="width:60%;" type="text" placeholder="search by product-id" name="product_id">
                    <button class="btn btn-primary" type="submit" style="width:20%">Search</button></form>
                </div>
                <div class="col-sm-3 text-center mb-5">
                    <button class="btn btn-primary mr-2" data-toggle="modal" data-target="#myModal" style="width:40%">Add Item</button>
                        <!-- Modal -->
                        <div id="myModal" class="modal fade" role="dialog">
                            <div class="modal-dialog">
  
                                 <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <div class=" container-fluid modal-body">
                                        <form class="ml-0 text-left" action="addingitem" method="post">
                                            <h6>Item-ID</h6>
                                            <input type="text" name="item_id" style="width: 100%;"><br>
                                            <h6>Brand</h6>
                                            <input type="text" name="brand" style="width: 100%;"><br>
                                            <h6>Item Category</h6>
                                            <input type="text" name="category" style="width: 100%;"><br>
                                            <h6>Item Name</h6>
                                            <input type="text" name="name" style="width: 100%;"><br>
                                            <h6>Cost Price</h6>
                                            <input type="text" name="price" style="width: 100%;"><br>
                                            <h6>Retail Price</h6>
                                            <input type="text" name="s_price" style="width: 100%;"><br>
                                            <h6>Perishable</h6>
                                            <h7 class="mr-1">Yes</h7><input class="mr-3" type="radio" name="perish" value="true">    
                                            <h7 class="mr-1">No</h7><input type="radio" name="perish" value="false"><br><br>
                                            <h6>Upload Item image</h6><br>
                                            <input type="file" id="myFile" name="filename">
                                   			
                                   			
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-primary"  style="width:40%">Add</button>
                                            </div>
                                        	</form>
                                    </div>
                                </div>
  
                             </div>
                        </div>
                    <button style="width:40%" class="btn btn-primary mr-2" data-toggle="modal" data-target="#myModal2">Delete Item</button>
                     <!-- Modal -->
                        <div id="myModal2" class="modal fade" role="dialog">
                            <div class="modal-dialog">
  
                                 <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <div class=" container-fluid modal-body">
                                        <form class="ml-0 text-left" action="deleteitem">
                                            <h6>Item-ID</h6>
                                            <input type="text" name="id" style="width: 100%;"><br>
                                   			
                                   			
                                            <div class="modal-footer">
                                               <button type="submit" class="btn btn-primary"  style="width:40%">Delete</button> 
                                            </div></form>
                                        
                                    </div>
                                </div>
  
                             </div>
                        </div>
                        <button style="width:45%" class="btn btn-primary mt-5 mr-2" onclick="gotodlvrytransit()">Delivery Transit</button>
                         <script>
                   		function gotodlvrytransit(){
                   			window.location.href = "dlvrytransit.jsp";
                   		}
                   </script>
                         <button style="width:35%" class="btn btn-primary mt-5 mr-2" onclick="gotodmgedtransit()">Damaged in Transit</button>
                         <script>
                   		function gotodmgedtransit(){
                   			window.location.href = "dmgedtransit.jsp";
                   		}
                   </script>
                </div>
                <div class="col-sm-3 text-center mb-5">
                    <button style="width:40%" onclick="gotoavailable()" class="btn btn-primary mr-2">Available</button>
                   	<script>
                   		function gotoavailable(){
                   			window.location.href = "available.jsp";
                   		}
                   </script>
                    <button style="width:40%"  onclick="gotosold()" class="btn btn-primary mr-2">Sold</button>
                    <script>
                   		function gotosold(){
                   			window.location.href = "sold.jsp";
                   		}
                   </script>
                    <button style="width:40%" onclick="gotoinvntry()" class="btn btn-primary mt-5 mr-2">All</button>
                    <script>
                   		function gotoinvntry(){
                   			window.location.href = "invntry.jsp";
                   		}
                   </script>
                </div>
          
        </div>  
            <div class="row">
                <div class="col-sm-6 text-center mb-5">
                    <form action="searchcat.jsp" class="ml-5" ><input style="width:60%;" type="text" placeholder="search by category" name="product_cat">
                    <button class="btn btn-primary" type="submit" style="width:20%">Search</button></form>
                </div>
                <div class="col-sm-3 text-center mb-5">
                   <button style="width:40%" type="submit" onclick="gotodamaged()" class="btn btn-primary  mr-2">Damaged</button>
                   <script>
                   		function gotodamaged(){
                   			window.location.href = "damaged.jsp";
                   		}
                   </script>
                   <button style="width:40%" type="submit" onclick="gotonotdamaged()" class="btn btn-primary mr-2">Not Damaged</button>
                   <script>
                   		function gotonotdamaged(){
                   			window.location.href = "notdamaged.jsp";
                   		}
                   </script>
                </div>
                <div class="col-sm-3 text-center mb-5">
                    <button type="submit" style="width:40%" onclick="gotoexpired()" class="btn btn-primary mb-2 mr-2">Expired</button>
                    <script>
                   		function gotoexpired(){
                   			window.location.href = "expired.jsp";
                   		}
                   </script>
                    <button type="submit" style="width:40%" onclick="gotoperishable()" class="btn btn-primary mr-2">Perishable</button>
                    <script>
                   		function gotoperishable(){
                   			window.location.href = "perishable.jsp";
                   		}
                   </script>
                </div>
            </div>
            <div class="row">
            	<div class="col-sm-12 text-left">
            		<table class="table table-primary table-hover table-responsive-sm" style="width:100%;">
    <thead>
      <tr class="light">
        <th>Product ID</th>
        <th>Brand</th>
        <th>Category</th>
        <th>Item name</th>
        <th>Inventory price</th>
        <th>Retail price</th>
        <th>Perishable</th>
        <th>Expiry</th>
        <th>Damage</th>
        <th>Availability</th>
      </tr>
    </thead>
    <tbody>
    	<%
    		InventoryDAO invntrydao=new InventoryDAO();
    		ResultSet res=invntrydao.getavailabledata();
    		String bool="";
    		while(res.next()){
    			%> <tr>
      					<td><%= res.getString(1) %></td>
      					<td><%= res.getString(2) %></td>
      					<td><%= res.getString(3) %></td>
        				<td><%= res.getString(4) %></td>
        				<td><%= res.getString(5) %></td>
        				<td><%= res.getString(6) %></td>
        				<td><% if(res.getBoolean(7)) bool="Yes"; else bool="No";%><%=bool %></td>
        				<td><% if(res.getBoolean(8)) bool="Expired"; else bool="Not Expired";%><%=bool %></td>
        				<td><% if(res.getBoolean(9)) bool="Damaged"; else bool="Not Damaged";%><%=bool %></td>
        				<td><% if(res.getBoolean(10)) bool="Available"; else bool="Sold";%><%=bool %></td>
      				</tr>
    		<% }%>
     
    </tbody>
  </table>
            	</div>
            </div>
      </div>
    </body>
</html>