package subservlet;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReservedRoom;
import model.Room;
import service.RoomService;
import servlet.MasterServlet;
import utils.Utils;

/**
 * Servlet implementation class MeetingRoomPage
 */

public class MeetingRoomPageServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL = "/MeetingRoomPageServlet";
 
    public MeetingRoomPageServlet() {super();    }


    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MEETING ROOM PAGE GET");
		
		//check if a user is logged in
			//if yes, load first name last name
		//Load Meeting room details
	}


	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MEETING ROOM PAGE POST");
		//User user = null;
		
		ArrayList<ReservedRoom> roomList = RoomService.getReservedRoomsAtThisDateUSER(new Date());
		
		int[] timeSlots = Utils.getTimeSlots();
		ArrayList<String> timeID = new ArrayList<String>();
		ArrayList<String> timeString = new ArrayList<String>();
		for(int i=0; i<timeSlots.length; i++){
			if(i < timeSlots.length - 1){
				if(timeSlots[i] < 1000){
					System.out.println("0" + timeSlots[i] + " - " + timeSlots[i+1]);
					timeString.add("0" + timeSlots[i] + " - " + timeSlots[i+1]);
				}
				else{
					System.out.println(timeSlots[i] + " - " + timeSlots[i+1]);
					timeString.add(timeSlots[i] + " - " + timeSlots[i+1]);
				}
				timeID.add(timeSlots[i] + "");
			}
			
		}
		
		for(int i=0; i<roomList.size(); i++){
			System.out.println(roomList.get(i).getMrID());
		}
		
		request.setAttribute(Room.TABLE_NAME, RoomService.getALLRooms());
		request.setAttribute(ReservedRoom.TABLE_NAME, roomList);
		request.setAttribute("timeStart", timeID);
		request.setAttribute("timeSlots", timeString);
		
		
		/*
    	
    	//Check if a user is logged in
		Cookie[] cookies = request.getCookies();

		System.out.println("[Cookies]: " + cookies.length);
		//Search specific cookie
		for(int i = 0; i < cookies.length; i ++) {
			System.out.println(cookies[i].getName());
			if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
				user = UserService.viewProfileUser(cookies[i].getValue());
			}
		}
		
		//If user is logged in
		if(user != null)
		{
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
											user.getFirstName() + " " + user.getLastName());
		}
		else
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
					"Sign In");
		*/
		request.getRequestDispatcher("meeting-rooms.jsp").forward(request, response);
	}

	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}
