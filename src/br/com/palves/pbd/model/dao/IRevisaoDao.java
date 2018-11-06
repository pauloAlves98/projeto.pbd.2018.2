package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Revisao;

public interface IRevisaoDao {
	public Revisao persistOrMerge(Revisao revisao) throws DaoException;
}
