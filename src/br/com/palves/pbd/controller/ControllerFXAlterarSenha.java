package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.dao.FuncionarioDao;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class ControllerFXAlterarSenha implements Initializable {
    @FXML
    private AnchorPane AlterarPane;
	@FXML
	private JFXButton salvarButton;

	@FXML
	private PasswordField senhaAntigaField;

	@FXML
	private PasswordField senhaNovaField;

	@FXML
	void alterarSenha(ActionEvent event) {
		if(senhaNovaField.getText().length()<6) {
			Alerta a = Alerta.getInstance();
			a.setAlertType(AlertType.INFORMATION);
			a.setMensagem("A senha Deve possuir de 6 a 11 caracteres!");
			a.show();
			return;
		}
		else if(Corrente.usuarioFisico == null && Corrente.usuarioJuridico==null && Corrente.funcionario==null) {
			Alerta.mostrarAlertaInformacao("Eh preciso estar Logado para executar essa Funcao!");
		}else if(Corrente.usuarioFisico != null){
			PessoaFisicaDao pd = PessoaFisicaDao.getInstance();
			if(Corrente.usuarioFisico.getSenha().equals(EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaAntigaField.getText().length()<=0?"":senhaAntigaField.getText()))){
				//System.out.println("Senha do pw:"+new String(fpv.getAtualField().getPassword()));
				try {
					PessoaFisica pf = Corrente.usuarioFisico;
					pf.setSenha(EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaNovaField.getText()).length()<=0?"":senhaNovaField.getText());
					String s = EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaNovaField.getText());
					System.out.println("Senha do pf:"+s);
					pf.setSenha(s);
					PessoaFisica p = pd.persistOrMerge(pf);
					Corrente.usuarioFisico = p;
					Alerta.mostrarAlertaInformacao("Senha  alterada!");
					System.out.println("PF");
					this.limparCampos();
					return;
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
				Alerta.mostrarAlertaInformacao("A senha Antiga está incorreta!");
		}else if(Corrente.usuarioJuridico!=null){
			PessoaJuridicaDao pd = PessoaJuridicaDao.getInstance();
			if(Corrente.usuarioJuridico.getSenha().equals(EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaAntigaField.getText().length()<=0?"":senhaAntigaField.getText()))) {
				PessoaJuridica pj = Corrente.usuarioJuridico;
				pj.setSenha(EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaNovaField.getText()));
				String s = EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaNovaField.getText());
				pj.setSenha(s);
				try {
					PessoaJuridica p = pd.persistOrMerge(pj);
					Corrente.usuarioJuridico = p;
					Alerta.mostrarAlertaInformacao("Senha alterada!");
					this.limparCampos();
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				Alerta.mostrarAlertaInformacao("A senha antiga está incorreta!");
			}
		}else {
			FuncionarioDao pd = FuncionarioDao.getInstance();
			if(Corrente.funcionario.getSenha().equals(EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaAntigaField.getText().length()<=0?"":senhaAntigaField.getText()))) {
				Funcionario pj = Corrente.funcionario;
				pj.setSenha(EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaNovaField.getText()));
				String s = EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaNovaField.getText());
				pj.setSenha(s);
				try {
					Funcionario p = pd.persistOrMerge(pj);
					Corrente.funcionario = p;
					Alerta.mostrarAlertaInformacao("Senha alterada!");
					this.limparCampos();
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				Alerta.mostrarAlertaInformacao("A senha antiga está incorreta!");
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MascaraFX.maxField(senhaAntigaField, 11);
		MascaraFX.maxField(senhaNovaField, 11);
	}
	private void limparCampos() {
		LimparCampo.limparCamposFX(this.AlterarPane.getChildren());
	}
}
