package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.palves.pbd.view.AlertaDetalhes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControllerFXDetalhes implements Initializable{
    @FXML
    private Label voltarLabel;

    @FXML
    void voltar(MouseEvent event) {
    	System.out.println("Fechou!");
    		AlertaDetalhes.getInstance().hide();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}
