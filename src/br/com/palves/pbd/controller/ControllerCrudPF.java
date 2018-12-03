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
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.view.FormularioCrudPF;

public class ControllerCrudPF {
	private FormularioCrudPF fpf =  new FormularioCrudPF() ;
	private List<PessoaFisica> pessoasF  = null;
	private int indiceCorrente;
	public ControllerCrudPF() {
		this.fpf.setVisible(true);
		this.fpf.getSalvarButton().addActionListener(ActionEvent -> salvarPF());	
		TratadorDeMascara.soNumero(this.fpf.getBuscarField());
		TratadorDeMascara.soNumero(this.fpf.getIdField());
		this.fpf.getIdField().setEditable(false);
		this.fpf.getIrButton().addActionListener(ActionEvent -> carregarPF());//Busca por ID!
		this.fpf.getLimparButton().addActionListener(ActionEvent -> limparCampos());
		this.fpf.getAllButton().addActionListener(ActionEvent -> carregarAll());
		this.fpf.getDireitaButton().addActionListener(ActionEvent -> direita());
		this.fpf.getEsquerdaButton().addActionListener(ActionEvent -> esquerda());
	}
	private void salvarPF()
	{
		PessoaFisicaDao daoPF = PessoaFisicaDao.getInstance();
		try {
			this.validacoesDeNull();
			PessoaFisica pessoaF = new PessoaFisica();
			this.preencherCampos(pessoaF);
			daoPF.persistOrMerge(pessoaF);
			JOptionPane.showMessageDialog(null,pessoaF.getNome()+(this.fpf.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
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
		if(this.fpf.getDataNascimentoField().getDate() == null)
			throw new ValidacaoException("Campo data de nascimento nulo!");
		if(this.fpf.getnHabilitacaoField().getText().length()!=0)
			if(this.fpf.getDataVencHabField().getDate()==null)
				throw new ValidacaoException("Campo data de vencimento habilitação nulo!");
		if(this.fpf.getSenhaField().getText().length()<6 || this.fpf.getSenhaField().getText().length()>11 )
			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(this.fpf.getNomeField().getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		if(this.fpf.getLoginField().getText().length()<=0)
			throw new ValidacaoException("O Login não pode ser nulo!");
		if(this.fpf.getCpfField().getText().replace(" ","").trim().length()<=3) {
			throw new ValidacaoException("O CPF não pode ser nulo!");
		}
	}
	private void preencherCampos(PessoaFisica pessoaF) {
		String  nomeField = this.fpf.getNomeField().getText();
		String senhaField = this.fpf.getSenhaField().getText();
		String loginField = this.fpf.getLoginField().getText();
		String idField = this.fpf.getIdField().getText();
		String cpfField = this.fpf.getCpfField().getText();
		String  sexo = this.fpf.getSexoFradio().isSelected()?this.fpf.getSexoFradio().getText():this.fpf.getSexoMradio().getText();
		Date dataNascimentoFieldv = this.fpf.getDataNascimentoField().getDate();
		String nHabilitacaoField = this.fpf.getnHabilitacaoField().getText();
		Date dataVencHabField = this.fpf.getDataVencHabField().getDate();
		String ruaField = this.fpf.getRuaField().getText();
		int numeroField = this.fpf.getNumeroField().getText().length()<=0?0:Integer.parseInt( this.fpf.getNumeroField().getText());
		String bairroField = this.fpf.getBairroField().getText();
		String cepField =  this.fpf.getCepField().getText();
		String ufField = this.fpf.getUfField().getText();
		String cidadeField = this.fpf.getCidadeField().getText();
		String buscarField = this.fpf.getBuscarField().getText();
		Endereco e = new Endereco();
		pessoaF.setEndereco(e);
		e.setRua(ruaField);
		e.setNumero(numeroField);
		e.setCep(cepField);
		e.setUf(ufField);
		e.setCidade(cidadeField);
		e.setBairro(bairroField);
		if(idField.trim().length()>0) {//então eh update
			pessoaF.setId(Integer.parseInt(idField));
			pessoaF.getEndereco().setId(Integer.parseInt(this.fpf.getIdEnd().getText()));
		}else
			this.fpf.getIdEnd().setText("");//OLHAR                                     OLHAR      AKIIIIIIIIIIIIIIIIIIIIIIII
		pessoaF.setNome(nomeField);
		pessoaF.setSenha(senhaField);
		pessoaF.setLogin(loginField);
		pessoaF.setCpf(cpfField);
		pessoaF.setSexo(sexo);
		pessoaF.setnHabilitacao(nHabilitacaoField);
		pessoaF.setDiscriminador(Discriminador.PF.getValor());//Importante
		pessoaF.setDataNascimento(dataNascimentoFieldv);
		pessoaF.setDataVencHabilitacao(dataVencHabField);
		pessoaF.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
	}
	private void carregarPF() {
		PessoaFisicaDao daoPF = PessoaFisicaDao.getInstance();
		if(this.fpf.getBuscarField().getText().trim().length()<=0)
			JOptionPane.showMessageDialog(null, "Digite um ID para Busca!");
		else {
			try {
				int id = Integer.parseInt(this.fpf.getBuscarField().getText().trim());
				PessoaFisica p = daoPF.findById(PessoaFisica.class,id);
				if(p!=null) {
					limparCampos();
					this.fpf.getOperacaoLabel().setText("Modo Update");
					this.fpf.getOperacaoLabel().setForeground(Color.blue);
					this.fpf.getCpfField().setText("");
					preencherBusca(p);
				}else
					JOptionPane.showMessageDialog(null,"Nenhum cliente encontrado para o id:"+id);
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
	private void preencherBusca(PessoaFisica pessoaF) {	
		this.fpf.getNomeField().setText(pessoaF.getNome());
		this.fpf.getSenhaField().setText(pessoaF.getSenha());
		this.fpf.getLoginField().setText(pessoaF.getLogin());
		this.fpf.getIdField().setText(pessoaF.getId()+"");
		this.fpf.getCpfField().setCaretPosition(0);
		//this.fpf.getCpfField().setText(pessoaF.getCpf());
		this.fpf.getCpfField().setText(pessoaF.getCpf().replace(".","").replace("-",""));
		if(pessoaF.getSexo().equals("F"))this.fpf.getSexoFradio().setSelected(true);else this.fpf.getSexoMradio().setSelected(true);
		this.fpf.getDataNascimentoField().setDate(pessoaF.getDataNascimento());
		this.fpf.getnHabilitacaoField().setText(pessoaF.getnHabilitacao());
		this.fpf.getDataVencHabField().setDate(pessoaF.getDataVencHabilitacao());
		this.fpf.getRuaField().setText(pessoaF.getEndereco().getRua());
		this.fpf.getNumeroField().setText(pessoaF.getEndereco().getNumero()!=null?pessoaF.getEndereco().getNumero()+"":"");
		this.fpf.getBairroField().setText(pessoaF.getEndereco().getBairro());
		this.fpf.getCepField().setText(pessoaF.getEndereco().getCep());
		this.fpf.getUfField().setText(pessoaF.getEndereco().getUf());
		this.fpf.getCidadeField().setText(pessoaF.getEndereco().getCidade());
		this.fpf.getIdEnd().setText(pessoaF.getEndereco().getId()+"");
		
	}
	private void carregarAll() {
		if(this.fpf.getBuscarField().getText().replace(" ","").length()<=0) {
			PessoaFisicaDao daoPF = PessoaFisicaDao.getInstance();
			limparCampos();
			try {
				pessoasF = daoPF.findAll(PessoaFisica.class);
				if(pessoasF!=null) {
					JOptionPane.showMessageDialog(null,"Foram Encontrados: "+pessoasF.size()+" Clientes!");
					this.preencherBusca(pessoasF.get(0));
					indiceCorrente = 0;
					this.fpf.getOperacaoLabel().setText("Modo Update");
					this.fpf.getOperacaoLabel().setForeground(Color.blue);
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
		this.fpf.getOperacaoLabel().setText("Modo Inserção");
		this.fpf.getOperacaoLabel().setForeground(Color.red);
		this.fpf.getIdEnd().setText("");
		this.pessoasF = null;
		indiceCorrente = 0;
		LimparCampo.limparCamposDialog(fpf.getContentPane().getComponents());
	}
	private void direita() {
		if(pessoasF!=null) {
			if(indiceCorrente >= pessoasF.size()-1) {
				indiceCorrente = 0;//vai para o primeiro!
			}else
				indiceCorrente++;
			this.preencherBusca(pessoasF.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
		
	}
	private void esquerda() {
		if(pessoasF!=null) {
			if(indiceCorrente==0) {
				indiceCorrente = pessoasF.size()-1;
			}else
				indiceCorrente--;
			this.preencherBusca(pessoasF.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
	}
}
