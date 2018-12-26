package br.com.palves.pbd.controller;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Endereco;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class ControllerFXEditarClientePJ implements Initializable{
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
	void editar(ActionEvent event) {
		PessoaJuridicaDao daoPJ = PessoaJuridicaDao.getInstance();
		try {
			this.validacoesDeNull();
			PessoaJuridica pessoaJ = new PessoaJuridica();
			this.preencherCampos(pessoaJ);
			Corrente.usuarioJuridico = daoPJ.persistOrMerge(pessoaJ);
			Alerta a = Alerta.getInstance();
			a.setAlertType(AlertType.INFORMATION);
			a.setMensagem(pessoaJ.getNome()+" "+"Editado Com Sucesso!");
			a.show();
			ControllerFXMenu.atualizarNome(Corrente.usuarioJuridico.getNome());
			this.limparCampos();
			this.preencher();
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
		e.setId(Corrente.usuarioJuridico.getEndereco().getId());
		pessoaJ.setId(Corrente.usuarioJuridico.getId()); //Importante
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
		pessoaJ.setSituacao(StatusEnum.ATIVO.getValor());
	}
	private void limparCampos() {
		LimparCampo.limparCamposFX(cadastroPessoaJuridicaPanel.getChildren());
	}
	//EXTRA
	public void preencher() {
		this.carregarPJ();
	}
	private void carregarPJ() {
		limparCampos();
		preencherBusca(Corrente.usuarioJuridico);

	}
	private void preencherBusca(PessoaJuridica p) {	
		nomeField.setText(p.getNome());
		senhaField.setText(EncriptaDecriptaApacheCodec.decodificaBase64Decoder(p.getSenha()));
		loginField.setText(p.getLogin());
		//this.fpf.getCpfField().setText(pessoaF.getCpf());
		cnpjField.setText(p.getCnpj().replace(".","").replace("-",""));
		inscricaoEstadualField.setText(p.getIncEstadual());
		ruaField.setText(p.getEndereco().getRua());
		numeroField.setText(p.getEndereco().getNumero()!=null?p.getEndereco().getNumero()+"":"");
		bairroField.setText(p.getEndereco().getBairro());
		cepField.setText(p.getEndereco().getCep());
		ufField.setText(p.getEndereco().getUf());
		cidadeField.setText(p.getEndereco().getCidade());
	}

}
