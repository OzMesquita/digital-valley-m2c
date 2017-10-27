package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	private ConnectionFactory() {
		//
	}

	public static EntityManagerFactory getConnection() {
		return Persistence.createEntityManagerFactory("digital-valley-m2c");
	}
}
