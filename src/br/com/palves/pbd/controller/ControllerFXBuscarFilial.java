package br.com.palves.pbd.controller;

import java.awt.Color;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ControllerFXBuscarFilial implements Initializable{

	@FXML
	private BorderPane buscarFilialPanel;

	@FXML
	private TextField filtroField;

	@FXML
	private Button buscarFiltroButton;

	@FXML
	private TableView<Filial> tableFilial;

	@FXML
	private TableColumn<Filial, Integer> codFilialColumn;

	@FXML
	private TableColumn<Filial, String> nomeFilialColumn;

	@FXML
	private TableColumn<Filial, String> situacaoFilialColumn;

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
	private JFXButton excluirButton;
	@FXML
	private JFXTimePicker horaInicio;

	@FXML
	private JFXTimePicker horaFim;
	@FXML
	void buscarFilial(ActionEvent event) {
		try {
			if(this.filtroField.getText().replace(" ","").length()<=0) {
				List<Filial> l = FilialDao.getInstance().buscarPorFiltro("%"+""+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaFilial(l);
			}else {
				List<Filial> l = FilialDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
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
	void editar(ActionEvent event) {
		FilialDao daoPF = FilialDao.getInstance();
		String msg = "Filial Editada com Sucesso!";
		try {
			this.validacoes();
			Filial filial = this.tableFilial.getSelectionModel().getSelectedItem();
			this.preencherCampos(filial);
			if(event.getSource()==this.excluirButton) {
				Alert alert = new Alert(AlertType.WARNING,"", ButtonType.YES, ButtonType.NO);  //new alert object
				//alert.setTitle("Warning!");  //warning box title
				alert.setHeaderText("WARNING!!!");// Header
				alert.setContentText("Deseja Realmente Inativar esta Filial???"); //Discription of warning
				alert.getDialogPane().setPrefSize(500, 100); //sets size of alert box 
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.YES) {
					filial.setSituacao(StatusEnum.DESATIVADO.getValor());
					msg = "Filial Inativada Com Sucesso!";
				}else
					return;
			}
			daoPF.persistOrMerge(filial);
			Alerta.mostrarAlertaInformacao(msg);
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
	@FXML
	void eventoKPFilial(KeyEvent event) {
		if(this.tableFilial.getItems().size()>0) {
			if(event.getCode()==KeyCode.UP) {
				tableFilial.getSelectionModel().selectPrevious();;
				this.carregar(tableFilial.getSelectionModel().getSelectedItem());
				tableFilial.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				tableFilial.getSelectionModel().selectNext();
				this.carregar(tableFilial.getSelectionModel().getSelectedItem());
				tableFilial.getSelectionModel().selectPrevious();
			}
		}
	}
	@FXML
	void eventoMCFilial(MouseEvent event) {
		if(this.tableFilial.getItems().size()>0) {
			this.carregar(tableFilial.getSelectionModel().getSelectedItem());
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.codFilialColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeFilialColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.situacaoFilialColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		MascaraFX.cepField(cepField);
		MascaraFX.letrasField(ufField);
		MascaraFX.maxField(ufField, 2);
		MascaraFX.numericField(numeroField);

	}
	//Editar<>
	private void validacoes() throws ValidacaoException {
		if(this.nomeField.getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		if(this.horaInicio.getValue()==null)
			throw new ValidacaoException("É nescessario uma Hora de Inicio!");
		if(this.horaFim.getValue()==null)
			throw new ValidacaoException("É nescessario uma Hora de Termino!");
		if(this.tableFilial.getItems().size()<=0)
			throw new ValidacaoException("Escolha uma filial!");
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

		Endereco e = filial.getEndereco();
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
	private void carregar(Filial f) {
		LimparCampo.limparCamposFX(buscarFilialPanel.getChildren());
		preencherBusca(f);
	}
	private void preencherBusca(Filial filial) {	
		this.nomeField.setText(filial.getNome());
		//this.fpf.getIdField().setText(filial.getId()+"");
		//this.fpf.getCpfField().setText(pessoaF.getCpf());
		this.horaFim.setValue(TratadorDeMascara.dateToLocalTime(filial.getDataFimExpediente()));
		this.horaInicio.setValue(TratadorDeMascara.dateToLocalTime(filial.getHoraInicioExpediente()));
		this.ruaField.setText(filial.getEndereco().getRua());
		this.numeroField.setText(filial.getEndereco().getNumero()!=null?filial.getEndereco().getNumero()+"":"");
		this.bairroField.setText(filial.getEndereco().getBairro());
		this.cepField.setText(filial.getEndereco().getCep());
		this.ufField.setText(filial.getEndereco().getUf());
		this.cidadeField.setText(filial.getEndereco().getCidade());
		//this.fpf.getIdEnd().setText(filial.getEndereco().getId()+"");
	}
	//Editar</>
	//Filail<>
	public void atualizarFilial() 
	{//Filiais Ativas
		FilialDao fd = FilialDao.getInstance();
		try {
			List<Filial> filiais = FilialDao.getInstance().findAll(Filial.class);
			if(filiais!=null)
				this.atualizarTabelaFilial(filiais);
			else
				this.tableFilial.setItems(null);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void atualizarTabelaFilial(List<Filial>flist) {
		ObservableList<Filial>list = this.listaDeFiliais(flist);
		tableFilial.setItems(list);
		tableFilial.getSelectionModel().select(list.get(0));
		this.carregar(tableFilial.getSelectionModel().getSelectedItem());
	}

	//	private void preencherAreaFilial(Filial f) {Carregar!
	//		this.detalhesArea.setText(f.toStringArea());
	//	}
	private static ObservableList<Filial> listaDeFiliais(List<Filial> l ) {
		ObservableList<Filial>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	//Filial</>
	//Other<>
	private void limparCampos() {
		LimparCampo.limparCamposFX(buscarFilialPanel.getChildren());
		this.tableFilial.setItems(null);
		//
	}
	public void limparTudo() {
		this.limparCampos();
	}
	//Other</>

}
