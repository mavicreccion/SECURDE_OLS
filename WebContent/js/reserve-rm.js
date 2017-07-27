/* RESERVE-RM.JS 
 * Includes button events for reservation of rm 
 * 		1) Reserve Book Button events
 * 		2) Confirm Reservatipn Button events
 * 		3) Cancel Reservation Button events
 */

function clickedType(id)
{
	$("#confirm-reservation").fadeIn("fast", function(){});
	$("#overlay-screen").fadeIn(fast, function(){});
	
	submitReservation(id);
}

function submitReservation(id){
	 console.log("hahaha " + id);
 	$("#locationID").val(id);
 	$("#locationID").text(id);
 	console.log("ehhe " + $("#locationID").val()); 
 	 $("#reserveForm").submit();
}

function review(id)
{
	 console.log("hahaha " + id);
	$("#reviewID").val(id);
	$("#reviewID").text(id);
	console.log("EHHE " + $("#reviewID").val()); 
	 $("#reviewForm").submit();
	
}

$('.reserve-inline:not(.disabled)').click(function(){
	console.log(status);
	$("#location").val(status);
	//$("#reserveForm").submit();
});
 
$('#cancel-reserve').click(function(){
    $('#overlay-screen').fadeOut("fast", function(){})
    $('#confirm-reservation').fadeOut(350, function(){});
});

$('#overlay-screen').click(function(){
    $('#overlay-screen').fadeOut("fast", function(){})
    $('#confirm-reservation').fadeOut(350, function(){});
});
