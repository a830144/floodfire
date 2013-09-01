package edu.nccu.floodfire.test.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.util.TwitterSearchRec;


public class TwitterStreamRecTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//TwitterSearchRec twitterSearchRec = new TwitterSearchRec();
		//System.out.println((1373715/60)/60/18);
		//System.out.println(1373715/(60*60*19));
		
		String keyword = "spurs";
		//Date date = new Date();
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//String time = df.format(date);
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TwitterSearchRec twitterSearchRec = (TwitterSearchRec)applicationContext.getBean("twitterSearchRec");
		twitterSearchRec.getTweetData(keyword);
		
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String eventName = time + "?queryType=Search&keyword="+keyword;
		
		//twitterSearchRec.stop(eventName);

	}

}
