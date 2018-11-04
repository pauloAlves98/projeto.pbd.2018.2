package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaFisica;

public class PessoaFisicaDao implements IPessoaFisicaDao {
	private EntityManager em;
	@Override
	public PessoaFisica persistOrMerge(PessoaFisica pessoaF) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(pessoaF.getId() == null)
				em.persist(pessoaF);
			else {
				pessoaF = em.merge(pessoaF);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Pessoa Fisica: "+e.getMessage());
		}finally {
			em.close();
		}
		return pessoaF;
	}

}
