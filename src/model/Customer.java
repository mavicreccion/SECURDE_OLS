package model;

import java.util.ArrayList;

public class Customer extends User {
	
	private ArrayList<ReadingMaterial> borrowedList;
	private ArrayList<ReservedRoom> reservedRoom;
	
	public Customer() {
		super();
		borrowedList = new ArrayList<>();
		reservedRoom = new ArrayList<>();
	}
	
	public void borrowRM(ReadingMaterial rm) {
		borrowedList.add(rm);
	}
	
	public void reserveRoom(ReservedRoom room) {
		reservedRoom.add(room);
	}

}
