package model;

public enum RMType {
	
	ALL, BOOK, THESIS, MAGAZINE;
	
	public static final String ENUM_RMType = "RMType";
	
	public static RMType getValue(String rmType) {
		switch(rmType) {
		case "BOOK": return BOOK;
		case "THESIS": return THESIS;
		case "MAGAZINE": return MAGAZINE;
		case "ALL": return ALL;
		default: return null;
		}
	}

}
