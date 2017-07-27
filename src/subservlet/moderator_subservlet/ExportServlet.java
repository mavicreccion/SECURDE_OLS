package subservlet.moderator_subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReadingMaterial;
import model.User;
import service.ReadingMaterialService;
import service.UserService;
import servlet.MasterServlet;
import utils.DataExport;

/**
 * Servlet implementation class ExportServlet
 */
//@WebServlet("/ExportServlet")
public class ExportServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/ExportServlet";
       
    public ExportServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ExportServlet GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ExportServlet POST");
    	
    	String filename = request.getParameter("filename");
    	filename = "C:\\Users\\Ronnie Nieva\\Documents\\Dydy\\output.xlsx";
    	
    	boolean result = DataExport.exportAll(filename);
    	//boolean result = DataExport.exportAll();
    	//boolean result = true;
    	if(result) {
    		// redirect to success page
    	} else {
    		// redirect to fail page
    	}
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
