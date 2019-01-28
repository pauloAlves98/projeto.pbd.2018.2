package br.com.palves.pbd.controller;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.backup.PostgresBackup;
import br.com.palves.pbd.model.bin.Reserva;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.DaoGenerico;
import br.com.palves.pbd.model.dao.ReservaDao;
import br.com.palves.pbd.model.dao.RevisaoDao;
import br.com.palves.pbd.view.Alerta;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class Carregar implements Initializable {
	public static boolean detalhes  = false,rodando = true,backup = false;//Para exibição das telas de detlhaes em locação e backup!
	//private DirectoryChooser fileChooser = new DirectoryChooser();
	private static Thread minhaThread;
	private static FXMLLoader editarPFLoader,
	editarPJLoader,
	cadastroReservaLoader,
	cadastroFuncionarioLoader,cadastroClientePFLoader,
	buscarFuncionarioLoader,
	buscarClientePFLoader,
	buscarVeiculoLoader,
	buscarFilialLoader,
	editarFuncionarioLoader,buscarReservaLoader,cadastroLocacaoCReservaLoader,
	cadastroLocacaoSReservaLoader,buscarClientePJLoader,buscarLocacaoLoader,configuracoesLoader;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		App.stage.setOnCloseRequest(WindowEvent -> System.exit(0));
		new javafx.concurrent.Service<Object>() {//uma Tarefa
			@Override
			protected Task<Object> createTask() {
				return new Task<Object>() {
					@Override
					protected Object call() throws Exception {
						try {

							App.setMenuCadastros(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/MenuCadastros.fxml")));
							App.setLoginPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/Login.fxml")));
							System.out.println("Login");
							cadastroClientePFLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroCliente.fxml"));
							App.setCadastroClienteFisicoPane(cadastroClientePFLoader.load());
							System.out.println("CadastroClientePF");
							App.setCadastroClienteJuridicoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroClientePJ.fxml")));
							System.out.println("CadastroClientePJ");
							editarPFLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/EditarClientePF.fxml"));
							App.setEditarClienteFisicoPane(editarPFLoader.load());
							System.out.println("EditarClientePF");
							editarPJLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/EditarClientePJ.fxml"));
							App.setEditarClienteJuridicoPane(editarPJLoader.load());
							System.out.println("EditarClientePJ");
							App.setMenuClientePane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/MenuCliente.fxml")));
							System.out.println("MenuCliente");
							App.setAlterarSenhaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/AlterarSenha.fxml")));
							System.out.println("AlterarSenha");
							//Reserva
							cadastroReservaLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroReserva.fxml"));
							App.setCadastroReservaPane(cadastroReservaLoader.load());
							System.out.println("CadastroReserva");
							buscarReservaLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarReserva.fxml"));
							App.setBuscarReservaPane(buscarReservaLoader.load());
							System.out.println("Buscar Reserva");
							//Funcionario
							App.setMenuFuncionarioPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/MenuFuncionario.fxml")));
							System.out.println("Menu Func");
							cadastroFuncionarioLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroFuncionario.fxml"));
							App.setCadastroFuncionarioPane(cadastroFuncionarioLoader.load());
							System.out.println("Cadastro Func");
							buscarFuncionarioLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarFuncionario.fxml"));
							App.setBuscarFuncionarioPane(buscarFuncionarioLoader.load());
							//Filial
							App.setCadastroFilialPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroFilial.fxml")));
							System.out.println("Cadastro Filial");
							buscarFilialLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarFilial.fxml"));
							App.setBuscarFilialPane(buscarFilialLoader.load());
							System.out.println("Buscar Filial");
							buscarClientePFLoader = new  FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarClientePF.fxml"));
							App.setBuscarPFPane(buscarClientePFLoader.load());
							System.out.println("Buscar Pessoa Fisica ");
							buscarClientePJLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarClientePJ.fxml"));
							App.setBuscarPJPane(buscarClientePJLoader.load());
							System.out.println("Buscar Pessoa Juridica ");
							editarFuncionarioLoader =  new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/EditarFuncionario.fxml"));
							App.setEditarFuncionarioPane(editarFuncionarioLoader.load());
							System.out.println("Editar funcionario ");
							App.setCadastroCategoriaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroCategoria.fxml")));
							System.out.println("Cadastro Categoria");
							App.setBuscarCategoriaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarCategoria.fxml")));
							System.out.println("Buscar Categoria");
							buscarVeiculoLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarVeiculo.fxml"));
							App.setBuscarVeiculoPane(buscarVeiculoLoader.load());
							System.out.println("Cadastro Veiculo");
							App.setCadastroVeiculoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroVeiculo.fxml")));
							System.out.println("Buscar Veiculo");
							cadastroLocacaoCReservaLoader =  new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroLocacaoCReserva.fxml"));
							App.setCadastroLocacaoCReservaPane(cadastroLocacaoCReservaLoader.load());
							System.out.println("Cadastro Locacao Com Reserva");
							App.setFechamentoDialog(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/FechamentoDialog.fxml")));
							App.setPermissaoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/TelaPermissao.fxml")));
							System.out.println("Permissao");
							cadastroLocacaoSReservaLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/CadastroLocacaoSReserva.fxml"));
							App.setCadastroLocacaoSReservaPane(cadastroLocacaoSReservaLoader .load());
							System.out.println("LOC S Reserva");
							buscarLocacaoLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/BuscarLocacao.fxml"));
							App.setBuscarLocacaoPane(buscarLocacaoLoader.load());
							System.out.println("Buscar Locação");
							App.setRetornoLocacaoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/RetornoLocacao.fxml")));
							System.out.println("Retorno Loc");
							configuracoesLoader = new FXMLLoader(getClass().getClassLoader().getResource("br/com/palves/pbd/view/Configuracoes.fxml"));
							App.setConfiguracoesPane(configuracoesLoader.load());
							System.out.println("Configuracoes");
							App.setResetarSenhaPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/ResetSenha.fxml")));
							System.out.println("Resetar Senha");
							App.setAlugadosPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/VeiculosAlugados.fxml")));
							System.out.println("Veiculos Alugados");
							App.setDisponibilidadeVeiculosPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/DisponibilidadeVeiculo.fxml")));
							System.out.println("Disponibilidade Veículos");
							App.setRelatorioClientesPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/RelatorioClientes.fxml")));
							System.out.println("Relatorio clientes");
							App.setRelatorioLocacaoPane(FXMLLoader.load(getClass().getClassLoader().getResource("br/com/palves/pbd/view/RelatorioLocacao.fxml")));
							System.out.println("Relatorio Locacao");
							//Scene
							App.cenaLogin = new Scene(App.getLoginPane(),Toolkit.getDefaultToolkit().getScreenSize().getWidth(),Toolkit.getDefaultToolkit().getScreenSize().getHeight());
							App.cenaCadastro = new Scene(App.getMenuCadastros(),Toolkit.getDefaultToolkit().getScreenSize().getWidth(),Toolkit.getDefaultToolkit().getScreenSize().getHeight());
							//App.cenaCadastroPJ = new Scene(App.getCadastroClienteJuridicoPane(),1000,700);
							App.cenaMenuCliente =  new Scene(App.getMenuClientePane(),Toolkit.getDefaultToolkit().getScreenSize().getWidth(),Toolkit.getDefaultToolkit().getScreenSize().getHeight());
							App.cenaMenuFuncionario = new Scene(App.getMenuFuncionarioPane(),Toolkit.getDefaultToolkit().getScreenSize().getWidth(),Toolkit.getDefaultToolkit().getScreenSize().getHeight());
							DaoGenerico.getInstance().primeiroAcesso();
							//App.sceneMenu = new Scene(App.menu);
						} catch (IOException e) {
							e.printStackTrace();
						}catch (Exception i) {
							Alerta a = Alerta.getInstance();
							a.setMensagem("Erro ao conectar BD!");
							a.show();
						}
						return null;
					}
					@Override
					protected void succeeded() {
						super.succeeded();
						App.stage.setResizable(true);
						App.stage.setScene(App.cenaLogin);
						App.stage.centerOnScreen();
						App.addTelas();
						App.stage.show();
						iniciarThread();
					}	
				};

			}
		}.start();
	}
	public static FXMLLoader getEditarPFLoader() {
		return editarPFLoader;
	}
	public static FXMLLoader getEditarPJLoader() {
		return editarPJLoader;
	}
	public static FXMLLoader getCadastroReservaLoader() {
		return cadastroReservaLoader;
	}
	public static FXMLLoader getCadastroFuncionarioLoader() {
		return cadastroFuncionarioLoader;
	}
	public static FXMLLoader getBuscarFuncionarioLoader() {
		return buscarFuncionarioLoader;
	}
	public static FXMLLoader getBuscarFilialLoader() {
		return buscarFilialLoader;
	}
	public static FXMLLoader getEditarFuncionarioLoader() {
		return editarFuncionarioLoader;
	}
	public static FXMLLoader getCadastroLocacaoCReservaLoader() {
		return cadastroLocacaoCReservaLoader;
	}
	public static FXMLLoader getBuscarReservaLoader() {
		return buscarReservaLoader;
	}
	public static boolean isDetalhes() {
		return detalhes;
	}
	public static FXMLLoader getBuscarClientePFLoader() {
		return buscarClientePFLoader;
	}
	public static FXMLLoader getBuscarVeiculoLoader() {
		return buscarVeiculoLoader;
	}
	public static FXMLLoader getCadastroClientePFLoader() {
		return cadastroClientePFLoader;
	}
	public static FXMLLoader getBuscarClientePJLoader() {
		return buscarClientePJLoader;
	}
	public static FXMLLoader getCadastroLocacaoSReservaLoader() {
		return cadastroLocacaoSReservaLoader;
	}
	public static FXMLLoader getBuscarLocacaoLoader() {
		return buscarLocacaoLoader;
	}
	public static boolean isRodando() {
		return rodando;
	}
	public static boolean isBackup() {
		return backup;
	}
	public static Thread getMinhaThread() {
		return minhaThread;
	}
	public static FXMLLoader getConfiguracoesLoader() {
		return configuracoesLoader;
	}
	public void iniciarThread() {
		minhaThread = new  Thread(()->{
			System.out.println("iniciado loop");
			rodando = true;
			while(rodando) {
				System.err.println("iteração");
				Platform.runLater(()->{
					try {
						//Tirar o veiculo da revisão
						RevisaoDao rDao = RevisaoDao.getInstance();
						rDao.procedureChacaVeiculosNaRevisao();//Funcionando!
						System.err.println("Executou Revisao");
						//Verificar validade da reserva
						this.validarReserva();//Funcionando!
						System.err.println("Executou Reserva");
						//Bakcup
						this.checarBackup();
						System.err.println("Checou Backup");
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				try {
					Thread.sleep(10000);
					System.out.println("+10m");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		minhaThread.start();
	}
	private void validarReserva() {
		try {
			List<Reserva>rlist = null;
			rlist = ReservaDao.getInstance().buscarPorStatus(StatusEnum.EM_ESPERA.getValor());
			System.err.println("TAM:"+rlist.size());
			for (Reserva r: rlist) {
				if(r.getSituacao().replace(" ","").equalsIgnoreCase(StatusEnum.EM_ESPERA.getValor().replace(" ", ""))) {
					if(r.getDataHoraRetirada().getTime() < new Date().getTime()) { //Ja Passou
						if((new Date().getTime() - r.getDataHoraRetirada().getTime() > 3600000)){//Se passou mais uma hora!
							r.setSituacao(StatusEnum.CANCELADA.getValor());
							ReservaDao.getInstance().persistOrMerge(r);
							System.err.println("Cancelou por passar mais de uma hora");
						}else if(new Date().getTime() - r.getDataHoraRetirada().getTime()<=3600000) {//Não passou uma hora ainda!ver se esta na tolerancia
							LocalTime agr = TratadorDeMascara.dateToLocalTime(new Date());
							LocalTime ini = TratadorDeMascara.dateToLocalTime(r.getFilial().getHoraInicioExpediente());
							LocalTime fim = TratadorDeMascara.dateToLocalTime(r.getFilial().getHoraFimExpediente());
							//Compara em minutos
							if((((agr.getHour()+1)*60 + (agr.getMinute()+1))) >= (((ini.getHour()+1)*60 + ini.getMinute()+1)) && (((agr.getHour()*60+1 + agr.getMinute()+1))<=(fim.getHour()*60+1 + fim.getMinute()+1))) {
								System.out.println("Está na tolerância!");
							}else {
								r.setSituacao(StatusEnum.CANCELADA.getValor());
								ReservaDao.getInstance().persistOrMerge(r);
								System.err.println("Cancelou Reserva Fora de Expediente");
							}	
						}
					}
				}
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	public static Date lerArquivo() {
		File arq = new File("Backup.txt");
		try {
			if(!arq.exists())
				escreverArquivo(new Date());
			FileReader fr = new FileReader(arq);
			BufferedReader lerArq = new BufferedReader(fr);
			String linha = lerArq.readLine();
			return TratadorDeMascara.converterStringDataHora(linha);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void chamarBackup() {
		App.stage.close();
		try {
			FileChooser fc = new FileChooser();
			fc.setTitle("-- BACKUP -- Selecione o arquivo pg_dump - Porta: 5432 user postgress senha:123");
			DirectoryChooser directChooser = new DirectoryChooser();
			String pg_dump =  fc.showOpenDialog(null).getAbsolutePath();
			directChooser.setTitle("-- BACKUP -- Escolha um diretorio para salvar o Backup");
			String dir =  directChooser.showDialog(null).getAbsolutePath();
			PostgresBackup b = new PostgresBackup();
			b.realizaBackup(pg_dump, dir);
			escreverArquivo(new Date((new Date().getTime())));//Se der certo Um dia após!
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (java.lang.NullPointerException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//Se tudo ocorrer bem
		backup = false;
		System.out.println("AMANHA:"+new Date((new Date().getTime() + (24*3600000))));
		App.stage.show();
		System.out.println("saiu!");
	}
	public static void escreverArquivo(Date date) {
		File arq = new File("Backup.txt");
		try {
			if(!arq.exists())
				arq.createNewFile();
			FileWriter fw = new FileWriter(arq);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.print(TratadorDeMascara.converterDataHoraString(date));
			gravarArq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void checarBackup() {
		if(Corrente.funcionario!=null) {//Olha se funcionario esta logado!
			if(backup==false) {//Olha se a tela de bakcup esta online 
				if(lerArquivo().getTime() + (24*3600000) < new Date().getTime()) {//Olha se ja passou um dia!
					backup=true;//Ativa o backup;
					Platform.runLater(()->{
						this.chamarBackup();
					});	
				}else {
					System.out.println("Não chegou a hora");
				}
			}else
				System.out.println("Um bakcup em andamento");
		}else 
			System.out.println("Funcionario não online");
	}
	//	public static void main(String[] args) {
	//		//escreverArquivo(new Date());
	//		Date data = lerArquivo();
	//		System.out.println(data.toString());
	//	}
}
