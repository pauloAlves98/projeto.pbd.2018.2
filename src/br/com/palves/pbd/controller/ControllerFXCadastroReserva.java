package br.com.palves.pbd.controller;
//Evento ao trocar de linha selecinada, mudar cor da tabela e Area, Query com ILike
import java.net.URL;
import java.util.Date;
import java.util.List;
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
import br.com.palves.pbd.model.complemento.MascaraFX;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ControllerFXCadastroReserva implements Initializable {
	@FXML
	private BorderPane borderGeral;

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

	@FXML
	void buscarCategoria(ActionEvent event) {
		try {
			if(this.buscaCategoriaField.getText().replace(" ","").length()<=0) {
				this.atualizarTabelaCategoria(CategoriaDao.getInstance().findAll(Categoria.class));
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
				this.atualizarTabelaFilial(FilialDao.getInstance().findAll(Filial.class));
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
	void salvarReserva(ActionEvent event) {
		//		System.out.println(this.tableFiilial.getSelectionModel().getSelectedItem().getId());
		//		System.out.println(this.tableFiilial.getItems().size());
		//		System.out.println(this.tableCategoria.getItems().size());
		ReservaDao daoPJ = ReservaDao.getInstance();
		if(Corrente.usuarioFisico == null && Corrente.usuarioJuridico == null) {
			Alerta.mostrarAlertaErro("É preciso que o cliente esteja Logado  para executar esta operação!");
		}else {
			try {
				this.validacoesDeNull();
				Reserva pessoaJ = new Reserva();
				this.preencherCampos(pessoaJ);
				daoPJ.persistOrMerge(pessoaJ);
				Alerta.mostrarAlertaInformacao("Reserva realizada com sucesso!");
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
		//this.tableFiilial.even
		//		ObservableList<Filial>l = this.listaDeClientes();
		//		tableFiilial.setItems(l);
		//		tableFiilial.getSelectionModel().select(l.get(1));
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
	private void preencherCampos(Reserva reserva) throws DaoException {
		try {
			//String idField = this.fpv.getIdField().getText();
			Date horaRetirada = TratadorDeMascara.unirDataHora(TratadorDeMascara.localDatetoDate(this.dataDeRetirada.getValue()),TratadorDeMascara.localTimetoString(this.horaRetirada.getValue()));
			//String situacao = this.fpv.getSituacaoCombo().getSelectedItem().toString();

			double valorKlivre = Double.parseDouble(this.kmLivreField.getText().replace("R$","").replace(".","").replace(",","."));
			System.out.println(valorKlivre);
			double valorkControle = Double.parseDouble(this.kmControllerField.getText().replace("R$","").replace(".","").replace(",","."));
			int  idFilial = this.tableFiilial.getSelectionModel().getSelectedItem().getId();
			int idCategoria = this.tableCategoria.getSelectionModel().getSelectedItem().getId();
			Date realizacao = new Date();
			//			if(idField.trim().length()>0) {//então eh update
			//				ReservaDao r = ReservaDao.getInstance();
			//				Reserva rs = r.findById(Reserva.class,Integer.parseInt(idField));
			//				realizacao = rs.getDataHoraReserva();
			//				Pessoa p = new PessoaFisica();
			//				valorKlivre = rs.getValorDiariaKlivre();
			//				valorkControle = rs.getValorDiariaKcontrole();
			//				//rs = null;
			//				reserva.setPessoa(rs.getPessoa());
			//				reserva.setId(Integer.parseInt(idField));
			//				//reserva.setDataHoraReserva(rs.getDataHoraReserva());
			//			}else {
			//Pessoa p = new PessoaFisica();
			if(Corrente.usuarioFisico!=null)
				reserva.setPessoa(Corrente.usuarioFisico);
			else if(Corrente.usuarioJuridico!=null) {
				reserva.setPessoa(Corrente.usuarioJuridico);
			}else
				reserva.setPessoa(null);
			//}
			Filial f = new Filial();
			f.setId(idFilial);
			Categoria cat = new Categoria();
			cat.setId(idCategoria);
			reserva.setFilial(f);
			reserva.setCategoria(cat);
			reserva.setDataHoraRetirada(horaRetirada);
			reserva.setDataHoraReserva(realizacao);
			reserva.setValorDiariaKcontrole(valorkControle);
			reserva.setValorDiariaKlivre(valorKlivre);
			reserva.setSituacao(StatusEnum.EM_ESPERA.getValor());
		}catch(java.lang.NumberFormatException r) {
			throw new DaoException("Existem Caracteres Inválidos!");
		}
	}
	//Relacionado ao preenchimento dos campos!
	public  void atualizarFilialeCategoria() 
	{
		
		FilialDao fd = FilialDao.getInstance();
		CategoriaDao cd = CategoriaDao.getInstance();
		carregarConfiguracaoes();
		try {
			List<Filial>filiais = fd.findAll(Filial.class);
			List<Categoria>categorias =  cd.findAll(Categoria.class);
			if(filiais!=null)
				this.atualizarTabelaFilial(filiais);
			if(categorias!=null)
				this.atualizarTabelaCategoria(categorias);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	private void limparCampos() {
		LimparCampo.limparCamposFX(cadastroReservaPane.getChildren());
		
		this.atualizarFilialeCategoria();
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
}
