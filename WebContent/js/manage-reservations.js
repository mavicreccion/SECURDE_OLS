$(document).ready(function(){
	
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
	 
	 $(document).on("click", ".title", function(){
		var rmID = $(this).parent().attr('id');
		getRMDetails(rmID);
	 });
	 
	 $(document).on("click", ".override-btn", function(){
		 alert($(this).hasClass("mr"));
		 var id = $(this).parent().attr('id');
		 
		 if($(this).hasClass("rm"))
			 overrideRMReservation(id);
		 
		 else if($(this).hasClass("mr"))
			 overrideMRReservation(id);
	 });
	 
});

function getRMDetails(rmID){
	$("#rmID_location").val(rmID);
	$("#GoToRMDetails").submit();
}

function overrideRMReservation(rmID){
	$.ajax({
		url: "OverrideReservationRMServlet",
		method: "post",
		data: {
			'rmID_location': rmID,
		},
		dataType: "json",
		success: function(result) {
			location.reload();
		}
	});
}

function overrideMRReservation(mrID){
	$.ajax({
		url: "OverrideReservationRoomServlet",
		method: "post",
		data: {
			'reservedMRID': mrID,
		},
		dataType: "json",
		success: function(result) {
			location.reload();
		}
	});
}
