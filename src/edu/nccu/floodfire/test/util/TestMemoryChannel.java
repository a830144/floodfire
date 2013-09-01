package edu.nccu.floodfire.test.util;
import java.util.HashMap;
import java.util.Map;

import org.apache.flume.Channel;
import org.apache.flume.ChannelException;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.channel.MemoryChannel;
import org.apache.flume.conf.Configurables;
import org.apache.flume.event.EventBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMemoryChannel {
	private Channel channel;

	  @Before
	  public void setUp() {
	    channel = new MemoryChannel();
	  }

	  @Test
	  public void testPutTake() throws InterruptedException, EventDeliveryException {
	    Event event = EventBuilder.withBody("test event".getBytes());
	    Context context = new Context();

	    Configurables.configure(channel, context);

	    Transaction transaction = channel.getTransaction();
	    Assert.assertNotNull(transaction);

	    transaction.begin();
	    channel.put(event);
	    transaction.commit();
	    transaction.close();

	    transaction = channel.getTransaction();
	    Assert.assertNotNull(transaction);

	    transaction.begin();
	    Event event2 = channel.take();
	    Assert.assertEquals(event, event2);
	    transaction.commit();
	  }

	  @Test
	  public void testChannelResize() {
	    Context context = new Context();
	    Map<String, String> parms = new HashMap<String, String>();
	    parms.put("capacity", "5");
	    parms.put("transactionCapacity", "5");
	    context.putAll(parms);
	    Configurables.configure(channel,  context);

	    Transaction transaction = channel.getTransaction();
	    transaction.begin();
	    for(int i=0; i < 5; i++) {
	      channel.put(EventBuilder.withBody(String.format("test event %d", i).getBytes()));
	    }
	    transaction.commit();
	    transaction.close();

	    /*
	     * Verify overflow semantics
	     */
	    transaction = channel.getTransaction();
	    boolean overflowed = false;
	    try {
	      transaction.begin();
	      channel.put(EventBuilder.withBody("overflow event".getBytes()));
	      transaction.commit();
	    } catch (ChannelException e) {
	      overflowed = true;
	      transaction.rollback();
	    } finally {
	      transaction.close();
	    }
	    Assert.assertTrue(overflowed);

	    /*
	     * Reconfigure capacity down and add another event, shouldn't result in exception
	     */
	    parms.put("capacity", "6");
	    context.putAll(parms);
	    Configurables.configure(channel, context);
	    transaction = channel.getTransaction();
	    transaction.begin();
	    channel.put(EventBuilder.withBody("extended capacity event".getBytes()));
	    transaction.commit();
	    transaction.close();

	    /*
	     * Attempt to reconfigure capacity to below current entry count and verify
	     * it wasn't carried out
	     */
	    parms.put("capacity", "2");
	    parms.put("transactionCapacity", "2");
	    context.putAll(parms);
	    Configurables.configure(channel, context);
	    for(int i=0; i < 6; i++) {
	      transaction = channel.getTransaction();
	      transaction.begin();
	      Assert.assertNotNull(channel.take());
	      transaction.commit();
	      transaction.close();
	    }
	  }

	  @Test(expected=ChannelException.class)
	  public void testTransactionPutCapacityOverload() {
	    Context context = new Context();
	    Map<String, String> parms = new HashMap<String, String>();
	    parms.put("capacity", "5");
	    parms.put("transactionCapacity", "2");
	    context.putAll(parms);
	    Configurables.configure(channel,  context);

	    Transaction transaction = channel.getTransaction();
	    transaction.begin();
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    // shouldn't be able to fit a third in the buffer
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    Assert.fail();
	  }

	  @Test(expected=ChannelException.class)
	  public void testCapacityOverload() {
	    Context context = new Context();
	    Map<String, String> parms = new HashMap<String, String>();
	    parms.put("capacity", "5");
	    parms.put("transactionCapacity", "3");
	    context.putAll(parms);
	    Configurables.configure(channel,  context);

	    Transaction transaction = channel.getTransaction();
	    transaction.begin();
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    transaction.commit();
	    transaction.close();

	    transaction = channel.getTransaction();
	    transaction.begin();
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    // this should kill  it
	    transaction.commit();
	    Assert.fail();
	  }

	  @Test
	  public void testBufferEmptyingAfterTakeCommit() {
	    Context context = new Context();
	    Map<String, String> parms = new HashMap<String, String>();
	    parms.put("capacity", "3");
	    parms.put("transactionCapacity", "3");
	    context.putAll(parms);
	    Configurables.configure(channel,  context);

	    Transaction tx = channel.getTransaction();
	    tx.begin();
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    tx.commit();
	    tx.close();

	    tx = channel.getTransaction();
	    tx.begin();
	    channel.take();
	    channel.take();
	    tx.commit();
	    tx.close();

	    tx = channel.getTransaction();
	    tx.begin();
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    tx.commit();
	    tx.close();
	  }

	  @Test
	  public void testBufferEmptyingAfterRollback() {
	    Context context = new Context();
	    Map<String, String> parms = new HashMap<String, String>();
	    parms.put("capacity", "3");
	    parms.put("transactionCapacity", "3");
	    context.putAll(parms);
	    Configurables.configure(channel,  context);

	    Transaction tx = channel.getTransaction();
	    tx.begin();
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    tx.rollback();
	    tx.close();

	    tx = channel.getTransaction();
	    tx.begin();
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    channel.put(EventBuilder.withBody("test".getBytes()));
	    tx.commit();
	    tx.close();
	  }
}
