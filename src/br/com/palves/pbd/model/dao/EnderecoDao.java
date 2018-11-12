package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Endereco;

public class EnderecoDao implements IEnderecoDao{
	private EntityManager em;
	private static EnderecoDao instance;
	 
	public static EnderecoDao getInstance () {
		if(instance == null) {
			instance = new EnderecoDao();
		}
		return instance;
	}
	private EnderecoDao () {
		
	}
	public Endereco persistOrMerge(Endereco end) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(end.getId() == null)
				em.persist(end);
			else
				em.merge(end);
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Endereco: "+e.getMessage());
		}finally {
			em.close();
		}
		return end;
	}
	@Override
	public Endereco findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Endereco end = null;
		String op = "Busca ID";
		try {
			end = em.find(Endereco.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em Endereco: "+e.getMessage());
		}finally {
			em.close();
		}
		return end;
	}
	@Override
	public List<Endereco>findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Endereco> end = null;
		String op = "Busca Enderecos";
		try {
			Query query = em.createQuery(" select ed from Endereco ed");
			end = query.getResultList();
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em Endereco: "+e.getMessage());
		}finally {
			em.close();
		}
		return end;
	}
	@Override
	public Endereco deleteById (int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Endereco end = null;
		try {
			em.getTransaction().begin();
			end = em.find(Endereco.class,id);
			em.remove(end);
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Endereco: "+e.getMessage());
		}finally {
			em.close();
		}
		return end;
	}
}
