package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import db.Query;
import model.RMTag;
import model.ReadingMaterial;
import model.Review;
import model.User;
import model.UserType;
import utils.Utils;

public class ReadingMaterialService {

	// add
	public static boolean addRM(ReadingMaterial myRM) {
		boolean result = false;

		String query = "\nINSERT INTO " + ReadingMaterial.TABLE_RM + " ( "
				+ ReadingMaterial.COL_RMTYPE + ", "
				+ ReadingMaterial.COL_TITLE + ", " 
				+ ReadingMaterial.COL_AUTHOR + ", " 
				+ ReadingMaterial.COL_PUBLISHER + ", " 
				+ ReadingMaterial.COL_YEAR + ", " 
				+ ReadingMaterial.COL_DATEARRIVED + ")\n "
				+ " VALUES (?, ?, ?, ?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(myRM.getRMType());
		input.add(myRM.getTitle());
		input.add(myRM.getAuthor());
		input.add(myRM.getPublisher());
		input.add(myRM.getYear());
		input.add(Utils.convertDateJavaToStringDB(myRM.getDateArrived()));

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
					query = "\nINSERT INTO " + ReadingMaterial.TABLE_RMTAG + " ( "
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

		String query = "\nUPDATE " + ReadingMaterial.TABLE_RM + " \n "
				+ " SET "
				+ ReadingMaterial.COL_RMTYPE + " = ?, "
				+ ReadingMaterial.COL_TITLE + " = ?, " 
				+ ReadingMaterial.COL_AUTHOR + " = ?, " 
				+ ReadingMaterial.COL_PUBLISHER + " = ?, " 
				+ ReadingMaterial.COL_YEAR + " = ? \n "
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(myRM.getRMType());
		input.add(myRM.getTitle());
		input.add(myRM.getAuthor());
		input.add(myRM.getPublisher());
		input.add(myRM.getYear());
		input.add(myRM.getRMID_Location());

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
	public static boolean editRMTags(String rmID_location, ArrayList<RMTag> tags) {
		boolean result = false;

		Query q = Query.getInstance();

		String query;
		ArrayList<Object> input = new ArrayList<>();

		try {
			// delete current tags
			query = "\nDELETE FROM " + ReadingMaterial.TABLE_RMTAG
					+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

			input.add(rmID_location);

			result = q.runInsertUpdateDelete(query, input);

			// insert new tags
			for (RMTag rmTag : tags) {
				query = "\nINSERT INTO " + ReadingMaterial.TABLE_RMTAG + " ( "
						+ ReadingMaterial.COL_TAGID + ", "
						+ ReadingMaterial.COL_RMID +")\n"
						+ " VALUES (?, ?);";

				input.clear();
				input.add(rmTag.getTagID());
				input.add(rmID_location);

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
	public static boolean borrowRM(User user, String rmID_location) {
		boolean result = false;

		String query = "\nINSERT INTO " + ReadingMaterial.TABLE_BORROWEDRM + " ( "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_IDNUMBER + ", "
				+ ReadingMaterial.COL_DATEBORROWED + ", "
				+ ReadingMaterial.COL_DATERETURNED +")\n"
				+ " VALUES (?, ?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(rmID_location);
		input.add(user.getIDNumber());
		input.add(Utils.convertDateJavaToStringDB(Calendar.getInstance().getTime()));

		input.add((user.getUserType() == UserType.STUDENT ? 
				input.add(Utils.convertDateJavaToStringDB(Utils.addDays(Calendar.getInstance().getTime(), 7))) :
					input.add(Utils.convertDateJavaToStringDB(Utils.addMonth(Calendar.getInstance().getTime(), 1)))));

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
	public static boolean reserveRM(User user, String rmID_location) {
		boolean result = false;

		String query = "\nINSERT INTO " + ReadingMaterial.TABLE_RESERVEDRM + " ( "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_IDNUMBER + ", "
				+ ReadingMaterial.COL_DATERESERVED + ")\n"
				+ " VALUES (?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(rmID_location);
		input.add(user.getIDNumber());
		input.add(Utils.convertDateJavaToStringDB(Calendar.getInstance().getTime()));

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
	public static boolean returnRM(int borrowedRMID) {
		boolean result = false;

		String query = "\nDELETE FROM " + ReadingMaterial.TABLE_BORROWEDRM
				+ " WHERE " + ReadingMaterial.COL_BORROWEDRMID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(borrowedRMID);

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

	// cancel reservation
	public static boolean cancelResRM(int reservedRMID) {
		boolean result = false;

		String query = "\nDELETE FROM " + ReadingMaterial.TABLE_RESERVEDRM
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

	// get RM by id and user type
	public static ReadingMaterial getRMByID(String rmID_location) {
		ReadingMaterial rm = null;
		RMTag rmTag = null;
		Review review = null;
		User user = null;

		// 1. all reading material details
		String query = "\nSELECT * FROM " + ReadingMaterial.TABLE_RM 
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(rmID_location);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			if(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(rmID_location);
				rm.setRMType(r.getString(ReadingMaterial.COL_RMTYPE));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setAuthor(r.getString(ReadingMaterial.COL_AUTHOR));
				rm.setPublisher(r.getString(ReadingMaterial.COL_PUBLISHER));
				rm.setYear(r.getInt(ReadingMaterial.COL_YEAR));

				// 2. tags
				query = "\nSELECT * FROM " + ReadingMaterial.TABLE_RMTAG
						+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

				input.clear();
				input.add(rmID_location);

				r = q.runQuery(query, input);

				while(r.next()) {
					rmTag = new RMTag();
					rmTag.setRMTagID(r.getInt(ReadingMaterial.COL_RMTAGID));
					rmTag.setRMID(rmID_location);
					rmTag.setTagID(r.getInt(ReadingMaterial.COL_TAGID));
					rmTag.setTag(r.getString(ReadingMaterial.COL_TAG));

					rm.addTag(rmTag);
				}

				// 3. reviews
				query = "\nSELECT "
						+ User.COL_FIRSTNAME + ", "
						+ User.COL_LASTNAME + ", "
						+ Review.COL_REVIEW + "\n"
						+ " FROM " + Review.TABLE_NAME
						+ " NATURAL JOIN " + User.TABLE_USER + "\n"
						+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

				input.clear();
				input.add(rmID_location);

				r = q.runQuery(query, input);

				while(r.next()) {
					review = new Review();
					review.setReview(r.getString(Review.COL_REVIEW));

					user = new User();
					user.setFirstName(r.getString(User.COL_FIRSTNAME));
					user.setLastName(r.getString(User.COL_LASTNAME));

					review.setUser(user);

					rm.addReview(review);
				}

				// 4. get borrowed and reserved


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

		return rm;
	}

	// get list of user who borrowed rm
	public static ArrayList<ReadingMaterial> getUserListBorrowedRM(int rmID_location) {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();
		User user = null;
		ReadingMaterial rm = null;

		String query = "\nSELECT "
				+ ReadingMaterial.COL_BORROWEDRMID + ", "
				+ ReadingMaterial.COL_DATEBORROWED + ", "
				+ ReadingMaterial.COL_DATERETURNED + ", "
				+ User.COL_IDNUMBER + ", "
				+ User.COL_FIRSTNAME + ", "
				+ User.COL_LASTNAME + "\n"
				+ " FROM " + ReadingMaterial.TABLE_BORROWEDRM
				+ " NATURAL JOIN " + User.TABLE_USER + "\n"
				+ " WHERE " + ReadingMaterial.COL_BORROWEDRMID + " = ?\n"
				+ " ORDER BY " + ReadingMaterial.COL_DATEBORROWED;

		ArrayList<Object> input = new ArrayList<>();		
		input.add(rmID_location);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setBorrowedRMID(r.getInt(ReadingMaterial.COL_BORROWEDRMID));
				rm.setDateBorrowed(r.getDate(ReadingMaterial.COL_DATEBORROWED));
				rm.setDateReturned(r.getDate(ReadingMaterial.COL_DATERETURNED));
				
				user = new User();
				user.setIDNumber(r.getString(User.COL_IDNUMBER));
				user.setFirstName(r.getString(User.COL_FIRSTNAME));
				user.setLastName(r.getString(User.COL_LASTNAME));

				rm.setUserBorrowed(user);

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
	
	// get user who currently reserved this rm
	public static ReadingMaterial getUserReservedRM(int rmID_location) {
		User user = null;
		ReadingMaterial rm = null;

		String query = "\nSELECT "
				+ ReadingMaterial.COL_RESERVEDRMID + ", "
				+ ReadingMaterial.COL_DATERESERVED + ", "
				+ User.COL_IDNUMBER + ", "
				+ User.COL_FIRSTNAME + ", "
				+ User.COL_LASTNAME + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM
				+ " NATURAL JOIN " + User.TABLE_USER + "\n"
				+ " WHERE " + ReadingMaterial.COL_RESERVEDRMID + " = ?\n"
				+ " ORDER BY " + ReadingMaterial.COL_DATERESERVED;

		ArrayList<Object> input = new ArrayList<>();		
		input.add(rmID_location);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			while(r.next()) {

				rm = new ReadingMaterial();
				rm.setBorrowedRMID(r.getInt(ReadingMaterial.COL_RESERVEDRMID));
				rm.setDateReturned(r.getDate(ReadingMaterial.COL_DATERESERVED));
				
				user = new User();
				user.setIDNumber(r.getString(User.COL_IDNUMBER));
				user.setFirstName(r.getString(User.COL_FIRSTNAME));
				user.setLastName(r.getString(User.COL_LASTNAME));

				rm.setUserReserved(user);

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

		return rm;
	}

	// get all RM by search/tag
	// get most borrowed RM
	// get new arrivals


	// get borrowed RM of user

}
