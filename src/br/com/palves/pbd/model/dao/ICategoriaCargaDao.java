package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;

public interface ICategoriaCargaDao {
	public CategoriaCarga persisteOrMerge(CategoriaCarga categoriaCarga) throws DaoException;
}
