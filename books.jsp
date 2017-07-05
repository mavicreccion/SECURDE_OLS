<!DOCTYPE html>
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
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    
    
    <!--local resources-->
    <link rel="stylesheet" type="text/css" href="img/icons_by_freepik/font/flaticon.css"> 
    <link rel="stylesheet" href="css/layouts/side-menu.css">
    <link rel="stylesheet" href="css/content.css">
    <link rel="stylesheet" href="css/inputs.css">
</head>
<body>
<div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>


    About 1,440,000,000 results

    <div id="menu">
        <div class="menu-logo" align="center">
            <a href="index.html"><img src="img/logoicon.png" width="60%" height="60%" /></a>
        </div>
        <div class="pure-menu">
            <ul class="pure-menu-list">
            <div class="pure-menu-heading" href="#">Search By: </div>
                <li class="pure-menu-item"><i class="flaticon-book"></i><a href="books.html" class="pure-menu-link">Books</a></li>
                <li class="pure-menu-item"><i class="flaticon-users"></i><a href="mtg-rooms.html" class="pure-menu-link">Meeting Rooms</a></li>
            </ul>
    
            <ul class="pure-menu-list">
               <li class="pure-menu-item"><i class="flaticon-chat"></i><a href="about_us.html" class="pure-menu-link">About Us</a></li> 
            </ul>
            
        </div>
        <div class="menu-bottom">
            <div class="menu-bottom-account">
                <a href="registration.html" class="menu-bottom-account-link">
                    <span id="account-name">Sign-Out</span>
                    <div class="nextimg">></div>
                </a>
            </div>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>SHS Online Library System</h1>
            <h2>Reserve Books and Meeting Rooms anytime, anywhere!</h2>
        </div>
        <div class="content">

        <h2 align="center">Search for Books</h2>

        <form id="book-search">
            <div class="pure-g">
                <div class="pure-u-3-24"> <h3>Search</h3> </div>
                <div class="pure-u-3-5" style="margin: auto;"> 
                    <input id="book-search-bar" type="text" class="search-bar" />
                </div>
                <div class="pure-u-1-4" style="margin: auto;"> 
                    <input id="book-search-bar-button" class="submit-button" type="submit" value="Submit" /> 
                </div>
            </div>

            <p>Type the search term you want to find. Example: 
            <ul>
                <li> Italian Cooking</li>
                <li> The Appeal </li>
            </ul>
            </p>
        </form>
            
        </div>
    </div>
</div>




<script src="js/layouts/ui.js"></script>

</body>
</html>
