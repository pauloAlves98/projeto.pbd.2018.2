package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Endereco;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ControllerFXBuscarClientePJ implements Initializable{
	@FXML
	private BorderPane cadastroPessoaJuridicaPanel;

	@FXML
	private TextField filtroField;

	@FXML
	private Button buscarFiltroButton;

	@FXML
	private TableView<PessoaJuridica> tablePessoa;

	@FXML
	private TableColumn<PessoaJuridica, Integer> codPJColumn;

	@FXML
	private TableColumn<PessoaJuridica,String> nomePJColumn;

	@FXML
	private TableColumn<PessoaJuridica, String> situacaoPJColumn;

	@FXML
	private TextField nomeField;

	@FXML
	private TextField loginField;

	@FXML
	private TextField cnpjField;

	@FXML
	private PasswordField senhaField;

	@FXML
	private TextField inscricaoEstadualField;

	@FXML
	private TextField ruaField;

	@FXML
	private TextField bairroField;

	@FXML
	private TextField numeroField;

	@FXML
	private TextField cepField;

	@FXML
	private TextField cidadeField;

	@FXML
	private TextField ufField;

	@FXML
	private JFXButton salvarButton;

	@FXML
	private JFXButton excluirButton;

	@FXML
	void buscarClientePJ(ActionEvent event) {
		try {
			if(this.filtroField.getText().replace(" ","").length()<=0) {
				List<PessoaJuridica> l = PessoaJuridicaDao.getInstance().buscarPorFiltro("%"+""+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaPessoa(l);
			}else {

				List<PessoaJuridica> l = PessoaJuridicaDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaPessoa(l);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void editar(ActionEvent event) {
		PessoaJuridicaDao daoPJ = PessoaJuridicaDao.getInstance();
		try {
			this.validacoes();
			if(event.getSource()==this.salvarButton) {
				PessoaJuridica pessoaJ = this.tablePessoa.getSelectionModel().getSelectedItem();
				this.preencherCampos(pessoaJ);
				pessoaJ = daoPJ.persistOrMerge(pessoaJ);
				Alerta a = Alerta.getInstance();
				a.setAlertType(AlertType.INFORMATION);
				a.setMensagem(pessoaJ.getNome()+" "+"Editado Com Sucesso!");
				a.show();
				//ControllerFXMenu.atualizarNome(Corrente.usuarioJuridico.getNome());
				this.limparCampos();
			}else {
				Alert alert = new Alert(AlertType.WARNING,"", ButtonType.YES, ButtonType.NO);  //new alert object
				//alert.setTitle("Warning!");  //warning box title
				alert.setHeaderText("WARNING!!!");// Header
				alert.setContentText("Deseja Realmente Inativar este Cliente???"); //Discription of warning
				alert.getDialogPane().setPrefSize(500, 100); //sets size of alert box 
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.YES) {
					PessoaJuridica p =this.tablePessoa.getSelectionModel().getSelectedItem();
					//this.validacoes();
					this.preencherCampos(p);
					p.setSituacao(StatusEnum.DESATIVADO.getValor());
					p = daoPJ.persistOrMerge(p);
					Alerta.mostrarAlertaInformacao("Cliente Inativado com sucesso!");
					this.limparCampos();
				}
			}
			//this.preencher();
		} 
		catch (DaoException e1) {
			Alerta a = Alerta.getInstance();
			a.setAlertType(AlertType.ERROR);
			a.setMensagem(e1.getMessage());
			a.show();
			e1.printStackTrace();
		}catch(ValidacaoException e3) {
			Alerta a = Alerta.getInstance();
			a.setAlertType(AlertType.ERROR);
			a.setMensagem(e3.getMessage());
			a.show();
			e3.printStackTrace();
		}catch(java.lang.NumberFormatException e4) {
			e4.printStackTrace();
		}
	}
	@FXML
	void eventoKPPessoa(KeyEvent event) {
		if(this.tablePessoa.getItems().size()>0) {
			if(event.getCode()==KeyCode.UP) {
				this.tablePessoa.getSelectionModel().selectPrevious();;
				this.carregarPJ(this.tablePessoa.getSelectionModel().getSelectedItem());
				this.tablePessoa.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				this.tablePessoa.getSelectionModel().selectNext();
				this.carregarPJ(this.tablePessoa.getSelectionModel().getSelectedItem());
				this.tablePessoa.getSelectionModel().selectPrevious();
			}
		}
	}

	@FXML
	void eventoMCPessoa(MouseEvent event) {
		if(this.tablePessoa.getItems().size()>0) {
			this.carregarPJ(this.tablePessoa.getSelectionModel().getSelectedItem());
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MascaraFX.cnpjField(cnpjField);
		MascaraFX.maxField(senhaField, 11);
		MascaraFX.maxField(inscricaoEstadualField, 10);
		MascaraFX.numericField(inscricaoEstadualField);
		MascaraFX.cepField(cepField);
		MascaraFX.numericField(numeroField);
		MascaraFX.maxField(ufField,2);
		MascaraFX.letrasField(ufField);
		this.codPJColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.nomePJColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		this.situacaoPJColumn.setCellValueFactory(new PropertyValueFactory<>("situacao"));
	}
	//Editar<>
	private void validacoes() throws ValidacaoException {
		if(this.tablePessoa.getItems().size()<=0) {
			throw new ValidacaoException("É nescessário um cliente para realizar esta operação!");
		}
		if(senhaField.getText().length()<6 || senhaField.getText().length()>11 )
			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(nomeField.getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		if(loginField.getText().length()<=0)
			throw new ValidacaoException("O Login não pode ser nulo!");
		if(cnpjField.getText().replace(" ","").trim().length()<=4) {
			throw new ValidacaoException("O CNPJ não pode ser nulo!");
		}
	}
	private void preencherCampos(PessoaJuridica pessoaJ) {
		String incEstadual = inscricaoEstadualField.getText().trim();
		String cnpj = cnpjField.getText();
		String  nome = nomeField.getText();
		String senha = EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaField.getText());
		String login = loginField.getText();
		//String idField = this.fpj.getIdField().getText();
		String rua = ruaField.getText();
		int numero = numeroField.getText().length()<=0?0:Integer.parseInt( numeroField.getText());
		String bairro = bairroField.getText();
		String cep =  cepField.getText();
		String uf = ufField.getText();
		String cidade = cidadeField.getText();
		//String buscarField = this.fpj.getBuscarField().getText();

		Endereco e = pessoaJ.getEndereco();
		//e.setId(Corrente.usuarioJuridico.getEndereco().getId());
		//pessoaJ.setId(Corrente.usuarioJuridico.getId()); //Importante
		e.setRua(rua);
		e.setNumero(numero);
		e.setCep(cep);
		e.setUf(uf);
		e.setCidade(cidade);
		e.setBairro(bairro);
		pessoaJ.setEndereco(e);
		pessoaJ.setNome(nome);
		pessoaJ.setSenha(senha);
		pessoaJ.setLogin(login);
		pessoaJ.setCnpj(cnpj);
		pessoaJ.setIncEstadual(incEstadual);
		pessoaJ.setDiscriminador(Discriminador.PJ.getValor());//Importante
		pessoaJ.setSituacao(StatusEnum.ATIVO.getValor());
		if(Corrente.usuarioFisico!=null) {
			pessoaJ.setUltimoModificador(Corrente.usuarioFisico.getNome());
		}else if(Corrente.usuarioJuridico!=null) {
			pessoaJ.setUltimoModificador(Corrente.usuarioJuridico.getNome());
		}else if(Corrente.funcionario!=null){
			pessoaJ.setUltimoModificador(Corrente.funcionario.getNome());
		}
	}
	//Editar</>
	//Pessoa Juridica<>
	public void atualizarTabelaPessoa(List<PessoaJuridica>flist) {
		ObservableList<PessoaJuridica>list = this.listaDePessoa(flist);
		this.tablePessoa.setItems(list);
		this.tablePessoa.getSelectionModel().select(list.get(0));
		this.carregarPJ(tablePessoa.getSelectionModel().getSelectedItem());
	}
	private static ObservableList<PessoaJuridica> listaDePessoa(List<PessoaJuridica> l ) {
		ObservableList<PessoaJuridica>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	public void preencher(PessoaJuridica p) {
		this.carregarPJ(p);
	}
	private void carregarPJ(PessoaJuridica p) {
		//limparCampos();
		//LimparCampo.limparCamposFX(this.cadastroPessoaJuridicaPanel.getChildren());
		preencherBusca(p);

	}
	private void preencherBusca(PessoaJuridica p) {	
		nomeField.setText(p.getNome());
		senhaField.setText(EncriptaDecriptaApacheCodec.decodificaBase64Decoder(p.getSenha()));
		loginField.setText(p.getLogin());
		//this.fpf.getCpfField().setText(pessoaF.getCpf());
		cnpjField.setText(p.getCnpj().replace(".","").replace("-",""));
		inscricaoEstadualField.setText(p.getIncEstadual());
		ruaField.setText(p.getEndereco().getRua());
		numeroField.setText(p.getEndereco().getNumero()!=null?p.getEndereco().getNumero()+"":"");
		bairroField.setText(p.getEndereco().getBairro());
		cepField.setText(p.getEndereco().getCep());
		ufField.setText(p.getEndereco().getUf());
		cidadeField.setText(p.getEndereco().getCidade());
	}
	//Pessoa Juridica</>
	//Other<>
	private void limparCampos() {
		LimparCampo.limparCamposFX(this.cadastroPessoaJuridicaPanel.getChildren());
		this.tablePessoa.setItems(null);
	}
	//Other</>

}
