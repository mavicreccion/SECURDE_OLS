package subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class LoginServlet
 */
public class SignInServlet {

	public static final String URL = "/SignInServlet";
	
    private SignInServlet() { }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
    	System.out.println("SIGN IN SERVLET  GET");
    	
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SIGN IN SERVLET POST");
		
		//retrieve attributes sign in details (idnumber, password)
		String idNumber = (String) request.getParameter(User.COL_IDNUMBER);
		String password = (String) request.getParameter(User.COL_PASSWORD);
		System.out.println("[idNumber  --- Password] : " + idNumber + "    " + password);
		//search user from db
		User user = UserService.loginUser(idNumber, password);
		//If user exists, proceed to home page
		if(user!=null){
			System.out.println("USER NOT NULL ");
			// Create cookie
			Cookie idNumURLcookie = new Cookie(User.COL_IDNUMBER, user.getIDNumber());
			// Add cookie to list of cookies
			response.addCookie(idNumURLcookie);		
			
			//Pass first name and last name of user
			request.setAttribute(User.COL_FIRSTNAME, user.getFirstName());
			request.setAttribute(User.COL_LASTNAME, user.getLastName());
			
			request.getRequestDispatcher("/HomePageServlet").forward(request, response);
		}
		else
			request.getRequestDispatcher("sign_in_sign_up.jsp").forward(request, response);
		//else, display error message
		
		
		
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}
