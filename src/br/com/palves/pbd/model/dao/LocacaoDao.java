package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.bin.Locacao;

public class LocacaoDao implements ILocacaoDao{
	private EntityManager em;
	private static LocacaoDao instance;

	public static LocacaoDao getInstance () {
		if(instance == null) {
			instance = new LocacaoDao();
		}
		return instance;
	}
	private LocacaoDao() {

	}
	@Override
	public Locacao persistOrMerge(Locacao locacao) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(locacao.getId() == null)
				em.persist(locacao);
			else {
				locacao = em.merge(locacao);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+locacao.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return locacao;
	}
	@Override
	public Locacao findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Locacao  var = null;
		String op = "Busca ID";
		try {
			var = em.find(Locacao.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<Locacao> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Locacao> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+Locacao.class.getSimpleName()+" ed");
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
	public Locacao deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Locacao var = null;
		try {
			em.getTransaction().begin();
			var = em.find(Locacao.class,id);
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
