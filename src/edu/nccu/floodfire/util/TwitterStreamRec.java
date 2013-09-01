package edu.nccu.floodfire.util;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import edu.nccu.floodfire.constants.SystemConstants;
import edu.nccu.floodfire.model.twitter.TweetData;
import edu.nccu.floodfire.service.MySQLTwitterStatusService;
import edu.nccu.floodfire.service.NoSQLTwitterStatusService;

/**
 * 
 * @author User
 * 這隻程式負責Twitter Stream API的資料蒐集，由於程式本身設計為可以線上(同步)/批次(非同步)，
 * 所以工作代號(jobSeq)並不由建構子帶過來，而是自己產生，等同於所有程式之間的protocol
 * 這個protocol是
 * yyyy-mm-dd 加上 ?queryType=stream&keyword=keyword
 *
 */

public class TwitterStreamRec {
	
	private NoSQLTwitterStatusService noSQLTwitterStatusService;
	private MySQLTwitterStatusService mySQLTwitterStatusService;
	public MySQLTwitterStatusService getMySQLTwitterStatusService() {
		return mySQLTwitterStatusService;
	}

	public void setMySQLTwitterStatusService(
			MySQLTwitterStatusService mySQLTwitterStatusService) {
		this.mySQLTwitterStatusService = mySQLTwitterStatusService;
	}
	public NoSQLTwitterStatusService getNoSQLTwitterStatusService() {
		return noSQLTwitterStatusService;
	}
	public void setNoSQLTwitterStatusService(
			NoSQLTwitterStatusService noSQLTwitterStatusService) {
		this.noSQLTwitterStatusService = noSQLTwitterStatusService;
	}
	
	@SuppressWarnings("rawtypes")
	public Map twitterStreamMap = new HashMap();
	
	
	public void getDataByFilter(final String keyword,final String queryFunc)
	{
		TwitterConfiguration twitterConfiguration = new TwitterConfiguration();
		getDataByFilter(keyword,queryFunc,twitterConfiguration);
	}
	
	public void getDataByFilter(final String keyword,final String queryFunc,final TwitterConfiguration twitterConfiguration)
	{
		getDataByFilter(keyword,queryFunc,twitterConfiguration,true);
	}
	
	/**
	 * 這是對應到twitter stream中public stream<filter>的方法
	 * @param keyword
	 */
	@SuppressWarnings("unchecked")
	public void getDataByFilter(final String keyword,final String queryFunc,final TwitterConfiguration twitterConfiguration,boolean insertTable)
	{
		
		TwitterStream twitterStream = twitterConfiguration.getTwitterStream();
		final String jobseq = JobseqUtil.getStreamJobseq();
		StatusListener listener = new StatusListener() {
			public void onStatus(Status status) {
				insertTwitter(jobseq,status);
			}
			public void onDeletionNotice(
					StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:"
						+ statusDeletionNotice.getStatusId());
			}
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:"
						+ numberOfLimitedStatuses);
			}
			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId
						+ " upToStatusId:" + upToStatusId);
			}
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
			@Override
			public void onStallWarning(StallWarning arg0) {
				System.out.println("xxxxxx");
			}
		};
		twitterStream.addListener(listener);
		FilterQuery filterQuery = new FilterQuery();
		String[] keyWords = {keyword};
		filterQuery.track(keyWords);
		twitterStream.filter(filterQuery);
		
		SystemConstants.STREAM_POOL.put(jobseq,twitterStream);
	}
	
	
	public void stop(String jobseq)
	{
		TwitterStream twitterStream = (TwitterStream)twitterStreamMap.get(jobseq);		
		twitterStream.cleanUp(); // shutdown internal stream consuming thread
		twitterStream.shutdown(); // Shuts down internal dispatcher thread shared by all TwitterStream instances.
	}
	
	private void insertTwitter(String jobSeq,Status status)
	{
		TweetData tweetData = new TwitterDtoUtil().setTwitter(jobSeq,status);
		if("MySQL".equals(SystemConstants.USE_DB))
		{
			mySQLTwitterStatusService.insert(tweetData);
		}
		else
		{
		   noSQLTwitterStatusService.insert(tweetData);
		}
		setCount(jobSeq);
		setPreviewData(jobSeq,tweetData);
	}
	@SuppressWarnings("unchecked")
	private void setCount(String jobSeq)
	{
		if(SystemConstants.COLLECTTWEETCOUNT.containsKey(jobSeq))
		{
			int count = Integer.parseInt(""+SystemConstants.COLLECTTWEETCOUNT.get(jobSeq))+1;
			SystemConstants.COLLECTTWEETCOUNT.put(jobSeq, count);
		}
		else
		{
			SystemConstants.COLLECTTWEETCOUNT.put(jobSeq, 1);
		}
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes"})
	private void setPreviewData(String jobSeq, TweetData tweetData) {

		if (SystemConstants.PREVIEW_COLLECTTWEETDATA.containsKey(jobSeq)) {
			Queue queue = (Queue) SystemConstants.PREVIEW_COLLECTTWEETDATA.get(jobSeq);
			if(queue.size() < 150)
			{
				queue.offer(tweetData);
			}
			else
			{
			    queue.poll();
			    queue.offer(tweetData);
			}
		} else {
			Queue queue = new ArrayBlockingQueue(150);
			queue.offer(tweetData);
			SystemConstants.PREVIEW_COLLECTTWEETDATA.put(jobSeq, queue);
		}
	}
	

}
