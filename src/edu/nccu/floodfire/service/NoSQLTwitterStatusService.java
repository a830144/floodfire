package edu.nccu.floodfire.service;

import java.util.Map;

public interface NoSQLTwitterStatusService extends TwitterStatusService {
	@SuppressWarnings("rawtypes")
	public Map queryEventsByDate(String date) throws Exception;
	@SuppressWarnings("rawtypes")
	public Map queryTweetDataByDate(String date) throws Exception;
	
	

}
