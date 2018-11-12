package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;

public interface ICategoriaPassageiroDao {
	public CategoriaPassageiro persistOrMerge(CategoriaPassageiro categoriaPassageiro) throws DaoException;
	public CategoriaPassageiro findById(Integer id) throws DaoException;
	public List< CategoriaPassageiro> findAll() throws DaoException;
	public  CategoriaPassageiro deleteById (int id) throws DaoException;
}
