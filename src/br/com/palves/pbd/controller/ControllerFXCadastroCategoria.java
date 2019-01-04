package br.com.palves.pbd.controller;

import java.awt.Color;
import java.awt.Panel;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.CategoriaCargaDao;
import br.com.palves.pbd.model.dao.CategoriaDao;
import br.com.palves.pbd.model.dao.CategoriaPassageiroDao;
import br.com.palves.pbd.view.Alerta;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ControllerFXCadastroCategoria implements Initializable{

	@FXML
	private BorderPane categoriaPanel;

	@FXML
	private GridPane gridPane;

	@FXML
	private AnchorPane cnPane;

	@FXML
	private TextField nomeField;

	@FXML
	private TextField valorField;

	@FXML
	private TextField nPassageirosFiled;

	@FXML
	private JFXButton salvarButton;

	@FXML
	private JFXTimePicker horaLimpeza;

	@FXML
	private JFXComboBox<String> tipoCambioBox;

	@FXML
	private JFXCheckBox arCondCheck;

	@FXML
	private JFXCheckBox dvdCheck;

	@FXML
	private JFXCheckBox radioCheck;

	@FXML
	private JFXCheckBox mp3Check;

	@FXML
	private JFXCheckBox cameraReCheck;

	@FXML
	private AnchorPane cpPane;

	@FXML
	private JFXComboBox<String> airBargBox;

	@FXML
	private JFXCheckBox dirAssistidaCheck;

	@FXML
	private JFXCheckBox cintoSegCheck;

	@FXML
	private JFXCheckBox rodaLigaLeveCheck;

	@FXML
	private JFXCheckBox controlePoluicaoCheck;

	@FXML
	private AnchorPane cgPane;

	@FXML
	private TextField capacidadeCargaField;

	@FXML
	private TextField potenciaMotorField;

	@FXML
	private TextField volCombustivelField;

	@FXML
	private TextField distanciaEixosField;

	@FXML
	private JFXComboBox<String> embreagemBox;

	@FXML
	private TextField consumoKmField;

	@FXML
	private JFXComboBox<String> tipoCategoriaBox;

	@FXML
	void salvar(ActionEvent event) {
		boolean cpb = false,cnb=false,cgb=false;
		if(this.tipoCategoriaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Passageiro"))
			cpb = true;
		else if(this.tipoCategoriaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Carga"))
			cgb = true;
		else
			cnb = true;
		try {
			this.validacoesDeNull();
			if(cpb) {
				CategoriaPassageiroDao cPdao = CategoriaPassageiroDao.getInstance();
				CategoriaPassageiro cp = new CategoriaPassageiro();
				this.preencherCampos(cp);
				cPdao.persistOrMerge(cp);
				Alerta.mostrarAlertaInformacao("Categoria CP Cadastrada com Sucesso!");
				this.limparCampos();
			}
			else if(cgb) {
				CategoriaCargaDao cGDao =  CategoriaCargaDao.getInstance();
				CategoriaCarga cp = new CategoriaCarga();
				this.preencherCampos(cp);
				cGDao.persistOrMerge(cp);
				Alerta.mostrarAlertaInformacao("Categoria CG Cadastrada com Sucesso!");
				this.limparCampos();
			}
			else {
				CategoriaDao cnDao = CategoriaDao.getInstance();
				Categoria cp = new Categoria();
				this.preencherCampos(cp);
				cnDao.persistOrMerge(cp);
				Alerta.mostrarAlertaInformacao("Categoria CN Cadastrada com Sucesso!");
				this.limparCampos();
			}
		} 
		catch (DaoException e1) {
			Alerta.mostrarAlertaInformacao(e1.getMessage());
			e1.printStackTrace();
		}catch(ValidacaoException e3) {
			Alerta.mostrarAlertaInformacao(e3.getMessage());
			e3.printStackTrace();
		}catch(java.lang.NumberFormatException e4) {
			e4.printStackTrace();
		}
	}

	@FXML
	void mudancaCategoria(ActionEvent event) {
		this.limparCampos();
		if(this.tipoCategoriaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Normal")) {
			this.desabilita(this.cgPane);
			this.desabilita(cpPane);
		}
		else if(this.tipoCategoriaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Carga")) {
			this.habilita(this.cgPane);
			this.desabilita(cpPane);
			this.gridPane.getChildren().remove(this.cgPane);
			this.gridPane.getChildren().remove(this.cpPane);
//			this.gridPane.add(this.cgPane, 1, 0);
//			this.gridPane.add(this.cpPane, 2, 0);
			this.animationGeral(cpPane, 2, 0);
			this.animationGeral(cgPane, 1, 0);
		}
		else if(this.tipoCategoriaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Passageiro")) {
			this.desabilita(cgPane);
			this.habilita(this.cpPane);
			this.gridPane.getChildren().remove(this.cgPane);
			this.gridPane.getChildren().remove(this.cpPane);
//			this.gridPane.add(this.cpPane, 1, 0);
//			this.gridPane.add(this.cgPane, 2, 0);
			this.animationGeral(cgPane, 2, 0);
			this.animationGeral(cpPane, 1, 0);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MascaraFX.numericField(this.nPassageirosFiled);
		MascaraFX.monetaryField(this.valorField);
		MascaraFX.numericField(this.capacidadeCargaField);
		MascaraFX.numericField(this.potenciaMotorField);
		MascaraFX.numericField(this.volCombustivelField);
		MascaraFX.numericField(this.distanciaEixosField);
		MascaraFX.numericField(this.consumoKmField);
		this.airBargBox.getItems().addAll("Simples-Dianteira","Duplo-Dianteira","Total");
		this.airBargBox.getSelectionModel().select("Simples-Dianteira");
		this.embreagemBox.getItems().addAll("Manual","Hidraulica");
		this.embreagemBox.getSelectionModel().select("Manual");
		this.tipoCambioBox.getItems().addAll("Automatico" ,"Manual");
		this.tipoCambioBox.getSelectionModel().select("Manual");
		this.tipoCategoriaBox.getItems().addAll("NORMAL","PASSAGEIRO","CARGA");
		this.tipoCategoriaBox.getSelectionModel().select("NORMAL");
		this.desabilita(cgPane);
		this.desabilita(cpPane);
	}
	private void preencherCampos(Categoria categoria) throws DaoException {
		try {
			String  nome = this.nomeField.getText().toUpperCase();
			int  numeroPassageiros = this.nPassageirosFiled.getText().replace(" ","").length()<=0?0:Integer.parseInt(this.nPassageirosFiled.getText().replace(" ",""));
			Double valor =  this.valorField.getText().replace(" ","").replace(",",".").length()<=0?0:Double.parseDouble(this.valorField.getText().replace(".","").replace(",","."));
			String horalimpeza = this.horaLimpeza.getValue()==null?"00:30":TratadorDeMascara.localTimetoString(this.horaLimpeza.getValue());
			String tipocambio = this.tipoCambioBox.getSelectionModel().getSelectedItem();
			//String idField = this.fpc.getIdField().getText();
			boolean  arcondicionado = this.arCondCheck.isSelected();
			boolean  dvd = this.dvdCheck.isSelected();
			boolean radio=  this.radioCheck.isSelected();
			boolean mp3 = this.mp3Check.isSelected();
			boolean camerare = this.cameraReCheck.isSelected();
			//CP
			String tipoAirBag = this.airBargBox.getSelectionModel().getSelectedItem();
			boolean dirAssistida = this.dirAssistidaCheck.isSelected();
			boolean cintoSeguranca = this.cintoSegCheck.isSelected();
			boolean rodaLiga = this.rodaLigaLeveCheck.isSelected();
			boolean poluicaoAr = this.controlePoluicaoCheck.isSelected();
			//CG
			float capacidadeC = this.capacidadeCargaField.getText().replace(" ","").replace(",",".").length()<=0?0:Float.parseFloat(this.capacidadeCargaField.getText().replace(" ","").replace(",","."));
			int distanciaEixo = this.distanciaEixosField.getText().replace(" ","").length()<=0?0:Integer.parseInt(this.distanciaEixosField.getText().replace(" ",""));
			int potenciaMotor = this.potenciaMotorField.getText().replace(" ","").length()<=0?0:Integer.parseInt(this.potenciaMotorField.getText().replace(" ",""));
			int volumeCombustivel = this.volCombustivelField.getText().replace(" ","").length()<=0?0:Integer.parseInt(this.volCombustivelField.getText().replace(" ",""));
			String tipoEmbreagem = this.embreagemBox.getSelectionModel().getSelectedItem();
			String consumo = this.consumoKmField.getText();
			//
			categoria.setNome(nome);
			categoria.setnPassageiro(numeroPassageiros);
			categoria.setValor(valor);
			categoria.setHoraLimpeza(horalimpeza);
			categoria.setTipoCambio(tipocambio);
			categoria.setArCondicionado(arcondicionado);
			categoria.setDvd(dvd);
			categoria.setRadio(radio);
			categoria.setMp3(mp3);
			categoria.setCameraRe(camerare);
			categoria.setDiscriminador(Discriminador.CN.getValor());//Importante
			categoria.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
			//			if(idField.trim().length()>0) {//então eh update
			//				categoria.setId(Integer.parseInt(idField));
			//			}
			if(categoria instanceof CategoriaPassageiro) {
				((CategoriaPassageiro) (categoria)).setTipoAirBag(tipoAirBag);
				((CategoriaPassageiro) (categoria)).setDirecaoAssistida(dirAssistida);
				((CategoriaPassageiro) (categoria)).setCintoSeguancaTraseiro(cintoSeguranca);
				((CategoriaPassageiro) (categoria)).setRodaLigaLeve(rodaLiga);
				((CategoriaPassageiro) (categoria)).setControlePoluicaoAr(poluicaoAr);
				categoria.setDiscriminador(Discriminador.CP.getValor());
			}else if(categoria instanceof CategoriaCarga) {
				((CategoriaCarga) (categoria)).setCapacidadeCarga(capacidadeC);
				((CategoriaCarga) (categoria)).setDistanciaEixo(distanciaEixo);
				((CategoriaCarga) (categoria)).setPotenciaMotor(potenciaMotor);
				((CategoriaCarga) (categoria)).setVolumeCombustivel(volumeCombustivel);
				((CategoriaCarga) (categoria)).setTipoEmbreagem(tipoEmbreagem);
				((CategoriaCarga) (categoria)).setConsumoKm(consumo);
				categoria.setDiscriminador(Discriminador.CG.getValor());
			}
		}catch(java.lang.NumberFormatException e) {
			throw new DaoException("Existem campos com caracteres iválidos!");
		}
	}
	//Salvar<>
	private void validacoesDeNull() throws ValidacaoException {
		//		if(this.fpc.getSenhaField().getText().length()<6 || this.fpc.getSenhaField().getText().length()>11 )
		//			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(this.nomeField.getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		//		if(this.fpc.getLoginField().getText().length()<=0)
		//			throw new ValidacaoException("O Login não pode ser nulo!");
		//		if(this.fpc.getCnpjField().getText().replace(" ","").trim().length()<=4) {
		//			throw new ValidacaoException("O CNPJ não pode ser nulo!");
		//		}
	}
	//Salvar</>
	//Categoria<>

	//Categoria</>
	//Other<>
	private void limparCampos() {
		LimparCampo.limparCamposFX(this.categoriaPanel.getChildren());
	}
	private void desabilita(AnchorPane cgPane2) {
		cgPane2.setStyle("-fx-background-color: #808080");//setBackground(new BackgroundFill(Color.web("#" +"808080"), CornerRadii.EMPTY, Insets.EMPTY));
		cgPane2.setDisable(true);
		//		if(this.tipoCategoriaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Normal")) {
		//			this
		//		}
		//		else if(this.tipoCategoriaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Passageiro")) {
		//			
		//		}
		//		else if(this.tipoCategoriaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Carga")) {
		//			
		//		}
		//		Component[] c = pnl.getComponents(); //Pega todos os componentes adicionados no panel
		//		for (int i = 0; i < c.length; i++) {  
		//			c[i].setEnabled(false);  
		//		}  
	}
	private void habilita(AnchorPane cgPane2) {
		cgPane2.setStyle("-fx-background-color: #ffffff");
		cgPane2.setDisable(false);
		//		Component[] c = pnl.getComponents(); //Pega todos os componentes adicionados no panel
		//		for (int i = 0; i < c.length; i++) {  
		//			c[i].setEnabled(true);  
		//		}  
	}
	private void animationGeral(Pane pane,int col,int l) {
		//Set Y of second scene to Height of window
		pane.translateXProperty().set(pane.getWidth());
		//pane.translateXProperty().set(App.getCenaMenuFuncionario().getWidth());
		//Add second scene. Now both first and second scene is present
		//gridPane.getChildren().clear();
		gridPane.add(pane, col, l);
		//Create new TimeLine animation
		Timeline timeline = new Timeline();
		//Animate Y property
		KeyValue kv = new KeyValue(pane.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		//After completing animation, remove first scene
		timeline.setOnFinished(t -> {
			//painelCentral.getChildren().remove(App.cadastroClienteFisicoPane);
		});
		timeline.play();
	}
	//Other</>
}
