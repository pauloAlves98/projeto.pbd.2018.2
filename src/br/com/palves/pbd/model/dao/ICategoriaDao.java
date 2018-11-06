package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;

public interface ICategoriaDao {
	public Categoria persisteOrMerge(Categoria categoria) throws DaoException;
}
