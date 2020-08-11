<html>
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
     <div class="container col-sm-12 text-center" style="background-color: #0077ff; color:antiquewhite"><h2 class="pt-2 pb-2">Login</h2></div>
      <div class="container-fluid" style="margin-top: 10%;">
        <div class="row">
          <div class="col-sm-12 text-center">
          <form action="aftrlogin" method="post">
            <h6 style="color: black; "> User Type <select name="usertype" placeholder="inventory admin"><option>inventory admin</option><option>delivery agent</option></select></h6><br><br>
           <h6 style="color: black; "> Username <input name="username" type="email" placeholder="e.g-abc@gmail.com"></h6><br><br>
          <h6 style="color: black; "> Password <input name="password" type="password" placeholder="*****"><br><br></h6>
            <button class="btn btn-primary" type="submit" style="height:50px; width: 200px;color:beige!important;">Login</button>
          </form>
          </div>
        </div>  
      </div>"
    </body>
</html>