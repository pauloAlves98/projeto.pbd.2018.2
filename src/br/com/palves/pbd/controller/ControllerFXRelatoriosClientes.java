package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.Relatorio;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.view.Alerta;
import br.com.palves.pbd.view.AlertaDetalhes;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControllerFXRelatoriosClientes implements Initializable {
	@FXML
	private AnchorPane retornoPane;

	@FXML
	private TableView<PessoaJuridica> tablePJ;

	@FXML
	private TableColumn<PessoaJuridica, Integer> codPJColumn;

	@FXML
	private TableColumn<PessoaJuridica, String> nomePJColumn;

	@FXML
	private TableColumn<PessoaJuridica, String> cnpjPJColumn;

	@FXML
	private TableColumn<PessoaJuridica, String> situacaoPJColumn;

	@FXML
	private JFXButton buscarButtonPF;
	@FXML
	private JFXButton buscarButtonPJ;
	@FXML
	private JFXButton infoButtonPF;

	@FXML
	private TextField filtroPFField;

	@FXML
	private TextField filtroFieldPJ;

	@FXML
	private JFXButton infoButtonPJ;

	@FXML
	private JFXButton addPJButton;

	@FXML
	private JFXButton limparPJButton;

	@FXML
	private TableView<PessoaJuridica> tableItensPJ;

	@FXML
	private TableColumn<PessoaJuridica, Integer> codIntenPJColumn;

	@FXML
	private TableColumn<PessoaJuridica, String> nomeItenPJColumn;

	@FXML
	private TableColumn<PessoaJuridica, String> cnpjItenColum;

	@FXML
	private TableColumn<PessoaJuridica, String> situacaoItenColumn;

	@FXML
	private JFXButton gerarPJButton;

	@FXML
	private JFXButton removerPJButton;

	@FXML
	private TableView<PessoaFisica> tableClientePF;

	@FXML
	private TableColumn<PessoaFisica, Integer> codClientePFColumn;

	@FXML
	private TableColumn<PessoaFisica, String> nomePFColumn;

	@FXML
	private TableColumn<PessoaFisica, String> cpfPFColumn;

	@FXML
	private TableColumn<PessoaFisica, String> situacaoPFColumn;

	@FXML
	private TableView<PessoaFisica> tableIntensPF;

	@FXML
	private TableColumn<PessoaFisica, Integer> codItenPFColum;

	@FXML
	private TableColumn<PessoaFisica, String> nomeItenPFColumn;

	@FXML
	private TableColumn<PessoaFisica, String> cpfItenColumn;

	@FXML
	private TableColumn<PessoaFisica, String> situacaoItenPFColumn;

	@FXML
	private JFXButton addButtonPF;

	@FXML
	private JFXButton limparButtonPF;

	@FXML
	private JFXButton gerarButtonPF;

	@FXML
	private JFXButton removerButtonPF;

	@FXML
	void BuscarPorFiltroPF(ActionEvent event) {
		if(event.getSource() == this.addButtonPF) {
			if( this.tableClientePF ==null ||this.tableClientePF.getItems()==null || this.tableClientePF.getItems().size()<=0)
			{
				Alerta.mostrarAlertaErro("Nehuma Pessoa Selecionada!");
				return;
			}
			List <PessoaFisica> at = this.tableIntensPF.getItems();
			if(at==null)
				at=new ArrayList();
			List<PessoaFisica>aux = this.tableClientePF.getSelectionModel().getSelectedItems();
			System.out.println(this.tableClientePF.getSelectionModel().getSelectedItems());
			for (PessoaFisica f:aux) {
				at.add(f);
			}
			this.atualizarTabelaPF(at,this.tableIntensPF);
		}else if(event.getSource() == this.removerButtonPF) {
			if( this.tableIntensPF ==null ||this.tableIntensPF.getItems()==null || this.tableIntensPF.getItems().size()<=0)
			{
				Alerta.mostrarAlertaErro("Nehuma Iten Pessoa Fisica Selecionada!");
				return;
			}
			List <PessoaFisica> at = this.tableIntensPF.getItems();
			if(at==null)
				at=new ArrayList();
			at.remove(this.tableIntensPF.getSelectionModel().getSelectedIndex());
			this.atualizarTabelaPF(at,this.tableIntensPF);
		}
		else if(event.getSource() == this.limparButtonPF) {
			this.tableIntensPF.setItems(null);
		}
		else if(event.getSource() == this.gerarButtonPF) {
			if( this.tableIntensPF ==null ||this.tableIntensPF.getItems()==null || this.tableIntensPF.getItems().size()<=0)
			{
				Alerta.mostrarAlertaErro("Nehum Item Pessoa Fisica!");
				return;
			}
			this.gerarRelatorio(tableIntensPF.getItems(),"relatorioPF");
		}
		else if(event.getSource() == this.buscarButtonPF) {
			try {
				if(this.filtroPFField.getText().replace(" ","").length()<=0) {
					List<PessoaFisica> l = PessoaFisicaDao.getInstance().buscarPorFiltro("%"+""+"%");
					if(l==null)
						Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
					else
						this.atualizarTabelaPF(l,this.tableClientePF);
				}else {

					List<PessoaFisica> l = PessoaFisicaDao.getInstance().buscarPorFiltro("%"+this.filtroPFField.getText().toLowerCase()+"%");
					if(l==null)
						Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
					else
						this.atualizarTabelaPF(l,this.tableClientePF);
				}
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}

	}
	@FXML
	void BuscarPorFiltroPJ(ActionEvent event) {
		if(event.getSource() == this.addPJButton) {
			if(this.tablePJ ==null ||this.tablePJ .getItems()==null || this.tablePJ .getItems().size()<=0)
			{
				Alerta.mostrarAlertaErro("Nehuma Pessoa PJ Selecionada!");
				return;
			}
			List <PessoaJuridica> at = this.tableItensPJ.getItems();
			if(at==null)
				at=new ArrayList();
			at.addAll(this.tablePJ.getSelectionModel().getSelectedItems());
			this.atualizarTabelaPJ(at,this.tableItensPJ);
		}else if(event.getSource() == this.removerPJButton) {
			if( this.tableItensPJ ==null || this.tableItensPJ.getItems()==null || this.tableItensPJ.getItems().size()<=0)
			{
				Alerta.mostrarAlertaErro("Nehuma Iten Pessoa Juridica Selecionada!");
				return;
			}
			List  <PessoaJuridica> at = this.tableItensPJ.getItems();
			if(at==null)
				at=new ArrayList();
			at.remove(this.tableItensPJ.getSelectionModel().getSelectedIndex());
			this.atualizarTabelaPJ(at,this.tableItensPJ);
		}
		else if(event.getSource() == this.limparPJButton) {
			this.tableItensPJ.setItems(null);
		}
		else if(event.getSource() == this.gerarPJButton) {
			if( this.tableItensPJ ==null || this.tableItensPJ.getItems()==null || this.tableItensPJ.getItems().size()<=0)
			{
				Alerta.mostrarAlertaErro("Nehum Item Pessoa Juridica!");
				return;
			}
			this.gerarRelatorio(tableItensPJ.getItems(),"relatorioPJ");
		}
		else if(event.getSource() == this.buscarButtonPJ) {
			try {
				if(this.filtroFieldPJ.getText().replace(" ","").length()<=0) {
					List<PessoaJuridica> l = PessoaJuridicaDao.getInstance().buscarPorFiltro("%"+""+"%");
					if(l==null)
						Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
					else
						this.atualizarTabelaPJ(l,this.tablePJ);
				}else {
					List<PessoaJuridica> l = PessoaJuridicaDao.getInstance().buscarPorFiltro("%"+this.filtroFieldPJ.getText().toLowerCase()+"%");
					if(l==null)
						Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
					else
						this.atualizarTabelaPJ(l,this.tablePJ);
				}
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void mostrarDetalhesPF(ActionEvent event) {
		if(this.tableClientePF.getItems().size()>0 && this.tableClientePF.getSelectionModel().getSelectedItems().size()==1) {
			AlertaDetalhes.getBorder().getChildren().clear();
			//AlertaDetalhes.getBorder().setTop(App.getFechamentoDialog());
			AlertaDetalhes.getBorder().setCenter(App.getBuscarPFPane());
			AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getBuscarPFPane().getPrefWidth(),App.getBuscarPFPane().getPrefHeight()-800);
			ControllerFXBuscarCliente cr = (ControllerFXBuscarCliente) Carregar.getBuscarClientePFLoader().getController();
			//			List<PessoaFisica>lr = new ArrayList<>();
			//			lr.add((this.tableClientePF.getSelectionModel().getSelectedItem());
			cr.atualizarTabelaPessoa(this.tableClientePF.getSelectionModel().getSelectedItems());
			Carregar.detalhes = true;
			//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
			AlertaDetalhes.getInstance().showAndWait();
		}else
			Alerta.mostrarAlertaErro("Nenhum Veiculo Selecionado!!!");
	}
	@FXML
	void mostrarDetalhesPJ(ActionEvent event) {
		if(this.tablePJ.getItems().size()>0 && this.tablePJ.getSelectionModel().getSelectedItems().size()==1) {
			AlertaDetalhes.getBorder().getChildren().clear();
			//AlertaDetalhes.getBorder().setTop(App.getFechamentoDialog());
			AlertaDetalhes.getBorder().setCenter(App.getBuscarPJPane());
			AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getBuscarPJPane().getPrefWidth(),App.getBuscarPJPane().getPrefHeight()-800);
			ControllerFXBuscarClientePJ cr = (ControllerFXBuscarClientePJ) Carregar.getBuscarClientePJLoader().getController();
			//			List<PessoaFisica>lr = new ArrayList<>();
			//			lr.add((this.tableClientePF.getSelectionModel().getSelectedItem());
			cr.atualizarTabelaPessoa(this.tablePJ.getSelectionModel().getSelectedItems());
			Carregar.detalhes = true;
			//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
			AlertaDetalhes.getInstance().showAndWait();
		}else
			Alerta.mostrarAlertaErro("Nenhum Veiculo Selecionado!!!");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.codPJColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomePJColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.cnpjPJColumn.setCellValueFactory(
				new PropertyValueFactory<>("cnpj"));
		this.situacaoPJColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		//--------- || ------- || ------- || -----
		this.codIntenPJColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeItenPJColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.cnpjItenColum.setCellValueFactory(
				new PropertyValueFactory<>("cnpj"));
		this.situacaoItenColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		//--------- || ------- || ------- || -----
		this.codClientePFColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomePFColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.cpfPFColumn.setCellValueFactory(
				new PropertyValueFactory<>("cpf"));
		this.situacaoPFColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		//--------- || ------- || ------- || -----
		this.codItenPFColum.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeItenPFColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.cpfItenColumn.setCellValueFactory(
				new PropertyValueFactory<>("cpf"));
		this.situacaoItenColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		this.tableClientePF.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.tablePJ.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	private static ObservableList<PessoaFisica> listaDePF(List<PessoaFisica> l ) {
		ObservableList<PessoaFisica>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void atualizarTabelaPF(List<PessoaFisica>flist, TableView pf) {
		ObservableList<PessoaFisica>list = this.listaDePF(flist);
		pf.setItems(list);
		pf.getSelectionModel().select(list.get(0));
	}
	private static ObservableList<PessoaJuridica> listaDePJ(List<PessoaJuridica> l ) {
		ObservableList<PessoaJuridica>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void atualizarTabelaPJ(List<PessoaJuridica>flist, TableView pj) {
		ObservableList<PessoaJuridica>list = this.listaDePJ(flist);
		pj.setItems(list);
		pj.getSelectionModel().select(list.get(0));
	}
	private void gerarRelatorio(List t,String nome) {
		new Thread(()->{
			Platform.runLater(()->{
				try {
					Relatorio.imprimir(t,"",nome+".jrxml");
				} catch (Exception e) {
					e.printStackTrace();
					Alerta.mostrarAlertaErro("Erro ao gerar visualização de relatorio!");
				}
			}
		);}).start();
	}

}
