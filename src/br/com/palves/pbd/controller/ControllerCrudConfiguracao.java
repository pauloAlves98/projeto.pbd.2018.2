package br.com.palves.pbd.controller;

import java.awt.Color;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.bin.Endereco;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.ConfiguracaoDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.view.FormularioCrudConfiguracao;

public class ControllerCrudConfiguracao {
	public  static FormularioCrudConfiguracao fconfg =  new FormularioCrudConfiguracao() ;
	private List<Configuracao> configs  = null;
	private int indiceCorrente;
	public ControllerCrudConfiguracao() {
		//this.fconfg.setVisible(true);
		this.fconfg.setModal(true);
		this.fconfg.getSalvarButton().addActionListener(ActionEvent -> salvarPJ());	
		TratadorDeMascara.soNumero(this.fconfg.getBuscarField());
		TratadorDeMascara.soNumero(this.fconfg.getIdField());
		this.fconfg.getIdField().setEditable(false);
		this.fconfg.getIrButton().addActionListener(ActionEvent -> carregarConfig());//Busca por ID!
		this.fconfg.getLimparButton().addActionListener(ActionEvent -> limparCampos());
		this.fconfg.getAllButton().addActionListener(ActionEvent -> carregarAll());
		this.fconfg.getDireitaButton().addActionListener(ActionEvent -> direita());
		this.fconfg.getEsquerdaButton().addActionListener(ActionEvent -> esquerda());
	}
	private void salvarPJ()
	{
		ConfiguracaoDao daoPJ = ConfiguracaoDao.getInstance();
		try {
			this.validacoesDeNull();
			Configuracao conf = new Configuracao();
			this.preencherCampos(conf);
			daoPJ.persistOrMerge(conf);
			JOptionPane.showMessageDialog(null,(this.fconfg.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Conf Inserido":" Conf Atualizado")+" com sucesso!");
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
//		if(this.fconfg.getSenhaField().getText().length()<6 || this.fconfg.getSenhaField().getText().length()>11 )
//			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
//		if(this.fconfg.getNomeField().getText().length()<=0)
//			throw new ValidacaoException("O nome não pode ser nulo!");
//		if(this.fconfg.getLoginField().getText().length()<=0)
//			throw new ValidacaoException("O Login não pode ser nulo!");
//		if(this.fconfg.getCnpjField().getText().replace(" ","").trim().length()<=4) {
//			throw new ValidacaoException("O CNPJ não pode ser nulo!");
//		}
	}
	private void preencherCampos(Configuracao conf) throws DaoException {
		String idField;
		try {
		double kcontrole = Double.parseDouble(this.fconfg.getValorKcontroleField().getText().replace(",","."));
		double klivre = Double.parseDouble(this.fconfg.getValorKlivreField().getText().replace(",","."));
		Date  data = new Date();
		double tcombust = Double.parseDouble(this.fconfg.getTaxaCombField().getText().replace(",","."));
		double tdev = Double.parseDouble(this.fconfg.getTaxaDevField().getText().replace(",","."));
		idField = this.fconfg.getIdField().getText();
		double higiene = Double.parseDouble(this.fconfg.getTaxaHigField().getText().replace(",","."));
		String buscarField = this.fconfg.getBuscarField().getText();
		
		
		if(idField.trim().length()>0)//então eh update
			conf.setId(Integer.parseInt(idField));
//		}else
//			this.fconfg.getIdEnd().setText("");//OLHAR                                     OLHAR      AKIIIIIIIIIIIIIIIIIIIIIIII
		conf.setDataRealizacao(data);
		conf.setDiariaKlivre(klivre);
		conf.setTaxaCombustivel(tcombust);
		conf.setTaxaDevolucao(tdev);
		conf.setDiariaKcontrole(kcontrole);
		conf.setTaxaHigiene(higiene);
		}catch(java.lang.NumberFormatException e4) {
			throw new DaoException("Existe números invalidos!");
		}
	}
	private void carregarConfig() {
		ConfiguracaoDao daoPJ = ConfiguracaoDao.getInstance();
		if(this.fconfg.getBuscarField().getText().trim().length()<=0)
			JOptionPane.showMessageDialog(null, "Digite um ID para Busca!");
		else {
			try {
				int id = Integer.parseInt(this.fconfg.getBuscarField().getText().trim());
				Configuracao p = daoPJ.findById(Configuracao.class,id);
				if(p!=null) {
					limparCampos();
					this.fconfg.getOperacaoLabel().setText("Modo Update");
					this.fconfg.getOperacaoLabel().setForeground(Color.blue);
					preencherBusca(p);
				}else
					JOptionPane.showMessageDialog(null,"Nenhuma Config encontrado para o id: "+id);
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
	private void preencherBusca(Configuracao p) {	
		this.fconfg.getValorKcontroleField().setText(p.getDiariaKcontrole()+"");
		this.fconfg.getValorKlivreField().setText(p.getDiariaKlivre()+"");
	    this.fconfg.getTaxaCombField().setText(p.getTaxaCombustivel()+"");;
	    this.fconfg.getTaxaDevField().setText(p.getTaxaDevolucao()+"");
		this.fconfg.getIdField().setText(p.getId()+"");
		this.fconfg.getTaxaHigField().setText(p.getTaxaHigiene()+"");
		
	}
	private void carregarAll() {
		if(this.fconfg.getBuscarField().getText().replace(" ","").length()<=0) {
			ConfiguracaoDao daoPJ = ConfiguracaoDao.getInstance();
			limparCampos();
			try {
				configs = daoPJ.findAll(Configuracao.class);
				if(configs!=null) {
					JOptionPane.showMessageDialog(null,"Foram Encontrados: "+configs.size()+" Configurações!");
					this.preencherBusca(configs.get(0));
					indiceCorrente = 0;
					this.fconfg.getOperacaoLabel().setText("Modo Update");
					this.fconfg.getOperacaoLabel().setForeground(Color.blue);
					//this.fpf.getCpfField().setText("");
				}else
					JOptionPane.showMessageDialog(null,"Nenhum Config encontrado!");
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			JOptionPane.showMessageDialog(null,"O Campo de Busca deve estar vazio!");
	}
	private void limparCampos() {
		this.fconfg.getOperacaoLabel().setText("Modo Inserção");
		this.fconfg.getOperacaoLabel().setForeground(Color.red);
		//this.fconfg.getIdEnd().setText("");
		this.configs = null;
		indiceCorrente = 0;
		LimparCampo.limparCamposDialog(fconfg.getContentPane().getComponents());
	}
	private void direita() {
		if(configs!=null) {
			if(indiceCorrente >= configs.size()-1) {
				indiceCorrente = 0;//vai para o primeiro!
			}else
				indiceCorrente++;
			this.preencherBusca(configs.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
		
	}
	private void esquerda() {
		if(configs!=null) {
			if(indiceCorrente==0) {
				indiceCorrente = configs.size()-1;
			}else
				indiceCorrente--;
			this.preencherBusca(configs.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
	}
}
