package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Reserva;
import br.com.palves.pbd.model.bin.Revisao;

public interface IRevisaoDao {
	public Revisao persistOrMerge(Revisao revisao) throws DaoException;
	public Revisao findById(Integer id) throws DaoException;
	public List<Revisao> findAll() throws DaoException;
	public  Revisao deleteById (int id) throws DaoException;
}
