package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.Veiculo;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.dao.LocacaoDao;
import br.com.palves.pbd.model.dao.VeiculoDao;
import br.com.palves.pbd.view.Alerta;
import br.com.palves.pbd.view.AlertaDetalhes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ControllerFXVeiculosAlugados implements Initializable{
	@FXML
	private AnchorPane retornoPane;

	@FXML
	private TableView<Locacao> tableLoc;

	@FXML
	private TableColumn<Locacao,Integer> codLocacaoColumn;

	@FXML
	private TableColumn<Locacao,Filial> pontoDEEntregaLocacaoColum;

	@FXML
	private TableColumn<Locacao, Date> dataEntregaLocacaoColumn;

	@FXML
	private TableColumn<Locacao, String> situacaoLocacaoColumn;

	@FXML
	private JFXButton buscarButton;

	@FXML
	private JFXButton infoButton;

	@FXML
	private TableView<Veiculo> tableVeiculo;

	@FXML
	private TableColumn<Veiculo,Integer> codColumn;

	@FXML
	private TableColumn<Veiculo,String> nomeVeiculoColumn;

	@FXML
	private TableColumn<Veiculo, String> placaVeiculoColumn;

	@FXML
	private TableColumn<Veiculo, String> chassiVeiculoColumn;

	@FXML
	private TableColumn<Veiculo, String> situacaoColumnVeiculoColumn;

	@FXML
	private TextField filtroField;
	@FXML
	void BuscarPorFiltro(ActionEvent event) {
		try {
			List<Veiculo> l =null;
			l = VeiculoDao.getInstance().buscarPorStatus(Corrente.funcionario.getFilial().getId(),StatusEnum.LOCADO.getValor(),"%"+this.filtroField.getText().toLowerCase()+"%");
			if(l==null) {
				Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				LimparCampo.limparCamposFXTOTAL(this.retornoPane.getChildren());
			}
			else {
				this.atualizarTabelaVeiculo(l);
			}
		} catch (DaoException e) {
			Alerta.mostrarAlertaErro(e.getMessage());
			e.printStackTrace();
		}
	}
	@FXML
	void mostrarDetalhes(ActionEvent event) {
		if(this.tableVeiculo.getItems().size()>0) {
			AlertaDetalhes.getBorder().getChildren().clear();
			//AlertaDetalhes.getBorder().setTop(App.getFechamentoDialog());
			AlertaDetalhes.getBorder().setCenter(App.getBuscarVeiculoPane());
			AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getBuscarVeiculoPane().getPrefWidth(),App.getBuscarVeiculoPane().getPrefHeight()-800);
			ControllerFXBuscarVeiculo cr = (ControllerFXBuscarVeiculo) Carregar.getBuscarVeiculoLoader().getController();
			List<Veiculo>lr = new ArrayList<>();
			lr.add(this.tableVeiculo.getSelectionModel().getSelectedItem());
			cr.atualizarTabelaVeiculo(lr);
			Carregar.detalhes = true;
			//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
			AlertaDetalhes.getInstance().showAndWait();
		}else
			Alerta.mostrarAlertaErro("Nenhum Veiculo Selecionado!!!");
	}
	@FXML
	void mudancaDeLocacaoClick(MouseEvent event) {

	}
	@FXML
	void mudancaDeLocacaoKey(KeyEvent event) {

	}
	@FXML
	void mudancaDeVeiculoClick(MouseEvent event) {
		if(this.tableVeiculo.getItems().size()>0) {
			this.buscarLocacao();
		}
	}
	@FXML
	void mudancaDeVeiculoKey(KeyEvent event) {
		if(this.tableVeiculo.getItems().size()>0) {
			if(event.getCode()==KeyCode.UP) {
				this.tableVeiculo.getSelectionModel().selectPrevious();;
				this.buscarLocacao();
				this.tableVeiculo.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				this.tableVeiculo.getSelectionModel().selectNext();
				this.buscarLocacao();
				this.tableVeiculo.getSelectionModel().selectPrevious();;
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.codLocacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.dataEntregaLocacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("dataEntrega"));
		this.situacaoLocacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		this.pontoDEEntregaLocacaoColum.setCellValueFactory(
				new PropertyValueFactory<>("filialEntrega"));
		this.codColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeVeiculoColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.placaVeiculoColumn.setCellValueFactory(
				new PropertyValueFactory<>("placa"));
		this.chassiVeiculoColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.situacaoColumnVeiculoColumn.setCellValueFactory(
				new PropertyValueFactory<>("status"));
	}
	//Veiculo<>
	private static ObservableList<Veiculo> listaDeVeiculos(List<Veiculo> l ) {
		ObservableList<Veiculo>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void atualizarTabelaVeiculo(List<Veiculo>flist) {
		ObservableList<Veiculo>list = this.listaDeVeiculos(flist);
		tableVeiculo.setItems(list);
		tableVeiculo.getSelectionModel().select(list.get(0));
		this.buscarLocacao();
	}
	//Veiculo</>
	private static ObservableList<Locacao> listaDeLocacoes(List<Locacao> l ) {
		ObservableList<Locacao>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void atualizarTabelaLocacao(List<Locacao>flist) {
		ObservableList<Locacao>list = this.listaDeLocacoes(flist);
		tableLoc.setItems(list);
		tableLoc.getSelectionModel().select(list.get(0));//Se chegou até aqui é pq encontrou resultados!
		//this.carregar(list.get(0));
		//this.preencherAreaCategoria(list.get(0));
	}
	private void buscarLocacao() {
		try {
			List l = LocacaoDao.getInstance().buscarPorVeiculo("%"+""+"%",this.tableVeiculo.getSelectionModel().getSelectedItem().getId());
			if(l!=null) {
				this.atualizarTabelaLocacao(l);
			
				l.toString();	
			}else
				Alerta.mostrarAlertaErro("Sem Locacoes associadas!");
		} catch (DaoException e) {
			Alerta.mostrarAlertaErro(e.getMessage());
			e.printStackTrace();
		}
	}

}
