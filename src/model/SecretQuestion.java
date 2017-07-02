package model;

public class SecretQuestion {
	
	public static final String TABLE_NAME = "secret_questions";
	public static final String COL_SQID = "sqID";
	public static final String COL_QUESTION = "question";
	
	private int SQID;
	private String question;
	
	public SecretQuestion() {
		
	}
	
	public int getSQID() {
		return SQID;
	}
	
	public void setSQID(int SQID) {
		this.SQID = SQID;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	

}
