package model;

import java.util.Date;

public class ReservedRoom {
	
	public static final String TABLE_NAME = "reserved_mr";
	public static final String COL_RESERVEDMRID = "reserved_mr";
	public static final String COL_MRID = "reserved_mr";
	public static final String COL_IDNUMBER = "reserved_mr";
	public static final String COL_DATERESERVED = "reserved_mr";
	public static final String COL_TIMESTART = "reserved_mr";
	public static final String COL_TIMEEND = "reserved_mr";
	
	private int reservedMRID;
	private int MRID;
	private String idnumber;
	private Date reservedDate;
	private int timeStart;
	private int timeEnd;
	
	private User user;
	
	public ReservedRoom() {
		
	}

	public int getReservedMRID() {
		return reservedMRID;
	}

	public void setReservedMRID(int reservedMRID) {
		this.reservedMRID = reservedMRID;
	}

	public int getMRID() {
		return MRID;
	}

	public void setMRID(int mRID) {
		MRID = mRID;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public Date getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(Date reservedDate) {
		this.reservedDate = reservedDate;
	}

	public int getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}

	public int getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(int timeEnd) {
		this.timeEnd = timeEnd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
