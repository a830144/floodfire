package edu.nccu.floodfire.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.sql.DataSource;

import edu.nccu.floodfire.dao.TwitterDao;
import edu.nccu.floodfire.entity.Tweets;

public class TwitterDaoImpl implements TwitterDao{
	private EntityManagerFactory emf;
	private DataSource datasource;

    public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	public EntityManagerFactory getEmf() {
		return emf;
	}
	@PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
	@Override
	public void removeTwitter(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tweets> findTwitterById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persistTweet(Tweets tweet) {
		
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(tweet);
		etx.commit();
		em.close();
		
	}
	
	public List<?> getTweetsByScreenName(String screenName)
	{
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createQuery("SELECT e " +
                "  FROM Tweets e "
		        +
                " WHERE e.user.screenName = '" + screenName +"'" );
		List<?> results = query.getResultList();
		em.close();
		return results;

	}
	@Override
	public List<?> getTweetsByExample(Tweets tweets) {
		// TODO Auto-generated method stub
		EntityManager em = this.emf.createEntityManager();
		String jpql = "SELECT e " +
        "  FROM Tweets e "
        +  " WHERE 1=1";
		
		if(tweets.getContributors()!=null && "".equals(tweets.getContributors()))
		{
			jpql +="and e.user.screenName = '" + tweets.getContributors()+"'";
		}
		if(tweets.getCreatedAt()!=null )
		{
			jpql +="and e.createdAt = '" + tweets.getCreatedAt()+"'";
		}
		
		Query query = em.createQuery(jpql);
		List<?> results = query.getResultList();
		em.close();
		return results;
	}
	@Override
	public List<?> getTweetsByCreateDateAndScreenNameAndKeywordAndMentionPersion(
			Date startDate, Date endDate, String screenName,
			String keyword, String mentionPerson) {

		EntityManager em = this.emf.createEntityManager();
		String sql = "SELECT e.id,e.contributors,e.created_at,e.text " +
        "  FROM tweetData.tweets e "
        +  " WHERE 1=1";
		
		if(screenName!=null && !"".equals(screenName))
		{
			sql +="and e.contributors = '" + screenName+"'";
		}
		if(startDate!=null && !"".equals(startDate))
		{
			sql +=" and date(e.created_at) > '" + startDate+"'";
		}
		if(endDate!=null && !"".equals(endDate))
		{
			sql +=" and date(e.created_at) < '" + endDate+"'";
		}
		if(keyword!=null && !"".equals(keyword))
		{
			sql +=" and e.text like '%" + keyword+"%'";
		}
		sql +=" group by e.id";
		Query query = em.createNativeQuery(sql);
		List<?> results = query.getResultList();
		em.close();
		return results;
		

	}
	@Override
	public int getContributorCountByJobSeqNoAndCreateAt(String jobSeq,Date createAt) {
		EntityManager em = this.emf.createEntityManager();
		String sql = "select count(*) from (select distinct(contributors) from tweetData.tweets e" 
		  +  " WHERE 1=1";      

		if(jobSeq!=null && !"".equals(jobSeq))
		{
			sql +=" and e.jobSeq = '" + jobSeq+"'";
		}
		if(createAt!=null)
		{
			sql +=" and date(e.created_at) = '" + createAt+"'";
		}
		
		sql += 	") a " ;
		
		Query query = em.createNativeQuery(sql);  
		Long val = (Long) query.getSingleResult(); 
		
		em.close();
		return val.intValue();
	}
	@Override
	public int getContributorCountByJobSeqNoAndCreateAtAndKeyword(String jobSeq,Date createAt,String keyword) {
		EntityManager em = this.emf.createEntityManager();
		String sql = "select count(*) from (select distinct(contributors) from tweetData.tweets e" 
		  +  " WHERE 1=1";      

		if(jobSeq!=null && !"".equals(jobSeq))
		{
			sql +=" and e.jobSeq = '" + jobSeq+"'";
		}
		if(createAt!=null)
		{
			sql +=" and date(e.created_at) = '" + createAt+"'";
		}
		if(keyword!=null && !"".equals(keyword))
		{
			sql +=" and e.text like '%" + keyword+"%'";
		}
		
		sql += 	") a " ;
		
		Query query = em.createNativeQuery(sql);  
		Long val = (Long) query.getSingleResult(); 
		
		em.close();
		return val.intValue();
	}
	
	public int getTweetCountByJobSeqNoAndCreateAt(String jobSeq,Date createAt,String condition)
	{
		String sql = "SELECT count(*)  from (select distinct(id) from tweetData.tweets a "
        +  " WHERE 1=1";
		if(jobSeq!=null && !"".equals(jobSeq))
		{
			sql +=" and a.jobSeq = '" + jobSeq+"'";
		}
		if(createAt!=null)
		{
			sql +=" and date(a.created_at)" + condition +  "'"+createAt+"'";
		}
		
		sql += 	") a " ;
		
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createNativeQuery(sql);
		Long val = (Long) query.getSingleResult();

		em.close();
		return val.intValue();
	}
	public int getTweetCountByJobSeqNoAndCreateAtAndKeyword(String jobSeq,Date createAt,String condition,String keyword)
	{
		String sql = "SELECT count(*)  from (select distinct(id) from tweetData.tweets a "
        +  " WHERE 1=1";
		if(jobSeq!=null && !"".equals(jobSeq))
		{
			sql +=" and a.jobSeq = '" + jobSeq+"'";
		}
		if(createAt!=null)
		{
			sql +=" and date(a.created_at)" + condition +  "'"+createAt+"'";
		}
		if(keyword!=null && !"".equals(keyword))
		{
			sql +=" and a.text like '%" + keyword+"%'";
		}
		
		sql += 	") a " ;
		
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createNativeQuery(sql);
		Long val = (Long) query.getSingleResult();

		em.close();
		return val.intValue();
	}
	
	public int getTweetCountNoDuplicateByJobSeqNoAndCreateAt(String jobSeq,Date createAt,String condition)
	{
        String sql_sub = "select a.id from tweetData.tweets a  where 1=1";
		
		if(jobSeq!=null && !"".equals(jobSeq))
		{
			sql_sub +=" and a.jobSeq = '" + jobSeq+"'";
		}
		if(createAt!=null)
		{
			sql_sub +=" and date(a.created_at)" + condition + "'"+createAt+"'";
		}
		sql_sub +=" group by a.id";
		
		String sql = "select count(*)  " +
        "  FROM  (" + sql_sub + ") b ";
		
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createNativeQuery(sql);
		Long val = (Long) query.getSingleResult();

		em.close();
		return val.intValue();
	}
	
	
	
	
	
	@Override
	public List<?> getTweetByRetweetOrMentionCount(String jobSeq ,Date createAt,Date endAt,String retweetOrCount) {

		EntityManager em = this.emf.createEntityManager();
		String sql = "SELECT e.id,e.text,e.retweet_count,e.favorite_count " +
        "  FROM tweetData.tweets e "
        +  " WHERE 1=1";
		
		if(jobSeq!=null && !"".equals(jobSeq))
		{
			sql +=" and e.jobSeq = '" + jobSeq+"'";
		}
		if(createAt!=null )
		{
			sql +=" and date(e.created_at) = '" + createAt+"'";
		}
		if("1".equals(retweetOrCount))
		{
			sql +=" group by e.id order by e.retweet_count DESC";
		}
		else
		{
			sql +=" group by e.id order by e.favorite_count DESC";
		}
		
		
		Query query = em.createNativeQuery(sql);
		List<?> results = query.getResultList();
		em.close();
		return results;
	}
	@Override
	public void exportData(Date startDate, Date endDate, String fileName) {
		String sql = "SELECT a.id,a.text into OUTFILE  '" + fileName
				+ "' FIELDS TERMINATED BY ','" + "  FROM tweetData.tweets a "
				+ " WHERE 1=1";

		if (startDate != null) {
			sql += " and date(created_at)" + ">" + "'" + startDate + "'";
		}
		if (endDate != null) {
			sql += " and date(created_at)" + "<" + "'" + endDate + "'";
		}

		Connection conn = null;
		Statement stmt = null;
		try {

			conn = datasource.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			stmt.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
			stmt = null;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (conn != null) {

				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}


	}
	

}
