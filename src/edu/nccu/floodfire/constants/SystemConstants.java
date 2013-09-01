package edu.nccu.floodfire.constants;

import java.util.HashMap;
import java.util.Map;

public class SystemConstants {
	
	public static final String CASSANDRA_KEYSPACE = "floodfire";
	
	public static final String CASSANDRA_COLUMN_FAMILY_TWEETDATA = "tweetData";
	
	public static final String CASSANDRA_COLUMN_FAMILY_EVENTS = "events";
	
	public static final String CASSANDRA_TFRAMETRANSPORT_IP = "localhost";
	
	public static final int CASSANDRA_TFRAMETRANSPORT_PORT = 9160;
	
    public static final String HBASE_TABLE_TWEETDATA = "tweetData";
	
	public static final String HBASE_TABLE_EVENTS = "events";
	
	public static final String MySQL_TWEETS = "tweets";
	
	public static final String MySQL_YOURTWAPPERKEEPER = "rawstream";
	
	@SuppressWarnings("rawtypes")
	public static final Map COLLECTTWEETCOUNT = new HashMap();
	
	@SuppressWarnings("rawtypes")
	public static final Map PREVIEW_COLLECTTWEETDATA = new HashMap();
	
	@SuppressWarnings("rawtypes")
	public static final Map PREVIEW_COLLECTTWEETDATA_FULL = new HashMap();
	
	@SuppressWarnings("rawtypes")
	public static final Map TEST_CONNECTION = new HashMap();
	
	public static String USE_DB = "MySQL";
	@SuppressWarnings("rawtypes")
	public static Map SCHEDULER_POOL = new HashMap();
	@SuppressWarnings("rawtypes")
	public static Map STREAM_POOL = new HashMap();


}
