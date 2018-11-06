package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Locacao;

public class LocacaoDao implements ILocacaoDao{
	private EntityManager em;

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
	
	
}
