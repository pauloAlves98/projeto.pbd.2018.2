package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaFisica;

public interface IPessoaFisicaDao {
	public PessoaFisica persistOrMerge(PessoaFisica pessoaF) throws DaoException;
}
