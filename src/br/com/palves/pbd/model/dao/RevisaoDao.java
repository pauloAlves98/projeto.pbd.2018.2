package br.com.palves.pbd.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Revisao;
import br.com.palves.pbd.model.bin.views.ViewLocacao;
import br.com.palves.pbd.model.bin.views.ViewRevisao;
import br.com.palves.pbd.sql.SQLUtil;

public class RevisaoDao extends DaoGenerico<Revisao> implements IRevisaoDao{
	private static RevisaoDao instance;

	public static RevisaoDao getInstance () {
		if(instance == null) {
			instance = new RevisaoDao();
		}
		return instance;
	}
	private RevisaoDao() {

	}
	@Override
	public void procedureChacaVeiculosNaRevisao() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Object var = null;
		String op = "Procedure Checa Veiculos Revisão";
		try {
			Query query = em.createNativeQuery(SQLUtil.Revisao.NATIVEQUERY_CHACA_VEICULOS_REVISAO);
			query.getSingleResult();//Precisa que algo seja retornado; vetor para mais de um parametro.
		}
		catch(NoResultException nre) {
			nre.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			//throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
	}
	public List<ViewRevisao> buscarViewPorVeiculo(int cod) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Object[]> var = null;
		List<ViewRevisao> lr = null;
		String op = "Busca Por Veiculo Revisao View";
		try {
			Query query = em.createNativeQuery(SQLUtil.Revisao.NATIVEQUERYBUSCARVIEWREVISAO_POR_VEICULO);
			query.setParameter("var1",cod);
			var = query.getResultList();
			if(var.size()<=0)
				return null;
			else {
				lr = new ArrayList();
				for(Object[] v:var) {
					ViewRevisao vloc = new ViewRevisao();
					vloc.setCod((int)v[0]);
					vloc.setHoraRevisao((Date)v[1]);
					vloc.setStatus((String)v[2]);
					vloc.setCodVeiculo((int)v[3]);
					vloc.setNomeVeiculo((String)v[4]);
					vloc.setPlaca((String)v[5]);
					vloc.setChassi((String)v[6]);
					System.out.println(vloc.toString());
					lr.add(vloc);
				}
				return lr;
			}
		}
		catch(NoResultException nre) {
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
	}
//	public static void main(String[] args) {
//		try {
//			RevisaoDao.getInstance().buscarViewPorVeiculo(2);
//		} catch (DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
