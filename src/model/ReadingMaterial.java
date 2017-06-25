package model;

import java.util.ArrayList;
import java.util.Date;

public class ReadingMaterial {
	
	public static final String TABLE_RM = "reading_material";
	public static final String TABLE_RMTAG = "rm_tag";
	public static final String TABLE_BORROWEDRM = "borrowed_rm";
	public static final String TABLE_RESERVEDRM = "reserved_rm";
	
	public static final String COL_RMID = "rmID";
	public static final String COL_RMTYPE = "rm_type";
	public static final String COL_TITLE = "title";
	public static final String COL_AUTHOR = "author";
	public static final String COL_PUBLISHER = "publisher";
	public static final String COL_YEAR = "year";
	public static final String COL_LOCATION = "location";
	public static final String COL_DATEARRIVED = "date_arrived";
	
	public static final String COL_RMTAGID = "rmTagID";
	public static final String COL_TAGID = "tagID";

	public static final String COL_USERID = User.COL_USERID;
	
	public static final String COL_BORROWEDRMID = "borrowedRmID";
	public static final String COL_DATEBORROWED = "date_borrowed";
	public static final String COL_DATERETURNED = "date_returned";
	
	public static final String COL_RESERVEDRMID = "reservedRmID";
	public static final String COL_DATERESERVED = "date_reserved";

	private String RMID;
	private String RMType;
	private String title;
	private String author;
	private String publisher;
	private int year;
	private String location;
	private Date dateArrived;
	
	private ArrayList<RMTag> tags;
	private ArrayList<Review> reviews;
	private int numTimesBorrowed;
	
	private RMStatus status;
	private Date dateBorrowed;
	private Date dateReturned;
	private Date dateReserved;
	
	public ReadingMaterial() {
		tags = new ArrayList<>();
		reviews = new ArrayList<>();
	}

	public String getRMID() {
		return RMID;
	}

	public void setRMID(String rMID) {
		RMID = rMID;
	}

	public String getRMType() {
		return RMType;
	}

	public void setRMType(String rMType) {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDateArrived() {
		return dateArrived;
	}

	public void setDateArrived(Date dateArrived) {
		this.dateArrived = dateArrived;
	}

	public ArrayList<RMTag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<RMTag> tags) {
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
	
	
	
}
