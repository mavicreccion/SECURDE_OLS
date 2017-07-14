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
          <li role="presentation" id="reservations-tab" ><a href class="tab">Manage Reservations</a></li> 
          <li role="presentation" class="active" id="accounts-tab" ><a href class="tab">Manage Accounts</a></li> 
          <li role="presentation" id="log-tab"><a href class="tab">Export Log</a></li> 
        </ul>
        <br>
    
        <div id ="manage-accounts" class="content lesser-padding-content">
          <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <div class="btn-group" role="group">
              <button id="btn_add_account" type="button" class="btn btn-default">Add Account</button>
            </div>
            <div class="btn-group" role="group">
              <button id="btn_unlock_account" type="button" class="btn btn-default">Unlock Account</button>
            </div>
          </div><br>
          
          <div class="content-unlock-account" style="display: none;">
          
          	<!--  sample -->
          	<div class="rm-results locked-accounts" style="margin: 0px !important;">
          	 <div class="row rm-acc-details" style="margin: 10px 0;">
		      	<div class="col-md-4 name">LastName, FirstName</div>
		      	<div class="col-md-2 idnumber">1112233</div>
		      	<div class="col-md-2 usertype">Amazing</div>
		      	<div class="col-md-2 status reserved-status">LOCKED</div>
		      	<div class="col-md-2 unlock-btn">
		      		<button type="submit" id="unlock-acc-btn" 
		      			class="btn btn-default submit-btn form-components-rd auto-width">Unlock</button>
		      	</div>
		      </div>
		      <div class="row rm-acc-details" style="margin: 10px 0;">
		      	<div class="col-md-4 name">LastName, FirstName</div>
		      	<div class="col-md-2 idnumber">1112233</div>
		      	<div class="col-md-2 usertype">Amazing</div>
		      	<div class="col-md-2 status reserved-status">LOCKED</div>
		      	<div class="col-md-2 unlock-btn">
		      		<button type="submit" id="unlock-acc-btn" 
		      			class="btn btn-default submit-btn form-components-rd auto-width">Unlock</button>
		      	</div>
		      </div>
		      <!--  end of sample -->
		      
		      <c:forEach items="${user}" var = "u" >
		      <!-- START -->
		      <div class="row" id="${u.idnumber}">
		      	<div class="col-md-4 name">${u.lastName}, ${u.firstName}</div>
		      	<div class="col-md-2 idnumber">${u.idnumber}</div>
		      	<div class="col-md-2 usertype">${u.userType}</div>
		      	<div class="col-md-2 status reserved-status">LOCKED</div>
		      	<div class="col-md-2 unlock-btn">
		      		<button type="submit" id="unlock-acc-btn" 
		      			class="btn btn-default submit-btn form-components-rd">Unlock</button>
		      	</div>
		      </div>
		      <!-- END -->
		      </c:forEach>
		      </div>
          </div>
          
          <div class="content-add-account">
          	<form id = "register-details" style="margin-right: 300px;" action="RegisterUserServlet" method="POST">
	        <input type="hidden" id="isModAdding" name="isModAdding" value="MOD"/>
	        
	        <label for="user_type">User Type</label>
	        <select id = "user_type" name="user_type" class="form-control form-components-rd"
	        	style="margin-left: 0; margin-right: 0;" required>
	        	<!-- STUDENT, FACULTY, ADMIN, LIBSTAFF, LIBMNGR -->
	        	<option value="" selected disabled>Select User Type</option>
	        	<option value="STUDENT">Student</option>
	        	<option value="FACULTY">Faculty</option>
	        	<option value="ADMIN">Administrator</option>
	        	<option value="LIBSTAFF">Library Staff</option>
	        	<option value="LIBMNGR">Library Manager</option>
	        </select>
	        
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
        </div>
          
        </div>
     </div>
   </div>
 </div>
 
 
<form id="addModAccount" action="addModAccount" method="post"></form>
<form id="ManageBooks" action="AdminAreaServlet" method="post"></form>
<form id="ManageReservations" action="AdminReservationsServlet" method="post"></form>
<form id="ManageLog" action="AdminLogServlet" method="post"></form>

<script>
$("a.tab").click(function(e){
	e.preventDefault();
});

$("#books-tab a").click(function(e){
	$("#ManageBooks").submit();
});

$("#reservations-tab a").click(function(e){
	$("#ManageReservations").submit();
});

$("#log-tab a").click(function(e){
  $("#ManageLog").submit();
});


$("#btn_add_account").click(function(e){
   $(".content-unlock-account").fadeOut(350, function(){});
   $(".content-add-account").fadeIn(350, function(){});
})

$("#btn_unlock_account").click(function(e){
	$(".content-unlock-account").fadeIn(350, function(){});
	$(".content-add-account").fadeOut(350, function(){});
	getAllLockedAccounts();
})

$("#unlock-acc-btn").click(function(e){
	console.log("Unlocking Account...");
	//add ajax here that will unlock account.
});

function getAllLockedAccounts(){
	//add ajax here that will display / get all locked accounts from servlet
}
  
</script>
</body>
</html>