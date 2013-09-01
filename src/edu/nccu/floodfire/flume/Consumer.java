package edu.nccu.floodfire.flume;

import java.util.HashMap;
import java.util.Map;

import org.apache.flume.Event;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.event.EventBuilder;

import edu.nccu.floodfire.model.twitter.TweetData;

public class Consumer implements Runnable{
	private Store store;
	private ChannelProcessor channel;

	public Consumer(Store store,ChannelProcessor channel) {
		this.store = store;
		this.channel = channel;
	}
	
	
	public void run()
	{
		while(true)
		{
			TweetData tweetData = (TweetData)store.get();
			Map<String, String> headers = new HashMap<String, String>();

			headers.put("timestamp", System.currentTimeMillis()+"");
			Event event = EventBuilder.withBody(
					(tweetData.getText()).getBytes(),
					headers);
			channel.processEvent(event);
		}
	}

}
