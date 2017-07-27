package subservlet.moderator_subservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import model.ReservedRoom;
import model.User;
import service.ReadingMaterialService;
import service.RoomService;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class OverrideReservationRoomServlet
 */
//@WebServlet("/OverrideReservationServlet")
public class OverrideReservationRoomServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/OverrideReservationRoomServlet";
       
    public OverrideReservationRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("OverrideReservationRoomServlet GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("OverrideReservationRoomServlet POST");
    	
    	int rmrID = Integer.parseInt(request.getParameter(ReservedRoom.COL_RESERVEDMRID));
    	boolean result = RoomService.cancelReserveMR(rmrID);
    	System.out.println("Canceled Reservation for " + rmrID + " : " + result);
    	PrintWriter pw = response.getWriter();
    	pw.write(result + "");
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
