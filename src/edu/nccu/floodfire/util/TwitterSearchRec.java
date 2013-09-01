package edu.nccu.floodfire.util;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import edu.nccu.floodfire.constants.SystemConstants;
import edu.nccu.floodfire.model.twitter.TweetData;
import edu.nccu.floodfire.service.MySQLTwitterStatusService;
import edu.nccu.floodfire.service.NoSQLTwitterStatusService;

/**
 * 
 * @author User
 * 1.這隻程式負責Twitter Search API的資料蒐集，由於程式本身設計為可以線上(同步)/批次(非同步)，
 * 所以工作代號(jobSeq)並不由建構子帶過來，而是自己產生，等同於所有程式之間的protocol
 * 這個protocol是
 * yyyy-mm-dd 加上 ?queryType=search&keyword=keyword
 * 
 * 2.凡是放記憶體、塞db，所以程式的共用protocol均以此為準，以避免這隻程式要用做線上程式時無法使用
 *
 */
public class TwitterSearchRec {

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

	public void getTweetData(String keyword)
	{
		TwitterConfiguration twitterConfiguration = new TwitterConfiguration();
		getTweetData(keyword,twitterConfiguration);

	}
	
	public void getTweetData(String keyword,TwitterConfiguration twitterConfiguration)
	{
		getTweetData(keyword,twitterConfiguration,true);

	}
	/**
	 * 
	 * @param keyword 輸入的keyword
	 * @param twitterConfiguration 是否要用自定義的TwitterConfiguration
	 * @param insertTable 是否要將蒐集的資料塞進db
	 */
	@SuppressWarnings("unchecked")
	public void getTweetData(String keyword,TwitterConfiguration twitterConfiguration,boolean insertTable)
	{
		Twitter twitter = twitterConfiguration.getTwitter();
        try {
            Query query = new Query(keyword);
            query.setCount(100);
            QueryResult result;         
            result = twitter.search(query);                
            List<Status> tweets = result.getTweets();
            String jobseq = JobseqUtil.getSearchJobseq();
            for (Status tweet : tweets) {
               if(insertTable)
               {
                   insertTwitter(jobseq,tweet);
               }
               else
               {
            	   SystemConstants.TEST_CONNECTION.put(twitterConfiguration.getAccessToken(), tweet.getText());
               }
               System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
            }
            JobseqUtil.removeSearchlocalJobseq();
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        } catch (Exception e){
        	e.printStackTrace();
            System.out.println("Failed to search tweets OR insert into HBase: " + e.getMessage());
        }

	}

	private void insertTwitter(String jobSeq,Status status)
	{
		TweetData tweetData = new TwitterDtoUtil().setTwitter(jobSeq,status);
		setCount(jobSeq);
		if("MySQL".equals(SystemConstants.USE_DB))
		{
			mySQLTwitterStatusService.insert(tweetData);
		}
		else
		{
		   noSQLTwitterStatusService.insert(tweetData);
		}		
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
