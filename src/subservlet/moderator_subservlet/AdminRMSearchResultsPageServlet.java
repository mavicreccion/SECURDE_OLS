package subservlet.moderator_subservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.RMFilter;
import model.RMType;
import model.ReadingMaterial;
import model.SecretQuestion;
import model.User;
import service.ReadingMaterialService;
import service.SecretQuestionService;
import servlet.MasterServlet;

/**
 * Servlet implementation class AccountPageServlet
 */
//@WebServlet("/AccountPageServlet")
public class AdminRMSearchResultsPageServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminRMSearchResultsPageServlet";
       
    public AdminRMSearchResultsPageServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("RM SEARCH RESULTS PAGE GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("RM SEARCH RESULTS PAGE POST");
    	PrintWriter pw = response.getWriter();
    	String searchString = (String) request.getParameter("stringToSearch");
    	RMFilter rmFilter = RMFilter.getValue((String) request.getParameter(RMFilter.ENUM_RMFilter));
    	RMType rmType = RMType.getValue((String) request.getParameter(RMType.ENUM_RMType));
    	System.out.println(rmType);
    	ArrayList<ReadingMaterial> readingMaterials = ReadingMaterialService.searchRM(rmFilter, rmType, searchString);
    	
    	System.out.println("[SEARCH] : " + searchString);
    	System.out.println("[RESULTS] : ");
    	for(int i = 0; i < readingMaterials.size(); i++){
    		System.out.println(i + " : " + readingMaterials.get(i).getTitle());
    	}
    	
    	//request.getSession().setAttribute("numOfRM", readingMaterials.size());
    	//request.getSession().setAttribute(ReadingMaterial.TABLE_RM, readingMaterials);
    	
    	Gson gson = null;
		gson = new Gson();
		String results = gson.toJson(readingMaterials);
		System.out.println(results);
		pw.write(results);
		
		//pw.write(String.valueOf(readingMaterials.size()));
    	
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}
    
    
    
    
    

}
