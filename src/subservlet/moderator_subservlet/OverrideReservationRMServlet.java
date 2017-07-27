package subservlet.moderator_subservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import model.User;
import model.UserType;
import service.ReadingMaterialService;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class OverrideReservationServlet
 */
//@WebServlet("/OverrideReservationServlet")
public class OverrideReservationRMServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/OverrideReservationRMServlet";
       
    public OverrideReservationRMServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("OverrideReservationServlet GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("OverrideReservationServlet POST");
    	
    	String rmID = request.getParameter(ReadingMaterial.COL_RMID);
    	ReadingMaterial rm = ReadingMaterialService.getRMByID(rmID);
    	boolean result = ReadingMaterialService.cancelResRM(rm.getReservedRMID());
    	System.out.println("Canceled Reservation for " + rm.getTitle() + " : " + result);
    	PrintWriter pw = response.getWriter();
    	pw.write(result + "");
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
