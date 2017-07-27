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
        <ul class="nav navbar-nav" style="border-top: 1px solid #fff;">
          <li class="">
            <a href="" id="books-tab">Manage Books</a>
          </li>
          <li class="">
            <a href="" id="rm-reserve-tab">Manage Book Reservations</a>
          </li>
          <li class="">
            <a href="" id="mr-reserve-tab">Manage Meeting Room Reservations</a>
          </li>
          <li class="">
            <a href="" id="accounts-tab">Manage Accounts</a>
          </li>
          <li class="end">
            <a href="" id="log-tab">Export Log</a>
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

<form id="GoToPage" action="AdminAreaServlet" method="post">
	<input type="hidden" value="" name="destination" id="destination"/>
</form>

<!-- 
 
<form id="ManageBooks" action="AdminAreaServlet" method="post"></form>
<form id="ManageRMReserve" action="AdminDisplayRMReservationsServlet" method="post"></form>
<form id="ManageMRReserve" action="AdminDisplayMRReservationsServlet" method="post"></form>
<form id="ManageAccounts" action="AdminDisplayAccountsServlet" method="post"></form>
<form id="ExportLog" action="ExportServlet" method="post"></form>
 
  -->
</body>
<script src="js/jquery-3.0.0.min.js"></script>
<script src="js/admin-menu-links.js"></script>
</html>