package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Query;
import model.SecretQuestion;
import model.User;

public class SecretQuestionService {
	
	// get all
	public static ArrayList<SecretQuestion> getAllSecretQuestions() {
		ArrayList<SecretQuestion> sqList = new ArrayList<>();
		SecretQuestion sq;
		
		String query = "\nSELECT * FROM " + SecretQuestion.TABLE_NAME;
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query);
			
			while(r.next()) {
				sq = new SecretQuestion();
				sq.setSQID(r.getInt(User.COL_SQID));
				sq.setQuestion(r.getString(SecretQuestion.COL_QUESTION));
				
				sqList.add(sq);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return sqList;
		
	}
	
	// get sq of user
	public static SecretQuestion getSecretQuestionOfUser(int idnumber) {
		SecretQuestion sq = null;
		
		String query = "\nSELECT " + User.TABLE_USER + "." + User.COL_SQID + ", " + SecretQuestion.COL_QUESTION + " \n"
				+ " FROM " + User.TABLE_USER + " NATURAL JOIN " + SecretQuestion.TABLE_NAME 
				+ " WHERE " + User.COL_IDNUMBER + " = ?;";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(idnumber);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			if(r.next()) {
				sq = new SecretQuestion();
				sq.setSQID(r.getInt(User.COL_SQID));
				sq.setQuestion(r.getString(SecretQuestion.COL_QUESTION));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return sq;
	}

	// get sq by id
	public static SecretQuestion getSecretQuestionByID(int sqID) {
		SecretQuestion sq = null;
		
		String query = "\nSELECT * FROM " + SecretQuestion.TABLE_NAME;
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(sqID);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			if(r.next()) {
				sq = new SecretQuestion();
				sq.setSQID(r.getInt(User.COL_SQID));
				sq.setQuestion(r.getString(SecretQuestion.COL_QUESTION));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return sq;
	}
}
