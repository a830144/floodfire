package edu.nccu.floodfire.test.service;


import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.entity.Job;
import edu.nccu.floodfire.service.ManagementService;

public class ManagementServiceTest extends TestCase{

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	public void testAddUser()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ManagementService managementService = (ManagementService)applicationContext.getBean("managementService");
		managementService.addUser("manu", "1234");
	}
	
	@SuppressWarnings("rawtypes")
	public void testGetUserByIdWildCard()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ManagementService managementService = (ManagementService)applicationContext.getBean("managementService");
		List list = managementService.getUserByIdWildCard("");
		for(int i=0;i<list.size();i++)
		{
			List sublist = (List)list.get(i);
			System.out.println(sublist.get(0) +"::"+sublist.get(1));
		}
	}
	
	public void testAddJob()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ManagementService managementService = (ManagementService)applicationContext.getBean("managementService");
		Job job =new Job();
		job.setJob_Seq("333333");
		job.setJobStatus("1");
		job.setCreateId("jim");
		managementService.addJob(job);
	}
	
	@SuppressWarnings("rawtypes")
	public void testGetJobByUserId()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ManagementService managementService = (ManagementService)applicationContext.getBean("managementService");
		List list = managementService.getJobByUserId("jim");
		for(int i=0;i<list.size();i++)
		{
			List sublist = (List)list.get(i);
			System.out.println(sublist.get(0) +"::"+sublist.get(1));
		}
	}

}
