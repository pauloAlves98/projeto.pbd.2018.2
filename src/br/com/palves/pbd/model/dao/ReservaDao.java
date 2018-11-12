package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.bin.Reserva;

public class ReservaDao implements IReservaDao{
	private EntityManager em;
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
	public Reserva persistOrMerge(Reserva reserva) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(reserva.getId() == null)
				em.persist(reserva);
			else {
				reserva = em.merge(reserva);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+reserva.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return reserva;
	}
	@Override
	public Reserva findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Reserva var = null;
		String op = "Busca ID";
		try {
			var = em.find(Reserva.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<Reserva> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Reserva> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+Reserva.class.getSimpleName()+" ed");
			var = query.getResultList();
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public Reserva deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Reserva  var = null;
		try {
			em.getTransaction().begin();
			var = em.find(Reserva.class,id);
			em.remove(var);
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	
	
}
