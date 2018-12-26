package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.TransicaoTelaEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.dao.FuncionarioDao;
import br.com.palves.pbd.model.dao.PessoaDao;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
/**
 * @author: P Alves
 * */
public class ControllerLogin implements Initializable {
	@FXML
	private TextField emailField;

	@FXML
	private Button entrarButton;

	@FXML
	private Label recuperarSenhaLabel;

	@FXML
	private PasswordField senhaField;

	@FXML
	private RadioButton clienteRadio;

	@FXML
	private RadioButton funcionarioRadio;

	@FXML
	private Button pessoaFisicaButton;

	@FXML
	private Button pessoaJuridicaButton;

	private Object EncriptaDecriptaApacheDoc;

	@FXML
	public void logar(ActionEvent event) {
		PessoaDao pessoaDao = PessoaDao.getInstance();
		FuncionarioDao funcionarioDao = FuncionarioDao.getInstance();
		String email = emailField.getText();
		String senha = EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaField.getText());
		System.out.println(senha);
		try {
			Object[] obj;// pessoaDao.buscarIdPorLogin(email, senha);
			if(funcionarioRadio.isSelected()) {//Busca Funcionario!
				obj =funcionarioDao.buscarIdPorLogin(email, senha);
				if(obj == null) {//Então neunum func foi encontrado.
					Alerta a = Alerta.getInstance();
					a.setAlertType(AlertType.ERROR);
					a.setMensagem("Nenhum Funcionário encontrado!");
					a.show();
				}else {//Encontou um func!
					Corrente.funcionario = funcionarioDao.findById(Funcionario.class,(int)obj[1]);
					ControllerFXMenu.atualizarNome(Corrente.funcionario.getNome());
					Corrente.usuarioFisico = null;
					Corrente.usuarioJuridico = null;
					Alerta a = Alerta.getInstance();
					a.setAlertType(AlertType.INFORMATION);
					a.setMensagem("Funcionario:"+Corrente.funcionario.getNome() +" ID:"+	Corrente.funcionario.getId() +" Email:"+Corrente.funcionario.getLogin()+" Logado Com Sucesso!");
					a.show();
				}
			}else{//Então procura em usuario!
				
				obj = pessoaDao.buscarIdPorLogin(email, senha);
				if(obj==null) {//Não encontrou Cliente
					Alerta a = Alerta.getInstance();
					a.setAlertType(AlertType.ERROR);
					a.setMensagem("Nenhum Cliente encontrado!");
					a.show();
				}else {//Encontrou cliente e os diferencia!
					Discriminador discriminador = Enum.valueOf(Discriminador.class, obj[0]+"".toUpperCase());
					Corrente.usuarioJuridico = null;
					Corrente.usuarioFisico = null;
					Corrente.funcionario = null;
					switch(discriminador) {
					case PF:{
						Corrente.usuarioFisico = PessoaFisicaDao.getInstance().findById(PessoaFisica.class,(int)obj[1]);
						Alerta a = Alerta.getInstance(); //new Alert(,);
						ControllerFXMenu.atualizarNome(Corrente.usuarioFisico.getNome());
						a.setAlertType(AlertType.CONFIRMATION);
						a.setMensagem("Usuario: "+Corrente.usuarioFisico.getNome() +" ID: "+Corrente.usuarioFisico.getId() +" Email: "+Corrente.usuarioFisico.getLogin()+" Logado Com Sucesso!");
						a.show();
						break;
					}
					case PJ:{
						Corrente.usuarioJuridico = PessoaJuridicaDao.getInstance().findById(PessoaJuridica.class,(int)obj[1]);
						ControllerFXMenu.atualizarNome(Corrente.usuarioJuridico.getNome());
						Alerta a = Alerta.getInstance();//new Alert(,);
						a.setMensagem("Usuario: "+	Corrente.usuarioJuridico.getNome() +" ID: "+	Corrente.usuarioJuridico.getId() +" Email:"+Corrente.usuarioJuridico.getLogin()+" Logado Com Sucesso!");
						a.setAlertType(AlertType.CONFIRMATION);
						a.show();
						break;
					}
					}
					mudarCenaCliente();
				}
			}
		} catch (DaoException e) {
			Alerta a = Alerta.getInstance();
			a.setAlertType(AlertType.ERROR);
			a.setMensagem(e.getMessage());
			a.show();
			e.printStackTrace();
		}	
	}
	@FXML
	public void recuperarSenha(MouseEvent event) {

	}
    @FXML
    void trocarTela(ActionEvent event) {
    	if(event.getSource()==pessoaFisicaButton)
    		App.stage.setScene(App.cenaCadastroPF);
    	else
    		App.stage.setScene(App.cenaCadastroPJ);
    	App.stage.centerOnScreen();
    	App.stage.show();
    }
	@Override
	public void initialize(URL url, ResourceBundle rsc) {
		ToggleGroup group = new ToggleGroup();
		funcionarioRadio.setToggleGroup(group);
		clienteRadio.setToggleGroup(group);
	}
	private void mudarCenaCliente() {
		App.stage.setScene(App.cenaMenuCliente);
		App.stage.centerOnScreen();
    	App.stage.show();
	}

}
