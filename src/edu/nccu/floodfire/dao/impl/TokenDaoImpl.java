package edu.nccu.floodfire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import edu.nccu.floodfire.dao.TokenDao;
import edu.nccu.floodfire.entity.Token;

public class TokenDaoImpl implements TokenDao {
	
	private EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
		return emf;
	}

	@PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

	@SuppressWarnings("rawtypes")
	@Override
	public List getTokensList() {
		EntityManager em = this.emf.createEntityManager();
		String sql = "select t.tokenId,t.tokenName,t.locked from manageent.token t" 
		  +  " WHERE 1=1";      		
		Query query = em.createNativeQuery(sql);  
        List list = query.getResultList();		
		em.close();
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getTokensByExample(Token token){
		EntityManager em = this.emf.createEntityManager();
		String jpql = "SELECT e " + 
		"  FROM Token e where 1=1 " ; 
		
		if(token.getTokenId()!=null && !"".equals(token.getTokenId()))
		{
			jpql+="and e.tokenId like '%" + token.getTokenId() + "%' ";
		}
		
		if(token.getTokenName()!=null && !"".equals(token.getTokenName()))
		{
			jpql+="and e.tokenName like '%" + token.getTokenName() + "%' ";
		}
		if(token.getLocked()!=null && !"".equals(token.getLocked()))
		{
			jpql+="and e.locked like '%" +token.getLocked() + "%' ";
		}
		if(token.getJobSeq()!=null && !"".equals(token.getJobSeq()))
		{
			jpql+="and e.jobSeq like '%" + token.getJobSeq() + "%' ";
		}
		Query query = em.createQuery(jpql);
		List list = query.getResultList();		
		em.close();
		return list;
	}

	@Override
	public void updateTokenStatusById(String id,String jobSeq) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();
		String sql = "UPDATE manageent.token t SET t.locked ='1',t.jobSeq='" + jobSeq 
		           + "' where t.tokenId='" + id +"'"; 
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
		etx.commit();
		em.close();

	}
	
	@Override
	public void updateTokenByIdExample(String id,Token token) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();
		String sql = "UPDATE manageent.token t SET ";
		boolean start = true;
		if(token.getFrequency()!=null && !"".equals(token.getFrequency()))
		{			
			sql = start ? sql+"t.frequency='" + token.getFrequency() +"'" :sql+",t.frequency='" + token.getFrequency() +"'";
			start = false;
		}
		if(token.getLocked()!=null && !"".equals(token.getLocked()))
		{
			sql = start ? sql+"t.locked='" + token.getLocked() +"'" :sql+",t.locked='" + token.getLocked() +"'";
			start = false;
		}
		sql+= " where t.tokenId='" + id +"'"; 
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
		etx.commit();
		em.close();

	}

	@Override
	public void updateTokenStatusByJobSeq(String jobSeq) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		String sql = "UPDATE manageent.token t SET t.locked ='0' where t.jobSeq='" + jobSeq+"'"; 
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
		etx.commit();
		em.close();
		
	}

	@Override
	public void addToken(Token token) {
		// TODO Auto-generated method stub
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("floodfire-MySQL-JPA");
		//EntityManager em= 	emf.createEntityManager();
		
		
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();
        
        em.persist(token);
		etx.commit();
		em.close();
		
	}

	@Override
	public Token getTokenById(String tokenId) {
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createQuery("SELECT e " +
                "  FROM Token e " +
                " WHERE e.tokenId = '" + tokenId +"'" );
		List<?> results = query.getResultList();
		Token token = results.size()>0 ? (Token)results.get(0) : null;
		em.close();
		return token;
	}

}
