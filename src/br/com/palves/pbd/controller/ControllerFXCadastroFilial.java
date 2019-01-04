package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;

import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Endereco;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ControllerFXCadastroFilial implements Initializable{
	@FXML
    private BorderPane cadastroFilialPanel;

    @FXML
    private TextField nomeField;

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
    private JFXTimePicker horaInicio;

    @FXML
    private JFXTimePicker horaFim;

    @FXML
    void salvar(ActionEvent event) {
    	FilialDao daoPF = FilialDao.getInstance();
		try {
			this.validacoes();
			Filial filial = new Filial();
			this.preencherCampos(filial);
			daoPF.persistOrMerge(filial);
			Alerta.mostrarAlertaInformacao("Filial Cadastrada com Sucesso!");
		    this.limparCampos();
		} 
		catch (DaoException e1) {
			Alerta.mostrarAlertaErro(e1.getMessage());
			e1.printStackTrace();
		}catch(ValidacaoException e3) {
			Alerta.mostrarAlertaErro(e3.getMessage());
			e3.printStackTrace();
		}catch(java.lang.NumberFormatException e4) {
			e4.printStackTrace();
		}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MascaraFX.cepField(cepField);
		MascaraFX.letrasField(ufField);
		MascaraFX.maxField(ufField, 2);
		MascaraFX.numericField(numeroField);
	}
	//Salvar<>
	private void validacoes() throws ValidacaoException {
		if(this.nomeField.getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		if(this.horaInicio.getValue()==null)
			throw new ValidacaoException("É nescessario uma Hora de Inicio!");
		if(this.horaFim.getValue()==null)
			throw new ValidacaoException("É nescessario uma Hora de Termino!");
	}
	private void preencherCampos(Filial filial) {
		String  nome = this.nomeField.getText();
		//String idField = this.fpf.getIdField().getText();
		Date horaI = TratadorDeMascara.localTimeToDate(this.horaInicio.getValue());
		Date horaF = TratadorDeMascara.localTimeToDate(this.horaFim.getValue());
		
		String ruaField = this.ruaField.getText();
		int numeroField = this.nomeField.getText().length()<=0?0:Integer.parseInt( this.numeroField.getText());
		String bairroField = this.bairroField.getText();
		String cepField =  this.cepField.getText();
		String ufField = this.ufField.getText();
		String cidadeField = this.cidadeField.getText();
		//String buscarField = this.fpf.getBuscarField().getText();
		
		Endereco e = new Endereco();
		filial.setEndereco(e);
		e.setRua(ruaField);
		e.setNumero(numeroField);
		e.setCep(cepField);
		e.setUf(ufField);
		e.setCidade(cidadeField);
		e.setBairro(bairroField);
		filial.setNome(nome);
		filial.setSituacao(StatusEnum.ATIVO.getValor());
		filial.setHoraInicioExpediente(horaI);
		filial.setDataFimExpediente(horaF);
	}
	//Salvar</>
	//other<>
	private void limparCampos() {
		LimparCampo.limparCamposFX(cadastroFilialPanel.getChildren());
		//
	}
	//other</>
}
