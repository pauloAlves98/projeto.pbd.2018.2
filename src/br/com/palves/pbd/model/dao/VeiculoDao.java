package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Veiculo;

public class VeiculoDao implements IVeiculoDao{
	private EntityManager em;

	@Override
	public Veiculo persistOrMerge(Veiculo veiculo) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(veiculo.getId() == null)
				em.persist(veiculo);
			else {
				veiculo = em.merge(veiculo);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+veiculo.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return veiculo;
	}
	
	
}
