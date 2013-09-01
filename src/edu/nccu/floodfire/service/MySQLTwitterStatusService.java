package edu.nccu.floodfire.service;

import java.util.Date;
import java.util.List;


public interface MySQLTwitterStatusService extends TwitterStatusService {
	
	public List<?> getTweetDataByUserName(String userName);
	public List<?> getTweetsByCreateDateAndScreenNameAndKeywordAndMentionPersion(Date startDate,Date endDate,String screenName,String keyword,String mentionPerson);
	public String exportTweetData(Date startDate,Date endData,String fileName);

}
