package edu.nccu.floodfire.service;

import org.apache.hadoop.hbase.client.ResultScanner;


public interface HBaseTwitterStatusService extends NoSQLTwitterStatusService {
	public ResultScanner queryTweetDataScannerByDate(String date) throws  Exception;
	public int queryCountByJobSeq(String jobSeq);
}
