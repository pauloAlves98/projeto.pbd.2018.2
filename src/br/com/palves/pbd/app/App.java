package br.com.palves.pbd.app;

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

//Atenção
/**
 * O Codigo contem Gatilhos e procedures , caso seja o primeiro acesso estes terão que ser criados para bom
 * funcionamento do sw, os mesmos estão disponiveis em no pacote SQL, classe SQLUtil.
 * Para efetuar os cruds instanciar o controle correspondente!
 * backup do banco disponivel na pastas documentacao!
 * */
public class App extends Application{
	public static Stage stage;
	public static Pane loginPane,cadastroClienteFisicoPane, cadastroClienteJuridicoPane,editarClienteFisicoPane,
	editarClienteJuridicoPane,menuClientePane,alterarSenhaPane,cadastroReservaPane,buscarReservaPane;
	public static Scene cenaLogin,cenaCadastroPF,cenaCadastroPJ,cenaMenuCliente;
	
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


}
