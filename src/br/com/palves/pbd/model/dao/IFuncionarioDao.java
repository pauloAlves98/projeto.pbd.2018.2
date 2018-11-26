package br.com.palves.pbd.model.dao;

import java.util.List;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Funcionario;

public interface IFuncionarioDao {
	public Object[]buscarIdPorLogin(String email,String senha) throws DaoException;
//	public Funcionario persistOrMerge(Funcionario funcionario) throws DaoException;
//	public  Funcionario findById(Integer id) throws DaoException;
//	public List<Funcionario> findAll() throws DaoException;
//	public  Funcionario deleteById (int id) throws DaoException;
}
