$('#submit-reserve').click(function(){
    alert("Reserved!");
})

$('.reserve-inline:not(.disabled)').click(function(){
    $('#overlay-screen').fadeIn("fast", function(){})
    $('#confirm-reservation').fadeIn(350, function(){});
})

$('#cancel-reserve').click(function(){
    $('#overlay-screen').fadeOut("fast", function(){})
    $('#confirm-reservation').fadeOut(350, function(){});
})

$('#overlay-screen').click(function(){
    $('#overlay-screen').fadeOut("fast", function(){})
    $('#confirm-reservation').fadeOut(350, function(){});
})
