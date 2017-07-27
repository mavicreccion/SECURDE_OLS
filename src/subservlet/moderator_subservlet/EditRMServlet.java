package subservlet.moderator_subservlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RMType;
import model.ReadingMaterial;
import service.ReadingMaterialService;
import servlet.MasterServlet;

/**
 * Servlet implementation class EditRMServlet
 */
//@WebServlet("/DeleteRMServlet")
public class EditRMServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/EditRMServlet";
       
    public EditRMServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("EDIT GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("EDIT POST");
    	
    	System.out.println("RM- " + request.getParameter("newRMType").toUpperCase());
    	System.out.println("Title " + request.getParameter(ReadingMaterial.COL_TITLE));
    	System.out.println("Loc " + request.getParameter(ReadingMaterial.COL_RMID));
    	System.out.println("Author " + request.getParameter(ReadingMaterial.COL_AUTHOR));
    	System.out.println("Publisher " + request.getParameter(ReadingMaterial.COL_PUBLISHER));
    	System.out.println("Year " + request.getParameter(ReadingMaterial.COL_YEAR));
    	System.out.println("Tags " + request.getParameter(ReadingMaterial.COL_TAG));
    	
    	ReadingMaterial rm = new ReadingMaterial();
    	rm.setRMType(RMType.getValue(request.getParameter("newRMType").toUpperCase()));
    	rm.setTitle(request.getParameter(ReadingMaterial.COL_TITLE));
    	rm.setRMID_Location(request.getParameter(ReadingMaterial.COL_RMID));
    	rm.setAuthor(request.getParameter(ReadingMaterial.COL_AUTHOR));
    	rm.setPublisher(request.getParameter(ReadingMaterial.COL_PUBLISHER));
    	rm.setYear(Integer.parseInt(request.getParameter(ReadingMaterial.COL_YEAR)));
    	rm.setTags(request.getParameter(ReadingMaterial.COL_TAG));
    	
    	boolean result = ReadingMaterialService.editRM(rm);
    	
    	System.out.println("EDITTING BOOK : " + result);
    	
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
