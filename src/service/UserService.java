package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Query;
import model.User;
import model.UserStatus;
import model.UserType;
import utils.Utils;

public class UserService {
	
	// register user
	public static boolean registerUser(User user) {
		boolean result = false;
		
		String query = "\nINSERT INTO " + User.TABLE_USER + "\n"
				+ " VALUES (?, ?, ?, ?, ?, SHA2(?, 512), ?, ?, ?, ?, ?);";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(user.getIdnumber());
		input.add(user.getUserType());
		input.add(user.getFirstName());
		input.add(user.getMiddleInitial());
		input.add(user.getLastName());
		input.add(user.getPassword());
		input.add(user.getEmail());
		input.add(Utils.convertDateJavaToStringDB(user.getBirthdate()));
		input.add(user.getSecretQuestion().getSQID());
		input.add(user.getSecretAnswer());
		input.add(UserStatus.ACTIVATED+"");
		
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
	
	// register libstaff/libmngr
	public static boolean registerLibStaffLibMngr(User user) {
		boolean result = false;
		
		String query = "\nINSERT INTO " + User.TABLE_USER + "\n"
				+ " VALUES (?, ?, ?, ?, ?, SHA2(?, 512), ?, ?, ?, ?, ?);";
		
		String query_event = "\nCREATE EVENT activate_event_" + user.getIdnumber() +"\n"
				+ " ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 MINUTE \n" 
				+ " DO \n"
				+ "\tUPDATE " + User.TABLE_USER + "\n"
				+ "\tSET status = ?\n"
				+ "\tWHERE id_number = ? AND status = ?;";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(user.getIdnumber());
		input.add(user.getUserType());
		input.add(user.getFirstName());
		input.add(user.getMiddleInitial());
		input.add(user.getLastName());
		input.add(user.getPassword());
		input.add(user.getEmail());
		input.add(Utils.convertDateJavaToStringDB(user.getBirthdate()));
		input.add(user.getSecretQuestion().getSQID());
		input.add(user.getSecretAnswer());
		input.add(UserStatus.PENDING+"");
		
		Query q = Query.getInstance();
		
		try {
			result = q.runInsertUpdateDelete(query, input);
			
			input.clear();
			input.add(UserStatus.DEACTIVATED + "");
			input.add(user.getIdnumber());
			input.add(UserStatus.PENDING + "");
			
			result = q.runSQLEvent(query_event, input);
			
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
	
	// login
	public static User loginUser(String id_number, String password) {
		User user = null;
		
		String query = "\nSELECT " 
				+ User.COL_IDNUMBER + ", "
				+ User.COL_FIRSTNAME + ", "
				+ User.COL_LASTNAME + "\n"
				+ " FROM " + User.TABLE_USER + "\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ?"
				+ " AND " + User.COL_PASSWORD + " = SHA2(?, 512);";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(id_number);
		input.add(password);
		
		Query q = Query.getInstance();
		
		try {
			ResultSet r = q.runQuery(query, input);
			
			// login is successful
			if(r.next()) {
				user = new User();
				user.setIdnumber(id_number);
				user.setFirstName(r.getString(User.COL_FIRSTNAME));
				user.setLastName(r.getString(User.COL_LASTNAME));
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
		
		return user;
	}
	
	// view profile
	public static User viewProfileUser(String id_number) {
		User user = null;
		
		String query = "\nSELECT * FROM " + User.TABLE_USER
				+ " WHERE " + User.COL_IDNUMBER + " = ?";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(id_number);
		
		Query q = Query.getInstance();
		
		try {
			ResultSet r = q.runQuery(query, input);
			
			if(r.next()) {				
				user = new User();
				
				// id number
				user.setIdnumber(r.getString(User.COL_IDNUMBER));
				
				// user type
				user.setUserType(UserType.getValue(r.getString(User.COL_USERTYPE)));
				
				// name
				user.setFirstName(r.getString(User.COL_FIRSTNAME));
				user.setMiddleInitial(r.getString(User.COL_MI));
				user.setLastName(r.getString(User.COL_LASTNAME));
				
				// email
				user.setEmail(r.getString(User.COL_EMAIL));
				
				// birthday
				user.setBirthdate(r.getDate(User.COL_BDAY));
				
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
		
		return user;
	}
	
	// get user type
	public static UserType getUserType(String id_number) {
		UserType userType = null;
		
		String query =  "\nSELECT " + User.COL_USERTYPE
				+ " FROM " + User.TABLE_USER + "\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ?;";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(id_number);
		
		Query q = Query.getInstance();
		
		try {
			ResultSet r = q.runQuery(query, input);
			
			if(r.next()) {
				userType = UserType.getValue(r.getString(User.COL_USERTYPE));
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
		
		return userType;
	}
	
	// change password of libstaff/libmngr to activate account
	public static boolean changePasswordToActivateAccount(User user) {
		boolean result = false;
		
		String query = "\nUPDATE " + User.TABLE_USER
				+ " SET "
				+ User.COL_PASSWORD + " = SHA2(?, 512),"
				+ User.COL_STATUS + "  = ?\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ?";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(user.getPassword());
		input.add(UserStatus.ACTIVATED);
		input.add(user.getIdnumber());
		
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

	// get all locked accounts
	public static ArrayList<User> getLockedAccounts() {
		ArrayList<User> lockedAccounts = new ArrayList<>();
		User user = null;
		
		
		String query = "\nSELECT * FROM " + User.TABLE_USER
				+ " WHERE " + User.COL_STATUS + " = ?";
		
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(UserStatus.DEACTIVATED + "");
		
		Query q = Query.getInstance();
		ResultSet r = null;
		
		try {
			r = q.runQuery(query, input);
			//r = q.runQuery(query);
			
			while(r.next()) {
				user = new User();
				user.setIdnumber(r.getString(User.COL_IDNUMBER));
				user.setFirstName(r.getString(User.COL_FIRSTNAME));
				user.setMiddleInitial(r.getString(User.COL_MI));
				user.setLastName(r.getString(User.COL_LASTNAME));
				user.setUserType(UserType.getValue(r.getString(User.COL_USERTYPE)));
				user.setStatus(UserStatus.getValue(r.getString(User.COL_STATUS)));
				lockedAccounts.add(user);
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
		
		return lockedAccounts;
	}
	
	// unlock account
	public static boolean unlockAccount(String idnumber) {
		boolean result = false;
		
		String query = "\nUPDATE " + User.TABLE_USER
				+ " SET "
				+ User.COL_STATUS + "  = ?\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ?";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(UserStatus.ACTIVATED + "");
		input.add(idnumber);
		
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
}