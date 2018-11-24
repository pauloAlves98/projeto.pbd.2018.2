package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.bin.Locacao;

public class LocacaoDao extends DaoGenerico<Locacao> implements ILocacaoDao{
	private static LocacaoDao instance;

	public static LocacaoDao getInstance () {
		if(instance == null) {
			
			instance = new LocacaoDao();
		}
		return instance;
	}
	private LocacaoDao() {
	}
}
