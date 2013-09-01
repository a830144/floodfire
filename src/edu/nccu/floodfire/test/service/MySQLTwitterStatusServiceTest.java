package edu.nccu.floodfire.test.service;


import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.service.MySQLTwitterStatusService;

public class MySQLTwitterStatusServiceTest extends TestCase{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("rawtypes")
	public void testGetTweetDataByUserName()
	{
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		MySQLTwitterStatusService mySqlTwitterStatusService = (MySQLTwitterStatusService)applicationContext.getBean("mySqlTwitterStatusService");
		List list =mySqlTwitterStatusService.getTweetDataByUserName("JIM chou");
		List sublist = (List)list.get(0);
		System.out.println(sublist.get(0));
		System.out.println(sublist.get(1));
		System.out.println(sublist.get(2));
		System.out.println(sublist.get(3));
	}
	
	

}
