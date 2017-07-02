package model;

public class Review {
	
	public static final String TABLE_NAME = "review";
	public static final String COL_REVIEWID = "reviewID";
	public static final String COL_IDNUMBER = User.COL_IDNUMBER;
	public static final String COL_RMID = ReadingMaterial.COL_RMID;
	public static final String COL_REVIEW = "review";
	
	private int reviewID;
	private User user;
	private String RMID;
	private String review;
	
	public Review() {
		
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRMID() {
		return RMID;
	}

	public void setRMID(String rMID) {
		RMID = rMID;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	

}
