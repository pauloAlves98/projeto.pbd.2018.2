package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.bin.Filial;

public class FilialDao extends DaoGenerico<Filial>implements IFilialDao{
	private static FilialDao instance;

	public static FilialDao getInstance () {
		if(instance == null) {
			instance = new FilialDao();
		}
		return instance;
	}
	private FilialDao () {

	}
}
