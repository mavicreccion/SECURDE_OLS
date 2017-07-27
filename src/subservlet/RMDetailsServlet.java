package subservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RMType;
import model.ReadingMaterial;
import model.Review;
import model.User;
import model.UserType;
import service.ReadingMaterialService;
import service.ReviewService;
import servlet.MasterServlet;

/**
 * Servlet implementation class AccountPageServlet
 */
//@WebServlet("/AccountPageServlet")
public class RMDetailsServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/RMDetailsServlet";
       
    public RMDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("RM SEARCH RESULTS PAGE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	// TODO Auto-generated method stub
    	System.out.println("RESERVE RM PAGE POST");
    	
    	User user = null;
    	
    	//TODO: THIS IS FOR DEBUGGING. PLEASE ERASE THIS!!
    	user = new User();
    	user.setIdnumber("11400366");
    	user.setUserType(UserType.LIBMNGR);
    	
    	HttpSession session = request.getSession();
    	
    	String rmID = request.getParameter(ReadingMaterial.COL_RMID); 
    	System.out.println("rmID : " + rmID);
		
		ReadingMaterial rm = ReadingMaterialService.getRMByID(rmID);
		
		System.out.println("Date Returned .. " + rm.getDateAvailable());
		
		session.setAttribute(ReadingMaterial.TABLE_RM, rm);
		session.setAttribute(Review.TABLE_NAME, rm.getReviews());
		
		if(user != null){
			boolean hasBorrowed = ReviewService.checkIfBorrowed(user.getIdnumber(), rmID);
			session.setAttribute("hasBorrowed", hasBorrowed);
			
			boolean canEdit = false;
			if(user.getUserType() == UserType.LIBSTAFF || user.getUserType() == UserType.LIBMNGR){
				
				ArrayList<String> rmTypeStrings = new ArrayList<String>();
				for(int i=0; i<RMType.values().length; i++){
					rmTypeStrings.add(RMType.values()[i] + "");
				}
				
				session.setAttribute("rmtypelist", RMType.values());
				canEdit = true;
			}
			
			System.out.println(canEdit);
			
			session.setAttribute("canEdit", canEdit); 
		}
		
		
		request.getRequestDispatcher("rm-details.jsp").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else
			doPost(request, response);
	}


}
