$(document).ready(function(){
    $('#cancel-reserve').click(function(){
        $('#overlay-screen').fadeOut("fast", function(){});
        $('#confirm-reservation').fadeOut(350, function(){});
    });

    $('#overlay-screen').click(function(){
        $('#overlay-screen').fadeOut("fast", function(){});
        $('#confirm-reservation').fadeOut(350, function(){});
    });
    
    $(".otherCells:not(.closedCell)").click(function(e){                
    	$("#confirm-reservation").fadeIn(350, function(){});
    	$('#overlay-screen').fadeIn("fast", function(){});
    	console.log($(this).attr('id') + " " + $(this).parent().attr('id'));
    	var today = new Date();
    	$("#reserve-date").text(today.getFullYear() + "-" + (today.getMonth()+1) + "-" + today.getDate());
    	$("#reserve-time").text($(this).attr('id'));
    	$("#room-number").text($(this).parent().attr('id'));
    }); 	

});



