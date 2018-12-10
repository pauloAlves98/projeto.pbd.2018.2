package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.sql.SQLUtil;

public class ConfiguracaoDao extends DaoGenerico<Configuracao>implements IConfiguracaoDao{
	private static ConfiguracaoDao instance;

	public static ConfiguracaoDao getInstance () {
		if(instance == null) {
			instance = new ConfiguracaoDao();
		}
		return instance;
	}
	private ConfiguracaoDao () {

	}
	@Override
	public Configuracao buscarUltimo() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Configuracao var = null;
		String op = "Busca Ultimo";
		try {
			Query query = em.createNamedQuery("Configuracao.capturarUltimo");
			
			List t =  query.getResultList();//Precisa que algo seja retornado; vetor para mais de um parametro.
			if(t.size()<1)
				return null;
			else
				return (Configuracao) t.get(0);
		}
		catch(NoResultException nre) {
			return null;
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
	}
}
