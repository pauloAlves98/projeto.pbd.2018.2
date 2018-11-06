package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Funcionario;

public interface IFuncionarioDao {
	public Funcionario persistOrMerge(Funcionario funcionario) throws DaoException;
}
