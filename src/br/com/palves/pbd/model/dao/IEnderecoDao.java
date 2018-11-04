package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Endereco;

public interface IEnderecoDao {
	public Endereco persistOrMerge(Endereco end) throws DaoException;
}
