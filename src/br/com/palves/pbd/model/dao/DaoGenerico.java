package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.Generico;

public class DaoGenerico <T extends Generico>{
	protected EntityManager em;
	private static DaoGenerico instance;
	protected DaoGenerico() {}
	
	public T persistOrMerge(T obj) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(obj.getId() == null)
				em.persist(obj);
			else {
				obj = em.merge(obj);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return obj;
	}
	public T findById(Class <T>clazz ,Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		T var = null;
		String op = "Busca ID";
		try {
			var = em.find(clazz, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	public List<T> findAll(Class <T>clazz) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<T> var = null;
		String op = "Buscar CG";
		try {
			Query query = em.createQuery(" select ed from "+clazz.getSimpleName()+" ed");
			var = query.getResultList();
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	public T deleteById(Class<T>clazz, int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		T var = null;
		try {
			em.getTransaction().begin();
			var = em.find(clazz,id);
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
	public T refresh(T obj) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Refresh";
		try {
			em.getTransaction().begin();
			em.refresh(obj);
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return obj;
	}

}
