<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    	<!-- API -->
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" href="css/navbar-fixed-side.css" />
    	<!-- SELF -->
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

      <h2 align="center">Activity Log</h2><br><br>

	  <!-- CURRENT / PENDING RM RESERVATIONS AND BORROWS-->
      <div class="panel panel-default">
        <div class="panel-heading">
          <a data-toggle="collapse" href="#current-book-log">Current / Pending Reading Material/s</a>
        </div>
        
        <div id="current-book-log" class="panel-collapse collapse in">
          <div class="panel-body">
            
            <div class="log-item bordered-dark">
              <a class="title">Analyzing Driving Risks of Roadway Traffic under Adverse Weather Conditions: In Case of Rain Day</a>
              <div class="status reserved-status">Reserved</div>
              Reserved on : <span class="date_reserved">mm / dd / yyyy</span><br>
              Return by : <span class="date_returned">mm / dd / yyyy</span>
            </div>

            <div class="log-item bordered-dark">
              <a class="title">Analyzing Driving Risks of Roadway Traffic under Adverse Weather Conditions: In Case of Rain Day</a>
              <div class="status borrowed-status">Borrowed</div>
              Borrowed on : <span class="date_reserved">mm / dd / yyyy</span><br>
              Return by : <span class="date_returned">mm / dd / yyyy</span>
            </div>

          </div>
        </div>
      </div>


	  <!-- PAST BORROWED READING MATERIALS -->
      <div class="panel panel-default">
        <div class="panel-heading">
          <a data-toggle="collapse" href="#previous-book-log">Previously Borrowed Reading Material/s</a>
        </div>
        
        <div id="previous-book-log" class="panel-collapse collapse in">
          <div class="panel-body">
            
            <div class="log-item bordered-dark">
              <a class="title">Analyzing Driving Risks of Roadway Traffic under Adverse Weather Conditions: In Case of Rain Day</a>
              <br>
              Borrowed on : <span class="date_borrowed">mm / dd / yyyy</span><br>
              Returned by : <span class="date_returned">mm / dd / yyyy</span>
            </div>

          </div>
        </div>
      </div>
	
	  <!-- RESERVED MEETING ROOMS PANEL -->
      <div class="panel panel-default">
        <div class="panel-heading">
          <a data-toggle="collapse" href="#previous-book-log">Reserved Meeting Rooms</a>
        </div>
        
        <div id="previous-book-log" class="panel-collapse collapse in">
          <div class="panel-body">
            
            <div class="log-item bordered-dark">
              <a class="room_no">R201</a><br>
              Reserved on : <br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="date_reserved">mm / dd / yyyy</span> <br> 
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="time_reserved">0930 am</span>
            </div>

          </div>
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
