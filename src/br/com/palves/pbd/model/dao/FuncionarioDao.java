package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Funcionario;

public class FuncionarioDao implements IFuncionarioDao {
	private EntityManager em;
	private static FuncionarioDao instance;

	public static FuncionarioDao getInstance () {
		if(instance == null) {
			instance = new FuncionarioDao();
		}
		return instance;
	}
	private FuncionarioDao () {

	}
	@Override
	public Funcionario persistOrMerge(Funcionario funcionario) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(funcionario.getId() == null)
				em.persist(funcionario);
			else {
				funcionario = em.merge(funcionario);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em Funcionario: "+e.getMessage());
		}finally {
			em.close();
		}
		return funcionario;
	}
	@Override
	public Funcionario findById(Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		Funcionario var = null;
		String op = "Busca ID";
		try {
			var = em.find(Funcionario.class, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	@Override
	public List<Funcionario> findAll() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<Funcionario> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+Funcionario.class.getSimpleName()+" ed");
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
	public Funcionario deleteById(int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		Funcionario var = null;
		try {
			em.getTransaction().begin();
			var = em.find(Funcionario.class,id);
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
