package model;

import java.util.ArrayList;
import java.util.Date;

public class ReadingMaterial {
	
	public static final String TABLE_RM = "reading_material";
	public static final String TABLE_RESERVEDRM = "reserved_rm";
	
	public static final String COL_RMID = "rmID_location";
	public static final String COL_RMTYPE = "rm_type";
	public static final String COL_TITLE = "title";
	public static final String COL_AUTHOR = "author";
	public static final String COL_PUBLISHER = "publisher";
	public static final String COL_YEAR = "year";
	public static final String COL_DATEARRIVED = "date_arrived";
	public static final String COL_LIBSTATUS = "lib_status";
	public static final String COL_TAG = "tags";

	public static final String COL_IDNUMBER = User.COL_IDNUMBER;

	public static final String COL_RESERVEDRMID = "reservedRmID";
	public static final String COL_DATERESERVED = "date_reserved";
	public static final String COL_DATEBORROWED = "date_borrowed";
	public static final String COL_DATERETURNED = "date_returned";

	private String RMID_Location;
	private RMType RMType;
	private String title;
	private String author;
	private String publisher;
	private int year;
	private Date dateArrived;
	private String tags;
	
	private ArrayList<Review> reviews;
	private int numTimesBorrowed;
	
	private RMStatus status;
	private Date dateReserved;
	private Date dateBorrowed;
	private Date dateReturned;
	private Date dateAvailable;
	
	private int reservedRMID;
	
	private User userReserved;
	
	public ReadingMaterial() {
		reviews = new ArrayList<>();
	}

	public String getRMID_Location() {
		return RMID_Location;
	}

	public void setRMID_Location(String RMID_Location) {
		this.RMID_Location = RMID_Location;
	}

	public RMType getRMType() {
		return RMType;
	}

	public void setRMType(RMType rMType) {
		RMType = rMType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getDateArrived() {
		return dateArrived;
	}

	public void setDateArrived(Date dateArrived) {
		this.dateArrived = dateArrived;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public int getNumTimesBorrowed() {
		return numTimesBorrowed;
	}

	public void setNumTimesBorrowed(int numTimesBorrowed) {
		this.numTimesBorrowed = numTimesBorrowed;
	}

	public RMStatus getStatus() {
		return status;
	}

	public void setStatus(RMStatus status) {
		this.status = status;
	}

	public Date getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(Date dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	public Date getDateReserved() {
		return dateReserved;
	}

	public void setDateReserved(Date dateReserved) {
		this.dateReserved = dateReserved;
	}
	
	public Date getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(Date dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public int getReservedRMID() {
		return reservedRMID;
	}

	public void setReservedRMID(int reservedRMID) {
		this.reservedRMID = reservedRMID;
	}

	public User getUserReserved() {
		return userReserved;
	}

	public void setUserReserved(User userReserved) {
		this.userReserved = userReserved;
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
}
