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
    <link rel="stylesheet" type="text/css" href="css/content.css"> 
    <script src="js/jquery-3.0.0.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="js/app.js"></script>
    <script type="text/javascript">
	   console.log("iN index");
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

<body>

<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="servicesForm" action="CalendarOrgRepServlet" method="post"></form>
<form id="homeForm" action="HomePageServlet" method="post"></form>
<form id="yourReservationsForm" action="CalendarOrgRepServlet" method="post"></form>

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
          <form>
          <div class="form-group">
          <button type="submit" id="submit-search" class="btn btn-default"><i class="flaticon-loupe"></i></button>
          </div>
        </form>

        <br>
        <div class="divider-dark"></div>
        <br>
      </div>

      
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


    </div>
  </div>
</div>



</body>
</html>
