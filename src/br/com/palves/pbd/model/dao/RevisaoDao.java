package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Reserva;
import br.com.palves.pbd.model.bin.Revisao;

public class RevisaoDao implements IRevisaoDao{
	private EntityManager em;
	private static RevisaoDao instance;

	public static RevisaoDao getInstance () {
		if(instance == null) {
			instance = new RevisaoDao();
		}
		return instance;
	}
	private RevisaoDao() {

	}
	@Override
	public Revisao persistOrMerge(Revisao revisao) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(revisao.getId() == null)
				em.persist(revisao);
			else {
				revisao = em.merge(revisao);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+revisao.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return revisao;
	}
	@Override
	public Revisao findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Revisao var = null;
		String op = "Busca ID";
		try {
			var = em.find(Revisao.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<Revisao> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Revisao> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+Revisao.class.getSimpleName()+" ed");
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
	public Revisao deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Revisao  var = null;
		try {
			em.getTransaction().begin();
			var = em.find(Revisao.class,id);
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
