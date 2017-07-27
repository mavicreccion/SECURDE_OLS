<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div class="col-sm-3 col-lg-2">
  <nav class="navbar navbar-default navbar-fixed-side">
    <div class="container">
      <div class="navbar-header">
        <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="" id="logo"></a>
      </div>
      <div class="collapse navbar-collapse">
      <br>
        <div class="row" id="toggle-search">
          <div class="col-xs-12 col-md-8 search-button">Search For...</div>
          <div class="col-xs-6 col-md-4"><i class="flaticon-loupe"></i></div>
        </div>
        <ul class="nav navbar-nav">
          <li class="">
            <a href="" id="mRoom">Meeting Rooms</a>
          </li>
          <li class="end">
            <a href="" id="reservations">Your Reservations</a>
          </li>
          <li class="divider"></li>
        </ul>
        <p class="navbar-text">
          Made by
          <a href="http://www.samrayner.com">Sam Rayner</a>
        </p><br><br><br>
        <span class="divider"></span>
        
        
        <div class="self-menu">
        	<div id="signInSignOut" style="margin: 10px 0;" class="re-navbar-link">
        		<i class="flaticon-user-2"></i><span id="account-name">Sign In</span>
        	</div>
		    <div class="re-navbar-link">Log out</div>
		</div>
        

      </div>
    </div>
  </nav>
</div>

<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="signInForm" action="SignInSignUpPageServlet" method="post"></form>
<form id="homeForm" action="HomePageServlet" method="post"></form>
<form id="yourReservationsForm" action="CalendarOrgRepServlet" method="post"></form>

</body>
<script src="../js/menu-links.js"></script>
</html>