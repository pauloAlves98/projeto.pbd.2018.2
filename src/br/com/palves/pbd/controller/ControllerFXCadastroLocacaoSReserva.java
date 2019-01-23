package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.bin.Veiculo;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.ConfiguracaoDao;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.LocacaoDao;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.model.dao.VeiculoDao;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ControllerFXCadastroLocacaoSReserva implements Initializable{
	@FXML
	private BorderPane borderGeral;

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
	private Button buscarClienteButton;

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
	void buscarCliente(ActionEvent event) {
		try {
			if(this.cpfCnpjField.getText().replace(" ", "").length()<=14) {
				Pessoa l =null;
				l = PessoaFisicaDao.getInstance().buscarPorCpf(this.cpfCnpjField.getText());
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else {
					List p = new ArrayList();
					p.add(l);
					this.atualizarTabelaCliente(p);
				}
			}else {
				Pessoa l = null;
				l = PessoaJuridicaDao.getInstance().buscarPorCnpj(this.cpfCnpjField.getText());
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
		limparValoresLocacao();
	}
	@FXML
	void buscarFilial(ActionEvent event) {
		try {
			if(this.filialEntregaField.getText().replace(" ","").length()<=0) {
				List<Filial> l = FilialDao.getInstance().buscarPorParametro("%"+this.filialEntregaField.getText().toLowerCase()+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaFilial(l);
			}else {
				List<Filial> l = FilialDao.getInstance().buscarPorParametro("%"+this.filialEntregaField.getText().toLowerCase()+"%");
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
	void buscarMotorista(ActionEvent event) {
		try {
			if(this.buscarMotoristaField.getText().replace(" ","").length()<=0) {
				List<PessoaFisica> l = PessoaFisicaDao.getInstance().buscarPorParametro("%"+this.buscarMotoristaField.getText().toLowerCase()+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaMotorista(l);
			}else {
				List<PessoaFisica> l = PessoaFisicaDao.getInstance().buscarPorParametro("%"+this.buscarMotoristaField.getText().toLowerCase()+"%");
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
	void buscarVeiculo(ActionEvent event) {
		try {
			List<Veiculo> l =null;
			l = VeiculoDao.getInstance().buscarPorFiltro("%"+this.buscaVeiculoField.getText()+"%");
			if(l==null)
				Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
			else
				this.atualizarTabelaVeiculo(l);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limparValoresLocacao();
	}

	@FXML
	void cadastrarMotorista(ActionEvent event) {
		AlertaDetalhes.getBorder().getChildren().clear();
		//AlertaDetalhes.getBorder().setTop(App.getFechamentoDialog());
		AlertaDetalhes.getBorder().setCenter(App.getCadastroClienteFisicoPane());
		AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getCadastroClienteFisicoPane().getPrefWidth(),App.getCadastroClienteFisicoPane().getPrefHeight()-50);
		//ControllerFXCadastroClientePF cr = (ControllerFXCadastroClientePF ) Carregar.getBuscarClientePFLoader().getController();
		//List<PessoaFisica>lr = new ArrayList<>();
		//lr.add(this.tableMotorista.getSelectionModel().getSelectedItem());
		//cr.atualizarTabelaPessoa(lr);
		Carregar.detalhes = true;
		//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
		AlertaDetalhes.getInstance().showAndWait();
	}
	@FXML
	void calcValoresLocacao(ActionEvent event) {
		if(this.tableVeiculo.getItems()!=null && this.tableVeiculo.getItems().size()!=0) {
			this.valorVeiculoField.setText(TratadorDeMascara.valorReais(this.tableVeiculo.getSelectionModel().getSelectedItem().getCategoria().getValor()));
			double p = this.carregarConfiguracaoes();
			this.precoLocacaoField.setText(TratadorDeMascara.valorReais(p));
			this.valorDiaria.setText(TratadorDeMascara.valorReais(this.tableVeiculo.getSelectionModel().getSelectedItem().getCategoria().getValor()+p));
			this.entradaField.setText(TratadorDeMascara.valorReais((this.tableVeiculo.getSelectionModel().getSelectedItem().getCategoria().getValor()+p)/2));
		}else {
			Alerta.mostrarAlertaErro("Veículo não Válido!!!");
			return;
		}

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
	void mudancaDeMotoristaClick(MouseEvent event) {

	}

	@FXML
	void mudancaTipoDeLocacao(ActionEvent event) {
		limparValoresLocacao();
	}

	@FXML
	void mudarFilialKey(KeyEvent event) {

	}

	@FXML
	void mudarMotoristalKey(KeyEvent event) {

	}

	@FXML
	void mudarVeiculoClick(MouseEvent event) {
		limparValoresLocacao();
	}

	@FXML
	void mudarVeiculoKey(KeyEvent event) {
		limparValoresLocacao();
	}

	@FXML
	void salvarLocacao(ActionEvent event) {
		LocacaoDao dao = LocacaoDao.getInstance();
		try {
			this.validacoesDeNull();
			Locacao locacao = new Locacao();
			this.preencherCampos(locacao);
			dao.persistOrMerge(locacao);
			Alerta.mostrarAlertaInformacao("Locação cadastrada com sucesso!");
			//this.efetivarReserva();
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
		ToggleGroup group = new ToggleGroup();
		this.kmControle.setToggleGroup(group);
		this.kmLivre.setToggleGroup(group);
		this.kmLivre.setSelected(true);
	}
	//Salvar<>
	private void preencherCampos(Locacao locacao) {
		Date dataHoraRealizacao = new Date();
		//String idField = this.fpl.getIdField().getText();
		Date dataEntrega = TratadorDeMascara.unirDataHora(TratadorDeMascara.localDatetoDate(this.dataEntrega.getValue()),
				TratadorDeMascara.localTimetoString(this.horaEntrega.getValue()));
		boolean kmlivre = this.kmLivre.isSelected();
		//			int idLocataria = Integer.parseInt(this.fpl.getIdFilialLocatariaField().getText());
		//			int idEntrega = Integer.parseInt(this.fpl.getFilialEntregaIdField().getText());
		//			int idVeiculo = Integer.parseInt(this.fpl.getVeiculoIdField().getText());
		//			int idMotorista = Integer.parseInt(this.fpl.getIdMotoristaField().getText());
		//			int idCliente = Integer.parseInt(this.fpl.getClienteField().getText());
		//			if(idField.trim().length()>0) {//então eh update
		//				locacao.setId(Integer.parseInt(idField));
		//			}    
		Filial fl = Corrente.funcionario.getFilial();
		//fl.setId(idLocataria);
		Filial fe = this.tableFiilial.getSelectionModel().getSelectedItem();
		//fe.setId(idEntrega);
		Veiculo v = this.tableVeiculo.getSelectionModel().getSelectedItem();
		//v.setId(idVeiculo);
		PessoaFisica pf = this.tableMotorista.getSelectionModel().getSelectedItem();
		//pf.setId(idMotorista);
		Pessoa cliente = this.tableCliente.getSelectionModel().getSelectedItem();
		//cliente.setId(idCliente);
		locacao.setDataRetirada(dataHoraRealizacao);
		locacao.setKmAtual(v.getKmAtual());
		locacao.setDataEntrega(dataEntrega);
		locacao.setKmLivre(kmlivre);
		locacao.setFilialEntrega(fe);
		locacao.setFilialLocaataria(fl);
		v.setStatus(StatusEnum.LOCADO.getValor());//Gatilho pra isso!!!
		locacao.setVeiculo(v);
		locacao.setMotorista(pf);
		locacao.setPessoa(cliente);
		locacao.setFuncionario(Corrente.funcionario);
		locacao.setValorDiaria(this.tableVeiculo.getSelectionModel().getSelectedItem().getCategoria().getValor()+this.carregarConfiguracaoes());
		locacao.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
	}
	private void validacoesDeNull() throws ValidacaoException {
		if(this.tableFiilial.getItems().size()<=0)
			throw new ValidacaoException("Escolha uma filial para entrega do veículo!");
		if(this.horaEntrega.getValue()==null)
			throw new ValidacaoException("Horário de Entrega vazio!");
		if(this.dataEntrega.getValue()==null)
			throw new ValidacaoException("A data prevista para entrega não pode ser nula!");
		//Validação reserva
		if(this.tableCliente.getItems().size()<=0)
			throw new ValidacaoException("Escolha uma Cliente para realizar a locação!");
		else {
			if(!this.tableCliente.getSelectionModel().getSelectedItem().getSituacao().equalsIgnoreCase(StatusEnum.ATIVO.getValor()))
				throw new ValidacaoException("O Cliente Não é válido!!!");
		}
		if(TratadorDeMascara.unirDataHora(TratadorDeMascara.localDatetoDate(this.dataEntrega.getValue()),TratadorDeMascara.localTimetoString(this.horaEntrega.getValue())).getTime() < new Date().getTime())
			throw new ValidacaoException("Data de entrega inválida!!!");
		if(!this.tableVeiculo.getSelectionModel().getSelectedItem().getStatus().equalsIgnoreCase(StatusEnum.ATIVO.getValor()))
			throw new ValidacaoException("Veículo não disponivel no momento!!!");
		//Validação de Motorista!!!
		if(this.tableMotorista.getItems().size()<=0)
			throw new ValidacaoException("Escolha uma Motorista!");
		else
		{
			PessoaFisica p = tableMotorista.getSelectionModel().getSelectedItem();
			Calendar c = Calendar.getInstance();
			c.setTime(p.getDataNascimento());
			if(p.getnHabilitacao()==null)
				throw new ValidacaoException("O Motorista não possui CNH!!!");
			if(c.get(Calendar.YEAR)>1998)
				throw new ValidacaoException("O Motorista deve ter mais de 21 anos!!!");
			if(p.getDataVencHabilitacao()==null)
				throw new ValidacaoException("Data de vencimento habilitação nula!!!");
			else {
				System.out.println(TratadorDeMascara.unirDataHora(p.getDataVencHabilitacao(),"00:00").getTime());
				System.out.println(TratadorDeMascara.unirDataHora(TratadorDeMascara.localDatetoDate(this.dataEntrega.getValue()),"00:00").getTime());
				if(TratadorDeMascara.unirDataHora(p.getDataVencHabilitacao(),"00:00").getTime()<= TratadorDeMascara.unirDataHora(TratadorDeMascara.localDatetoDate(this.dataEntrega.getValue()),"00:00").getTime()) 
					throw new ValidacaoException("A CNH não pode se vencer dentro do prazo da locação!!!");
			}
			//Validacao hora filial
			FilialDao f = FilialDao.getInstance();
			try {
				Filial fl = f.findById(Filial.class,this.tableFiilial.getSelectionModel().getSelectedItem().getId());
				String horaInicio = TratadorDeMascara.converterHoraString(fl.getHoraInicioExpediente()).replace(":","");
				String horaFim = TratadorDeMascara.converterHoraString(fl.getHoraFimExpediente()).replace(":","");
				int hi = Integer.parseInt(horaInicio);
				int hf = Integer.parseInt(horaFim);
				int ha = Integer.parseInt(TratadorDeMascara.converterHoraString(TratadorDeMascara.localTimeToDate(this.horaEntrega.getValue())).replace(":",""));
				if(ha>= hi && ha<=hf)
					System.out.println();
				else
					throw new ValidacaoException("A hora de entrega tem que estar dentro do expediente da filial!!!");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ValidacaoException("Filial invalida!");
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ValidacaoException("Filial Não existe no banco!");
			}
		}
	}
	//Salvar</>
	//Reserva<>
	private boolean validarPorCPF(String cpf) {
		try {
			return (boolean)PessoaFisicaDao.getInstance().procedureValidaPorCPF(cpf);
		} catch (DaoException e) {
			Alerta.mostrarAlertaErro("Nehuma Cliente Encontrado!!!");
			e.printStackTrace();
		}
		return false;
	}
	private boolean validarPorCNPJ(String cnpj) {
		try {
			return (boolean)PessoaJuridicaDao.getInstance().procedureValidaPorCNPJ(cnpj);
		} catch (DaoException e) {
			Alerta.mostrarAlertaErro("Nehum Cliente Encontrado!!!");
			e.printStackTrace();
		}
		return false;
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
	//Others<>
	void limparValoresLocacao() {
		this.valorDiaria.setText("");
		this.valorVeiculoField.setText("");
		this.entradaField.setText("");
		this.precoLocacaoField.setText("");
	}
	private void limparCampos() {
		LimparCampo.limparCamposFXTOTAL(locacaoPane.getChildren());
		//
	}
	public  double  carregarConfiguracaoes() {
		ConfiguracaoDao c =  ConfiguracaoDao.getInstance();
		Configuracao cf;
		try {
			cf = c.buscarUltimo();
			if(cf==null) {
				return 0;
			}
			return this.kmLivre.isSelected()?cf.getDiariaKlivre():cf.getDiariaKcontrole();
		} catch (DaoException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public Label getNomeFilialRetirada() {
		return nomeFilialRetirada;
	}
	//Otehrs</>
}
