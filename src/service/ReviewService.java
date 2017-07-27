package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import db.Query;
import model.ReadingMaterial;
import model.Review;
import utils.Utils;

public class ReviewService {
	
	// check if user can review the rm
	public static boolean checkIfBorrowed(String id_number, String rmID) {
		boolean result = false;
		
		String query = "\nSELECT " + ReadingMaterial.COL_RMID
				+ " FROM " + ReadingMaterial.TABLE_RESERVEDRM + "\n"
				+ " WHERE " + ReadingMaterial.COL_RMID + " = ? "
				+ " AND " + ReadingMaterial.COL_IDNUMBER + " = ? "
				+ " AND CURDATE() >= " + ReadingMaterial.COL_DATEBORROWED;
		
		ArrayList<Object> input = new ArrayList<>();
		input.add(rmID);
		input.add(id_number);
		
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

	// add review
	public static boolean addReview(Review review) {
		boolean result = false;

		String query = "\nINSERT INTO " + Review.TABLE_NAME + " ( "
				+ Review.COL_IDNUMBER + ", "
				+ Review.COL_RMID + ", "
				+ Review.COL_DATEREVIEWED + ", "
				+ Review.COL_REVIEW + ")\n"
				+ " VALUES (?, ?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(review.getUser().getIdnumber());
		input.add(review.getRMID());
		input.add(Utils.convertDateJavaToStringDB(Calendar.getInstance().getTime()));
		input.add(review.getReview());

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

	// edit review
	public static boolean editReview(Review review) {
		boolean result = false;

		String query = "\nUPDATE " + Review.TABLE_NAME + " \n "
				+ " SET "
				+ Review.COL_IDNUMBER + " = ?, "
				+ Review.COL_RMID + " = ?, "
				+ Review.COL_REVIEW + " = ?, "
				+ Review.COL_DATEREVIEWED + " = ?\n"
				+ " WHERE " + Review.COL_REVIEWID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(review.getUser().getIdnumber());
		input.add(review.getRMID());
		input.add(review.getReview());
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

	
}
