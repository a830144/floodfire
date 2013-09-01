package edu.nccu.floodfire.test.service;


import java.util.Date;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.model.twitter.TweetData;
import edu.nccu.floodfire.service.CassandraTwitterStatusService;

public class CassandraTwitterStatusServiceTest extends TestCase{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
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
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		CassandraTwitterStatusService cassandraService = (CassandraTwitterStatusService)applicationContext.getBean("cassandraTwitterStatusService");
		cassandraService.insert(tweetData);
	}
	
	public void testQueryByID()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		CassandraTwitterStatusService cassandraService = (CassandraTwitterStatusService)applicationContext.getBean("cassandraTwitterStatusService");
		cassandraService.queryBySid("332402941187194880");
		
	}

}
