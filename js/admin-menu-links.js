$(document).ready(function(){

	$('#books-tab').click(function(e) {
	   e.preventDefault();
	   $("#ManageBooks").submit();
    });
	
	$('#rm-reserve-tab').click(function(e) {
	   e.preventDefault();
	   $("#ManageRMReserve").submit();
    });
	
	$('#mr-reserve-tab').click(function(e) {
	   e.preventDefault();
	   $("#ManageMRReserve").submit();
    });
	
	$('#accounts-tab').click(function(e) {
	   e.preventDefault();
	   $("#ManageAccounts").submit();
    });
	
	$('#log-tab').click(function(e) {
	   e.preventDefault();
	   $("#ExportLog").submit();
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