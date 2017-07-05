$(document).ready(function(){
  if(user != null){
    //window.location = "index.html";
  }

	initBdayDatePicker();
  initEditDetailsButton();
});



function initBdayDatePicker(){
  var currentDate = {
    date: new Date().getDate(),
    year: new Date().getFullYear(),
    month: new Date().getMonth(),
  }
  var startDate = new Date(currentDate.year-60, currentDate.month, currentDate.date);
  var endDate = new Date(currentDate.year+60, currentDate.month, currentDate.date);
  $('#bdaypicker').datepicker({
    format:"yyyy-mm-dd",
    startDate: startDate,
    endDate: endDate,});
  $('#bdaypicker').datepicker('update', currentDate.year + '-' + currentDate.month + '-' + currentDate.date);
}



function initEditDetailsButton(){
  $("#edit-profile").click(function(e){
    e.preventDefault();
    $("#edit-profile").fadeOut("fast", function(){});
    $("#submit-changes").show("fast", function(){});
    $(".form-components-rd:not(.btn)").attr('disabled', false);
    $("#bdaypicker span").css("display", "inline-block");
    $(".form-components-rd:not(.btn)").removeClass("register-input-disabled");
  });

  $("#cancel-changes").click(function(e){
    e.preventDefault();
    $("#submit-changes").hide("fast", function(){});
    $(".form-components-rd:not(.btn)").attr('disabled', true);
    $("#bdaypicker span").css("display", "none");
    $("#edit-profile").fadeIn("fast", function(){});
    $(".form-components-rd:not(.btn)").addClass("register-input-disabled");


  })

  $("#submit-changes").click(function(e){
    e.preventDefault();
    $("#submit-changes").hide("fast", function(){});
    $(".form-components-rd:not(.btn)").attr('disabled', true);
    $("#bdaypicker span").css("display", "none");
    $(".form-components-rd:not(.btn)").addClass("register-input-disabled");

    $("#edit-profile").fadeIn("fast", function(){});

    //form.submit
  })
}

