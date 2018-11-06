package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Veiculo;

public interface IVeiculoDao {
	public Veiculo persistOrMerge(Veiculo veiculo) throws DaoException;
}
