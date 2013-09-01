package edu.nccu.floodfire.dao;

import java.util.List;

import edu.nccu.floodfire.entity.Event;
@SuppressWarnings("rawtypes")
public interface EventDao {
	public void addEvent(Event event);	
	public List getEventsByExample(Event event);
	public List getEventsAndJobsByExample(Event event);
}
