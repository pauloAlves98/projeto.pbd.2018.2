package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;
import br.com.palves.pbd.model.bin.Configuracao;

public class ConfiguracaoDao implements IConfiguracaoDao{
	private EntityManager em;
	private static ConfiguracaoDao instance;

	public static ConfiguracaoDao getInstance () {
		if(instance == null) {
			instance = new ConfiguracaoDao();
		}
		return instance;
	}
	private ConfiguracaoDao () {

	}
	@Override
	public Configuracao persistOrMerge(Configuracao configuracao) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(configuracao.getId() == null)
				em.persist(configuracao);
			else {
				configuracao = em.merge(configuracao);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+configuracao.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return configuracao;
	}
	@Override
	public Configuracao findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Configuracao var = null;
		String op = "Busca ID";
		try {
			var = em.find(Configuracao.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<Configuracao> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Configuracao> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+Configuracao.class.getSimpleName()+" ed");
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
	public Configuracao deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Configuracao var = null;
		try {
			em.getTransaction().begin();
			var = em.find( Configuracao.class,id);
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
