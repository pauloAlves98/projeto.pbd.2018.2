package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Configuracao;

public class ConfiguracaoDao implements IConfiguracaoDao{
	private EntityManager em;

	@Override
	public Configuracao persistOrMerge(Configuracao configuracao) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(configuracao.getId() == null)
				em.persist(configuracao);
			else {
				configuracao = em.merge(configuracao);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+configuracao.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return configuracao;
	}
	
}
