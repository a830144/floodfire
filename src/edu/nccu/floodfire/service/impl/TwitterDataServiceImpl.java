package edu.nccu.floodfire.service.impl;

import edu.nccu.floodfire.service.TwitterDataService;
import edu.nccu.floodfire.util.TwitterConfiguration;
import edu.nccu.floodfire.util.TwitterSearchRec;
import edu.nccu.floodfire.util.TwitterStreamRec;

public class TwitterDataServiceImpl implements TwitterDataService {

    public TwitterStreamRec getTwitterStreamRec() {
		return twitterStreamRec;
	}
	public TwitterSearchRec getTwitterSearchRec() {
		return twitterSearchRec;
	}

	private TwitterStreamRec twitterStreamRec;
    private TwitterSearchRec twitterSearchRec;
	public void setTwitterStreamRec(TwitterStreamRec twitterStreamRec) {
		this.twitterStreamRec = twitterStreamRec;
	}	
	public void setTwitterSearchRec(TwitterSearchRec twitterSearchRec) {
		this.twitterSearchRec = twitterSearchRec;
	}


	@Override
	public void getTweetDataBySearch(String keyword) {
		twitterSearchRec.getTweetData(keyword);
		
	}
	@Override
	public void getTweetDataBySearch(String keyword,TwitterConfiguration twitterConfiguration) {
		twitterSearchRec.getTweetData(keyword,twitterConfiguration);
		
	}
	
	@Override
	public void getTweetDataBySearch(String keyword,TwitterConfiguration twitterConfiguration,boolean insert) {
		twitterSearchRec.getTweetData(keyword,twitterConfiguration,insert);
		
	}
	
	@Override
	public void stopStream(String eventName) {
		twitterStreamRec.stop(eventName);
		
	}
	
	@Override
	public void getTweetDataByStreamFilter(String keyword) {
		twitterStreamRec.getDataByFilter(keyword,"keyword");
	}

	@Override
	public void getTweetDataByStreamFilter(String keyword,
			TwitterConfiguration twitterConfiguration) {
		twitterStreamRec.getDataByFilter(keyword,"keyword",twitterConfiguration);
		
	}
	@Override
	public void getTweetDataByStreamFilter(String keyword,
			TwitterConfiguration twitterConfiguration, boolean insert) {
		twitterStreamRec.getDataByFilter(keyword,"keyword",twitterConfiguration,insert);
	}

}
