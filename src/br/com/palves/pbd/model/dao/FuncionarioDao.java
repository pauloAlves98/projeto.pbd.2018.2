package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.sql.SQLUtil;

public class FuncionarioDao extends DaoGenerico <Funcionario> implements IFuncionarioDao {
	private static FuncionarioDao instance;

	public static FuncionarioDao getInstance () {
		if(instance == null) {
			instance = new FuncionarioDao();
		}
		return instance;
	}
	private FuncionarioDao () {

	}
	@Override
	public Object[] buscarIdPorLogin(String email, String senha) throws DaoException{
			em = ConnectionFactory.getInstance().getConnection();
			Object var[] = null;
			String op = "Busca ID Login Funcionario";
			try {
				Query query = em.createNativeQuery(SQLUtil.Funcionario.NATIVEQUERY_BUSCAR_ID_POR_LOGIN);
				query.setParameter(1,""+email+"");
				query.setParameter(2,senha);
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
