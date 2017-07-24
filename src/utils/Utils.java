package utils;

import java.util.Calendar;
import java.util.Date;

import model.UserType;

public class Utils {
	
	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}
	
	public static Date addMonth(Date date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		return c.getTime();
	}
	
	public static Date subtractMonth(Date date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -months);
		return c.getTime();
	}
	
	public static String convertDateJavaToStringDB(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		return year + "-" + ((month < 10) ? "0" + month : month) + "-" + ((day < 10) ? "0" + day : day);
	}
	
	public static Date convertStringToDate(String strDate) {
		
		String[] splitDate = strDate.split("-");
		int year = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1])-1;
		int day = Integer.parseInt(splitDate[2]);
		
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		
		return c.getTime();
	}
	
	public static int convertCurrTimetoInteger() {
		Calendar c = Calendar.getInstance();
		
		int h = c.get(Calendar.HOUR_OF_DAY) * 100;
		int m = c.get(Calendar.MINUTE);
		
		return h + m;
	}
	
	public static int[] getTimeSlots() {
		int[] timeSlots = new int[27];
		int start = 700;
		timeSlots[0] = start;
		
		for(int i = 1; i < timeSlots.length; i ++) {
			timeSlots[i] = start + ((i % 2 == 0) ? 70 : 30);
			start = timeSlots[i];
		}
		
		return timeSlots;
		
	}
	
	public static Date getAnticipatedReturnDate(UserType userType) {
		// set "reservation date"
		Date date_reserved = Calendar.getInstance().getTime();
		Date date_anticipated = null;

		// set anticipated return date
		if(userType == UserType.STUDENT) {
			date_anticipated = addDays(date_reserved, 8);
		} else if(userType == UserType.FACULTY) {
			date_anticipated = addMonth(date_reserved, 1);
			date_anticipated = addDays(date_anticipated, 1);
		}
		
		return date_anticipated;
	}

}
