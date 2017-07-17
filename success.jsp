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
      <!-- SEARCH BAR -->
      <div id="content-search">
        <h3 align="center">Search for Books</h3>
        <form class="form-inline input-group" id="search" align="center" action="RMSearchResultsPageServlet" method = "post">
          <div class="form-group">
            <select id="search-filter" name="RMFilter" class="form-control form-components-rd">
              <option value="KEYWORDS" selected> Keyword</option>
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
              <option value="ALL" selected>All</option>
              <option value="BOOK">Books</option>
              <option value="MAGAZINE">Magazines</option>
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
       <!-- END OF SEARCH BAR -->

       <div align="center">

         <div class="row">
           <div class="alert alert-success col-md-6 col-md-offset-3"  role="alert">
             <h3>Insert Success Message here</h3>
           </div>
         </div>  

         <div class="row">
           <br><br>
           <a href="index.html" class="btn btn-default">Return to Homepage</a>
   	     </div>
   	   </div>


	<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->
<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="signInForm" action="SignInSignUpPageServlet" method="post"></form>
<form id="homeForm" action="HomePageServlet" method="post"></form>
<form id="yourReservationsForm" action="CalendarOrgRepServlet" method="post"></form>

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/menu-links.js"></script>
<script src="js/app.js"></script>			
<!-- //////////////////// -->
<script>
$("#verify-secret-answer").click(function(){
	  $('.alert-danger').show("fast", function(){});
	  setTimeout(function(){
	      $('.alert-danger').hide("fast", function(){});  
	    }, 1000);
});
</script>
</body>


</body>
</html>
