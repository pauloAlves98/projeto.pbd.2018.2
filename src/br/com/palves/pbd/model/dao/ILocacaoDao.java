package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Locacao;

public interface ILocacaoDao {
	public Locacao persistOrMerge(Locacao locacao) throws DaoException;
}
