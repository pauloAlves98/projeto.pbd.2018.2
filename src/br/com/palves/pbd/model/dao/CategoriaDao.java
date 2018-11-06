package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;

public class CategoriaDao implements ICategoriaDao{
	private EntityManager em;

	@Override
	public Categoria persisteOrMerge(Categoria categoria) throws DaoException {
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
	
}
