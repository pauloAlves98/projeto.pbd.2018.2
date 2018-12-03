package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.sql.SQLUtil;

public class FilialDao extends DaoGenerico<Filial>implements IFilialDao{
	private static FilialDao instance;

	public static FilialDao getInstance () {
		if(instance == null) {
			instance = new FilialDao();
		}
		return instance;
	}
	private FilialDao () {

	}
	
	public Object[] buscaIdPorNome(String nome) throws DaoException{
			em = ConnectionFactory.getInstance().getConnection();
			Object var[] = null;
			String op = "Busca ID  Funcionario!";
			try {
				Query query = em.createNativeQuery(SQLUtil.Filial.NATIVEQUERY_BUSCAR_ID_POR_NOME);
				query.setParameter(1,nome);
				var = (Object[])query.getSingleResult();//Precisa que algo seja retornado; vetor para mais de um parametro.
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
