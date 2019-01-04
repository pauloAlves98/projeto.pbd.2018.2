package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.FuncionarioDao;
import br.com.palves.pbd.view.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ControllerFXEditarFuncionario implements Initializable {
	@FXML
	private BorderPane cadastroPessoaFisicaPanel;

	@FXML
	private TextField nomeField;

	@FXML
	private TextField loginField;

	@FXML
	private TextField cpfField;

	@FXML
	private PasswordField senhaField;

	@FXML
	private RadioButton fRadio;

	@FXML
	private RadioButton mRadio;

	@FXML
	private TextField salarioField;

	@FXML
	private JFXButton salvarButton;

	@FXML
	private JFXComboBox<String> cargoBox;

	@FXML
	private TableView<Filial> tableFilial;

	@FXML
	private TableColumn<Filial, Integer> codColumn;

	@FXML
	private TableColumn<Filial, String> nomeColumn;

	@FXML
	private TextArea detalhesArea;

	@FXML
	private TextField buscarField;

	@FXML
	private Button buscarButton;

	@FXML
	void buscarFilial(ActionEvent event) {
		try {
			if(this.buscarField.getText().replace(" ","").length()<=0) {
				List<Filial> l = FilialDao.getInstance().buscarPorParametro("%"+""+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaFilial(l);
			}else {
				List<Filial> l = FilialDao.getInstance().buscarPorParametro("%"+this.buscarField.getText().toLowerCase()+"%");
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
	void mudancaDeFilialClick(MouseEvent event) {
		if(this.tableFilial.getItems().size()>0) {
			this.preencherAreaFilial(tableFilial.getSelectionModel().getSelectedItem());
		}
	}
	@FXML
	void mudarFilialKey(KeyEvent event) {
		if(this.tableFilial.getItems().size()>0) {
			if(event.getCode()==KeyCode.UP) {
				tableFilial.getSelectionModel().selectPrevious();;
				this.preencherAreaFilial(tableFilial.getSelectionModel().getSelectedItem());
				tableFilial.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				tableFilial.getSelectionModel().selectNext();
				this.preencherAreaFilial(tableFilial.getSelectionModel().getSelectedItem());
				tableFilial.getSelectionModel().selectPrevious();
			}
		}
	}

	@FXML
	void salvar(ActionEvent event) {
		FuncionarioDao daoFunc = FuncionarioDao.getInstance();
		try {
			if(event.getSource()==this.salvarButton) {
				this.validacoes();
				Funcionario func = Corrente.funcionario;
				this.preencherCampos(func);
				func = daoFunc.persistOrMerge(func);
				Corrente.funcionario = func;
				ControllerFXMenuFuncionario.atualizarNome(Corrente.funcionario.getNome());
				Alerta.mostrarAlertaInformacao("Funcionario Editado com sucesso!");
				limparCampos();
				this.preencherBusca(func);
			}
		}
		catch (DaoException e1) {
			Alerta.mostrarAlertaErro(e1.getMessage());
			e1.printStackTrace();
		}catch(ValidacaoException e3) {
			Alerta.mostrarAlertaErro(e3.getMessage());
			e3.printStackTrace();
		}catch(java.lang.NumberFormatException e4) {
			Alerta.mostrarAlertaErro(e4.getMessage());
			e4.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MascaraFX.monetaryField(salarioField);
		MascaraFX.cpfField(this.cpfField);
		MascaraFX.maxField(this.senhaField,11);
		ToggleGroup group = new ToggleGroup();
		fRadio.setToggleGroup(group);
		mRadio.setToggleGroup(group);
		mRadio.setSelected(true);
		this.codColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.nomeColumn.setCellValueFactory(
				new PropertyValueFactory<>("nome"));
		this.cargoBox.getItems().addAll("ATENDENTE","ADMINISTRADOR","GERENTE");
		cargoBox.getSelectionModel().select("ATENDENTE");
	}
	//Editar<>
	private void validacoes() throws ValidacaoException {
		if(this.senhaField.getText().trim().length()<6 || this.senhaField.getText().length()>11 )
			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(this.nomeField.getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		if(this.loginField.getText().length()<=0)
			throw new ValidacaoException("O Login não pode ser nulo!");
		if(this.cpfField.getText().replace(" ","").trim().length()<=3) {
			throw new ValidacaoException("O CPF não pode ser nulo!");
		}
		if(this.tableFilial.getItems().size()<=0) {
			throw new ValidacaoException("É nescessario uma Filial!");
		}
	}
	private void preencherCampos(Funcionario func) throws NumberFormatException{
		String  nome = this.nomeField.getText();
		String senha = EncriptaDecriptaApacheCodec.codificaBase64Encoder(this.senhaField.getText());
		String login = this.loginField.getText();
		//String idField = this.fpf.getIdField().getText();
		String cpf = this.cpfField.getText();
		String  sexo = this.fRadio.isSelected()?this.fRadio.getText():this.mRadio.getText();
		String cargo = this.cargoBox.getSelectionModel().getSelectedItem();
		float salario = Float.parseFloat(this.salarioField.getText().length()<=0?"0":this.salarioField.getText().trim().replace(".","").replace(",","."));

		func.setNome(nome);
		func.setSenha(senha);
		func.setLogin(login);
		func.setCpf(cpf);
		func.setSexo(sexo);
		func.setCargo(cargo);
		func.setSalario(salario);
		Filial f = this.tableFilial.getSelectionModel().getSelectedItem();
		//f.setId(Integer.parseInt(this.fpf.getCodFilialField().getText()));
		func.setFilial(f);
		func.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
	}
	//Editar</>
	//Filial<>
	private void atualizarTabelaFilial(List<Filial>flist) {
		ObservableList<Filial>list = this.listaDeFiliais(flist);
		tableFilial.setItems(list);
		tableFilial.getSelectionModel().select(list.get(0));
		this.preencherAreaFilial(list.get(0));
	}
	private void preencherAreaFilial(Filial f) {
		this.detalhesArea.setText(f.toStringArea());
	}
	private static ObservableList<Filial> listaDeFiliais(List<Filial> l ) {
		ObservableList<Filial>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	//Filial</>
	//Funcionario<>
	private static ObservableList<Funcionario> listaDeFuncionarios(List<Funcionario> l ) {
		ObservableList<Funcionario>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void preencherBusca(Funcionario p) {	
		this.nomeField.setText(p.getNome());
		this.senhaField.setText(EncriptaDecriptaApacheCodec.decodificaBase64Decoder(p.getSenha()));
		this.loginField.setText(p.getLogin());
		//this.fpf.getIdField().setText(p.getId()+"");
		//this.fpf.getCpfField().setText(pessoaF.getCpf());
		this.cpfField.setText(p.getCpf().replace(".","").replace("-",""));
		if(p.getSexo().equalsIgnoreCase("F"))this.fRadio.setSelected(true);
		else this.mRadio.setSelected(true);
		this.cargoBox.getSelectionModel().select(p.getCargo()); 
		this.salarioField.setText(p.getSalario()+"0");
		List<Filial> lf = new ArrayList();
		lf.add(p.getFilial());
		List<Categoria> lc = new ArrayList();
		this.atualizarTabelaFilial(lf);	
	}
	public void carregarFuncionario(Funcionario f) {
		LimparCampo.limparCamposFX(this.cadastroPessoaFisicaPanel.getChildren());
		this.preencherBusca(f);
	}
	//Funcionario</>
	//Other<>
	private void limparCampos() {
		LimparCampo.limparCamposFX(this.cadastroPessoaFisicaPanel.getChildren());
		this.tableFilial.setItems(null);

		//this.tableFuncionario.setItems(null);
	}
	//Other</>

}
