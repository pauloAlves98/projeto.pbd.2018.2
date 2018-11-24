package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Endereco;

public class EnderecoDao extends DaoGenerico <Endereco>implements IEnderecoDao{
	private static EnderecoDao instance;
	 
	public static EnderecoDao getInstance () {
		if(instance == null) {
			instance = new EnderecoDao();
		}
		return instance;
	}
	private EnderecoDao () {
		
	}
}
