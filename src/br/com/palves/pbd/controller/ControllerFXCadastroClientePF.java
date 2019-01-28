package br.com.palves.pbd.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Endereco;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.view.Alerta;
import br.com.palves.pbd.view.AlertaDetalhes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

public class ControllerFXCadastroClientePF implements Initializable{
	@FXML
	private BorderPane cadastroPessoaFisicaPanel;

	@FXML
	private TextField nomeField;

	@FXML
	private TextField loginField;

	@FXML
	private TextField cpfField;

	@FXML
	private PasswordField senhaField;

	@FXML
	private RadioButton fRadio;

	@FXML
	private RadioButton mRadio;

	@FXML
	private TextField nHabilitacaoField;

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

//	@FXML
//	private JFXButton voltarButton;

	@FXML
	private JFXDatePicker dataDeNascimento;

	@FXML
	private JFXDatePicker dataDeVencimentoHabilitacao;

	@FXML
	void salvar(ActionEvent event) {
		PessoaFisicaDao daoPF = PessoaFisicaDao.getInstance();
		try {
			this.validacoesDeNull();
			PessoaFisica pessoaF = new PessoaFisica();
			this.preencherCamposDaTelaParaEntidade(pessoaF);
			daoPF.persistOrMerge(pessoaF);
			Alerta a = Alerta.getInstance();
			a.setAlertType(AlertType.INFORMATION);
			a.setMensagem(pessoaF.getNome()+" "+"Cadastrado Com Sucesso!");
			a.show();
			if(Carregar.detalhes)//Eh porque esta cadastrando o motorista!!!
				AlertaDetalhes.getInstance().hide();
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
//	@FXML
//	void voltar(ActionEvent event) {
//		this.limparCampos();
//		App.stage.setScene(App.cenaLogin);
//		App.stage.centerOnScreen();
//		App.stage.show();
//	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ToggleGroup group = new ToggleGroup();
		fRadio.setToggleGroup(group);
		mRadio.setToggleGroup(group);
		mRadio.setSelected(true);
		MascaraFX.cpfField(cpfField);
		MascaraFX.maxField(nHabilitacaoField, 11);
		MascaraFX.numericField(nHabilitacaoField);
		MascaraFX.cepField(cepField);
		MascaraFX.numericField(numeroField);
		MascaraFX.maxField(ufField,2);
		MascaraFX.letrasField(ufField);
	}

	private void validacoesDeNull() throws ValidacaoException {
		if(dataDeNascimento.getValue()== null)
			throw new ValidacaoException("Campo data de nascimento nulo!");
		if(nHabilitacaoField.getText().length()!=0)
			if(dataDeVencimentoHabilitacao.getValue()==null)
				throw new ValidacaoException("Campo data de vencimento habilitação nulo!");
		if(dataDeVencimentoHabilitacao.getValue()!=null) {
			if(nHabilitacaoField.getText().replace(" ", "").length()<=0)
				throw new ValidacaoException("Ja que possui data é nescessario informar o número da Habilitação!");
		}
		if(senhaField.getText().length()<6 || senhaField.getText().length()>11 )
			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(nomeField.getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		if(loginField.getText().length()<=0)
			throw new ValidacaoException("O Login não pode ser nulo!");
		if(cpfField.getText().replace(" ","").trim().length()<=3) {
			throw new ValidacaoException("O CPF não pode ser nulo!");
		}
		System.out.println(dataDeNascimento.getValue().getYear()+"");
		if(Carregar.detalhes) {
			if(this.nHabilitacaoField.getText().replace(" ","").length()<=0)
				throw new ValidacaoException("CNH é Obrigatoria para o motorista!!!");
			if(dataDeVencimentoHabilitacao.getValue()==null)
				throw new ValidacaoException("Data de vencimento CNH não pode ser Nulo!!!");
			if(dataDeNascimento.getValue().getYear()>1998)
				throw new ValidacaoException("O Motorista deve ter mais de 21 anos!!!");
		}
	}
	private void preencherCamposDaTelaParaEntidade(PessoaFisica pessoaF) throws DaoException, ValidacaoException {
		String  nome = nomeField.getText();
		String senha = EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaField.getText());
		String login = loginField.getText();
		//String idField = this.fpf.getIdField().getText();
		String cpf = cpfField.getText();
		String  sexo = fRadio.isSelected()?fRadio.getText():mRadio.getText();
		LocalDate dataNascimentoFieldv = dataDeNascimento.getValue();
		String nHab = nHabilitacaoField.getText();
		LocalDate dataVencHabField = dataDeVencimentoHabilitacao.getValue();
		String rua = ruaField.getText();
		int numero = numeroField.getText().length()<=0?0:Integer.parseInt( numeroField.getText());
		String bairro = bairroField.getText();
		String cep = cepField.getText();
		String uf = ufField.getText();
		String cidade = cidadeField.getText();
		//String buscarField = this.fpf.getBuscarField().getText();
		Endereco e = new Endereco();
		pessoaF.setEndereco(e);
		e.setRua(rua);
		e.setNumero(numero);
		e.setCep(cep);
		e.setUf(uf);
		e.setCidade(cidade);
		e.setBairro(bairro);
		if(nHab!=null && nHab.replace(" ","").length()>0) {
			verificaUnicidadeHab(nHab);
		}
		pessoaF.setNome(nome);
		pessoaF.setSenha(senha);
		pessoaF.setLogin(login);
		pessoaF.setCpf(cpf);
		pessoaF.setSexo(sexo);
		pessoaF.setnHabilitacao(nHab);
		pessoaF.setDiscriminador(Discriminador.PF.getValor());//Importante
		pessoaF.setDataNascimento(dataNascimentoFieldv==null?null:Date.from(dataNascimentoFieldv.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		pessoaF.setDataVencHabilitacao(dataVencHabField==null?null:Date.from(dataVencHabField.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		pessoaF.setSituacao(StatusEnum.ATIVO.getValor());
		if(Corrente.usuarioFisico!=null) {
			pessoaF.setUltimoModificador(Corrente.usuarioFisico.getNome());
		}else if(Corrente.usuarioJuridico!=null) {
			pessoaF.setUltimoModificador(Corrente.usuarioJuridico.getNome());
		}else if(Corrente.funcionario!=null){
			pessoaF.setUltimoModificador(Corrente.funcionario.getNome());
		}
	}
	private void verificaUnicidadeHab(String nHabilitacaoField) throws ValidacaoException, DaoException {
		PessoaFisicaDao pf = PessoaFisicaDao.getInstance();
		try {
			boolean b = (boolean)pf.procedureValidaHabilitacao(nHabilitacaoField);
			if(b==true)
				throw new ValidacaoException("O número de habilitação já Existe!");
		} 
		catch (ValidacaoException e2) {
			e2.printStackTrace();
			throw new ValidacaoException(e2.getMessage());
		}
		catch (DaoException e1) {
			e1.printStackTrace();
			throw new DaoException("Erro ao executar procedure");
		}
	}
	private void limparCampos() {
		LimparCampo.limparCamposFX(cadastroPessoaFisicaPanel.getChildren());
		//
	}
}
