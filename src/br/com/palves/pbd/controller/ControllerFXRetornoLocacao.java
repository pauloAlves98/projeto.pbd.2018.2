package br.com.palves.pbd.controller;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.Veiculo;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.MascaraFX;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.ConfiguracaoDao;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.LocacaoDao;
import br.com.palves.pbd.model.dao.VeiculoDao;
import br.com.palves.pbd.view.Alerta;
import br.com.palves.pbd.view.AlertaDetalhes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ControllerFXRetornoLocacao implements Initializable{
	@FXML
	private AnchorPane retornoPane;

	@FXML
	private JFXButton salvarButton;
	@FXML
	private TextArea detalhesArea;
	@FXML
	private TableView<Locacao> tableLoc;
	@FXML
	private TableColumn<Locacao, Integer> codColumn;

	@FXML
	private TableColumn<Locacao,Date> dataRealizacaoColumn;

	@FXML
	private TableColumn<Locacao,Date> dataEntregColumn;
	@FXML
	private TableColumn<Locacao, String> situacaoColumn;

	@FXML
	private JFXButton buscarButton;

	@FXML
	private TextField filtroField;

	@FXML
	private TextField modalidadeLocacaoField;

	@FXML
	private TextField valorVeiculoField;

	@FXML
	private TextField valorDiaria;

	@FXML
	private JFXRadioButton simHigieneRadio;

	@FXML
	private JFXRadioButton naoHigieneRadio;

	@FXML
	private JFXRadioButton simCombustivelRadio;

	@FXML
	private JFXRadioButton naoCombustivelRadio;

	@FXML
	private TextField precoFinalField;
	@FXML
	private TextField kmVeiculoField;
	@FXML
	private JFXButton calcPrecoButton;

	@FXML
	private JFXButton infoButton;

	@FXML
	private JFXDatePicker dataInicial;

	@FXML
	private JFXDatePicker dataFinal;

	@FXML
	void BuscarPorFiltro(ActionEvent event) {
		try {
			if(this.filtroField.getText().replace(" ","").length()>0 && this.dataInicial.getValue()==null && this.dataFinal.getValue()==null) {
				String txt = "%"+filtroField.getText().toLowerCase()+"%";
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro(txt,
						TratadorDeMascara.coletorDeData("10/12/1998"),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.coletorDeData("10/12/3000"),"23:59"));	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataInicial.getValue()==null && this.dataFinal.getValue()==null) {//Nehum
				String txt = "%"+" "+"%";
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro(txt,
						TratadorDeMascara.coletorDeData("10/12/1998"),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.coletorDeData("10/12/3000"),"23:59"));	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataInicial.getValue()==null && this.dataFinal.getValue()!=null) {//Só por data final
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+""+"%",
						TratadorDeMascara.localDatetoDate(this.dataFinal.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinal.getValue()),"23:59")
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataFinal.getValue()==null && this.dataInicial.getValue()!=null) {//Só por data inicial
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+"a"+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicial.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataInicial.getValue()),"23:59")
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()>0 && this.dataFinal.getValue()==null && this.dataInicial.getValue()!=null) {//Por filtro e data Inicial
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicial.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataInicial.getValue()),"23:59")
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()>0 && this.dataInicial.getValue()==null && this.dataFinal.getValue()!=null) {//por filtro e dat Final
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%",
						TratadorDeMascara.localDatetoDate(this.dataFinal.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinal.getValue()),"23:59")
						);	
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else if(this.filtroField.getText().replace(" ","").length()<=0 && this.dataInicial.getValue()!=null && this.dataFinal.getValue()!=null) {//Só por data
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+""+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicial.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinal.getValue()),"23:59")
						);	
				System.out.println(this.dataInicial.getValue().toString());
				System.out.println(this.dataFinal.getValue().toString());
				System.out.println(TratadorDeMascara.unirDataHora(
						TratadorDeMascara.localDatetoDate(this.dataFinal.getValue()),"23:59").toString());
				System.out.println("Iiii");
				if(l==null)
					Alerta.mostrarAlertaErro("Nehum resultado Encontrado!!!");
				else
					this.atualizarTabelaLocacao(l);
			}
			else {
				List<Locacao> l = LocacaoDao.getInstance().buscarPorFiltro("%"+this.filtroField.getText().toLowerCase()+"%",
						TratadorDeMascara.localDatetoDate(this.dataInicial.getValue()),TratadorDeMascara.unirDataHora(
								TratadorDeMascara.localDatetoDate(this.dataFinal.getValue()),"23:59")
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
		this.limparValores();
	}
	@FXML
	void mudancaDeLocacaoClick(MouseEvent event) {
		if(this.tableLoc.getItems().size()>0) {
			this.limparValores();
			this.valoresLoc();
			//this.preencherAreaFilial(tableFiilial.getSelectionModel().getSelectedItem());
		}
	}
	@FXML
	void mudancaDeLocacaoKey(KeyEvent event) {
		if(this.tableLoc.getItems().size()>0) {
			this.limparValores();
			if(event.getCode()==KeyCode.UP) {
				this.tableLoc.getSelectionModel().selectPrevious();;
				this.valoresLoc();
				this.tableLoc.getSelectionModel().selectNext();
			}
			else if(event.getCode()==KeyCode.DOWN) {
				this.tableLoc.getSelectionModel().selectNext();
				this.valoresLoc();
				this.tableLoc.getSelectionModel().selectPrevious();
			}
		}
	}
	@FXML
	void calcPreco(ActionEvent event) {
		//this.limparValores();
		if(this.tableLoc.getItems().size()>0) {
			Locacao l = this.tableLoc.getSelectionModel().getSelectedItem();
			Configuracao cf = this.carregarConfiguracaoes();
			if(l.getSituacao().replace(" ","").equalsIgnoreCase(StatusEnum.ATIVO.getValor())) {
				if(l.isKmLivre()) {
					Date dataRetirada = l.getDataRetirada();
					Date dataAgr = new Date();//TratadorDeMascara.unirDataHora(TratadorDeMascara.converterStringData("23/01/2019"),"14:00");
					System.out.println(dataAgr.toString());
					Date dataPrevistaE = l.getDataEntrega();
					if(dataAgr.getTime() < dataPrevistaE.getTime()){//Entrega antes : só cobra os dias usados sem taxa de devolução!
						LocalTime horaagr = TratadorDeMascara.dateToLocalTime(dataAgr);
						long dt = (dataAgr.getTime() - dataRetirada.getTime()) + 3600000; // 1 hora para compensar horário de verão
						long quantDias = Math.abs((dt / 86400000L));//
						if(quantDias<=0) {//No mesmo dia!
							System.out.println("ENTROU EM ANTES DO PRAZO POREM EM MESMO DIA KM LIVRE");
							this.valorDiaria.setText(TratadorDeMascara.valorReais(l.getValorDiaria()));
							this.modalidadeLocacaoField.setText(TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor()));
							this.valorVeiculoField.setText(TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor()));
							this.kmVeiculoField.setText("");
							double vld = l.getValorDiaria();//Paga só a diária!!!
							double valorFinal = vld + (this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);
							this.precoFinalField.setText(TratadorDeMascara.valorReais(valorFinal));
							this.detalhesArea.setText(
									"M Locação: "+TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor())+""
											+ "\nVeículo: "+TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor())+ "\nDiária: "+TratadorDeMascara.valorReais(l.getValorDiaria())+"\n"+ 
											"Dias da Locacao:"+quantDias+"\nHoras extras: 0");
						}else{//Entregou antes do prazo mais em outro dia!!!
							System.out.println("ENTROU EM ANTES DO PRAZO POREM EM OUTRO DIA KM LIVRE");
							this.valorDiaria.setText(TratadorDeMascara.valorReais(l.getValorDiaria()));
							this.modalidadeLocacaoField.setText(TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor()));
							this.valorVeiculoField.setText(TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor()));
							this.kmVeiculoField.setText("");
							quantDias = quantDias==0?1:quantDias;
							double vld = l.getValorDiaria()*(quantDias);
							double valorFinal = vld + (this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);
							this.precoFinalField.setText(TratadorDeMascara.valorReais(valorFinal));
							this.detalhesArea.setText(
									"M Locação: "+TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor())+"\n"
											+ "Veículo: "+TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor())
											+ "\nDiária: "+TratadorDeMascara.valorReais(l.getValorDiaria())+ 
											"\nDias da Locacao:"+quantDias+"\nHoras extras:"+0);
						}
					}else if(dataAgr.getTime()==dataPrevistaE.getTime() || (dataAgr.getTime()>=dataPrevistaE.getTime() && dataAgr.getTime()<= dataPrevistaE.getTime()+3600000)){//Entregou na hora com tolerancia de 1 hora minutos!
						System.out.println("Igual ou com Tolerância de uma hora");
						LocalTime horaagr = TratadorDeMascara.dateToLocalTime(dataAgr);
						long dt = (dataAgr.getTime() - dataRetirada.getTime()) + 3600000; // 1 hora para compensar horário de verão
						long quantDias = Math.abs((dt / 86400000L));//
						quantDias = quantDias==0?1:quantDias;
						this.valorDiaria.setText(TratadorDeMascara.valorReais(l.getValorDiaria()));
						this.modalidadeLocacaoField.setText(TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor()));
						this.valorVeiculoField.setText(TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor()));
						this.kmVeiculoField.setText("");
						double vld = l.getValorDiaria()*(quantDias==0?1:quantDias);
						double valorFinal = vld + (this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);
						this.precoFinalField.setText(TratadorDeMascara.valorReais(valorFinal));
						this.detalhesArea.setText(
								"M Locação: "+TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor())+"\n"
										+ "Veículo: "+TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor())
										+ "\nDiária: "+TratadorDeMascara.valorReais(l.getValorDiaria())+"\n"+ 
										"Dias da Locacao:"+quantDias+"\nHoras extras:"+0);
					}
					else if(dataAgr.getTime()> dataPrevistaE.getTime()+3600000){//Entregou depois da tolerancia//de uma hora
						System.out.println("Depois da Tolerância...!");
						LocalTime horaagr = TratadorDeMascara.dateToLocalTime(dataAgr);
						long dt = (dataAgr.getTime() - dataRetirada.getTime()) + 3600000; // 1 hora para compensar horário de verão
						long quantDias = Math.abs((dt / 86400000L));//
						quantDias = quantDias==0?1:quantDias;
						this.valorDiaria.setText(TratadorDeMascara.valorReais(l.getValorDiaria()));
						this.modalidadeLocacaoField.setText(TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor()));
						this.valorVeiculoField.setText(TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor()));
						this.kmVeiculoField.setText("");
						LocalTime horaPrevista = TratadorDeMascara.dateToLocalTime(l.getDataEntrega());//hora de realização
						LocalTime horaReal = TratadorDeMascara.dateToLocalTime(dataAgr);
						long minReal = horaReal.getMinute() + 1;
						long minPrev =  horaPrevista.getMinute()+1;
						long hora = (((horaReal.getHour()*60) +minReal+ 1 ) - ((horaPrevista.getHour()*60) + 1 + minPrev))/60;//Converte pra minutos depois pra o[hora denv
						double horasExtras = 0;
						System.out.println(horaReal.getHour()+" O1");
						System.out.println(horaPrevista.getHour()+" O2");
						System.out.println((horaReal.getHour()*60) +minReal+ 1 +" m1");
						System.out.println((horaPrevista.getHour()*60) + 1 + minPrev+" m2");
						if(hora<=4 && hora>=0) {
							System.out.println("Hora:"+hora);
							horasExtras = (double)((double)(hora*l.getValorDiaria())/4);
							System.out.println(horasExtras);
						}else {
							quantDias+=1;
						}
						double vld = l.getValorDiaria()*(quantDias==0?(1+quantDias):quantDias)+horasExtras;
						double valorFinal = vld + (this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);
						this.precoFinalField.setText(TratadorDeMascara.valorReais(valorFinal));
						this.detalhesArea.setText(
								"M Locação: "+TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor())+"\n"
										+ "Veículo: "+TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor())
										+ "\nDiária: "+TratadorDeMascara.valorReais(l.getValorDiaria())+ 
										"\nDias da Locacao:"+quantDias+"\nHoras extras:"+(hora<0?0:hora)+"\n"+(hora>4?"'Nova Diária'":"")+""+(hora<0?"Nova Diaria":""));
					}
				}else {//KM Retorno!!!
					System.out.println("KM Retorno");
					if(this.kmVeiculoField.getText().replace(" ", "").length()<=0) {
						Alerta.mostrarAlertaErro("Digite a KM do veiculo!!!");
						return;
					}
					this.valorDiaria.setText(TratadorDeMascara.valorReais(l.getValorDiaria()));
					this.modalidadeLocacaoField.setText(TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor()));
					this.valorVeiculoField.setText(TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor()));
					double vld = l.getValorDiaria()+(l.getValorDiaria()*cf.getPorcentagemKm()/100)*(Integer.parseInt(this.kmVeiculoField.getText().replace(" ","")) - l.getKmAtual());
					double valorFinal = vld + (this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);
					this.precoFinalField.setText(TratadorDeMascara.valorReais(valorFinal));
					this.detalhesArea.setText(
							"M Locação: "+TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor())+"\n"
									+ "Veículo: "+TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor())
									+ "\nKM Consumidos: "+(Integer.parseInt(this.kmVeiculoField.getText().replace(" ","")) - l.getKmAtual()));	
				}
			}else {
				Alerta.mostrarAlertaErro("Não é possivel Calcular valores para essa Locação!!!");
				return;
			}
		}else
		{
			Alerta.mostrarAlertaErro("Nenhuma Locação escolhida!!!");
			return;
		}
	}
	@FXML
	void mostrarDetalhesLocacao(ActionEvent event) {
		if(this.tableLoc!=null && this.tableLoc.getItems() !=null && this.tableLoc.getItems().size()>0) {
			AlertaDetalhes.getBorder().getChildren().clear();
			//AlertaDetalhes.getBorder().setTop(App.getFechamentoDialog());
			AlertaDetalhes.getBorder().setCenter(App.getBuscarLocacaoPane());
			AlertaDetalhes.getInstance().getDialogPane().setPrefSize(App.getBuscarLocacaoPane().getPrefWidth(),App.getBuscarLocacaoPane().getPrefHeight()-800);
			ControllerFXBuscarLocacao cr = (ControllerFXBuscarLocacao) Carregar.getBuscarLocacaoLoader().getController();
			List<Locacao>lr = new ArrayList<>();
			lr.add(this.tableLoc.getSelectionModel().getSelectedItem());
			cr.atualizarTabelaLocacao(lr);
			Carregar.detalhes = true;
			//cr.carregar(this.tableReserva.getSelectionModel().getSelectedItem());
			AlertaDetalhes.getInstance().showAndWait();
		}else
			Alerta.mostrarAlertaErro("Nenhuma Locação Selecionada!!!");
	}
	@FXML
	void salvar(ActionEvent event) {
		LocacaoDao dao = LocacaoDao.getInstance();
		VeiculoDao vDao = VeiculoDao.getInstance();
		try {
			this.validacoesDeNull();
			//Fazer gatilhos e procedures no salvamento!------
			Locacao locacao = this.tableLoc.getSelectionModel().getSelectedItem();
			this.preencherCampos(locacao);
			vDao.persistOrMerge(locacao.getVeiculo());//Atualiza o Veiculo
			dao.persistOrMerge(locacao);
			Alerta.mostrarAlertaInformacao("Locação Finalizada com sucesso!");
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
		MascaraFX.numericField(this.kmVeiculoField);
		this.codColumn.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		this.dataRealizacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("dataRetirada"));
		this.dataEntregColumn.setCellValueFactory(
				new PropertyValueFactory<>("dataEntrega"));
		this.situacaoColumn.setCellValueFactory(
				new PropertyValueFactory<>("situacao"));
		ToggleGroup group1 = new ToggleGroup();
		ToggleGroup group2 = new ToggleGroup();

		this.simCombustivelRadio.setToggleGroup(group1);
		this.naoCombustivelRadio.setToggleGroup(group1);
		this.naoCombustivelRadio.setSelected(true);
		this.simHigieneRadio.setToggleGroup(group2);
		this.naoHigieneRadio.setToggleGroup(group2);
		this.naoHigieneRadio.setSelected(true);
	}
	//Salvar<>
	private void preencherCampos(Locacao l) {
		Configuracao cf = this.carregarConfiguracaoes();
		Date dataAgr = null;
		double vld = 0;
		double valorFinal = 0;
		if(l.isKmLivre()) {
			Date dataRetirada = l.getDataRetirada();
		    dataAgr = new Date();//TratadorDeMascara.unirDataHora(TratadorDeMascara.converterStringData("23/01/2019"),"14:00");
			System.out.println(dataAgr.toString());
			Date dataPrevistaE = l.getDataEntrega();
			if(dataAgr.getTime() < dataPrevistaE.getTime()){//Entrega antes : só cobra os dias usados sem taxa de devolução!
				LocalTime horaagr = TratadorDeMascara.dateToLocalTime(dataAgr);
				long dt = (dataAgr.getTime() - dataRetirada.getTime()) + 3600000; // 1 hora para compensar horário de verão
				long quantDias = Math.abs((dt / 86400000L));//
				if(quantDias<=0) {//No mesmo dia!
					System.out.println("ENTROU EM ANTES DO PRAZO POREM EM MESMO DIA KM LIVRE");
					vld = l.getValorDiaria();//Paga só a diária!!!
					valorFinal = vld + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);
				}else{//Entregou antes do prazo mais em outro dia!!!
					System.out.println("ENTROU EM ANTES DO PRAZO POREM EM OUTRO DIA KM LIVRE");
					quantDias = quantDias==0?1:quantDias;
					vld = l.getValorDiaria()*(quantDias);
					valorFinal = vld + (this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);
				}
			}else if(dataAgr.getTime()==dataPrevistaE.getTime() || (dataAgr.getTime()>=dataPrevistaE.getTime() && dataAgr.getTime()<= dataPrevistaE.getTime()+3600000)){//Entregou na hora com tolerancia de 1 hora minutos!
				System.out.println("Igual ou com Tolerância de uma hora");
				LocalTime horaagr = TratadorDeMascara.dateToLocalTime(dataAgr);
				long dt = (dataAgr.getTime() - dataRetirada.getTime()) + 3600000; // 1 hora para compensar horário de verão
				long quantDias = Math.abs((dt / 86400000L));//
				quantDias = quantDias==0?1:quantDias;
				vld = l.getValorDiaria()*(quantDias==0?1:quantDias);
				valorFinal = vld + (this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);
			}
			else if(dataAgr.getTime()> dataPrevistaE.getTime()+3600000){//Entregou depois da tolerancia//de uma hora
				System.out.println("Depois da Tolerância...!");
				LocalTime horaagr = TratadorDeMascara.dateToLocalTime(dataAgr);
				long dt = (dataAgr.getTime() - dataRetirada.getTime()) + 3600000; // 1 hora para compensar horário de verão
				long quantDias = Math.abs((dt / 86400000L));//
				quantDias = quantDias==0?1:quantDias;
				LocalTime horaPrevista = TratadorDeMascara.dateToLocalTime(l.getDataEntrega());//hora de realização
				LocalTime horaReal = TratadorDeMascara.dateToLocalTime(dataAgr);
				long minReal = horaReal.getMinute() + 1;
				long minPrev =  horaPrevista.getMinute()+1;
				long hora = (((horaReal.getHour()*60) +minReal+ 1 ) - ((horaPrevista.getHour()*60) + 1 + minPrev))/60;//Converte pra minutos depois pra o[hora denv
				double horasExtras = 0;
				if(hora<=4 && hora>=0) {
					System.out.println("Hora:"+hora);
					horasExtras = (double)((double)(hora*l.getValorDiaria())/4);
					System.out.println(horasExtras);
				}else {
					quantDias+=1;
				}
				vld = l.getValorDiaria()*(quantDias==0?(1+quantDias):quantDias)+horasExtras;
				valorFinal = vld + (this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);
			}
		}else {//KM Retorno!!!
			vld = l.getValorDiaria()+(l.getValorDiaria()*cf.getPorcentagemKm()/100)*(Integer.parseInt(this.kmVeiculoField.getText().replace(" ","")) - l.getKmAtual());
			valorFinal = vld + (this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0) + (this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0);	
		}
		l.setPrecoFinal(valorFinal);
		l.setTaxaHigiene((this.simHigieneRadio.isSelected()?(vld*(cf.getTaxaHigiene()/100)):0));
		l.setTaxaDevolucao(this.simCombustivelRadio.isSelected()?(vld*(cf.getTaxaCombustivel()/100)):0);
		l.setKmRetorno(Integer.parseInt(this.kmVeiculoField.getText().replace(" ", "")));
		//Olhar no gatilho se essa kmTragem utrapassou!!!(kmParaRev + (KM Retorno - KM Antiga)
		l.getVeiculo().setKmRestanteRevisao(l.getVeiculo().getKmRestanteRevisao()+Integer.parseInt(this.kmVeiculoField.getText().replace(" ", ""))-l.getVeiculo().getKmAtual());
		l.getVeiculo().setKmAtual(Integer.parseInt(this.kmVeiculoField.getText().replace(" ", "")));
		l.getVeiculo().setFilialAtual(Corrente.funcionario.getFilial());
		l.setDataRealEntrega(dataAgr);
		l.setSituacao(StatusEnum.FINALIZADA.getValor());
		l.setUltimoModificador(Corrente.funcionario.getNome());
	}
	private void validacoesDeNull() throws ValidacaoException {
		if(this.tableLoc==null || this.tableLoc.getItems() ==null || this.tableLoc.getItems().size()<=0)
			throw new ValidacaoException("Escolha uma Locação!");
		if(!this.tableLoc.getSelectionModel().getSelectedItem().getSituacao().replace(" ","").equalsIgnoreCase(StatusEnum.ATIVO.getValor())) {
			System.out.println(this.tableLoc.getSelectionModel().getSelectedItem().getSituacao());
			throw new ValidacaoException("Locação não Válida!!!");
		}
		if(this.tableLoc.getSelectionModel().getSelectedItem().getFilialEntrega().getId()!=Corrente.funcionario.getFilial().getId())
			throw new ValidacaoException("O veículo não pode ser devolvido nesta filial!!!");
		if(this.kmVeiculoField.getText().replace(" ", "").length()<=0)
			throw new ValidacaoException("Digite a KM atual do Veículo!!!");
	}
	//Salvar</>
	//Locacao<>
	private static ObservableList<Locacao> listaDeLocacoes(List<Locacao> l ) {
		ObservableList<Locacao>lo =  FXCollections.observableArrayList(l);
		return lo;
	}
	private void atualizarTabelaLocacao(List<Locacao>flist) {
		ObservableList<Locacao>list = this.listaDeLocacoes(flist);
		tableLoc.setItems(list);
		tableLoc.getSelectionModel().select(list.get(0));//Se chegou até aqui é pq encontrou resultados!
		this.valoresLoc();
		//this.carregar(list.get(0));
		//this.preencherAreaCategoria(list.get(0));
	}
	//Locacao</>
	//Other<>
	private void limparCampos() {
		LimparCampo.limparCamposFXTOTAL(retornoPane.getChildren());
		//
	}
	private void limparValores() {
		this.valorDiaria.setText("");
		this.modalidadeLocacaoField.setText("");
		this.valorVeiculoField.setText("");
		this.precoFinalField.setText("");
		this.kmVeiculoField.setText("");
		this.detalhesArea.setText("");
	}
	private void valoresLoc() {
		if(this.tableLoc.getSelectionModel().getSelectedItem().getSituacao().equals(StatusEnum.ATIVO.getValor())) {
			Locacao l = this.tableLoc.getSelectionModel().getSelectedItem();
			this.valorDiaria.setText(TratadorDeMascara.valorReais(l.getValorDiaria()));
			this.modalidadeLocacaoField.setText(TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor()));
			this.valorVeiculoField.setText(TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor()));
			//this.precoFinalVeiculo.setText("");
			//this.kmVeiculoField.setText("");
			//this.detalhesArea.setText("");
		}else if(this.tableLoc.getSelectionModel().getSelectedItem().getSituacao().equals(StatusEnum.CANCELADA.getValor())) {
			Locacao l = this.tableLoc.getSelectionModel().getSelectedItem();
			this.valorDiaria.setText(TratadorDeMascara.valorReais(l.getValorDiaria()));
			this.modalidadeLocacaoField.setText(TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor()));
			this.valorVeiculoField.setText(TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor()));
		}
		else {//Finalizada
			Locacao l = this.tableLoc.getSelectionModel().getSelectedItem();
			this.valorDiaria.setText(TratadorDeMascara.valorReais(l.getValorDiaria()));
			this.modalidadeLocacaoField.setText(TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor()));
			this.valorVeiculoField.setText(TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor()));
			this.kmVeiculoField.setText(l.getKmRetorno()+"");
			this.precoFinalField.setText(TratadorDeMascara.valorReais(l.getPrecoFinal()));
			String kl = null,kc=null;
			if(l.isKmLivre()) {
				Date dataRetirada = l.getDataRetirada();
				Date agr = l.getDataRealEntrega();
				LocalTime ll = TratadorDeMascara.dateToLocalTime(agr);
				//ll.getNano()
				long dt = (agr.getTime() - dataRetirada.getTime()) + 3600000; // 1 hora para compensar horário de verão
				long quantDias = Math.abs((dt / 86400000L));//
				quantDias = quantDias==0?1:quantDias;
				LocalTime horaPrevista = TratadorDeMascara.dateToLocalTime(l.getDataEntrega());//hora de realização
				LocalTime horaReal = TratadorDeMascara.dateToLocalTime(agr);
				long minReal = horaReal .getMinute() + 1;
				long minPrev =  horaPrevista.getMinute()+1;
				long hora = (((horaReal.getHour()*60) +minReal+ 1 ) - ((horaPrevista.getHour()*60) + 1 + minPrev))/60;
				hora =  hora<0?0:hora;//se for menor é porque entrgou antes!!!
				kl="Dias da Locacao:"+quantDias+" H:"+hora;
			}else {
				long km = l.getKmRetorno() - l.getKmAtual();
				kc = "KM Rodados: "+km;	
			}
			this.detalhesArea.setText(
					"M Locação: "+TratadorDeMascara.valorReais(l.getValorDiaria() - l.getVeiculo().getCategoria().getValor())+"\n"
							+ "Veículo: "+TratadorDeMascara.valorReais(l.getVeiculo().getCategoria().getValor())
							+ "\nDiária: "+TratadorDeMascara.valorReais(l.getValorDiaria())+"\n"+"TaxaHigiene: "+TratadorDeMascara.valorReais(l.getTaxaHigiene())+"\n"+"TaxaCombustivel: "+TratadorDeMascara.valorReais(l.getTaxaDevolucao())+"\n"+
							(l.isKmLivre()==true?kl:kc));
		}
	}
	public Configuracao  carregarConfiguracaoes() {
		ConfiguracaoDao c =  ConfiguracaoDao.getInstance();
		Configuracao cf;
		try {
			cf = c.buscarUltimo();
			if(cf==null) {
				return new Configuracao();
			}
			return cf;
		} catch (DaoException e) {
			e.printStackTrace();
			return null;
		}
	}
	//Other</>
}
