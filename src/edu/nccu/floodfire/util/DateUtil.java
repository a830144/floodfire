package edu.nccu.floodfire.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	
	
	public static String getCurrentYYYYMMDD()
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(date);
		return time;
	}
	
	public static String getCurrentYYYYMMDDHHmmss()
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String time = df.format(date);
		return time;
	}
	
	public static String getTodayString(int interval)
	{
		Calendar   calendar=Calendar.getInstance(); 
		calendar.add(Calendar.DATE,interval); 
 
		  
		int   year=calendar.get(Calendar.YEAR); 
		String yearS = year +"";

		int   month=calendar.get(Calendar.MONTH);
		month=month+1;
		String monthS = (month+"").length() == 1 ? "0"+month :(month+""); 
		  
		 int   date=calendar.get(Calendar.DATE);
		 String dateS = (date+"").length() == 1 ? "0"+date :(date+""); 
		 
		String   today=""+yearS+"/"+monthS +"/"+dateS+"";
		
		return today;
	}
	
	public static String getTodayFormat(String todayString)
	{
		String format="";
		try {
			String today = todayString;			
			DateFormat df = DateFormat.getDateInstance();
			Date datee = df.parse(today);
			SimpleDateFormat sdf= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
			format = sdf.format(datee);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return format;
	}
	
	public static Date getParseDate(String sourceString)
	{
		Date date = null;
		try {
			
			SimpleDateFormat sdf= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
			date = sdf.parse(sourceString);
					
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	@SuppressWarnings("deprecation")
	public static Long getDiffBetweenTwoDate(Date date1,Date date2)
	{
		  Calendar calendar1 = Calendar.getInstance();
		  Calendar calendar2 = Calendar.getInstance();
		  calendar1.set(date1.getYear(), date1.getMonth(), date1.getDate());
		  calendar2.set(date2.getYear(), date2.getMonth(), date2.getDate());
		  long milliseconds1 = calendar1.getTimeInMillis();
		  long milliseconds2 = calendar2.getTimeInMillis();
		  long diff = milliseconds2 - milliseconds1;
		  long diffDays = diff / (24 * 60 * 60 * 1000);
          return diffDays;
	}

}
