package br.com.palves.pbd.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.palves.pbd.connection.ConnectionFactory;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;

public class CategoriaDao extends DaoGenerico<Categoria> implements ICategoriaDao{
	private static CategoriaDao instance;
	
	public static CategoriaDao getInstance () {
		if(instance == null) {
			instance = new CategoriaDao();
		}
		return instance;
	}
	private CategoriaDao () {
		
	}
}
