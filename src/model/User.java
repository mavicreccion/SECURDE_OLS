package model;

import java.util.Date;

public class User {
	
	public static final String TABLE_USER = "user";
	public static final String TABLE_SECRETQUESTION = "secret_questions";
	
	public static final String COL_USERID = "userID";
	public static final String COL_USERTYPE = "user_type";
	public static final String COL_FIRSTNAME = "first_name";
	public static final String COL_MI = "middle_initial";
	public static final String COL_LASTNAME = "last_name";
	public static final String COL_USERNAME = "username";
	public static final String COL_PASSWORD = "password";
	public static final String COL_EMAIL = "email_address";
	public static final String COL_BDAY = "birthday";
	public static final String COL_SQID = "sqID";
	public static final String COL_SQANSWER = "sq_answer";
	
	public static final String COL_QUESTION = "question";
	
	protected String userID;
	protected String firstName;
	protected String middleInitial;
	protected String lastName;
	protected String username;
	protected String password;
	protected String email;
	protected Date birthdate;
	protected String secretQuestion;
	protected String secretAnswer;
	protected UserType userType;
	
	public User() {
		
	}

	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleInitial() {
		return middleInitial;
	}
	
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getSecretQuestion() {
		return secretQuestion;
	}
	
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	
	public String getSecretAnswer() {
		return secretAnswer;
	}
	
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	

}
