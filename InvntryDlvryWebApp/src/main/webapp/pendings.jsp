<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
  <head>
    <title>Delivery</title>
    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> 
    <script src="dlvryjs.js"></script> 
  </head>
  	<%@ page import="com.delivery.*,java.sql.ResultSet"%>
  	<%
  		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
  		if((String)session.getAttribute("username")==null)
  				response.sendRedirect("index.jsp");
  	%>
    <body>
     <div class="container col-sm-12 text-center" style="position:absolute; background-color: #0077ff; color:antiquewhite"><h2 class="pt-2 pb-2">Delivery</h2></div>
     <div class="container col-sm-12 text-right" style="position:relative;">
       <form action="logout"> <button class="btn btn-success mt-2">Log out</button></form>
    </div>
        <div class="container-fluid mt-5" style="position: relative;">
            <div class="row">
                <div class="col-sm-6 text-center mb-5">
                    <form action="searchcustomer.jsp" class="ml-5" ><input style="width:60%;" type="text" placeholder="search by customer name" name="customer_name">
                    <button class="btn btn-primary" style="width:20%">Search</button></form>
                </div>
                <div class="col-sm-3 text-center mb-5">
                    <button class="btn btn-primary mr-2" onclick="gototodays()" style="width:40%">Today's Deliveries</button>
                    <script>
                    	function gototodays(){
                    		window.location.href="todaysdlvry.jsp";
                    	}
                    </script>   
                    <button class="btn btn-primary mr-2" onclick="gotoall()" style="width:40%">All Deliveries</button> 
                    <script>
                    	function gotoall(){
                    		window.location.href="dlvry.jsp";
                    	}
                    </script> 
                </div>
                <div class="col-sm-3 text-center mb-5">
                  <button style="width:40%" onclick="gotopendings()" class="btn btn-primary mr-2">Pendings</button>
                  <script>
                    	function gotopendings(){
                    		window.location.href="pendings.jsp";
                    	}
                    </script> 
                </div>
          
        </div>  
            <div class="row">
                <div class="col-sm-6 text-center mb-5">
                   <form action="tracking.jsp" class="ml-5" ><input style="width:60%;" type="text" placeholder="track delivery here" name="customerid">
                    <button class="btn btn-primary" type="submit" style="width:20%">Track</button></form>
                </div>
                <div class="col-sm-3 text-center mb-5">
                    <button style="width:40%" data-toggle="modal" data-target="#myModal4" class="btn btn-primary mr-2">Door Locked</button>
                    <!-- Modal -->
                    <div id="myModal4" class="modal fade" role="dialog">
                        <div class="modal-dialog">
  
                             <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <div class=" container-fluid modal-body">
                                    <form class="ml-0 text-left" action="doorlocked">
                                        
                                        <h6>Customer ID</h6>
                                        <input type="text" name="custid" style="width: 100%;"><br>
                                    
                                
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary" style="width:40%">Confirm</button>
                                </div></form>
                            </div>
  							</div>
                         </div>
                     </div>
               
                    <button style="width:40%" class="btn btn-primary mr-2"  data-toggle="modal" data-target="#myModal3">Return</button>
                    <!-- Modal -->
                    <div id="myModal2" class="modal fade" role="dialog">
                      <div class="modal-dialog">

                           <!-- Modal content-->
                          <div class="modal-content">
                              <div class="modal-header">
                                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                              </div>
                              <div class=" container-fluid modal-body">
                                  <form class="ml-0 text-left" action="confirmdlvry">
                                      <h6>Customer-ID</h6>
                                      <input type="text" name="customerid" style="width: 100%;"><br>
                                      <h6>Customer Name</h6>
                                      <input type="text" name="customername" style="width: 100%;"><br>
                                      <h6>Product-ID</h6>
                                      <input type="text" name="productid" style="width: 100%;"><br>
                                  
                              
                              	<div  class="modal-footer">
                                  <button type="submit" class="btn btn-primary" style="width:40%">Confirm</button>
                               </div>
                              </form>
						</div>
                    	</div>
                    	</div>
                     </div>
                </div>
                <div class="col-sm-3 text-center mb-5">
                    <button style="width:40%" onclick="gotopriority()" class="btn btn-primary mr-2">Priority List</button>
                    <script>
                    	function gotopriority(){
                    		window.location.href="priority.jsp";
                    	}
                    </script> 
                    <button style="width:40%" class="btn btn-primary mr-2" data-toggle="modal" data-target="#myModal2">Confirm Delivery</button>
                     <!-- Modal -->
                     <div id="myModal3" class="modal fade" role="dialog">
                        <div class="modal-dialog">

                           <!-- Modal content-->
                             <div class="modal-content">
                                 <div class="modal-header">
                                     <button type="button" class="close" data-dismiss="modal">&times;</button>
                                 </div>
                                 <div class=" container-fluid modal-body">
                                     <form class="ml-0 text-left" action="returndlvry">
                                     	<h6>Customer ID</h6>
                                        <input type="text" name="cid" style="width: 100%;"><br>
                                     	<h6>Product ID</h6>
                                        <input type="text" name="id" style="width: 100%;"><br>
                                         <h6>Damage</h6>
                                         <input type="radio" name="item_damage" value="true">Yes<br>
                                        <input type="radio" name="item_damage" value="false">No<br>
                                         <h6>Reason for Returning</h6>
                                        <input type="text" name="reason" style="width: 100%;"><br>
                                     
                                 
                                 <div  class="modal-footer">
                                     <button type="submit" class="btn btn-primary" style="width:40%">Confirm Returning</button>
                                 </div>
                                 </form>
                            </div>
							</div>
                       </div>
                    </div>
                </div>
            </div>
             </div>   
            <div class="container-fluid">
            	<div class="row">
            	<div class="col-sm-12 text-left">
            		<table class="table table-primary table-hover table-responsive-sm" style="width:100%;">
    <thead>
      <tr class="light">
        <th>Customer ID</th>
        <th>Customer Name</th>
        <th>Address</th>
        <th>Product Brand</th>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Retail price</th>
        <th>Door Locked</th>
      </tr>
    </thead>
    <tbody>
    	<%
    		DeliveryDAO dlvrydao=new DeliveryDAO();
    		ResultSet res=dlvrydao.getpendings((String)session.getAttribute("username"));
    		String bool="";
    		while(res.next()){
    			%> <tr>
      					<td><%= res.getString(1) %></td>
      					<td><%= res.getString(2) %></td>
      					<td><%= res.getString(3) %></td>
        				<td><%= res.getString(4) %></td>
        				<td><%= res.getString(5) %></td>
        				<td><%= res.getString(6) %></td>
        				<td><%= res.getDouble(7) %></td>
        				<td><%= res.getBoolean(8) %></td>
      				</tr>
    		<% }%>
     
    </tbody>
  </table>
            	</div>
            </div>
            </div>
    </body>
</html>