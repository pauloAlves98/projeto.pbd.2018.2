package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;

public class CategoriaPassageiroDao extends DaoGenerico<CategoriaPassageiro> implements ICategoriaPassageiroDao {
	private static CategoriaPassageiroDao instance;

	public static CategoriaPassageiroDao getInstance () {
		if(instance == null) {
			instance = new CategoriaPassageiroDao();
		}
		return instance;
	}
	private CategoriaPassageiroDao () {

	}
	@Override
	public List<CategoriaPassageiro> buscarPorFiltro(String var1) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List var = null;
		String op = "Busca Por Filtro";
		try {
			Query query = em.createNamedQuery("CategoriaPassageiro.listarPorFiltro");
			query.setParameter("var1",var1);
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
