package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;

public class CategoriaPassageiroDao implements ICategoriaPassageiroDao {
	private EntityManager em;
	private static CategoriaPassageiroDao instance;

	public static CategoriaPassageiroDao getInstance () {
		if(instance == null) {
			instance = new CategoriaPassageiroDao();
		}
		return instance;
	}
	private CategoriaPassageiroDao () {

	}
	@Override
	public CategoriaPassageiro persistOrMerge(CategoriaPassageiro categoriaPassageiro) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(categoriaPassageiro.getId() == null)
				em.persist(categoriaPassageiro);
			else {
				categoriaPassageiro = em.merge(categoriaPassageiro);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar"+op+" em "+categoriaPassageiro.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return categoriaPassageiro;
	}
	@Override
	public CategoriaPassageiro findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		CategoriaPassageiro var = null;
		String op = "Busca ID";
		try {
			var = em.find(CategoriaPassageiro.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<CategoriaPassageiro> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<CategoriaPassageiro> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+CategoriaPassageiro.class.getSimpleName()+" ed");
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
	public CategoriaPassageiro deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		CategoriaPassageiro var = null;
		try {
			em.getTransaction().begin();
			var = em.find( CategoriaPassageiro.class,id);
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
