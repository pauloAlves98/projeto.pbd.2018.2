package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaFisica;

public interface IPessoaFisicaDao {
	public Object procedureValidaHabilitacao(String nHab) throws DaoException;
//	public PessoaFisica persistOrMerge(PessoaFisica pessoaF) throws DaoException;
//	public PessoaFisica findById(Integer id) throws DaoException;
//	public List<PessoaFisica> findAll() throws DaoException;
//	public  PessoaFisica deleteById (int id) throws DaoException;
	public Object[]buscarIdPorNome(String nome) throws DaoException;
	public PessoaFisica buscarPorCpf(String cpf) throws DaoException;
    public List<PessoaFisica> buscarPorFiltro(String var) throws DaoException;
    public List<PessoaFisica> buscarPorParametro(String var) throws DaoException;//Na busca por parametro o cliente tem que estar ativo!
	public Object procedureValidaPorCPF(String cpf) throws DaoException;
}
