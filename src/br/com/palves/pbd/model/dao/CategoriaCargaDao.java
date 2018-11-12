package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.Endereco;

public class CategoriaCargaDao implements ICategoriaCargaDao{
	private EntityManager em;
	private static CategoriaCargaDao instance;
	
	public static CategoriaCargaDao getInstance () {
		if(instance == null) {
			instance = new CategoriaCargaDao();
		}
		return instance;
	}
	private CategoriaCargaDao () {
		
	}
	@Override
	public CategoriaCarga persistOrMerge(CategoriaCarga categoriaCarga) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(categoriaCarga.getId() == null)
				em.persist(categoriaCarga);
			else {
				categoriaCarga = em.merge(categoriaCarga);
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
		return categoriaCarga;
	}
	@Override
	public CategoriaCarga findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		CategoriaCarga var = null;
		String op = "Busca ID";
		try {
			var = em.find(CategoriaCarga.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<CategoriaCarga> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<CategoriaCarga> var = null;
		String op = "Buscar CG";
		try {
			Query query = em.createQuery(" select ed from CategoriaCarga ed");
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
	public CategoriaCarga deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		CategoriaCarga var = null;
		try {
			em.getTransaction().begin();
			var = em.find( CategoriaCarga.class,id);
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
