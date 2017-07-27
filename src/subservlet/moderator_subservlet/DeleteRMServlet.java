package subservlet.moderator_subservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import model.UserType;
import service.ReadingMaterialService;
import servlet.MasterServlet;

/**
 * Servlet implementation class DeleteRMServlet
 */
//@WebServlet("/DeleteRMServlet")
public class DeleteRMServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/DeleteRMServlet";
       
    public DeleteRMServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DELETE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DELETE POST");
    	
    	String rmID = request.getParameter(ReadingMaterial.COL_RMID);
    	boolean result = ReadingMaterialService.deleteRM(rmID);
    	System.out.println("Removing RM " + rmID + " : " + result);
    	PrintWriter pw = response.getWriter();
    	pw.write(result + "");
    	
    	
    	/*
    	if(result) {
    		// redirect to success page
    	} else {
    		// redirect to fail page
    	}*/
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
