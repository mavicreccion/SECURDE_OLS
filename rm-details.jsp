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
  	<c:choose>
      <c:when test="${canEdit == 'true'}">
      	<!-- NAV BAR -->
	    <jsp:include page="reusable/admin-navbar.jsp"/>    
	    <!-- END OF NAV BAR -->  
      </c:when>
      <c:otherwise>
      	<!-- NAV BAR -->
	    <jsp:include page="reusable/navbar.jsp"/>    
	    <!-- END OF NAV BAR -->  
      </c:otherwise>
     </c:choose>
      
    <div class="col-sm-9 col-lg-10 content">
      <!-- your page content -->
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
      </div>
      
      <div id="overlay-screen" style="display: none;"></div>
      
      <!-- SEARCH BAR -->
      <jsp:include page="reusable/search-bar-toggable.jsp"/>    
      <!-- END OF SEARCH BAR -->  
	
	  <c:choose>
	  	<c:when test="${canEdit == 'true'}">
	  	  <div id="content-edit-book" class="lesser-padding-content" style="display: none;">
	        <h3 align="center">Edit Reading Material</h3>
	        <form id="EditRMDetails" action="EditRMServlet" method="post">
	        <div class="content" style="margin: 0 10%">
	        
	            <div class="form-group">
	              <label for="newRMType">Type of Reading Material</i></label>
	              <select id="newRMType" name="newRMType" class="form-control form-components-rd" style="width:70%">
					<option value="${reading_material.RMType}" selected>${reading_material.RMType}</option>
	               
	                
	                <c:forEach items="${rmtypelist}" var="i">
	                	<c:choose>
	                		<c:when test ="${i != reading_material.RMType}">
				            	<option value="${i}">${i}</option>
				            </c:when>
	                	</c:choose>
				    </c:forEach> 
				     
	              </select>
	            </div>
	            
	           <label for="title">Title <i>(Dewey Decimal System)</i></label>
	          <input type="text" class="form-control form-components-rd" id="title" name="title" placeholder="Title" style="width:100%" 
	          value="${reading_material.title}" required>
	          
	          <label for="RMID_Location">Location <i>(Dewey Decimal System)</i></label>
	          <input type="text" class="form-control form-components-rd" id="rmID_location" name="rmID_location" placeholder="Location" style="width:100%" 
	          value="${reading_material.RMID_Location}" required>
	
	          <label for="author">Author/s <i>(If more then 2 authors, please separated with & (ampersand) )</i></label>
	          <input type="text" class="form-control form-components-rd" id="author" name="author" placeholder="Author/s" style="width:100%" 
	          value="${reading_material.author}" required>
	
	          <label for="publisher">Publisher</label>
	          <input type="text" class="form-control form-components-rd" id="publisher" name="publisher" placeholder="Publisher" style="width:100%" 
	          value="${reading_material.publisher}" required>
	
	          <label for="year">Year Published</label>
	          <input type="number" class="form-control form-components-rd" id="year" name="year" placeholder="Year Published" style="width:100%" 
	          value="${reading_material.year}" required>
	
	          <label for="tags">Tags</label>
	          <input type="text" class="form-control form-components-rd" id="tags" name="tags" placeholder="Tags, Tags, Tags" style="width:100%" 
	          value="${reading_material.tags}" required>
	          <br>
	          <button type="submit" id="submit-edit-rm" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Edit Reading Material</button>
	        </div>
	        </form>
	      </div>
	  	</c:when>
	  </c:choose>
	  

      <div class="row rm-gen-details">
      	<c:choose>
          <c:when test="${canEdit == 'true'}">
	      	<div style="position: relative;">
	          <div id="edit-rm-btn">
	            <a href="">
	              <button class="btn btn-default">
	                <i class="flaticon-paint-brush"></i>
	              </button>
	            </a>
	          </div>
	        </div>
	      </c:when>
        </c:choose>
        <div class="col-md-2">
        <img src="img/book_placeholder.jpg" class="rm-img" width="100%" style="margin: auto 0;"></div>
        <div class="col-md-10 rm-information">
          <span class="title"><b>${reading_material.title}</b></span>
          <span class="author">${reading_material.author}</span>
          <span class="pub-info">${reading_material.publisher}, ${reading_material.year}</span>
          <span class="tags">Tags</span>
          <br>
          <div class="row">
            <b>
            <div class="col-md-3"><u>Location</u></div>
            <div class="col-md-3"><u>Status</u></div>
            <div class="col-md-3"></div>
            <div class="col-md-3">
            <c:choose>
              <c:when test="${reading_material.status == 'BORROWED' or reading_material.status == 'AVAILABLE'}">
            	<u>Returned By:</u>
              </c:when>
            </c:choose>
            </div></b>
          </div>
          <div class="location-status">
            <div class="row">
              <div class="col-md-3"><span class="location">${reading_material.RMID_Location}</span></div>
              <div class="col-md-3"><span class="available-status">${reading_material.status}</span></div>
              <c:choose>
              	<c:when test="${canEdit =='true'}">
              		<div class="col-md-3"></div>
              		<div class="col-md-3"></div>
              	</c:when>
			    <c:when test="${reading_material.status == 'BORROWED'}">
			      <div onclick="clickedType('${loc}')" class="col-md-3"><button onclick="clickedType(${reading_material.RMID_Location })" class="reserve-inline btn btn-default">Reserve</button></div>
			      <div class="col-md-3">
			      	<div class="col-md-3"><span class="availability-date"></span>${reading_material.date_returned}</div>
			      </div>
			    </c:when>
			    <c:when test="${reading_material.status == 'AVAILABLE'}">
			      <div onclick="clickedType('${loc}')" class="col-md-3"><button onclick="clickedType(${reading_material.RMID_Location })" class="reserve-inline btn btn-default">Reserve</button></div>
			      <div class="col-md-3"></div>
			    </c:when>
			    <c:otherwise>
			        <div class="col-md-3"><button class="reserve-inline btn btn-default disabled">Reserve</button></div>
			        <div class="col-md-3"><span class="availability-date"></span>${reading_material.date_returned}</div>
			    </c:otherwise>
			  </c:choose>
            </div> 
          </div>

        </div>
      </div>
      
      <br>
      <div class="divider"></div>
      <br>

      <div class="rm-reviews">
        <b id="results-found" style="margin: -1px;">Reviews</b>
        
        <c:choose>
          <c:when test="${hasBorrowed == 'true'}">
        	<div class="write-review divider">
	          <b>Write a short review</b>
	          <form id="reviewForm" class="form-inline" align="center" action="ReviewServlet" method="post">
	
	            <textarea class="form-control" name="textReview" placeholder="Tell us what you think" rows="2" style="width: 80%; margin-right: 20px;" ></textarea>
	            <input type="hidden" name="rmID" value="${reading_material.RMID_Location}"/>
	            <button class="btn btn-default" style="margin: 0 auto;"><i class="flaticon-message"></i></button>
	
	          </form>
	        </div>
          </c:when>
        </c:choose>
        
        
        <c:forEach items="${review}" var = "r" >
      	<div class="review divider">Reviewed on <i>${r.date_reviewed}</i>
          <br>${r.review}
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
<c:choose>
	<c:when test="${canEdit == 'true'}">
		<script src="js/menu-links.js"></script>
	</c:when>
	<c:otherwise>
		<script src="js/admin-menu-links.js"></script>
	</c:otherwise>
</c:choose>
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

$("#edit-rm-btn").click(function(e){
  e.preventDefault();
  $("#content-edit-book").fadeIn("fast", function(){});
  $(".rm-gen-details").fadeOut("fast", function(){});
  $(".rm-reviews").fadeOut("fast", function(){});
  

})

$("#submit-edit-rm").click(function(e){
  //temporary
  location.reload();

});

</script>
</body>
</html>
