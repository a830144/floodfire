package edu.nccu.floodfire.batch;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.constants.SystemConstants;
import edu.nccu.floodfire.service.SyncDataService;

public class SyncJob implements Job {
	
	SyncDataService syncDataService;
	
	String jobSeq = "";


	@Override
	public void execute(JobExecutionContext context)
		    throws JobExecutionException
 {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String syncFunc = (String) dataMap.get("syncFunc");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		syncDataService = (SyncDataService) applicationContext.getBean("syncDataService");
		if ("yourTWapperKeeper".equals(syncFunc)) {
			syncDataService.syncDataFromNoSQLToMySQL("2013-07-07",
					SystemConstants.MySQL_YOURTWAPPERKEEPER);
		} else {
			syncDataService.syncDataFromNoSQLToMySQL("2013-07-7",
					SystemConstants.MySQL_TWEETS);
		}
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
		 
	}
	
	public void stopIt()
	{
	
	}





}
