package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Log;
import br.com.palves.pbd.model.bin.PessoaFisica;

public class PessoaFisicaDao implements IPessoaFisicaDao {
	private EntityManager em;
	private static PessoaFisicaDao instance;

	public static PessoaFisicaDao getInstance () {
		if(instance == null) {
			instance = new PessoaFisicaDao();
		}
		return instance;
	}
	private PessoaFisicaDao() {

	}
	@Override
	public PessoaFisica persistOrMerge(PessoaFisica pessoaF) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(pessoaF.getId() == null)
				em.persist(pessoaF);
			else {
				pessoaF = em.merge(pessoaF);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+pessoaF.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return pessoaF;
	}
	@Override
	public PessoaFisica findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		PessoaFisica  var = null;
		String op = "Busca ID";
		try {
			var = em.find(PessoaFisica.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<PessoaFisica> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<PessoaFisica> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+PessoaFisica.class.getSimpleName()+" ed");
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
	public PessoaFisica deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		PessoaFisica var = null;
		try {
			em.getTransaction().begin();
			var = em.find(PessoaFisica.class,id);
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
