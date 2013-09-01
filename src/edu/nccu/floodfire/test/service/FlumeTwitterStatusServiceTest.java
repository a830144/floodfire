package edu.nccu.floodfire.test.service;

import java.util.Date;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.model.twitter.TweetData;
import edu.nccu.floodfire.service.FlumeTwitterStatusService;

public class FlumeTwitterStatusServiceTest extends TestCase{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public void testInsert()
	{
		TweetData tweetData = new TweetData();
		tweetData.setTweetId("1234567890");
		tweetData.setContributors("jimChou");
		tweetData.setCoordinates("Tim Duncan,Tony Parker,Manu Ginobili");
		tweetData.setCreated_at(new Date(1));
		tweetData.setEntities("A LOT OF ENTITY");
		tweetData.setFavorite_count(2);
		tweetData.setFavorited(true);
		tweetData.setText("father");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		FlumeTwitterStatusService flumeTwitterStatusService = (FlumeTwitterStatusService)applicationContext.getBean("flumeTwitterStatusService");
		flumeTwitterStatusService.insert(tweetData);
	}

}
