package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;

public class CategoriaPassageiroDao extends DaoGenerico<CategoriaPassageiro> implements ICategoriaPassageiroDao {
	private static CategoriaPassageiroDao instance;

	public static CategoriaPassageiroDao getInstance () {
		if(instance == null) {
			instance = new CategoriaPassageiroDao();
		}
		return instance;
	}
	private CategoriaPassageiroDao () {

	}
}
