package br.com.palves.pbd.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.FuncionarioDao;
import br.com.palves.pbd.view.FormularioCrudFuncionario;

public class ControllerCrudFuncionario {
	private FormularioCrudFuncionario fpf =  new FormularioCrudFuncionario() ;
	private List<Funcionario> funcionario  = null;
	private int indiceCorrente;
	public ControllerCrudFuncionario() {
		this.fpf.setVisible(true);
		this.fpf.getSalvarButton().addActionListener(ActionEvent -> salvarFunc());	
		TratadorDeMascara.soNumero(this.fpf.getBuscarField());
		TratadorDeMascara.soNumero(this.fpf.getIdField());
		this.fpf.getIdField().setEditable(false);
		this.fpf.getIrButton().addActionListener(ActionEvent -> carregarFunc());//Busca por ID!
		this.fpf.getLimparButton().addActionListener(ActionEvent -> limparCampos());
		this.fpf.getAllButton().addActionListener(ActionEvent -> carregarAll());
		this.fpf.getDireitaButton().addActionListener(ActionEvent -> direita());
		this.fpf.getEsquerdaButton().addActionListener(ActionEvent -> esquerda());
		this.fpf.getFilialCombo().addItemListener(ItemListener->itemCombo());
		this.atualizarComboFilial();
	}
	private void salvarFunc()
	{
		FuncionarioDao daoFunc = FuncionarioDao.getInstance();
		try {
			this.validacoesDeNull();
			Funcionario func = new Funcionario();
			this.preencherCampos(func);
			daoFunc.persistOrMerge(func);
			JOptionPane.showMessageDialog(null,func.getNome()+(this.fpf.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
			this.limparCampos();
		} 
		catch (DaoException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage());
			e1.printStackTrace();
		}catch(ValidacaoException e3) {
			JOptionPane.showMessageDialog(null,e3.getMessage());
			e3.printStackTrace();
		}catch(java.lang.NumberFormatException e4) {
			JOptionPane.showMessageDialog(null,"Salário Inválido!");
			e4.printStackTrace();
		}
	}	
	private void validacoesDeNull() throws ValidacaoException {
		if(this.fpf.getSenhaField().getText().trim().length()<6 || this.fpf.getSenhaField().getText().length()>11 )
			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(this.fpf.getNomeField().getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		if(this.fpf.getLoginField().getText().length()<=0)
			throw new ValidacaoException("O Login não pode ser nulo!");
		if(this.fpf.getCpfField().getText().replace(" ","").trim().length()<=3) {
			throw new ValidacaoException("O CPF não pode ser nulo!");
		}
	}
	private void preencherCampos(Funcionario func) throws NumberFormatException{
		String  nomeField = this.fpf.getNomeField().getText();
		String senhaField = EncriptaDecriptaApacheCodec.codificaBase64Encoder(this.fpf.getSenhaField().getText());
		String loginField = this.fpf.getCargoBox().getSelectedItem().toString()+"-"+this.fpf.getLoginField().getText();
		String idField = this.fpf.getIdField().getText();
		String cpfField = this.fpf.getCpfField().getText();
		String  sexo = this.fpf.getSexoFradio().isSelected()?this.fpf.getSexoFradio().getText():this.fpf.getSexoMradio().getText();
		String cargo = this.fpf.getCargoBox().getSelectedItem().toString();
		float salario = Float.parseFloat(this.fpf.getSalarioField().getText().trim().replace(" ","").replace(",","."));

		if(idField.trim().length()>0) {//então eh update
			func.setId(Integer.parseInt(idField));
			loginField = this.fpf.getLoginField().getText();
			//func.getEndereco().setId(Integer.parseInt(this.fpf.getIdEnd().getText()));
		}
//		else
//			this.fpf.getIdEnd().setText("");//OLHAR                                     OLHAR      AKIIIIIIIIIIIIIIIIIIIIIIII
		func.setNome(nomeField);
		func.setSenha(senhaField);
		func.setLogin(loginField);
		func.setCpf(cpfField);
		func.setSexo(sexo);
		func.setCargo(cargo);
		func.setSalario(salario);
		Filial f = new Filial();
		f.setId(Integer.parseInt(this.fpf.getCodFilialField().getText()));
		func.setFilial(f);
		func.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
	}
	private void carregarFunc() {
		FuncionarioDao daoFunc = FuncionarioDao.getInstance();
		if(this.fpf.getBuscarField().getText().trim().length()<=0)
			JOptionPane.showMessageDialog(null, "Digite um ID para Busca!");
		else {
			try {
				int id = Integer.parseInt(this.fpf.getBuscarField().getText().trim());
				Funcionario p = daoFunc.findById(Funcionario.class,id);
				if(p!=null) {
					limparCampos();
					this.fpf.getOperacaoLabel().setText("Modo Update");
					this.fpf.getOperacaoLabel().setForeground(Color.blue);
					this.fpf.getCpfField().setText("");
					preencherBusca(p);
				}else
					JOptionPane.showMessageDialog(null,"Nenhum Funcionario encontrado para o id:"+id);
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
	private void preencherBusca(Funcionario p) {	
		this.fpf.getNomeField().setText(p.getNome());
		this.fpf.getSenhaField().setText(EncriptaDecriptaApacheCodec.decodificaBase64Decoder(p.getSenha()));
		this.fpf.getLoginField().setText(p.getLogin());
		this.fpf.getIdField().setText(p.getId()+"");
		this.fpf.getCpfField().setCaretPosition(0);
		//this.fpf.getCpfField().setText(pessoaF.getCpf());
		this.fpf.getCpfField().setText(p.getCpf().replace(".","").replace("-",""));
		if(p.getSexo().equalsIgnoreCase("F"))this.fpf.getSexoFradio().setSelected(true);
			else this.fpf.getSexoMradio().setSelected(true);
		this.fpf.getCargoBox().setSelectedItem(p.getCargo()); 
		this.fpf.getSalarioField().setText(p.getSalario()+"");

		this.fpf.getCodFilialField().setText(p.getFilial().getId()+"");
		this.fpf.getFilialCombo().setSelectedItem(p.getFilial().getNome());
		
	}
	private void carregarAll() {
		if(this.fpf.getBuscarField().getText().replace(" ","").length()<=0) {
			FuncionarioDao daoPF = FuncionarioDao.getInstance();
			limparCampos();
			try {
				funcionario = daoPF.findAll(Funcionario.class);
				if(funcionario!=null) {
					JOptionPane.showMessageDialog(null,"Foram Encontrados: "+funcionario.size()+" Funcionarios!");
					this.preencherBusca(funcionario.get(0));
					indiceCorrente = 0;
					this.fpf.getOperacaoLabel().setText("Modo Update");
					this.fpf.getOperacaoLabel().setForeground(Color.blue);
					//this.fpf.getCpfField().setText("");
				}else
					JOptionPane.showMessageDialog(null,"Nenhum Funcionario encontrado!");
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
		this.funcionario = null;
		indiceCorrente = 0;
		LimparCampo.limparCamposDialog(fpf.getContentPane().getComponents());
		atualizarComboFilial();
	}
	private void direita() {
		if(funcionario!=null) {
			if(indiceCorrente >= funcionario.size()-1) {
				indiceCorrente = 0;//vai para o primeiro!
			}else
				indiceCorrente++;
			this.preencherBusca(funcionario.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
		
	}
	private void esquerda() {
		if(funcionario!=null) {
			if(indiceCorrente==0) {
				indiceCorrente = funcionario.size()-1;
			}else
				indiceCorrente--;
			this.preencherBusca(funcionario.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
	}
	private void atualizarComboFilial() {
		if(this.fpf.getFilialCombo().getModel().getSize() > 0) {
			this.fpf.getFilialCombo().removeAllItems();
			System.gc();
		}
		FilialDao fd = FilialDao.getInstance();
		try {
			List<Filial>lts = fd.findAll(Filial.class);//Criar um native query
			if(lts==null)
				return;
			for(int i = 0;i<lts.size();i++) {
				fpf.getFilialCombo().addItem(lts.get(i).getNome());
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar as filiais no combo!");
			e.printStackTrace();
		}	
	}
	private void itemCombo() {
		FilialDao fd = FilialDao.getInstance();
		//essa busca eh por nome
		try {
			if(this.fpf.getFilialCombo().getModel().getSize()>0) {
			Object[ ] f = fd.buscaIdPorNome(this.fpf.getFilialCombo().getSelectedItem().toString());//Criar um native query
			if(f==null)
				throw new DaoException("Sem filiais!");
			this.fpf.getCodFilialField().setText(f[1]+"");
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar esta filial");
			e.printStackTrace();
		}	
	}
}
