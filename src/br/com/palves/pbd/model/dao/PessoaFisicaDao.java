package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Log;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.sql.SQLUtil;

public class PessoaFisicaDao extends DaoGenerico<PessoaFisica> implements IPessoaFisicaDao {
	private static PessoaFisicaDao instance;

	public static PessoaFisicaDao getInstance () {
		if(instance == null) {
			instance = new PessoaFisicaDao();
		}
		return instance;
	}
	private PessoaFisicaDao() {

	}
	@Override
	public Object procedureValidaHabilitacao(String nHab) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Object var = null;
		String op = "Procedure validaHab";
		try {
			Query query = em.createNativeQuery(SQLUtil.PessoaFisica.NATIVEQUERYPROCEDURE_VALIDA_HABILITACAO);
			query.setParameter("nHab",""+nHab+"");
			var = (Object)query.getSingleResult();//Precisa que algo seja retornado; vetor para mais de um parametro.
		}
		catch(NoResultException nre) {
			nre.printStackTrace();
			return nHab;
		}
		catch(Exception e){
			e.printStackTrace();
			return nHab;
			//throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}

}
