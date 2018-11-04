package br.com.palves.pbd.model.dao;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Filial;

public interface IFilialDao {
	public Filial persistOrMerge(Filial filial) throws DaoException;
	
}
