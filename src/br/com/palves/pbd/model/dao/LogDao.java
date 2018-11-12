package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.Log;

public class LogDao implements ILogDao{
	private EntityManager em;
	private static LogDao instance;

	public static LogDao getInstance () {
		if(instance == null) {
			instance = new LogDao();
		}
		return instance;
	}
	private LogDao() {

	}
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
	@Override
	public Log findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Log  var = null;
		String op = "Busca ID";
		try {
			var = em.find(Log.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<Log> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Log> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+Log.class.getSimpleName()+" ed");
			var = query.getResultList();
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public Log deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Log var = null;
		try {
			em.getTransaction().begin();
			var = em.find(Log.class,id);
			em.remove(var);
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	
}
