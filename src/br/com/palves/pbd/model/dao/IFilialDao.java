package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.bin.Filial;

public interface IFilialDao {
//	public Filial persistOrMerge(Filial filial) throws DaoException;
//	public  Filial findById(Integer id) throws DaoException;
//	public List< Filial> findAll() throws DaoException;
//	public  Filial deleteById (int id) throws DaoException;
	public Object[] buscaIdPorNome(String nome) throws DaoException;
	public List< Filial> buscarPorParametro(String disc) throws DaoException;
	public List< Filial> buscarPorFiltro(String var) throws DaoException;
}
