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
    <link rel="stylesheet" type="text/css" href="css/alert/alert.css"> 
</head>

<body>
<div class="container-fluid">
  <div class="row">
    <jsp:include page="reusable/navbar.jsp"/>    
    <div class="col-sm-9 col-lg-10 content admin-area">
      <!-- your page content -->
      <div class="header">
       <h1>SHS Online Library System</h1>
       <h2>Manage Books. Add Books. Delete Books. Edit Books.</h2>
       <h3></h3>
      </div>
      

      <div class="btn-group btn-group-justified" role="group" aria-label="...">
        <div class="btn-group"  role="group">
          <button type="button" id="browse-rm-tab" class="btn btn-default">Browse Reading Materials</button>
        </div>
        <div class="btn-group"  role="group">
          <button type="button" id="add-rm-tab" class="btn btn-default">Add Reading Material</button>
        </div>
      </div>
      <br><br><br>
      
      <!-- SEARCH BAR -->
      <jsp:include page="reusable/search-bar.jsp"/>    
      <!-- END OF SEARCH BAR -->

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
      
      <div id="content-browse-book">
       <div class="rm-results">
          <b class="results-found" style="margin: -1px;"><span id="results-found"></span> results found ...</b>
          <!--  INSERT AJAX HERE -->
        </div>
      </div>
      
<!-- inser alerts beyond this point -->
<div id="ohsnap">
</div>

<!-- don't go beyond this point -->
    </div> <!-- end of content -->
  </div> <!-- end of row -->
</div> <!-- end of container-fluid -->

<form id="GoToRMDetails" method="post" action="RMDetailsServlet">
	<input type="hidden" id="rmID_location" name="rmID_location" value=""/>
</form>	

<!--  INSERT SCRIPT TAGS HERE -->
<!-- must be in every page -->
<script src="js/jquery-3.0.0.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="vitalets-bootstrap-datepicker-c7af15b/js/bootstrap-datepicker.js"></script>
<script src="js/app.js"></script>			
<!-- //////////////////// -->
<script src="js/register.js"></script>
<script src="js/manage-books.js"></script>


</body>
</html>