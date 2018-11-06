package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;

public interface ICategoriaPassageiroDao {
	public CategoriaPassageiro persisteOrMerge(CategoriaPassageiro categoriaPassageiro) throws DaoException;
}
