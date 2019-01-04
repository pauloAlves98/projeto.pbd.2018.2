package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;

public interface IPessoaJuridicaDao {
//	public PessoaJuridica persistOrMerge(PessoaJuridica pessoaJ) throws DaoException;
//	public PessoaJuridica findById(Integer id) throws DaoException;
//	public List<PessoaJuridica> findAll() throws DaoException;
//	public  PessoaJuridica deleteById (int id) throws DaoException;
	public PessoaJuridica buscarPorCnpj(String cnpj) throws DaoException;
	public List<PessoaJuridica> buscarPorFiltro(String var) throws DaoException;
}
