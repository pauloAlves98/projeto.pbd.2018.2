package br.com.palves.pbd.app;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.dao.CategoriaCargaDao;

public class App {
	public static void main(String[] args) {
		CategoriaCarga cg = new CategoriaCarga();
		CategoriaCargaDao dao = CategoriaCargaDao.getInstance();
		
		cg.setId(4);
		
		try {
			cg = dao.findById(4);
			System.out.println(cg.getNome());
			System.out.println(cg.getPotenciaMotor());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		//A relação tambem tem que ter o id modificado no merge, caso não ele persist um novo registro!!
//		PessoaFisica cg = new PessoaFisica();
//		PessoaFisicaDao dao = PessoaFisicaDao.getInstance();
//		//cg.setLogin("NN@gmail.com");
//		cg.setNome("KIll");
//		cg.setCpf("1");
//		try {
//			dao.persistOrMerge(cg);
//			//System.out.println(dao.findById(7).getNome());
//			//dao.persisteOrMerge(cg);
//			List<PessoaFisica>ld = dao.findAll();
//			for(PessoaFisica e:ld) {
//				System.out.println("ID:"+e.getId());
//				System.out.println("UF:"+ e.getNome());
//				System.out.println("End id:" + e.getEndereco().getId());
//			}
//		}catch (DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/**
		Endereco end = new Endereco();
		EnderecoDao daoEnd = EnderecoDao.getInstance();

		end.setBairro("Caixa");
		end.setCep("56820-000");
		end.setRua("Rua 1");
		end.setCidade("Gotan");
		end.setUf("A1");

	try {
		//daoEnd.persistOrMerge(end);
		List<Endereco> ld = daoEnd.findAll();
		Endereco e1 = new Endereco();
		e1.setId(4);
		daoEnd.deleteById(4);
//		for(Endereco e:ld) {
//			System.out.println("ID:"+e.getId());
//			System.out.println("UF:"+ e.getUf());
//		}
	} catch (DaoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 */
		System.exit(0);

		//		Reserva r =new Reserva();
		//		ReservaDao rd = new ReservaDao();
		//		
		//		r.setDataHoraReserva(new Date());
		//		r.setDataHoraRetirada(new Date());
		//		
		//		try {
		//			rd.persistOrMerge(r);
		//		} catch (DaoException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

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


	}
}
