package br.com.palves.pbd.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	private static  ConnectionFactory connectionFactory;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PADSPU"); // fabrica de entity Manager
	
	private  ConnectionFactory() {}
	
	public static ConnectionFactory getInstance() {
		if(connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
			return connectionFactory;
	}
	public EntityManager getConnection() {
		return emf.createEntityManager();
	}
	/*
	 * class DaoG < T extends En>
	 * */
}
