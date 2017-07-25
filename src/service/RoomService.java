package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import db.Query;
import model.ReservedRoom;
import model.Room;
import model.RoomStatus;
import model.User;
import model.UserType;
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
		input.add(reserved_room.getMrID());
		input.add(reserved_room.getUser().getIDNumber());
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
	
	// get ALL rooms
	public static ArrayList<Room> getALLRooms() {
		ArrayList<Room> roomList = new ArrayList<>();
		Room room = null;
		
		String query = "\nSELECT * FROM " + Room.TABLE_NAME;
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query);
			
			while(r.next()) {
				room = new Room();
				room.setMrID(r.getInt(Room.COL_MRID));
				room.setMr_name(r.getString(Room.COL_MRNAME));
				
				System.out.println("Room: " + room.getMr_name());
				
				roomList.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return roomList;
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
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				rm = new ReservedRoom();
				rm.setReservedMRID(r.getInt(ReservedRoom.COL_RESERVEDMRID));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return rmList;
	}
	
	// check if user can still reserve a meeting room
	public static boolean checkIfUserCanStillReserve(User user, Date date) {
		boolean result = false;
		
		String query = "\nSELECT COUNT(*) "
				+ " FROM " + ReservedRoom.TABLE_NAME
				+ " WHERE " + User.COL_IDNUMBER + " = ? "
				+ " AND " + ReservedRoom.COL_DATERESERVED + " = DATE(?) "
				+ " GROUP BY " + User.COL_IDNUMBER
				+ " HAVING COUNT(*) < ?;";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(user.getIDNumber());
		input.add(Utils.convertDateJavaToStringDB(date));
		
		if(user.getUserType() == UserType.STUDENT) 
			input.add(4);
		else if(user.getUserType() == UserType.FACULTY)
			input.add(10);
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			result = r.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
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
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				rm = new ReservedRoom();
				rm.setMrID(r.getInt(ReservedRoom.COL_MRID));
				rm.setTimeStart(r.getInt(ReservedRoom.COL_TIMESTART));
				rm.setTimeEnd(r.getInt(ReservedRoom.COL_TIMEEND));
				
				rmList.add(rm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
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
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			
			while(r.next()) {
				rm = new ReservedRoom();
				rm.setReservedMRID(r.getInt(ReservedRoom.COL_RESERVEDMRID));
				rm.setMrID(r.getInt(ReservedRoom.COL_MRID));
				rm.setTimeStart(r.getInt(ReservedRoom.COL_TIMESTART));
				rm.setTimeEnd(r.getInt(ReservedRoom.COL_TIMEEND));
				
				user = new User();
				user.setIDNumber(r.getString(User.COL_IDNUMBER));
				user.setFirstName(r.getString(User.COL_FIRSTNAME));
				user.setLastName(r.getString(User.COL_LASTNAME));
				
				rm.setUser(user);
				
				rmList.add(rm);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rmList;
	}
	
	// get room number and status of particular time of all rooms
	public static ArrayList<Room> getDataForExport() {
		int[] timeSlots = Utils.getTimeSlots();
		ArrayList<Room> rooms = getALLRooms();
		int time_start, time_end;
		
		ArrayList<Room> all_rooms = new ArrayList<>();
		
		Room room = null;
		
		String query = "\nSELECT " + ReservedRoom.COL_RESERVEDMRID + "\n"
				+ " FROM " + ReservedRoom.TABLE_NAME + "\n"
				+ " WHERE " + Room.COL_MRID + " = ? "
				+ " AND " + ReservedRoom.COL_TIMESTART + " = ?"
				+ " AND " + ReservedRoom.COL_TIMEEND + " = ?"
				+ " AND " + ReservedRoom.COL_DATERESERVED + " = CURDATE();";
		
		ArrayList<Object> input = new ArrayList<>();
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			
			for (int j = 0; j < rooms.size(); j ++) {
				
				for(int i = 1; i < timeSlots.length; i ++) {
					
					room = new Room();
					room.setMrID(rooms.get(j).getMrID());
					room.setMr_name(rooms.get(j).getMr_name());
					
					time_start = timeSlots[i-1];
					time_end = timeSlots[i];
					
					input.clear();
					input.add(room.getMrID());
					input.add(time_start);
					input.add(time_end);
					
					r = q.runQuery(query, input);
					
					System.out.print(room.getMr_name() + " " + time_start + " - " + time_end + " ");
					
					if(r.next()) {
						// there's a reservation at this time
						room.setRoomStatus(RoomStatus.RESERVED);
						System.out.println(RoomStatus.RESERVED);
					} else {
						room.setRoomStatus(RoomStatus.AVAILABLE);
						System.out.println(RoomStatus.AVAILABLE);
					}
					
					all_rooms.add(room);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(r != null) {
					r.close();
				}
				
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return all_rooms;
	}
}
