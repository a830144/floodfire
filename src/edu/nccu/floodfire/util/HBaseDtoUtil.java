package edu.nccu.floodfire.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import edu.nccu.floodfire.constants.SystemConstants;
import edu.nccu.floodfire.model.twitter.TweetData;

public class HBaseDtoUtil {
	
	public static void setTweetDataToHBaseColumn(Configuration cfg,TweetData tweetData)
	{
		if(cfg==null ||tweetData==null)
		{
			System.out.println("input value is null!!!");
			throw new NullPointerException(); 
		}
		try {
			HTable table = new HTable(
					cfg, SystemConstants.HBASE_TABLE_TWEETDATA);
			String tweetId = tweetData.getTweetId();		
			Put put = new Put(Bytes.toBytes(tweetId + "::" +tweetData.getCalendarDate()));
			put.add(Bytes.toBytes("cf"), 
					Bytes.toBytes(BufferUtil.toByteBuffer("contributors")), 
					Bytes.toBytes(BufferUtil.toByteBuffer(tweetData.getContributors())));
			
			put.add(Bytes.toBytes("cf"), 
					Bytes.toBytes(BufferUtil.toByteBuffer("coordinates")), 
					Bytes.toBytes(BufferUtil.toByteBuffer(tweetData.getCoordinates())));
			
			put.add(Bytes.toBytes("cf"), 
					Bytes.toBytes(BufferUtil.toByteBuffer("created_at")), 
					Bytes.toBytes(BufferUtil.toByteBuffer(tweetData.getCreated_at()+"")));
			
			put.add(Bytes.toBytes("cf"), 
					Bytes.toBytes(BufferUtil.toByteBuffer("entities")), 
					Bytes.toBytes(BufferUtil.toByteBuffer(tweetData.getEntities())));
			
			put.add(Bytes.toBytes("cf"), 
					Bytes.toBytes(BufferUtil.toByteBuffer("favorite_count")), 
					Bytes.toBytes(BufferUtil.toByteBuffer(tweetData.getFavorite_count()+"")));
			
			put.add(Bytes.toBytes("cf"), 
					Bytes.toBytes(BufferUtil.toByteBuffer("favorited")), 
					Bytes.toBytes(BufferUtil.toByteBuffer(tweetData.isFavorited()+"")));
			
			put.add(Bytes.toBytes("cf"), 
					Bytes.toBytes(BufferUtil.toByteBuffer("text")), 
					Bytes.toBytes(BufferUtil.toByteBuffer(StringUtil.unicodeSurrogate(tweetData.getText()))));
			
			put.add(Bytes.toBytes("cf"), 
					Bytes.toBytes(BufferUtil.toByteBuffer("jobSeq")), 
					Bytes.toBytes(BufferUtil.toByteBuffer( tweetData.getEvent())));

			table.put(put);
			table.close();
			
			if(tweetData.getEvent()!=null)
			{
			    String rowKey =   tweetData.getCalendarDate() + tweetData.getEvent();
			    HTable tableEvent = new HTable(
						cfg, SystemConstants.HBASE_TABLE_EVENTS);
			    Put putEvent = new Put(Bytes.toBytes(rowKey));
			    putEvent.add(Bytes.toBytes("cf"), 
						Bytes.toBytes(BufferUtil.toByteBuffer(tweetData.getTweetId())), 
						Bytes.toBytes(BufferUtil.toByteBuffer(tweetData.getTotalTweetJson())));
			    tableEvent.put(putEvent);
			    tableEvent.close();

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

}
