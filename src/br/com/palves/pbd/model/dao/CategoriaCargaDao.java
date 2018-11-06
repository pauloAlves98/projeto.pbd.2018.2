package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaCarga;

public class CategoriaCargaDao implements ICategoriaCargaDao{
	private EntityManager em;
	@Override
	public CategoriaCarga persisteOrMerge(CategoriaCarga categoriaCarga) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(categoriaCarga.getId() == null)
				em.persist(categoriaCarga);
			else {
				categoriaCarga = em.merge(categoriaCarga);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Categoria Carga: "+e.getMessage());
		}finally {
			em.close();
		}
		return categoriaCarga;
	}

}
