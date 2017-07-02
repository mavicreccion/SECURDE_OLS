package model;

public class Room {
	
	public static final String TABLE_NAME = "meeting_room";
	public static final String COL_MRID = "mrID";
	public static final String COL_MRNAME = "mr_name";
	
	private int mrID;
	private String mr_name;
	
	public Room() {
		
	}

	public int getMrID() {
		return mrID;
	}

	public void setMrID(int mrID) {
		this.mrID = mrID;
	}

	public String getMr_name() {
		return mr_name;
	}

	public void setMr_name(String mr_name) {
		this.mr_name = mr_name;
	}
	
	

}
