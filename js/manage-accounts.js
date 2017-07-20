$(document).ready(function(){
	
	getLockedAccounts();
	
	$("#btn_add_account").click(function(e){
	   $(".content-unlock-account").fadeOut(350, function(){});
	   $(".content-add-account").fadeIn(350, function(){});
	})

	$("#btn_unlock_account").click(function(e){
		$(".content-unlock-account").fadeIn(350, function(){});
		$(".content-add-account").fadeOut(350, function(){});
		getLockedAccounts();
	})

	$("#unlock-acc-btn").click(function(e){
		console.log("Unlocking Account...");
		//add ajax here that will unlock account.
	});
	
});


function getLockedAccounts(){
	$.ajax({
		url: "AdminDisplayAccountsServlet",
		method: "get",
		data: {
		},
		dataType: "json",
		success: function(result) {
			/* add to the html */
			//displayLockedAccounts(result);
			displayTableLockedAccounts(result);
		}
	});
}

function displayTableLockedAccounts(accounts){
	var tableContainer = $(".locked-accounts");
	var table = "<table class=\"table table-striped\">";
	var rowHeader = "<thead><tr> <th>#</th> <th>Name</th> <th>ID Number</th> <th>UserType</th> <th>Status</th> <th></th> </tr></thead>";
	
	for(var i=0; i<accounts.length; i++){
		tableContainer.append(
			table + rowHeader + 
			"<tr> <th scope=\"row\">" + (i+1) + "</th>" + 
			"<td>" + accounts[i].lastName + ", " + accounts[i].firstName + "</td>" + 
			"<td>" + accounts[i].idnumber + "</td>" + 
			"<td>" + accounts[i].userType + "</td>" + 
			"<td> <span class=\"reserved-status\">LOCKED</span> </td>" + 
			"<td>" + 
				"<button type=\"submit\" id=\"" + accounts[i].idnumber + "\"" + 
				"class=\"btn btn-default unlock-btn submit-btn form-components-rd erase-margin auto-width\">Unlock</button></div>" + 
			"</td>" 
		);
	}
}