package br.com.palves.pbd.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.views.ViewLocacao;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.sql.SQLUtil;

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
	@Override
	public List<Locacao> buscarPorVeiculo(String var1, int var2) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List var = null;
		String op = "Busca Por Veiculo";
		try {
			Query query = em.createNamedQuery("Locacao.listarPorVeiculo");
			query.setParameter("var1",var1);
			query.setParameter("var2",var2);
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
	public List<ViewLocacao> buscarViewPorPeriodo(Date var1, Date var2) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Object[]> var = null;
		List<ViewLocacao> lr = null;
		String op = "Busca Por Periodo View";
		try {
			Query query = em.createNativeQuery(SQLUtil.Locacao.NATIVEQUERY_BUSCARVIEWLOCACAO_POR_PERIODO);
			query.setParameter("var1",var1);
			query.setParameter("var2",var2);
			var = query.getResultList();
			if(var.size()<=0)
				return null;
			else {
				lr = new ArrayList();
				for(Object[] v:var) {
					ViewLocacao vloc = new ViewLocacao();
					vloc.setCod((int)v[0]);
					vloc.setDataPrevistaEntrega((Date)v[1]);
					vloc.setDataRealEntrega((Date)v[2]);
					vloc.setDataRetirada((Date)v[3]);
					vloc.setNomeCliente((String)v[4]);
					vloc.setNomeVeiculo((String)v[5]);
					vloc.setSituacao((String)v[6]);
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
	public List<ViewLocacao> buscarViewPorCliente(int codCliente) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Object[]> var = null;
		List<ViewLocacao> lr = null;
		String op = "Busca Por Periodo View";
		try {
			Query query = em.createNativeQuery(SQLUtil.Locacao.NATIVEQUERY_BUSCARVIEWLOCACAO_POR_CLIENTE);
			query.setParameter("var1",codCliente);
			var = query.getResultList();
			if(var.size()<=0)
				return null;
			else {
				lr = new ArrayList();
				for(Object[] v:var) {
					int cont = 0;
					ViewLocacao vloc = new ViewLocacao();
					vloc.setCod((int)v[0]);
					vloc.setCodCliente((int)v[1]);
					vloc.setDataPrevistaEntrega((Date)v[2]);
					vloc.setDataRealEntrega((Date)v[3]);
					vloc.setDataRetirada((Date)v[4]);
					vloc.setNomeCliente((String)v[5]);
					vloc.setSituacao((String)v[6]);
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
	public List<ViewLocacao> buscarViewPorMotorista(String cpf) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Object[]> var = null;
		List<ViewLocacao> lr = null;
		String op = "Busca Por Periodo View";
		try {
			Query query = em.createNativeQuery(SQLUtil.Locacao.NATIVEQUERY_BUSCARVIEWLOCACAO_POR_MOTORISTA);
			query.setParameter("var1",cpf);
			var = query.getResultList();
			if(var.size()<=0)
				return null;
			else {
				lr = new ArrayList();
				for(Object[] v:var) {
					int cont = 0;
					ViewLocacao vloc = new ViewLocacao();
					vloc.setCod((int)v[0]);
					vloc.setCpf((String)v[1]);
					vloc.setDataPrevistaEntrega((Date)v[2]);
					vloc.setDataRealEntrega((Date)v[3]);
					vloc.setDataRetirada((Date)v[4]);
					vloc.setNomeMotorista((String)v[5]);
					vloc.setSituacao((String)v[6]);
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
	 public List<ViewLocacao> buscarViewPorPeriodoFinanceiro(Date var1, Date var2) throws DaoException {
			em = ConnectionFactory.getInstance().getConnection();
			List<Object[]> var = null;
			List<ViewLocacao> lr = null;
			String op = "Busca Por Periodo Finaceiro View";
			try {
				Query query = em.createNativeQuery(SQLUtil.Locacao.NATIVEQUERY_BUSCARVIEWLOCACAO_POR_PERIODO_FINACEIRO);
				query.setParameter("var1",var1);
				query.setParameter("var2",var2);
				var = query.getResultList();
				if(var.size()<=0)
					return null;
				else {
					lr = new ArrayList();
					for(Object[] v:var) {
						ViewLocacao vloc = new ViewLocacao();
						vloc.setCod((int)v[0]);
						vloc.setDataPrevistaEntrega((Date)v[1]);
						vloc.setDataRealEntrega((Date)v[2]);
						vloc.setDataRetirada((Date)v[3]);
						vloc.setSituacao((String)v[4]);
						vloc.setPrecoFinal((double)v[5]);
						vloc.setTaxaCombustivel((double)v[6]);
						vloc.setTaxaHigiene((double)v[7]);
						vloc.setValorDiaria((double)v[8]);
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
//	 public static void main(String[] args) {
//		try {
//			LocacaoDao.getInstance().buscarViewPorPeriodoFinanceiro(TratadorDeMascara.converterStringData("10/10/2018"), new Date());
//		} catch (DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
