package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Log;

public class LogDao implements ILogDao{
	private EntityManager em;

	@Override
	public Log persistOrMerge(Log log) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(log.getId() == null)
				em.persist(log);
			else {
				log = em.merge(log);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+log.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return log;
	}
	
}
