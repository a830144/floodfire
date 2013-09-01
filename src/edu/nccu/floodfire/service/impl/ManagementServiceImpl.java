package edu.nccu.floodfire.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.nccu.floodfire.dao.EventDao;
import edu.nccu.floodfire.dao.JobDao;
import edu.nccu.floodfire.dao.TokenDao;
import edu.nccu.floodfire.dao.UserDao;
import edu.nccu.floodfire.entity.Event;
import edu.nccu.floodfire.entity.Job;
import edu.nccu.floodfire.entity.Token;
import edu.nccu.floodfire.entity.User;
import edu.nccu.floodfire.service.ManagementService;

public class ManagementServiceImpl implements ManagementService {

	private UserDao userDao;
	private JobDao jobDao;
	private EventDao eventDao;
	public TokenDao getTokenDao() {
		return tokenDao;
	}
	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}
	private TokenDao tokenDao;
	public EventDao getEventDao() {
		return eventDao;
	}
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	public JobDao getJobDao() {
		return jobDao;
	}
	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public boolean isPasswdTrue(String userId, String password) {
		return userDao.isPasswdTrue(userId, password);
	}
	@Override
	public String addUser(String userName, String password) {
		if(userDao.getUserById(userName) != null)
		{
			return "帳號已存在";
		}
		else
		{
			userDao.addUser(userName, password);
			return "已新增帳號::"+ userName;
		}
	}
	@Override
	public List<List<String>> getUserByIdWildCard(String id) {
		List<List<String>> newList = new ArrayList<List<String>>();
		List<?> list = userDao.getUserByIdWildCard(id);
		for(int i =0;i<list.size();i++)
		{
			User user = (User)list.get(i);
			List<String> sublist = new ArrayList<String>();
			sublist.add(user.getIduser());
			sublist.add(user.getPassword());
			newList.add(sublist);
		}
		return newList;
	}
	@Override
	public String addJob(Job job) {
		jobDao.addJob(job);
		return "已新增job::"+job.getJob_Seq();
	}
	@Override
	/**
	 *      sublist.add(job.getEvent().getEventName());
			sublist.add(job.getJob_Seq());
			sublist.add("1".equals(job.getQueryType())?"SEARCH":"STREAM");
			sublist.add("byKeyword".equals(job.getQueryFunction())?"關鍵字":job.getQueryFunction());
			sublist.add(job.getStartTime());
			sublist.add(job.getStopTime());
			sublist.add(job.getCreateId());	
			sublist.add(job.getStoreDataSum());
			sublist.add("1".equals(job.getJobStatus())?"執行中":"已停止");		
	 */
	public List<?> getJobByUserId(String id) {
		List<List<String>> newList = new ArrayList<List<String>>();
		List<?> list = jobDao.getJobsById(id);
		for(int i =0;i<list.size();i++)
		{
			Job job = (Job)list.get(i);
			List<String> sublist = new ArrayList<String>();
			sublist.add(job.getEvent().getEventName());
			sublist.add(job.getJob_Seq());
			sublist.add("1".equals(job.getQueryType())?"SEARCH":"STREAM");
			sublist.add("byKeyword".equals(job.getQueryFunction())?"關鍵字:"+job.getKeyword():job.getQueryFunction());
			sublist.add(job.getStartTime());
			sublist.add(job.getStopTime());
			sublist.add(job.getCreateId());	
			sublist.add(job.getStoreDataSum());
			sublist.add("1".equals(job.getJobStatus())?"執行中":"已停止");			
			newList.add(sublist);
		}
		return newList;
	}
	@Override
	public String addEvent(Event event) {
		if(eventDao.getEventsByExample(event).size()!= 0)
		{
			return "事件已存在";
		}
		else
		{
			eventDao.addEvent(event);
			return "已新增事件::"+ event.getEventName();
		}
	}
	@Override
	public List<?> getEventByWildCard(String eventName, String eventType) {
		List<List<String>> newList = new ArrayList<List<String>>();
		Event event = new Event();
		event.setEventName(eventName);
		event.setEventType(eventType);
		List<?> list = eventDao.getEventsByExample(event);
		for(int i =0;i<list.size();i++)
		{
			event = (Event)list.get(i);
			List<String> sublist = new ArrayList<String>();
			sublist.add(event.getEventName());
			sublist.add(event.getEventType());
			Job job = new Job();
			job.setEvent(event);
			List<?> jobList = jobDao.getJobsByExample(job);
			String keywords = getTransKeywordsFromJob(jobList);
			sublist.add(keywords);
			newList.add(sublist);
		}
		return newList;
	}
	
	public String getTransKeywordsFromJob(@SuppressWarnings("rawtypes") List list)
	{
		String keywords="";
		for(int i =0;i<list.size();i++)
		{
			Job job =(Job)list.get(i);
			String queryType = "";
			if("1".equals(job.getQueryType()))
			{
			   queryType = "search";
			}
			else
			{
				queryType = "stream";
			}
			String keyword = job.getKeyword()+"("+ queryType+")";
			keywords +=keyword +",";
		}
		return keywords;
	}
	
	@Override
	public String updateJobStatusById(String id, String jobStatus) {
		//jobDao.updateJobStatusById(id, jobStatus);
		Job job = new Job();
		job.setJobStatus(jobStatus);
		job.setStopTime(""+new Date());
		jobDao.updateJobByExample(id, job);
		return "工作序號 :: 『" + id + "』已成功停止，狀態變更為" + ("1".equals(jobStatus) ? "啟動中":"工作停止完成")  ;
	}
	@Override
	public void updateTokenStatusByID(String id,String jobSeq) {
		tokenDao.updateTokenStatusById(id,jobSeq);
		
	}
	@Override
	public void updateTokenStatusByJobSeq(String jobSeq) {
		tokenDao.updateTokenStatusByJobSeq(jobSeq);
		
	}
	@Override
	public String updateTokenByExample(Token token){
		if(token.getTokenId()!=null&& !"".equals(token.getTokenId()))
		{
		    tokenDao.updateTokenByIdExample(token.getTokenId(), token);
		}
		return "更新成功";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<?> queryAllToken() {
		
		List list =  tokenDao.getTokensList();
		List newList = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			List sublist = new ArrayList();
			Object[] o = (Object[])list.get(i);
			sublist.add(o[0]);
			sublist.add(o[1]);
			sublist.add(o[2]);
			newList.add(sublist);
		}
		return newList;
		
	}
	@Override
	public String addToken(Token token) {
		if(tokenDao.getTokenById(token.getTokenId()) != null)
		{
			return "Token已存在";
		}
		else
		{
			tokenDao.addToken(token);
			return "已新增Token::"+ token.getTokenName();
		}
	}
	/**
	 * 1.為了前端使用便利性，SERVICE都用LIST當作傳遞的方式
	 * 2.list資料內容
	 *      sublist.add(token.getTokenId());
	        sublist.add(token.getTokenName());
			sublist.add(token.getAccessToken());
			sublist.add(token.getAccessTokenSecret());
			sublist.add(token.getConsumerKey());
			sublist.add(token.getConsumerSecret());
			sublist.add(token.getLocked());
			sublist.add(token.getJobSeq());
			sublist.add(token.getFrequency());
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<?> getTokenByWildCard(String tokenName,String tokenId) {
		List<List<String>> newList = new ArrayList<List<String>>();
		Token token = new Token();
		if(tokenName!=null && !"".equals(tokenName))
		{
				token.setTokenName(tokenName);
		}
		if(tokenId!=null && !"".equals(tokenId))
		{
				token.setTokenId(tokenId);
		}
		
		List list = tokenDao.getTokensByExample(token);
		for(int i =0;i<list.size();i++)
		{
			token = (Token)list.get(i);
			List<String> sublist = new ArrayList<String>();
			sublist.add(token.getTokenId());
			sublist.add(token.getTokenName());
			sublist.add(token.getAccessToken());
			sublist.add(token.getAccessTokenSecret());
			sublist.add(token.getConsumerKey());
			sublist.add(token.getConsumerSecret());
			sublist.add(token.getLocked());
			sublist.add(token.getJobSeq());
			sublist.add(token.getFrequency());
			newList.add(sublist);
		}
		return newList;
	}
	@Override
	public String lockRandomTokenToUse(String jobSeq) {
		
		Token token = new Token();
		token.setLocked("0");
		List<?> list =  tokenDao.getTokensByExample(token);
		token = (Token)list.get(0);
		tokenDao.updateTokenStatusById(token.getTokenId(), jobSeq);
		return token.getTokenId();
	}
	
	@Override
	public String[] lockRandomTokenAndFrequencyToUse(String jobSeq) {
		
		Token token = new Token();
		token.setLocked("0");
		List<?> list =  tokenDao.getTokensByExample(token);
		token = (Token)list.get(0);
		tokenDao.updateTokenStatusById(token.getTokenId(), jobSeq);
		String[] rtnArray = new String[2];
		rtnArray[0] = token.getTokenId();
		rtnArray[1] = token.getFrequency();
		return rtnArray;
	}

}
