package edu.nccu.floodfire.flume.source;

import org.apache.flume.Context;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.conf.Configurable;
import org.apache.flume.source.AbstractSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.flume.Consumer;
import edu.nccu.floodfire.flume.Store;
import edu.nccu.floodfire.service.TwitterDataService;
import edu.nccu.floodfire.service.impl.FlumeTwitterStatusServiceImpl;
import edu.nccu.floodfire.service.impl.TwitterDataServiceImpl;

public class FlumeTwitterSource extends AbstractSource implements EventDrivenSource, Configurable{

	@Override
	public void start() {
		Store store = new Store();
		ChannelProcessor channel = getChannelProcessor();
		(new Thread(new Consumer(store,channel))).start();
		
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		TwitterDataService twitterDataService = (TwitterDataService) applicationContext
				.getBean("twitterDataService");
		
		if(twitterDataService instanceof TwitterDataServiceImpl)
		{
		     TwitterDataServiceImpl tl = (TwitterDataServiceImpl)twitterDataService;
		     if(tl.getTwitterStreamRec().getNoSQLTwitterStatusService() instanceof FlumeTwitterStatusServiceImpl)
		     {
		    	 FlumeTwitterStatusServiceImpl flumeTwitterStatusServiceImpl =
		    		 (FlumeTwitterStatusServiceImpl)tl.getTwitterStreamRec().getNoSQLTwitterStatusService();
		    	 flumeTwitterStatusServiceImpl.setStore(store);
		     }
		     if(tl.getTwitterSearchRec().getNoSQLTwitterStatusService() instanceof FlumeTwitterStatusServiceImpl)
		     {
		    	 FlumeTwitterStatusServiceImpl flumeTwitterStatusServiceImpl =
		    		 (FlumeTwitterStatusServiceImpl)tl.getTwitterSearchRec().getNoSQLTwitterStatusService();
		    	 flumeTwitterStatusServiceImpl.setStore(store);
		     }
		}
		twitterDataService.getTweetDataBySearch("app");
		
	}


	@Override
	public void configure(Context arg0) {
		
	}
	
	
}
