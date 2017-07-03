$(document).ready(function(){
  if(user != null){
    //window.location = "index.html";
  }

	initBdayDatePicker();
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


