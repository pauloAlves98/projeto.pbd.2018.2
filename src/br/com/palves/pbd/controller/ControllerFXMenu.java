package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.util.Duration;

public class ControllerFXMenu  implements Initializable {


	private static Label nomeL;
	@FXML
	private Label nomeUserLabel;

	@FXML
	private MenuItem realizarReservaItem;

	@FXML
	private MenuItem buscarReservaItem;

	@FXML
	private MenuItem alterarDadosItem;

	@FXML
	private MenuItem alterarSenhaItem;

	@FXML
	private MenuItem logoutItem;

	@FXML
	private BorderPane painelCentral;

	@FXML
	void alterarSenha(ActionEvent event) {
		this.animationAlterarSenha();
		//		painelCentral.getChildren().clear();
		//		painelCentral.setCenter(App.retornaTela(TransicaoTelaEnum.ALTERAR_SENHA));
		//olhar qua cliente estta logado
		//    	painelCentral.getChildren().clear();
		//    	painelCentral.setCenter(App.editarClienteFisicoPane);
		//painelCentral.s
	}
	@FXML
	void editar(ActionEvent event) {
		//olhar qua cliente estta logado
		if(Corrente.usuarioFisico!=null) {
			this.animationEditarPF();
			ControllerFXEditarCliente ed =(ControllerFXEditarCliente)Carregar.getEditarPFLoader().getController();
			((ControllerFXEditarCliente) ed).preencher();
		}
		else {//Eh juridico!
			this.animationEditarPJ();
			ControllerFXEditarClientePJ ed =(ControllerFXEditarClientePJ)Carregar.getEditarPJLoader().getController();
			((ControllerFXEditarClientePJ) ed).preencher();
		}
	}
    @FXML
    private void buscarReserva(ActionEvent event) {
    	this.animationBuscarReserva();
//    	painelCentral.getChildren().clear();
//		painelCentral.setCenter(App.buscarReservaPane);
    }
    @FXML
   private  void realizarReserva(ActionEvent event) {
    	this.animationCadastroReserva();
    	ControllerFXCadastroReserva ed =(ControllerFXCadastroReserva)Carregar.getCadastroReservaLoader().getController();
		((ControllerFXCadastroReserva) ed).atualizarFilialeCategoria();
    }
    @FXML
    private void sair(ActionEvent event) {
    	LimparCampo.limparLogout();
    	App.stage.setScene(App.cenaLogin);
		App.stage.centerOnScreen();
		App.stage.show();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nomeL = nomeUserLabel;
	}
	
	
	//Animations
	private void animationAlterarSenha() {
		//Set Y of second scene to Height of window
		App.getAlterarSenhaPane().translateYProperty().set(App.cenaMenuCliente.getHeight());
		//Add second scene. Now both first and second scene is present
		painelCentral.getChildren().clear();
		painelCentral.setCenter(App.getAlterarSenhaPane());
		//Create new TimeLine animation
		Timeline timeline = new Timeline();
		//Animate Y property
		KeyValue kv = new KeyValue(App.getAlterarSenhaPane().translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		//After completing animation, remove first scene
		timeline.setOnFinished(t -> {
			//painelCentral.getChildren().remove(App.cadastroClienteFisicoPane);
		});
		timeline.play();
	}
	private void animationEditarPF() {
		//Set Y of second scene to Height of window
		App.getEditarClienteFisicoPane().translateYProperty().set(App.cenaMenuCliente.getHeight());
		//Add second scene. Now both first and second scene is present
		painelCentral.getChildren().clear();
		painelCentral.setCenter(App.getEditarClienteFisicoPane());
		//Create new TimeLine animation
		Timeline timeline = new Timeline();
		//Animate Y property
		KeyValue kv = new KeyValue(App.getEditarClienteFisicoPane().translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		//After completing animation, remove first scene
		timeline.setOnFinished(t -> {
			//painelCentral.getChildren().remove(App.cadastroClienteFisicoPane);
		});
		timeline.play();
	}
	private void animationEditarPJ() {
		//Set Y of second scene to Height of window
		App.getEditarClienteJuridicoPane().translateYProperty().set(App.cenaMenuCliente.getHeight());
		//Add second scene. Now both first and second scene is present
		painelCentral.getChildren().clear();
		painelCentral.setCenter(App.getEditarClienteJuridicoPane());
		//Create new TimeLine animation
		Timeline timeline = new Timeline();
		//Animate Y property
		KeyValue kv = new KeyValue(App.getEditarClienteJuridicoPane().translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		//After completing animation, remove first scene
		timeline.setOnFinished(t -> {
			//painelCentral.getChildren().remove(App.cadastroClienteFisicoPane);
		});
		timeline.play();
	}
	private void animationCadastroReserva() {
		//Set Y of second scene to Height of window
		App.getCadastroReservaPane().translateYProperty().set(App.cenaMenuCliente.getHeight());
		//Add second scene. Now both first and second scene is present
		painelCentral.getChildren().clear();
		painelCentral.setCenter(App.getCadastroReservaPane());
		//Create new TimeLine animation
		Timeline timeline = new Timeline();
		//Animate Y property
		KeyValue kv = new KeyValue(App.getCadastroReservaPane().translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		//After completing animation, remove first scene
		timeline.setOnFinished(t -> {
			//painelCentral.getChildren().remove(App.cadastroClienteFisicoPane);
		});
		timeline.play();
	}
	private void animationBuscarReserva() {
		//Set Y of second scene to Height of window
		App.getBuscarReservaPane().translateYProperty().set(App.cenaMenuCliente.getHeight());
		//Add second scene. Now both first and second scene is present
		painelCentral.getChildren().clear();
		painelCentral.setCenter(App.getBuscarReservaPane());
		//Create new TimeLine animation
		Timeline timeline = new Timeline();
		//Animate Y property
		KeyValue kv = new KeyValue(App.getBuscarReservaPane().translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		//After completing animation, remove first scene
		timeline.setOnFinished(t -> {
			//painelCentral.getChildren().remove(App.cadastroClienteFisicoPane);
		});
		timeline.play();
	}
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
