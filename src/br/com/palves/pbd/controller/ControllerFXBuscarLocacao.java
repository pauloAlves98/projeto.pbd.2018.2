package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.bin.Reserva;
import br.com.palves.pbd.model.bin.Veiculo;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.LocacaoDao;
import br.com.palves.pbd.model.dao.ReservaDao;
import br.com.palves.pbd.view.Alerta;
import br.com.palves.pbd.view.AlertaDetalhes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ControllerFXBuscarLocacao implements Initializable{
	@FXML
	private BorderPane borderGeral;

	@FXML
	private TableView<Locacao> tableLocacao;

	@FXML
	private TableColumn<Locacao, Integer> codColumn;

	@FXML
	private TableColumn<Locacao, String> situacaoColumn;

	@FXML
	private TextField filtroField;

	@FXML
	private Button buscarLocacaoButton;

	@FXML
	private JFXDatePicker dataInicialPesquisa;

	@FXML
	private JFXDatePicker dataFinalPesquisa;

	@FXML
	private BorderPane locacaoPane;

	@FXML
	private JFXButton salvarButton;

	@FXML
	private TableView<Filial> tableFiilial;

	@FXML
	private TableColumn<Filial, Integer> codFilialColumn;

	@FXML
	private TableColumn<Filial, String> nomeFilialColumn;

	@FXML
	private TextField filialEntregaField;

	@FXML
	private Button buscarFilialButton;

	@FXML
	private TextField buscaVeiculoField;

	@FXML
	private Button buscarVeiculoButton;

	@FXML
	private TableView<Veiculo> tableVeiculo;

	@FXML
	private TableColumn<Veiculo, Integer> codVeiculoColumn;

	@FXML
	private TableColumn<Veiculo, String> nomeVeiculoColumn;

	@FXML
	private TableColumn<Veiculo, String> situacaoVeiculoColumn;

	@FXML
	private TextField cpfCnpjField;

	@FXML
	private TableView<Pessoa> tableCliente;

	@FXML
	private TableColumn<Pessoa, Integer> codClienteColumn;

	@FXML
	private TableColumn<Pessoa, String> nomeClienteColumn;

	@FXML
	private TableColumn<Pessoa, String> situacaoClienteColumn;

	@FXML
	private Button buscarReservaButton;

	@FXML
	private JFXDatePicker dataEntrega;

	@FXML
	private JFXTimePicker horaEntrega;

	@FXML
	private Button infoFilialButton;

	@FXML
	private Label nomeFilialRetirada;

	@FXML
	private RadioButton kmLivre;

	@FXML
	private RadioButton kmControle;

	@FXML
	private Button buscarInfoButton;

	@FXML
	private Button infoClienteButton;

	@FXML
	private TableView<PessoaFisica> tableMotorista;

	@FXML
	private TableColumn<PessoaFisica, Integer> codMotoristaColumn;

	@FXML
	private TableColumn<PessoaFisica, String> nomeMotoristaColumn;

	@FXML
	private TableColumn<PessoaFisica, String> cpfMotoristaColumn;

	@FXML
	private Button buscarMotoristaButton;

	@FXML
	private TextField buscarMotoristaField;

	@FXML
	private Button cadastroMotoristaButton;

	@FXML
	private TextField precoLocacaoField;

	@FXML
	private TextField valorVeiculoField;

	@FXML
	private TextField valorDiaria;

	@FXML
	private TextField entradaField;

	@FXML
	private Button calcularValoresButton;
	@FXML
	void buscarLocacao(ActionEvent event) {
		try {
			if(this.filtroField.getText().replace(" ","").length()>0 && this.dataInicialPesquisa.getValue()==null && this.dataFinalPesquisa.getValue()==null) {
				String txt = "%"+filtroField.getText().toLowerCase()+"%";
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro(txt,
						TratadorDeMascara.coletorDeData("10/12/1998"),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.coletorDeData("10/12/3000"),"23:59"));	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataInicialPesquisa.getValue()==null && this.dataFinalPesquisa.getValue()==null) {//Nehum
				String txt = "%"+" "+"%";
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro(txt,
						TratadorDeMascara.coletorDeData("10/12/1998"),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.coletorDeData("10/12/3000"),"23:59"));	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataInicialPesquisa.getValue()==null && this.dataFinalPesquisa.getValue()!=null) {//Só por data final
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+""+"%",
						TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),"23:59")
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataFinalPesquisa.getValue()==null && this.dataInicialPesquisa.getValue()!=null) {//Só por data inicial
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+"a"+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),"23:59")
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()>0 && this.dataFinalPesquisa.getValue()==null && this.dataInicialPesquisa.getValue()!=null) {//Por filtro e data Inicial
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),"23:59")
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()>0 && this.dataInicialPesquisa.getValue()==null && this.dataFinalPesquisa.getValue()!=null) {//por filtro e dat Final
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%",
						TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),"23:59")
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataInicialPesquisa.getValue()!=null && this.dataFinalPesquisa.getValue()!=null) {//Só por data
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+""+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),"23:59")
						);	
				System.out.println(this.dataInicialPesquisa.getValue().toString());
				System.out.println(this.dataFinalPesquisa.getValue().toString());
				System.out.println(TratadorDeMascara.unirDataHora(
						TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),"23:59").toString());
				System.out.println("Iiii");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else {
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),"23:59")
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void buscarCliente(ActionEvent event) {

	}
	@FXML
	void buscarFilial(ActionEvent event) {

	}
	@FXML
	void buscarMotorista(ActionEvent event) {

	}

	@FXML
	void buscarVeiculo(ActionEvent event) {

	}

	@FXML
	void cadastrarMotorista(ActionEvent event) {

	}

	@FXML
	void calcValoresLocacao(ActionEvent event) {

	}

	@FXML
	void mostrarCliente(ActionEvent event) {
		System.out.println();
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
		void mostrarFilial(ActionEvent event) {
			if(this.tableFiilial.getItems().size()>0) {
				AlertaDetalhes.getBorder().getChildren().clear();
				//AlertaDetalhes.getBorder().setTop(App.getFechamentoDialog());
				AlertaDetalhes.getBorder().setCenter(App.getBuscarFilialPane());
				AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getBuscarFilialPane().getPrefWidth(),App.getBuscarFilialPane().getPrefHeight()-800);
				ControllerFXBuscarFilial cr = (ControllerFXBuscarFilial ) Carregar.getBuscarFilialLoader().getController();
				List<Filial>lr = new ArrayList<>();
				lr.add(this.tableFiilial.getSelectionModel().getSelectedItem());
				cr.atualizarTabelaFilial(lr);
				Carregar.detalhes = true;
				//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
				AlertaDetalhes.getInstance().showAndWait();
			}else
				Alerta.mostrarAlertaErro("Nenhuma Filial Selecionada!!!");
		}
		@FXML
		void mostrarMotorista(ActionEvent event) {
			if(this.tableMotorista.getItems().size()>0) {
				AlertaDetalhes.getBorder().getChildren().clear();
				//AlertaDetalhes.getBorder().setTop(App.getFechamentoDialog());
				AlertaDetalhes.getBorder().setCenter(App.getBuscarPFPane());
				AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getBuscarPFPane().getPrefWidth(),App.getBuscarPFPane().getPrefHeight()-50);
				ControllerFXBuscarCliente cr = (ControllerFXBuscarCliente ) Carregar.getBuscarClientePFLoader().getController();
				List<PessoaFisica>lr = new ArrayList<>();
				lr.add(this.tableMotorista.getSelectionModel().getSelectedItem());
				cr.atualizarTabelaPessoa(lr);
				Carregar.detalhes = true;
				//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
				AlertaDetalhes.getInstance().showAndWait();
			}else
				Alerta.mostrarAlertaErro("Nenhum Motorista Selecionado!!!");
		}
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
		void mudancaDeFilialClick(MouseEvent event) {

		}

		@FXML
		void mudancaDeLocacaoClick(MouseEvent event) {
			if(this.tableLocacao.getItems().size()>0) {
				this.carregar(tableLocacao.getSelectionModel().getSelectedItem());
			}
		}

		@FXML
		void mudancaDeLocacaoKey(KeyEvent event) {
			if(this.tableLocacao.getItems().size()>0) {
				if(event.getCode()==KeyCode.UP) {
					this.tableLocacao.getSelectionModel().selectPrevious();;
					this.carregar(tableLocacao.getSelectionModel().getSelectedItem());
					this.tableLocacao.getSelectionModel().selectNext();
				}
				else if(event.getCode()==KeyCode.DOWN) {
					this.tableLocacao.getSelectionModel().selectNext();
					this.carregar(tableLocacao.getSelectionModel().getSelectedItem());
					this.tableLocacao.getSelectionModel().selectPrevious();
				}
			}
		}

		@FXML
		void mudancaDeMotoristaClick(MouseEvent event) {

		}

		@FXML
		void mudancaTipoDeLocacao(ActionEvent event) {

		}
		@FXML
		void mudarFilialKey(KeyEvent event) {

		}

		@FXML
		void mudarMotoristalKey(KeyEvent event) {

		}

		@FXML
		void mudarVeiculoClick(MouseEvent event) {

		}

		@FXML
		void mudarVeiculoKey(KeyEvent event) {

		}

		@FXML
		void salvarLocacao(ActionEvent event) {

		}
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			MascaraFX.cpfCnpjField(cpfCnpjField);
			this.codFilialColumn.setCellValueFactory(
					new PropertyValueFactory<>("id"));
			this.nomeFilialColumn.setCellValueFactory(
					new PropertyValueFactory<>("nome"));
			this.codVeiculoColumn.setCellValueFactory(
					new PropertyValueFactory<>("id"));
			this.nomeVeiculoColumn.setCellValueFactory(
					new PropertyValueFactory<>("nome"));
			this.situacaoVeiculoColumn.setCellValueFactory(
					new PropertyValueFactory<>("status"));
			this.codMotoristaColumn.setCellValueFactory(
					new PropertyValueFactory<>("id"));
			this.nomeMotoristaColumn.setCellValueFactory(
					new PropertyValueFactory<>("nome"));
			this.cpfMotoristaColumn.setCellValueFactory(
					new PropertyValueFactory<>("cpf"));
			this.codClienteColumn.setCellValueFactory(
					new PropertyValueFactory<>("id"));
			this.situacaoClienteColumn.setCellValueFactory(
					new PropertyValueFactory<>("situacao"));
			this.nomeClienteColumn.setCellValueFactory(
					new PropertyValueFactory<>("nome"));
			this.codColumn.setCellValueFactory(
					new PropertyValueFactory<>("id"));
			this.situacaoColumn.setCellValueFactory(
					new PropertyValueFactory<>("situacao"));
			ToggleGroup group = new ToggleGroup();
			this.kmControle.setToggleGroup(group);
			this.kmLivre.setToggleGroup(group);
			this.kmLivre.setSelected(true);

		}
		//Locacao<>
		private static ObservableList<Locacao> listaDeLocacoes(List<Locacao> l ) {
			ObservableList<Locacao>lo =  FXCollections.observableArrayList(l);
			return lo;
		}
		void atualizarTabelaLocacao(List<Locacao>flist) {
			ObservableList<Locacao>list = this.listaDeLocacoes(flist);
			tableLocacao.setItems(list);
			tableLocacao.getSelectionModel().select(list.get(0));//Se chegou até aqui é pq encontrou resultados!
			this.carregar(list.get(0));
			//this.preencherAreaCategoria(list.get(0));
		}
		//Locacao</>
		public void carregar(Locacao p) {
			LimparCampo.limparCamposFXTOTAL(this.locacaoPane.getChildren());
			preencherBusca(p);
		}
		private void preencherBusca(Locacao p) {	
			//data.setDate(p.getDataRetirada());
			//this.fpl.getHoraRealizacaoField().setText(TratadorDeMascara.converterHoraString(p.getDataRetirada()));
			this.dataEntrega.setValue(TratadorDeMascara.dateToLocalDate(p.getDataEntrega()));
			this.horaEntrega.setValue(TratadorDeMascara.dateToLocalTime(p.getDataEntrega()));
			if(p.isKmLivre())
				this.kmLivre.setSelected(true);
			else
				this.kmControle.setSelected(true);
			this.nomeFilialRetirada.setText(p.getFilialLocaataria().getNome());
			List<Filial> lf = new ArrayList();
			lf.add(p.getFilialEntrega());
			List<Veiculo> lc = new ArrayList();
			lc.add(p.getVeiculo());
			List<Pessoa> lp = new ArrayList();
			lp.add(p.getPessoa());
			List<PessoaFisica> lpf = new ArrayList();
			lpf.add(p.getMotorista());
			this.atualizarTabelaFilial(lf);
			this.atualizarTabelaVeiculo(lc);
			this.atualizarTabelaCliente(lp);
			this.atualizarTabelaMotorista(lpf);
			this.valorDiaria.setText(TratadorDeMascara.valorReais(p.getValorDiaria()));
			this.entradaField.setText(TratadorDeMascara.valorReais(p.getValorDiaria()/2));
			this.valorVeiculoField.setText(TratadorDeMascara.valorReais(p.getVeiculo().getCategoria().getValor()));
			this.precoLocacaoField.setText(TratadorDeMascara.valorReais(p.getValorDiaria()-p.getVeiculo().getCategoria().getValor()));
			//this.fpl.getFilialLocatariaCombo().setSelectedItem(p.getFilialLocaataria().getNome());
			//this.fpl.getFilialEntregaCombo().setSelectedItem(p.getFilialEntrega().getNome());
			//this.fpl.getVeiculoCombo().setSelectedItem(p.getVeiculo().getNome());
			//this.fpl.getMotoristaCombo().setSelectedItem(p.getMotorista().getNome());
			//this.fpl.getClienteCombo().setSelectedItem(p.getPessoa().getNome());					
			//this.fpl.getIdField().setText(p.getId()+"");
		}
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
		//Filial<>
		private static ObservableList<Filial> listaDeFiliais(List<Filial> l ) {
			ObservableList<Filial>lo =  FXCollections.observableArrayList(l);
			return lo;
		}
		private void atualizarTabelaFilial(List<Filial>flist) {
			ObservableList<Filial>list = this.listaDeFiliais(flist);
			tableFiilial.setItems(list);
			tableFiilial.getSelectionModel().select(list.get(0));
		}
		//Filial</>
		//Motorista<>
		private static ObservableList<PessoaFisica> listaDeMotoristas(List<PessoaFisica> l ) {
			ObservableList<PessoaFisica>lo =  FXCollections.observableArrayList(l);
			return lo;
		}
		private void atualizarTabelaMotorista(List<PessoaFisica>flist) {
			ObservableList<PessoaFisica>list = this.listaDeMotoristas(flist);
			tableMotorista.setItems(list);
			tableMotorista.getSelectionModel().select(list.get(0));
		}
		//Motorista</>
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
