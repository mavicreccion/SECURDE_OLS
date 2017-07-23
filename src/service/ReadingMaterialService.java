package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import db.Query;
import model.RMFilter;
import model.RMStatus;
import model.RMType;
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
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_RMTYPE + ", "
				+ ReadingMaterial.COL_TITLE + ", " 
				+ ReadingMaterial.COL_AUTHOR + ", " 
				+ ReadingMaterial.COL_PUBLISHER + ", " 
				+ ReadingMaterial.COL_YEAR + ", " 
				+ ReadingMaterial.COL_TAG + ", "
				+ ReadingMaterial.COL_DATEARRIVED + ", "
				+ ReadingMaterial.COL_LIBSTATUS + ")\n "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(myRM.getRMID_Location());
		input.add(myRM.getRMType());
		input.add(myRM.getTitle());
		input.add(myRM.getAuthor());
		input.add(myRM.getPublisher());
		input.add(myRM.getYear());
		input.add(myRM.getTags());
		input.add(Utils.convertDateJavaToStringDB(myRM.getDateArrived()));
		input.add(RMStatus.INSTOCK + "");

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


	// delete
	public static boolean deleteRM(String rmID) {
		boolean result = false;

		String query = "\nUPDATE " + ReadingMaterial.TABLE_RM + "\n"
				+ " SET " + ReadingMaterial.COL_LIBSTATUS + " = ?\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(RMStatus.OUTSTOCK + "");
		input.add(rmID);

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

	// edit reading material details
	public static boolean editRM(ReadingMaterial myRM) {
		boolean result = false;

		String query = "\nUPDATE " + ReadingMaterial.TABLE_RM + " \n "
				+ " SET "
				+ ReadingMaterial.COL_RMTYPE + " = ?, "
				+ ReadingMaterial.COL_TITLE + " = ?, " 
				+ ReadingMaterial.COL_AUTHOR + " = ?, " 
				+ ReadingMaterial.COL_PUBLISHER + " = ?, " 
				+ ReadingMaterial.COL_YEAR + " = ?, "
				+ ReadingMaterial.COL_TAG + " = ? \n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(myRM.getRMType());
		input.add(myRM.getTitle());
		input.add(myRM.getAuthor());
		input.add(myRM.getPublisher());
		input.add(myRM.getYear());
		input.add(myRM.getTags());
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

	// check if user can still borrow
	public static boolean checkIfUserCanReserve(User user) {
		boolean result = true;

		String query = "\nSELECT COUNT(*) "
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + ReadingMaterial.COL_IDNUMBER + " = ? "
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + ") "
				+ " OR CURDATE() <= " + ReadingMaterial.COL_DATERESERVED + ") \n"
				+ " GROUP BY " + ReadingMaterial.COL_IDNUMBER + "\n"
				+ " HAVING COUNT(*) < ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(user.getIDNumber());
		if(user.getUserType() == UserType.STUDENT)
			input.add(5);
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
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return result;
	}

	// reserve
	public static boolean reserveRM(ReadingMaterial rm) {
		boolean result = false;

		String query = "\nINSERT INTO " + ReadingMaterial.TABLE_RESERVEDRM + " ( "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_IDNUMBER + ", "
				+ ReadingMaterial.COL_DATERESERVED + ", "
				+ ReadingMaterial.COL_DATEBORROWED + ", "
				+ ReadingMaterial.COL_DATERETURNED + ")\n"
				+ " VALUES (?, ?, ?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(rm.getRMID_Location());
		input.add(rm.getUserReserved().getIDNumber());

		Date date_reserved = rm.getDateReserved();
		Date date_borrowed = Utils.addDays(date_reserved, 1);

		input.add(Utils.convertDateJavaToStringDB(date_reserved));
		input.add(Utils.convertDateJavaToStringDB(date_borrowed));

		input.add((rm.getUserReserved().getUserType() == UserType.STUDENT ? 
				Utils.convertDateJavaToStringDB(Utils.addDays(date_borrowed, 7)) :
					Utils.convertDateJavaToStringDB(Utils.addMonth(date_borrowed, 1))));

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

	// check if rm can be deleted
	// can only delete if no borrow or reserve
	public static boolean checkIfCanDelete(String rmID) {
		boolean result = false;

		String query = "\nSELECT " + ReadingMaterial.COL_RMID
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM 
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ? "
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + ") "
				+ " OR CURDATE() <= " + ReadingMaterial.COL_DATERESERVED + ")\n";

		ArrayList<Object> input = new ArrayList<>();
		input.add(rmID);

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


		return !result;
	}
	/////////////////////////// GETTERS ///////////////////////////////////////////

	// get RM by id and user type
	public static ReadingMaterial getRMByID(String rmID_location, UserType userType) {
		ReadingMaterial rm = null;
		Review review = null;
		User user = null;

		String query_details = "\nSELECT * FROM " + ReadingMaterial.TABLE_RM 
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

		String query_reviews = "\nSELECT "
				+ User.COL_FIRSTNAME + ", "
				+ User.COL_LASTNAME + ", "
				+ Review.COL_DATEREVIEWED + ", "
				+ Review.COL_REVIEW + "\n"
				+ " FROM " + Review.TABLE_NAME
				+ " NATURAL JOIN " + User.TABLE_USER + "\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

		String query_reserved = "\nSELECT *" + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND " + ReadingMaterial.COL_DATERESERVED + " >= CURDATE()";

		String query_borrowed = "\nSELECT " + ReadingMaterial.COL_DATERETURNED
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n" 
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + "); ";

		ArrayList<Object> input = new ArrayList<>();
		input.add(rmID_location);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {

			// 1. details
			r = q.runQuery(query_details, input);
			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(rmID_location);
				rm.setRMType(RMType.getValue(r.getString(ReadingMaterial.COL_RMTYPE)));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setAuthor(r.getString(ReadingMaterial.COL_AUTHOR));
				rm.setPublisher(r.getString(ReadingMaterial.COL_PUBLISHER));
				rm.setYear(r.getInt(ReadingMaterial.COL_YEAR));
				rm.setTags(r.getString(ReadingMaterial.COL_TAG));
				rm.setStatus(RMStatus.getStockValue(r.getString(ReadingMaterial.COL_LIBSTATUS)));
			}

			r.close();

			// 2. reviews
			r = q.runQuery(query_reviews, input);
			while(r.next()) {
				review = new Review();
				review.setReview(r.getString(Review.COL_REVIEW));
				review.setDate_reviewed(r.getDate(Review.COL_DATEREVIEWED));

				user = new User();
				user.setFirstName(r.getString(User.COL_FIRSTNAME));
				user.setLastName(r.getString(User.COL_LASTNAME));

				review.setUser(user);

				rm.addReview(review);
			}

			r.close();

			// 3. status
			if(rm.getStatus() == RMStatus.INSTOCK) {
				// reserved
				r = q.runQuery(query_reserved, input);

				if(r.next()) {
					rm.setStatus(RMStatus.RESERVED);
					rm.setReservedRMID(r.getInt(ReadingMaterial.COL_RESERVEDRMID));
					rm.setDateAvailable(r.getDate(ReadingMaterial.COL_DATERETURNED));
				} else {
					r.close();

					// borrowed
					r = q.runQuery(query_borrowed, input);

					if(r.next()) {
						rm.setStatus(RMStatus.BORROWED);

						// set date of availability
						rm.setDateAvailable(r.getDate(ReadingMaterial.COL_DATERETURNED));
					} else {

						///// available
						rm.setStatus(RMStatus.AVAILABLE);

						// set "reservation date"
						rm.setDateReserved(Calendar.getInstance().getTime());

						// set anticipated return date
						if(userType == UserType.STUDENT) {
							rm.setDateReturned(Utils.addDays(rm.getDateReserved(), 8));
						} else if(userType == UserType.FACULTY) {
							rm.setDateReturned(Utils.addMonth(rm.getDateReserved(), 1));
							rm.setDateReturned(Utils.addDays(rm.getDateReturned(), 1));
						}
					}
				}

				r.close();
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

		return rm;
	}

	// get RM title by id
	public static String getRMTitleByID(String rmID) {
		String title = "";

		String query = "\nSELECT " + ReadingMaterial.COL_TITLE
				+ " FROM " + ReadingMaterial.TABLE_RM
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(rmID);

		Query q = Query.getInstance();
		ResultSet r = null;
		try {
			r = q.runQuery(query, input);

			if(r.next()) {
				title = r.getString(1);
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

		return title;
	}

	// get RM by type
	public static ArrayList<ReadingMaterial> getRMByType(RMType rmType) {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();
		ReadingMaterial rm = null;
		
		String query = "\nSELECT * "
				+ " FROM " + ReadingMaterial.TABLE_RM
				+ " WHERE " + ReadingMaterial.COL_RMTYPE + " = ?";

		// for status
		String query_reserved = "\nSELECT " + ReadingMaterial.COL_DATERETURNED + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND " + ReadingMaterial.COL_DATERESERVED + " >= CURDATE()";

		String query_borrowed = "\nSELECT " + ReadingMaterial.COL_DATERETURNED
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n" 
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + "); ";
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(rmType + "");
		
		Query q = Query.getInstance();
		ResultSet r = null;
		ResultSet r2 = null;

		try {
			r = q.runQuery(query, input);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(r.getString(ReadingMaterial.COL_RMID));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setAuthor(r.getString(ReadingMaterial.COL_AUTHOR));
				rm.setPublisher(r.getString(ReadingMaterial.COL_PUBLISHER));
				rm.setYear(r.getInt(ReadingMaterial.COL_YEAR));
				rm.setStatus(RMStatus.getStockValue(r.getString(ReadingMaterial.COL_LIBSTATUS)));
				rm.setTags(r.getString(ReadingMaterial.COL_TAG));

				rmList.add(rm);
			}

			for (ReadingMaterial rm1 : rmList) {
				// for status
				// check if in stock
				if(rm1.getStatus() == RMStatus.INSTOCK) {
					input.clear();
					input.add(rm1.getRMID_Location());

					r2 = q.runQuery(query_reserved, input);

					if(r2.next()) {
						rm1.setStatus(RMStatus.RESERVED);
					} else {
						r2 = q.runQuery(query_borrowed, input);

						if(r2.next()) {
							rm1.setStatus(RMStatus.BORROWED);
						} else {
							rm1.setStatus(RMStatus.AVAILABLE);
						}
					}

					r2.close();
				}
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

	// get list of user who borrowed rm
	public static ArrayList<ReadingMaterial> getUserListBorrowedRM(int rmID_location) {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();
		User user = null;
		ReadingMaterial rm = null;

		String query = "\nSELECT "
				+ ReadingMaterial.COL_RESERVEDRMID + ", "
				+ ReadingMaterial.COL_DATEBORROWED + ", "
				+ ReadingMaterial.COL_DATERETURNED + ", "
				+ User.COL_IDNUMBER + ", "
				+ User.COL_FIRSTNAME + ", "
				+ User.COL_LASTNAME + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM
				+ " NATURAL JOIN " + User.TABLE_USER + "\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?\n"
				+ " ORDER BY " + ReadingMaterial.COL_DATERESERVED + " DESC";

		ArrayList<Object> input = new ArrayList<>();		
		input.add(rmID_location);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setReservedRMID(r.getInt(ReadingMaterial.COL_RESERVEDRMID));
				rm.setDateBorrowed(r.getDate(ReadingMaterial.COL_DATEBORROWED));
				rm.setDateReturned(r.getDate(ReadingMaterial.COL_DATERETURNED));

				user = new User();
				user.setIDNumber(r.getString(User.COL_IDNUMBER));
				user.setFirstName(r.getString(User.COL_FIRSTNAME));
				user.setLastName(r.getString(User.COL_LASTNAME));

				rm.setUserReserved(user);

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
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ? "
				+ " AND " + ReadingMaterial.COL_DATERESERVED + " >= CURDATE();";

		ArrayList<Object> input = new ArrayList<>();		
		input.add(rmID_location);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			if(r.next()) {

				rm = new ReadingMaterial();
				rm.setReservedRMID(r.getInt(ReadingMaterial.COL_RESERVEDRMID));
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
				r.close();
				q.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rm;
	}

	// get all RM by search
	public static ArrayList<ReadingMaterial> searchRM(RMFilter rmFilter, RMType rmType, String searchString) {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();
		ReadingMaterial rm = null;

		String query = "";
		ArrayList<Object> input = new ArrayList<>();
		input.add(searchString);

		// filters
		String column = "";
		if(rmFilter == RMFilter.TITLE) {
			column = ReadingMaterial.COL_TITLE;
		} else if(rmFilter == RMFilter.AUTHOR) {
			column = ReadingMaterial.COL_AUTHOR;
		} else if(rmFilter == RMFilter.PUBLISHER){
			column = ReadingMaterial.COL_PUBLISHER;
		}
		
		if(rmFilter == RMFilter.KEYWORDS && rmType == RMType.ALL) {
			query = "\nSELECT * "
					+ " FROM " + ReadingMaterial.TABLE_RM
					+ " WHERE MATCH("
					+ ReadingMaterial.COL_TITLE + ", "
					+ ReadingMaterial.COL_AUTHOR + ", "
					+ ReadingMaterial.COL_PUBLISHER + ", "
					+ ReadingMaterial.COL_TAG + ") AGAINST(?);";
			
		} else if(rmFilter == RMFilter.KEYWORDS) {
			query = "\nSELECT * "
					+ " FROM " + ReadingMaterial.TABLE_RM
					+ " WHERE MATCH("
					+ ReadingMaterial.COL_TITLE + ", "
					+ ReadingMaterial.COL_AUTHOR + ", "
					+ ReadingMaterial.COL_PUBLISHER + ", "
					+ ReadingMaterial.COL_TAG + ") AGAINST(?) "
					+ " AND " + ReadingMaterial.COL_RMTYPE + " = ?;";
			input.add(rmType + "");
			
		} else if(rmType == RMType.ALL) {
			// search in READING MATERIAL table without type
			query = "\nSELECT * "
					+ " FROM " + ReadingMaterial.TABLE_RM
					+ " WHERE MATCH(" + column + ") AGAINST(?);";

		} else {
			// search in READING MATERIAL table with type
			query = "\nSELECT * "
					+ " FROM " + ReadingMaterial.TABLE_RM
					+ " WHERE MATCH(" + column + ") AGAINST(?)"
					+ " AND " + ReadingMaterial.COL_RMTYPE + " = ?;";

			input.add(rmType + "");
		}

		// for status
		String query_reserved = "\nSELECT *" + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND " + ReadingMaterial.COL_DATERESERVED + " >= CURDATE()";

		String query_borrowed = "\nSELECT " + ReadingMaterial.COL_DATERETURNED
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n" 
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + "); ";

		Query q = Query.getInstance();
		ResultSet r = null;
		ResultSet r2 = null;

		try {
			r = q.runQuery(query, input);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(r.getString(ReadingMaterial.COL_RMID));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setAuthor(r.getString(ReadingMaterial.COL_AUTHOR));
				rm.setPublisher(r.getString(ReadingMaterial.COL_PUBLISHER));
				rm.setYear(r.getInt(ReadingMaterial.COL_YEAR));
				rm.setStatus(RMStatus.getStockValue(r.getString(ReadingMaterial.COL_LIBSTATUS)));
				rm.setTags(r.getString(ReadingMaterial.COL_TAG));

				rmList.add(rm);
			}

			for (ReadingMaterial rm1 : rmList) {
				// for status
				// check if in stock
				if(rm1.getStatus() == RMStatus.INSTOCK) {
					input.clear();
					input.add(rm1.getRMID_Location());

					r2 = q.runQuery(query_reserved, input);

					if(r2.next()) {
						rm1.setStatus(RMStatus.RESERVED);
					} else {
						r2 = q.runQuery(query_borrowed, input);

						if(r2.next()) {
							rm1.setStatus(RMStatus.BORROWED);
						} else {
							rm1.setStatus(RMStatus.AVAILABLE);
						}
					}

					r2.close();
				}
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

	// get most borrowed RM (TOP 10)
	public static ArrayList<ReadingMaterial> getMostBorrowedRM() {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();
		ReadingMaterial rm = null;

		String query = "\nSELECT "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_TITLE + ", "
				+ ReadingMaterial.COL_AUTHOR + ", "
				+ ReadingMaterial.COL_PUBLISHER + ", "
				+ ReadingMaterial.COL_YEAR + ", "
				+ " COUNT(*) AS NUMBORROWED \n"
				+ " FROM " + ReadingMaterial.TABLE_RM 
				+ " NATURAL JOIN " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " GROUP BY " + ReadingMaterial.COL_RMID + "\n"
				+ " ORDER BY NUMBORROWED DESC\n"
				+ " LIMIT 10;";

		ArrayList<Object> input = new ArrayList<>();

		// for status
		String query_reserved = "\nSELECT " + ReadingMaterial.COL_DATERETURNED + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND " + ReadingMaterial.COL_DATERESERVED + " >= CURDATE()";

		String query_borrowed = "\nSELECT " + ReadingMaterial.COL_DATERETURNED
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n" 
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + "); ";

		Query q = Query.getInstance();
		ResultSet r = null;
		ResultSet r2 = null;

		try {
			r = q.runQuery(query);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(r.getString(ReadingMaterial.COL_RMID));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setAuthor(r.getString(ReadingMaterial.COL_AUTHOR));
				rm.setPublisher(r.getString(ReadingMaterial.COL_PUBLISHER));
				rm.setYear(r.getInt(ReadingMaterial.COL_YEAR));
				rm.setStatus(RMStatus.getStockValue(r.getString(ReadingMaterial.COL_LIBSTATUS)));
				rm.setTags(r.getString(ReadingMaterial.COL_TAG));
				rm.setNumTimesBorrowed(r.getInt("NUMBORROWED"));

				rmList.add(rm);
			}

			for (ReadingMaterial rm1 : rmList) {
				// for status
				// check if in stock
				if(rm1.getStatus() == RMStatus.INSTOCK) {
					input.clear();
					input.add(rm1.getRMID_Location());

					r2 = q.runQuery(query_reserved, input);

					if(r2.next()) {
						rm1.setStatus(RMStatus.RESERVED);
					} else {
						r2 = q.runQuery(query_borrowed, input);

						if(r2.next()) {
							rm1.setStatus(RMStatus.BORROWED);
						} else {
							rm1.setStatus(RMStatus.AVAILABLE);
						}
					}

					r2.close();
				}
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

	// get new arrivals (TOP 10)
	public static ArrayList<ReadingMaterial> getNewArrivals() {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();
		ReadingMaterial rm = null;

		String query = "\nSELECT * "
				+ " FROM " + ReadingMaterial.TABLE_RM + "\n"
				+ " WHERE CURDATE() BETWEEN " + ReadingMaterial.COL_DATEARRIVED
				+ " AND DATE_ADD(" + ReadingMaterial.COL_DATEARRIVED + ", INTERVAL 1 MONTH)\n"
				+ " ORDER BY " + ReadingMaterial.COL_DATEARRIVED + " DESC\n"
				+ " LIMIT 10";

		// for status
		String query_reserved = "\nSELECT " + ReadingMaterial.COL_DATERETURNED + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND " + ReadingMaterial.COL_DATERESERVED + " >= CURDATE()";

		String query_borrowed = "\nSELECT " + ReadingMaterial.COL_DATERETURNED
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n" 
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + "); ";

		Query q = Query.getInstance();
		ResultSet r = null;
		ResultSet r2 = null;
		ArrayList<Object> input = new ArrayList<>();

		try {
			r = q.runQuery(query);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(r.getString(ReadingMaterial.COL_RMID));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setAuthor(r.getString(ReadingMaterial.COL_AUTHOR));
				rm.setPublisher(r.getString(ReadingMaterial.COL_PUBLISHER));
				rm.setYear(r.getInt(ReadingMaterial.COL_YEAR));
				rm.setStatus(RMStatus.getStockValue(r.getString(ReadingMaterial.COL_LIBSTATUS)));
				rm.setTags(r.getString(ReadingMaterial.COL_TAG));

				rmList.add(rm);
			}

			for (ReadingMaterial rm1 : rmList) {
				// for status
				// check if in stock
				if(rm1.getStatus() == RMStatus.INSTOCK) {
					input.clear();
					input.add(rm1.getRMID_Location());

					r2 = q.runQuery(query_reserved, input);

					if(r2.next()) {
						rm1.setStatus(RMStatus.RESERVED);
					} else {
						r2 = q.runQuery(query_borrowed, input);

						if(r2.next()) {
							rm1.setStatus(RMStatus.BORROWED);
						} else {
							rm1.setStatus(RMStatus.AVAILABLE);
						}
					}

					r2.close();
				}
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

	// get current borrowed RM of user
	public static ArrayList<ReadingMaterial> getCurrentBorrowedRMOfUser(String id_number) {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();	
		ReadingMaterial rm = null;

		String query = "\nSELECT "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_TITLE + ", "
				+ ReadingMaterial.COL_DATEBORROWED + ", "
				+ ReadingMaterial.COL_DATERETURNED + ", "
				+ ReadingMaterial.COL_LIBSTATUS + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RM 
				+ " NATURAL JOIN " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ? "
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + ") \n"
				+ " ORDER BY " + ReadingMaterial.COL_DATEBORROWED + " DESC\n";

		ArrayList<Object> input = new ArrayList<>();
		input.add(id_number);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(r.getString(ReadingMaterial.COL_RMID));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setDateBorrowed(r.getDate(ReadingMaterial.COL_DATEBORROWED));
				rm.setDateReturned(r.getDate(ReadingMaterial.COL_DATERETURNED));
				rm.setStatus(RMStatus.getStockValue(r.getString(ReadingMaterial.COL_LIBSTATUS)));
				rm.setTags(r.getString(ReadingMaterial.COL_TAG));

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

	// get past borrowed RM of user
	public static ArrayList<ReadingMaterial> getPastBorrowedRMOfUser(String id_number) {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();	
		ReadingMaterial rm = null;

		String query = "\nSELECT "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_TITLE + ", "
				+ ReadingMaterial.COL_DATEBORROWED + ", "
				+ ReadingMaterial.COL_DATERETURNED + ", "
				+ ReadingMaterial.COL_LIBSTATUS + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RM 
				+ " NATURAL JOIN " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + User.COL_IDNUMBER + " = ? "
				+ " AND CURDATE() <= " + ReadingMaterial.COL_DATERETURNED + "\n"
				+ " ORDER BY " + ReadingMaterial.COL_DATEBORROWED + " DESC\n";

		ArrayList<Object> input = new ArrayList<>();
		input.add(id_number);

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query, input);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(r.getString(ReadingMaterial.COL_RMID));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setDateBorrowed(r.getDate(ReadingMaterial.COL_DATEBORROWED));
				rm.setDateReturned(r.getDate(ReadingMaterial.COL_DATERETURNED));
				rm.setStatus(RMStatus.getStockValue(ReadingMaterial.COL_LIBSTATUS));
				rm.setTags(r.getString(ReadingMaterial.COL_TAG));

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

	// get all borrowed books
	public static ArrayList<ReadingMaterial> getAllCurrentBorrowedRM() {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();	
		ReadingMaterial rm = null;

		String query = "\nSELECT "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_TITLE + ", "
				+ ReadingMaterial.COL_DATEBORROWED + ", "
				+ ReadingMaterial.COL_DATERETURNED + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RM 
				+ " NATURAL JOIN " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + ") \n"
				+ " ORDER BY " + ReadingMaterial.COL_DATEBORROWED + " DESC\n";

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(r.getString(ReadingMaterial.COL_RMID));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setDateBorrowed(r.getDate(ReadingMaterial.COL_DATEBORROWED));
				rm.setDateReturned(r.getDate(ReadingMaterial.COL_DATERETURNED));

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

	// get all reserved books
	public static ArrayList<ReadingMaterial> getAllCurrentReservedRM() {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();	
		ReadingMaterial rm = null;

		String query = "\nSELECT "
				+ ReadingMaterial.COL_RESERVEDRMID + ", "
				+ ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_TITLE + ", "
				+ ReadingMaterial.COL_DATERESERVED + ", "
				+ ReadingMaterial.COL_DATERETURNED + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RM 
				+ " NATURAL JOIN " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE CURDATE() <= " + ReadingMaterial.COL_DATERESERVED + "\n"
				+ " ORDER BY " + ReadingMaterial.COL_DATERESERVED + " DESC\n";

		Query q = Query.getInstance();
		ResultSet r = null;

		try {
			r = q.runQuery(query);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setReservedRMID(r.getInt(ReadingMaterial.COL_RESERVEDRMID));
				rm.setRMID_Location(r.getString(ReadingMaterial.COL_RMID));
				rm.setTitle(r.getString(ReadingMaterial.COL_TITLE));
				rm.setDateReserved(r.getDate(ReadingMaterial.COL_DATERESERVED));
				rm.setDateReturned(r.getDate(ReadingMaterial.COL_DATERETURNED));

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

	// get id and status of ALL RM
	public static ArrayList<ReadingMaterial> getDataForExport() {
		ArrayList<ReadingMaterial> rmList = new ArrayList<>();	
		ReadingMaterial rm = null;

		String query = "\nSELECT " + ReadingMaterial.COL_RMID + ", "
				+ ReadingMaterial.COL_LIBSTATUS + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RM;

		// for status
		String query_reserved = "\nSELECT " + ReadingMaterial.COL_DATERETURNED + "\n"
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND " + ReadingMaterial.COL_DATERESERVED + " >= CURDATE()";

		String query_borrowed = "\nSELECT " + ReadingMaterial.COL_DATERETURNED
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n" 
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ?"
				+ " AND (CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED
				+ " AND CURDATE() < " + ReadingMaterial.COL_DATERETURNED + "); ";

		Query q = Query.getInstance();
		ResultSet r = null;
		ResultSet r2 = null;
		ArrayList<Object> input = new ArrayList<>();

		try {
			r = q.runQuery(query);

			while(r.next()) {
				rm = new ReadingMaterial();
				rm.setRMID_Location(r.getString(ReadingMaterial.COL_RMID));
				rm.setStatus(RMStatus.getStockValue(r.getString(ReadingMaterial.COL_LIBSTATUS)));

				rmList.add(rm);
			}

			for (ReadingMaterial rm1 : rmList) {
				// for status
				// check if in stock
				if(rm1.getStatus() == RMStatus.INSTOCK) {
					input.clear();
					input.add(rm1.getRMID_Location());

					r2 = q.runQuery(query_reserved, input);

					if(r2.next()) {
						rm1.setStatus(RMStatus.RESERVED);
					} else {
						r2 = q.runQuery(query_borrowed, input);

						if(r2.next()) {
							rm1.setStatus(RMStatus.BORROWED);
						} else {
							rm1.setStatus(RMStatus.AVAILABLE);
						}
					}

					r2.close();
				}
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

}
