package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.enums.QueryEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Veiculo;

public interface IVeiculoDao {
//	public Veiculo persistOrMerge(Veiculo veiculo) throws DaoException;
//	public Veiculo findById(Integer id) throws DaoException;
//	public List<Veiculo> findAll() throws DaoException;
//	public  Veiculo deleteById (int id) throws DaoException;
	public Object[]buscarIdPorNome(String nome) throws DaoException;
	public List<Veiculo> buscarPorFiltro(String var1) throws DaoException;
	public List<Veiculo> buscarPorParametro(int codFilial,int codCategoria,String disc, double valorCategoria,String filtro,QueryEnum quer) throws DaoException;
}
