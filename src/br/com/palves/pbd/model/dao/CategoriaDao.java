package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;

public class CategoriaDao implements ICategoriaDao{
	private EntityManager em;
	private static CategoriaDao instance;
	
	public static CategoriaDao getInstance () {
		if(instance == null) {
			instance = new CategoriaDao();
		}
		return instance;
	}
	private CategoriaDao () {
		
	}
	@Override
	public Categoria persistOrMerge(Categoria categoria) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(categoria.getId() == null)
				em.persist(categoria);
			else {
				categoria = em.merge(categoria);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Categoria: "+e.getMessage());
		}finally {
			em.close();
		}
		return categoria;
	}
	@Override
	public Categoria findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Categoria var = null;
		String op = "Busca ID";
		try {
			var = em.find(Categoria.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<Categoria> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Categoria> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from Categoria ed");
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
	public Categoria deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Categoria var = null;
		try {
			em.getTransaction().begin();
			var = em.find( Categoria.class,id);
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
