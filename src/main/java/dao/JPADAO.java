/**
 * 
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author N2S-PC01
 *
 */
public class JPADAO {
	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	static {
		entityManagerFactory = ConnectionFactory.getConnection();
	}

	public void open() {
		this.setEntityManager(entityManagerFactory.createEntityManager());
	}

	public void close() {
		this.getEntityManager().close();
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
