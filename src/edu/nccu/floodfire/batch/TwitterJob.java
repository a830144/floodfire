package edu.nccu.floodfire.batch;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import edu.nccu.floodfire.service.TwitterDataService;
import edu.nccu.floodfire.util.JobseqUtil;
import edu.nccu.floodfire.util.TwitterConfiguration;

public class TwitterJob implements Job{
	public void execute(JobExecutionContext context)
    throws JobExecutionException
  {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String queryType = dataMap.getString("queryType");
		String keyword = dataMap.getString("keyword");
		String jobSeq = dataMap.getString("jobSeq");
		TwitterConfiguration twitterConfiguration = (TwitterConfiguration)dataMap.get("twitterConfiguration");
		ApplicationContext applicationContext = (ApplicationContext)dataMap.get("applicationContext");
		TwitterDataService twitterDataService = (TwitterDataService) applicationContext.getBean("twitterDataService");
		

		/**
		 * 因應使用者需求，原有mention、keywordAndMention的查詢條件均刪除
		 */
		if ("search".equals(queryType)) {
			if (keyword != null && !"".equals(keyword)) {				
				twitterDataService.getTweetDataBySearch(keyword, twitterConfiguration, true);
				JobseqUtil.setSearchJobseq(jobSeq);
			}
			
		}
		else if("stream".equals(queryType)){
			if (keyword != null && !"".equals(keyword)) {				
				twitterDataService.getTweetDataByStreamFilter(keyword, twitterConfiguration, true);
				JobseqUtil.setStreamJobseq(jobSeq);
			}
			
		}
		
		
  }

}
