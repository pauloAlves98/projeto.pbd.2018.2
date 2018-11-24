package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.Corrente;
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

    /**@param: Loga Func e Usuarios , Futuramente eles serão diferenciados*/
    @FXML
    public void logar(ActionEvent event) {
    	PessoaDao pessoaDao = PessoaDao.getInstance();
    	String email = emailField.getText();
    	String senha = senhaField.getText();
    	try {
			Object[] obj = pessoaDao.buscarIdPorLogin(email, senha);
			if(obj==null) {
				Alert a = new Alert(AlertType.ERROR,"Nehum Usuario encontrado");
				a.show();
			}else {
				Discriminador discriminador = Enum.valueOf(Discriminador.class, obj[0]+"".toUpperCase());
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
