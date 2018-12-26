package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Endereco;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ControllerFXCadastroClientePJ implements Initializable {
	 @FXML
	    private BorderPane cadastroPessoaJuridicaPanel;

	    @FXML
	    private TextField nomeField;

	    @FXML
	    private TextField loginField;

	    @FXML
	    private TextField cnpjField;

	    @FXML
	    private PasswordField senhaField;

	    @FXML
	    private TextField inscricaoEstadualField;

	    @FXML
	    private TextField ruaField;

	    @FXML
	    private TextField bairroField;

	    @FXML
	    private TextField numeroField;

	    @FXML
	    private TextField cepField;

	    @FXML
	    private TextField cidadeField;

	    @FXML
	    private TextField ufField;

	    @FXML
	    private JFXButton salvarButton;

	    @FXML
	    private JFXButton voltarButton;

	    @FXML
	    void salvar(ActionEvent event) {
	    	PessoaJuridicaDao daoPJ = PessoaJuridicaDao.getInstance();
			try {
				this.validacoesDeNull();
				PessoaJuridica pessoaJ = new PessoaJuridica();
				this.preencherCampos(pessoaJ);
				daoPJ.persistOrMerge(pessoaJ);
				Alerta a = Alerta.getInstance();
				a.setAlertType(AlertType.INFORMATION);
				a.setMensagem(pessoaJ.getNome()+" "+"Cadastrado Com Sucesso!");
				a.show();
				this.limparCampos();
			} 
			catch (DaoException e1) {
				Alerta a = Alerta.getInstance();
				a.setAlertType(AlertType.ERROR);
				a.setMensagem(e1.getMessage());
				a.show();
				e1.printStackTrace();
			}catch(ValidacaoException e3) {
				Alerta a = Alerta.getInstance();
				a.setAlertType(AlertType.ERROR);
				a.setMensagem(e3.getMessage());
				a.show();
				e3.printStackTrace();
			}catch(java.lang.NumberFormatException e4) {
				e4.printStackTrace();
			}
	    }
	    @FXML
	    void voltar(ActionEvent event) {
	    	this.limparCampos();
			App.stage.setScene(App.cenaLogin);
			App.stage.centerOnScreen();
			App.stage.show();
	    }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			MascaraFX.cnpjField(cnpjField);
			MascaraFX.maxField(senhaField, 11);
			MascaraFX.maxField(inscricaoEstadualField, 10);
			MascaraFX.numericField(inscricaoEstadualField);
			MascaraFX.cepField(cepField);
			MascaraFX.numericField(numeroField);
			MascaraFX.maxField(ufField,2);
			MascaraFX.letrasField(ufField);
		}
		private void validacoesDeNull() throws ValidacaoException {
			if(senhaField.getText().length()<6 || senhaField.getText().length()>11 )
				throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
			if(nomeField.getText().length()<=0)
				throw new ValidacaoException("O nome não pode ser nulo!");
			if(loginField.getText().length()<=0)
				throw new ValidacaoException("O Login não pode ser nulo!");
			if(cnpjField.getText().replace(" ","").trim().length()<=4) {
				throw new ValidacaoException("O CNPJ não pode ser nulo!");
			}
		}
		private void preencherCampos(PessoaJuridica pessoaJ) {
			String incEstadual = inscricaoEstadualField.getText().trim();
			String cnpj = cnpjField.getText();
			String  nome = nomeField.getText();
			String senha = EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaField.getText());
			String login = loginField.getText();
			//String idField = this.fpj.getIdField().getText();
			String rua = ruaField.getText();
			int numero = numeroField.getText().length()<=0?0:Integer.parseInt( numeroField.getText());
			String bairro = bairroField.getText();
			String cep =  cepField.getText();
			String uf = ufField.getText();
			String cidade = cidadeField.getText();
			//String buscarField = this.fpj.getBuscarField().getText();
			
			Endereco e = new Endereco();
			pessoaJ.setEndereco(e);
			e.setRua(rua);
			e.setNumero(numero);
			e.setCep(cep);
			e.setUf(uf);
			e.setCidade(cidade);
			e.setBairro(bairro);
			
			pessoaJ.setNome(nome);
			pessoaJ.setSenha(senha);
			pessoaJ.setLogin(login);
			pessoaJ.setCnpj(cnpj);
			pessoaJ.setIncEstadual(incEstadual);
			pessoaJ.setDiscriminador(Discriminador.PJ.getValor());//Importante
			pessoaJ.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
		}
		private void limparCampos() {
			LimparCampo.limparCamposFX(cadastroPessoaJuridicaPanel.getChildren());
			//
		}
}
