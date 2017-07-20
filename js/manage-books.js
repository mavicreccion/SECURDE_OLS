$(document).ready(function(){

	$("#submit-search").click(function(e){
		e.preventDefault();
		console.log("Searching...");
		getRMSearch();
		
	});
	
	$(".override-btn:not(.disabled)").click(function(e){
	    $('#overlay-screen').fadeIn("fast", function(){});
	    $("#confirm-override").fadeIn(350, function(){});
	});
	
	$(".delete-btn:not(.disabled)").click(function(e){
	    $('#overlay-screen').fadeIn("fast", function(){});
	    $("#confirm-override").fadeIn(350, function(){});
	});
	
	 $('#overlay-screen').click(function(e){
	    $("#confirm-override").fadeIn(350, function(){});
	
	 });
	
	 $("#add-rm-tab").click(function(e){
	    $("#content-add-book").fadeIn(350, function(){});
	    $("#content-search").fadeOut(350, function(){});
	    $("#content-browse-book").fadeOut(350, function(){});
	    $("#content-add-book").fadeIn(350, function(){});
	 });
	
	 $("#browse-rm-tab").click(function(e){
	    $("#content-add-book").fadeOut(350, function(){});
	    $("#content-search").fadeIn(350, function(){});
	    $("#content-browse-book").fadeIn(350, function(){});
	    $("#content-add-book").fadeOut(350, function(){});
	 });
	
	 $("#submit-add-rm").click(function(e){
		$("#newRMType").val($("#rm-type option:selected").val());
	    $("#addNewRM").submit();
	 });
	 
	 $("#btn_add_account").click(function (e) {
		console.log("Add Account CLICKED");
		$("#AddAccountForm").submit();
	 });
	 
	 window.setTimeout(function() {
	    $(".alert").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 4000);
});

function getRMSearch(){
	var searchString = $("#search-bar").val();
	//searchString = "harry potter";
	var searchFilter = $("#search-filter").val();
	//searchFilter = "TITLE";
	var searchCollection = $("#search-collection").val();
	//searchCollection = "ALL";
	
	$.ajax({
		url: "AdminRMSearchResultsPageServlet",
		method: "get",
		data: {
			stringToSearch: searchString,
			RMFilter: searchFilter,
			RMType: searchCollection,
			
		},
		dataType: "json",
		success: function(result) {
			/* add to the html */
			console.log("Result: \n");
			console.log(result);
			console.log("Result no: " + result.length);
			
			addSearchResults(result);
		}
	});
}

function setTitle (title){
	return "<a onclick=\"review('${rmID}')\" class=\"title\">" + title + "</b></a>";
}

function setAuthor(author){
	return "<span class=\"author\">" + author + "</span>";
}

function setPubInfo(publisher, year){
	return "<span class=\"pub-info\">" + publisher +", " + year + "</span>";
}

function setStatus(status){
	var statusUI = "";
	if(status == 'RESERVED'){
		statusUI = "<div class=\"col-md-3\"><span class=\"reserved-status\">" + status + "</span></div>";
	}
	else if(status == 'AVAILABLE'){
		statusUI = "<div class=\"col-md-3\"><span class=\"available-status\">" + status + "</span></div>";
	}
	else{ //BORROWED
		statusUI = "<div class=\"col-md-3\"><span class=\"borrowed-status\">" + status + "</span></div>";
	}
	return statusUI;
}

function setLocationStatus(location, status){
	var locationHeader = "<div class=\"row\"><b>" +
						    "<div class=\"col-md-3\"><u>Location</u></div>" + 
						    "<div class=\"col-md-3\"><u>Status</u></div>" + 
						    "<div class=\"col-md-3\"></div>" +
						    "<div class=\"col-md-3\"></div></b>" +
						  "</div>";
	var header = "<div class=\"location-status\">";
	var rowHeader = "<div class=\"row\">";
	var locationId = "<div class=\"col-md-3\"><span class=\"location\">" + location + "</span></div>";
	var disabledOverride = "<div onclick=\"clickedType('${loc}')\" class=\"col-md-3\">" +
							"<button onclick=\"clickedType(${i.RMID_Location })\" " +
							"class=\"reserve-inline btn btn-default override-btn disabled\">Override<br> Reservation</button></div>";
	
	var abledOverride = "<div onclick=\"clickedType('${loc}')\" class=\"col-md-3\">" +
	"<button onclick=\"clickedType(${i.RMID_Location })\" " +
	"class=\"reserve-inline btn btn-default override-btn\">Override<br> Reservation</button></div>";
	
	var disabledDelete = "<div class=\"col-md-3\"><button class=\"reserve-inline btn btn-default delete-btn disabled\"><i class=\"flaticon-trash\"></i></button></div>";
	
	var abledDelete = "<div class=\"col-md-3\"><button class=\"reserve-inline btn btn-default delete-btn\"><i class=\"flaticon-trash\"></i></button></div>";
	
	var overrideAndDelete = "";
	
	if(status == 'RESERVED'){
		overrideAndDelete = abledOverride + disabledDelete;
	}
	else if(status == 'AVAILABLE'){
		overrideAndDelete = disabledOverride + abledDelete;
	}
	else{ //BORROWED
		overrideAndDelete = disabledOverride + disabledDelete;
	}
	
	return (
		locationHeader +
		header + 
		rowHeader +
		locationId +
		setStatus(status) +
		overrideAndDelete +
		"</div> </div>"
		);
		
}

function addSearchResults(results){
	var resultsContainer = $(".rm-results");
	
	var rmGenDetails = "<div class=\"row rm-gen-details\">";
	var rmImageRow = "<div class=\"col-md-2\"><img src=\"img/book_placeholder.jpg\" class=\"rm-img\" width=\"100%\" style=\"margin: auto 0;\"></div>";
	var rmInfoStart = "<div class=\"col-md-10 rm-information\">";
	
	$("#results-found").text(results.length);
	
	for(var i=0; i<results.length; i++){
		resultsContainer.append(
			rmGenDetails + 
			rmImageRow + 
			rmInfoStart + 
			setTitle(results[i].title) +
			setAuthor(results[i].author) +
			setPubInfo(results[i].publisher, results[i].year) +
			setLocationStatus(results[i].RMID_Location, results[i].status) +
			"</div>" +
			"</div>" +
			"<br><br><br>"
		);
		
		
		
	}
}