package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaJuridica;

public interface IPessoaJuridicaDao {
	public PessoaJuridica persistOrMerge(PessoaJuridica pessoaJ) throws DaoException;
}
