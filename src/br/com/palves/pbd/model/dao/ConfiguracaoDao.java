package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;
import br.com.palves.pbd.model.bin.Configuracao;

public class ConfiguracaoDao extends DaoGenerico<Configuracao>implements IConfiguracaoDao{
	private static ConfiguracaoDao instance;

	public static ConfiguracaoDao getInstance () {
		if(instance == null) {
			instance = new ConfiguracaoDao();
		}
		return instance;
	}
	private ConfiguracaoDao () {

	}
}
