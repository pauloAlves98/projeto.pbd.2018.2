package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;

public class PessoaJuridicaDao extends DaoGenerico<PessoaJuridica> implements IPessoaJuridicaDao{
	private static PessoaJuridicaDao instance;

	public static PessoaJuridicaDao getInstance () {
		if(instance == null) {
			instance = new PessoaJuridicaDao();
		}
		return instance;
	}
	private PessoaJuridicaDao() {

	}
}
