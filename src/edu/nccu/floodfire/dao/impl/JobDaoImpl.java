package edu.nccu.floodfire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import edu.nccu.floodfire.dao.JobDao;
import edu.nccu.floodfire.entity.Job;

public class JobDaoImpl implements JobDao {
	private EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
		return emf;
	}

	@PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
	
	public void addJob(Job job) {
		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("floodfire-MySQL-JPA");
		//EntityManager em= 	emf.createEntityManager();
		
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();
        
        em.persist(job);
		etx.commit();
		em.close();
		
	}
	
	@SuppressWarnings("rawtypes")
	public List getJobsById(String id)
	{
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("floodfire-MySQL-JPA");
		//EntityManager em= 	emf.createEntityManager();
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createQuery("SELECT e " +
                "  FROM Job e " +
                " WHERE e.createId = '" + id +"'" );
		List<?> results = query.getResultList();
		em.close();
		return results;
	}

	@Override
	public void updateJobStatusById(String id,String jobStatus) {
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("floodfire-MySQL-JPA");
		//EntityManager em= 	emf.createEntityManager();		
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();
		Query query = em.createQuery("UPDATE Job e SET e.jobStatus = : jobStatus WHERE e.job_Seq ='" + id +"'" );
		query.setParameter("jobStatus", jobStatus);
		query.executeUpdate();
		etx.commit();
		em.close();
		
	}
	
	@Override
	public void updateJobByExample(String id,Job job) {
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("floodfire-MySQL-JPA");
		//EntityManager em= 	emf.createEntityManager();		
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();
        String update ="UPDATE Job e SET ";
        if(job.getJobStatus()!=null && !"".equals(job.getJobStatus()))
        {
        	update = update + "e.jobStatus = : jobStatus,";
        	
        }
        if(job.getStopTime()!=null && !"".equals(job.getStopTime()))
        {
        	update = update + "e.stopTime = : stopTime";
        }
        update = update + " WHERE e.job_Seq ='" + id +"'";
		Query query = em.createQuery(update);
		if(job.getJobStatus()!=null && !"".equals(job.getJobStatus()))
		{
		   query.setParameter("jobStatus", job.getJobStatus());
		}
		if(job.getStopTime()!=null && !"".equals(job.getStopTime()))
        {
			query.setParameter("stopTime", job.getStopTime());
        }
		
		
		query.executeUpdate();
		etx.commit();
		em.close();
		
	}
	
	@SuppressWarnings("rawtypes")
	public List getJobsByExample(Job job)
	{
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("floodfire-MySQL-JPA");
		//EntityManager em= 	emf.createEntityManager();
		EntityManager em = this.emf.createEntityManager();
		String jpql = "SELECT e FROM Job e WHERE 1=1";
		
		if(job.getEvent().getEventName()!=null && !"".equals(job.getEvent().getEventName()))
		{
			jpql +=" and e.event.eventName = '" + job.getEvent().getEventName()+"'";
		}
		Query query = em.createQuery(jpql);
		List<?> results = query.getResultList();
		em.close();
		return results;
	}

}
