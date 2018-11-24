package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.Log;

public class LogDao  extends DaoGenerico<Log> implements ILogDao{
	
	private static LogDao instance;

	public static LogDao getInstance () {
		if(instance == null) {
			instance = new LogDao();
		}
		return instance;
	}
	private LogDao() {

	}
	
}
