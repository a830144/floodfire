package edu.nccu.floodfire.util;

import twitter4j.Status;
import edu.nccu.floodfire.model.twitter.TweetData;

public class TwitterDtoUtil {
	public TweetData setTwitter(Status status) 
	{
		return this.setTwitter(null, status);
	}
	
	public TweetData setTwitter(String jobSeq,Status status) 
	{
		TweetData tweetData = new TweetData();
		tweetData.setTweetId(""+status.getId());
		tweetData.setContributors(""+status.getUser().getId());
		tweetData.setCoordinates("");
		tweetData.setCreated_at(status.getCreatedAt());
		tweetData.setEntities(""+status.getHashtagEntities().toString()+status.getMediaEntities().toString());
		tweetData.setFavorite_count(0);
		tweetData.setFavorited(status.isFavorited());
		tweetData.setText(status.getText());
		
		/**for event column family; there are three factor;
		 * 1:event-name 
		 * 2:current date 
		 * 3:total Tweet JSON 
		 */
		
		if (jobSeq != null) {
			tweetData.setEvent(jobSeq);
			String thisDate = DateUtil.getCurrentYYYYMMDD();
			tweetData.setCalendarDate(thisDate);
			String json = status.toString();
			tweetData.setTotalTweetJson(json);
		}
		
		return tweetData;
	}

}
