<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
    <link rel="stylesheet" type="text/css" href="vitalets-bootstrap-datepicker-c7af15b/css/datepicker.css"/>
    <link rel="stylesheet" type="text/css" href="css/content.css"> 
</head>

<body >

<div class="container-fluid">
  <div class="row">
    <!-- NAV BAR -->
    <jsp:include page="reusable/navbar.jsp"/>    
    <!-- END OF NAV BAR -->
    <div class="col-sm-9 col-lg-10 content">
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
      </div>

      <div id="overlay-screen" style="display: none;"></div>
      <!-- SEARCH BAR -->
      <jsp:include page="reusable/search-bar-toggable.jsp"/>    
      <!-- END OF SEARCH BAR -->  
      	
      <form id = "register-details" style="margin-right: 300px;" action="RegisterUserServlet" method="POST">
      <!-- names -->
        <div class="row">
          <div class="col-md-5"><label for="first-name">First Name</label></div>
          <div class="col-md-2"><label for="middle-name">Middle Initial</label></div>
          <div class="col-md-5"><label for="last-name">Last Name</label></div>
        </div>
        <div class="row" align="center">
          <div class="col-md-5">
            <input type="text" class="form-control form-components-rd" name="first_name" id="first-name" placeholder="Juan Coco" required></div>
          <div class="col-md-2">
            <input type="text" class="form-control form-components-rd" name="middle_initial" id="middle-name" placeholder="S" required>
          </div>
          <div class="col-md-5">
            <input type="text" class="form-control form-components-rd" name="last_name" id="last-name" placeholder="Dela Cruz " required>
          </div>
        </div>

        <label for="password">Password</label>
        <input type="password" class="form-control form-components-rd" name="passwordHash" id="password" placeholder="Password" style="width:70%" required>
          
        <label for="email-address">Email Address</label>
        <input type="email" class="form-control form-components-rd" name="email_address" id="email-address" placeholder="Email" style="width:70%" required>
    
        <label for="idnumber">ID Number</label>
        <input type="number" class="form-control form-components-rd" name="id_number" id="idnumber" placeholder="Email" style="width:70%" required>
                    
        <label for="bdaypicker">Birthday</label>
        <div id="bdaypicker" class="input-append date" id="dp3" data-date-format="yyyy-mm-dd" style="width:70%">
          <input class="span2 form-components-rd" size=11 type="text" value="12-02-2012" readonly
          style="width:87%" name="birthday" required>
          <span class="add-on" style="width:10%"><i class="flaticon-calendar"></i></span>
        </div>
	
      
        <div class="bordered-dark auto-width" style="margin: 10px 0; padding: 10px; width:70%; border-radius: 5px;">
          <label for="secret-question" required>Secret Question</label>
          <select id = "secret-question" name="sqID" class="form-control form-components-rd"  
          			style="margin-left: 0; margin-right: 0;" required>
            <option value="" selected disabled>Select Secret Question</option>
          </select>
  
          <label for="secret-answer">Your Answer</label>
          <input type="text" class="form-control form-components-rd" name="sq_answer" id="secret-answer" placeholder="Your Answer" style="margin-left: 0; margin-right: 0;" required>
        </div>

        <button type="submit" id="submit-register-id" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Sign Up</button>
      </form>
      

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
<script src="js/register.js"></script>
</body>

</html>
