package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import db.Query;
import model.RMTag;
import model.ReadingMaterial;
import model.User;
import model.UserType;
import utils.Utils;

public class ReadingMaterialService {

	// add
	public static boolean addRM(ReadingMaterial myRM) {
		boolean result = false;

		String query = "INSERT INTO " + ReadingMaterial.TABLE_RM + " ( "
				+ ReadingMaterial.COL_RMTYPE + ", "
				+ ReadingMaterial.COL_TITLE + ", " 
				+ ReadingMaterial.COL_AUTHOR + ", " 
				+ ReadingMaterial.COL_PUBLISHER + ", " 
				+ ReadingMaterial.COL_YEAR + ", " 
				+ ReadingMaterial.COL_LOCATION + ", " 
				+ ReadingMaterial.COL_DATEARRIVED + ")\n "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(myRM.getRMType());
		input.add(myRM.getTitle());
		input.add(myRM.getAuthor());
		input.add(myRM.getPublisher());
		input.add(myRM.getYear());
		input.add(myRM.getLocation());
		input.add(myRM.getDateArrived());

		Query q = Query.getInstance();

		try {
			result = q.runInsertUpdateDelete(query, input);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// for every reading material, record its tags
		int rmID;
		try {
			rmID = q.getGeneratedKey();

			if(rmID != 0) {
				ArrayList<RMTag> tags = myRM.getTags();
				for (RMTag rmTag : tags) {
					query = "INSERT INTO " + ReadingMaterial.TABLE_RMTAG + " ( "
							+ ReadingMaterial.COL_TAGID + ", "
							+ ReadingMaterial.COL_RMID +")\n"
							+ " VALUES (?, ?);";

					input.clear();
					input.add(rmTag.getTagID());
					input.add(rmID);

					result = q.runInsertUpdateDelete(query, input);
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// delete


	// edit reading material details
	public static boolean editRM(ReadingMaterial myRM) {
		boolean result = false;

		String query = "UPDATE " + ReadingMaterial.TABLE_RM + " \n "
				+ ReadingMaterial.COL_RMTYPE + " = ?, "
				+ ReadingMaterial.COL_TITLE + " = ?, " 
				+ ReadingMaterial.COL_AUTHOR + " = ?, " 
				+ ReadingMaterial.COL_PUBLISHER + " = ?, " 
				+ ReadingMaterial.COL_YEAR + " = ?, " 
				+ ReadingMaterial.COL_LOCATION + " = ?)\n "
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(myRM.getRMType());
		input.add(myRM.getTitle());
		input.add(myRM.getAuthor());
		input.add(myRM.getPublisher());
		input.add(myRM.getYear());
		input.add(myRM.getLocation());
		input.add(myRM.getRMID());

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

	// edit reading material tags
	public static boolean editRMTags(int rmID, ArrayList<RMTag> tags) {
		boolean result = false;

		Query q = Query.getInstance();

		String query;
		ArrayList<Object> input = new ArrayList<>();

		try {
			// delete current tags
			query = "DELETE FROM " + ReadingMaterial.TABLE_RMTAG
					+ " WHERE " + ReadingMaterial.COL_RMID + " = ?";

			input.add(rmID);

			result = q.runInsertUpdateDelete(query, input);

			// insert new tags
			for (RMTag rmTag : tags) {
				query = "INSERT INTO " + ReadingMaterial.TABLE_RMTAG + " ( "
						+ ReadingMaterial.COL_TAGID + ", "
						+ ReadingMaterial.COL_RMID +")\n"
						+ " VALUES (?, ?);";

				input.clear();
				input.add(rmTag.getTagID());
				input.add(rmID);

				result = q.runInsertUpdateDelete(query, input);
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

		return result;
	}

	// borrow
	public static boolean borrowRM(User user, int rmID) {
		boolean result = false;

		String query = "INSERT INTO " + ReadingMaterial.TABLE_BORROWEDRM + " ( "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_USERID + ", "
				+ ReadingMaterial.COL_DATEBORROWED + ", "
				+ ReadingMaterial.COL_DATERETURNED +")\n"
				+ " VALUES (?, ?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(rmID);
		input.add(user.getUserID());
		input.add(Calendar.getInstance().getTime());

		input.add((user.getUserType() == UserType.STUDENT ? 
				input.add(Utils.addDays(Calendar.getInstance().getTime(), 7)) :
					input.add(Utils.addMonth(Calendar.getInstance().getTime(), 1))));

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

	// reserve
	public static boolean reserveRM(User user, int rmID) {
		boolean result = false;

		String query = "INSERT INTO " + ReadingMaterial.TABLE_RESERVEDRM + " ( "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_USERID + ", "
				+ ReadingMaterial.COL_DATERESERVED + ")\n"
				+ " VALUES (?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(rmID);
		input.add(user.getUserID());
		input.add(Calendar.getInstance().getTime());

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

	// return	
	
	// cancel reservation
	public static boolean cancelResRM(int reservedRMID) {
		boolean result = false;
		
		String query = "DELETE FROM " + ReadingMaterial.TABLE_RESERVEDRM
				+ " WHERE " + ReadingMaterial.COL_RESERVEDRMID + " = ?;";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(reservedRMID);
		
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

	// add review
	
	
	// edit review
	

	// get all RM by search/tag
	// get most borrowed RM
	// get new arrivals

	// get RM by id

	// get borrowed RM of user

}
