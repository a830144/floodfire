package edu.nccu.floodfire.test.batch;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.batch.TwitterJob;
import edu.nccu.floodfire.util.TwitterConfiguration;

public class QuartzDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		
            // Grab the Scheduler instance from the Factory 
            try {
				Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

				// and start it off
				TwitterConfiguration twitterConfiguration = 
					new TwitterConfiguration("nKIM7AIZpjxEvZSU5iQnA",
							"1BiL6ARMLT5GV7ubJOcDkDMyU9S130z5cEOEqOAQ",
							"1006162200-etpJhjK66JoeuvGisE3GwMZx36iLsXnPbXyOH0f",
							"G8RocgIYJSCoAkXaqSzUS3dDzFpZh3CqM9LRH8Odc");
				ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
				JobDataMap jobDataMap = new JobDataMap();
				jobDataMap.put("twitterConfiguration", twitterConfiguration);
				jobDataMap.put("applicationContext", applicationContext);
				
				scheduler.start();
				JobDetail job = newJob(TwitterJob.class)
				.withIdentity("myJob", "group1") // name "myJob", group "group1"
				.usingJobData("queryType", "search")
				.usingJobData("keyword", "babe")
				.usingJobData(jobDataMap)
				.build();

				Trigger trigger = newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(simpleSchedule()
				    .withIntervalInSeconds(40)
				    .repeatForever())
				.build();
				
				scheduler.scheduleJob(job, trigger);

				Thread.sleep(60000) ;
				
				scheduler.shutdown();
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        

	}

}
