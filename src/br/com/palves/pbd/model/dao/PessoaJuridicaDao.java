package br.com.palves.pbd.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaJuridica;


public class PessoaJuridicaDao extends DaoGenerico<PessoaJuridica> implements IPessoaJuridicaDao{
	private static PessoaJuridicaDao instance;

	public static PessoaJuridicaDao getInstance () {
		if(instance == null) {
			instance = new PessoaJuridicaDao();
		}
		return instance;
	}
	private PessoaJuridicaDao() {

	}
	@Override
	public PessoaJuridica buscarPorCnpj(String cnpj) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		PessoaJuridica var = null;
		String op = "Busca ID PessoaF";
		try {
			Query query = em.createNamedQuery("PessoaJuridica.buscarPorCnpj");
			query.setParameter("var",cnpj);
			var = (PessoaJuridica) query.getSingleResult();//Precisa que algo seja retornado; vetor para mais de um parametro.
		}
		catch(NoResultException nre) {
			return null;
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
}
