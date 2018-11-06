package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Funcionario;

public class FuncionarioDao implements IFuncionarioDao {
	EntityManager em;
	@Override
	public Funcionario persistOrMerge(Funcionario funcionario) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(funcionario.getId() == null)
				em.persist(funcionario);
			else {
				funcionario = em.merge(funcionario);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Funcionario: "+e.getMessage());
		}finally {
			em.close();
		}
		return funcionario;
	}

}
