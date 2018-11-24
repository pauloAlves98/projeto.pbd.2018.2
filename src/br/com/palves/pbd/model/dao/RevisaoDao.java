package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Reserva;
import br.com.palves.pbd.model.bin.Revisao;

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
}
