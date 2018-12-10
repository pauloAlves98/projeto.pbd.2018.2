package br.com.palves.pbd.controller;

import java.awt.Color;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Locacao;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.Veiculo;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.LocacaoDao;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.VeiculoDao;
import br.com.palves.pbd.view.FormularioCrudLocacao;

public class ControllerCrudLocacao {
	public static  FormularioCrudLocacao fpl =  new FormularioCrudLocacao() ;
	private List<Locacao> locacoes  = null;
	private int indiceCorrente;
	public ControllerCrudLocacao() {
		//this.fpl.setVisible(true);
		this.fpl.setModal(true);
		this.fpl.getSalvarButton().addActionListener(ActionEvent -> salvarPJ());	
		TratadorDeMascara.soNumero(this.fpl.getBuscarField());
		TratadorDeMascara.soNumero(this.fpl.getIdField());
		TratadorDeMascara.mascaraHora(fpl.getHoraEntregaField());
		TratadorDeMascara.mascaraHora(fpl.getHoraRealizacaoField());
		this.fpl.getIdField().setEditable(false);
		this.fpl.getIrButton().addActionListener(ActionEvent -> carregarPJ());//Busca por ID!
		this.fpl.getLimparButton().addActionListener(ActionEvent -> limparCampos());
		this.fpl.getAllButton().addActionListener(ActionEvent -> carregarAll());
		this.fpl.getDireitaButton().addActionListener(ActionEvent -> direita());
		this.fpl.getEsquerdaButton().addActionListener(ActionEvent -> esquerda());
		
		this.fpl.getFilialEntregaCombo().addItemListener(ItemListener->itemComboFilial(fpl.getFilialEntregaCombo(),fpl.getFilialEntregaIdField(),fpl.getFilialEntregaTextArea()));
		this.fpl.getFilialLocatariaCombo().addItemListener(ItemListener->itemComboFilial(fpl.getFilialLocatariaCombo(),fpl.getIdFilialLocatariaField(),fpl.getFilialLocatariaTextArea()));
		this.fpl.getClienteCombo().addItemListener(ItemListener->itemComboPessoa(fpl.getClienteCombo(),fpl.getClienteField()));
		this.fpl.getMotoristaCombo().addItemListener(ItemListener->itemComboPessoa(fpl.getMotoristaCombo(),fpl.getIdMotoristaField()));
		this.fpl.getVeiculoCombo().addItemListener(ItemListener->itemComboVeiculo(fpl.getVeiculoCombo(),fpl.getVeiculoIdField(),fpl.getVeiculoTextArea()));
		
		this.atualizarComboFilial(this.fpl.getFilialEntregaCombo());
		this.atualizarComboFilial(this.fpl.getFilialLocatariaCombo());
		this.atualizarComboPessoa(fpl.getClienteCombo());
		this.atualizarComboPessoa(fpl.getMotoristaCombo());
		this.atualizarComboVeiculo(fpl.getVeiculoCombo());
	}
	private void salvarPJ()
	{
		LocacaoDao dao = LocacaoDao.getInstance();
		try {
			this.validacoesDeNull();
			Locacao locacao = new Locacao();
			this.preencherCampos(locacao);
			dao.persistOrMerge(locacao);
			JOptionPane.showMessageDialog(null,(this.fpl.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
			this.limparCampos();
		} 
		catch (DaoException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
		}catch(ValidacaoException e3) {
			JOptionPane.showMessageDialog(null,e3.getMessage());
			e3.printStackTrace();
		}catch(java.lang.NumberFormatException e4) {
			e4.printStackTrace();
		}
	}	
	private void validacoesDeNull() throws ValidacaoException {
		if(this.fpl.getEntregaDateChooser().getDate()==null)
			throw new ValidacaoException("A data prevista para entrega não pode ser nulo!");
		if(this.fpl.getRealizacaoDataChooser().getDate()==null)
			throw new ValidacaoException("A data de realização naão pode ser nula!");
	}
	private void preencherCampos(Locacao locacao) {
		Date dataHoraRealizacao= TratadorDeMascara.unirDataHora(this.fpl.getRealizacaoDataChooser().getDate(),
				this.fpl.getHoraRealizacaoField().getText().length()<=1?"00:00":this.fpl.getHoraRealizacaoField().getText());
		String idField = this.fpl.getIdField().getText();
		Date dataEntrega = TratadorDeMascara.unirDataHora(this.fpl.getEntregaDateChooser().getDate(),
				this.fpl.getHoraEntregaField().getText().length()<=1?"00:00":this.fpl.getHoraEntregaField().getText());
		boolean kmlivre = this.fpl.getKmLivreRadio().isSelected();
		int idLocataria = Integer.parseInt(this.fpl.getIdFilialLocatariaField().getText());
		int idEntrega = Integer.parseInt(this.fpl.getFilialEntregaIdField().getText());
		int idVeiculo = Integer.parseInt(this.fpl.getVeiculoIdField().getText());
		int idMotorista = Integer.parseInt(this.fpl.getIdMotoristaField().getText());
		int idCliente = Integer.parseInt(this.fpl.getClienteField().getText());
		if(idField.trim().length()>0) {//então eh update
			locacao.setId(Integer.parseInt(idField));
		}    
		Filial fl = new Filial();
		fl.setId(idLocataria);
		Filial fe = new Filial();
		fe.setId(idEntrega);
		Veiculo v = new Veiculo();
		v.setId(idVeiculo);
		PessoaFisica pf = new PessoaFisica();
		pf.setId(idMotorista);
		Pessoa cliente = new PessoaFisica();
		cliente.setId(idCliente);

		locacao.setDataRetirada(dataHoraRealizacao);
		locacao.setDataEntrega(dataEntrega);
		locacao.setKmLivre(kmlivre);
		locacao.setFilialEntrega(fe);
		locacao.setFilialLocaataria(fl);
		locacao.setVeiculo(v);
		locacao.setMotorista(pf);
		locacao.setPessoa(cliente);
		locacao.setFuncionario(Corrente.funcionario);
		locacao.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
	}
	private void carregarPJ() {
		LocacaoDao daoPJ = LocacaoDao.getInstance();
		if(this.fpl.getBuscarField().getText().trim().length()<=0)
			JOptionPane.showMessageDialog(null, "Digite um ID para Busca!");
		else {
			try {
				int id = Integer.parseInt(this.fpl.getBuscarField().getText().trim());
				Locacao p = daoPJ.findById(Locacao.class,id);
				if(p!=null) {
					limparCampos();
					this.fpl.getOperacaoLabel().setText("Modo Update");
					this.fpl.getOperacaoLabel().setForeground(Color.blue);
					preencherBusca(p);
				}else
					JOptionPane.showMessageDialog(null,"Nenhum Locacao encontrada para o id: "+id);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());

				e.printStackTrace();
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
				e.printStackTrace();
				e.printStackTrace();
			}

		}
	}
	private void preencherBusca(Locacao p) {	
		this.fpl.getRealizacaoDataChooser().setDate(p.getDataRetirada());
		this.fpl.getHoraRealizacaoField().setText(TratadorDeMascara.converterHoraString(p.getDataRetirada()));
		this.fpl.getEntregaDateChooser().setDate(p.getDataEntrega());
		this.fpl.getHoraEntregaField().setText(TratadorDeMascara.converterHoraString(p.getDataEntrega()));
		if(p.isKmLivre())
			this.fpl.getKmLivreRadio().setSelected(true);
		else
			this.fpl.getRdbtnKmControle().setSelected(true);
		this.fpl.getFilialLocatariaCombo().setSelectedItem(p.getFilialLocaataria().getNome());
		this.fpl.getFilialEntregaCombo().setSelectedItem(p.getFilialEntrega().getNome());
		this.fpl.getVeiculoCombo().setSelectedItem(p.getVeiculo().getNome());
		this.fpl.getMotoristaCombo().setSelectedItem(p.getMotorista().getNome());
		this.fpl.getClienteCombo().setSelectedItem(p.getPessoa().getNome());					
		this.fpl.getIdField().setText(p.getId()+"");


	}
	private void carregarAll() {
		if(this.fpl.getBuscarField().getText().replace(" ","").length()<=0) {
			LocacaoDao daoPJ = LocacaoDao.getInstance();
			limparCampos();
			try {
				locacoes = daoPJ.findAll(Locacao.class);
				if(locacoes!=null) {
					JOptionPane.showMessageDialog(null,"Foram Encontrados: "+locacoes.size()+" Locacao!");
					this.preencherBusca(locacoes.get(0));
					indiceCorrente = 0;
					this.fpl.getOperacaoLabel().setText("Modo Update");
					this.fpl.getOperacaoLabel().setForeground(Color.blue);
					//this.fpf.getCpfField().setText("");
				}else
					JOptionPane.showMessageDialog(null,"Nenhum Locacao encontrado!");
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			JOptionPane.showMessageDialog(null,"O Campo de Busca deve estar vazio!");
	}
	private void limparCampos() {
		this.fpl.getOperacaoLabel().setText("Modo Inserção");
		this.fpl.getOperacaoLabel().setForeground(Color.red);
		this.locacoes = null;
		indiceCorrente = 0;
		LimparCampo.limparCamposDialog(fpl.getContentPane().getComponents());
		
		this.atualizarComboFilial(this.fpl.getFilialEntregaCombo());
		this.atualizarComboFilial(this.fpl.getFilialLocatariaCombo());
		this.atualizarComboPessoa(fpl.getClienteCombo());
		this.atualizarComboPessoa(fpl.getMotoristaCombo());
		this.atualizarComboVeiculo(fpl.getVeiculoCombo());
	}
	private void direita() {
		if(locacoes!=null) {
			if(indiceCorrente >= locacoes.size()-1) {
				indiceCorrente = 0;//vai para o primeiro!
			}else
				indiceCorrente++;
			this.preencherBusca(locacoes.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}

	}
	private void esquerda() {
		if(locacoes!=null) {
			if(indiceCorrente==0) {
				indiceCorrente = locacoes.size()-1;
			}else
				indiceCorrente--;
			this.preencherBusca(locacoes.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
	}
	private void atualizarComboFilial(JComboBox filialCombo) {
		if(filialCombo.getModel().getSize() > 0) {
			filialCombo.removeAllItems();
			System.gc();
		}
		FilialDao fd = FilialDao.getInstance();
		try {
			List<Filial>lts = fd.findAll(Filial.class);//Criar um native query
			for(int i = 0;i<lts.size();i++) {
				filialCombo.addItem(lts.get(i).getNome());
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar as filiais no combo!");
			e.printStackTrace();
		}	
	}
	private void atualizarComboVeiculo(JComboBox veiculoCombo) {
		if(veiculoCombo.getModel().getSize() > 0) {
			veiculoCombo.removeAllItems();
			System.gc();
		}
		VeiculoDao fd = VeiculoDao.getInstance();
		try {
			List<Veiculo>lts = fd.findAll(Veiculo.class);//Criar um native query
			for(int i = 0;i<lts.size();i++) {
				veiculoCombo.addItem(lts.get(i).getNome());
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar os Veiculos no combo!");
			e.printStackTrace();
		}	
	}
	private void atualizarComboPessoa(JComboBox pessoaCombo) {
		if(pessoaCombo.getModel().getSize() > 0) {
			pessoaCombo.removeAllItems();
			System.gc();
		}
		PessoaFisicaDao fd = PessoaFisicaDao.getInstance();
		try {
			List<PessoaFisica>lts = fd.findAll(PessoaFisica.class);//Criar um native query
			for(int i = 0;i<lts.size();i++) {
				pessoaCombo.addItem(lts.get(i).getNome());
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar os Pessoas no combo!");
			e.printStackTrace();
		}	
	}
	private void itemComboFilial(JComboBox filialCombo,JTextField idC,JTextArea txt) {
		FilialDao fd = FilialDao.getInstance();
		//essa busca eh por nome
		try {
			if(filialCombo.getModel().getSize()>0) {
				Object[ ] f = fd.buscaIdPorNome(filialCombo.getSelectedItem().toString());//Criar um native query
				if(f==null)
					throw new DaoException("Sem filiais!");
				idC.setText(f[1]+"");
				txt.setText("");
				Filial fl = fd.findById(Filial.class,Integer.parseInt(f[1]+""));
				txt.append("Exp: "+TratadorDeMascara.converterHoraString(fl.getHoraInicioExpediente())+" às "+TratadorDeMascara.converterHoraString(fl.getDataFimExpediente())+"\n");
				txt.append("Cidade: "+fl.getEndereco().getCidade()+"\n");
				txt.append("Rua: "+fl.getEndereco().getRua()+"\n");
				txt.append("Numero: "+fl.getEndereco().getNumero()+"\n");
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar esta filial");
			e.printStackTrace();
		}	
	}
	private void itemComboPessoa(JComboBox pessoaCombo,JTextField idC) {
		PessoaFisicaDao fd = PessoaFisicaDao.getInstance();
		//essa busca eh por nome
		try {
			if(pessoaCombo.getModel().getSize()>0) {
				Object[ ] f = fd.buscarIdPorNome(pessoaCombo.getSelectedItem().toString());//Criar um native query
				if(f==null)
					throw new DaoException("Sem Pessoas!");
				idC.setText(f[1]+"");
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar esta Pessoa");
			e.printStackTrace();
		}	
	}
	private void itemComboVeiculo(JComboBox veiculoCombo,JTextField idC,JTextArea txt) {
		VeiculoDao fd = VeiculoDao.getInstance();
		//essa busca eh por nome
		try {
			if(veiculoCombo.getModel().getSize()>0) {
				Object[ ] f = fd.buscarIdPorNome(veiculoCombo.getSelectedItem().toString());//Criar um native query
				if(f==null)
					throw new DaoException("Sem Veiculos!");
				idC.setText(f[1]+"");
				txt.setText("");
				Veiculo fl = fd.findById(Veiculo.class,Integer.parseInt(f[1]+""));
				txt.setText(fl.toString().replace("=",":").replace(",","\n"));
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar esta Veiculo");
			e.printStackTrace();
		}	
	}
}
