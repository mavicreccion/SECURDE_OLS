package model;

public class RMTag {
	
	private int RMTagID;
	private int tagID;
	private String rmID;
	private String tag;
	
	public RMTag() {
		
	}

	public int getRMTagID() {
		return RMTagID;
	}

	public void setRMTagID(int rMTagID) {
		RMTagID = rMTagID;
	}

	public int getTagID() {
		return tagID;
	}

	public void setTagID(int tagID) {
		this.tagID = tagID;
	}

	public String getRMID() {
		return rmID;
	}

	public void setRMID(String rmID) {
		this.rmID = rmID;
	}

	public String getRmID() {
		return rmID;
	}

	public void setRmID(String rmID) {
		this.rmID = rmID;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
