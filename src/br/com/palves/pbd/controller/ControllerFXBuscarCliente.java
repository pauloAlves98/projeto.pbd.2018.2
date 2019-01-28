package br.com.palves.pbd.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Endereco;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.dao.FuncionarioDao;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ControllerFXBuscarCliente implements Initializable{

	@FXML
	private BorderPane cadastroPessoaFisicaPanel;

	@FXML
	private TextField filtroField;

	@FXML
	private Button buscarFiltroButton;

	@FXML
	private TableView<PessoaFisica> tablePessoa;

	@FXML
	private TableColumn<PessoaFisica, Integer> codPFColumn;

	@FXML
	private TableColumn<PessoaFisica, String> nomePFColumn;

	@FXML
	private TableColumn<PessoaFisica, String> situacaoPFColumn;

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
	private TextField nHabilitacaoField;

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
	private JFXDatePicker dataDeNascimento;

	@FXML
	private JFXDatePicker dataDeVencimentoHabilitacao;

	@FXML
	void buscarCliente(ActionEvent event) {
		if(Carregar.detalhes) {
			Alerta.mostrarAlertaInformacao("Não é possível realizar esta operação no momento!!!");
			return;
		}
		try {
			if(this.filtroField.getText().replace(" ","").length()<=0) {
				List<PessoaFisica> l = PessoaFisicaDao.getInstance().buscarPorFiltro("%"+""+"%");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaPessoa(l);
			}else {

				List<PessoaFisica> l = PessoaFisicaDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%");
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
		PessoaFisicaDao daoPF = PessoaFisicaDao.getInstance();
		if(Carregar.detalhes)
			return;
		try {
			if(event.getSource()==salvarButton) {
				this.validacoes();
				PessoaFisica pessoaF = this.tablePessoa.getSelectionModel().getSelectedItem();
				this.preencherCamposDaTelaParaEntidade(pessoaF);
				//Corrente.usuarioFisico = daoPF.persistOrMerge(pessoaF);
				daoPF.persistOrMerge(pessoaF);
				Alerta a = Alerta.getInstance();
				a.setAlertType(AlertType.INFORMATION);
				a.setMensagem(pessoaF.getNome()+" "+"Editado Com Sucesso!");
				a.show();
				//ControllerFXMenu.atualizarNome(Corrente.usuarioFisico.getNome());
				this.limparCampos();
			}else//entao veio do excluirButton!
			{
				Alert alert = new Alert(AlertType.WARNING,"", ButtonType.YES, ButtonType.NO);  //new alert object
				//alert.setTitle("Warning!");  //warning box title
				alert.setHeaderText("WARNING!!!");// Header
				alert.setContentText("Deseja Realmente Inativar este Cliente???"); //Discription of warning
				alert.getDialogPane().setPrefSize(500, 100); //sets size of alert box 
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.YES) {
					PessoaFisica p = this.tablePessoa.getSelectionModel().getSelectedItem();
					this.validacoes();
					this.preencherCamposDaTelaParaEntidade(p);
					p.setSituacao(StatusEnum.DESATIVADO.getValor());
					p = daoPF.persistOrMerge(p);
					Alerta.mostrarAlertaInformacao("Pessoa Inativada com sucesso!");
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
				this.carregarPF(this.tablePessoa.getSelectionModel().getSelectedItem());
				this.tablePessoa.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				this.tablePessoa.getSelectionModel().selectNext();
				this.carregarPF(this.tablePessoa.getSelectionModel().getSelectedItem());
				this.tablePessoa.getSelectionModel().selectPrevious();
			}
		}
	}

	@FXML
	void eventoMCPessoa(MouseEvent event) {
		if(this.tablePessoa.getItems().size()>0) {
			this.carregarPF(this.tablePessoa.getSelectionModel().getSelectedItem());
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ToggleGroup group = new ToggleGroup();
		fRadio.setToggleGroup(group);
		mRadio.setToggleGroup(group);
		mRadio.setSelected(true);
		MascaraFX.cpfField(cpfField);
		MascaraFX.maxField(nHabilitacaoField, 11);
		MascaraFX.numericField(nHabilitacaoField);
		MascaraFX.cepField(cepField);
		MascaraFX.numericField(numeroField);
		MascaraFX.maxField(ufField,2);
		MascaraFX.letrasField(ufField);
		this.codPFColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.nomePFColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		this.situacaoPFColumn.setCellValueFactory(new PropertyValueFactory<>("situacao"));
	}
	//Editar<>
	private void validacoes() throws ValidacaoException {
		if(tablePessoa.getItems().size()<=0) {
			throw new ValidacaoException("Nehum Cliente Selecionado!!!");
		}
		if(dataDeNascimento.getValue()== null)
			throw new ValidacaoException("Campo data de nascimento nulo!");
		if(nHabilitacaoField.getText().length()!=0)
			if(dataDeVencimentoHabilitacao.getValue()==null)
				throw new ValidacaoException("Campo data de vencimento habilitação nulo!");
		if(dataDeVencimentoHabilitacao.getValue()!=null) {
			if(nHabilitacaoField.getText().replace(" ", "").length()<=0)
				throw new ValidacaoException("Ja que possui data é nescessario informar o número da Habilitação!");
		}
		if(senhaField.getText().length()<6 || senhaField.getText().length()>11 )
			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(nomeField.getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		if(loginField.getText().length()<=0)
			throw new ValidacaoException("O Login não pode ser nulo!");
		if(cpfField.getText().replace(" ","").trim().length()<=3) {
			throw new ValidacaoException("O CPF não pode ser nulo!");
		}
	}
	private void preencherCamposDaTelaParaEntidade(PessoaFisica pessoaF) throws DaoException, ValidacaoException {
		String  nome = nomeField.getText();
		String senha = EncriptaDecriptaApacheCodec.codificaBase64Encoder(senhaField.getText());
		String login = loginField.getText();
		//String idField = this.fpf.getIdField().getText();
		String cpf = cpfField.getText();
		String  sexo = fRadio.isSelected()?fRadio.getText():mRadio.getText();
		LocalDate dataNascimentoFieldv = dataDeNascimento.getValue();
		String nHab = nHabilitacaoField.getText();
		LocalDate dataVencHabField = dataDeVencimentoHabilitacao.getValue();
		String rua = ruaField.getText();
		int numero = numeroField.getText().length()<=0?0:Integer.parseInt( numeroField.getText());
		String bairro = bairroField.getText();
		String cep = cepField.getText();
		String uf = ufField.getText();
		String cidade = cidadeField.getText();
		//String buscarField = this.fpf.getBuscarField().getText();
		Endereco e = pessoaF.getEndereco();
		e.setRua(rua);
		e.setNumero(numero);
		e.setCep(cep);
		e.setUf(uf);
		e.setCidade(cidade);
		e.setBairro(bairro);
		pessoaF.setEndereco(e);
		//pessoaF.setId(Corrente.usuarioFisico.getId());
		//pessoaF.getEndereco().setId(Corrente.usuarioFisico.getEndereco().getId());
		String nHabMult = pessoaF.getnHabilitacao();
		if(nHab!=null && nHab.replace(" ","").length()> 0  && !nHab.equalsIgnoreCase(nHabMult==null?"":nHabMult)) {//Eh pq Mudou .. olha se o numero de habilitação mudou
			verificaUnicidadeHab(nHab);
		}
		pessoaF.setNome(nome);
		pessoaF.setSenha(senha);
		pessoaF.setLogin(login);
		pessoaF.setCpf(cpf);
		pessoaF.setSexo(sexo);
		pessoaF.setnHabilitacao(nHab);
		pessoaF.setDiscriminador(Discriminador.PF.getValor());//Importante
		pessoaF.setDataNascimento(dataNascimentoFieldv==null?null:Date.from(dataNascimentoFieldv.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		pessoaF.setDataVencHabilitacao(dataVencHabField==null?null:Date.from(dataVencHabField.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		pessoaF.setSituacao(StatusEnum.ATIVO.getValor());
		if(Corrente.usuarioFisico!=null) {
			pessoaF.setUltimoModificador(Corrente.usuarioFisico.getNome());
		}else if(Corrente.usuarioJuridico!=null) {
			pessoaF.setUltimoModificador(Corrente.usuarioJuridico.getNome());
		}else if(Corrente.funcionario!=null){
			pessoaF.setUltimoModificador(Corrente.funcionario.getNome());
		}
	}
	
	//Editar</>
	//Pessoa<>
	private void verificaUnicidadeHab(String nHabilitacaoField) throws ValidacaoException, DaoException {
		PessoaFisicaDao pf = PessoaFisicaDao.getInstance();
		try {
			boolean b = (boolean)pf.procedureValidaHabilitacao(nHabilitacaoField);
			if(b==true)
				throw new ValidacaoException("O número de habilitação já Existe!");
		} 
		catch (ValidacaoException e2) {
			e2.printStackTrace();
			throw new ValidacaoException(e2.getMessage());
		}
		catch (DaoException e1) {
			e1.printStackTrace();
			throw new DaoException("Erro ao executar procedure");
		}
	}
	public void atualizarTabelaPessoa(List<PessoaFisica>flist) {
		ObservableList<PessoaFisica>list = this.listaDePessoa(flist);
		this.tablePessoa.setItems(list);
		this.tablePessoa.getSelectionModel().select(list.get(0));
		this.carregarPF(tablePessoa.getSelectionModel().getSelectedItem());
	}
	private static ObservableList<PessoaFisica> listaDePessoa(List<PessoaFisica> l ) {
		ObservableList<PessoaFisica>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	public void preencher(PessoaFisica p) {
		carregarPF(p);
	}
	private void carregarPF(PessoaFisica p) {
		//limparCampos();
		//LimparCampo.limparCamposFX(this.cadastroPessoaFisicaPanel.getChildren());
		preencheBuscaDaEntidadeParaTela(p);
	}
	private void preencheBuscaDaEntidadeParaTela(PessoaFisica pessoaF) {	
		nomeField.setText(pessoaF.getNome());
		senhaField.setText(EncriptaDecriptaApacheCodec.decodificaBase64Decoder(pessoaF.getSenha()));
		loginField.setText(pessoaF.getLogin());
		//this.fpf.getCpfField().setText(pessoaF.getCpf());
		cpfField.setText(pessoaF.getCpf().replace(".","").replace("-",""));
		//System.out.println(pessoaF.getDataNascimento());
		Date dn = pessoaF.getDataNascimento();
		Date dh = pessoaF.getDataVencHabilitacao();
		if(pessoaF.getSexo().equals("F"))fRadio.setSelected(true);else mRadio.setSelected(true);
		dataDeNascimento.setValue(new Date(dn.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		nHabilitacaoField.setText(pessoaF.getnHabilitacao());
		dataDeVencimentoHabilitacao.setValue(dh==null?null:new Date(dh.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		ruaField.setText(pessoaF.getEndereco().getRua());
		numeroField.setText(pessoaF.getEndereco().getNumero()!=null?pessoaF.getEndereco().getNumero()+"":"");
		bairroField.setText(pessoaF.getEndereco().getBairro());
		cepField.setText(pessoaF.getEndereco().getCep());
		ufField.setText(pessoaF.getEndereco().getUf());
		cidadeField.setText(pessoaF.getEndereco().getCidade());
	}
	//Pessoa</>
	//Other<>
	private void limparCampos() {
		LimparCampo.limparCamposFX(cadastroPessoaFisicaPanel.getChildren());
		this.tablePessoa.setItems(null);
		//
	}
	//Otehr</>
}
