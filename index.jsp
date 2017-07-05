<!DOCTYPE html>
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
    <link rel="stylesheet" type="text/css" href="css/navbar-redeed.css"/>
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
				$("#signInForm").submit();
			});
	      
	      });
    </script>
</head>

<body>
<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="signInForm" action="SignInSignUpPageServlet" method="post"></form>
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
            <a class="navbar-brand" href="./"></a>
          </div>
          <div class="collapse navbar-collapse">
          <br>
            <div href class="row" id="toggle-search">
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
	<!-- SEARCH BAR -->
      <div id="content-search">
        <h3 align="center">Search for Books</h3>
        <form class="form-inline input-group" id="search" align="center" action="RMSearchResultsPageServlet" method = "post">
          <div class="form-group">
            <select id="search-filter" name="RMFilter" class="form-control form-components-rd">
              <option value="" selected disabled>Choose Filter</option>
              <option value="KEYWORDS"> Keyword</option>
              <option value="TITLE"> Title</option>
              <option value="AUTHOR"> Author</option>
              <option value="PUBLISHER"> Publisher</option>  
            </select>
          </div>
          <div class="form-group" style="width:50%">
            <input type="text" name= "reading_material" class="form-control form-components-rd" id="search-bar" placeholder="Search for..." autofocus>
          </div>
          <div class="form-group">
            <select id="search-collection" name="RMType" class="form-control form-components-rd">
              <option value="" selected disabled>Choose Collection</option>
              <option value="BOOK">Books</option>
              <option value="MAGAZINE">Magazines</option>
              <option value="ALL">All</option>
              <option value="THESIS">Thesis</option>    
            </select>
          </div>
          <div class="form-group">
          <button type="submit" id="submit-search" class="btn btn-default form-components-rd transparent-bg"><i class="flaticon-loupe"></i></button>
          </div>
        </form>
        <br>
        <div class="divider-dark"></div>
        </div>

        
        <div class="container" style="width:100%;">
          <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
              <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
              <li data-target="#myCarousel" data-slide-to="1"></li>
              <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">
              <div class="item active">
                <img src="img/banners/menu banner-character creation.jpg" alt="Los Angeles" style="width:100%;">
              </div>

              <div class="item">
                <img src="img/banners/menu banner-quests.jpg" alt="Chicago" style="width:100%;">
              </div>
            
              <div class="item">
                <img src="img/banners/menu banner-dormitories.jpg" alt="New york" style="width:100%;">
              </div>
            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
              <span class="glyphicon glyphicon-chevron-left"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
              <span class="glyphicon glyphicon-chevron-right"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div>

      <div id="account-content">
        <h3 id="name"></h3>
      </div>


    </div>
  </div>
</div>



</body>
</html>
