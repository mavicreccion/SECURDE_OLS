<html lang="en">
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
</head>

<body>

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
            <div class="row" id="toggle-search">
              <div class="col-xs-12 col-md-8 search-button">Search For...</div>
              <div class="col-xs-6 col-md-4"><i class="flaticon-loupe"></i></div>
            </div>
            <ul class="nav navbar-nav">
              <li class="">
                <a href="./">Meeting Rooms</a>
              </li>
              <li class="">
                <a href="inverse.html">Services</a>
              </li>
              <li class="end">
                <a href="inverse.html">Your Reservations</a>
              </li>
              <li class="divider"></li>
            </ul>
            <p class="navbar-text">
              Made by
              <a href="http://www.samrayner.com">Sam Rayner</a>
            </p><br><br><br>
            <span class="divider"></span>
            <a href="sign-in.html" class="self-menu">
              <i class="flaticon-user-2"></i><span id="account-name">Sign In</span>
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

      <div id="confirm-reservation" style="display: none;">
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

      <div class="rm-results">
      <b id="results-found" style="margin: -1px;" >${numOfRM} results found</b>
      <c:forEach items="${reading_material}" var = "i" >
      <!-- START -->
      <div class="row rm-gen-details">
        <div class="col-md-2">
	        <img src="img/book_placeholder.jpg" class="rm-img" width="100%" style="margin: auto 0;"></div>
	        <div class="col-md-10 rm-information">
	          <a href="rm-details.html" class="title"><b>${i.title}</b></a>
	          <span class="author">${i.author}</span>
	          <span class="pub-info">${i.publisher}</span>
	          <c:forEach items="${i.tags}" var = "o" >
	          	<span class="tags">${o.tag}, </span>
	          </c:forEach>
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
					    <c:when test='${status== "Available" }'>
					       <div class="col-md-4"><button class="reserve-inline btn btn-default">Reserve</button></div>
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
		
        
      

      <div class="row rm-gen-details">
        <div class="col-md-2">
        <img src="img/book_placeholder.jpg" class="rm-img" width="100%" style="margin: auto 0;"></div>
        <div class="col-md-10 rm-information">
          <a href="rm-details.html" class="title"><b>Title</b></a>
          <span class="author">Author</span>
          <span class="pub-info">Pub Info</span>
          <span class="tags">Tags</span>
          <br>
          <div class="row">
            <b>
            <div class="col-md-4"><u>Location</u></div>
            <div class="col-md-4"><u>Status</u></div>
            <div class="col-md-4"></div></b>
          </div>
          <div class="location-status">
            <div class="row">
              <div class="col-md-4"><span class="location">Location</span></div>
              <div class="col-md-4"><span class="available-status">Available</span></div>
              <div class="col-md-4"><button class="reserve-inline btn btn-default">Reserve</button></div>
            </div> 
          </div>

        </div>
      </div>

      <div class="row rm-gen-details">
        <div class="col-md-2">
        <img src="img/book_placeholder.jpg" class="rm-img" width="100%" style="margin: auto 0;"></div>
        <div class="col-md-10 rm-information">
          <a href="rm-details.html" class="title"><b>Title</b></a>
          <span class="author">Author</span>
          <span class="pub-info">Pub Info</span>
          <span class="tags">Tags</span>
          <br>
          <div class="row">
            <b>
            <div class="col-md-4"><u>Location</u></div>
            <div class="col-md-4"><u>Status</u></div>
            <div class="col-md-4"></div></b>
          </div>
          <div class="location-status">
            <div class="row">
              <div class="col-md-4"><span class="location">Location</span></div>
              <div class="col-md-4"><span class="borrowed-status">Borrowed</span></div>
              <div class="col-md-4"><button class="reserve-inline btn btn-default">Reserve</button></div>
            </div> 
          </div>

        </div>
      </div>


      </div>


    </div>
  </div>
</div>
</body>
<script>

$('#submit-reserve').click(function(){
    alert("Reserved!");
})

$('.reserve-inline:not(.disabled)').click(function(){
    $('#overlay-screen').fadeIn("fast", function(){})
    $('#confirm-reservation').fadeIn(350, function(){});
})

$('#cancel-reserve').click(function(){
    $('#overlay-screen').fadeOut("fast", function(){})
    $('#confirm-reservation').fadeOut(350, function(){});
})

$('#overlay-screen').click(function(){
    $('#overlay-screen').fadeOut("fast", function(){})
    $('#confirm-reservation').fadeOut(350, function(){});
})

</script>
</html>
