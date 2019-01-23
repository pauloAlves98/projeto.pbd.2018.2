package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Generico;

public class DaoGenerico <T extends Generico>{
	protected EntityManager em;
	private static DaoGenerico instance;
	protected DaoGenerico() {
		
	}
	public static DaoGenerico getInstance() {
		if(instance==null)
			instance=new DaoGenerico();
		return instance;
	}

	public static void setInstance(DaoGenerico instance) {
		DaoGenerico.instance = instance;
	}

	public T persistOrMerge(T obj) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Persist";
		try {
			em.getTransaction().begin();
			if(obj.getId() == null)
				em.persist(obj);
			else {
				obj = em.merge(obj);
				op = "Merge";
			}
			em.getTransaction().commit();
		}
		catch(org.hibernate.exception.ConstraintViolationException e1) {
			System.out.println("Constraint Exception");
		}
		catch(Exception e){
			//Desembrulha as Exceptions;
			for (Throwable t = e.getCause(); t != null; t = t.getCause()) {//sai pegando a causa da causa!
				if(t instanceof org.postgresql.util.PSQLException) {
					org.postgresql.util.PSQLException psqlException =(org.postgresql.util.PSQLException)t; //Faz o cast
					if (psqlException.getMessage().trim().contains("already exists") &&  
							psqlException.getMessage().trim().contains("cpf")){
						e.printStackTrace();
						em.getTransaction().rollback();
						throw new DaoException("O CPF já existe!");
					}
					if (psqlException.getMessage().trim().contains("already exists") &&  
							psqlException.getMessage().trim().contains("cnpj")){
						e.printStackTrace();
						em.getTransaction().rollback();
						throw new DaoException("O CNPJ já existe!");
					}
					if (psqlException.getMessage().trim().contains("already exists") &&  
							psqlException.getMessage().trim().contains("login")){
						e.printStackTrace();
						em.getTransaction().rollback();
						throw new DaoException("O Login já existe: Tente outro login!");
					}
					if (psqlException.getMessage().trim().contains("already exists") &&  
							psqlException.getMessage().trim().contains("nome")){
						e.printStackTrace();
						em.getTransaction().rollback();
						throw new DaoException("O Nome já existe: Tente outro Nome!");
						
					}
					if (psqlException.getMessage().trim().contains("already exists") &&  
							psqlException.getMessage().trim().contains("numero_chassi")){
						e.printStackTrace();
						em.getTransaction().rollback();
						throw new DaoException("O Chassi já existe!");
					}
					if (psqlException.getMessage().trim().contains("already exists") &&  
						psqlException.getMessage().trim().contains("placa")){
						e.printStackTrace();
						em.getTransaction().rollback();
						throw new DaoException("A placa já existe!");
					}
				}
			}
			//Caso não encontre um PSQLException
			em.getTransaction().rollback();
			e.printStackTrace();
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getSimpleName()+": Contate o ADM!");
		}
		finally {
			em.close();
		}
		return obj;
	}
	public T findById(Class <T>clazz ,Integer id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		T var = null;
		String op = "Busca ID";
		try {
			var = em.find(clazz, id);
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	public List<T> findAll(Class <T>clazz) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		List<T> var = null;
		String op = "Buscar ALL";
		try {
			Query query = em.createQuery(" select ed from "+clazz.getSimpleName()+" ed");
			var = query.getResultList( );
			if(var.size()<=0)
				return null;
		}
		catch(Exception e){
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return var;
	}
	public T deleteById(Class<T>clazz, int id) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Delete por ID";
		T var = null;
		try {
			em.getTransaction().begin();
			var = em.find(clazz,id);
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
	public T refresh(T obj) throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		String op = "Refresh";
		try {
			em.getTransaction().begin();
			em.refresh(obj);
			em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Realizar "+op+" em "+this.getClass().getName()+":"+e.getMessage());
		}finally {
			em.close();
		}
		return obj;
	}
	public void primeiroAcesso() throws DaoException {
		em = ConnectionFactory.getInstance().getConnection();
		try {
			//em.getTransaction().begin();
			//em.createQuery("Pessoa e from pessoa e where e.id = 1");
			//em.getTransaction().commit();
		}
		catch(Exception e){
			em.getTransaction().rollback();
			throw new DaoException("Erro ao Conectar ao Banco!");
		}finally {
			em.close();
		}
	}
}
