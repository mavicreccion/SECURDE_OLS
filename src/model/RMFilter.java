package model;

public enum RMFilter {
	
	KEYWORDS, AUTHOR, TITLE, PUBLISHER;
	public static final String ENUM_RMFilter = "RMFilter";
	public static RMFilter getValue(String rmFilter) {
		switch(rmFilter) {
		case "KEYWORDS": return KEYWORDS;
		case "AUTHOR": return AUTHOR;
		case "TITLE": return TITLE;
		case "PUBLISHER": return PUBLISHER;
		default: return null;
		}
	}

}
