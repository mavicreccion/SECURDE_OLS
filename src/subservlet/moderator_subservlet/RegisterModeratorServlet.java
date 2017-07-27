package subservlet.moderator_subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SecretQuestion;
import model.User;
import model.UserType;
import service.SecretQuestionService;
import service.UserService;
import servlet.MasterServlet;
import utils.Utils;

/**
 * Servlet implementation class SignInSignUpPageServlet
 */
//@WebServlet("/RegisterModeratorServlet")
public class RegisterModeratorServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/RegisterModeratorServlet";
       
    public RegisterModeratorServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("SIGN IN SIGN UP PAGE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("Register Moderator Servlet POST");
    	
    	//Retrieve attributes entered by user
    	String firstname = request.getParameter(User.COL_FIRSTNAME);
    	String middleInitial = request.getParameter(User.COL_MI);
    	String lastname = request.getParameter(User.COL_LASTNAME);
    	System.out.println("Name: " + firstname + " " + middleInitial + " " + lastname);
    	String emailAddress = request.getParameter(User.COL_EMAIL);
    	String password = request.getParameter(User.COL_PASSWORD);
    	String idNumber = request.getParameter(User.COL_IDNUMBER);
    	String bday = request.getParameter(User.COL_BDAY);
    	System.out.println("BDAY: " + bday);
    	String questionID = request.getParameter(SecretQuestion.COL_SQID);
    	SecretQuestion secretQuestion = SecretQuestionService.getSecretQuestionByID(Integer.valueOf(questionID) + 1);
    	String secretAnswer = request.getParameter(User.COL_SQANSWER);
    	String userType = request.getParameter(User.COL_USERTYPE);
    	
    	// create user object
    	User user = new User();
    	user.setIdnumber(idNumber);
    	user.setFirstName(firstname);
    	user.setMiddleInitial(middleInitial);
    	user.setLastName(lastname);
    	user.setEmail(emailAddress);
    	user.setPassword(password);
    	user.setBirthdate(Utils.convertStringToDate((bday)));
    	user.setSecretQuestion(secretQuestion);
    	user.setSecretAnswer(secretAnswer);
    	user.setUserType(UserType.getValue(userType));
    	
    	// add to db
    	boolean result = UserService.registerLibStaffLibMngr(user);
    	
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
