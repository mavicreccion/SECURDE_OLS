var rooms = [];
var STATUS = {
    OPEN : { value: 0, name : "Open", code: "O"},
    CLOSED : { value: 1, name : "Closed", code: "X"},
};

var openHour = 7;
var closeHour = 19;

var visible = false;
var username;
var password;
var roomNumber;
var input_userType = null;


$(document).ready(function(){
    initTable();
    initRoomStatuses();
    initToday();


    $('#cancel-reserve').click(function(){
        $('#overlay-screen').fadeOut("fast", function(){})
        $('#confirm-reservation').fadeOut(350, function(){});
    })

    $('#overlay-screen').click(function(){
        $('#overlay-screen').fadeOut("fast", function(){})
        $('#confirm-reservation').fadeOut(350, function(){});
    })

    editRoomStatus("0900", "0930", "R201", "CLOSED");
    editRoomStatus("1000", "0930", "R202", "CLOSED");
    editRoomStatus("1000", "1030", "R203", "CLOSED");
    editRoomStatus("1000", "1030", "R204", "CLOSED");
    editRoomStatus("1600", "1030", "R206", "CLOSED");
    editRoomStatus("1700", "1030", "R205", "CLOSED");
    editRoomStatus("1300", "1030", "R203", "CLOSED");
    editRoomStatus("1400", "1030", "R205", "CLOSED");
    editRoomStatus("1200", "1030", "R205", "CLOSED");
    editRoomStatus("0800", "1030", "R201", "CLOSED");
    editRoomStatus("1100", "1030", "R202", "CLOSED");
    editRoomStatus("1130", "1030", "R203", "CLOSED");
    editRoomStatus("1400", "1030", "R206", "CLOSED");
    editRoomStatus("1600", "1030", "R205", "CLOSED");
    editRoomStatus("1300", "1030", "R205", "CLOSED");
    

});


function editRoomStatus(timeStart, timeEnd, roomNumber, status){
	
    for(var i=0; i<$("#" + roomNumber).children().length; i++){
        var $currentRoom = $("#" + roomNumber + " td:nth-child(" + (i+2) + ")");
        if($currentRoom.attr('id') == timeStart){
        	console.log(roomNumber + " " + $currentRoom.attr('id'))
            changeRoomColor($currentRoom, status);
        }
    }
}

// if false, login popups
// else redirect to repective room geography
function checkIfLoggedIn() {
    /*
    if (logged_in == false && userType == "student") {
        showOverlay();
        showLoginForm();
        
    }
    else if(logged_in == true && userType == "student"){
        setVariables();
        //show confirmation popup below
    }
    */
    
    $('#overlay-screen').fadeIn("fast", function(){})
    $('#confirm-reservation').fadeIn(350, function(){})


    //showOverlay();
    //showEditRoomSlotsForm(userType);
}

function initToday(){
    var today = new Date();
    $("#month").text(getMonthString(today.getMonth()));
    $("#day").text(today.getDate());
    $("#year").text(today.getFullYear());
    $("#dayOfWeek").text(getDayString(today.getDay()));

}

function initTable(){
    var otherCells = "";
    for(var i=openHour; i<closeHour; i++){
        var time = i;
        if(i<10){
            time = "0"+i;  
        }
        // time += ":00"
        var startTag = "<th style=\"border-left: 1px dashed #c9c7c7;\">";

        var str1 = startTag + time + "00 - " + time + "30</th>";
        var timeAfterHour = i+1;
            if(i<10){
                timeAfterHour = "0"+timeAfterHour;  
            }
        var str2 = "<th>" + time + "30 - " + timeAfterHour + "00</th>"

        //putting the headers
         $("#table-of-rooms").find('tr').each(function(){
            $(this).find('th').eq(-1).after(str1);
            $(this).find('th').eq(-1).after(str2);
            // for(var i=0; i<4; i++)
            // $(this).find('td').eq(-1).after('<td></td>'); 
        });

        otherCells += '<td id="' + time + '00' + '" class = "otherCells"></td>';
        otherCells += '<td id="' + time + '30' + '" class = "otherCells"></td>';

    }


    var roomNumber = ["R201", "R202", "R203", "R204", "R205", "R206"];

   for(var i=0; i<roomNumber.length; i++){
        // G302A
        $('#table-of-rooms').find('tbody').append('<tr id = "' + roomNumber[i] + '"><td class="colHeader">' + roomNumber[i] + '</td>' + otherCells + '</tr>');
        
        for(var j=0; j<$("#" + roomNumber[i]).children().length; j++){
            $("#" + roomNumber[i] + " td:nth-child(" + (j+2) + ")").click(function(e){                
                isClickedRoom(this, "OPEN");
            });
        }
   
    }

    initRoomStatuses();
}

