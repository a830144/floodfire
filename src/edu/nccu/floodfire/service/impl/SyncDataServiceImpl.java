package edu.nccu.floodfire.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;

import edu.nccu.floodfire.constants.SystemConstants;
import edu.nccu.floodfire.dao.TwitterDao;
import edu.nccu.floodfire.dao.YourtwapperkeeperDao;
import edu.nccu.floodfire.entity.Rawstream;
import edu.nccu.floodfire.entity.Tweets;
import edu.nccu.floodfire.entity.Users;
import edu.nccu.floodfire.service.HBaseTwitterStatusService;
import edu.nccu.floodfire.service.SyncDataService;
import edu.nccu.floodfire.util.DateUtil;

public class SyncDataServiceImpl implements SyncDataService {
	private TwitterDao twitterDao;
	private HBaseTwitterStatusService hBaseTwitterStatusService;
	private YourtwapperkeeperDao yourtwapperkeeperDao;

	public void setYourtwapperkeeperDao(YourtwapperkeeperDao yourtwapperkeeperDao) {
		this.yourtwapperkeeperDao = yourtwapperkeeperDao;
	}

	public void setTwitterDao(TwitterDao twitterDao) {
		this.twitterDao = twitterDao;
	}

	public void sethBaseTwitterStatusService(
			HBaseTwitterStatusService hBaseTwitterStatusService) {
		this.hBaseTwitterStatusService = hBaseTwitterStatusService;
	}

	private int count;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void syncDataFromNoSQLToMySQL(String syncDate,String destination) {
		// TODO Auto-generated method stub
		ResultScanner resultScanner = null;
		try {
			resultScanner = hBaseTwitterStatusService.queryTweetDataScannerByDate(syncDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Iterator<Result> resultIterator = resultScanner.iterator();
        count = 0;
        Map map = new HashMap();
		if (SystemConstants.MySQL_TWEETS.equals(destination)) {
			Tweets tweets = new Tweets();
			Users users = new Users();
			storeData(resultIterator, map, tweets, users);
		}
        else if(SystemConstants.MySQL_YOURTWAPPERKEEPER.equals(destination))
        {
        	Rawstream rawstream = new Rawstream();
        	storeDataToYourtwrapperkeeperRawstream(resultIterator, map, rawstream);
        }
		resultScanner.close();

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void storeData(Iterator<Result> resultIterator,Map map,Tweets tweets,Users users) {
		
		try {
			while (resultIterator.hasNext()) {

				Result result = resultIterator.next();

				String rowKey = Bytes.toString(result.getRow());
				String tweetId = rowKey.substring(0, rowKey.indexOf(":"));
				
				for (KeyValue kv : result.list()) {
					map.put(Bytes.toString(kv.getQualifier()),
							Bytes.toString(kv.getValue()));

				}
				
				tweets.setId(tweetId);
				tweets.setTimestamp(DateUtil.getCurrentYYYYMMDDHHmmss());
				tweets.setContributors("" + map.get("contributors"));
				tweets.setCoordinates("" + map.get("coordinates"));
				tweets.setCreatedAt((Timestamp)map.get("created_at"));
				tweets.setFavoriteCount("" + map.get("favorite_count"));
				tweets.setFavorited("" + map.get("favorited"));
				tweets.setText("" + map.get("text"));
				tweets.setJobSeq("" + map.get("jobSeq"));
				
				if ("".equals(map.get("contributors"))
						|| map.get("contributors") == null) {
					users.setId("unKnow user");
				} else {
					users.setId("" + map.get("contributors"));
				}

				tweets.setUser(users);

				twitterDao.persistTweet(tweets);
				count++;
				System.out.println("sync data::"+count);
				System.gc();
			}
		} catch (Exception e) {
			System.out.println("find error!!!");
			e.printStackTrace();
			System.gc();
			storeData(resultIterator,map,tweets,users);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void storeDataToYourtwrapperkeeperRawstream(Iterator<Result> resultIterator,Map map,Rawstream rawstream) {
		
		try {
			while (resultIterator.hasNext()) {

				Result result = resultIterator.next();

				String rowKey = Bytes.toString(result.getRow());
				String tweetId = rowKey.substring(0, rowKey.indexOf(":"));
				
				for (KeyValue kv : result.list()) {
					map.put(Bytes.toString(kv.getQualifier()),
							Bytes.toString(kv.getValue()));

				}
				rawstream.setId(tweetId);
				rawstream.setCreatedAt("" + map.get("created_at"));
				rawstream.setFlag("");
				rawstream.setFromUser("" + map.get("contributors"));
				rawstream.setFromUserId("" + map.get("contributors"));
				rawstream.setGeoCoordinates0(0);
				rawstream.setGeoCoordinates1(0);
				rawstream.setGeoType("");
				rawstream.setIsoLanguageCode("");
				rawstream.setProfileImageUrl("");
				rawstream.setSource("");
				rawstream.setText("" + map.get("text"));
				rawstream.setTime(0);
				rawstream.setToUserId("");


				yourtwapperkeeperDao.persistRawstream(rawstream);
				count++;
				System.out.println("sync data::"+count);
				System.gc();
			}
		} catch (Exception e) {
			System.out.println("find error!!!");
			e.printStackTrace();
			System.gc();
			storeDataToYourtwrapperkeeperRawstream(resultIterator,map,rawstream);
		}
	}
	
	

}
