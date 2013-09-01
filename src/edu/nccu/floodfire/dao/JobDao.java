package edu.nccu.floodfire.dao;

import java.util.List;

import edu.nccu.floodfire.entity.Job;

public interface JobDao {
	public void addJob(Job job);
	@SuppressWarnings("rawtypes")
	public List getJobsById(String id);
	public void updateJobStatusById(String id,String jobStatus);
	@SuppressWarnings("rawtypes")
	public List getJobsByExample(Job job);
	public void updateJobByExample(String id,Job job);
}