//initializes everything to open
/* @author: Dyan Nieva */


function initRoomStatuses(){
    var date = new Date();

    for(var i=0; i<$("#table-of-rooms tbody").children().length; i++){
        var currentRoom = $("#table-of-rooms tbody tr:nth-child(" + (i+3) + ")");
        for(var j=0; j< $("#table-of-rooms tbody tr:nth-child(" + (i+3) + ")").children().length; j++){

            var $currentTimeSlotRoom = $('#table-of-rooms tr:nth-child(' + (i+3) + ') td:nth-child(' + (j+2) + ')');

            changeRoomColor($currentTimeSlotRoom, "OPEN");
        }
    }
}

function isClickedRoom(room, status){   
    if(status == "OPEN"){
        console.log(room.parentElement.id + " - " + room.id);
        sessionStorage.setItem("roomNumber", room.parentElement.id + "");
        sessionStorage.setItem("timeStart", room.id + "");
        if(room.id < "1000")
                sessionStorage.setItem("timeEnd", "0" + room.id);
            else
                sessionStorage.setItem("timeEnd", room.id);
        sessionStorage.setItem("roomStatus", status);
        
        setVariables();

        $("#reserve-date").text((new Date().getMonth()+1) + "/" + new Date().getDate() + "/" + new Date().getFullYear());
        $("#reserve-time").text(room.id);
        checkIfLoggedIn(); //DO NOT DO THIS UNTIL SERVLETS ARE done
    }else if(userType == "student"){
        alertClickedRoom(status);
    }    
}

function changeRoomColor($currentRoom, status){
    switch(status){

        case 'OPEN':    
            $currentRoom.click(function(e){        
                isClickedRoom(this, "OPEN")
            })
            $currentRoom.addClass('openCell');
            $currentRoom.removeClass('closedCell');
            $currentRoom.removeClass('classesCell');
            $currentRoom.removeClass('breakCell');
        break;

        case 'CLOSED':
            $currentRoom.off('click');
            $currentRoom.click(function(e){        
                isClickedRoom(this, "CLOSED");
            });
            $currentRoom.removeClass('openCell');
            $currentRoom.addClass('closedCell');
            $currentRoom.removeClass('classesCell');
            $currentRoom.removeClass('breakCell');
        break;

        case 'CLASSES':
            $currentRoom.off('click');
            $currentRoom.click(function(e){        
                isClickedRoom(this, "CLASSES");
            });
            $currentRoom.removeClass('openCell');
            $currentRoom.removeClass('closedCell');
            $currentRoom.addClass('classesCell');
            $currentRoom.removeClass('breakCell');
        break;

        case 'BREAK':
            $currentRoom.off('click');
            $currentRoom.click(function(e){        
                isClickedRoom(this, "BREAK");
            });
            $currentRoom.removeClass('openCell');
            $currentRoom.remove('closedCell');
            $currentRoom.removeClass('classesCell');
            $currentRoom.addClass('breakCell');
        break;

    }
}


function setVariables(){
    /* do not do this until servlets are done :) thnaks -DY*/
    /*
    var roomStatusString = sessionStorage.getItem("roomStatus");
    var roomNumberString = sessionStorage.getItem("roomNumber");
    var timeStartString = sessionStorage.getItem("timeStart");
    
    $("#roomNumberForm").val(roomNumberString);
    $("#timeStartForm").val(timeStartString);
    $("#roomStatusForm").val(roomStatusString);
    */
}




/* ALL ABOUT TIME */
function getMonthString(month){
    var months = new Array();
    months[0] = "January";
    months[1] = "February";
    months[2] = "March";
    months[3] = "April";
    months[4] = "May";
    months[5] = "June";
    months[6] = "July";
    months[7] = "August";
    months[8] = "September";
    months[9] = "October";
    months[10] = "November";
    months[11] = "December";
    return months[month];
}

function getDayString(day){
    var days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
    return days[day];
}

