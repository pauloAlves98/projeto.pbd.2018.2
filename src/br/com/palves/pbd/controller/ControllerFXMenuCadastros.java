package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.model.complemento.LimparCampo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ControllerFXMenuCadastros implements Initializable{
    @FXML
    private BorderPane painelGeral;
    @FXML
    private BorderPane painelCentral;
    private static  BorderPane painel;
    @FXML
    private Label voltarLabel;
    @FXML
    void voltar(MouseEvent event) {
    	LimparCampo.limparCamposFX(painelCentral.getChildren());
    	App.stage.setScene(App.getCenaLogin());
		App.stage.centerOnScreen();
		App.stage.show();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		painel = painelCentral;
	}
	public BorderPane getPainelGeral() {
		return painelGeral;
	}
	public BorderPane getPainelCentral() {
		return painelCentral;
	}
	public static BorderPane getPainel() {
		return painel;
	}
}
