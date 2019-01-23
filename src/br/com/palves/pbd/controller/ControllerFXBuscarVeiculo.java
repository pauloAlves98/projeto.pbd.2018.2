package br.com.palves.pbd.controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;

import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Veiculo;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.CategoriaDao;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.VeiculoDao;
import br.com.palves.pbd.view.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class ControllerFXBuscarVeiculo implements Initializable{
	@FXML
	private BorderPane borderGeral;

	@FXML
	private BorderPane cadastroVeiculoPane;

	@FXML
	private TextField modeloField;

	@FXML
	private TextField nMotorField;

	@FXML
	private JFXButton salvarButton;

	@FXML
	private TableView<Filial> tableFiilial;

	@FXML
	private TableColumn<Filial, Integer> codFilialColumn;

	@FXML
	private TableColumn<Filial, String> nomeFilialColumn;

	@FXML
	private TextArea detalhesFilialArea;

	@FXML
	private TextField filialRetiradaField;

	@FXML
	private Button buscarFilialButton;

	@FXML
	private TextField buscaCategoriaField;

	@FXML
	private Button buscarCategoriaButton;

	@FXML
	private TableView<Categoria> tableCategoria;

	@FXML
	private TableColumn<Categoria, Integer> codCategoriaColumn;

	@FXML
	private TableColumn<Categoria,String> nomeCategoriaColumn;

	@FXML
	private TextArea detalhesCategoriaArea;

	@FXML
	private JFXComboBox<String> tamanhoBox;

	@FXML
	private TextField nChassiField;

	@FXML
	private TextField nomeField;

	@FXML
	private JFXCheckBox gasolinaCheck;

	@FXML
	private JFXCheckBox dieselCheck;

	@FXML
	private JFXCheckBox biocombustivelCheck;

	@FXML
	private JFXCheckBox etanolCheck;

	@FXML
	private TextField nPortasField;

	@FXML
	private TextField anoModeloField;

	@FXML
	private TextField fabricanteField;

	@FXML
	private TextField anoFabricacaoField;

	@FXML
	private TextField torqueMotorField;

	@FXML
	private JFXTimePicker horaRevisao;
	@FXML
	private JFXColorPicker corColor;
	@FXML
	private TextField kmAtualField;
	@FXML
	private JFXButton excluirButton;
	@FXML
	private TextField filtroField;
	@FXML
	private Button buscarFiltroButton;
	@FXML
	private TableView<Veiculo> tableVeiculo;
	@FXML
	private TableColumn<Veiculo, Integer> codColumn;
	@FXML
	private TableColumn<Veiculo, String> situacaoColumn;
	@FXML
	private TextField placaField;
	@FXML
	void buscarCategoria(ActionEvent event) {
		try {
			if(this.buscaCategoriaField.getText().replace(" ","").length()<=0) {
				List<Categoria> l = CategoriaDao.getInstance().buscarPorParametro("%"+""+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaCategoria(l);
			}else {
				List<Categoria> l = CategoriaDao.getInstance().buscarPorParametro("%"+this.buscaCategoriaField.getText().toLowerCase()+"%");
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
	void buscarFilial(ActionEvent event) {
		try {
			if(this.filialRetiradaField.getText().replace(" ","").length()<=0) {
				List<Filial> l = FilialDao.getInstance().buscarPorParametro("%"+""+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaFilial(l);
			}else {

				List<Filial> l = FilialDao.getInstance().buscarPorParametro("%"+this.filialRetiradaField.getText().toLowerCase()+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaFilial(l);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void mudancaDeFilialClick(MouseEvent event) {
		if(this.tableFiilial.getItems().size()>0) {
			this.preencherAreaFilial(tableFiilial.getSelectionModel().getSelectedItem());
		}
	}
	@FXML
	void mudarFilialKey(KeyEvent event) {
		if(this.tableFiilial.getItems().size()>0) {
			if(event.getCode()==KeyCode.UP) {
				tableFiilial.getSelectionModel().selectPrevious();;
				this.preencherAreaFilial(tableFiilial.getSelectionModel().getSelectedItem());
				tableFiilial.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				tableFiilial.getSelectionModel().selectNext();
				this.preencherAreaFilial(tableFiilial.getSelectionModel().getSelectedItem());
				tableFiilial.getSelectionModel().selectPrevious();
			}
		}
	}
	@FXML
	void mudarCategoriaClick(MouseEvent event) {
		if(this.tableCategoria.getItems().size()>0) {
			this.preencherAreaCategoria(this.tableCategoria.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	void mudarCategoriaKey(KeyEvent event) {
		if(this.tableCategoria.getItems().size()>0) {
			if(event.getCode()==KeyCode.UP) {
				this.tableCategoria.getSelectionModel().selectPrevious();;
				this.preencherAreaCategoria(this.tableCategoria.getSelectionModel().getSelectedItem());
				this.tableCategoria.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				this.tableCategoria.getSelectionModel().selectNext();
				this.preencherAreaCategoria(this.tableCategoria.getSelectionModel().getSelectedItem());
				this.tableCategoria.getSelectionModel().selectPrevious();;
			}
		}
	}
	@FXML
	void editarVeiculo(ActionEvent event) {//Editar
		if(Carregar.detalhes) {
			return;
		}
		VeiculoDao daoPJ = VeiculoDao.getInstance();
		try {
			this.validacoesDeNull();
			if(event.getSource()==salvarButton) {
				Veiculo v = this.tableVeiculo.getSelectionModel().getSelectedItem();
				this.preencherCampos(v);
				v = daoPJ.persistOrMerge(v);
				Alerta.mostrarAlertaInformacao(v.getNome()+" Cadastrado com Sucesso!!!");
				this.limparCampos();
			}else {
				Alert alert = new Alert(AlertType.WARNING,"", ButtonType.YES, ButtonType.NO);  //new alert object
				//alert.setTitle("Warning!");  //warning box title
				alert.setHeaderText("WARNING!!!");// Header
				alert.setContentText("Deseja Realmente Inativar este Veiculo???"); //Discription of warning
				alert.getDialogPane().setPrefSize(500, 100); //sets size of alert box 
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.YES) {
					Veiculo p = this.tableVeiculo.getSelectionModel().getSelectedItem();
					//this.validacoes();
					this.preencherCampos(p);
					p.setStatus(StatusEnum.DESATIVADO.getValor());
					p = daoPJ.persistOrMerge(p);
					Alerta.mostrarAlertaInformacao("Veiculo Inativado com sucesso!");
					this.limparCampos();
				}
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.codFilialColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeFilialColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.codCategoriaColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeCategoriaColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.situacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("status"));
		this.codColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.tamanhoBox.getItems().addAll("Pequeno","Medio","Grande");
		this.tamanhoBox.getSelectionModel().select("Pequeno");


		MascaraFX.maxField(this.nChassiField,17);
		MascaraFX.numericField(this.nPortasField);
		MascaraFX.numericField(this.anoModeloField);
		MascaraFX.maxField(this.anoModeloField,4);
		MascaraFX.numericField(this.anoFabricacaoField);
		MascaraFX.maxField(this.anoFabricacaoField,4);
		MascaraFX.placa(this.placaField);
		this.placaField.positionCaret(this.placaField.getText().replace(" ","").length());
	}
	@FXML
	void buscarPorFiltro(ActionEvent event) {
		if(Carregar.detalhes) {
			Alerta.mostrarAlertaErro("Este recurso está indisponivel no momento!!!");
			return;
		}
		try {
			if(this.filtroField.getText().replace(" ","").length()<=0) {
				List<Veiculo> l = VeiculoDao.getInstance().buscarPorFiltro("%"+""+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaVeiculo(l);
			}else {
				List<Veiculo> l  = VeiculoDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaVeiculo(l);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void clickEvent(MouseEvent event) {
		if(this.tableVeiculo.getItems().size()>0) {
			this.carregar(this.tableVeiculo.getSelectionModel().getSelectedItem());
		}
	}
	@FXML
	void keyPEvent(KeyEvent event) {
		if(this.tableVeiculo.getItems().size()>0) {
			if(event.getCode()==KeyCode.UP) {
				this.tableVeiculo.getSelectionModel().selectPrevious();;
				this.carregar(this.tableVeiculo.getSelectionModel().getSelectedItem());
				this.tableVeiculo.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				this.tableVeiculo.getSelectionModel().selectNext();
				this.carregar(this.tableVeiculo.getSelectionModel().getSelectedItem());
				this.tableVeiculo.getSelectionModel().selectPrevious();;
			}
		}
	}
	//editar<>
	private void validacoesDeNull() throws ValidacaoException {
		if(tableVeiculo.getItems().size()<=0)
			throw new ValidacaoException("É nescessario um veiculo para realizar essa operação!!!");
		if(this.horaRevisao.getValue()==null)
			throw new ValidacaoException("Escolha a quantidade de horas para revisão!");
		if(this.nomeField.getText().replace(" "," ").length()<=0)
			throw new ValidacaoException("Digite um Nome!");
		if(this.nChassiField.getText().replace(" "," ").length()<=0)
			throw new ValidacaoException("Digite um Chassi!");
		if(this.placaField.getText().replace(" "," ").length()<=0)
			throw new ValidacaoException("Digite uma Placa!");
		if(this.torqueMotorField.getText().replace(" "," ").length()<=0)
			throw new ValidacaoException("Digite um valor para o Torque!");
		if(this.nMotorField.getText().replace(" "," ").length()<=0)
			throw new ValidacaoException("Digite um Nº do Motor!");
		if(this.fabricanteField.getText().replace(" "," ").length()<=0)
			throw new ValidacaoException("Digite um Fabricante!");
		if(this.tableFiilial.getItems().size()<=0)
			throw new ValidacaoException("Escolha Uma Filial!!!");
		if(tableCategoria.getItems().size()<=0)
			throw new ValidacaoException("Escolha Uma Categoria!!!");
	}
	private void preencherCampos(Veiculo veiculo) {
		String nome = nomeField.getText().toUpperCase();
		//String idField = this.fpv.getIdField().getText();
		String numeroChassi = this.nChassiField.getText().toUpperCase();
		int numeroPortas = this.nPortasField.getText().length()<=0?4:Integer.parseInt(this.nPortasField.getText());
		String g =this.gasolinaCheck.isSelected()?this.gasolinaCheck.getText():"NOT";
		String e = this.etanolCheck.isSelected()? this.etanolCheck.getText():"NOT";
		String d = this.dieselCheck.isSelected()?this.dieselCheck.getText():"NOT";
		String bio = this.biocombustivelCheck.isSelected()?this.biocombustivelCheck.getText():"NOT";

		String combustivel = (g.equals("NOT")?"":g) + (e.equals("NOT")?"":";"+e) + (d.equals("NOT")?"":";"+d) + (bio.equals("NOT")?"":";"+bio);
		String tamanho =  this.tamanhoBox.getSelectionModel().getSelectedItem();
		String torque =  this.torqueMotorField.getText();
		String numeroMotor =  this.nMotorField.getText();
		String modelo =  this.modeloField.getText().toUpperCase();
		int anoModelo =  this.anoModeloField.getText().replace(" ","").length()<=0?0:Integer.parseInt(this.anoModeloField.getText().replace(" ",""));
		String cor = this.corColor.getValue().toString();
		System.out.println(this.corColor.getPromptText());
		String fabricante = this.fabricanteField.getText().toUpperCase();
		int anoFabricacao = this.anoFabricacaoField.getText().replace(" ","").length()<=0?2019:Integer.parseInt(this.anoFabricacaoField.getText().replace(" ",""));
		Date horaRevisao = TratadorDeMascara.localTimeToDate(this.horaRevisao.getValue());
		int kmAtual = this.kmAtualField.getText().replace(" ","").length()<=0?0:Integer.parseInt( this.kmAtualField.getText().replace(" ",""));
		int kmRev= tamanho.equalsIgnoreCase("Grande")?10000:Integer.parseInt("5000");
		//int  idFilial = Integer.parseInt(this.fpv.getCodFilial().getText());
		//int idCategoria = Integer.parseInt(this.fpv.getCodCategoriaField().getText());
		//		if(idField.trim().length()>0) {//então eh update
		//			veiculo.setId(Integer.parseInt(idField));
		//		}
		Filial f = this.tableFiilial.getSelectionModel().getSelectedItem();
		//f.setId(idFilial);
		Categoria cat = this.tableCategoria.getSelectionModel().getSelectedItem();
		//cat.setId(idCategoria);
		veiculo.setFilialAtual(f);
		veiculo.setCategoria(cat);
		veiculo.setNome(nome.toUpperCase());
		veiculo.setNumeroChassi(numeroChassi.toLowerCase());
		veiculo.setnPorta(numeroPortas);
		veiculo.setTipoCombustivel(combustivel);
		veiculo.setTamanho(tamanho);
		veiculo.setTorqueMotor(torque.toUpperCase());
		veiculo.setNumeroMotor(numeroMotor);
		veiculo.setModelo(modelo.toUpperCase());
		veiculo.setAnoModelo(anoModelo);
		veiculo.setCor(cor);
		veiculo.setFabricante(fabricante.toUpperCase());
		veiculo.setAnoFabricao(anoFabricacao);
		veiculo.setHoraRevisao(horaRevisao);
		veiculo.setKm_revisao(kmRev);
		veiculo.setKmAtual(kmAtual);
		veiculo.setPlaca(this.placaField.getText());
		veiculo.setStatus(StatusEnum.ATIVO.getValor());//tem que ser um campo
	}
	private void preencherBusca(Veiculo p) {	
		this.nomeField.setText(p.getNome());
		//this.fpv.getIdField().setText(p.getId()+"");
		this.nChassiField.setText(p.getNumeroChassi());
		this.nPortasField.setText(p.getnPorta()+"");
		String [] comb = p.getTipoCombustivel().split(";");
		this.gasolinaCheck.setSelected(false);
		this.etanolCheck.setSelected(false);
		this.dieselCheck.setSelected(false);
		this.biocombustivelCheck.setSelected(false);
		for (int i = 0;i <comb.length;i++) {
			if(comb[i].equalsIgnoreCase("Gasolina"))
				this.gasolinaCheck.setSelected(true);
			else if(comb[i].equalsIgnoreCase("Etanol"))
				this.etanolCheck.setSelected(true);
			else if(comb[i].equalsIgnoreCase("Diesel"))
				this.dieselCheck.setSelected(true);
			else if(comb[i].equalsIgnoreCase("Biocombustivel"))
				this.biocombustivelCheck.setSelected(true);
		}
		this.tamanhoBox.getSelectionModel().select(p.getTamanho());
		this.torqueMotorField.setText(p.getTorqueMotor());
		this.nMotorField.setText(p.getNumeroMotor());
		this.modeloField.setText(p.getModelo());
		this.anoModeloField.setText(p.getAnoModelo()+"");
		this.corColor.setValue(Color.web(p.getCor()));
		this.fabricanteField.setText(p.getFabricante());
		this.anoFabricacaoField.setText(p.getAnoFabricao()+"");
		this.horaRevisao.setValue(TratadorDeMascara.dateToLocalTime(p.getHoraRevisao()));
		this.kmAtualField.setText(p.getKmAtual()+"");
		this.placaField.setText(p.getPlaca());
		System.out.println(p.getPlaca());
		List<Filial> lf = new ArrayList();
		lf.add(p.getFilialAtual());
		List<Categoria> lc = new ArrayList();
		lc.add(p.getCategoria());
		this.atualizarTabelaFilial(lf);
		this.atualizarTabelaCategoria(lc);

	}
	private void carregar(Veiculo p) {
		preencherBusca(p);
	}
	//Editar</>
	//Veiculo<>
	private static ObservableList<Veiculo> listaDeVeiculos(List<Veiculo> l ) {
		ObservableList<Veiculo>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	public void atualizarTabelaVeiculo(List<Veiculo>flist) {
		ObservableList<Veiculo>list = this.listaDeVeiculos(flist);
		tableVeiculo.setItems(list);
		tableVeiculo.getSelectionModel().select(list.get(0));//Se chegou até aqui é pq encontrou resultados!
		carregar(this.tableVeiculo.getSelectionModel().getSelectedItem());
	}
	//Veiculo</>
	//Filial<>
	private static ObservableList<Filial> listaDeFiliais(List<Filial> l ) {
		ObservableList<Filial>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void preencherAreaFilial(Filial f) {
		this.detalhesFilialArea.setText(f.toStringArea());
	}
	private void atualizarTabelaFilial(List<Filial>flist) {
		ObservableList<Filial>list = this.listaDeFiliais(flist);
		tableFiilial.setItems(list);
		tableFiilial.getSelectionModel().select(list.get(0));
		this.preencherAreaFilial(list.get(0));
	}
	//Filial</>
	//Categoria<>
	private static ObservableList<Categoria> listaDeCategorias(List<Categoria> l ) {
		ObservableList<Categoria>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void preencherAreaCategoria(Categoria f) {
		this.detalhesCategoriaArea.setText(f.toString());
	}
	private void atualizarTabelaCategoria(List<Categoria>flist) {
		ObservableList<Categoria>list = this.listaDeCategorias(flist);
		tableCategoria.setItems(list);
		tableCategoria.getSelectionModel().select(list.get(0));//Se chegou até aqui é pq encontrou resultados!
		this.preencherAreaCategoria(list.get(0));
	}
	//Categoria</>
	//Others<>
	private void limparCampos() {
		LimparCampo.limparCamposFXTOTAL(this.borderGeral.getChildren());
	}
	//Others</>
}
