package edu.nccu.floodfire.service.impl;

import java.util.Map;

import edu.nccu.floodfire.flume.Store;
import edu.nccu.floodfire.model.twitter.TweetData;
import edu.nccu.floodfire.service.FlumeTwitterStatusService;

public class FlumeTwitterStatusServiceImpl extends FlumeTwitterStatusService {

    private Store store;
	
	
	public void setStore(Store store) {
		this.store = store;
	}


	@Override
	public void insert(Object o) {
		TweetData tweetData = (TweetData) o;
        store.add(tweetData);
	}




	@SuppressWarnings("rawtypes")
	@Override
	public Map queryEventsByDate(String date) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public Map queryTweetDataByDate(String date) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
