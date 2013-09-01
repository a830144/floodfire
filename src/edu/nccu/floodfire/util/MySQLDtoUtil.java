package edu.nccu.floodfire.util;

import edu.nccu.floodfire.entity.Tweets;
import edu.nccu.floodfire.entity.Users;
import edu.nccu.floodfire.model.twitter.TweetData;

public class MySQLDtoUtil {
	
	public static Tweets setTweetDataToMySQLTweetsEntity(TweetData tweetData)
	{
		/**
		 * 應該用beanUtil.copyBeans,時間太趕,硬作
		 */
		Tweets tweets = new Tweets();
		Users users = new Users();
		if(tweetData==null)
		{
			System.out.println("input value is null!!!");
			throw new NullPointerException(); 
		}
		
		tweets.setContributors(tweetData.getContributors());
		tweets.setCoordinates(tweetData.getCoordinates());
		tweets.setCreatedAt(new java.sql.Timestamp((tweetData.getCreated_at()).getTime()));
		tweets.setCurrentUserRetweet(tweetData.getCurrent_user_retweet());
		tweets.setFavoriteCount(""+tweetData.getFavorite_count());
		tweets.setFavorited(tweetData.getFavorite_count()>0?"true":"false");
		tweets.setId(tweetData.getTweetId());
		tweets.setTimestamp(DateUtil.getCurrentYYYYMMDDHHmmss());
		tweets.setLang(tweetData.getLang());
		tweets.setRetweetCount(""+tweetData.getRetweet_count());
		tweets.setText(StringUtil.unicodeSurrogate(tweetData.getText()));
		tweets.setJobSeq( tweetData.getEvent());
		users.setId(tweetData.getContributors());
		tweets.setUser(users);
		return tweets;
		
	}

}
