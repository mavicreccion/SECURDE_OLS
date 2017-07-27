package subservlet.moderator_subservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import servlet.MasterServlet;

import com.google.gson.Gson;

//@WebServlet("/RegisterUserServlet")
public class AdminUnlockAccountServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminUnlockAccountServlet";
	
	
    public AdminUnlockAccountServlet() { super();}


    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN DISPLAY ACCOUNTS GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN ACCOUNTS POST");
    	
    	String idnumber = request.getParameter(User.COL_IDNUMBER);
    	boolean result = UserService.unlockAccount(idnumber);
    	System.out.println("Unlocking Account Success... " + result);
    	PrintWriter pw = response.getWriter();
    	pw.write(result + "");
   	}
   
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
 		if(type == MasterServlet.TYPE_GET)
 			doGet(request, response);
 		doPost(request, response);
 	}

}
