package edu.nccu.floodfire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import edu.nccu.floodfire.dao.EventDao;
import edu.nccu.floodfire.entity.Event;

public class EventDaoImpl implements EventDao {
	private EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
		return emf;
	}

	@PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
	
	public void addEvent(Event event) {
	
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("floodfire-MySQL-JPA");
		//EntityManager em= 	emf.createEntityManager();
		
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();
        
        em.persist(event);
		etx.commit();
		em.close();
		
	}
	



	@SuppressWarnings("rawtypes")
	@Override
	public List getEventsByExample(Event event) {
		// TODO Auto-generated method stub
		EntityManager em = this.emf.createEntityManager();
		String jpql = "SELECT e " +
        "  FROM Event e "
        +  " WHERE 1=1";
		
		if(event.getEventName()!=null && !"".equals(event.getEventName()))
		{
			jpql +="and e.eventName = '" + event.getEventName()+"'";
		}
		if(event.getEventType()!=null && !"".equals(event.getEventType()))
		{
			jpql +="and e.eventType = '" + event.getEventType()+"'";
		}
		
		Query query = em.createQuery(jpql);
		List<?> results = query.getResultList();
		em.close();
		return results;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getEventsAndJobsByExample(Event event) {
		// TODO Auto-generated method stub
		EntityManager em = this.emf.createEntityManager();
		String sql = "select a.eventName,a.eventType,b.job_Seq " +
        "  From  manageent.event a,manageent.job b"
        +  " WHERE 1=1";
		
		if(event.getEventName()!=null && !"".equals(event.getEventName()))
		{
			sql +="and a.eventName = '" + event.getEventName()+"'";
		}
		if(event.getEventType()!=null && !"".equals(event.getEventType()))
		{
			sql +="and a.eventType = '" + event.getEventType()+"'";
		}
		
		Query query = em.createNativeQuery(sql);
		List<?> results = query.getResultList();
		em.close();
		return results;
	}

}
