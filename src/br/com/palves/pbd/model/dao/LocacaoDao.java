package br.com.palves.pbd.model.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.bin.Locacao;

public class LocacaoDao extends DaoGenerico<Locacao> implements ILocacaoDao{
	private static LocacaoDao instance;

	public static LocacaoDao getInstance () {
		if(instance == null) {
			
			instance = new LocacaoDao();
		}
		return instance;
	}
	private LocacaoDao() {
		
	}
	@Override
	public List<Locacao> buscarPorFiltro(String var1, Date var2, Date var3) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List var = null;
		String op = "Busca Por Filtro";
		try {
			Query query = em.createNamedQuery("Locacao.listarPorFiltro");
			query.setParameter("var1",var1);
			query.setParameter("var2",var2);
			query.setParameter("var3",var3);
			System.out.println(var1);
			var = query.getResultList();
			if(var.size()<=0)
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
		return var;
	}
}
