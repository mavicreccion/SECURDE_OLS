package model;

public enum RMStatus {

	BORROWED, RESERVED, AVAILABLE, INSTOCK, OUTSTOCK;
	
	public static RMStatus getStockValue(String rmStatus) {
		switch(rmStatus) {
		case "INSTOCK" : return INSTOCK;
		case "OUTSTOCK": return OUTSTOCK;
		default: return null;
		}
	}
	
}
