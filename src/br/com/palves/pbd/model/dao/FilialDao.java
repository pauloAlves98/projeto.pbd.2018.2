package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Filial;

public class FilialDao implements IFilialDao{
	private EntityManager em;

	@Override
	public Filial persistOrMerge(Filial filial) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(filial.getId() == null)
				em.persist(filial);
			else {
				filial = em.merge(filial);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Filial: "+e.getMessage());
		}finally {
			em.close();
		}
		return filial;
	}
	
	
}
