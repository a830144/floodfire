package edu.nccu.floodfire.test.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nccu.floodfire.constants.SystemConstants;
import edu.nccu.floodfire.service.SyncDataService;

public class SyncDataTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SyncDataService syncDataService = (SyncDataService)applicationContext.getBean("syncDataService");
		syncDataService.syncDataFromNoSQLToMySQL("2013-06-11",SystemConstants.MySQL_YOURTWAPPERKEEPER);
		//hbaseTwitterStatusService.queryEventsByDate("2013-06-03");

	}

}
