<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <meta charset="utf-8">
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
    <script src="js/register.js"></script>
	<script type="text/javascript">
	   console.log("In Registration");
	    $(document).ready(function() {
	    	//Meeting Rooms
		 	   $('#mRoom').click(function() {
		 		   console.log("Meeting Rooms CLICKED");
					$("#meetingRoomForm").submit();
				});
		 	   //Services
		 	  $('#services').click(function() {
		 		   console.log("Services CLICKED");
					$("#servicesForm").submit();
				});
		 	  //Reservations
		 	 $('#reservations').click(function() {
		 		   console.log("Resevations CLICKED");
					$("#yourReservationsForm").submit();
				});
		 	  
		 	$('#logo').click(function() {
		 		console.log("LOGO CLICKED");
				$("#homeForm").submit();
			});
		 	
		 	$("#signInSignOut").click(function() {
		  		console.log($("#account-name").text());
		  		if($("#account-name").text() == "Sign In"){
		  			console.log("GO TO SIGN IN");
		  			window.location.href = "sign_in_sign_up.jsp";
		  		}
		  	});
		      
	      });
    </script>
</head>

<body >

<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="servicesForm" action="CalendarOrgRepServlet" method="post"></form>
<form id="yourReservationsForm" action="CalendarOrgRepServlet" method="post"></form>
<form id="homeForm" action="HomePageServlet" method="post"></form>
<form id="secretQuestionList" action="RegistrationPageServlet" method="get"></form>

<div class="container-fluid">
  <div class="row">
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
            <a id="logo" class="navbar-brand" ></a>
          </div>
          <div class="collapse navbar-collapse">
          <br>
            <div class="row" id="toggle-search">
              <div class="col-xs-12 col-md-8 search-button">Search For...</div>
              <div class="col-xs-6 col-md-4"><i class="flaticon-loupe"></i></div>
            </div>
            <ul class="nav navbar-nav">
              <li class="">
                <a id="mRoom">Meeting Rooms</a>  
              </li>
              <li class="">
                <a id="services">Services</a>
              </li>
              <li class="end">
                <a id = "reservations">Your Reservations</a>
              </li>
              <li class="divider"></li>
            </ul>
            <p class="navbar-text">
              Made by
              <a href="http://www.samrayner.com">Sam Rayner</a>
            </p><br><br><br>
            <span class="divider"></span>
            <a id = "signInSignOut" class="self-menu">
              <i class="flaticon-user-2"></i><span id="account-name">${first_namelast_name}</span>
            </a>

          </div>
        </div>
      </nav>
    </div>
    <div class="col-sm-9 col-lg-10 content">
      <!-- your page content -->
      
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
      </div>

      <div id="overlay-screen" style="display: none;"></div>
      <div id="content-search" class="collapse white-container" style="display:none;">
        <h3 align="center">Search for Books</h3>
        <form class="form-inline input-group" id="search" align="center">
          <div class="form-group">
            <select id="search-filter" class="form-control">
              <option value="" selected disabled>Choose Filter</option>
              <option value="k"> Keyword</option>
              <option value="t"> Title</option>
              <option value="a"> Author</option>
              <option value="d"> Subject</option>
              <option value="c"> LC Call #</option>
              <option value="g"> Govt Doc #</option>
              <option value="i"> ISBN/ISSN</option>     
            </select>
          </div>
          <div class="form-group" style="width:50%">
            <input type="text" class="form-control" id="search-bar" placeholder="Search for...">
          </div>
          <div class="form-group">
            <select id="search-collection" class="form-control">
              <option value="" selected disabled>Choose Collection</option>
              <option value="b">Books</option>
              <option value="m">Magazines</option>
              <option value="j">Journals</option>
              <option value="t">Thesis</option>    
            </select>
          </div>
          <div class="form-group">
          <button type="submit" id="submit-search" class="btn btn-default"><i class="flaticon-loupe"></i></button>
          </div>
        </form>

        <br>
        <div class="divider-dark"></div>
        <br>
      </div>
      	
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

    </div>
  </div>
</div>



</body>

</html>
