package br.com.palves.pbd.controller;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.model.dao.DaoGenerico;
import br.com.palves.pbd.view.Alerta;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

public class Carregar implements Initializable {
	public static boolean detalhes  = false;//Para exibição das telas de detlhaes em locação!
	private static FXMLLoader editarPFLoader,
	editarPJLoader,
	cadastroReservaLoader,
	cadastroFuncionarioLoader,cadastroClientePFLoader,
	buscarFuncionarioLoader,
	buscarClientePFLoader,
	buscarVeiculoLoader,
	buscarFilialLoader,
	editarFuncionarioLoader,buscarReservaLoader,cadastroLocacaoCReservaLoader,
	cadastroLocacaoSReservaLoader,buscarClientePJLoader,buscarLocacaoLoader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		App.stage.setOnCloseRequest(WindowEvent -> System.exit(0));
		new javafx.concurrent.Service<Object>() {//uma Tarefa
			@Override
			protected Task<Object> createTask() {
				return new Task<Object>() {
					@Override
					protected Object call() throws Exception {
						try {
						
							App.setMenuCadastros(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/MenuCadastros.fxml")));
							App.setLoginPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/Login.fxml")));
							System.out.println("Login");
							cadastroClientePFLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroCliente.fxml"));
							App.setCadastroClienteFisicoPane(cadastroClientePFLoader.load());
							System.out.println("CadastroClientePF");
							App.setCadastroClienteJuridicoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroClientePJ.fxml")));
							System.out.println("CadastroClientePJ");
							editarPFLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/EditarClientePF.fxml"));
							App.setEditarClienteFisicoPane(editarPFLoader.load());
							System.out.println("EditarClientePF");
							editarPJLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/EditarClientePJ.fxml"));
							App.setEditarClienteJuridicoPane(editarPJLoader.load());
							System.out.println("EditarClientePJ");
							App.setMenuClientePane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/MenuCliente.fxml")));
							System.out.println("MenuCliente");
							App.setAlterarSenhaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/AlterarSenha.fxml")));
							System.out.println("AlterarSenha");
							//Reserva
							cadastroReservaLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroReserva.fxml"));
							App.setCadastroReservaPane(cadastroReservaLoader.load());
							System.out.println("CadastroReserva");
							buscarReservaLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarReserva.fxml"));
							App.setBuscarReservaPane(buscarReservaLoader.load());
							System.out.println("Buscar Reserva");
							//Funcionario
							App.setMenuFuncionarioPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/MenuFuncionario.fxml")));
							System.out.println("Menu Func");
							cadastroFuncionarioLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroFuncionario.fxml"));
							App.setCadastroFuncionarioPane(cadastroFuncionarioLoader.load());
							System.out.println("Cadastro Func");
							buscarFuncionarioLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarFuncionario.fxml"));
							App.setBuscarFuncionarioPane(buscarFuncionarioLoader.load());
							//Filial
							App.setCadastroFilialPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroFilial.fxml")));
							System.out.println("Cadastro Filial");
							buscarFilialLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarFilial.fxml"));
							App.setBuscarFilialPane(buscarFilialLoader.load());
							System.out.println("Buscar Filial");
							buscarClientePFLoader = new  FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarClientePF.fxml"));
							App.setBuscarPFPane(buscarClientePFLoader.load());
							System.out.println("Buscar Pessoa Fisica ");
							buscarClientePJLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarClientePJ.fxml"));
							App.setBuscarPJPane(buscarClientePJLoader.load());
							System.out.println("Buscar Pessoa Juridica ");
							editarFuncionarioLoader =  new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/EditarFuncionario.fxml"));
							App.setEditarFuncionarioPane(editarFuncionarioLoader.load());
							System.out.println("Editar funcionario ");
							App.setCadastroCategoriaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroCategoria.fxml")));
							System.out.println("Cadastro Categoria");
							App.setBuscarCategoriaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarCategoria.fxml")));
							System.out.println("Buscar Categoria");
							buscarVeiculoLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarVeiculo.fxml"));
							App.setBuscarVeiculoPane(buscarVeiculoLoader.load());
							System.out.println("Cadastro Veiculo");
							App.setCadastroVeiculoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroVeiculo.fxml")));
							System.out.println("Buscar Veiculo");
							cadastroLocacaoCReservaLoader =  new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroLocacaoCReserva.fxml"));
							App.setCadastroLocacaoCReservaPane(cadastroLocacaoCReservaLoader.load());
							System.out.println("Cadastro Locacao Com Reserva");
							App.setFechamentoDialog(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/FechamentoDialog.fxml")));
							App.setPermissaoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/TelaPermissao.fxml")));
							System.out.println("Permissao");
							cadastroLocacaoSReservaLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroLocacaoSReserva.fxml"));
							App.setCadastroLocacaoSReservaPane(cadastroLocacaoSReservaLoader .load());
							System.out.println("LOC S Reserva");
							buscarLocacaoLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarLocacao.fxml"));
							App.setBuscarLocacaoPane(buscarLocacaoLoader.load());
							System.out.println("Buscar Locação");
							App.setRetornoLocacaoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/RetornoLocacao.fxml")));
							System.out.println("Retorno Loc");
							//Scene
							App.cenaLogin = new Scene(App.getLoginPane(),Toolkit.getDefaultToolkit().getScreenSize().getWidth(),Toolkit.getDefaultToolkit().getScreenSize().getHeight());
							App.cenaCadastro = new Scene(App.getMenuCadastros(),Toolkit.getDefaultToolkit().getScreenSize().getWidth(),Toolkit.getDefaultToolkit().getScreenSize().getHeight());
							//App.cenaCadastroPJ = new Scene(App.getCadastroClienteJuridicoPane(),1000,700);
							App.cenaMenuCliente =  new Scene(App.getMenuClientePane(),Toolkit.getDefaultToolkit().getScreenSize().getWidth(),Toolkit.getDefaultToolkit().getScreenSize().getHeight());
							App.cenaMenuFuncionario = new Scene(App.getMenuFuncionarioPane(),Toolkit.getDefaultToolkit().getScreenSize().getWidth(),Toolkit.getDefaultToolkit().getScreenSize().getHeight());
							DaoGenerico.getInstance().primeiroAcesso();
							//App.sceneMenu = new Scene(App.menu);
						} catch (IOException e) {
							e.printStackTrace();
						}catch (Exception i) {
							Alerta a = Alerta.getInstance();
							a.setMensagem("Erro ao conectar BD!");
							a.show();
						}
						return null;
					}
					@Override
					protected void succeeded() {
						super.succeeded();
						App.stage.setResizable(true);
						App.stage.setScene(App.cenaLogin);
						App.stage.centerOnScreen();
						App.addTelas();
						App.stage.show();
					}	
				};

			}
		}.start();
	
	}
	public static FXMLLoader getEditarPFLoader() {
		return editarPFLoader;
	}
	public static FXMLLoader getEditarPJLoader() {
		return editarPJLoader;
	}
	public static FXMLLoader getCadastroReservaLoader() {
		return cadastroReservaLoader;
	}
	public static FXMLLoader getCadastroFuncionarioLoader() {
		return cadastroFuncionarioLoader;
	}
	public static FXMLLoader getBuscarFuncionarioLoader() {
		return buscarFuncionarioLoader;
	}
	public static FXMLLoader getBuscarFilialLoader() {
		return buscarFilialLoader;
	}
	public static FXMLLoader getEditarFuncionarioLoader() {
		return editarFuncionarioLoader;
	}
	public static FXMLLoader getCadastroLocacaoCReservaLoader() {
		return cadastroLocacaoCReservaLoader;
	}
	public static FXMLLoader getBuscarReservaLoader() {
		return buscarReservaLoader;
	}
	public static boolean isDetalhes() {
		return detalhes;
	}
	public static FXMLLoader getBuscarClientePFLoader() {
		return buscarClientePFLoader;
	}
	public static FXMLLoader getBuscarVeiculoLoader() {
		return buscarVeiculoLoader;
	}
	public static FXMLLoader getCadastroClientePFLoader() {
		return cadastroClientePFLoader;
	}
	public static FXMLLoader getBuscarClientePJLoader() {
		return buscarClientePJLoader;
	}
	public static FXMLLoader getCadastroLocacaoSReservaLoader() {
		return cadastroLocacaoSReservaLoader;
	}
	public static FXMLLoader getBuscarLocacaoLoader() {
		return buscarLocacaoLoader;
	}
	
	
	
}
