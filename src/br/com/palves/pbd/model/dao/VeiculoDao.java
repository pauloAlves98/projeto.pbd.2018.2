package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.enums.QueryEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Veiculo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.sql.SQLUtil;

public class VeiculoDao extends DaoGenerico<Veiculo>implements IVeiculoDao{
	private static VeiculoDao instance;

	public static VeiculoDao getInstance () {
		if(instance == null) {
			instance = new VeiculoDao();
		}
		return instance;
	}
	private VeiculoDao() {

	}
	@Override
	public Object[] buscarIdPorNome(String nome) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Object var[] = null;
		String op = "Busca ID Veiculo";
		try {
			Query query = em.createNativeQuery(SQLUtil.Veiculo.NATIVEQUERY_BUSCAR_ID_POR_NOME);
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
	@Override
	public List<Veiculo> buscarPorFiltro(String var1) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List var = null;
		String op = "Busca Por Filtro";
		try {
			Query query = em.createNamedQuery("Veiculo.listarPorFiltro");
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
	@Override
	public List<Veiculo> buscarPorParametro(int codFilial, int codCategoria, String disc, double valorCategoria,
			String filtro,QueryEnum quer) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List var = null;
		String op = "Busca Por Parametro "+quer.getValor();
		try {
			switch(quer) {
			case LISTARVEICULOPORPARAMETROFILIALCATEGORIA:{
				Query query = em.createNamedQuery(quer.getValor());
				query.setParameter("var4",disc);
				query.setParameter("var3",codCategoria);
				query.setParameter("var2",codFilial);
				query.setParameter("var1",filtro);
				var = query.getResultList();
				break;
			}
			case LISTARVEICULOPORPARAMETROFILIALCATEGORIAGERENTE:{
				Query query = em.createNamedQuery(quer.getValor());
				query.setParameter("var3", valorCategoria);
				query.setParameter("var2",codFilial);
				query.setParameter("var1",filtro);
				var = query.getResultList();
				break;
			}
			}
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
