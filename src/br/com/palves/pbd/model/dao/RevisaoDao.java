package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Revisao;

public class RevisaoDao implements IRevisaoDao{
	private EntityManager em;

	@Override
	public Revisao persistOrMerge(Revisao revisao) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(revisao.getId() == null)
				em.persist(revisao);
			else {
				revisao = em.merge(revisao);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+revisao.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return revisao;
	}
	
}
