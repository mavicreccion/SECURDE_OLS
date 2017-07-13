package subservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RMFilter;
import model.RMType;
import model.ReadingMaterial;
import model.User;
import service.ReadingMaterialService;
import service.UserService;
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
    	
    	//Check if a user is logged in
		Cookie[] cookies = request.getCookies();

		System.out.println("[Cookies]: " + cookies.length);
		//Search specific cookie
		for(int i = 0; i < cookies.length; i ++) {
			System.out.println(cookies[i].getName());
			if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
				user = UserService.viewProfileUser(cookies[i].getValue());
			}
		}
		String review = request.getParameter("reviewID");
    	System.out.println("LOCATION " + review);
		//If user is logged in
		if(user != null)
		{
			/*request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
											user.getFirstName() + " " + user.getLastName());*/
			
			ReadingMaterial rm ;
			System.out.println("USER IS NOT NULLLLLLL");
			System.out.println("REVIEW   " + review);
			ArrayList<ReadingMaterial> rMaterials = ReadingMaterialService.getPastBorrowedRMOfUser(user.getIDNumber());
			System.out.println("Size + " + rMaterials.size());
			for(int i = 0 ; i < rMaterials.size(); i++)
			{
				rm = ReadingMaterialService.getRMByID(review, user.getUserType());
				System.out.println(rm.getRMID_Location() + " == " + review);
				if(rm.getRMID_Location() == review){
					request.getSession().setAttribute(ReadingMaterial.TABLE_RM, rm);
					System.out.println(rm.getReviews().size()+"");
					request.getSession().setAttribute("reviewList", rm.getReviews());
					request.getRequestDispatcher("rm-details.jsp").forward(request, response);
				}
			}
			
		}else
			request.getRequestDispatcher("sign_in_sign_up.jsp").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
