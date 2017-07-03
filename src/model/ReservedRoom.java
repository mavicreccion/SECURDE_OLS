package model;

import java.util.Date;

public class ReservedRoom extends Room {
	
	public static final String TABLE_NAME = "reserved_mr";
	public static final String COL_RESERVEDMRID = "reservedMrID";
	public static final String COL_IDNUMBER = "id_number";
	public static final String COL_DATERESERVED = "date_reserved";
	public static final String COL_TIMESTART = "time_start";
	public static final String COL_TIMEEND = "time_end";
	
	private int reservedMRID;
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
