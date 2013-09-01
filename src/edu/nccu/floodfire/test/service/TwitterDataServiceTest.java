package edu.nccu.floodfire.test.service;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.service.TwitterDataService;

public class TwitterDataServiceTest extends TestCase{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	
	public static void main(String args[])
	{

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TwitterDataService twitterDataService = (TwitterDataService)applicationContext.getBean("twitterDataService");		
		twitterDataService.getTweetDataBySearch("app");
	}
}
