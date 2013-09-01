package edu.nccu.floodfire.test.dao;


import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.dao.TwitterDao;
import edu.nccu.floodfire.entity.Tweets;
import edu.nccu.floodfire.entity.Users;
import edu.nccu.floodfire.util.DateUtil;

public class TwitterDaoTest extends TestCase{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
	
	
	public void testGetUserNameByScreenName()
	{
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TwitterDao twitterDao = (TwitterDao)applicationContext.getBean("twitterDao");
		twitterDao.getTweetsByScreenName("JIM chou");
	}
	
	public void testPersistTweet()
	{
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TwitterDao twitterDao = (TwitterDao)applicationContext.getBean("twitterDao");
		Tweets tweets = new Tweets();
		Users users = new Users(); 
		tweets.setId("1234567890");
		tweets.setTimestamp(DateUtil.getCurrentYYYYMMDDHHmmss());
		tweets.setContributors("Jim Chou");
		tweets.setCreatedAt(new java.sql.Timestamp(DateUtil.getParseDate("Wed Jul 04 12:08:56 -0700 2001").getTime()));
		users.setId("0000");
		tweets.setUser(users);
		twitterDao.persistTweet(tweets);
		
	}
	

	public void testGetTweetCountByJobSeqNo()
	{
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TwitterDao twitterDao = (TwitterDao)applicationContext.getBean("twitterDao");
		
		twitterDao.getTweetCountByJobSeqNoAndCreateAt(null, null, null);
		
	}
	
	public void testExportData()
	{
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TwitterDao twitterDao = (TwitterDao)applicationContext.getBean("twitterDao");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		java.sql.Date endDate = new java.sql.Date(cal.getTime().getTime());
		cal.add(Calendar.DATE, -2); 
		java.sql.Date startDate = new java.sql.Date(cal.getTime().getTime());
		
		
		
		
		String fileName = "exportTweetData.csv";
		
		twitterDao.exportData(startDate, endDate, fileName);
		
	}


}
