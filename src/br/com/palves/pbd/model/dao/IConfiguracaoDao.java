package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.Configuracao;

public interface IConfiguracaoDao {
	public Configuracao persistOrMerge(Configuracao configuracao) throws DaoException;
	public  Configuracao findById(Integer id) throws DaoException;
	public List< Configuracao> findAll() throws DaoException;
	public  Configuracao deleteById (int id) throws DaoException;
}
