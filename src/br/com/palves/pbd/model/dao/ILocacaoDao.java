package br.com.palves.pbd.model.dao;

import java.util.Date;
import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.Reserva;

public interface ILocacaoDao {
//	public Locacao persistOrMerge(Locacao locacao) throws DaoException;
//	public  Locacao findById(Integer id) throws DaoException;
//	public List<Locacao> findAll() throws DaoException;
//	public  Locacao deleteById (int id) throws DaoException;
	public List<Locacao> buscarPorFiltro(String var1, Date var2, Date var3) throws DaoException;
	public List<Locacao> buscarPorVeiculo(String var1, int var2) throws DaoException;
}
