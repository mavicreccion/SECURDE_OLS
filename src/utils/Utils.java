package utils;

import java.util.Calendar;
import java.util.Date;

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

}
