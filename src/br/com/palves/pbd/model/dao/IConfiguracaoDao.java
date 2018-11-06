package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Configuracao;

public interface IConfiguracaoDao {
	public Configuracao persistOrMerge(Configuracao configuracao) throws DaoException;
}
