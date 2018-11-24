package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Pessoa;

public interface IPessoaDao {
	public Object[]buscarIdPorLogin(String email,String senha) throws DaoException;
}
