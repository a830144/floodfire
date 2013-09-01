package edu.nccu.floodfire.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import edu.nccu.floodfire.dao.YourtwapperkeeperDao;
import edu.nccu.floodfire.entity.Archive;
import edu.nccu.floodfire.entity.Rawstream;

public class YourtwapperkeeperDaoImpl implements YourtwapperkeeperDao {
	private EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
		return emf;
	}
	@PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
	@Override
	public void removeArchive(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void persistArchive(Archive archive) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();

        em.persist(archive);
		etx.commit();
		em.close();

	}

	@Override
	public void persistRawstream(Rawstream rawstream) {
		EntityManager em = this.emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
        etx.begin();

        em.persist(rawstream);
		etx.commit();
		em.close();

	}
	
	

}
