package edu.nccu.floodfire.dao;

import java.sql.Date;
import java.util.List;

import edu.nccu.floodfire.entity.Tweets;

public interface TwitterDao {
	public void removeTwitter(final int id) ;
	public List<Tweets> findTwitterById(final String Id) ;  
	public void persistTweet(final Tweets tweet);
	public List<?> getTweetsByScreenName(String screenName);
	public List<?> getTweetsByExample(Tweets tweets);
	public List<?> getTweetsByCreateDateAndScreenNameAndKeywordAndMentionPersion(Date startDate,Date endDate,String screenName,String keyword,String mentionPerson);
	public int getContributorCountByJobSeqNoAndCreateAt(String jobSeq,Date createAt);
	public int getContributorCountByJobSeqNoAndCreateAtAndKeyword(String jobSeq,Date createAt,String keyword);
	public int getTweetCountByJobSeqNoAndCreateAt(String jobSeq,Date createAt,String condition);
	public int getTweetCountByJobSeqNoAndCreateAtAndKeyword(String jobSeq,Date createAt,String condition,String keyword);
	public int getTweetCountNoDuplicateByJobSeqNoAndCreateAt(String jobSeq,Date createAt,String condition);		
	public List<?> getTweetByRetweetOrMentionCount(String jobSeq ,Date createAt,Date endAt,String retweetOrCount) ;
	public void exportData(Date startDate,Date endDate,String fileName);
}
