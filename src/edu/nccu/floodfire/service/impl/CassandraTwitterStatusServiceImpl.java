package edu.nccu.floodfire.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnOrSuperColumn;
import org.apache.cassandra.thrift.ColumnParent;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.SlicePredicate;
import org.apache.cassandra.thrift.SliceRange;
import org.apache.cassandra.thrift.TBinaryProtocol;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import edu.nccu.floodfire.constants.SystemConstants;
import edu.nccu.floodfire.model.twitter.TweetData;
import edu.nccu.floodfire.service.CassandraTwitterStatusService;
import edu.nccu.floodfire.util.BufferUtil;
import edu.nccu.floodfire.util.CassandraDtoUtil;

public class CassandraTwitterStatusServiceImpl implements CassandraTwitterStatusService {
	 public static final String UTF8 = "UTF8";
	 private TTransport tr;
	 private TProtocol proto;
	 private Cassandra.Client client;

	 public void configure()
	 {
		tr = new TFramedTransport(new TSocket(
				SystemConstants.CASSANDRA_TFRAMETRANSPORT_IP,
				SystemConstants.CASSANDRA_TFRAMETRANSPORT_PORT));
		proto = new TBinaryProtocol(tr);
		client = new Cassandra.Client(proto);
	 }
	 
	@Override
	public void insert(Object o) {
		try {
			configure();
			tr.open();

			client.set_keyspace(SystemConstants.CASSANDRA_KEYSPACE);

			// insert data
			TweetData tweetData = (TweetData) o;
			CassandraDtoUtil.setTweetDataToCassandraColumn(client, tweetData);

			tr.close();
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		
	

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<?> query() {
		// TODO Auto-generated method stub
		
		return null;
	     
	}

	@Override
	public List<?> queryBySid(String sid) {
		try {
			configure();
			tr.open();
			client.set_keyspace(SystemConstants.CASSANDRA_KEYSPACE);
			ColumnParent parent = new ColumnParent(
					SystemConstants.CASSANDRA_COLUMN_FAMILY_TWEETDATA);
			
			SlicePredicate predicate = new SlicePredicate();
			SliceRange sliceRange = new SliceRange(BufferUtil.toByteBuffer(""),
					BufferUtil.toByteBuffer(""), false, 10);
			predicate.setSlice_range(sliceRange);
			List<ColumnOrSuperColumn> results = client.get_slice(
					BufferUtil.toByteBuffer(sid), parent, predicate, ConsistencyLevel.ONE);

			for (ColumnOrSuperColumn result : results) {
				Column column = result.column;
				System.out.println(BufferUtil.toString(column.name) + " -> "
						+ BufferUtil.toString(column.value));
			}
			tr.close();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} catch (UnavailableException e) {
			e.printStackTrace();
		} catch (TimedOutException e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}



	@SuppressWarnings("rawtypes")
	@Override
	public Map queryEventsByDate(String date) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map queryTweetDataByDate(String date) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
