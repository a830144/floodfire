package edu.nccu.floodfire.test.service;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.service.HBaseTwitterStatusService;
public class HBaseTwitterStatusServiceTest {
	static Configuration cfg = null;
    static {
        //Configuration HBASE_CONFIG = new Configuration();
       // HBASE_CONFIG.set("hbase.zookeeper.quorum", "192.168.50.216");
        //HBASE_CONFIG.set("hbase.zookeeper.property.clientPort", "2181");
        cfg = HBaseConfiguration.create();
    }
    
    public static void creatTable(String tablename) throws Exception {
    	HBaseAdmin admin = new HBaseAdmin(cfg);
    	HTableDescriptor desc = new HTableDescriptor(tablename);
    	desc.addFamily(new HColumnDescriptor("article"));
    	desc.addFamily(new HColumnDescriptor("author"));
    	admin.createTable(desc );
    	
    	
        
    
         
    }

    public static void addData (String tablename) throws Exception{
    	HTable table = new HTable(cfg, tablename);
    	Put put = new Put(Bytes.toBytes("1"));
    	put.add(Bytes.toBytes("article"), Bytes.toBytes("title"), Bytes.toBytes("Head First HBase"));
    	put.add(Bytes.toBytes("article"), Bytes.toBytes("content"), Bytes.toBytes("HBase is the Hadoop database. Use it when you need random, realtime read/write access to your Big Data."));
    	put.add(Bytes.toBytes("article"), Bytes.toBytes("tags"), Bytes.toBytes("Hadoop,HBase,NoSQL"));
    	put.add(Bytes.toBytes("author"), Bytes.toBytes("name"), Bytes.toBytes("hujinjun"));
    	put.add(Bytes.toBytes("author"), Bytes.toBytes("nickname"), Bytes.toBytes("一叶渡江"));
    	table.put(put);
    	
    	
        
   }
   
    public static void getAllData (String tablename) throws Exception{
    	HTable table = new HTable(cfg, tablename);
    	Scan scan = new Scan();
    	ResultScanner rs =null;
    	try {
    	  rs = table.getScanner(scan);
    	  for (Result r : rs) {
    	    for(KeyValue kv :r.list()){
    	      System.out.println("family:" +Bytes.toString(kv.getFamily()));
    	      System.out.println("qualifier:" +Bytes.toString(kv.getQualifier()));
    	    System.out.println("value:" +Bytes.toString(kv.getValue()));
    	    }
    	  }
    	} finally {
    	  rs.close();
    	}
   }
    
    
	/**
	 * @param args
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
			HBaseTwitterStatusService hbaseTwitterStatusService = (HBaseTwitterStatusService)applicationContext.getBean("hbaseTwitterStatusService");

			//hbaseTwitterStatusService.queryEventsByDate("2013-06-03");
			Map map = hbaseTwitterStatusService.queryTweetDataByDate("2013-06-09");
			List list = (List)map.get("index");
			for(int i = 0;i<list.size();i++)
			{
				String key = (String)list.get(i);
				String value = (String)map.get(key);
				//System.out.println("key::"+key+ ";value::"+value);
			}
			//hbaseTwitterStatusService.queryBySid("");
        } 
    catch (Exception e) {
        e.printStackTrace();
    }
    
	}

}
