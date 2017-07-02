package model;

public enum UserType {
	
	STUDENT, FACULTY, ADMIN, LIBSTAFF, LIBMNGR;
	
	public static UserType getValue(String userType) {
		switch(userType) {
		case "STUDENT": return STUDENT;
		case "FACULTY": return FACULTY;
		case "ADMIN": return ADMIN;
		case "LIBSTAFF": return LIBSTAFF;
		case "LIBMNGR": return LIBMNGR;
		default: return null;
		}
	}

}
