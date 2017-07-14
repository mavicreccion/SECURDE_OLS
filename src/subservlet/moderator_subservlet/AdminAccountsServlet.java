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

//@WebServlet("/RegisterUserServlet")
public class AdminAccountsServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminAccountsServlet";
	
	
    public AdminAccountsServlet() { super();}


    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN ACCOUNTS GET");
    	
    	
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN ACCOUNTS POST");
    	request.getRequestDispatcher("/admin-accounts.jsp").forward(request, response);
    	
    	
   	}
   
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
 		if(type == MasterServlet.TYPE_GET)
 			doGet(request, response);
 		doPost(request, response);
 	}

}
