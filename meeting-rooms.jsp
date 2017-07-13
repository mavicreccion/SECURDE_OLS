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
    <link rel="stylesheet" type="text/css" href="css/reservation_table.css"> 
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


    </div>
  </div>
</div>


<script src="js/reserve-slots.js"></script>
</body>
</html>
