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
public class AdminDisplayAccountsServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminDisplayAccountsServlet";
	
	
    public AdminDisplayAccountsServlet() { super();}


    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN DISPLAY ACCOUNTS GET");
   
    	PrintWriter pw = response.getWriter();
    	ArrayList<User> users = UserService.getLockedAccounts();
    	
    	System.out.println("[RESULTS] : ");
    	for(int i = 0; i < users.size(); i++){
    		System.out.println(i + " : " + users.get(i).getIdnumber());
    	}
    	Gson gson = null;
		gson = new Gson();
		String results = gson.toJson(users);
		System.out.println(results);
		pw.write(results);
    	
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN ACCOUNTS POST");
    	
    	
   	}
   
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
 		if(type == MasterServlet.TYPE_GET)
 			doGet(request, response);
 		doPost(request, response);
 	}

}
