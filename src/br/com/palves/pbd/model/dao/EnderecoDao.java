package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Endereco;

public class EnderecoDao implements IEnderecoDao{
	private EntityManager em;

	public Endereco persistOrMerge(Endereco end) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(end.getId() == null)
				em.persist(end);
			else
				em.merge(end);
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Endereco: "+e.getMessage());
		}finally {
			em.close();
		}
		return end;
	}

}
