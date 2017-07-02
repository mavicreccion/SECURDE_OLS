package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Query;
import model.SecretQuestion;
import model.User;

public class SecretQuestionService {
	
	public static ArrayList<SecretQuestion> getAllSecretQuestions() {
		ArrayList<SecretQuestion> sqList = new ArrayList<>();
		SecretQuestion sq;
		
		String query = "\nSELECT * FROM " + User.TABLE_SECRETQUESTION;
		
		Query q = Query.getInstance();
		
		try {
			ResultSet r = q.runQuery(query);
			
			while(r.next()) {
				sq = new SecretQuestion();
				sq.setSQID(r.getInt(User.COL_SQID));
				sq.setQuestion(r.getString(User.COL_QUESTION));
				
				sqList.add(sq);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return sqList;
		
	}
	
	public static SecretQuestion getSecretQuestion(int idnumber) {
		SecretQuestion sq = null;
		
		String query = "\nSELECT " + User.TABLE_USER + "." + User.COL_SQID + ", " + User.COL_QUESTION + " \n"
				+ " FROM " + User.TABLE_USER + " NATURAL JOIN " + User.TABLE_SECRETQUESTION 
				+ " WHERE " + User.COL_IDNUMBER + " = ?;";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(idnumber);
		
		Query q = Query.getInstance();
		
		try {
			ResultSet r = q.runQuery(query, input);
			
			if(r.next()) {
				sq = new SecretQuestion();
				sq.setSQID(r.getInt(User.COL_SQID));
				sq.setQuestion(r.getString(User.COL_QUESTION));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return sq;
	}

}
