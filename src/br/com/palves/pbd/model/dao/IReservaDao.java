package br.com.palves.pbd.model.dao;

import java.util.Date;
import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Reserva;

public interface IReservaDao {
//	public Reserva persistOrMerge(Reserva reserva) throws DaoException;
//	public Reserva findById(Integer id) throws DaoException;
//	public List<Reserva> findAll() throws DaoException;
//	public  Reserva deleteById (int id) throws DaoException;
	public List<Reserva> buscarPorFiltro(String var1,Date var2,Date var3,int var4) throws DaoException;
}
