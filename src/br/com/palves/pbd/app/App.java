package br.com.palves.pbd.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.palves.pbd.enums.TransicaoTelaEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * @author: P Alves
 * */
//– os veículos presentemente alugados pela filial, o ponto de entrega
//(caso seja diferente do local de locação) e data de entrega
//prevista.
//Atenção
/**
 * O Codigo contem Gatilhos e procedures , caso seja o primeiro acesso estes terão que ser criados para bom
 * funcionamento do sw, os mesmos estão disponiveis em no pacote SQL, classe SQLUtil.
 * Para efetuar os cruds instanciar o controle correspondente!
 * backup do banco disponivel na pastas documentacao!
 * */
public class App extends Application{
	private static List<Pane>listaDeTelas = new ArrayList<>();
	public static Stage stage;
	private static Pane loginPane,cadastroClienteFisicoPane, cadastroClienteJuridicoPane,editarClienteFisicoPane,
	editarClienteJuridicoPane,menuClientePane,alterarSenhaPane,cadastroReservaPane,buscarReservaPane,
	menuFuncionarioPane,cadastroFuncionarioPane,buscarFuncionarioPane,cadastroFilialPane,buscarFilialPane,menuCadastros,
	buscarPFPane,buscarPJPane,editarFuncionarioPane,cadastroCategoriaPane,buscarCategoriaPane,cadastroVeiculoPane,
	buscarVeiculoPane,cadastroLocacaoCReservaPane;

