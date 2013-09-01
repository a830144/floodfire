package edu.nccu.floodfire.test.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AnalyzeServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			for(int i=0;i>-7;i--)
			{
			Calendar   calendar=Calendar.getInstance(); 
			calendar.add(Calendar.DATE,i); 
			  
			int   year=calendar.get(Calendar.YEAR); 
			String yearS = year +"";

			int   month=calendar.get(Calendar.MONTH);
			month=month+1;
			String monthS = (month+"").length() == 1 ? "0"+month :(month+""); 
			  
			 int   date=calendar.get(Calendar.DATE);
			 String dateS = (date+"").length() == 1 ? "0"+date :(date+""); 
			 
			String   today=""+yearS+"/"+monthS +"/"+dateS+"";
			
			
			DateFormat df = DateFormat.getDateInstance();
			Date datee = df.parse(today);
			SimpleDateFormat sdf= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
			String format = sdf.format(datee);

				System.out.println(format);
			}
				
				
				
				
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	@SuppressWarnings("unused")
	private String getToday()
	{
		Calendar   calendar=Calendar.getInstance();  
  
		int   year=calendar.get(Calendar.YEAR);  

		int   month=calendar.get(Calendar.MONTH);  
 
		 month=month+1; 
		 int   date=calendar.get(Calendar.DATE);  
		 //獲得日期  
		String   today=""+year+"-"+month+"-"+date+"";
		return today;
	}

}
