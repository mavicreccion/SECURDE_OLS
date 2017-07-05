package subservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.SecretQuestion;
import model.User;
import model.datetime.SimpleDate;
import service.SecretQuestionService;
import servlet.MasterServlet;

//@WebServlet("/RegistrationPage")
public class RegistrationPageServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL = "/RegistrationPageServlet";
	
    public RegistrationPageServlet() {}

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("REGISTRATION PAGE GET");
    	
    	//load secret questions
    	
    	ArrayList<SecretQuestion> secretQuestions = SecretQuestionService.getAllSecretQuestions();
    	ArrayList<String> questions = new ArrayList<String>();
    	for(int i = 0; i < secretQuestions.size(); i++ )
    		questions.add(secretQuestions.get(i).getQuestion());
    	request.getSession().setAttribute(SecretQuestion.COL_QUESTION, questions);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
    	PrintWriter pw = response.getWriter();
		Gson gson = null;
		gson = new Gson();
		String secretQ = gson.toJson(questions);
		System.out.println("S.Q. : " + questions);
		String allJson = "[ " 
			+ secretQ + "]";
				
		pw.write(allJson);
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("REGISTRATION PAGE POST");
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
