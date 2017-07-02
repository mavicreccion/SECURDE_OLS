package model.datetime;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SimpleDate {
	private int year;
	private int month;
	private int day;
	public SimpleDate(int year,int month,int day){
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
	}
public SimpleDate(String date){
		
		String[] arrDate = date.substring(0,10).split("-");
		year = Integer.parseInt(arrDate[0]); 
		month = Integer.parseInt(arrDate[1]);
		day = Integer.parseInt(arrDate[2]);
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		if(month > 12)
			setYear(getYear() + 1);
		this.month = month % 12 + 1;
		// this.month %= 12; 
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		if(day > new GregorianCalendar(getYear(), getMonth(), 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH) + 1)
			setMonth(getMonth() + 1);
		this.day = day % (new GregorianCalendar(getYear(), getMonth(), 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH) + 1);
	}
	
	public Calendar getCalendar() {
	    Calendar date = Calendar.getInstance();
	    date.set(Calendar.YEAR, year);
	    date.set(Calendar.MONTH, month);
	    date.set(Calendar.DAY_OF_MONTH, day);

	    return date;
	}
	
	public String toString(){
		return year + "-" + ((month < 10) ? "0" + month : month) + "-" + ((day < 10) ? "0" + day : day);
	}
	
	public boolean equals(int year, int month, int day){
		return getYear() == year && getMonth() == month && getDay() == day;
	}
	
	public boolean equals(SimpleDate date){
		return date.getYear() == year && date.getMonth() == month && date.getDay() == day;
	}
}
