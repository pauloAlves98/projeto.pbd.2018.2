package br.com.palves.pbd.model.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.enums.QueryEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.bin.Reserva;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;

public class ReservaDao extends DaoGenerico<Reserva> implements IReservaDao{
	private static ReservaDao instance;

	public static ReservaDao getInstance () {
		if(instance == null) {
			instance = new ReservaDao();
		}
		return instance;
	}
	private ReservaDao() {

	}
	@Override
	public List<Reserva> buscarPorFiltro(String var1, Date var2, Date var3,int var4) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List var = null;
		String op = "Busca Por Filtro";
		try {
			Query query = em.createNamedQuery("Reserva.listarPorFiltro");
			query.setParameter("var1",var1);
			query.setParameter("var2",var2);
			query.setParameter("var3",var3);
			query.setParameter("var4",var4);
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
	public List<Reserva> buscarPorParametroCliente(int cod, Date periodo, QueryEnum quer) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List var = null;
		String op = "Busca Por Parametro "+quer.getValor();
		try {
			switch(quer) {
			case RESERVACLIENTEDATA:{
				Query query = em.createNamedQuery(quer.getValor());
				query.setParameter("var",cod);
				query.setParameter("var2",TratadorDeMascara.unirDataHora(periodo,"00:00:00"));
				query.setParameter("var3",TratadorDeMascara.unirDataHora(periodo,"24:00:00"));
				var = query.getResultList();
				break;
			}
			case RESERVACLIENTE:{
				System.out.println(" jjjjj");
				Query query = em.createNamedQuery(quer.getValor());
				query.setParameter("var",cod);
				var = query.getResultList();
				break;
			}
			case RESERVADATA:{
				Query query = em.createNamedQuery(quer.getValor());
				query.setParameter("var1",TratadorDeMascara.unirDataHora(periodo,"00:00:00"));
				query.setParameter("var2",TratadorDeMascara.unirDataHora(periodo,"24:00:00"));
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
