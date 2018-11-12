package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;

public class PessoaJuridicaDao implements IPessoaJuridicaDao{
	private EntityManager em;
	private static PessoaJuridicaDao instance;

	public static PessoaJuridicaDao getInstance () {
		if(instance == null) {
			instance = new PessoaJuridicaDao();
		}
		return instance;
	}
	private PessoaJuridicaDao() {

	}
	@Override
	public PessoaJuridica persistOrMerge(PessoaJuridica pessoaJ) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(pessoaJ.getId() == null)
				em.persist(pessoaJ);
			else {
				em.merge(pessoaJ);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Pessoa Juridica: "+e.getMessage());
		}finally {
			em.close();
		}
		return pessoaJ;
	}
	@Override
	public PessoaJuridica findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		PessoaJuridica var = null;
		String op = "Busca ID";
		try {
			var = em.find(PessoaJuridica.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<PessoaJuridica> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<PessoaJuridica> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+PessoaJuridica.class.getSimpleName()+" ed");
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
	public PessoaJuridica deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		PessoaJuridica  var = null;
		try {
			em.getTransaction().begin();
			var = em.find(PessoaJuridica .class,id);
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
