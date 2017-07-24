package model;

public enum UserStatus {
	
	PENDING, ACTIVATED, DEACTIVATED;

	public static UserStatus getValue(String userStatus) {
		switch(userStatus) {
		case "PENDING": return PENDING;
		case "ACTIVATED": return ACTIVATED;
		case "DEACTIVATED": return DEACTIVATED;
		default: return null;
		}
	}
}
