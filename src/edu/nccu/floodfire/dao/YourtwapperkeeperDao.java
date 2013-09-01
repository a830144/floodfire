package edu.nccu.floodfire.dao;

import edu.nccu.floodfire.entity.Archive;
import edu.nccu.floodfire.entity.Rawstream;

public interface YourtwapperkeeperDao {
	
	public void removeArchive(int id);
	public void persistArchive(Archive archive); 
	public void persistRawstream(Rawstream rawstream); 

}
