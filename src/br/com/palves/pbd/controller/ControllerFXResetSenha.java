package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.dao.PessoaDao;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
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

public class ControllerFXResetSenha implements Initializable {
	@FXML
	private BorderPane resetSenhaPane;

	@FXML
	private AnchorPane AlterarPane;

	@FXML
	private TextField filtroField;

	@FXML
	private JFXButton pesquisarButton;

	@FXML
	private JFXButton resetarSenhaBuutton;

	@FXML
	private TableView<Pessoa> tableCliente;

	@FXML
	private TableColumn<Pessoa, Integer> codClienteColumn;

	@FXML
	private TableColumn<Pessoa, String> nomeClienteColumn;

	@FXML
	private TableColumn<Pessoa, String> situacaoClienteColumn;

	@FXML
	private JFXButton infoButton;

	@FXML
	void mostrarCliente(ActionEvent event) {
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
	void pesquisar(ActionEvent event) {
		try {
			if(this.filtroField.getText().replace(" ", "").length()<=14) {
				Pessoa l =null;
				l = PessoaFisicaDao.getInstance().buscarPorCpf(this.filtroField.getText());
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else {
					List p = new ArrayList();
					p.add(l);
					this.atualizarTabelaCliente(p);
				}
			}else {
				Pessoa l = null;
				l = PessoaJuridicaDao.getInstance().buscarPorCnpj(this.filtroField.getText());
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else {
					List p = new ArrayList();
					p.add(l);
					this.atualizarTabelaCliente(p);
				}
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void resetarSenha(ActionEvent event) {
		this.solicitarPermissao();
		if(ControllerFXCadastroLocacaoCReserva.isPermissaoGerente()) {
			if(this.tableCliente.getItems().size()<=0) {
				Alerta.mostrarAlertaInformacao("Escolha uma pessoa para resetar a senha!");
			}else {
				try {
					Pessoa p = this.tableCliente.getSelectionModel().getSelectedItem();
					p.setSenha(EncriptaDecriptaApacheCodec.codificaBase64Encoder("123456"));//Senha padrao!
					PessoaDao.getInstance().persistOrMerge(p);
					Alerta.mostrarAlertaInformacao("SENHA RESETADA COM SUCESSO!");
				} catch (DaoException e) {
					Alerta.mostrarAlertaErro(e.getMessage());
					e.printStackTrace();
				}
			}
			//Entao reseta a senha!	
		}else {
			Alerta.mostrarAlertaInformacao("Sem Permissão para executar esta funcionalidade");
			return;
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.codClienteColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.nomeClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		this.situacaoClienteColumn.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		MascaraFX.cpfCnpjField(this.filtroField);
	}
	//Pessoa<>
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
	private void solicitarPermissao() {
		AlertaDetalhes.getBorder().getChildren().clear();
		//AlertaDetalhes.getBorder().setTop(App.getFechamentoDialog());
		AlertaDetalhes.getBorder().setCenter(App.getPermissaoPane());
		AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getPermissaoPane().getPrefWidth(),App.getPermissaoPane().getPrefHeight()-800);
		//ControllerFXBuscarFilial cr = (ControllerFXBuscarFilial ) Carregar.getBuscarFilialLoader().getController();
		//List<Filial>lr = new ArrayList<>();
		//lr.add(this.tableFiilial.getSelectionModel().getSelectedItem());
		//cr.atualizarTabelaFilial(lr);
		Carregar.detalhes = true;
		//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
		AlertaDetalhes.getInstance().showAndWait();
	}
	//Pessoa</>
}
