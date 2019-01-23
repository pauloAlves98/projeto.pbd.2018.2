package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.dao.FuncionarioDao;
import br.com.palves.pbd.view.Alerta;
import br.com.palves.pbd.view.AlertaDetalhes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControllerFXPermissao implements Initializable{
	@FXML
	private AnchorPane AlterarPane;

	@FXML
	private JFXButton salvarButton;

	@FXML
	private PasswordField senhaField;

	@FXML
	private TextField loginField;

	@FXML
	void buscarPermissao(ActionEvent event) {
		try {
			Object obj[] = FuncionarioDao.getInstance().buscarIdPorLogin(loginField.getText(),	EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaField.getText()));
			if(obj==null) {
				Alerta.mostrarAlertaErro("Nehum registro encontrado!!!");
				loginField.setText(" ");
				senhaField.setText(" ");
				AlertaDetalhes.getInstance().close();
				return;
			}else {
				if(((String)obj[2]).equalsIgnoreCase("GERENTE")) {
					ControllerFXCadastroLocacaoCReserva.setPermissaoGerente(true);
					Alerta.mostrarAlertaInformacao("Permissão Concedida!!!");
					
					AlertaDetalhes.getInstance().close();
				}
				else {
					Alerta.mostrarAlertaErro("O Funcionario encontrado não possui o cargo de Gerente!!!");
					AlertaDetalhes.getInstance().close();
				}
				loginField.setText(" ");
				senhaField.setText(" ");
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
