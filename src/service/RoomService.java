package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import db.Query;
import model.ReservedRoom;
import model.User;
import utils.Utils;

public class RoomService {
	
	// reserve
	public static boolean reserveRoom(ReservedRoom reserved_room) {
		boolean result = false;
		
		String query = "\nINSERT INTO " + ReservedRoom.TABLE_NAME + " ( "
				+ ReservedRoom.COL_MRID + ", "
				+ ReservedRoom.COL_IDNUMBER + ", "
				+ ReservedRoom.COL_DATERESERVED + ", "
				+ ReservedRoom.COL_TIMESTART + ", "
				+ ReservedRoom.COL_TIMEEND + " )\n "
				+ " VALUES (?, ?, ?, ?, ?);";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(reserved_room.getMRID());
		input.add(reserved_room.getIdnumber());
		input.add(Utils.convertDateJavaToStringDB(reserved_room.getReservedDate()));
		input.add(reserved_room.getTimeStart());
		input.add(reserved_room.getTimeEnd());
		
		Query q = Query.getInstance();
		
		try {
			result = q.runInsertUpdateDelete(query, input);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// cancel
	public static boolean cancelReserveMR(int reservedMRID) {
		boolean result = false;
		
		String query = "\nDELETE FROM " + ReservedRoom.TABLE_NAME
				+ " WHERE " + ReservedRoom.COL_RESERVEDMRID + " = ?;";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(reservedMRID);
		
		Query q = Query.getInstance();
		
		try {
			result = q.runInsertUpdateDelete(query, input);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// get all reservations of user
	public static ArrayList<ReservedRoom> getReservationsOfUser(String idNumber) {
		ArrayList<ReservedRoom> rmList = new ArrayList<>();
		ReservedRoom rm = null;
		
		String query = "\nSELECT * FROM " + ReservedRoom.TABLE_NAME
				+ " WHERE " + ReservedRoom.COL_IDNUMBER + " = ?;";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(idNumber);
		
		Query q = Query.getInstance();
		try {
			ResultSet r = q.runQuery(query, input);
			
			while(r.next()) {
				rm = new ReservedRoom();
				rm.setReservedMRID(r.getInt(ReservedRoom.COL_RESERVEDMRID));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return rmList;
	}

	// get number of hours of current reservation of user
	public static int getReservedMinutesOfThisDay(String id_number, Date date) {
		int minutes = 0;
		
		String query = "\nSELECT COUNT(*) "
				+ " FROM " + ReservedRoom.TABLE_NAME
				+ " WHERE " + User.COL_IDNUMBER + " = ? "
				+ " AND " + ReservedRoom.COL_DATERESERVED + " = DATE(?);";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(id_number);
		input.add(Utils.convertDateJavaToStringDB(date));
		
		Query q = Query.getInstance();
		
		try {
			ResultSet r = q.runQuery(query, input);
			
			if(r.next()) {
				minutes = r.getInt(1) * 30;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return minutes;
	}
	
	// get reserved rooms at this time and day (USER)
	public static ArrayList<ReservedRoom> getReservedRoomsAtThisDateUSER(Date date) {
		ArrayList<ReservedRoom> rmList = new ArrayList<>();
		ReservedRoom rm = null;
		
		String query = "\nSELECT " + ReservedRoom.COL_MRID + ", "
				+ ReservedRoom.COL_TIMESTART + ", "
				+ ReservedRoom.COL_TIMEEND + "\n"
				+ " FROM " + ReservedRoom.TABLE_NAME
				+" WHERE " + ReservedRoom.COL_DATERESERVED + " = DATE(?)"; 
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(Utils.convertDateJavaToStringDB(date));
		
		Query q = Query.getInstance();
		
		try {
			ResultSet r = q.runQuery(query, input);
			
			while(r.next()) {
				rm = new ReservedRoom();
				rm.setMRID(r.getInt(ReservedRoom.COL_MRID));
				rm.setTimeStart(r.getInt(ReservedRoom.COL_TIMESTART));
				rm.setTimeEnd(r.getInt(ReservedRoom.COL_TIMEEND));
				
				rmList.add(rm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rmList;
	}
	
	// get reserved rooms at this time and day (LIBSTAFF/LIBMNGR)
	public static ArrayList<ReservedRoom> getReservedRoomsAtThisDateADMIN(Date date) {
		ArrayList<ReservedRoom> rmList = new ArrayList<>();
		ReservedRoom rm = null;
		User user = null;
		
		// firstname lastname idnumber
		// timestart timeend date
		// roomid
		// reservation id
		
		String query = "\nSELECT " 
				+ ReservedRoom.COL_RESERVEDMRID
				+ ReservedRoom.COL_MRID + ", "
				+ ReservedRoom.COL_TIMESTART + ", "
				+ ReservedRoom.COL_TIMEEND + ", "
				+ User.COL_IDNUMBER + ", "
				+ User.COL_FIRSTNAME + ", "
				+ User.COL_LASTNAME + ", "
				+ " FROM " + ReservedRoom.TABLE_NAME + " NATURAL JOIN " + User.TABLE_USER
				+ " WHERE " + ReservedRoom.COL_DATERESERVED + " = DATE(?)"; 
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(Utils.convertDateJavaToStringDB(date));
		
		Query q = Query.getInstance();
		
		try {
			ResultSet r = q.runQuery(query, input);
			
			while(r.next()) {
				rm = new ReservedRoom();
				rm.setReservedMRID(r.getInt(ReservedRoom.COL_RESERVEDMRID));
				rm.setMRID(r.getInt(ReservedRoom.COL_MRID));
				rm.setTimeStart(r.getInt(ReservedRoom.COL_TIMESTART));
				rm.setTimeEnd(r.getInt(ReservedRoom.COL_TIMEEND));
				
				user = new User();
				user.setIDNumber(r.getString(User.COL_IDNUMBER));
				user.setFirstName(r.getString(User.COL_FIRSTNAME));
				user.setLastName(r.getString(User.COL_LASTNAME));
				
				rmList.add(rm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rmList;
	}
	
}
