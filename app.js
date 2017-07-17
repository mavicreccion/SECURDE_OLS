/* APP.JS 
 * Includes button events for elements seen in every page such as:
 * 		1) Search Toggle Button (the one in nav bar)
 * 		2) Account Name / Link initialization
 * 		3) Overlay panel event
 * 
 * This must be included in every page. Please. Thank you. -Dyan
 */

$(document).ready(function(){
	initUserLink();
	console.log("LOL HI");
	initSearchToggle();
});


function initUserLink(){
	/*
  if(user != null){
    $('#account-name').text(user.firstName + " " + user.lastName);
    $('#account-name').parent("a").attr('href', 'profile.html');
    //$('#account-name').("href", "http://www.google.com/");
  }*/
}

function initSearchToggle(){
  /* do not put in index.html */
	console.log($("#toggle-search"));
  $('#toggle-search').click(function(){
    $('#overlay-screen').fadeIn("fast", function(){});
    $('#content-search').fadeIn(350, function(){});
    $('#search-bar').focus();
  });

  $('#overlay-screen').click(function(){
      $('#overlay-screen').fadeOut("fast", function(){});
      $('#content-search').fadeOut(350, function(){});
  });
}


/* OVERLAY */ 
function showOverlay(){
	$('#overlay-screen').fadeIn("fast", function(){});
}

function hideOverlay(){
	$('#overlay-screen').fadeOut("fast", function(){});
}
/* END OVERLAY */