package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.Endereco;

public interface ICategoriaCargaDao {
	public CategoriaCarga persistOrMerge(CategoriaCarga categoriaCarga) throws DaoException;
	public  CategoriaCarga findById(Integer id) throws DaoException;
	public List< CategoriaCarga> findAll() throws DaoException;
	public  CategoriaCarga deleteById (int id) throws DaoException;
	public CategoriaCarga refresh (CategoriaCarga categoriaCarga) throws DaoException;
}
