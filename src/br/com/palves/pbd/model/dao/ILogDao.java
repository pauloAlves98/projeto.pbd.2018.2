package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Log;

public interface ILogDao {
	public Log persistOrMerge(Log log) throws DaoException;
}
