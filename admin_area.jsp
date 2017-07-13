<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
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
    <script src="js/register.js"></script>
</head>

<body>

<form id="AddAccountForm" action="AddModeratorAccountServlet" method="post"></form>

<div class="container-fluid">
  <div class="row">
    <jsp:include page="reusable/navbar.jsp"/>    
    <div class="col-sm-9 col-lg-10 content admin-area">
      <!-- your page content -->
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
      </div>
      <div id="overlay-screen" style="display: none;"></div>
      <div id="confirm-override" class="pop-up" style="display: none;">
        <h3 align="center">Confirm Override?</h3>
        <div align="center" class="divider"></div>
        <h3>Reserving <a href="rm-details.html" class="title">Title</a></h3>
        <br>
        <div class="form-group">
          <label for="override-idnum" class="col-sm-2 control-label">ID #</label>
          <div class="col-sm-10">
            <input type="number" class="form-control form-components-rd erase-margin" id="override-idnum" placeholder="ID Number" required>
          </div>
        </div>
        <br>
        <div class="form-group">
          <label for="override-idnum" class="col-sm-2 control-label">On Date: </label>
          <div class="col-sm-10">
            <div id="bdaypicker" class="input-append date" data-date-format="yyyy-mm-dd">
              <input class="span2 form-components-rd" size="11" type="text" value="12-02-2012" readonly="" style="width:80%; margin: 5px 0 !important;" required>
              <span class="add-on" style="width:10%"><i class="flaticon-calendar"></i></span>
            </div>
          </div>
        </div><br><br>
        <div style="padding: 0 3%;" align="left">
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

        <ul class="nav nav-tabs nav-justified"> 
          <li role="presentation" class="active" id="books-tab"><a href class="tab">Manage Books</a></li> 
          <li role="presentation" id="reservations-tab" ><a href class="tab">Manage Reservations</a></li> 
          <li role="presentation" id="accounts-tab" ><a href class="tab">Manage Accounts</a></li> 
          <li role="presentation" id="log-tab"><a href class="tab">Log</a></li> 
        </ul>
        <br>

        <div id = "manage-books" class ="content lesser-padding-content">
          <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <div class="btn-group"  role="group">
              <button type="button" id="browse-rm-tab" class="btn btn-default">Browse Reading Materials</button>
            </div>
            <div class="btn-group"  role="group">
              <button type="button" id="add-rm-tab" class="btn btn-default">Add Reading Material</button>
            </div>
          </div>
          <br><br><br>

          <div id="content-add-book" class="lesser-padding-content toggable" >
            <h3 align="center">Add Reading Material</h3>
            <div class="content">
            <form id="addNewRM" method="post" action="AddRMServlet">
              <fieldset>
              <input type = "hidden" name = "newRMType" id="newRMType"/>
              <div class="form-group">
                <label for="rm-type">Type of Reading Material</i></label>
                <select id="rm-type" class="form-control form-components-rd" style="width:70%">
                  <option value="" selected disabled>Choose Collection</option>
                  <option value="Book">Books</option>
                  <option value="Thesis">Thesis</option>
                  <option value="Magazine">Magazine</option>    
                </select>
              </div>
            
              <label for="author-name">Reading Material Title</label>
              <input type="text" class="form-control form-components-rd" name="title" id="title" placeholder="Author/s" style="width:70%" required>
            
              <label for="location-id">Location <i>(Dewey Decimal System)</i></label>
              <input type="text" class="form-control form-components-rd" name="location-id" id="location-id" placeholder="Location" style="width:70%" required>

              <label for="author-name">Author/s <i>(If more then 2 authors, please separated with & (ampersand) )</i></label>
              <input type="text" class="form-control form-components-rd" name="author-name" id="author-name" placeholder="Author/s" style="width:70%" required>

              <label for="author-name">Publisher</label>
              <input type="text" class="form-control form-components-rd" name="publisher" id="publisher" placeholder="Publisher" style="width:70%" required>

              <label for="year-published">Year Published</label>
              <input type="number" class="form-control form-components-rd" name="year-published" id="year-published" placeholder="Year Published" style="width:70%" required>

              <label for="year-published">Tags</label>
              <input type="text" class="form-control form-components-rd" name="tags" id="tags" placeholder="Tag, Tag, Tag" width:70%" required>
              <br>
              <button type="submit" id="submit-add-rm" class="btn btn-default submit-btn form-components-rd auto-width erase-margin">Add Reading Material</button>
              </fieldset>
            </form>
            </div>
          </div>
          <div id="content-search2">
            <h3 align="center">Search for Books</h3>
            <form class="form-inline input-group" id="search" align="center" action="AdminRMSearchResultsPageServlet" method = "post">
              <div class="form-group">
                <select id="search-filter" name="RMFilter" class="form-control form-components-rd">
              <option value="KEYWORDS" selected> Keyword</option>
              <option value="TITLE"> Title</option>
              <option value="AUTHOR"> Author</option>
              <option value="PUBLISHER"> Publisher</option>  
                </select>
              </div>
              <div class="form-group" style="width:50%">
                <input type="text" class="form-control form-components-rd" name="search-bar" id="search-bar" placeholder="Search for..."  autofocus>
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
          <br>
        </div>

        <div id ="manage-reservations" class="content lesser-padding-content toggable">
          <div class="panel panel-default">
            <div class="panel-heading">
              <a data-toggle="collapse" href="#current-book-log">Current Reading Material Reservations</a>
            </div>
            
            <div id="current-book-log" class="panel-collapse collapse in">
              <div class="panel-body">
                <div class="log-item bordered-dark">
                  <a class="title">Analyzing Driving Risks of Roadway Traffic under Adverse Weather Conditions: In Case of Rain Day</a>
                  <div class="status reserved-status">Reserved</div>
                  Reserved on : <span class="date_reserved">mm / dd / yyyy</span><br>
                  Return by : <span class="date_returned">mm / dd / yyyy</span>

                  <button class="btn btn-default override-btn">Override Reservation</button>
                </div>

                <div class="log-item bordered-dark">
                  <a class="title">Analyzing Driving Risks of Roadway Traffic under Adverse Weather Conditions: In Case of Rain Day</a>
                  <div class="status reserved-status">Reserved</div>
                  Reserved on : <span class="date_reserved">mm / dd / yyyy</span><br>
                  Return by : <span class="date_returned">mm / dd / yyyy</span>

                  <button class="btn btn-default override-btn">Override Reservation</button>
                </div>

              </div>
            </div>
          </div>
          
          <div class="panel panel-default">
            <div class="panel-heading">
              <a data-toggle="collapse" href="#current-book-log">Current Meeting Room Reservations</a>
            </div>
            
            <div id="current-book-log" class="panel-collapse collapse in">
              <div class="panel-body">
                <div class="log-item bordered-dark">
                  <a class="title">Analyzing Driving Risks of Roadway Traffic under Adverse Weather Conditions: In Case of Rain Day</a>
                  <div class="status reserved-status">Reserved</div>
                  Reserved on : <span class="date_reserved">mm / dd / yyyy</span><br>
                  Return by : <span class="date_returned">mm / dd / yyyy</span>

                  <button class="btn btn-default override-btn">Override Reservation</button>
                </div>

                <div class="log-item bordered-dark">
                  <a class="title">Analyzing Driving Risks of Roadway Traffic under Adverse Weather Conditions: In Case of Rain Day</a>
                  <div class="status reserved-status">Reserved</div>
                  Reserved on : <span class="date_reserved">mm / dd / yyyy</span><br>
                  Return by : <span class="date_returned">mm / dd / yyyy</span>

                  <button class="btn btn-default override-btn">Override Reservation</button>
                </div>

              </div>
            </div>
          </div>
        </div>

        <div id ="manage-accounts" class="content lesser-padding-content toggable">
          <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <div class="btn-group" role="group">
              <button id="btn_add_account" type="button" class="btn btn-default">Add Account</button>
            </div>
            <div class="btn-group" role="group">
              <button type="button" class="btn btn-default">Delete Account</button>
            </div>
          </div><br>
          <div class="panel panel-default">
            <div class="panel-heading"><h3 class="panel-title">Recent Announcements</h3></div>
            <div class="panel-body">
              <ul>
              <li><B>SECURDE Milestone 1 due on July 6, 2017</B></li>
              </ul>
            </div>
          </div>
        </div>



    </div>
  </div>
