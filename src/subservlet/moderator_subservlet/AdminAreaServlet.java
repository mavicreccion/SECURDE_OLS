package subservlet.moderator_subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class SignInSignUpPageServlet
 */
//@WebServlet("/SignInSignUpPageServlet")
public class AdminAreaServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminAreaServlet";
       
    public AdminAreaServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN AREA PAGE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN AREA POST");
    	
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
		String userName = null;
		//If user is logged in
		if(user!=null)
		{
			userName = (String) user.getFirstName() + " " + user.getLastName();
		}else if(request.getAttribute(User.COL_FIRSTNAME)  != null)
		{
			userName = (String) request.getAttribute(User.COL_FIRSTNAME)  
					+ " " + request.getAttribute(User.COL_LASTNAME);
			request.getSession().setAttribute(User.COL_FIRSTNAME + User.COL_LASTNAME,
											userName);
		}
		else 
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
					"Sign In");
    	
    	request.getRequestDispatcher("admin_area.jsp").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
