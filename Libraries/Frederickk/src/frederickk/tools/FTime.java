package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.5
 *  FTime.java
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a collection of tools that i tend to use frequently
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
//import java.text.DateFormatSymbols;
import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;



public class FTime {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static Calendar cal = Calendar.getInstance();
	private static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private static String[] shortMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	

	
	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	private static String addZero(String val) {
		if (val.length() == 1) val = "0" + val;
		return val;
	}
	
	
	
	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	/**
	 * return the current year as string "yyyy"
	 * "yy" = year as String with leading zero (i.e. 12)
	 * "yyyy" = year as String (i.e. 2012)
	 * 
	 * @return year
	 */
//	public static String year(String format) {
//		String year = String.valueOf( cal.get(Calendar.YEAR) ); 
//		year = addZero(year);
//
//		if(format.equals("yy")) {
//			year.substring(2);
//		}
//		
//		return year;
//	}
	/**
	 * return the current year as string "yyyy"
	 * 
	 * @return year
	 */
	public static String year() {
//		return year("yyyy");
		String year = String.valueOf( cal.get(Calendar.YEAR) ); 
		year = addZero(year);
		return year;
	}

	
	/**
	 * return the current month as string 
	 * "MM" = month as String with leading zero (i.e. 03)
	 * "MMM" = month as String as 3 char abbreviation (i.e. Mar)
	 * "MMMM" = month as String (i.e. March)
	 * 
	 * @return month
	 */
	public static String month(String format) {
		String month;

		if(format.equals("MMMM")) {
			//month = new DateFormatSymbols().getMonths()[ cal.get(Calendar.MONTH) ];
			month = months[ cal.get(Calendar.MONTH) ];
		}
		else if(format.equals("MMM")) {
			//month = new DateFormatSymbols().getShortMonths()[ cal.get(Calendar.MONTH) ];
			month = shortMonths[ cal.get(Calendar.MONTH) ];
		}
		else {
			month = String.valueOf( cal.get(Calendar.MONTH) + 1 );
			month = addZero(month);
		}
		return month;
	}
	/**
	 * return the current month as string "MM"
	 * 
	 * @return month
	 */
	public static String month() {
		return month("MM");
	}


	/**
	 * return the current day as string "dd"
	 * 
	 * @return day
	 */
	public static String day() {
		String day = String.valueOf( cal.get(Calendar.DATE) ); 
		day = addZero(day);
		return day;
	}

	
	/**
	 * return the current date as string "yyyyMMdd"
	 * 
	 * @return date
	 */
	public static String date() {
//		Calendar cal = Calendar.getInstance();
//		String[] date = new String[3];
//		
//		date[0] = String.valueOf( cal.get(Calendar.YEAR) );
//		date[1] = String.valueOf( cal.get(Calendar.MONTH) + 1 );
//		date[2] = String.valueOf( cal.get(Calendar.DATE) );
//
//		for (int i = 0; i != date.length; i++) {
//			if (date[i].length() == 1)
//				date[i] = "0" + date[i];
//		}
//
//		return date[0] + date[1] + date[2];
		return year() + month() + day();
	
	}

	
	
	/**
	 * return the current hour as string "HH"
	 * 
	 * @return hour
	 */
	public static String hour() {
		String hour = String.valueOf( cal.get(Calendar.HOUR_OF_DAY) ); 
		hour = addZero(hour);
		return hour;
	}
	/**
	 * return the current minute as string "mm"
	 * 
	 * @return minute
	 */
	public static String minute() {
		String minute = String.valueOf( cal.get(Calendar.MINUTE) ); 
		minute = addZero(minute);
		return minute;
	}
	/**
	 * return the current second as string "ss"
	 * 
	 * @return second
	 */
	public static String second() {
		String second = String.valueOf( cal.get(Calendar.SECOND) ); 
		second = addZero(second);
		return second;
	}
	
	/**
	 * return the current time as string "HHmmss"
	 * 
	 * @return time
	 */
	public static String time() {
		//Calendar cal = new GregorianCalendar();
//		Calendar cal = Calendar.getInstance();
//		String[] time = new String[3];
//
//		time[0] = String.valueOf( cal.get(Calendar.HOUR_OF_DAY) ); 
//		time[1] = String.valueOf( cal.get(Calendar.MINUTE) );
//		time[2] = String.valueOf( cal.get(Calendar.SECOND) );
//
//		for (int i = 0; i != time.length; i++) {
//			if (time[i].length() == 1)
//				time[i] = "0" + time[i];
//		}
//
//		return time[0] + time[1] + time[2];
		return hour() + minute() + second();
	}
}
