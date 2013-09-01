package edu.nccu.floodfire.service;

import java.util.List;

import edu.nccu.floodfire.entity.Event;
import edu.nccu.floodfire.entity.Job;
import edu.nccu.floodfire.entity.Token;

public interface ManagementService {
	public boolean isPasswdTrue(String userId,String password);
	public String addUser(String userName,String password);
	public List<?> getUserByIdWildCard(String id);
	
	public String addJob(Job job);
	public List<?> getJobByUserId(String id);
	public String updateJobStatusById(String id,String jobStatus);
	
	public String addEvent(Event event);
	public List<?> getEventByWildCard(String eventName,String eventType);
	
	public void updateTokenStatusByID(String id,String jobSeq);
	public void updateTokenStatusByJobSeq(String jobSeq);
	public String updateTokenByExample(Token token);
	
	
	public List<?> queryAllToken();
	public String addToken(Token token);
	/**
	 * 1.為了前端使用便利性，SERVICE都用LIST當作傳遞的方式<br>
	 * 2.list資料內容<br>
	 * sublist.add(token.getTokenId());<br>
	        sublist.add(token.getTokenName());<br>
			sublist.add(token.getAccessToken());<br>
			sublist.add(token.getAccessTokenSecret());<br>
			sublist.add(token.getConsumerKey());<br>
			sublist.add(token.getConsumerSecret());<br>
			sublist.add(token.getLocked());<br>
			sublist.add(token.getJobSeq());<br>
	 */
	public List<?> getTokenByWildCard(String tokenName,String tokenId);
    public String lockRandomTokenToUse(String jobSeq);
    public String[] lockRandomTokenAndFrequencyToUse(String jobSeq);

}
