package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.sql.SQLUtil;

public class CategoriaDao extends DaoGenerico<Categoria> implements ICategoriaDao{
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
	public List<Categoria> buscarTodosPorDiscriminador(String disc) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List var = null;
		String op = "Busca Discriminador";
		try {
			Query query = em.createNamedQuery("Categoria.listartodos");
			query.setParameter("var", disc);
			var = query.getResultList();
		}
		catch(NoResultException nre) {
			return null;
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public Object[] buscarIdeDiscriminadorPorNome(String nome) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Object var[] = null;
		String op = "Busca ID e Discriminador Categoria";
		try {
			Query query = em.createNativeQuery(SQLUtil.Categoria.NATIVEQUERY_BUSCAR_ID_DISC_POR_NOME);
			query.setParameter(1,nome);
			var = (Object[])query.getSingleResult();//Precisa que algo seja retornado; vetor para mais de um parametro.
		}
		catch(NoResultException nre) {
			nre.printStackTrace();
			return null;
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
}
