package edu.nccu.floodfire.service;

import java.util.List;

public interface TwitterStatusService {
	public void insert(Object o);
	public void update();
	public void delete();
	public List<?> query();
	public List<?> queryBySid(String sid);

}
