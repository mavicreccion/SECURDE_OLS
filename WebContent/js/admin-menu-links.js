$(document).ready(function(){
	
	$('#books-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("a-manage-books.jsp");
	   $("#GoToPage").submit();
    });
	
	$('#rm-reserve-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("AdminDisplayRMReservationsServlet");
	   $("#GoToPage").submit();
    });
	
	$('#mr-reserve-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("AdminDisplayMRReservationsServlet");
	   $("#GoToPage").submit();
    });
	
	$('#accounts-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("a-manage-accounts.jsp");
	   $("#GoToPage").submit();
    });
	
	$('#log-tab').click(function(e) {
	   e.preventDefault();
	   $("#GoToPage #destination").val("ExportServlet");
	   $("#GoToPage").submit();
	});
	
 	$("#signInSignOut").click(function(e) {
 	   e.preventDefault();
 		console.log($("#account-name").text());
 		$("#signInForm").submit();
 	});
 	
 	$("#signInSignOut").click(function(e) {
 	   e.preventDefault();
  		console.log($("#account-name").text());
  		if($("#account-name").text() == "Sign In"){
  			console.log("GO TO SIGN IN");
  			window.location.href = "sign-in-sign-up.jsp";
  		}
  	});
});