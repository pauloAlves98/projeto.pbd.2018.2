package br.com.palves.pbd.model.dao;

import java.util.List;

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
	@Override
	public List<PessoaJuridica> buscarPorFiltro(String var) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List var1 = null;
		String op = "Busca Por Filtro";
		try {
			Query query = em.createNamedQuery("PessoaJuridica.listarPorFiltro");
			query.setParameter("var",var);
			var1 = query.getResultList();
			if(var1.size()<=0)
				return null;
		}
		catch(NoResultException nre) {
			return null;
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var1;
	}
}
