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
	private static FXMLLoader editarPFLoader,editarPJLoader,cadastroReservaLoader;
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
						
							App.loginPane = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/Login.fxml"));
							System.out.println("Login");
							App.cadastroClienteFisicoPane = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroCliente.fxml"));
							System.out.println("CadastroClientePF");
							App.cadastroClienteJuridicoPane = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroClientePJ.fxml"));
							System.out.println("CadastroClientePJ");
							editarPFLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/EditarClientePF.fxml"));
							App.editarClienteFisicoPane = editarPFLoader.load();
							System.out.println("EditarClientePF");
							editarPJLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/EditarClientePJ.fxml"));
							App.editarClienteJuridicoPane = editarPJLoader.load();
							System.out.println("EditarClientePJ");
							App.menuClientePane = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/MenuCliente.fxml"));
							System.out.println("MenuCliente");
							App.alterarSenhaPane = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/AlterarSenha.fxml"));
							System.out.println("AlterarSenha");
							cadastroReservaLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroReserva.fxml"));
							App.cadastroReservaPane = cadastroReservaLoader.load();
							System.out.println("CadastroReserva");
							App.buscarReservaPane = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarReserva.fxml"));
							System.out.println("Buscar Reserva");
							//Scene
							App.cenaLogin = new Scene(App.loginPane,1000,600);
							App.cenaCadastroPF = new Scene(App.cadastroClienteFisicoPane,1000,700);
							App.cenaCadastroPJ = new Scene(App.cadastroClienteJuridicoPane,1000,700);
							App.cenaMenuCliente =  new Scene(App.menuClientePane,1000,700);
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
	
	
}
