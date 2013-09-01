package edu.nccu.floodfire.util;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration {
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	
	public TwitterConfiguration()
	{
		
	}
	
	public TwitterConfiguration(String consumerKey, String consumerSecret,
			String accessToken, String accessTokenSecret) {
		super();
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
	}

	public TwitterStream getTwitterStream()
	{
		ConfigurationBuilder cb = getConfigurationBuilder();
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
				.getInstance();
		return twitterStream;
	}
	
	public Twitter getTwitter()
	{
		ConfigurationBuilder cb = getConfigurationBuilder();
		Twitter twitter= new TwitterFactory(cb.build()).getInstance();
		return twitter;
	}
	
	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	private ConfigurationBuilder getConfigurationBuilder()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(consumerKey==null?"nKIM7AIZpjxEvZSU5iQnA":consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret==null?"1BiL6ARMLT5GV7ubJOcDkDMyU9S130z5cEOEqOAQ":consumerSecret);
		cb.setOAuthAccessToken(accessToken==null?"1006162200-etpJhjK66JoeuvGisE3GwMZx36iLsXnPbXyOH0f":accessToken);
		cb.setOAuthAccessTokenSecret(accessTokenSecret==null?"G8RocgIYJSCoAkXaqSzUS3dDzFpZh3CqM9LRH8Odc":accessTokenSecret);
		return cb;
	}

}
