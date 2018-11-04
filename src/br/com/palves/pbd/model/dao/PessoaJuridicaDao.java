package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaJuridica;

public class PessoaJuridicaDao implements IPessoaJuridicaDao{
	private EntityManager em;
	@Override
	public PessoaJuridica persistOrMerge(PessoaJuridica pessoaJ) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(pessoaJ.getId() == null)
				em.persist(pessoaJ);
			else {
				em.merge(pessoaJ);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Pessoa Juridica: "+e.getMessage());
		}finally {
			em.close();
		}
		return pessoaJ;
	}

}
