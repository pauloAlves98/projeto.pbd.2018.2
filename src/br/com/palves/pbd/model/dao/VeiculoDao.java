package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Revisao;
import br.com.palves.pbd.model.bin.Veiculo;

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
}
