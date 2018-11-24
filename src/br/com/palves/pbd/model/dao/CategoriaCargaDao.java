package br.com.palves.pbd.model.dao;

import javax.persistence.EntityManager;

import br.com.palves.pbd.model.bin.CategoriaCarga;

public class CategoriaCargaDao extends DaoGenerico<CategoriaCarga> implements ICategoriaCargaDao{
	private static CategoriaCargaDao instance;
	
	public static CategoriaCargaDao getInstance () {
		if(instance == null) {
			instance = new CategoriaCargaDao();
		}
		return instance;
	}
	private CategoriaCargaDao() {
		
	}
}
