package br.com.palves.pbd.controller;

import java.awt.Color;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Endereco;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.view.FormularioCrudPJ;

public class ControllerCrudPJ {
	private FormularioCrudPJ fpj =  new FormularioCrudPJ() ;
	private List<PessoaJuridica> pessoasJ  = null;
	private int indiceCorrente;
	public ControllerCrudPJ() {
		this.fpj.setVisible(true);
		this.fpj.getSalvarButton().addActionListener(ActionEvent -> salvarPJ());	
		TratadorDeMascara.soNumero(this.fpj.getBuscarField());
		TratadorDeMascara.soNumero(this.fpj.getIdField());
		TratadorDeMascara.soNumero(this.fpj.getNumeroField());
		this.fpj.getIdField().setEditable(false);
		this.fpj.getIrButton().addActionListener(ActionEvent -> carregarPJ());//Busca por ID!
		this.fpj.getLimparButton().addActionListener(ActionEvent -> limparCampos());
		this.fpj.getAllButton().addActionListener(ActionEvent -> carregarAll());
		this.fpj.getDireitaButton().addActionListener(ActionEvent -> direita());
		this.fpj.getEsquerdaButton().addActionListener(ActionEvent -> esquerda());
	}
	private void salvarPJ()
	{
		PessoaJuridicaDao daoPJ = PessoaJuridicaDao.getInstance();
		try {
			this.validacoesDeNull();
			PessoaJuridica pessoaJ = new PessoaJuridica();
			this.preencherCampos(pessoaJ);
			daoPJ.persistOrMerge(pessoaJ);
			JOptionPane.showMessageDialog(null,pessoaJ.getNome()+(this.fpj.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
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
		if(this.fpj.getSenhaField().getText().length()<6 || this.fpj.getSenhaField().getText().length()>11 )
			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(this.fpj.getNomeField().getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		if(this.fpj.getLoginField().getText().length()<=0)
			throw new ValidacaoException("O Login não pode ser nulo!");
		if(this.fpj.getCnpjField().getText().replace(" ","").trim().length()<=4) {
			throw new ValidacaoException("O CNPJ não pode ser nulo!");
		}
	}
	private void preencherCampos(PessoaJuridica pessoaJ) {
		String incEstadual = this.fpj.getIncEstadualField().getText().trim();
		String cnpjField = this.fpj.getCnpjField().getText();
		String  nomeField = this.fpj.getNomeField().getText();
		String senhaField = EncriptaDecriptaApacheCodec.codificaBase64Encoder(this.fpj.getSenhaField().getText());
		String loginField = this.fpj.getLoginField().getText();
		String idField = this.fpj.getIdField().getText();
		String ruaField = this.fpj.getRuaField().getText();
		int numeroField = this.fpj.getNumeroField().getText().length()<=0?0:Integer.parseInt( this.fpj.getNumeroField().getText());
		String bairroField = this.fpj.getBairroField().getText();
		String cepField =  this.fpj.getCepField().getText();
		String ufField = this.fpj.getUfField().getText();
		String cidadeField = this.fpj.getCidadeField().getText();
		String buscarField = this.fpj.getBuscarField().getText();
		
		Endereco e = new Endereco();
		pessoaJ.setEndereco(e);
		e.setRua(ruaField);
		e.setNumero(numeroField);
		e.setCep(cepField);
		e.setUf(ufField);
		e.setCidade(cidadeField);
		e.setBairro(bairroField);
		
		if(idField.trim().length()>0) {//então eh update
			pessoaJ.setId(Integer.parseInt(idField));
			pessoaJ.getEndereco().setId(Integer.parseInt(this.fpj.getIdEnd().getText()));
		}else
			this.fpj.getIdEnd().setText("");//OLHAR                                     OLHAR      AKIIIIIIIIIIIIIIIIIIIIIIII
		pessoaJ.setNome(nomeField);
		pessoaJ.setSenha(senhaField);
		pessoaJ.setLogin(loginField);
		pessoaJ.setCnpj(cnpjField);
		pessoaJ.setIncEstadual(incEstadual);
		pessoaJ.setDiscriminador(Discriminador.PJ.getValor());//Importante
		pessoaJ.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
	}
	private void carregarPJ() {
		PessoaJuridicaDao daoPJ = PessoaJuridicaDao.getInstance();
		if(this.fpj.getBuscarField().getText().trim().length()<=0)
			JOptionPane.showMessageDialog(null, "Digite um ID para Busca!");
		else {
			try {
				int id = Integer.parseInt(this.fpj.getBuscarField().getText().trim());
				PessoaJuridica p = daoPJ.findById(PessoaJuridica.class,id);
				if(p!=null) {
					limparCampos();
					this.fpj.getOperacaoLabel().setText("Modo Update");
					this.fpj.getOperacaoLabel().setForeground(Color.blue);
					this.fpj.getCnpjField().setText("");
					preencherBusca(p);
				}else
					JOptionPane.showMessageDialog(null,"Nenhum cliente encontrado para o id: "+id);
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
	private void preencherBusca(PessoaJuridica p) {	
		this.fpj.getNomeField().setText(p.getNome());
		this.fpj.getSenhaField().setText(EncriptaDecriptaApacheCodec.decodificaBase64Decoder(p.getSenha()));
		this.fpj.getLoginField().setText(p.getLogin());
		this.fpj.getIdField().setText(p.getId()+"");
		this.fpj.getCnpjField().setCaretPosition(0);
		//this.fpf.getCpfField().setText(pessoaF.getCpf());
		this.fpj.getCnpjField().setText(p.getCnpj().replace(".","").replace("-",""));
		this.fpj.getIncEstadualField().setText(p.getIncEstadual());
		this.fpj.getRuaField().setText(p.getEndereco().getRua());
		this.fpj.getNumeroField().setText(p.getEndereco().getNumero()!=null?p.getEndereco().getNumero()+"":"");
		this.fpj.getBairroField().setText(p.getEndereco().getBairro());
		this.fpj.getCepField().setText(p.getEndereco().getCep());
		this.fpj.getUfField().setText(p.getEndereco().getUf());
		this.fpj.getCidadeField().setText(p.getEndereco().getCidade());
		this.fpj.getIdEnd().setText(p.getEndereco().getId()+"");
		
	}
	private void carregarAll() {
		if(this.fpj.getBuscarField().getText().replace(" ","").length()<=0) {
			PessoaJuridicaDao daoPJ = PessoaJuridicaDao.getInstance();
			limparCampos();
			try {
				pessoasJ = daoPJ.findAll(PessoaJuridica.class);
				if(pessoasJ!=null) {
					JOptionPane.showMessageDialog(null,"Foram Encontrados: "+pessoasJ.size()+" Clientes!");
					this.preencherBusca(pessoasJ.get(0));
					indiceCorrente = 0;
					this.fpj.getOperacaoLabel().setText("Modo Update");
					this.fpj.getOperacaoLabel().setForeground(Color.blue);
					//this.fpf.getCpfField().setText("");
				}else
					JOptionPane.showMessageDialog(null,"Nenhum cliente encontrado!");
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			JOptionPane.showMessageDialog(null,"O Campo de Busca deve estar vazio!");
	}
	private void limparCampos() {
		this.fpj.getOperacaoLabel().setText("Modo Inserção");
		this.fpj.getOperacaoLabel().setForeground(Color.red);
		this.fpj.getIdEnd().setText("");
		this.pessoasJ = null;
		indiceCorrente = 0;
		LimparCampo.limparCamposDialog(fpj.getContentPane().getComponents());
	}
	private void direita() {
		if(pessoasJ!=null) {
			if(indiceCorrente >= pessoasJ.size()-1) {
				indiceCorrente = 0;//vai para o primeiro!
			}else
				indiceCorrente++;
			this.preencherBusca(pessoasJ.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
		
	}
	private void esquerda() {
		if(pessoasJ!=null) {
			if(indiceCorrente==0) {
				indiceCorrente = pessoasJ.size()-1;
			}else
				indiceCorrente--;
			this.preencherBusca(pessoasJ.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
	}
}
