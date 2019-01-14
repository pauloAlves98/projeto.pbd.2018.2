package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Reserva;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.CategoriaDao;
import br.com.palves.pbd.model.dao.ConfiguracaoDao;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.ReservaDao;
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

public class ControllerFXBuscarReserva implements Initializable{
	@FXML
	private BorderPane borderGeral;

	@FXML
	private Button buscarFiltroButton;

	@FXML
	private JFXDatePicker dataInicialPesquisa;

	@FXML
	private JFXDatePicker dataFinalPesquisa;

	@FXML
	private TextField filtroField;

	@FXML
	private TableView<Reserva> tableReserva;

	@FXML
	private TableColumn<Reserva,Integer> codColumn;

	@FXML
	private TableColumn<Reserva,String > situacaoColumn;

	//@FXML
	//private TableColumn<Reserva, Date> dataRetiradaColumn;

	@FXML
	private BorderPane cadastroReservaPane;

	@FXML
	private TextField kmControllerField;

	@FXML
	private TextField kmLivreField;

	@FXML
	private JFXButton salvarButton;

	@FXML
	private JFXDatePicker dataDeRetirada;

	@FXML
	private JFXTimePicker horaRetirada;

	@FXML
	private TableView<Filial> tableFiilial;

	@FXML
	private TableColumn<Filial,Integer> codFilialColumn;

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
	private TableColumn<Categoria, String> nomeCategoriaColumn;

	@FXML
	private TextArea detalhesCategoriaArea;

	//@FXML
	private TextField dataRealizacaoField;

	//@FXML
	private TextField horaRealizacaoField;

