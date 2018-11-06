package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;

public class CategoriaPassageiroDao implements ICategoriaPassageiroDao {
	private EntityManager em;

	@Override
	public CategoriaPassageiro persisteOrMerge(CategoriaPassageiro categoriaPassageiro) throws DaoException {
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
	
	
}
