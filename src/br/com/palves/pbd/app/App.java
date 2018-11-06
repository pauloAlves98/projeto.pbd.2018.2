package br.com.palves.pbd.app;

import java.util.Date;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Reserva;
import br.com.palves.pbd.model.dao.ReservaDao;

public class App {
	public static void main(String[] args) {
		//A relação tambem tem que ter o id modificado no merge, caso não ele persist um novo registro!!
		
		Reserva r =new Reserva();
		ReservaDao rd = new ReservaDao();
		
		r.setDataHoraReserva(new Date());
		r.setDataHoraRetirada(new Date());
		
		try {
			rd.persistOrMerge(r);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		Categoria c = new Categoria();
		ICategoriaDao daoC = new CategoriaDao();
		
		c.setNome("Categoria P1");
		
		try {
			daoC.persisteOrMerge(c);
			System.out.println(daoC.getClass().getName());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/**  
		Funcionario func = new Funcionario();
		IFuncionarioDao daoFunc=new FuncionarioDao();
		
		func.setCpf("12345678912343");
		func.setCargo("Administrador");
		func.setSalario("1500");
		
		try {
			daoFunc.persistOrMerge(func);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		/**Filial
		IFilialDao fdao = new FilialDao();
		Filial f = new Filial();
		Endereco e = new Endereco();
		e.setCidade("Konoha 1");
		e.setCep("56820-000");
		e.setUf("FL");
		
		f.setEndereco(e);
		f.setNome("Filial do Norte!");
		
		try {
			fdao.persistOrMerge(f);
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		/**
		 *Pessoa
		IPessoaFisicaDao dao = new PessoaFisicaDao();
		PessoaFisica pf = new PessoaFisica();
		PessoaJuridica pj = new PessoaJuridica();
		Endereco e = new Endereco(),e2 = new Endereco();
		
		//pf.setId(22);
		pf.setNome("Uzumaki Naruto");
		pf.setCpf("120.712.584-59");
		pf.setLogin("NN@gmail.com");
		pf.setSenha("12345678");
		pf.setnHabilitacao("1234321234");
		pf.setEndereco(e);
		//e.setId(35);
		e.setCidade("Konoha 1");
		e.setCep("56820-000");
		e.setUf("PF");
		
		//pj.setId(23);
		pj.setNome("Uzumaki Naruto LTDA.");
		pj.setCnpj("120.712.584-589");
		pj.setLogin("Juridica@gmail.com");
		pj.setSenha("12345678");
		pj.setIncEstadual("123213");
		e2.setCidade("Konoha 2");
		e2.setCep("56820-002");
		e2.setUf("PJ");
		//e2.setId(36);
		pj.setEndereco(e2);
		
		
		try {
			dao.persistOrMerge(pf);
			new PessoaJuridicaDao().persistOrMerge(pj);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
//		try {
//			
//		} catch (DaoException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		System.out.println("End");
		System.out.println(pf.getEndereco().getId().intValue()+"");
		System.out.println(e.getCidade());
		System.out.println("Pessoa F");
		System.out.println(pf.getId());
		System.out.println(pf.getCpf());
		*/
	}
}
