package service;

import java.sql.SQLException;
import java.util.ArrayList;

import db.Query;
import model.Review;

public class ReviewService {

	// add review
	public static boolean addReview(Review review) {
		boolean result = false;

		String query = "\nINSERT INTO " + Review.TABLE_NAME + " ( "
				+ Review.COL_IDNUMBER + ", "
				+ Review.COL_RMID + ", "
				+ Review.COL_REVIEW + ")\n"
				+ " VALUES (?, ?, ?);";

		ArrayList<Object> input = new ArrayList<>();
		input.add(review.getUserID());
		input.add(review.getRMID());
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
				+ Review.COL_REVIEW + " = ?\n"
				+ " WHERE " + Review.COL_REVIEWID + " = ?;";

		ArrayList<Object> input = new ArrayList<>();
		input.add(review.getUserID());
		input.add(review.getRMID());
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

}
