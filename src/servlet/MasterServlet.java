package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import subservlet.AccountPageServlet;
import subservlet.CategoryRMServlet;
import subservlet.HomePageServlet;
import subservlet.MeetingRoomPageServlet;
import subservlet.RMDetailsServlet;
import subservlet.RMSearchResultsPageServlet;
import subservlet.RegisterUserServlet;
import subservlet.RegistrationPageServlet;
import subservlet.ReserveRMServlet;
import subservlet.ReviewServlet;
import subservlet.SignInServlet;
import subservlet.SignInSignUpPageServlet;
import subservlet.SignUpServlet;
import subservlet.moderator_subservlet.AddRMServlet;
import subservlet.moderator_subservlet.AdminAccountsServlet;
import subservlet.moderator_subservlet.AdminAreaServlet;
import subservlet.moderator_subservlet.AdminDisplayAccountsServlet;
import subservlet.moderator_subservlet.AdminRMSearchResultsPageServlet;
import subservlet.moderator_subservlet.DeleteRMServlet;
import subservlet.moderator_subservlet.EditRMServlet;
import subservlet.moderator_subservlet.ExportServlet;
import subservlet.moderator_subservlet.OverrideReservationRMServlet;
import subservlet.moderator_subservlet.OverrideReservationRoomServlet;
import subservlet.moderator_subservlet.RegisterModeratorServlet;
import subservlet.moderator_subservlet.ViewReservedRMServlet;
import subservlet.moderator_subservlet.AdminReservationsServlet;

/**
 * Servlet implementation class MasterServlet
 */
@WebServlet(urlPatterns = { AccountPageServlet.URL,
							HomePageServlet.URL,
							MeetingRoomPageServlet.URL,
							RegistrationPageServlet.URL,
							RegisterUserServlet.URL,
							SignInServlet.URL,
							SignInSignUpPageServlet.URL,
							SignUpServlet.URL,
							ReserveRMServlet.URL,
							RMSearchResultsPageServlet.URL,
							AdminAreaServlet.URL,
							AddRMServlet.URL,
							AdminRMSearchResultsPageServlet.URL,
							AdminReservationsServlet.URL,
							AdminAccountsServlet.URL,
							AdminDisplayAccountsServlet.URL,
							ReviewServlet.URL,
							RMDetailsServlet.URL,
							RegisterModeratorServlet.URL,
							CategoryRMServlet.URL,
							ViewReservedRMServlet.URL,
							DeleteRMServlet.URL,
							EditRMServlet.URL,
							OverrideReservationRMServlet.URL,
							OverrideReservationRoomServlet.URL,
							ExportServlet.URL
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
				case MeetingRoomPageServlet.URL:
					MeetingRoomPageServlet.process(request, response, type);
					break;
				case RMSearchResultsPageServlet.URL:
					RMSearchResultsPageServlet.process(request, response, type);
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
				case SignUpServlet.URL:
					SignUpServlet.process(request, response, type);
					break;
				case SignInSignUpPageServlet.URL:
					SignInSignUpPageServlet.process(request, response, type);
					break;
				case AddRMServlet.URL:
					AddRMServlet.process(request, response, type);
					break;
				case ReserveRMServlet.URL:
					ReserveRMServlet.process(request, response, type);
					break;
				case AdminAreaServlet.URL:
					AdminAreaServlet.process(request, response, type);
					break;
				case AdminRMSearchResultsPageServlet.URL:
					AdminRMSearchResultsPageServlet.process(request, response, type);
					break;
				case AdminReservationsServlet.URL:
					AdminReservationsServlet.process(request, response, type);
					break;
				case AdminAccountsServlet.URL:
					AdminAccountsServlet.process(request, response, type);
					break;
				case AdminDisplayAccountsServlet.URL:
					AdminDisplayAccountsServlet.process(request, response, type);
					break;
				case ReviewServlet.URL:
					ReviewServlet.process(request, response, type);
					break;
				case RMDetailsServlet.URL:
					RMDetailsServlet.process(request, response, type);
					break;
				case RegisterModeratorServlet.URL:
					RegisterModeratorServlet.process(request, response, type);
					break;
				case CategoryRMServlet.URL:
					CategoryRMServlet.process(request, response, type);
					break;
				case ViewReservedRMServlet.URL:
					ViewReservedRMServlet.process(request, response, type);
					break;
				case DeleteRMServlet.URL:
					DeleteRMServlet.process(request, response, type);
					break;
				case EditRMServlet.URL:
					EditRMServlet.process(request, response, type);
					break;
				case OverrideReservationRMServlet.URL:
					OverrideReservationRMServlet.process(request, response, type);
					break;
				case OverrideReservationRoomServlet.URL:
					OverrideReservationRoomServlet.process(request, response, type);
					break;
				case ExportServlet.URL:
					ExportServlet.process(request, response, type);
					break;
			}
			
		} catch (ServletException | IOException e) {
			// TODO: handle exception
		}
	}

}
