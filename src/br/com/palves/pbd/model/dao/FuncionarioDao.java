package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Funcionario;

public class FuncionarioDao extends DaoGenerico <Funcionario> implements IFuncionarioDao {
	private static FuncionarioDao instance;

	public static FuncionarioDao getInstance () {
		if(instance == null) {
			instance = new FuncionarioDao();
		}
		return instance;
	}
	private FuncionarioDao () {

	}


}
