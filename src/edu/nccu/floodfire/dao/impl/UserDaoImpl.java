package edu.nccu.floodfire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import edu.nccu.floodfire.dao.UserDao;
import edu.nccu.floodfire.entity.User;

public class UserDaoImpl implements UserDao {
	private EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
		return emf;
	}

	@PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

	public String getPasswdById(String id)
	{
		EntityManager em = this.emf.createEntityManager();
		
		Query query = em.createQuery("SELECT e " +
                "  FROM User e " +
                " WHERE e.iduser = '" + id +"'" );
		List<?> results = query.getResultList();
		User user = results.size()>0 ? (User)results.get(0) : null;
		em.close();
		return user!=null ?user.getPassword():null;
	}
	
	public boolean isPasswdTrue(String userId,String password)
	{
		if(getPasswdById(userId)== null)
		{
			return false;
		}
		else
		{
		   return getPasswdById(userId).equals(password) ? true : false;
		}
		
	}

	public User getUserById(String id)
	{
		
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createQuery("SELECT e " +
                "  FROM User e " +
                " WHERE e.iduser = '" + id +"'" );
		List<?> results = query.getResultList();
		User user = results.size()>0 ? (User)results.get(0) : null;
		em.close();
		return user;
	}
	
	public List<User> getUserByIdWildCard(String id)
	{
		EntityManager em = this.emf.createEntityManager();		
		String queryJQL = "SELECT e " + "  FROM User e " + " WHERE 1=1 ";
		if (id != null || !"".equals(id)) {
			queryJQL += "and e.iduser like '%" + id + "%'";
		}
		Query query = em.createQuery(queryJQL);
		@SuppressWarnings("unchecked")
		List<User> results = query.getResultList();
		em.close();
		return results;
	}
	
	@Override
	public void addUser(String userName, String password) {
	    EntityManager em = this.emf.createEntityManager();		
		EntityTransaction etx = em.getTransaction();
        etx.begin();
        User user = new User();
        user.setIduser(userName);
        user.setPassword(password);
        em.persist(user);
		etx.commit();
		em.close();
		
	}
	


}
