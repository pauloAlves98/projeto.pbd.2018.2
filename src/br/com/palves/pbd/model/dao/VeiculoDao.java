package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Revisao;
import br.com.palves.pbd.model.bin.Veiculo;

public class VeiculoDao implements IVeiculoDao{
	private EntityManager em;
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
	public Veiculo persistOrMerge(Veiculo veiculo) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(veiculo.getId() == null)
				em.persist(veiculo);
			else {
				veiculo = em.merge(veiculo);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+veiculo.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return veiculo;
	}
	@Override
	public Veiculo findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Veiculo var = null;
		String op = "Busca ID";
		try {
			var = em.find(Veiculo.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<Veiculo> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Veiculo> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+Veiculo.class.getSimpleName()+" ed");
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
	public Veiculo deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Veiculo  var = null;
		try {
			em.getTransaction().begin();
			var = em.find(Veiculo.class,id);
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
