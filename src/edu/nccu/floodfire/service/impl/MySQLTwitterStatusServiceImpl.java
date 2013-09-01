package edu.nccu.floodfire.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import edu.nccu.floodfire.dao.TwitterDao;
import edu.nccu.floodfire.entity.Tweets;
import edu.nccu.floodfire.model.twitter.TweetData;
import edu.nccu.floodfire.service.MySQLTwitterStatusService;
import edu.nccu.floodfire.util.MySQLDtoUtil;

public class MySQLTwitterStatusServiceImpl implements MySQLTwitterStatusService {
	private TwitterDao twitterDao;
	public void setTwitterDao(TwitterDao twitterDao) {
		this.twitterDao = twitterDao;
	}

	@Override
	public void insert(Object o) {
		
		TweetData tweetData = (TweetData) o;
		Tweets tweets = MySQLDtoUtil.setTweetDataToMySQLTweetsEntity(tweetData);
		tweetData = null;
		twitterDao.persistTweet(tweets);
		tweets = null;
	}

	@Override
	public void update() {


	}

	@Override
	public void delete() {


	}

	@Override
	public List<?> query() {
		return null;
	}

	@Override
	public List<?> queryBySid(String sid) {
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getTweetDataByUserName(String userName) {
		//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		//TwitterDao twitterDao = (TwitterDao)applicationContext.getBean("twitterDao");
		List list = twitterDao.getTweetsByScreenName(userName);
		List newList = new ArrayList();
		for(int i =0;i< list.size();i++)
		{
			Tweets tweets = (Tweets)list.get(i);
			List sublist = new ArrayList();
			sublist.add(tweets.getId());
			sublist.add(tweets.getUser().getScreenName());
			sublist.add(tweets.getCreatedAt());
			sublist.add(tweets.getText());
			newList.add(sublist);
		}
		return newList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<?> getTweetsByCreateDateAndScreenNameAndKeywordAndMentionPersion(
			Date startDate, Date endDate, String screenName,
			String keyword, String mentionPerson) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		java.sql.Date sqlStartDate = new java.sql.Date(cal.getTime().getTime());
		cal.setTime(endDate);
		java.sql.Date sqlEndDate = new java.sql.Date(cal.getTime().getTime());
		
		
		List list = twitterDao.getTweetsByCreateDateAndScreenNameAndKeywordAndMentionPersion(sqlStartDate, sqlEndDate, screenName, keyword, mentionPerson);
		List newList = new ArrayList();
		if(list.size() > 300)
		{
			list = list.subList(0, 300);
		}
		for(int i =0;i< list.size();i++)
		{
			List sublist = new ArrayList();
			Object[] o = (Object[])list.get(i);
			sublist.add(o[0]);
			sublist.add(o[1]);
			sublist.add(o[2]);
			sublist.add(o[3]);
			newList.add(sublist);
		}
		return newList;
	}

	@Override
	public String exportTweetData(Date startDate, Date endDate, String fileName) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		java.sql.Date sqlStartDate = new java.sql.Date(cal.getTime().getTime());
		
		cal.setTime(endDate);
		java.sql.Date sqlEndDate = new java.sql.Date(cal.getTime().getTime());
		
		twitterDao.exportData(sqlStartDate, sqlEndDate, fileName);
		return fileName;
	}

}
