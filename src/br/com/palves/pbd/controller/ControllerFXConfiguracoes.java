package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.ConfiguracaoDao;
import br.com.palves.pbd.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerFXConfiguracoes implements Initializable{
	@FXML
    private BorderPane configuracoesPane;

    @FXML
    private AnchorPane AlterarPane;

    @FXML
    private JFXButton salvarButton;

    @FXML
    private TextField kmLivreField;

    @FXML
    private TextField taxaHigieneField;

    @FXML
    private TextField taxaCombustivelField;

    @FXML
    private TextField kmControleField;

    @FXML
    private TextField kmExtraField;

    @FXML
    void salvarConfiguracao(ActionEvent event) {
    	ConfiguracaoDao cdao = ConfiguracaoDao.getInstance();
    	try {
    		this.validacoes();
    		Configuracao conf = new Configuracao();
    		this.preencherCampos(conf);
			cdao.persistOrMerge(conf);
			this.carregarConfiguracaoes();
			Alerta.mostrarAlertaInformacao("Configuração Inserida com sucesso!");
		} catch (DaoException e) {
			Alerta.mostrarAlertaErro(e.getMessage());
			e.printStackTrace();
		}catch (Exception e1) {
			Alerta.mostrarAlertaErro(e1.getMessage());
			e1.printStackTrace();
		}
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	private void validacoes () throws ValidacaoException{
		try {
			Double.parseDouble(this.kmControleField.getText().replace(" ","").replace(",","."));
			Double.parseDouble(this.kmLivreField.getText().replace(" ","").replace(",","."));
			Double.parseDouble(this.kmExtraField.getText().replace(" ","").replace(",","."));
			Double.parseDouble(this.taxaHigieneField.getText().replace(" ","").replace(",","."));
			Double.parseDouble(this.taxaCombustivelField.getText().replace(" ","").replace(",","."));
		}catch(Exception e) {
			throw new ValidacaoException("Existe Campos Invalidos!");
		}
	}
	private void preencherCampos(Configuracao conf) {
		conf.setDataRealizacao(new Date());
		conf.setDiariaKcontrole(Double.parseDouble(this.kmControleField.getText().replace(" ","").replace(",",".")));
		conf.setDiariaKlivre(Double.parseDouble(this.kmLivreField.getText().replace(" ","").replace(",",".")));
		conf.setPorcentagemKm(Double.parseDouble(this.kmExtraField.getText().replace(" ","").replace(",",".")));
		conf.setTaxaHigiene(Double.parseDouble(this.taxaHigieneField.getText().replace(" ","").replace(",",".")));
		conf.setTaxaCombustivel(Double.parseDouble(this.taxaCombustivelField.getText().replace(" ","").replace(",",".")));
		
	}
	public  void carregarConfiguracaoes() {
		ConfiguracaoDao c =  ConfiguracaoDao.getInstance();
		Configuracao cf;
		try {
			cf = c.buscarUltimo();
			if(cf==null) {
				return;
			}
			System.out.println(" ");
			kmControleField.setText((cf.getDiariaKcontrole()+"").replace(".",","));
			kmLivreField.setText((cf.getDiariaKlivre()+"").replace(".",","));
			kmExtraField.setText((cf.getPorcentagemKm()+"").replace(".",","));
			taxaCombustivelField.setText((cf.getTaxaCombustivel()+"").replace(".",","));
			taxaHigieneField.setText((cf.getTaxaHigiene()+"").replace(".",","));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
