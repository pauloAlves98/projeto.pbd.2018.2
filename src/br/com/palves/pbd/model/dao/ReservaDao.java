package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Reserva;

public class ReservaDao implements IReservaDao{
	private EntityManager em;

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
	
	
}
