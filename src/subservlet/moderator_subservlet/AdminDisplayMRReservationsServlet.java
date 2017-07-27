package subservlet.moderator_subservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import model.ReservedRoom;
import service.RoomService;
import servlet.MasterServlet;


public class AdminDisplayMRReservationsServlet {

	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminDisplayMRReservationsServlet";
	
    public AdminDisplayMRReservationsServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }
	
	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY MR RESERVATIONS GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY MR RESERVATIONS POST");
    	
    	ArrayList<ReservedRoom> mrList = RoomService.getReservedRoomsAtThisDateADMIN(new Date());
    	request.getSession().setAttribute(ReservedRoom.TABLE_NAME, mrList);
    	request.getSession().setAttribute("rMRSize", mrList.size());
    	
    	for(int i=0; i<mrList.size(); i++){
    		System.out.println(mrList.get(i).getMr_name());
    	}

    	
    	request.getRequestDispatcher("/a-manage-mr-reservations.jsp").forward(request, response);
    	
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}
	
}
