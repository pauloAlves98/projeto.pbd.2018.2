package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Log;
import br.com.palves.pbd.model.bin.PessoaFisica;

public class PessoaFisicaDao extends DaoGenerico<PessoaFisica> implements IPessoaFisicaDao {
	private static PessoaFisicaDao instance;

	public static PessoaFisicaDao getInstance () {
		if(instance == null) {
			instance = new PessoaFisicaDao();
		}
		return instance;
	}
	private PessoaFisicaDao() {

	}
}
