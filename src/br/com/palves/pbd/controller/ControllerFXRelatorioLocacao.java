package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.bin.views.ViewLocacao;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.Relatorio;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.LocacaoDao;
import br.com.palves.pbd.model.dao.PessoaDao;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControllerFXRelatorioLocacao implements Initializable{
	@FXML
	private AnchorPane retornoPane;

	@FXML
	private TableView<ViewLocacao> tableLocacao;

	@FXML
	private TableColumn<ViewLocacao, Integer> codlocacaoColumn;

	@FXML
	private TableColumn<ViewLocacao, Date> dataRetiradaLocacaoColumn;

	@FXML
	private TableColumn<ViewLocacao, Date> dataEntregaColumn;

	@FXML
	private TableColumn<ViewLocacao, String> situacaoLocacaoColumn;

	@FXML
	private JFXButton buscarButtonMotorista;

	@FXML
	private JFXButton infoButtonMotorista;

	@FXML
	private TextField filtroPFField;

	@FXML
	private JFXButton buscarLocacaoButton;

	@FXML
	private JFXButton gerarlocacaoButton;

	@FXML
	private TableView<PessoaFisica> tableClientePF;//Motorista

	@FXML
	private TableColumn<PessoaFisica, Integer> codClientePFColumn;

	@FXML
	private TableColumn<PessoaFisica, String> nomePFColumn;

	@FXML
	private TableColumn<PessoaFisica, String> cpfPFColumn;

	@FXML
	private TableColumn<PessoaFisica, String> situacaoPFColumn;

	@FXML
	private TableView<Pessoa> tableCliente;

	@FXML
	private TableColumn<Pessoa, Integer> codItenClienteColum;

	@FXML
	private TableColumn<Pessoa,String> nomeClienteColumn;

	@FXML
	private TableColumn<Pessoa, String> situacaoClienteColumn;

	@FXML
	private JFXButton gerarButtonMotorista;

	@FXML
	private TextField filtroClienteField;

	@FXML
	private JFXButton buscarButtonPF1;

	@FXML
	private JFXButton infoButtonCliente;

	@FXML
	private JFXButton gerarButtonCliente;

	@FXML
	private JFXDatePicker dataInicio;

	@FXML
	private JFXDatePicker dataFinal;

	@FXML
	private JFXCheckBox financeiroCheck;

	@FXML
	void BuscarPorFiltroMotorista(ActionEvent event) {
		try {
			if(this.filtroPFField.getText().replace(" ","").length()<=0) {
				List<PessoaFisica> l = PessoaFisicaDao.getInstance().buscarPorFiltro("%"+""+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaMotorista(l);
			}else {

				List<PessoaFisica> l = PessoaFisicaDao.getInstance().buscarPorFiltro("%"+this.filtroPFField.getText().toLowerCase()+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaMotorista(l);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void BuscarPorFiltroCliente(ActionEvent event) {
		try {
			if(this.filtroPFField.getText().replace(" ","").length()<=0) {
				List<Pessoa> l = PessoaDao.getInstance().findAll(Pessoa.class);
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaCliente(l);
			}else {
				if(this.filtroClienteField.getText().replace(" ", "").length()<=14) {
					Pessoa l =null;
					l = PessoaFisicaDao.getInstance().buscarPorCpf(this.filtroClienteField.getText());
					if(l==null)
						Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
					else {
						List p = new ArrayList();
						p.add(l);
						this.atualizarTabelaCliente(p);
					}
				}else {
					Pessoa l = null;
					l = PessoaJuridicaDao.getInstance().buscarPorCnpj(this.filtroClienteField.getText());
					if(l==null)
						Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
					else {
						List p = new ArrayList();
						p.add(l);
						this.atualizarTabelaCliente(p);
					}
				}
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void BuscarPorFiltroPJ(ActionEvent event) {//Buscar por Periodo!
		try {
			if(this.dataInicio.getValue()==null || this.dataFinal.getValue()==null) {
				Alerta.mostrarAlertaErro("Preencha todas as datas.");
				return;
			}
			if(this.financeiroCheck.isSelected()) {
				if(Corrente.funcionario.getCargo().replace(" ","").equalsIgnoreCase("ATENDENTE".replace(" ",""))) {
					Alerta.mostrarAlertaErro("Você não tem permissão para acessar este relatorio!");
					return;
				}else {
					List<ViewLocacao> l = LocacaoDao.getInstance().buscarViewPorPeriodoFinanceiro(
							TratadorDeMascara.unirDataHora(TratadorDeMascara.localDatetoDate(this.dataInicio.getValue()),"00:00"),
							TratadorDeMascara.unirDataHora(TratadorDeMascara.localDatetoDate(this.dataFinal.getValue()),"23:59"));
					if(l==null)
						Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
					else
						this.atualizarTabelaLocacao(l);
				}
			}else {
				List<ViewLocacao> l = LocacaoDao.getInstance().buscarViewPorPeriodo(
						TratadorDeMascara.unirDataHora(TratadorDeMascara.localDatetoDate(this.dataInicio.getValue()),"00:00"),
						TratadorDeMascara.unirDataHora(TratadorDeMascara.localDatetoDate(this.dataFinal.getValue()),"23:59"));
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void gerarCliente(ActionEvent event) {
		if(this.tableCliente.getItems()==null || this.tableCliente.getItems().size()<=0) {
			Alerta.getInstance().mostrarAlertaErro("Nehum Cliente Selecionado!");
			return;
		}else {
			try {
				List l = LocacaoDao.getInstance().buscarViewPorCliente(this.tableCliente.getSelectionModel().getSelectedItem().getId());
				if(l==null || l.size()<=0) {
					Alerta.mostrarAlertaErro("Nehum resultado encontrado!");
					return;
				}
				this.gerarRelatorio(l,"relatorioLocacoesPorCliente");
			} catch (DaoException e) {
				Alerta.getInstance().mostrarAlertaErro(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	@FXML
	void gerarlocacao(ActionEvent event) {
		if(this.tableLocacao.getItems()==null || this.tableLocacao.getItems().size()<=0) {
			Alerta.getInstance().mostrarAlertaErro("Nehum Motorista Selecionado!");
			return;
		}
		else{
			if(this.financeiroCheck.isSelected()) {
				if(Corrente.funcionario.getCargo().replace(" ","").equalsIgnoreCase("ATENDENTE".replace(" ",""))) {
					Alerta.mostrarAlertaErro("Você não tem permissão para acessar este relatorio!");
					return;
				}else {
					this.gerarRelatorio(this.tableLocacao.getItems(),"relatorioLocacoesPorPeriodoFinanceiro");
					return;
				}
			}else {
				this.gerarRelatorio(this.tableLocacao.getItems(),"relatorioLocacoesPorPeriodo");
			}
		}
	}
	@FXML
	void gerarmotorista(ActionEvent event) {
		if(this.tableClientePF.getItems()==null || this.tableClientePF.getItems().size()<=0) {
			Alerta.getInstance().mostrarAlertaErro("Nehum Motorista Selecionado!");
			return;
		}else {
			try {
				List l = LocacaoDao.getInstance().buscarViewPorMotorista(this.tableClientePF.getSelectionModel().getSelectedItem().getCpf());
				if(l==null || l.size()<=0) {
					Alerta.mostrarAlertaErro("Nehum resultado encontrado!");
					return;
				}
				this.gerarRelatorio(l,"relatorioLocacoesPorMotorista");
			} catch (DaoException e) {
				Alerta.getInstance().mostrarAlertaErro(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@FXML
	void mostrarDetalhesCliente(ActionEvent event) {
		if(this.tableCliente.getItems().size()>0) {
			AlertaDetalhes.getBorder().getChildren().clear();
			if(this.tableCliente.getSelectionModel().getSelectedItem().getDiscriminador().equalsIgnoreCase("PF")) {
				AlertaDetalhes.getBorder().setCenter(App.getBuscarPFPane());
				AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getBuscarPFPane().getPrefWidth(),App.getBuscarPFPane().getPrefHeight()-800);
				ControllerFXBuscarCliente cr = (ControllerFXBuscarCliente) Carregar.getBuscarClientePFLoader().getController();
				List<PessoaFisica>lr = new ArrayList<>();
				lr.add((PessoaFisica) this.tableCliente.getSelectionModel().getSelectedItem());
				cr.atualizarTabelaPessoa(lr);
				Carregar.detalhes = true;
			}
			//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
			else {
				AlertaDetalhes.getBorder().setCenter(App.getBuscarPJPane());
				AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getBuscarPJPane().getPrefWidth(),App.getBuscarPJPane().getPrefHeight()-800);
				ControllerFXBuscarClientePJ cr = (ControllerFXBuscarClientePJ) Carregar.getBuscarClientePJLoader().getController();
				List<PessoaJuridica>lr = new ArrayList<>();
				lr.add((PessoaJuridica) this.tableCliente.getSelectionModel().getSelectedItem());
				cr.atualizarTabelaPessoa(lr);
				Carregar.detalhes = true;
			}
			AlertaDetalhes.getInstance().showAndWait();
		}else
			Alerta.mostrarAlertaErro("Nenhum Cliente Selecionado!!!");
	}

	@FXML
	void mostrarDetalhesMotorista(ActionEvent event) {
		if(this.tableClientePF.getItems().size()>0) {
			AlertaDetalhes.getBorder().getChildren().clear();
			//AlertaDetalhes.getBorder().setTop(App.getFechamentoDialog());
			AlertaDetalhes.getBorder().setCenter(App.getBuscarPFPane());
			AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getBuscarPFPane().getPrefWidth(),App.getBuscarPFPane().getPrefHeight()-50);
			ControllerFXBuscarCliente cr = (ControllerFXBuscarCliente ) Carregar.getBuscarClientePFLoader().getController();
			List<PessoaFisica>lr = new ArrayList<>();
			lr.add(this.tableClientePF.getSelectionModel().getSelectedItem());
			cr.atualizarTabelaPessoa(lr);
			Carregar.detalhes = true;
			//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
			AlertaDetalhes.getInstance().showAndWait();
		}else
			Alerta.mostrarAlertaErro("Nenhum Motorista Selecionado!!!");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.codClientePFColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomePFColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.cpfPFColumn.setCellValueFactory(
				new PropertyValueFactory<>("cpf"));
		this.situacaoPFColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		//--------- || ------- || ------- || -----
		this.codItenClienteColum.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeClienteColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.situacaoClienteColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		//--------- || ------- || ------- || -----
		this.codlocacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("cod"));
		this.dataRetiradaLocacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("dataRetirada"));
		this.dataEntregaColumn.setCellValueFactory(
				new PropertyValueFactory<>("dataPrevistaEntrega"));
		this.situacaoLocacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));

	}
	//Loc
	private static ObservableList<ViewLocacao> listaDeLocacoes(List<ViewLocacao> l ) {
		ObservableList<ViewLocacao>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	void atualizarTabelaLocacao(List<ViewLocacao>flist) {
		ObservableList<ViewLocacao>list = this.listaDeLocacoes(flist);
		tableLocacao.setItems(list);
		tableLocacao.getSelectionModel().select(list.get(0));//Se chegou até aqui é pq encontrou resultados!
		//this.preencherAreaCategoria(list.get(0));
	}
	//Motorista<>
	private static ObservableList<PessoaFisica> listaDeMotoristas(List<PessoaFisica> l ) {
		ObservableList<PessoaFisica>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void atualizarTabelaMotorista(List<PessoaFisica>flist) {
		ObservableList<PessoaFisica>list = this.listaDeMotoristas(flist);
		this.tableClientePF.setItems(list);
		this.tableClientePF.getSelectionModel().select(list.get(0));
	}
	//Motorista</>
	//Cliente
	private static ObservableList<Pessoa> listaDeClientes(List<Pessoa> l ) {
		ObservableList<Pessoa>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void atualizarTabelaCliente(List<Pessoa>flist) {
		ObservableList<Pessoa>list = this.listaDeClientes(flist);
		tableCliente.setItems(list);
		tableCliente.getSelectionModel().select(list.get(0));//Se chegou até aqui é pq encontrou resultados!
		//this.preencherAreaCategoria(list.get(0));
	}
	//Cliente</>
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
