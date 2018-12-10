package br.com.palves.pbd.controller;

import java.awt.Color;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Endereco;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.view.FormularioCrudFilial;

public class ControllerCrudFilial {
	public static FormularioCrudFilial fpf =  new FormularioCrudFilial() ;
	private List<Filial> filiais  = null;
	private int indiceCorrente;
	public ControllerCrudFilial() {
		//this.fpf.setVisible(true);
		this.fpf.setModal(true);
		this.fpf.getSalvarButton().addActionListener(ActionEvent -> salvarFilial());	
		TratadorDeMascara.soNumero(this.fpf.getBuscarField());
		TratadorDeMascara.soNumero(this.fpf.getIdField());
		this.fpf.getIdField().setEditable(false);
		this.fpf.getIrButton().addActionListener(ActionEvent -> carregarFilial());//Busca por ID!
		this.fpf.getLimparButton().addActionListener(ActionEvent -> limparCampos());
		this.fpf.getAllButton().addActionListener(ActionEvent -> carregarAll());
		this.fpf.getDireitaButton().addActionListener(ActionEvent -> direita());
		this.fpf.getEsquerdaButton().addActionListener(ActionEvent -> esquerda());
	}
	private void salvarFilial()
	{
		FilialDao daoPF = FilialDao.getInstance();
		try {
			this.validacoesDeNull();
			Filial filial = new Filial();
			this.preencherCampos(filial);
			daoPF.persistOrMerge(filial);
			JOptionPane.showMessageDialog(null,filial.getNome()+(this.fpf.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
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
		if(this.fpf.getNomeField().getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
	}
	private void preencherCampos(Filial filial) {
		String  nomeField = this.fpf.getNomeField().getText();
		String idField = this.fpf.getIdField().getText();
		Date horaInicio = TratadorDeMascara.converterStringHora(this.fpf.getHoraInicioCombo().getSelectedItem().toString());
		Date horaFim = TratadorDeMascara.converterStringHora(this.fpf.getHoraFimCombo().getSelectedItem().toString());
		
		String ruaField = this.fpf.getRuaField().getText();
		int numeroField = this.fpf.getNumeroField().getText().length()<=0?0:Integer.parseInt( this.fpf.getNumeroField().getText());
		String bairroField = this.fpf.getBairroField().getText();
		String cepField =  this.fpf.getCepField().getText();
		String ufField = this.fpf.getUfField().getText();
		String cidadeField = this.fpf.getCidadeField().getText();
		String buscarField = this.fpf.getBuscarField().getText();
		
		Endereco e = new Endereco();
		filial.setEndereco(e);
		e.setRua(ruaField);
		e.setNumero(numeroField);
		e.setCep(cepField);
		e.setUf(ufField);
		e.setCidade(cidadeField);
		e.setBairro(bairroField);
		if(idField.trim().length()>0) {//então eh update
			filial.setId(Integer.parseInt(idField));
			filial.getEndereco().setId(Integer.parseInt(this.fpf.getIdEnd().getText()));
		}else
			this.fpf.getIdEnd().setText("");//OLHAR                                     OLHAR      AKIIIIIIIIIIIIIIIIIIIIIIII
		filial.setNome(nomeField);
		filial.setSituacao("ATIVO");
		filial.setHoraInicioExpediente(horaInicio);
		filial.setDataFimExpediente(horaFim);
		//filial.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
	}
	private void carregarFilial() {
		FilialDao daoPF = FilialDao.getInstance();
		if(this.fpf.getBuscarField().getText().trim().length()<=0)
			JOptionPane.showMessageDialog(null, "Digite um ID para Busca!");
		else {
			try {
				int id = Integer.parseInt(this.fpf.getBuscarField().getText().trim());
				Filial p = daoPF.findById(Filial.class,id);
				if(p!=null) {
					limparCampos();
					this.fpf.getOperacaoLabel().setText("Modo Update");
					this.fpf.getOperacaoLabel().setForeground(Color.blue);
					preencherBusca(p);
				}else
					JOptionPane.showMessageDialog(null,"Nenhuma Filial encontrada para o id:"+id);
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
	private void preencherBusca(Filial filial) {	
		this.fpf.getNomeField().setText(filial.getNome());
		this.fpf.getIdField().setText(filial.getId()+"");
		//this.fpf.getCpfField().setText(pessoaF.getCpf());
		this.fpf.getHoraFimCombo().setSelectedItem(TratadorDeMascara.converterHoraString(filial.getDataFimExpediente()));
		this.fpf.getHoraInicioCombo().setSelectedItem(TratadorDeMascara.converterHoraString(filial.getHoraInicioExpediente()));
		
		this.fpf.getRuaField().setText(filial.getEndereco().getRua());
		this.fpf.getNumeroField().setText(filial.getEndereco().getNumero()!=null?filial.getEndereco().getNumero()+"":"");
		this.fpf.getBairroField().setText(filial.getEndereco().getBairro());
		this.fpf.getCepField().setText(filial.getEndereco().getCep());
		this.fpf.getUfField().setText(filial.getEndereco().getUf());
		this.fpf.getCidadeField().setText(filial.getEndereco().getCidade());
		this.fpf.getIdEnd().setText(filial.getEndereco().getId()+"");
		
	}
	private void carregarAll() {
		if(this.fpf.getBuscarField().getText().replace(" ","").length()<=0) {
			FilialDao daoPF = FilialDao.getInstance();
			limparCampos();
			try {
				filiais = daoPF.findAll(Filial.class);
				if(filiais!=null) {
					JOptionPane.showMessageDialog(null,"Foram Encontrados: "+filiais.size()+" Filiais!");
					this.preencherBusca(filiais.get(0));
					indiceCorrente = 0;
					this.fpf.getOperacaoLabel().setText("Modo Update");
					this.fpf.getOperacaoLabel().setForeground(Color.blue);
					//this.fpf.getCpfField().setText("");
				}else
					JOptionPane.showMessageDialog(null,"Nenhuma Filial encontrada!");
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
		}else
			JOptionPane.showMessageDialog(null,"O Campo de Busca deve estar vazio!");
	}
	private void limparCampos() {
		this.fpf.getOperacaoLabel().setText("Modo Inserção");
		this.fpf.getOperacaoLabel().setForeground(Color.red);
		this.fpf.getIdEnd().setText("");
		this.filiais = null;
		indiceCorrente = 0;
		LimparCampo.limparCamposDialog(fpf.getContentPane().getComponents());
	}
	private void direita() {
		if(filiais!=null) {
			if(indiceCorrente >= filiais.size()-1) {
				indiceCorrente = 0;//vai para o primeiro!
			}else
				indiceCorrente++;
			this.preencherBusca(filiais.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
		
	}
	private void esquerda() {
		if(filiais!=null) {
			if(indiceCorrente==0) {
				indiceCorrente = filiais.size()-1;
			}else
				indiceCorrente--;
			this.preencherBusca(filiais.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
	}
}
