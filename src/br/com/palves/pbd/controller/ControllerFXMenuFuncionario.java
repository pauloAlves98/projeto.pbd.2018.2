package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXNodesList;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.LimparCampo;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ControllerFXMenuFuncionario implements Initializable{
	@FXML
	private Label nomeUserLabel;
	private static Label nomeL;
	static JFXNodesList nodeL;
    @FXML
    private JFXNodesList nodeList;
	@FXML
	private MenuItem cadastrarCategoriaItem;

	@FXML
	private MenuItem buscarCategoriaItem;

	@FXML
	private MenuItem cadastrarVeiculoItem;

	@FXML
	private MenuItem buscarVeiculoItem;

	@FXML
	private MenuItem realizarLocacaoItem;

	@FXML
	private MenuItem buscarLocacaoItem;

	@FXML
	private MenuItem retornoLocacaoItem;

	@FXML
	private MenuItem cadastrarFilialItem;

	@FXML
	private MenuItem buscarFilialItem;

	@FXML
	private MenuItem cadastrarClienteItem;

	@FXML
	private MenuItem buscarClienteItem;

	@FXML
	private MenuItem resetSenhaClienteItem;

	@FXML
	private MenuItem configuracaoItem;

	@FXML
	private MenuItem cadastroFuncionarioItem;

	@FXML
	private MenuItem buscarFuncionarioItem;

	@FXML
	private MenuItem alterarDadosItem;
	
	@FXML
	private MenuItem alterarSenhaItem;
	@FXML
	private MenuItem logoutItem;
	@FXML
	private MenuItem cadastrarPJItem;
	@FXML
	private MenuItem buscarPJItem;
	@FXML
	private BorderPane painelCentral;
	@FXML
	private MenuItem realizarLocacaoSReservaItem;
	@FXML
	void eventoItens(ActionEvent event) {
		if(event.getSource()==this.cadastroFuncionarioItem) {
			this.animationGeral(App.getCadastroFuncionarioPane());
			LimparCampo.limparCamposFX(App.getCadastroFuncionarioPane().getChildren());
			ControllerFXCadastroFuncionario cf =(ControllerFXCadastroFuncionario)Carregar.getCadastroFuncionarioLoader().getController();
			cf.atualizarFilial();
		}
		else if(event.getSource()==this.buscarFuncionarioItem) {
			this.animationGeral(App.getBuscarFuncionarioPane());
			LimparCampo.limparCamposFX(App.getBuscarFuncionarioPane().getChildren());
			ControllerFXBuscarFuncionario cf =(ControllerFXBuscarFuncionario)Carregar.getBuscarFuncionarioLoader().getController();
			cf.limparTudo();
		}
		else if(event.getSource()==this.cadastrarFilialItem) {
			this.animationGeral(App.getCadastroFilialPane());
			LimparCampo.limparCamposFXTOTAL(App.getCadastroFilialPane().getChildren());
		}
		else if(event.getSource()==this.buscarFilialItem) {
			this.animationGeral(App.getBuscarFilialPane());
			ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			cf.limparTudo();
		}
		else if(event.getSource()==this.cadastrarClienteItem) {
			this.animationGeral(App.getCadastroClienteFisicoPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			LimparCampo.limparCamposFX(App.getCadastroClienteFisicoPane().getChildren());
		}
		else if(event.getSource()==this.buscarClienteItem) {
			this.animationGeral(App.getBuscarPFPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			//LimparCampo.limparCamposFX(App.getCadastroClienteFisicoPane().getChildren());
			LimparCampo.limparCamposFXTOTAL(App.getCadastroClienteFisicoPane().getChildren());
		}
		else if(event.getSource()==this.cadastrarPJItem) {
			this.animationGeral(App.getCadastroClienteJuridicoPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			LimparCampo.limparCamposFX(App.getCadastroClienteJuridicoPane().getChildren());
		}
		else if(event.getSource()==this.buscarPJItem) {
			this.animationGeral(App.getBuscarPJPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getBuscarPJPane().getChildren());
		}
		else if(event.getSource()==this.alterarDadosItem) {
			this.animationGeral(App.getEditarFuncionarioPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			ControllerFXEditarFuncionario cf =(ControllerFXEditarFuncionario)Carregar.getEditarFuncionarioLoader().getController();
			cf.carregarFuncionario(Corrente.funcionario);
		}
		else if(event.getSource()==this.alterarSenhaItem) {
			this.animationGeral(App.getAlterarSenhaPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getAlterarSenhaPane().getChildren());
		}
		else if(event.getSource()==this.cadastrarCategoriaItem) {
			this.animationGeral(App.getCadastroCategoriaPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getCadastroCategoriaPane().getChildren());
		}
		else if(event.getSource()==this.buscarCategoriaItem) {
			this.animationGeral(App.getBuscarCategoriaPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getBuscarCategoriaPane().getChildren());
		}
		else if(event.getSource()==this.cadastrarVeiculoItem) {
			this.animationGeral(App.getCadastroVeiculoPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getCadastroVeiculoPane().getChildren());
		}
		else if(event.getSource()==this.buscarVeiculoItem) {
			this.animationGeral(App.getBuscarVeiculoPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getBuscarVeiculoPane().getChildren());
		}
		else if(event.getSource()==this.realizarLocacaoItem) {//Com reserva
			this.animationGeral(App.getCadastroLocacaoCReservaPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			ControllerFXCadastroLocacaoCReserva c =(ControllerFXCadastroLocacaoCReserva ) Carregar.getCadastroLocacaoCReservaLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getCadastroLocacaoCReservaPane().getChildren());
			c.getNomeFilialRetirada().setText(Corrente.funcionario.getFilial().getNome());
		}
		else if(event.getSource()==this.realizarLocacaoSReservaItem) {//Sem reserva
			this.animationGeral(App.getCadastroLocacaoSReservaPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			ControllerFXCadastroLocacaoSReserva c = (ControllerFXCadastroLocacaoSReserva)Carregar.getCadastroLocacaoSReservaLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getCadastroLocacaoSReservaPane().getChildren());
			c.getNomeFilialRetirada().setText(Corrente.funcionario.getFilial().getNome());
		}
		else if(event.getSource()==this.buscarLocacaoItem) {//Sem reserva
			this.animationGeral(App.getBuscarLocacaoPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			//ControllerFXCadastroLocacaoSReserva c = (ControllerFXCadastroLocacaoSReserva)Carregar.getCadastroLocacaoSReservaLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getBuscarLocacaoPane().getChildren());
			//c.getNomeFilialRetirada().setText(Corrente.funcionario.getFilial().getNome());
		}
		else if(event.getSource()==this.retornoLocacaoItem) {//Sem reserva
			this.animationGeral(App.getRetornoLocacaoPane());
			//ControllerFXBuscarFilial cf =(ControllerFXBuscarFilial)Carregar.getBuscarFilialLoader().getController();
			//ControllerFXCadastroLocacaoSReserva c = (ControllerFXCadastroLocacaoSReserva)Carregar.getCadastroLocacaoSReservaLoader().getController();
			LimparCampo.limparCamposFXTOTAL(App.getRetornoLocacaoPane().getChildren());
			//c.getNomeFilialRetirada().setText(Corrente.funcionario.getFilial().getNome());
		}
		else if(event.getSource()==this.logoutItem) {
			LimparCampo.limparLogout();
			App.stage.setScene(App.cenaLogin);
			App.stage.centerOnScreen();
			App.stage.show();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomeL = nomeUserLabel;
		nodeL = nodeList;
		
	}
//Transições<>
	private void animationGeral(Pane pane) {
		//Set Y of second scene to Height of window
		pane.translateYProperty().set(App.getCenaMenuFuncionario().getHeight());
		//Add second scene. Now both first and second scene is present
		painelCentral.getChildren().clear();
		painelCentral.setCenter(pane);
		//Create new TimeLine animation
		Timeline timeline = new Timeline();
		//Animate Y property
		KeyValue kv = new KeyValue(pane.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		//After completing animation, remove first scene
		timeline.setOnFinished(t -> {
			//painelCentral.getChildren().remove(App.cadastroClienteFisicoPane);
		});
		timeline.play();
	}
	//Transições</>
	//Other
	public static Label getNomeL() {
		return nomeL;
	}
	public static void atualizarNome(String novoNome) {
		String vt[] =  novoNome.split(" ");
		String nome=" ";
		for(int i = 0;i<vt.length;i++) {
			if(i==0)
				nome=vt[i];	
			else
				nome=nome+" "+vt[i].substring(0,1)+".";
		}
		nomeL.setText(nome);
	}
	

}
