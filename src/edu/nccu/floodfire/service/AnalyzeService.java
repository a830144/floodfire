package edu.nccu.floodfire.service;

import java.util.Date;
import java.util.List;
@SuppressWarnings("rawtypes")
public interface AnalyzeService {
	public List<String> getAnalyzeByUserString(Date startDate,Date endDate,String jobSeq);	
	public List getAnalyzeRetweetCount(Date startDate,Date endDate,String jobSeq);
	public List getAnalyzeMentionCount(Date startDate,Date endDate,String jobSeq);
	public List<String> getAnalyzeByDataString(Date startDate,Date endDate,String jobSeq,String keyword);
	public List<String> getTrendByJobSeq(String startDate,String endDate,String jobSeq);
	public List getURLCount(String startDate,String endDate,String jobSeq);


}
