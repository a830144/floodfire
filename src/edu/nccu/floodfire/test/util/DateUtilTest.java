package edu.nccu.floodfire.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.nccu.floodfire.util.DateUtil;

public class DateUtilTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date date1 = sdf.parse("2013/08/30");
			Date date2 = sdf.parse("2013/09/01");
			
			 System.out.println(DateUtil.getDiffBetweenTwoDate(date2, date1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
