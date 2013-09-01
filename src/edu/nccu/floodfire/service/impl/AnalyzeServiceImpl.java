package edu.nccu.floodfire.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import edu.nccu.floodfire.dao.TwitterDao;
import edu.nccu.floodfire.service.AnalyzeService;
import edu.nccu.floodfire.util.DateUtil;

public class AnalyzeServiceImpl implements AnalyzeService {
    
	public void setTwitterDao(TwitterDao twitterDao) {
		this.twitterDao = twitterDao;
	}

	private TwitterDao twitterDao;
	
	/**
	 * 推文和推文人數的數量
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<String> getAnalyzeByUserString(Date startDate,Date endDate,String jobSeq) {
				
		String contributors ="";
		String tweets ="";
		List<String> list = new ArrayList<String>();
		if("".equals(startDate) || startDate==null || "".equals(endDate) ||endDate==null)
		{
			for (int i = 0; i > -7; i--) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new java.util.Date());
				cal.add(Calendar.DATE, i);
				java.sql.Date today = new java.sql.Date(cal.getTime().getTime());
				
				tweets = "" + twitterDao.getTweetCountByJobSeqNoAndCreateAt(jobSeq, today, "=");
				contributors = ""+ twitterDao.getContributorCountByJobSeqNoAndCreateAt(jobSeq, today);
				String one = "{date: " + today + ",user:" + contributors
						+ ",tweet:" + tweets + "}";
				list.add(one);
			}
		}
		else
		{
			long diff  = DateUtil.getDiffBetweenTwoDate(endDate,startDate);
			for (int i = 0; i > diff-1; i--) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(endDate);
				cal.add(Calendar.DATE, i);
				java.sql.Date today = new java.sql.Date(cal.getTime().getTime());
				
				tweets = "" + twitterDao.getTweetCountByJobSeqNoAndCreateAt(jobSeq, today, "=");
				contributors = ""+ twitterDao.getContributorCountByJobSeqNoAndCreateAt(jobSeq, today);
				String one = "{date: " + today + ",user:" + contributors
						+ ",tweet:" + tweets + "}";
				list.add(one);
			}
		}
		list = reverseListValue(list);        
		return list;
	}
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<String> getAnalyzeByDataString(Date startDate,Date endDate,String jobSeq,String keyword) {
				
		String contributors ="";
		String tweets ="";
		List<String> list = new ArrayList<String>();
		if("".equals(startDate) || startDate==null || "".equals(endDate) ||endDate==null)
		{
			for (int i = 0; i > -7; i--) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new java.util.Date());
				cal.add(Calendar.DATE, i);
				java.sql.Date today = new java.sql.Date(cal.getTime().getTime());
				
				tweets = "" + twitterDao.getTweetCountByJobSeqNoAndCreateAtAndKeyword(jobSeq, today, "=",keyword);
				contributors = ""+ twitterDao.getContributorCountByJobSeqNoAndCreateAtAndKeyword(jobSeq, today,keyword);
				String one = "{date: " + today + ",user:" + contributors
						+ ",tweet:" + tweets + "}";
				list.add(one);
			}
		}
		else
		{
			long diff  = DateUtil.getDiffBetweenTwoDate(endDate,startDate);
			for (int i = 0; i > diff-1; i--) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(endDate);
				cal.add(Calendar.DATE, i);
				java.sql.Date today = new java.sql.Date(cal.getTime().getTime());
				
				tweets = "" + twitterDao.getTweetCountByJobSeqNoAndCreateAtAndKeyword(jobSeq, today, "=",keyword);
				contributors = ""+ twitterDao.getContributorCountByJobSeqNoAndCreateAtAndKeyword(jobSeq, today,keyword);
				String one = "{date: " + today + ",user:" + contributors
						+ ",tweet:" + tweets + "}";
				list.add(one);
			}
		}
		list = reverseListValue(list);        
		return list;
	}
	
	

	
	/**
	 * retweet排行
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getAnalyzeRetweetCount(Date startDate,Date endDate,String jobSeq)
	{
		List<List<String>> newList = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		java.sql.Date sqlStartDate = null;
		java.sql.Date sqlEndDate = null;
		if(startDate!=null)
		{
		   cal.setTime(startDate);
		   sqlStartDate = new java.sql.Date(cal.getTime().getTime());
		}
		if(endDate!=null)
		{
		   cal.setTime(endDate);
		   sqlEndDate = new java.sql.Date(cal.getTime().getTime());
		}
		List<?> list = twitterDao.getTweetByRetweetOrMentionCount(jobSeq,sqlStartDate,sqlEndDate, "1");
		if(list.size() > 100)
		{
			list = list.subList(0, 100);
		}
		for(int i =0;i<list.size();i++)
		{
			List sublist = new LinkedList();
			Object[] o = (Object[])list.get(i);
			sublist.add(o[0]);
			sublist.add(o[1]);
			sublist.add(o[2]);
	
			newList.add(sublist);
		}
		
			
		return newList;
	}
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getAnalyzeMentionCount(Date startDate, Date endDate,
			String jobSeq) {
		List<List<String>> newList = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		java.sql.Date sqlStartDate = null;
		java.sql.Date sqlEndDate = null;
		if(startDate!=null)
		{
		   cal.setTime(startDate);
		   sqlStartDate = new java.sql.Date(cal.getTime().getTime());
		}
		if(endDate!=null)
		{
		   cal.setTime(endDate);
		   sqlEndDate = new java.sql.Date(cal.getTime().getTime());
		}
		List<?> list = twitterDao.getTweetByRetweetOrMentionCount(jobSeq,sqlStartDate,sqlEndDate, "2");
		if(list.size() > 100)
		{
			list = list.subList(0, 100);
		}
		for(int i =0;i<list.size();i++)
		{
			List sublist = new ArrayList();
			Object[] o = (Object[])list.get(i);
			sublist.add(o[0]);
			sublist.add(o[1]);
			sublist.add(o[3]);
			newList.add(sublist);
		}
		return newList;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List reverseListValue(List list) {
		if (list != null && list.size() > 0) {
			List lt = new ArrayList();
			for (int i = list.size() - 1; i >= 0; i--) {
				lt.add(list.get(i));
			}
			return lt;
		} else {
			System.out.println("該集合中没有元素");
			return null;
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<String> getTrendByJobSeq(String startDate, String endDate,
			String jobSeq) {
		
		String tweet ="";
		String noDuplicate ="";
		int totalTweet =0;
		int totalNoDuplicate =0;
		int percent=0;
		List<String> list = new ArrayList<String>();
		if("".equals(startDate) || startDate==null || "".equals(endDate) ||endDate==null)
		{
			for (int i = 0; i > -7; i--) {
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new java.util.Date());
				cal.add(Calendar.DATE, i); 
				
				java.sql.Date today = new java.sql.Date(cal.getTime().getTime());
				noDuplicate = "" + twitterDao.getTweetCountNoDuplicateByJobSeqNoAndCreateAt(jobSeq, today, "=");
				tweet = "" + twitterDao.getTweetCountByJobSeqNoAndCreateAt(jobSeq, today, "=");
				
				totalNoDuplicate = twitterDao.getTweetCountNoDuplicateByJobSeqNoAndCreateAt(jobSeq, today, "<=");
				totalTweet = twitterDao.getTweetCountByJobSeqNoAndCreateAt(jobSeq, today, "<=");
				System.out.println("totalTweet::"+totalTweet + ";totalNoDuplicate::"+totalNoDuplicate);
				
				if(totalNoDuplicate==0)
				{
				    percent = 0;
				}
				else
				{
					percent = totalTweet/totalNoDuplicate;
					System.out.println("here");
				}
				System.out.println("percent::"+percent);
				String one = "{date: " + today + ",noDuplicate:" + noDuplicate
						+ ",tweet:" + tweet + ",percent:" + percent + "}";
				list.add(one);
			}
		}		
		list = reverseListValue(list);        
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getURLCount(String startDate, String endDate, String jobSeq) {
		/** TODO 
		這隻程式的規格如下(如果以MySQL innoDB)，要不要用Lucene端看是否還有其他更進一步的統計需求；如果
		沒有更進一步統計需求(ex:有URL的推文是否又有其他關鍵字?)，那麼可以將第三步抓到的資料直接在JAVA內
		用Reqular Express直接處理，然後存入另一個資料表URLTweet
		資料表::URLcountTweetId  (欄位:lastId)
		資料表::URLTweet (欄位:URL,COUNT)
		1.系統先取出現有TweetData.tweets資料中的最後一筆，執行以下SQL<SQL1>，執行結果以<SQL1結果>表示
		  <SQL1>:
		  select top 1 [tweetId] from TweetData.tweets order by tweetId DESC
		2.系統將抓到的資料存入table URLcountTweetId，執行以下SQL<SQL2>，執行結果以<SQL2結果>表示
		  <SQL2>: 
		  Update URLcountTweetId set lastId values <SQL1結果>.tweetId
		3. 系統以http://過濾TweetData.tweets的text 欄位，執行以下SQL<SQL3>，執行結果以<SQL3結果>表示	
		  <SQL3>:	  
		  select text from TweetData.tweets a where a.text LIKE <% http://%> and tweetId <= <SQL1結果>.tweetId
		4. 系統將<SQL3結果>倒入Lucence
		5. 系統利用Lucene的正規表示式(Reqular Express)搜尋功能，取出為URL的資料， 執行結果以<Lucene結果>表示
		6. 系統將第五步驟取得資料存入URLTweet資料表
		   Update URLTweet set (URL,COUNT) values (<Lucene結果>.URL,<Lucene結果>.COUNT)
		7. 系統執行以下SQL<SQL4>，執行結果以<SQL4結果>表示，產生URL排序資料
		  <SQL4>:
		  select URLTweet.URL,URLTweet.COUNT FROM URLTweet ORDER BY COUNT		
		**/
		
		return null;
	}
	
}
