package subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.MasterServlet;

/**
 * Servlet implementation class MeetingRoomPage
 */

public class ServicesPageServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL = "/ServicesPageServlet";
 
    public ServicesPageServlet() {super();    }


    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SERVICES PAGE GET");
		
	}


	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SERVICES PAGE POST");
		//request.getRequestDispatcher("meeting-rooms.jsp").forward(request, response);
	}

	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}
