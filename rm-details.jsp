<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
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
      <jsp:include page="reusable/search-bar.jsp"/>    

      <div class="row rm-gen-details">
        <div class="col-md-2">
        <img src="img/book_placeholder.jpg" class="rm-img" width="100%" style="margin: auto 0;"></div>
        <div class="col-md-10 rm-information">
          <a href="rm-details.html" class="title"><b>${reading_material.title}</b></a>
          <span class="author">${reading_material.author}</span>
          <span class="pub-info">${reading_material.publisher}</span>
          <span class="tags">Tags</span>
          <br>
          <div class="row">
            <b>
            <div class="col-md-3"><u>Location</u></div>
            <div class="col-md-3"><u>Status</u></div>
            <div class="col-md-3"></div>
            <div class="col-md-3"></div></b>
          </div>
          <div class="location-status">
            <div class="row">
              <div class="col-md-3"><span class="location">${reading_material.RMID_Location}</span></div>
              <div class="col-md-3"><span class="available-status">${reading_material.status}</span></div>
              <c:choose>
					    <c:when test="${status== 'AVAILABLE'}">
					       <div onclick="clickedType('${loc}')" class="col-md-4"><button   onclick="clickedType(${reading_material.RMID_Location })" class="reserve-inline btn btn-default">Reserve</button></div>
					    </c:when>    
					    <c:otherwise>
					        <div class="col-md-4"><button class="reserve-inline btn btn-default disabled">Reserve</button></div>
					    </c:otherwise>
				 </c:choose>
              <div class="col-md-3"><span class="availability-date">Returned by: </span> ${reading_material.dateReturned}</div>
            </div> 
          </div>

        </div>
      </div>
      
      <br>
      <div class="divider"></div>
      <br>

      <div class="rm-reviews">
        <b id="results-found" style="margin: -1px;">Reviews</b>
        
        <div class="write-review divider">
          <b>Write a short review</b>
          <form id="reviewForm" class="form-inline" align="center" action="ReviewServlet" method="post">

            <textarea class="form-control" name="textReview" placeholder="Tell us what you think" rows="2" style="width: 80%; margin-right: 20px;" ></textarea>
            <input type="hidden" name="rmID" value="${reading_material.RMID_Location}"/>
            <button class="btn btn-default" style="margin: 0 auto;"><i class="flaticon-message"></i></button>

          </form>
        </div>
        
        <c:forEach items="${reviewList}" var = "i" >
      	<div class="review divider">Reviewed on <i>${i.date_reviewed}</i>
          <br>${i.review}
        </div>
        </c:forEach>

		<!-- Sample Data -->
        <div class="review divider">Reviewed on <i>6 / 30 / 2017</i>
          <br>
          • Library Manager - can only edit book information, add new book, delete book and override reservations. In addition, the manager can export to an excel sheet/XML of the status of all books and meeting rooms. • Library Staff - can only edit book information, add new book, delete book and availability of meeting rooms. • Administrator – can create new Library Manager, Library Staff, Library Student Assistant and accounts and assign temporary passwords, which if not changed within 24 hours, will render the account expired. Also, the administrator can export to an excel sheet/XML file of the status of all books and meeting rooms.
        </div>

        <div class="review divider">Reviewed on  <i>6 / 30 / 2017</i>
          <br>

          • Library Manager - can only edit book information, add new book, delete book and override reservations. In addition, the manager can export to an excel sheet/XML of the status of all books and meeting rooms. • Library Staff - can only edit book information, add new book, delete book and availability of meeting rooms. • Administrator – can create new Library Manager, Library Staff, Library Student Assistant and accounts and assign temporary passwords, which if not changed within 24 hours, will render the account expired. Also, the administrator can export to an excel sheet/XML file of the status of all books and meeting rooms.

        </div>
        <!-- End of Sample Data -->

      </div> <!--  END OF RM-REVIEWS -->

    	<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->

<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="servicesForm" action="CalendarOrgRepServlet" method="post"></form>
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
function passReview(id){
	//so para san to :o -Dyan
	console.log("Pass Review");
}
$(".btn btn-default").click(function() {
	$("#reviewForm").submit();
});
</script>
</body>
</html>