	public static Scene cenaLogin,cenaCadastro,cenaMenuCliente,cenaMenuFuncionario;
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage palcoStage) throws Exception {		
		stage = palcoStage;
		Parent load = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/Load.fxml"));
		Scene cenaLoad = new Scene(load,600,400);
		palcoStage.setScene(cenaLoad);
		palcoStage.setResizable(false);
		palcoStage.centerOnScreen();
		palcoStage.show();	
	
	}
	public static Pane retornaTela(TransicaoTelaEnum t) {
		switch(t) {
			case LOGIN:{
				return loginPane;
			}
			case CADASTRO_PESSOAFISICA:{
				return cadastroClienteFisicoPane;
			}
			case CADASTRO_PESSOAJURIDICA:{
				return cadastroClienteJuridicoPane;
			}
			case ALTERAR_SENHA:{
				return alterarSenhaPane;
			}
			case EDITAR_CLIENTEFISICO:{
				return editarClienteFisicoPane;
			}
			case EDITAR_CLIENTEJURIDICO:{
				return editarClienteJuridicoPane;
			}
		}
		return loginPane;
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
	
	public static Pane getAlterarSenhaPane() {
		return alterarSenhaPane;
	}
	public static void setAlterarSenhaPane(Pane alterarSenhaPane) {
		App.alterarSenhaPane = alterarSenhaPane;
	}
	public static Pane getLoginPane() {
		return loginPane;
	}
	public static Pane getCadastroClienteFisicoPane() {
		return cadastroClienteFisicoPane;
	}
	public static Pane getCadastroClienteJuridicoPane() {
		return cadastroClienteJuridicoPane;
	}
	public static Pane getEditarClienteFisicoPane() {
		return editarClienteFisicoPane;
	}
	public static Pane getEditarClienteJuridicoPane() {
		return editarClienteJuridicoPane;
	}
	public static Pane getMenuClientePane() {
		return menuClientePane;
	}
	public static Pane getCadastroReservaPane() {
		return cadastroReservaPane;
	}
	public static Pane getBuscarReservaPane() {
		return buscarReservaPane;
	}
	public static Pane getMenuFuncionarioPane() {
		return menuFuncionarioPane;
	}
	public static Pane getCadastroFuncionarioPane() {
		return cadastroFuncionarioPane;
	}
	public static Pane getBuscarFuncionarioPane() {
		return buscarFuncionarioPane;
	}
	public static Scene getCenaLogin() {
		return cenaLogin;
	}
	public static Scene getCenaCadastro() {
		return cenaCadastro;
	}
	public static Scene getCenaMenuCliente() {
		return cenaMenuCliente;
	}
	public static Scene getCenaMenuFuncionario() {
		return cenaMenuFuncionario;
	}
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		App.stage = stage;
	}
	public static void setLoginPane(Pane loginPane) {
		App.loginPane = loginPane;
	}
	public static void setCadastroClienteFisicoPane(Pane cadastroClienteFisicoPane) {
		App.cadastroClienteFisicoPane = cadastroClienteFisicoPane;
	}
	public static void setCadastroClienteJuridicoPane(Pane cadastroClienteJuridicoPane) {
		App.cadastroClienteJuridicoPane = cadastroClienteJuridicoPane;
	}
	public static void setEditarClienteFisicoPane(Pane editarClienteFisicoPane) {
		App.editarClienteFisicoPane = editarClienteFisicoPane;
	}
	public static void setEditarClienteJuridicoPane(Pane editarClienteJuridicoPane) {
		App.editarClienteJuridicoPane = editarClienteJuridicoPane;
	}
	public static void setMenuClientePane(Pane menuClientePane) {
		App.menuClientePane = menuClientePane;
	}
	public static void setCadastroReservaPane(Pane cadastroReservaPane) {
		App.cadastroReservaPane = cadastroReservaPane;
	}
	public static void setBuscarReservaPane(Pane buscarReservaPane) {
		App.buscarReservaPane = buscarReservaPane;
	}
	public static void setMenuFuncionarioPane(Pane menuFuncionarioPane) {
		App.menuFuncionarioPane = menuFuncionarioPane;
	}
	public static void setCadastroFuncionarioPane(Pane cadastroFuncionarioPane) {
		App.cadastroFuncionarioPane = cadastroFuncionarioPane;
	}
	public static void setBuscarFuncionarioPane(Pane buscarFuncionarioPane) {
		App.buscarFuncionarioPane = buscarFuncionarioPane;
	}
	public static void setCenaLogin(Scene cenaLogin) {
		App.cenaLogin = cenaLogin;
	}
	public static void setCenaCadastro(Scene cenaCadastroPF) {
		App.cenaCadastro = cenaCadastroPF;
	}
	public static void setCenaMenuCliente(Scene cenaMenuCliente) {
		App.cenaMenuCliente = cenaMenuCliente;
	}
	public static void setCenaMenuFuncionario(Scene cenaMenuFuncionario) {
		App.cenaMenuFuncionario = cenaMenuFuncionario;
	}
	public static Pane getCadastroFilialPane() {
		return cadastroFilialPane;
	}
	public static Pane getBuscarFilialPane() {
		return buscarFilialPane;
	}
	public static void setCadastroFilialPane(Pane cadastroFilialPane) {
		App.cadastroFilialPane = cadastroFilialPane;
	}
	public static void setBuscarFilialPane(Pane buscarFilialPane) {
		App.buscarFilialPane = buscarFilialPane;
	}
	public static Pane getMenuCadastros() {
		return menuCadastros;
	}
	public static void setMenuCadastros(Pane menuCadastros) {
		App.menuCadastros = menuCadastros;
	}
	public static Pane getBuscarPFPane() {
		return buscarPFPane;
	}
	public static void setBuscarPFPane(Pane buscarPFPane) {
		App.buscarPFPane = buscarPFPane;
	}
	public static Pane getBuscarPJPane() {
		return buscarPJPane;
	}
	public static void setBuscarPJPane(Pane buscarPJPane) {
		App.buscarPJPane = buscarPJPane;
	}
	public static Pane getEditarFuncionarioPane() {
		return editarFuncionarioPane;
	}
	public static void setEditarFuncionarioPane(Pane editarFuncionarioPane) {
		App.editarFuncionarioPane = editarFuncionarioPane;
	}
	public static List<Pane> getListaDeTelas() {
		return listaDeTelas;
	}
	
	public static Pane getCadastroCategoriaPane() {
		return cadastroCategoriaPane;
	}
	public static void setCadastroCategoriaPane(Pane cadastroCategoriaPane) {
		App.cadastroCategoriaPane = cadastroCategoriaPane;
	}
	
	public static Pane getBuscarCategoriaPane() {
		return buscarCategoriaPane;
	}
	public static void setBuscarCategoriaPane(Pane buscarCategoriaPane) {
		App.buscarCategoriaPane = buscarCategoriaPane;
	}
	
	public static Pane getCadastroVeiculoPane() {
		return cadastroVeiculoPane;
	}
	public static void setCadastroVeiculoPane(Pane cadastroVeiculoPane) {
		App.cadastroVeiculoPane = cadastroVeiculoPane;
	}
	
	public static Pane getBuscarVeiculoPane() {
		return buscarVeiculoPane;
	}
	public static void setBuscarVeiculoPane(Pane buscarVeiculoPane) {
		App.buscarVeiculoPane = buscarVeiculoPane;
	}
	
	public static Pane getCadastroLocacaoCReservaPane() {
		return cadastroLocacaoCReservaPane;
	}
	public static void setCadastroLocacaoCReservaPane(Pane cadastroLocacaoCReservaPane) {
		App.cadastroLocacaoCReservaPane = cadastroLocacaoCReservaPane;
	}
	public static void addTelas() {
		listaDeTelas.add(loginPane);
		listaDeTelas.add(cadastroClienteFisicoPane);
		listaDeTelas.add( cadastroClienteJuridicoPane);
		listaDeTelas.add(editarClienteFisicoPane);
		listaDeTelas.add(editarClienteJuridicoPane);
		listaDeTelas.add(alterarSenhaPane);
		listaDeTelas.add(cadastroReservaPane);
		listaDeTelas.add(buscarReservaPane);
		listaDeTelas.add(cadastroFuncionarioPane);
		listaDeTelas.add(buscarFuncionarioPane);
		listaDeTelas.add(cadastroFilialPane);
		listaDeTelas.add(buscarFilialPane);
		listaDeTelas.add(buscarPFPane);
		listaDeTelas.add(buscarPJPane);
		listaDeTelas.add(editarFuncionarioPane);
		listaDeTelas.add(cadastroCategoriaPane);
		listaDeTelas.add(buscarCategoriaPane);
		listaDeTelas.add(cadastroVeiculoPane);
		listaDeTelas.add(buscarVeiculoPane);
		listaDeTelas.add(cadastroLocacaoCReservaPane);
	}
	
}
