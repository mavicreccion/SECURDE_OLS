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
    <!-- apis -->
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" href="css/navbar-fixed-side.css" />
    <link rel="stylesheet" type="text/css" href="css/navbar-redesigned.css"/>
    <link rel="stylesheet" type="text/css" href="img/icons_by_freepik/font/flaticon.css"> 
    <link rel="stylesheet" type="text/css" href="css/content.css"> 
    <link rel="stylesheet" type="text/css" href="css/reservation_table.css"> 
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

      <div id="confirm-reservation" class="pop-up" style="display: none;">
        <h3 align="center">Confirm Reservation?</h3>
        <div align="center" class="divider"></div>

        <h3>Reserving <span id="room-number"></span></h3>

        <div style="padding: 0 20%;" align="left">
          <b>Reserved on</b>: <span id="reserve-date">07 / 09 / 2017</span> by <span id="reserve-time">0930</span>
          <b>Duration</b>: 30 Mins<br>
        </div>

        <br>

        <div class="row">
          <div class="col-md-1"></div>
          <div class="col-md-2"></div>
          <div class="col-md-2"><button id="submit-reserve" class="btn btn-default">Confirm</button></div>
          <div class="col-md-1"></div>
          <div class="col-md-2"><button id="cancel-reserve" class="btn btn-default">Cancel</button></div>
          <div class="col-md-2"></div>
          <div class="col-md-1"></div>
        </div>
      </div>

      <!-- SEARCH BAR -->
      <jsp:include page="reusable/search-bar.jsp"/>    
       <!-- END OF SEARCH BAR -->

      <h3 align="center">Meeting Room Reservation</h3>
      <h4 align="center">Pick a Room, Pick a Time, Confirm!</h4>
      <br><div class="divider"></div><br> 

      <div style="margin: 9px 0;" id="legends">
        <div class="title3" style="display:inline">Legend:</div>
        <div class="openCell legends">Open</div>
        <div class="closedCell legends">Closed</div>
      </div>


      <div id="table-of-rooms" align="center">
        <table id="table-of-rooms" class = "reserveSlots">
          <tr>
            <th>Time</th>
           </tr>
          <tr>
          </tr>
        </table>
      </div>

	<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->

<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="servicesForm" action="CalendarOrgRepServlet" method="post"></form>
<form id="homeForm" action="HomePageServlet" method="post"></form>
<form id="yourReservationsForm" action="CalendarOrgRepServlet" method="post"></form>
<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/menu-links.js"></script>
<script src="js/app.js"></script>			
<!-- //////////////////// -->
<script src="js/reserve-slots.js"></script>
</body>
</html>
