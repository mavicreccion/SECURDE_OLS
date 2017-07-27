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

<body>
<div class="container-fluid">
  <div class="row">
    <jsp:include page="reusable/navbar.jsp"/>   
    <div class="col-sm-9 col-lg-10 content admin-area">
    	<div class="header">
	       <h1>SHS Online Library System</h1>
	       <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
	    </div>
	    
        <div class="panel panel-default">
          <div class="panel-heading">
            <a data-toggle="collapse" href="#current-book-log">Today's Meeting Room Reservations</a>
          </div>
          
          <div id="current-book-log" class="panel-collapse collapse in">
            <div class="panel-body">
            
            <c:choose>
            	<c:when test="${rMRSize == '0'}">
            		<h3>No Reserved Rooms at the moment</h3>
            	</c:when>
            </c:choose>
              
              <c:forEach items="${reserved_mr}" var="mr">
              	<div id = "${mr.reservedMRID}" class="log-item bordered-dark">
	                <span>${mr.mr_name}</span><br>
	                <div class="status reserved-status">Reserved</div>
	                Reserved by user : <span class="id_number">${mr.user.idnumber}</span><br>
	                Reserved on Time: <span class="time_start">${mr.timeStart}</span> -  <span class="time_end">${mr.timeEnd}</span>
					<br>
	                <button class="btn btn-default override-btn mr">Override Reservation</button>
              	</div>
              </c:forEach>
            </div>
          </div>
        </div>
          

 <!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/app.js"></script>				
<!-- //////////////////// -->
<script src="js/manage-reservations.js"></script>




</body>
</html>