</div>

<form id="AdminReservations" action="AdminReservationsServlet" method="post"></form>

<script>

$("a.tab").click(function(e){
  e.preventDefault();
})

$("#books-tab a").click(function(e){
  $("#manage-books").css('display', 'block');
  $("#manage-reservations").css('display', 'none');
  $("#books-tab").addClass("active");
  $("#reservations-tab").removeClass("active");
  $("#accounts-tab").removeClass("active");
  $("#log-tab").removeClass("active");
})

$("#reservations-tab a").click(function(e){
	
	$("#AdminReservations").submit();
	
})


$("#accounts-tab a").click(function(e){
  $("#manage-books").css('display', 'none');
  $("#manage-reservations").css('display', 'none');
  $("#manage-accounts").css('display', 'block');
  $("#books-tab").removeClass("active");
  $("#reservations-tab").removeClass("active");
  $("#accounts-tab").addClass("active");
  $("#log-tab").removeClass("active");
  
})

$("#log-tab a").click(function(e){
  
})

$("#toggle-search").click(function(e){
  window.location.href = "index.html";
})

$(".override-btn:not(.disabled)").click(function(e){
    $('#overlay-screen').fadeIn("fast", function(){});
    $("#confirm-override").fadeIn(350, function(){});
})

$(".delete-btn:not(.disabled)").click(function(e){
    $('#overlay-screen').fadeIn("fast", function(){});
    $("#confirm-override").fadeIn(350, function(){});
})


 $('#overlay-screen').click(function(e){
    $("#confirm-override").fadeIn(350, function(){});

 })

 $("#add-rm-tab").click(function(e){
    $("#content-add-book").fadeIn(350, function(){});
    $(".rm-results").fadeOut(350, function(){});
    $("#content-search2").fadeOut(350, function(){});
 })

 $("#browse-rm-tab").click(function(e){
    $("#content-add-book").fadeOut(350, function(){});
    $(".rm-results").fadeIn(350, function(){});
    $("#content-search2").fadeIn(350, function(){});
 })

 $("#submit-add-rm").click(function(e){
	$("#newRMType").val($("#rm-type option:selected").val());
	 
    $("#addNewRM").submit();
 })
 
 $("#btn_add_account").click(function (e) {
	console.log("Add Account CLICKED");
	$("#AddAccountForm").submit();
 })
 </script>
</body>
</html>