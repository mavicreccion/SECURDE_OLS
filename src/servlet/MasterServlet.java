package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import subservlet.*;

/**
 * Servlet implementation class MasterServlet
 */
@WebServlet(urlPatterns = { AccountPageServlet.URL,
							HomePageServlet.URL,
							LoadBookTitleServlet.URL,
							MeetingRoomPageServlet.URL,
							RegistrationPageServlet.URL,
							RegisterUserServlet.URL,
							SignInServlet.URL,
							SignUpServlet.URL,
							RMDetailsServlet.URL,
							ReserveRMServlet.URL,
							RMSearchResultsPageServlet.URL
							})

public class MasterServlet extends HttpServlet {
	
	
	public static int TYPE_GET = 0;
	public static int TYPE_POST = 1;

	private static final long serialVersionUID = 2764423969467803721L;
	
    public MasterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response, TYPE_GET);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response, TYPE_POST);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response, int type)
	{
		System.out.println(request.getServletPath());
		try {
			switch(request.getServletPath())
			{
				case AccountPageServlet.URL:
					AccountPageServlet.process(request, response, type);
					break;
				case HomePageServlet.URL:
					HomePageServlet.process(request, response, type);
					break;
				case LoadBookTitleServlet.URL:
					LoadBookTitleServlet.process(request, response, type);
					break;
				case MeetingRoomPageServlet.URL:
					MeetingRoomPageServlet.process(request, response, type);
					break;
				case RMSearchResultsPageServlet.URL:
					RMSearchResultsPageServlet.process(request, response, type);
					break;
				case ReserveRMServlet.URL:
					ReserveRMServlet.process(request, response, type);
					break;
				case RegistrationPageServlet.URL:
					RegistrationPageServlet.process(request, response, type);
					break;
				case RegisterUserServlet.URL:
					RegisterUserServlet.process(request, response, type);
					break;
				case SignInServlet.URL:
					SignInServlet.process(request, response, type);
					break;
				case RMDetailsServlet.URL:
					RMDetailsServlet.process(request, response, type);
					break;
				case SignUpServlet.URL:
					SignUpServlet.process(request, response, type);
					break;
			}
			
		} catch (ServletException | IOException e) {
			// TODO: handle exception
		}
	}

}
