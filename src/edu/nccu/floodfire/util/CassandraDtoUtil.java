package edu.nccu.floodfire.util;

import java.io.UnsupportedEncodingException;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnParent;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.thrift.TException;

import edu.nccu.floodfire.constants.SystemConstants;
import edu.nccu.floodfire.model.twitter.TweetData;

public class CassandraDtoUtil {
	public static void setTweetDataToCassandraColumn(Cassandra.Client client,TweetData tweetData)
	{
		if(client==null ||tweetData==null)
		{
			System.out.println("input value is null!!!");
			throw new NullPointerException(); 
		}
		ColumnParent parent = new ColumnParent(
				SystemConstants.CASSANDRA_COLUMN_FAMILY_TWEETDATA);
		String tweetId = tweetData.getTweetId();
		
		
		try {
			long timestamp = System.currentTimeMillis();
			Column idColumn = new Column(BufferUtil.toByteBuffer("contributors"));
			idColumn.setValue(BufferUtil.toByteBuffer(tweetData.getContributors()));
			idColumn.setTimestamp(timestamp);
			client.insert(BufferUtil.toByteBuffer(tweetId), parent, idColumn,
					ConsistencyLevel.ONE);
			
			
			timestamp = System.currentTimeMillis();
			idColumn = new Column(BufferUtil.toByteBuffer("coordinates"));
			idColumn.setValue(BufferUtil.toByteBuffer(tweetData.getCoordinates()));
			idColumn.setTimestamp(timestamp);
			client.insert(BufferUtil.toByteBuffer(tweetId), parent, idColumn,
					ConsistencyLevel.ONE);
			
			
			timestamp = System.currentTimeMillis();
			idColumn = new Column(BufferUtil.toByteBuffer("created_at"));
			idColumn.setValue(BufferUtil.toByteBuffer(tweetData.getCreated_at()+""));
			idColumn.setTimestamp(timestamp);
			client.insert(BufferUtil.toByteBuffer(tweetId), parent, idColumn,
					ConsistencyLevel.ONE);
			
			
			timestamp = System.currentTimeMillis();
			idColumn = new Column(BufferUtil.toByteBuffer("entities"));
			idColumn.setValue(BufferUtil.toByteBuffer(tweetData.getEntities()));
			idColumn.setTimestamp(timestamp);
			client.insert(BufferUtil.toByteBuffer(tweetId), parent, idColumn,
					ConsistencyLevel.ONE);
			
			
			timestamp = System.currentTimeMillis();
			idColumn = new Column(BufferUtil.toByteBuffer("favorite_count"));
			idColumn.setValue(BufferUtil.toByteBuffer(tweetData.getFavorite_count()+""));
			idColumn.setTimestamp(timestamp);
			client.insert(BufferUtil.toByteBuffer(tweetId), parent, idColumn,
					ConsistencyLevel.ONE);
			
			timestamp = System.currentTimeMillis();
			idColumn = new Column(BufferUtil.toByteBuffer("favorited"));
			idColumn.setValue(BufferUtil.toByteBuffer(tweetData.isFavorited()+""));
			idColumn.setTimestamp(timestamp);
			client.insert(BufferUtil.toByteBuffer(tweetId), parent, idColumn,
					ConsistencyLevel.ONE);
			
			timestamp = System.currentTimeMillis();
			idColumn = new Column(BufferUtil.toByteBuffer("text"));
			idColumn.setValue(BufferUtil.toByteBuffer(tweetData.getText()));
			idColumn.setTimestamp(timestamp);
			client.insert(BufferUtil.toByteBuffer(tweetId), parent, idColumn,
					ConsistencyLevel.ONE);
			
			if(tweetData.getEvent()!=null)
			{
			    String rowKey = tweetData.getEvent() + "::" + tweetData.getCalendarDate();
				ColumnParent parentEvent = new ColumnParent(
					SystemConstants.CASSANDRA_COLUMN_FAMILY_EVENTS);
			    timestamp = System.currentTimeMillis();
				idColumn = new Column(BufferUtil.toByteBuffer(tweetData.getTweetId()));
				idColumn.setValue(BufferUtil.toByteBuffer(tweetData.getTotalTweetJson()));
				idColumn.setTimestamp(timestamp);
				client.insert(BufferUtil.toByteBuffer(rowKey), parentEvent, idColumn,
						ConsistencyLevel.ONE);
			}	
			
			
			
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (InvalidRequestException e) {

			e.printStackTrace();
		} catch (UnavailableException e) {

			e.printStackTrace();
		} catch (TimedOutException e) {

			e.printStackTrace();
		} catch (TException e) {

			e.printStackTrace();
		}
	}
	
		
	
}
