package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;

public interface ICategoriaDao {
	public List<Categoria> buscarTodosPorDiscriminador(String disc) throws DaoException;
	public Object[] buscarIdeDiscriminadorPorNome(String nome) throws DaoException;
	public List<Categoria> buscarPorParametro(String disc) throws DaoException;
}
