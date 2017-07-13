package subservlet.moderator_subservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import service.ReadingMaterialService;
import servlet.MasterServlet;

/**
 * Servlet implementation class ViewReservedRMServlet
 */
//@WebServlet("/ViewReservedRMServlet")
public class ViewReservedRMServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/ViewReservedRMServlet";
       
    public ViewReservedRMServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ViewReservedRMServlet GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ViewReservedRMServlet POST");
    	
    	ArrayList<ReadingMaterial> rmList = ReadingMaterialService.getAllCurrentReservedRM();
    	
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
