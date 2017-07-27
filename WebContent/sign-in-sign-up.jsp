<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="SHS Library Books and Meeting Room Reservations">
    <title>Lib.U</title>
    
    <!--online resources-->
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet"> <!-- font -->
    
    <!--local resources-->
    <!-- apis -->
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" href="css/navbar-fixed-side.css" />
    <link rel="stylesheet" type="text/css" href="css/navbar-redesigned.css"/>
    <link rel="stylesheet" type="text/css" href="img/icons_by_freepik/font/flaticon.css"> 
    <link rel="stylesheet" type="text/css" href="css/content.css">
</head>

<body>
<div class="container-fluid">
  <div class="row">
    <!-- NAV BAR -->
    <jsp:include page="reusable/navbar.jsp"/>    
    <!-- END OF NAV BAR -->   
    <div class="col-sm-9 col-lg-10 content">
      <!-- your page content -->
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
      </div>

      <div id="overlay-screen" style="display: none;"></div>
      
      <!-- SEARCH BAR -->
      <jsp:include page="reusable/search-bar-toggable.jsp"/>    
      <!-- END OF SEARCH BAR -->  

      
      <!-- Sign Up button | Sign In Form -->
      <div class="row">
        <div class="col-xs-5 bordered-dark">
        <h3 align="center">Create an Account</h3> 
        <div class="divider-dark"></div>
        <p align="center" style="margin: 20px 0">Please enter your ID number to create an account.</p>
        <form action="SignUpServlet" method="post">
        <div class="form-group">
          <label for="exampleInputEmail1">ID Number</label>
          <input type="number" id="register-idnum" name = "id_number" class="form-control form-components-rd erase-margin" id="exampleInputEmail1" placeholder="ID Number">
        </div>
        <div class="form-group">
          <button type="submit" id="submit-register-id" class="btn btn-default submit-btn submit-btn form-components-rd auto-width erase-margin">Sign Up</button>
        </div>
        </form>
  
        </div>
        <div class="col-xs-5 col-xs-offset-2 bordered-dark">
            <h3 align="center">Already Registered?</h3> 
            <div class="divider-dark"></div>
            <!--Sign In-->
            <form class="form-horizontal" action="SignInServlet" method="post">
              <div class="form-group">
                <label for="login-idnum" class="col-sm-2 control-label">ID #</label>
                <div class="col-sm-10">
                  <input type="number" name="id_number" class="form-control form-components-rd erase-margin" id="login-idnum" placeholder="ID Number">
                </div>
              </div>
              <div class="form-group">
                <label for="login-password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                  <input type="password" name="passwordHash" class="form-control form-components-rd erase-margin" id="login-password" placeholder="Password">
                </div>
              </div>

              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                <input type= "hidden" name = "SignIn"/> 
                  <button type="submit" id="submit-login" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Sign in</button>
                </div>
              </div>
            </form>
            <br>
        </div>
        
      </div>


    	<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->
<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="signInForm" action="SignInSignUpPageServlet" method="post"></form>
<form id="homeForm" action="HomePageServlet" method="post"></form>
<form id="yourReservationsForm" action="CalendarOrgRepServlet" method="post"></form>

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/menu-links.js"></script>
<script src="js/app.js"></script>			
<!-- //////////////////// -->


</body>
</html>
