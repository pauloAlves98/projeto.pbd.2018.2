package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Veiculo;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerFXDisponibilidadeVeiculo implements Initializable {
	@FXML
	private BorderPane resetSenhaPane;

	@FXML
	private AnchorPane AlterarPane;
	@FXML
	private JFXButton pesquisarButton;

	@FXML
	private TableView<Veiculo> tableVeiculo;

	@FXML
	private TableColumn<Veiculo, Integer> codVeiculoColumn;

	@FXML
	private TableColumn<Veiculo, String> nomeVeiculoColumn;

	@FXML
	private TableColumn<Veiculo,String > placaVeiculoColumn;

	@FXML
	private TableColumn<Veiculo, String> situacaoVeiculoColumn;
	@FXML
	private JFXButton infoButton;

	@FXML
	private JFXDatePicker dataVeiculo;

	@FXML
	void mostrarVeiculo(ActionEvent event) {
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
	void pesquisar(ActionEvent event) {
		if(dataVeiculo.getValue()!=null) {
			VeiculoDao vd = VeiculoDao.getInstance();
			try {
				List v = vd.buscarPorData(TratadorDeMascara.localDatetoDate(dataVeiculo.getValue()),Corrente.funcionario.getFilial().getId());
				System.out.println(Corrente.funcionario.getFilial().getId()+" Filial Id  ----------------");
				if(v!=null)
					this.atualizarTabelaVeiculo(v);
				else
					Alerta.mostrarAlertaErro("Nehum Veículo encontrado!");
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else
			Alerta.mostrarAlertaErro("Escolha uma data para realizar a pesquisa!");
			
		
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.codVeiculoColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeVeiculoColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.placaVeiculoColumn.setCellValueFactory(
				new PropertyValueFactory<>("placa"));
		this.situacaoVeiculoColumn.setCellValueFactory(
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
	}
	//Veiculo</>

}
