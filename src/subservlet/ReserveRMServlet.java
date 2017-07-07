package subservlet;

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

/**
 * Servlet implementation class AccountPageServlet
 */
//@WebServlet("/AccountPageServlet")
public class ReserveRMServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/ReserveRMServlet";
       
    public ReserveRMServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("RESERVE RM PAGE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("RESERVE RM PAGE POST");
    	
    	String location = request.getParameter("locationID");
    	System.out.println("LOCATION " + location);
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
		
		//If user is logged in
		if(user != null)
		{
			/*request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
											user.getFirstName() + " " + user.getLastName());*/
			System.out.println("USER IS NOT NULLLLLLL");
			System.out.println("ID NUM   " + location);
			
			ReadingMaterial rm = ReadingMaterialService.getRMByID(location, user.getUserType());
			rm.setUserReserved(user);
			ReadingMaterialService.reserveRM(rm);
			
			request.getRequestDispatcher("HomePageServlet").forward(request, response);
			
		}
		else
			request.getRequestDispatcher("sign_in_sign_up.jsp").forward(request, response);
		
		
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
