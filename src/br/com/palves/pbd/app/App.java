package br.com.palves.pbd.app;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * @author: P Alves
 * */
public class App extends Application{
	
	@Override
	public void start(Stage palcoStage) throws Exception {		
		Pane loginPane = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/Login.fxml"));
		Scene cenaLogin = new Scene(loginPane,1000,600);
		palcoStage.setScene(cenaLogin);
		palcoStage.centerOnScreen();
		palcoStage.show();
		Stage palcoStage1 = new Stage();
		palcoStage1.setScene(cenaLogin);
		palcoStage1.centerOnScreen();
		palcoStage1.show();
	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("Passou");
//		PessoaDao daoPessoa = PessoaDao.getInstance();
//		
//		Object obj[]=null;
//		try {
//			obj = daoPessoa.buscarIdPorLogin("nn@gmail.com","12345678");
//			if(obj == null)
//				System.out.println("Eh Nulo");
//			else
//			{
//				System.out.println("Tamanho: "+obj.length);
//				int j = (int) obj[1];
//				System.out.println("ID "+ obj[0] );
//				
//			
//			}
//		} catch (DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
//		CategoriaCarga cg = new CategoriaCarga();
//		CategoriaCargaDao dao = CategoriaCargaDao.getInstance();
//		cg.setNome("categoria Carga cg3");
//		cg.setPotenciaMotor(100);
//		//cg.setId(4);
//		
//		try {
//			cg = dao.findById(CategoriaCarga.class,4);
//			System.out.println(cg.getNome());
//			for(CategoriaCarga c:dao.findAll(CategoriaCarga.class)) {
//				System.out.println(c.getNome());
//				System.out.println(c.getId());
//				System.out.println(c.getPotenciaMotor());
//			}
//		} catch (DaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
	//	}
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
	public static void lookPadrao(){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Passando LookAndFeel padrão do sistema operacional
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			System.out.println("Nao Pegou");
		}

	}
	public static void lookWindows(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Passando LookAndFeel padrão do sistema operacional
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			System.out.println("Nao Pegou");
		}
	}
	public static void lookNimbus(){
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					    if ("Nimbus".equals(info.getName())) {
					        UIManager.setLookAndFeel(info.getClassName());
					        break;
					    }
					}
					} catch (Exception e) {
					   // If Nimbus is not available, you can set the GUI to another look and feel.
					}
	}
	
}
