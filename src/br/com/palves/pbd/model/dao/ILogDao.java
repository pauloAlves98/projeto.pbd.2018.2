package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.Log;

public interface ILogDao {
	public Log persistOrMerge(Log log) throws DaoException;
	public Log findById(Integer id) throws DaoException;
	public List<Log> findAll() throws DaoException;
	public  Log deleteById (int id) throws DaoException;
}
