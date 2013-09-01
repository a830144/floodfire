package edu.nccu.floodfire.service;

import edu.nccu.floodfire.util.TwitterConfiguration;


public interface TwitterDataService {
	
	
	public void getTweetDataByStreamFilter(String keyword);
	
	public void getTweetDataByStreamFilter(String keyword,TwitterConfiguration twitterConfiguration);
	
	public void getTweetDataByStreamFilter(String keyword,TwitterConfiguration twitterConfiguration,boolean insert);		
	
	public void getTweetDataBySearch(String keyword);
	
	public void getTweetDataBySearch(String keyword,TwitterConfiguration twitterConfiguration);
	
	public void getTweetDataBySearch(String keyword,TwitterConfiguration twitterConfiguration,boolean insert);
	
	public void stopStream(String eventName);

}
