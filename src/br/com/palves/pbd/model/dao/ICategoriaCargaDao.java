package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;
import br.com.palves.pbd.model.bin.Endereco;

public interface ICategoriaCargaDao {
	public List< CategoriaCarga> buscarPorFiltro(String var) throws DaoException;
}
