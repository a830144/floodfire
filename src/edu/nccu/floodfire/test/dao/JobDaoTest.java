package edu.nccu.floodfire.test.dao;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.dao.JobDao;
import edu.nccu.floodfire.entity.Job;


public class JobDaoTest extends TestCase{
	
	public static void main(String args[]) {
		
            System.out.println(700/100);
	}
	
	
	
	public void testAddJob()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		JobDao jobDao = (JobDao)applicationContext.getBean("JobDao");
		Job job =new Job();
		job.setJob_Seq("222222");
		job.setJobStatus("1");
		job.setCreateId("jim");
		jobDao.addJob(job);
	}
	
	public void testUpdateJob()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		JobDao jobDao = (JobDao)applicationContext.getBean("JobDao");
		//Job job =new Job();
		jobDao.updateJobStatusById("20130616?queryType=search&keyword=spurs&user=jim", "2");
	}
}
