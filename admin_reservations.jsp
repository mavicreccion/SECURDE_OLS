<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    <script src="js/jquery-3.0.0.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="vitalets-bootstrap-datepicker-c7af15b/js/bootstrap-datepicker.js"></script>
    <script src="js/app.js"></script>
<body>


<div class="container-fluid">
  <div class="row">
    <jsp:include page="reusable/navbar.jsp"/>    
    <div class="col-sm-9 col-lg-10 content admin-area">
    	<div class="header">
	       <h1>SHS Online Library System</h1>
	       <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
	    </div>
    
    	
    	<ul class="nav nav-tabs nav-justified"> 
          <li role="presentation" id="books-tab"><a href class="tab">Manage Books</a></li> 
          <li role="presentation" class="active" id="reservations-tab" ><a href class="tab">Manage Reservations</a></li> 
          <li role="presentation" id="accounts-tab" ><a href class="tab">Manage Accounts</a></li> 
          <li role="presentation" id="log-tab"><a href class="tab">Export Log</a></li> 
        </ul>
        <br>
    
		<div id ="manage-reservations" class="content lesser-padding-content">
		  <div class="panel panel-default">
		    <div class="panel-heading">
		    	
		      <a data-toggle="collapse" href="#current-book-log">Current Reading Material Reservations</a>
		    </div>
		    
		    <c:choose>
		    <c:when test="${numOfRM == '0'}">
		    <div id="current-book-log" class="panel-collapse collapse">
		    </c:when>
		    <c:otherwise>
		    <div id="current-book-log" class="panel-collapse collapse in">
		    </c:otherwise>
		    </c:choose>
		      <div class="panel-body">
		        <c:forEach items="${reading_material}" var ="rm">
			       	<div class="log-item bordered-dark">
			          <a class="title">${rm.title}</a>
			          <div class="status reserved-status">Reserved</div>
			          Reserved on : <span class="date_reserved">mm / dd / yyyy</span><br>
			          Return by : <span class="date_returned">mm / dd / yyyy</span>
			
			          <button class="btn btn-default override-btn">Override Reservation</button>
			        </div>
		        </c:forEach>
		       
		      </div>
		    </div>
		  </div>
		  
		  <div class="panel panel-default">
		    <div class="panel-heading">
		      <a data-toggle="collapse" href="#current-room-log">Current Meeting Room Reservations</a>
		    </div>
		    
		    <c:choose>
		    <c:when test="${numOfMR == '0'}">
		    <div id="current-room-log" class="panel-collapse collapse">
		    </c:when>
		    <c:otherwise>
		    <div id="current-room-log" class="panel-collapse collapse in">
		    </c:otherwise>
		    </c:choose>
		      <div class="panel-body">
		      
		      	<c:forEach items="${meeting_room}" var ="mr">
		        <div class="log-item bordered-dark">
	              <a class="room_no">${mr.mr_name }</a><br>
	              Reserved on : <br>
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="date_reserved">mm / dd / yyyy</span> <br> 
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="time_reserved">0930 am</span>
	            </div>
	            </c:forEach>
		
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- dont touch beond this point -->
	  </div>
  </div>
</div>

<form id="ManageBooks" action="AdminAreaServlet" method="post"></form>
<form id="ManageAccounts" action="AdminAccountsServlet" method="post"></form>
<form id="ManageLog" action="AdminLogServlet" method="post"></form>

</body>
<script>
$("a.tab").click(function(e){
	e.preventDefault();
});

$("#books-tab a").click(function(e){
	$("#ManageBooks").submit();
});

$("#accounts-tab a").click(function(e){
  $("#ManageAccounts").submit();
})

$("#log-tab a").click(function(e){
  $("#ManageLog").submit();
})

</script>
</html>