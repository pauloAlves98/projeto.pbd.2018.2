package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.palves.pbd.enums.Discriminador;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * @author: P Alves
 * */
public class ControllerLogin implements Initializable {
	@FXML
	private TextField emailField;

	@FXML
	private TextField senhaField;

	@FXML
	private Button entrarButton;

	@FXML
	private Label recuperarSenhaLabel;

	private Object EncriptaDecriptaApacheDoc;

	/**@param: Loga Func e Usuarios , Futuramente eles serão diferenciados*/
	@FXML
	public void logar(ActionEvent event) {
		PessoaDao pessoaDao = PessoaDao.getInstance();
		FuncionarioDao funcionarioDao = FuncionarioDao.getInstance();
		String email = emailField.getText();
		String senha = EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaField.getText());
		System.out.println(senha);
		try {
			Object[] obj = pessoaDao.buscarIdPorLogin(email, senha);
			if(obj==null) {//Então nehuma Pessoa Foi encontrada!
				obj =funcionarioDao.buscarIdPorLogin(email, senha);
				if(obj == null) {//Então neunum func foi encontrado.
					Alert a = new Alert(AlertType.ERROR,"Nehum Usuario ou Funcionario encontrado!");
					a.show();
				}else {//Encontou um func!
					Corrente.funcionario = funcionarioDao.findById(Funcionario.class,(int)obj[1]);
					Alert a = new Alert(AlertType.INFORMATION,"Funcionario:"+	Corrente.funcionario.getNome() +" ID:"+	Corrente.funcionario.getId() +" Email:"+Corrente.funcionario.getLogin()+" Logado Com Sucesso!");
					a.show();
				}
			}else {//Encontrou uma pessoa!
				Discriminador discriminador = Enum.valueOf(Discriminador.class, obj[0]+"".toUpperCase());
				Corrente.usuarioJuridico = null;
				Corrente.usuarioFisico = null;
				switch(discriminador) {
				case PF:{
					Corrente.usuarioFisico = PessoaFisicaDao.getInstance().findById(PessoaFisica.class,(int)obj[1]);
					Alert a = new Alert(AlertType.CONFIRMATION,"Usuario:"+Corrente.usuarioFisico.getNome() +" ID:"+Corrente.usuarioFisico.getId() +" Email:"+Corrente.usuarioFisico.getLogin()+" Logado Com Sucesso!");
					a.show();
					break;
				}
				case PJ:{
					Corrente.usuarioJuridico = PessoaJuridicaDao.getInstance().findById(PessoaJuridica.class,(int)obj[1]);
					Alert a = new Alert(AlertType.INFORMATION,"Usuario:"+	Corrente.usuarioJuridico.getNome() +" ID:"+	Corrente.usuarioJuridico.getId() +" Email:"+Corrente.usuarioJuridico.getLogin()+" Logado Com Sucesso!");
					a.show();
					break;
				}
				}
			}
		} catch (DaoException e) {
			Alert a = new Alert(AlertType.ERROR,e.getMessage());
			a.show();
			e.printStackTrace();
		}	
	}
	@FXML
	public void recuperarSenha(MouseEvent event) {

	}

	@Override
	public void initialize(URL url, ResourceBundle rsc) {

	}

}
