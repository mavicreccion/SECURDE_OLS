package subservlet.moderator_subservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import model.Room;
import service.ReadingMaterialService;
import service.RoomService;
import servlet.MasterServlet;


public class AdminReservationsServlet {

	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminReservationsServlet";
	
    public AdminReservationsServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }
	
	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN RESERVATIONS GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN RESERVATIONS POST");
    	
    	//TODO: Error in 
    	ArrayList<ReadingMaterial> reservedRM = ReadingMaterialService.getAllCurrentReservedRM();
    	ArrayList<Room> reservedMR = RoomService.getALLRooms();
    	
    	for(int i = 0; i < reservedRM.size(); i++){
    		System.out.println(i + " : " + reservedRM.get(i).getTitle());
    		System.out.println("	 reserved: " + reservedRM.get(i).getDateReserved());
    		System.out.println("	 returned: " + reservedRM.get(i).getDateReturned());
    	}
    	
    	for(int i = 0; i < reservedMR.size(); i++){
    		System.out.println(i + " : " + reservedMR.get(i).getMrID());
    		System.out.println("	 : " + reservedMR.get(i).getRoomStatus());
    	}
    	
    	request.getSession().setAttribute("numOfRM", reservedRM.size());
    	request.getSession().setAttribute(ReadingMaterial.TABLE_RM, reservedRM);
    	request.getSession().setAttribute("numOfMR", reservedMR.size());
    	request.getSession().setAttribute(Room.TABLE_NAME, reservedMR);
    	
    	request.getRequestDispatcher("admin_reservations.jsp").forward(request, response);
    	
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}
	
}
