package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;

public interface ICategoriaDao {
	public Categoria persistOrMerge(Categoria categoria) throws DaoException;
	public  Categoria findById(Integer id) throws DaoException;
	public List< Categoria> findAll() throws DaoException;
	public  Categoria deleteById (int id) throws DaoException;
}
