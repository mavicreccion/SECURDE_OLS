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
    <script src="js/jquery-3.0.0.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="js/app.js"></script>
    <script>
    
    function passReview(id){
    	
    	
    	
    	
    	
    }
    
    	$(".btn btn-default").click(function() {
    		$("#reviewForm").submit();
    	});
    </script>
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
            <a id = "signInSignOut" class="self-menu">
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
          <br>

          ${i.review}

        </div>
        </c:forEach>

        <div class="review divider">Reviewed on <i>6 / 30 / 2017</i>
          <br>

          • Library Manager - can only edit book information, add new book, delete book and override reservations. In addition, the manager can export to an excel sheet/XML of the status of all books and meeting rooms. • Library Staff - can only edit book information, add new book, delete book and availability of meeting rooms. • Administrator – can create new Library Manager, Library Staff, Library Student Assistant and accounts and assign temporary passwords, which if not changed within 24 hours, will render the account expired. Also, the administrator can export to an excel sheet/XML file of the status of all books and meeting rooms.

        </div>

        <div class="review divider">Reviewed on  <i>6 / 30 / 2017</i>
          <br>

          • Library Manager - can only edit book information, add new book, delete book and override reservations. In addition, the manager can export to an excel sheet/XML of the status of all books and meeting rooms. • Library Staff - can only edit book information, add new book, delete book and availability of meeting rooms. • Administrator – can create new Library Manager, Library Staff, Library Student Assistant and accounts and assign temporary passwords, which if not changed within 24 hours, will render the account expired. Also, the administrator can export to an excel sheet/XML file of the status of all books and meeting rooms.

        </div>

      </div>

    </div>
  </div>
</div>



</body>
</html>
