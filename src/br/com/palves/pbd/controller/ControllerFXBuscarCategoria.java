package br.com.palves.pbd.controller;
//Falta o metodo editar!Colocar o id Nas buscas tbm!
import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;

import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.CategoriaCargaDao;
import br.com.palves.pbd.model.dao.CategoriaDao;
import br.com.palves.pbd.model.dao.CategoriaPassageiroDao;
import br.com.palves.pbd.model.dao.FuncionarioDao;
import br.com.palves.pbd.view.Alerta;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ControllerFXBuscarCategoria implements Initializable{

	@FXML
	private BorderPane buscarCategoriaPanel;

	@FXML
	private TextField filtroField;

	@FXML
	private Button buscarFiltroButton;

	@FXML
	private TableView<Categoria> tableCategoria;

	@FXML
	private TableColumn<Categoria, Integer> codCategoriaColumn;

	@FXML
	private TableColumn<Categoria, String> nomeCategoriaColumn;

	@FXML
	private TableColumn<Categoria, String> situacaoCategoriaColumn;

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
	private JFXButton cancelarButton;

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
	private JFXComboBox<String> categoriaBuscaBox;

	@FXML
	void buscarCategoria(ActionEvent event) {
		try {
			if(this.filtroField.getText().replace(" ","").length()<=0) {
				List l =null;
				if(this.categoriaBuscaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("NORMAL"))
					 l = CategoriaDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
				else if(this.categoriaBuscaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("PASSAGEIRO"))
					 l = CategoriaPassageiroDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
				else if(this.categoriaBuscaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("CARGA"))
					 l = CategoriaCargaDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaCategoria(l);
			}else {
				List l = null;
				if(this.categoriaBuscaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("NORMAL"))
					 l = CategoriaDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
				else if(this.categoriaBuscaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("PASSAGEIRO"))
					 l = CategoriaPassageiroDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
				else if(this.categoriaBuscaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("CARGA"))
					 l = CategoriaCargaDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaCategoria(l);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void editar(ActionEvent event) {
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
				CategoriaPassageiro cp = (CategoriaPassageiro) this.tableCategoria.getSelectionModel().getSelectedItem();
				this.preencherCampos(cp);
				cPdao.persistOrMerge(cp);
				Alerta.mostrarAlertaInformacao("Categoria CP Editada com Sucesso!");
				this.limparCampos();
			}
			else if(cgb) {
				CategoriaCargaDao cGDao =  CategoriaCargaDao.getInstance();
				CategoriaCarga cp = (CategoriaCarga) this.tableCategoria.getSelectionModel().getSelectedItem();
				this.preencherCampos(cp);
				cGDao.persistOrMerge(cp);
				Alerta.mostrarAlertaInformacao("Categoria CG Editada com Sucesso!");
				this.limparCampos();
			}
			else {
				CategoriaDao cnDao = CategoriaDao.getInstance();
				Categoria cp = this.tableCategoria.getSelectionModel().getSelectedItem();
				this.preencherCampos(cp);
				cnDao.persistOrMerge(cp);
				Alerta.mostrarAlertaInformacao("Categoria CN Editada com Sucesso!");
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
	void eventoKPCategoria(KeyEvent event) {
		if(this.tableCategoria.getItems().size()>0) {
			if(event.getCode()==KeyCode.UP) {
				this.tableCategoria.getSelectionModel().selectPrevious();;
				this.carregar(this.tableCategoria.getSelectionModel().getSelectedItem());
				this.tableCategoria.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				this.tableCategoria.getSelectionModel().selectNext();
				this.carregar(this.tableCategoria.getSelectionModel().getSelectedItem());
				this.tableCategoria.getSelectionModel().selectPrevious();
			}
		}
	}

	@FXML
	void eventoMCCategoria(MouseEvent event) {
		if(this.tableCategoria.getItems().size()>0) {
			this.carregar(this.tableCategoria.getSelectionModel().getSelectedItem());
		}
	}
	@FXML
	void mudancaCategoria(ActionEvent event) {
		this.limparCampos();
		if(this.categoriaBuscaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Normal")) {
			this.desabilita(this.cgPane);
			this.desabilita(cpPane);
		}
		else if(this.categoriaBuscaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Carga")) {
			this.habilita(this.cgPane);
			this.desabilita(cpPane);
			this.gridPane.getChildren().remove(this.cgPane);
			this.gridPane.getChildren().remove(this.cpPane);
//			this.gridPane.add(this.cgPane, 1, 0);
//			this.gridPane.add(this.cpPane, 2, 0);
			this.animationGeral(cpPane, 2, 0);
			this.animationGeral(cgPane, 1, 0);
		}
		else if(this.categoriaBuscaBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Passageiro")) {
			this.desabilita(cgPane);
			this.habilita(this.cpPane);
			this.gridPane.getChildren().remove(this.cgPane);
			this.gridPane.getChildren().remove(this.cpPane);
//			this.gridPane.add(this.cpPane, 1, 0);
//			this.gridPane.add(this.cgPane, 2, 0);
			this.animationGeral(cgPane, 2, 0);
			this.animationGeral(cpPane, 1, 0);
		}
		this.tipoCategoriaBox.getSelectionModel().select(this.categoriaBuscaBox.getSelectionModel().getSelectedItem());
		System.out.println("Entrie");
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
		this.codCategoriaColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeCategoriaColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.situacaoCategoriaColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
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
		this.categoriaBuscaBox.getItems().addAll("NORMAL","PASSAGEIRO","CARGA");
		this.categoriaBuscaBox.getSelectionModel().select("NORMAL");
	}
	//Editar<>
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
	private void validacoesDeNull() throws ValidacaoException {
		//		if(this.fpc.getSenhaField().getText().length()<6 || this.fpc.getSenhaField().getText().length()>11 )
		//			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(this.tableCategoria.getSelectionModel().getSelectedItems().size()<=0)
			throw new ValidacaoException("Nehuma Categoria Encontrada!");
		if(this.nomeField.getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		//		if(this.fpc.getLoginField().getText().length()<=0)
		//			throw new ValidacaoException("O Login não pode ser nulo!");
		//		if(this.fpc.getCnpjField().getText().replace(" ","").trim().length()<=4) {
		//			throw new ValidacaoException("O CNPJ não pode ser nulo!");
		//		}
	}
	//Editar</>
	//Categoria<>
	private void atualizarTabelaCategoria(List<Categoria>flist) {
		ObservableList<Categoria>list = this.listaDeCategorias(flist);
		this.tableCategoria.setItems(list);
		this.tableCategoria.getSelectionModel().select(list.get(0));
		this.carregar(this.tableCategoria.getSelectionModel().getSelectedItem());
	}
	private static ObservableList<Categoria> listaDeCategorias(List<Categoria> l ) {
		ObservableList<Categoria>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void carregar(Categoria p) {
		this.preencherBusca(p);
	}
	private void preencherBusca(Categoria p) {	
		this.nomeField.setText(p.getNome());
		this.nPassageirosFiled.setText(p.getnPassageiro()+"");
		this.valorField.setText((p.getValor()+"0").replace(".", ","));
		this.horaLimpeza.setValue(TratadorDeMascara.dateToLocalTime(TratadorDeMascara.converterStringHora(p.getHoraLimpeza())));
		this.tipoCambioBox.getSelectionModel().select(p.getTipoCambio());
		//fpc.getIdField().setText(p.getId()+"");
		if(p.isArCondicionado())
			this.arCondCheck.setSelected(true);
		else
			this.arCondCheck.setSelected(false);
		if(p.isDvd())
			this.dvdCheck.setSelected(true);
		else
			this.dvdCheck.setSelected(false);
		if(p.isRadio())
			this.radioCheck.setSelected(true);
		else
			this.radioCheck.setSelected(false);
		if(p.isMp3())
			this.mp3Check.setSelected(true);
		else
			this.mp3Check.setSelected(false);
		if(p.isCameraRe())
			this.cameraReCheck.setSelected(true);
		else
			this.cameraReCheck.setSelected(false);
		if(p instanceof CategoriaPassageiro) {
			this.airBargBox.getSelectionModel().select(((CategoriaPassageiro) (p)).getTipoAirBag());
			if(((CategoriaPassageiro) (p)).isDirecaoAssistida())
				this.dirAssistidaCheck.setSelected(true);
			else
				this.dirAssistidaCheck.setSelected(false);
			if(((CategoriaPassageiro) (p)).isCintoSeguancaTraseiro())
				this.cintoSegCheck.setSelected(true);
			else
				this.cintoSegCheck.setSelected(false);
			if(((CategoriaPassageiro) (p)).isRodaLigaLeve())
				this.rodaLigaLeveCheck.setSelected(true);
			else
				this.rodaLigaLeveCheck.setSelected(false);
			if(((CategoriaPassageiro) (p)).isControlePoluicaoAr())
				this.controlePoluicaoCheck.setSelected(true);
			else
				this.controlePoluicaoCheck.setSelected(false);
		}else if(p instanceof CategoriaCarga) {
			this.capacidadeCargaField.setText(((CategoriaCarga) (p)).getCapacidadeCarga()+"");
			this.distanciaEixosField.setText(((CategoriaCarga) (p)).getDistanciaEixo()+"");
			this.potenciaMotorField.setText(((CategoriaCarga) (p)).getPotenciaMotor()+"");
			this.volCombustivelField.setText(	((CategoriaCarga) (p)).getVolumeCombustivel()+"");;
			this.embreagemBox.getSelectionModel().select(((CategoriaCarga) (p)).getTipoEmbreagem());
			this.consumoKmField.setText(	((CategoriaCarga) (p)).getConsumoKm());
		}
		this.tipoCategoriaBox.getSelectionModel().select(this.categoriaBuscaBox.getSelectionModel().getSelectedItem());
	}
	//Categoria</>
	//Other<>
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
	private void limparCampos() {
		LimparCampo.limparCamposFX(this.buscarCategoriaPanel.getChildren());
		this.tableCategoria.setItems(null);
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