package br.com.palves.pbd.model.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.sql.SQLUtil;

public class PessoaDao extends DaoGenerico<Pessoa> implements IPessoaDao{
	private static PessoaDao instance;

	public static PessoaDao getInstance () {
		if(instance == null) {
			instance = new PessoaDao();
		}
		return instance;
	}
	private PessoaDao() {

	}
	@Override
	public Object[] buscarIdPorLogin(String email, String senha) throws DaoException{
			em = ConnectionFactory.getInstance().getConnection();
			Object var[] = null;
			String op = "Busca ID Login Pessoa";
			try {
				Query query = em.createNativeQuery(SQLUtil.Pessoa.NATIVEQUERY_BUSCAR_ID_POR_LOGIN);
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
