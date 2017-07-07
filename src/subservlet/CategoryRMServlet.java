package subservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RMType;
import model.ReadingMaterial;
import service.ReadingMaterialService;
import servlet.MasterServlet;

/**
 * Servlet implementation class CategoryRMServlet
 */
//@WebServlet("/CategoryRMServlet")
public class CategoryRMServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/CategoryRMServlet";
       
    public CategoryRMServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("CategoryRMServlet GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("CategoryRMServlet POST");
    	
    	RMType rmType = RMType.getValue(request.getParameter(RMType.ENUM_RMType));
    	
    	ArrayList<ReadingMaterial> rmList = ReadingMaterialService.getRMByType(rmType);
    	
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
