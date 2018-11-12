package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.bin.Filial;

public class FilialDao implements IFilialDao{
	private EntityManager em;
	private static FilialDao instance;

	public static FilialDao getInstance () {
		if(instance == null) {
			instance = new FilialDao();
		}
		return instance;
	}
	private FilialDao () {

	}
	@Override
	public Filial persistOrMerge(Filial filial) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(filial.getId() == null)
				em.persist(filial);
			else {
				filial = em.merge(filial);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Filial: "+e.getMessage());
		}finally {
			em.close();
		}
		return filial;
	}
	@Override
	public Filial findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Filial var = null;
		String op = "Busca ID";
		try {
			var = em.find(Filial.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<Filial> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Filial> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+Filial.class.getSimpleName()+" ed");
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
	public Filial deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Filial var = null;
		try {
			em.getTransaction().begin();
			var = em.find( Filial.class,id);
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
