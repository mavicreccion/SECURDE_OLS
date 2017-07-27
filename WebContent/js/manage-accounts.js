$(document).ready(function(){
	
	getLockedAccounts();
	
	$("#add-acc-tab").click(function(e){
	   $(".content-unlock-account").fadeOut(350, function(){});
	   $(".content-add-account").fadeIn(350, function(){});
	});

	$("#unlock-acc-tab").click(function(e){
		$(".content-unlock-account").fadeIn(350, function(){});
		$(".content-add-account").fadeOut(350, function(){});
		getLockedAccounts();
	});

});


function unlockAccount(idnumber){
	$.ajax({
		url: "AdminUnlockAccountServlet",
		method: "post",
		data: {
			'id_number': idnumber,
		},
		dataType: "json",
		success: function(result) {
			//refresh
			
			getLockedAccounts();
		}
	});
}

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

function unlockClickEvent(button){
	
	alert("Unlocking " + $(button).attr('id'));
	unlockAccount($(button).attr('id'));
}

function displayTableLockedAccounts(accounts){
	var rowContainer = $(".row-container tbody");
	
	rowContainer.find('tr').remove();
	
	if(accounts.length <= 0){
		$(".no-results").show();
		$(".row-container").hide();
		return;
	}
	
	$(".no-results").hide();

	for(var i=0; i<accounts.length; i++){
		rowContainer.append(
			"<tr> <th scope=\"row\">" + (i+1) + "</th>" + 
			"<td>" + accounts[i].lastName + ", " + accounts[i].firstName + "</td>" + 
			"<td>" + accounts[i].idnumber + "</td>" + 
			"<td>" + accounts[i].userType + "</td>" + 
			"<td> <span class=\"reserved-status\">" + accounts[i].status + "</span> </td>" + 
			"<td>" + 
				"<button type=\"submit\" id=\"" + accounts[i].idnumber + "\"" + 
				"class=\"btn btn-default unlock-btn submit-btn form-components-rd erase-margin auto-width\" " +
				"onclick=\"unlockClickEvent(this)\" \">Unlock</button></div>" + 
			"</td>" 
		);
	}
	
	
}