	//@FXML
	private TextField situacaoField;

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
				List<Filial> l = FilialDao.getInstance().buscarPorParametro("%"+this.filialRetiradaField.getText().toLowerCase()+"%");
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
	void cancelarReserva(ActionEvent event) {
		ReservaDao daoPJ = ReservaDao.getInstance();
		//		System.out.println(this.tableFiilial.getSelectionModel().getSelectedItem().getId());
		//		System.out.println(this.tableFiilial.getItems().size());
		//		System.out.println(this.tableCategoria.getItems().size());
		if(tableReserva.getItems().size()<0) {
			Alerta.mostrarAlertaErro("Nehuma reserva Selecionada!");
			return ;
		}else if(!tableReserva.getSelectionModel().getSelectedItem().getSituacao().equalsIgnoreCase(StatusEnum.EM_ESPERA.getValor())){
			Alerta.mostrarAlertaErro("Ah Reserva Não pode ser cancelada!!!");
			return;
		}
		else if(Corrente.usuarioFisico == null && Corrente.usuarioJuridico == null) {
			Alerta.mostrarAlertaErro("É preciso que o cliente esteja Logado  para executar esta operação!");
		}else {
			try {
				//this.validacoesDeNull();
				//Reserva pessoaJ = new Reserva();
				//this.preencherCampos(pessoaJ);
				Alert alert = new Alert(AlertType.WARNING,"", ButtonType.YES, ButtonType.NO);  //new alert object
			    //alert.setTitle("Warning!");  //warning box title
			    alert.setHeaderText("WARNING!!!");// Header
			    alert.setContentText("Deseja Realmente Cancelar a reserva???"); //Discription of warning
			    alert.getDialogPane().setPrefSize(500, 100); //sets size of alert box 
			    Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.YES) {
					Reserva r = tableReserva.getSelectionModel().getSelectedItem();
					r.setSituacao(StatusEnum.CANCELADA.getValor());
					daoPJ.persistOrMerge(r);
					Alerta.mostrarAlertaInformacao("Reserva Cancelada com sucesso!");
					this.limparCampos();
				}else
					System.out.println("Não cancelou!!!");
			} 
			catch (DaoException e1) {
				Alerta.mostrarAlertaErro(e1.getMessage());
				e1.printStackTrace();
			}catch(java.lang.NumberFormatException e4) {
				e4.printStackTrace();
			}
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
	void clickEvent(MouseEvent event) {//Tabela de reserva!
		if(this.tableReserva.getItems().size()>0) {
			this.carregar(tableReserva.getSelectionModel().getSelectedItem());
			//O metodo de caaregar//this.preencherAreaCategoria(this.tableCategoria.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	void keyPEvent(KeyEvent event) {
		if(tableReserva.getItems().size()>0) {//Só que pra reserva
			if(event.getCode()==KeyCode.UP) {
				this.tableReserva.getSelectionModel().selectPrevious();;
				this.carregar(tableReserva.getSelectionModel().getSelectedItem());
				this.tableReserva.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				this.tableReserva.getSelectionModel().selectNext();
				this.carregar(tableReserva.getSelectionModel().getSelectedItem());
				this.tableReserva.getSelectionModel().selectPrevious();;
			}
		}
	}
	@FXML
	void buscarPorFiltro(ActionEvent event) {
		int id = Corrente.usuarioFisico!=null?Corrente.usuarioFisico.getId():Corrente.usuarioJuridico.getId();
		try {
			if(this.filtroField.getText().replace(" ","").length()>0 && this.dataInicialPesquisa.getValue()==null && this.dataFinalPesquisa.getValue()==null) {
				String txt = "%"+filtroField.getText().toLowerCase()+"%";
				List<Reserva> l = ReservaDao.getInstance().buscarPorFiltro(txt,
						TratadorDeMascara.coletorDeData("10/12/1998"),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.coletorDeData("10/12/3000"),"23:59"),id);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaReserva(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataInicialPesquisa.getValue()==null && this.dataFinalPesquisa.getValue()==null) {
				String txt = "%"+"a"+"%";
				List<Reserva> l = ReservaDao.getInstance().buscarPorFiltro(txt,
						TratadorDeMascara.coletorDeData("10/12/1998"),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.coletorDeData("10/12/3000"),"23:59"),id);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaReserva(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataInicialPesquisa.getValue()==null) {
				List<Reserva> l = ReservaDao.getInstance().buscarPorFiltro("%"+"a"+"%",
						TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),"23:59"),id
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaReserva(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataFinalPesquisa.getValue()==null) {
				List<Reserva> l = ReservaDao.getInstance().buscarPorFiltro("%"+"a"+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),"23:59"),id
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaReserva(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()>0 && this.dataFinalPesquisa.getValue()==null) {
				List<Reserva> l = ReservaDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),"23:59"),id
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaReserva(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()>0 && this.dataInicialPesquisa.getValue()==null) {
				List<Reserva> l = ReservaDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%",
						TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),"23:59"),id
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaReserva(l);
			}
			else {
				List<Reserva> l = ReservaDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicialPesquisa.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinalPesquisa.getValue()),"23:59"),id
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaReserva(l);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//MascaraFX.monetaryField(kmControllerField);
		this.codFilialColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeFilialColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.codCategoriaColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeCategoriaColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.situacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		//		this.dataRetiradaColumn.setCellValueFactory(
		//				new PropertyValueFactory<>("dataRetirada"));
		this.codColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		//		//this.tableFiilial.even
		//		//		ObservableList<Filial>l = this.listaDeClientes();
		//		//		tableFiilial.setItems(l);
		//		//		tableFiilial.getSelectionModel().select(l.get(1));
	}


	//Relacionado ao salvamento
	private void validacoesDeNull() throws ValidacaoException {
		if(this.dataDeRetirada.getValue()==null)
			throw new ValidacaoException("A Data não pode ser nula!");
		if(this.horaRetirada.getValue()==null)
			throw new ValidacaoException("Hora Inválida!");
		if(this.tableFiilial.getItems().size()<=0)
			throw new ValidacaoException("É necessario uma filial");
		if(this.tableCategoria.getItems().size()<=0)
			throw new ValidacaoException("É nescessario uma Categoria");
		FilialDao f = FilialDao.getInstance();
		try {
			Filial fl = f.findById(Filial.class,this.tableFiilial.getSelectionModel().getSelectedItem().getId());
			String horaInicio = TratadorDeMascara.converterHoraString(fl.getHoraInicioExpediente()).replace(":","");
			String horaFim = TratadorDeMascara.converterHoraString(fl.getHoraFimExpediente()).replace(":","");
			int hi = Integer.parseInt(horaInicio);
			int hf = Integer.parseInt(horaFim);
			int ha = Integer.parseInt(TratadorDeMascara.converterHoraString(TratadorDeMascara.localTimeToDate(this.horaRetirada.getValue())).replace(":",""));
			if(ha>= hi && ha<=hf)
				System.out.println();
			else
				throw new ValidacaoException("A hora de retirada tem que estar dentro do expediente!");

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
	//Relacionado ao preenchimento dos campos!
	private static ObservableList<Filial> listaDeFiliais(List<Filial> l ) {
		ObservableList<Filial>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private static ObservableList<Categoria> listaDeCategorias(List<Categoria> l ) {
		ObservableList<Categoria>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void preencherAreaFilial(Filial f) {
		this.detalhesFilialArea.setText(f.toStringArea());
	}
	private void preencherAreaCategoria(Categoria f) {
		this.detalhesCategoriaArea.setText(f.toString());
	}
	private void atualizarTabelaFilial(List<Filial>flist) {
		ObservableList<Filial>list = this.listaDeFiliais(flist);
		tableFiilial.setItems(list);
		tableFiilial.getSelectionModel().select(list.get(0));
		this.preencherAreaFilial(list.get(0));
	}
	private void atualizarTabelaCategoria(List<Categoria>flist) {
		ObservableList<Categoria>list = this.listaDeCategorias(flist);
		tableCategoria.setItems(list);
		tableCategoria.getSelectionModel().select(list.get(0));//Se chegou até aqui é pq encontrou resultados!
		this.preencherAreaCategoria(list.get(0));
	}
	private void atualizarTabelaReserva(List<Reserva>list) {
		if(list==null)
			return;
		ObservableList<Reserva>lists = FXCollections.observableArrayList(list);
		tableReserva.setItems(lists);
		tableReserva.getSelectionModel().select(lists.get(0));
		this.carregar(lists.get(0));
	}
	private void limparCampos() {
		LimparCampo.limparCamposFX(this.borderGeral.getChildren());
		this.tableReserva.setItems(null);
		this.tableCategoria.setItems(null);
		this.tableFiilial.setItems(null);
		//this.atualizarFilialeCategoria();
	}
	public  void carregarConfiguracaoes() {
		ConfiguracaoDao c =  ConfiguracaoDao.getInstance();
		Configuracao cf;
		try {
			cf = c.buscarUltimo();
			if(cf==null) {
				this.kmControllerField.setText("0");
				this.kmLivreField.setText("0");
				return;
			}
			System.out.println(" ");
			kmControllerField.setText(TratadorDeMascara.valorReais(cf.getDiariaKcontrole())+"");
			kmLivreField.setText(TratadorDeMascara.valorReais(cf.getDiariaKlivre()));
			kmControllerField.setText(TratadorDeMascara.valorReais(cf.getDiariaKcontrole())+"");
			kmLivreField.setText(TratadorDeMascara.valorReais(cf.getDiariaKlivre()));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Carregar
	private void carregar(Reserva p) {
		LimparCampo.limparCamposFX(this.cadastroReservaPane.getChildren());
		preencherBusca(p);
	}
	private void preencherBusca(Reserva p) {	
		this.dataDeRetirada.setValue(TratadorDeMascara.dateToLocalDate(p.getDataHoraRetirada()));
		this.horaRetirada.setValue(TratadorDeMascara.dateToLocalTime(p.getDataHoraRetirada()));
		//this.fpv.getSituacaoCombo().setSelectedItem(p.getSituacao());
		this.kmControllerField.setText(TratadorDeMascara.valorReais(p.getValorDiariaKcontrole())+"");
		this.kmLivreField.setText(TratadorDeMascara.valorReais(p.getValorDiariaKlivre())+"");
		//this.fpv.getClienteField().setText(p.getPessoa()!=null?p.getPessoa().getNome():"");
		//this.fpv.getIdField().setText(p.getId()+"");
		//this.fpv.getFilialAtualCombo().setSelectedItem(p.getFilial().getNome());
		List<Filial> lf = new ArrayList();
		lf.add(p.getFilial());
		List<Categoria> lc = new ArrayList();
		lc.add(p.getCategoria());
		this.atualizarTabelaFilial(lf);
		this.atualizarTabelaCategoria(lc);
		//this.fpv.getCategoriaCombo().setSelectedItem(p.getCategoria().getNome());
	}
}
