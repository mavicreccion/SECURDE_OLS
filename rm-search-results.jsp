<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
    <script src="js/reserve-rm.js"></script>
    
    <script>
    function clickedType(id)
    {
    	$("#confirm-reservation").fadeIn("fast, function(){});
    	$("#overlay-screen").fadeIn(fast, function(){});
    	
    	submitReservation(id);
    }
    
    function submitReservation(id){
    	 console.log("hahaha " + id);
     	$("#locationID").val(id);
     	$("#locationID").text(id);
     	console.log("ehhe " + $("#locationID").val()); 
     	 $("#reserveForm").submit();
    }
    
    function review(id)
    {
    	 console.log("hahaha " + id);
    	$("#reviewID").val(id);
    	$("#reviewID").text(id);
    	console.log("EHHE " + $("#reviewID").val()); 
    	 $("#reviewForm").submit();
    	
    }

     $('.reserve-inline:not(.disabled)').click(function(){
    	console.log(status);
    	$("#location").val(status);
    	//$("#reserveForm").submit();
    });
     
    $('#cancel-reserve').click(function(){
        $('#overlay-screen').fadeOut("fast", function(){})
        $('#confirm-reservation').fadeOut(350, function(){});
    })

    $('#overlay-screen').click(function(){
        $('#overlay-screen').fadeOut("fast", function(){})
        $('#confirm-reservation').fadeOut(350, function(){});
    }) 
    
    //////////////////////
    
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
   
    	
    </script>
</head>

<body>
<form id="meetingRoomForm" action="MeetingRoomPageServlet" method="post"></form>
<form id="servicesForm" action="CalendarOrgRepServlet" method="post"></form>
<form id="signInForm" action="SignInSignUpPageServlet" method="post"></form>
<form id="homeForm" action="HomePageServlet" method="post"></form>
<form id="signInFirstForm" action="sign_in_sign_up.jsp" method="post"></form>
<form id="reserveForm" action="ReserveRMServlet" method="post">
		 <input type = "hidden" name="locationID" id="locationID" value="hehehe">
</form>
<form id="reviewForm" action="RMDetailsServlet" method="post">
		 <input type = "hidden" name="reviewID" id="reviewID" value="hehehe">
</form>

<div class="container-fluid">
  <div class="row">
  	<!--  MENU -->
    <jsp:include page="reusable/navbar.jsp"/>
    <!--  END MENU -->
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

        <h3>Reserving <a href="rm-details.html" class="title">Title</a></h3>

        <div style="padding: 0 20%;" align="left">
          <b>Reservation Date</b>: <span class="confirm-reserve-date">07 / 02 / 2017</span><br>
          <b>Anticipated Return Date</b>: <span class="confirm-return-date">07 / 09 / 2017</span>
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
              <option value="KEYWORDS" selected disabled> Keyword</option>
              <option value="TITLE"> Title</option>
              <option value="AUTHOR"> Author</option>
              <option value="PUBLISHER"> Publisher</option>  
            </select>
          </div>
          <div class="form-group" style="width:50%">
            <input type="text" class="form-control" id="search-bar" placeholder="Search for...">
          </div>
          <div class="form-group">
            <select id="search-collection" class="form-control">
              <option value="ALL" selected disabled>All</option>
              <option value="BOOK">Books</option>
              <option value="MAGAZINE">Magazines</option>
              <option value="THESIS">Thesis</option>    
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

      <div class="rm-results">
      <b id="results-found" style="margin: -1px;" >${numOfRM} results found</b>
      <c:forEach items="${reading_material}" var = "i" >
      <!-- START -->
      <div class="row rm-gen-details">
        <div class="col-md-2">
	        <img src="img/book_placeholder.jpg" class="rm-img" width="100%" style="margin: auto 0;"></div>
	        <div class="col-md-10 rm-information">
	        <c:set var="rmID">${i.RMID_Location}</c:set>
	          <a onclick="review('${rmID}')" class="title"><b>${i.title}</b></a>
	          <span class="author">${i.author}</span>
	          <span class="pub-info">${i.publisher}</span>
	          <br>
	          
	          <div class="row">
	            <b>
	            <div class="col-md-4"><u>Location</u></div>
	            <div class="col-md-4"><u>Status</u></div></b>
	            <div class="col-md-4"></div>
	          </div>
	          <div class="location-status">
	            <div class="row">
	              <div class="col-md-4"><span class="location">${i.RMID_Location}</span></div>
	              <div class="col-md-4"><span class="reserved-status">${i.status}</span></div>
	              <c:set var="status">${i.status}</c:set>
	              <c:choose>
					    <c:when test="${status== 'AVAILABLE' || status == 'BORROWED'}">
					    	<c:set var="loc">${i.RMID_Location}</c:set>
					       <div onclick="clickedType('${loc}')" class="col-md-4"><button   onclick="clickedType(${i.RMID_Location })" class="reserve-inline btn btn-default">Reserve</button></div>
					       
					    </c:when>    
					    <c:otherwise>
					        <div class="col-md-4"><button class="reserve-inline btn btn-default disabled">Reserve</button></div>
					    </c:otherwise>
				 </c:choose>
	              
	            </div>
	          </div>
          </div>
          </div>
        </c:forEach>
        
		<!-- END -->
      </div>


    </div>
  </div>
</div>
</body>
</html>
