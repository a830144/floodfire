package edu.nccu.floodfire.service;

public interface SyncDataService {
	
	public void syncDataFromNoSQLToMySQL(String syncDate,String destination);

}
