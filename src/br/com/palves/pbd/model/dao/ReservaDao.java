package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.bin.Reserva;

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
	
}
