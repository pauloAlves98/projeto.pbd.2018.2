package br.com.palves.pbd.controller;

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
	private static FXMLLoader editarPFLoader,
	editarPJLoader,
	cadastroReservaLoader,
	cadastroFuncionarioLoader,buscarFuncionarioLoader,
	buscarFilialLoader,
	editarFuncionarioLoader;
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
							App.setCadastroClienteFisicoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroCliente.fxml")));
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
							App.setBuscarReservaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarReserva.fxml")));
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
							App.setBuscarPFPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarClientePF.fxml")));
							System.out.println("Buscar Pessoa Fisica ");
							App.setBuscarPJPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarClientePJ.fxml")));
							System.out.println("Buscar Pessoa Juridica ");
							editarFuncionarioLoader =  new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/EditarFuncionario.fxml"));
							App.setEditarFuncionarioPane(editarFuncionarioLoader.load());
							System.out.println("Editar funcionario ");
							App.setCadastroCategoriaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroCategoria.fxml")));
							System.out.println("Cadastro Categoria");
							App.setBuscarCategoriaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarCategoria.fxml")));
							System.out.println("Buscar Categoria");
							//Scene
							App.cenaLogin = new Scene(App.getLoginPane(),1000,600);
							App.cenaCadastro = new Scene(App.getMenuCadastros(),1000,700);
							//App.cenaCadastroPJ = new Scene(App.getCadastroClienteJuridicoPane(),1000,700);
							App.cenaMenuCliente =  new Scene(App.getMenuClientePane(),1000,700);
							App.cenaMenuFuncionario = new Scene(App.getMenuFuncionarioPane(),1000,700);
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
	
}
