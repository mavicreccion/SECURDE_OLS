var sampleUser = {firstName: "Dyan Raisa",
          lastName: "Nieva",
          idNumber:11400366,
          }

var user = sampleUser;


$(document).ready(function(){

	initUserLink();
	initSearchToggle();
});


function initUserLink(){
  if(user != null){
    $('#account-name').text(user.firstName + " " + user.lastName);
    $('#account-name').parent("a").attr('href', 'profile.html');
    //$('#account-name').("href", "http://www.google.com/");
  }
}

function initSearchToggle(){
  /* do not put in index.html */
  $('#toggle-search').click(function(){
    $('#overlay-screen').fadeIn("fast", function(){})
    $('#content-search').fadeIn(350, function(){});
    $('#search-bar').focus();
  })

  $('#overlay-screen').click(function(){
      $('#overlay-screen').fadeOut("fast", function(){})
      $('#content-search').fadeOut(350, function(){});
  })
}


/* "db" */