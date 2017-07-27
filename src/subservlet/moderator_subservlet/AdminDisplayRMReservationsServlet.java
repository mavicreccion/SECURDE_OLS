package subservlet.moderator_subservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import model.ReservedRoom;
import service.ReadingMaterialService;
import service.RoomService;
import servlet.MasterServlet;


public class AdminDisplayRMReservationsServlet {

	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminDisplayRMReservationsServlet";
	
    public AdminDisplayRMReservationsServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }
	
	private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY RM RESERVATIONS GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	// TODO Auto-generated method stub
    	System.out.println("DISPLAY RM RESERVATIONS POST");
    	
    	ArrayList<ReadingMaterial> rmList = ReadingMaterialService.getAllCurrentReservedRM();
    	request.getSession().setAttribute(ReadingMaterial.TABLE_RESERVEDRM, rmList);
    	request.getSession().setAttribute("rRMSize", rmList.size());
    	
    	for(int i=0; i<rmList.size(); i++){
    		System.out.println("Reserver: " + rmList.get(i).getUserReserved().getIdnumber());
    	}
    	
    	
    	request.getRequestDispatcher("/a-manage-rm-reservations.jsp").forward(request, response);
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}
	
}
