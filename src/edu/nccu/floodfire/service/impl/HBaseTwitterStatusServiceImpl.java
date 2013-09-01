package edu.nccu.floodfire.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import edu.nccu.floodfire.model.twitter.TweetData;
import edu.nccu.floodfire.service.HBaseTwitterStatusService;
import edu.nccu.floodfire.util.HBaseDtoUtil;

public class HBaseTwitterStatusServiceImpl implements HBaseTwitterStatusService {
	
	private Configuration cfg ;
	
	public void configure()
	{		
		cfg = HBaseConfiguration.create();
		cfg.set("hbase.zookeeper.quorum","192.168.83.128");
	}

	@Override
	public void insert(Object o) {
		configure();		
		TweetData tweetData = (TweetData) o;
		HBaseDtoUtil.setTweetDataToHBaseColumn(cfg, tweetData);					
	}

	@Override
	public void update() {

	}

	@Override
	public void delete() {

	}

	@Override
	public List<?> query() {
		return null;
	}

	@Override
	public List<?> queryBySid(String sid) {
		configure();
		Result result = null;
		try {
			HTable table = new HTable(cfg, "events");
			Get get = new Get(Bytes.toBytes("1")); 
			result = table.get(get);
			for(KeyValue kv :result.list()){
			  System.out.println("family:" +Bytes.toString(kv.getFamily()));
			  System.out.println("qualifier:" +Bytes.toString(kv.getQualifier()));
			  System.out.println("value:" +Bytes.toString(kv.getValue()));
			  System.out.println("Timestamp:" +kv.getTimestamp());
			}
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.list();
	}
	
	/**
	 * ;each element in map include key(qualifier) and value(column value)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map queryEventsByDate(String date) throws Exception {
		  configure();  
		  Scan scan = new Scan();  
		  SubstringComparator comp = new SubstringComparator(date);  
		  RowFilter filter = new RowFilter(CompareOp.EQUAL, comp);  
		  scan.setFilter(filter);  
		  scan.setCaching(200);  
		  scan.setCacheBlocks(false);  
		  HTable hTable = new HTable(cfg, "events");  
		  ResultScanner scanner = hTable.getScanner(scan);  
		  Map subMap = new HashMap();
		  List indexList = new ArrayList();
		  for (Result result : scanner) {  		      		       
		       for(KeyValue kv :result.list()){
					  indexList.add(kv.getQualifier());
					  subMap.put(kv.getQualifier(), kv.getValue());
					  subMap.put("index", indexList);
			   }	       
		  }  
		  hTable.close();
          return subMap; 
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map queryTweetDataByDate(String date) throws Exception {
		  configure();  
		  Scan scan = new Scan();  
		  SubstringComparator comp = new SubstringComparator("::"+date);  
		  RowFilter filter = new RowFilter(CompareOp.EQUAL, comp);  
		  scan.setFilter(filter);  
		  scan.setCaching(200);  
		  scan.setCacheBlocks(false);  
		  HTable hTable = new HTable(cfg, "tweetData");  
		  ResultScanner scanner = hTable.getScanner(scan); 
		  Map resultMap = new HashMap();
		  List indexList = new ArrayList();
		  Map subMap = null;
		  List subIndexList = null;
		  for (Result result : scanner) {  		    
			   String rowKey = Bytes.toString(result.getRow());
			   String tweetId = rowKey.substring(0, rowKey.indexOf(":"));
		       for(KeyValue kv :result.list()){
		    	      subMap = new HashMap();
		    	      subIndexList = new ArrayList();
		 		      subIndexList.add(Bytes.toString(kv.getQualifier()));
					  subMap.put(Bytes.toString(kv.getQualifier()), Bytes.toString(kv.getValue()));
					  subMap.put("subIndex", subIndexList);
			   }
		       indexList.add(tweetId);
		       resultMap.put(tweetId, subMap);
		  }
		  hTable.close();
		  resultMap.put("index", indexList);
        return resultMap; 
	}

	@Override
	public ResultScanner queryTweetDataScannerByDate(String date)
			throws Exception {
		  configure();  
		  Scan scan = new Scan();
		  SubstringComparator comp;
		  if(date == null || "".equals(date))
		  {
		      comp = new SubstringComparator("::"+date);
		  }
		  else
		  {
			  comp = new SubstringComparator("::");
		  }
		  RowFilter filter = new RowFilter(CompareOp.EQUAL, comp);  
		  scan.setFilter(filter);
		  scan.setBatch(8);

		  HTable hTable = new HTable(cfg, "tweetData");  
		  ResultScanner scanner = hTable.getScanner(scan); 
		  hTable.close();
          return scanner;
		  
	}
	@Override
	public int queryCountByJobSeq(String jobSeq) {
		configure();
		Result result = null;
		try {
			HTable table = new HTable(cfg, "events");
			Get get = new Get(Bytes.toBytes(jobSeq)); 
			//Get get = new Get(Bytes.toBytes("2013-07-20?queryType=search&keyword=H7N9")); 
			result = table.get(get);
			table.close();
			System.out.println(result.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.size();
	}
	
	public static void main(String args[])
	{
		HBaseTwitterStatusServiceImpl hb = new HBaseTwitterStatusServiceImpl();
		hb.queryCountByJobSeq("");
	}
}
