package br.com.palves.pbd.controller;

import java.awt.Color;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Veiculo;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.CategoriaCargaDao;
import br.com.palves.pbd.model.dao.CategoriaDao;
import br.com.palves.pbd.model.dao.CategoriaPassageiroDao;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.VeiculoDao;
import br.com.palves.pbd.view.FormularioCrudVeiculo;

public class ControllerCrudVeiculo {
	private FormularioCrudVeiculo fpv =  new FormularioCrudVeiculo() ;
	private List<Veiculo> veiculos  = null;
	private int indiceCorrente;
	public ControllerCrudVeiculo() {
		this.fpv.setVisible(true);
		this.fpv.getSalvarButton().addActionListener(ActionEvent -> salvarPJ());	
		TratadorDeMascara.soNumero(this.fpv.getBuscarField());
		TratadorDeMascara.soNumero(this.fpv.getIdField());
		TratadorDeMascara.soNumero(this.fpv.getAnoFabricacaoField());
		TratadorDeMascara.soNumero(this.fpv.getNumeroPortasField());
		TratadorDeMascara.soNumero(this.fpv.getAnoModeloField());
		this.fpv.getIdField().setEditable(false);
		this.fpv.getIrButton().addActionListener(ActionEvent -> carregarPJ());//Busca por ID!
		this.fpv.getLimparButton().addActionListener(ActionEvent -> limparCampos());
		this.fpv.getAllButton().addActionListener(ActionEvent -> carregarAll());
		this.fpv.getDireitaButton().addActionListener(ActionEvent -> direita());
		this.fpv.getEsquerdaButton().addActionListener(ActionEvent -> esquerda());
		this.fpv.getFilialAtualCombo().addItemListener(ItemListener->itemComboFilial());
		this.fpv.getCategoriaCombo().addItemListener(ItemListener->itemComboCategoria());
		this.atualizarComboFilial();
		this.atualizarComboCategoria();
	}
	private void salvarPJ()
	{
		VeiculoDao daoPJ = VeiculoDao.getInstance();
		try {
			this.validacoesDeNull();
			Veiculo pessoaJ = new Veiculo();
			this.preencherCampos(pessoaJ);
			daoPJ.persistOrMerge(pessoaJ);
			JOptionPane.showMessageDialog(null,pessoaJ.getNome()+(this.fpv.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
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
		if(this.fpv.getTamanhoCombo().getSelectedItem().toString().length()<=0)
			throw new ValidacaoException("O Tamanho não pode ser nulo!");
	}
	private void preencherCampos(Veiculo veiculo) {
		String nome = this.fpv.getNomeField().getText();
		String idField = this.fpv.getIdField().getText();
		String numeroChassi = this.fpv.getNumeroChassiField().getText();
		int numeroPortas = this.fpv.getNumeroPortasField().getText().length()<=0?0:Integer.parseInt(this.fpv.getNumeroPortasField().getText());
		String g = this.fpv.getGasolinaCheck().isSelected()?this.fpv.getGasolinaCheck().getText():"NOT";
		String e = this.fpv.getEtanolCheck().isSelected()?this.fpv.getEtanolCheck().getText():"NOT";
		String d = this.fpv.getDieselCheck().isSelected()?this.fpv.getDieselCheck().getText():"NOT";
		String bio = this.fpv.getBiocombustivelCheck().isSelected()?this.fpv.getBiocombustivelCheck().getText():"NOT";
		
		String combustivel = (g.equals("NOT")?"":g) + (e.equals("NOT")?"":";"+e) + (d.equals("NOT")?"":";"+d) + (bio.equals("NOT")?"":";"+bio);
		String tamanho =  this.fpv.getTamanhoCombo().getSelectedItem().toString();
		String torque =  this.fpv.getTorqueMotorField().getText();
		String numeroMotor =  this.fpv.getNumeroMotorField().getText();
		String modelo =  this.fpv.getModeloField().getText();
		int anoModelo =  this.fpv.getAnoModeloField().getText().replace(" ","").length()<=0?0:Integer.parseInt(this.fpv.getAnoModeloField().getText().replace(" ",""));
		String cor = this.fpv.getCorField().getText();
		String fabricante = this.fpv.getFabricanteField().getText();
		int anoFabricacao = this.fpv.getAnoFabricacaoField().getText().replace(" ","").length()<=0?0:Integer.parseInt(this.fpv.getAnoFabricacaoField().getText().replace(" ",""));
		Date horaRevisao = TratadorDeMascara.converterStringHora(this.fpv.getHorasRevisaoField().getText().length()<=0?"00:00":this.fpv.getHorasRevisaoField().getText().replace(" ",""));
		int  idFilial = Integer.parseInt(this.fpv.getCodFilial().getText());
		int idCategoria = Integer.parseInt(this.fpv.getCodCategoriaField().getText());
		if(idField.trim().length()>0) {//então eh update
			veiculo.setId(Integer.parseInt(idField));
		}
		Filial f = new Filial();
		f.setId(idFilial);
		Categoria cat = new Categoria();
		cat.setId(idCategoria);
		veiculo.setFilialAtual(f);
		veiculo.setCategoria(cat);

		veiculo.setNome(nome);
		veiculo.setNumeroChassi(numeroChassi);
		veiculo.setnPorta(numeroPortas);
		veiculo.setTipoCombustivel(combustivel);
		veiculo.setTamanho(tamanho);
		veiculo.setTorqueMotor(torque);
		veiculo.setNumeroMotor(numeroMotor);
		veiculo.setModelo(modelo);
		veiculo.setAnoModelo(anoModelo);
		veiculo.setCor(cor);
		veiculo.setFabricante(fabricante);
		veiculo.setAnoFabricao(anoFabricacao);
		veiculo.setHoraRevisao(horaRevisao);
		veiculo.setStatus(StatusEnum.ATIVO.getValor());//tem que ser um campo
	}
	private void carregarPJ() {
		VeiculoDao daoPJ = VeiculoDao.getInstance();
		if(this.fpv.getBuscarField().getText().trim().length()<=0)
			JOptionPane.showMessageDialog(null, "Digite um ID para Busca!");
		else {
			try {
				int id = Integer.parseInt(this.fpv.getBuscarField().getText().trim());
				Veiculo p = daoPJ.findById(Veiculo.class,id);
				if(p!=null) {
					limparCampos();
					this.fpv.getOperacaoLabel().setText("Modo Update");
					this.fpv.getOperacaoLabel().setForeground(Color.blue);
					preencherBusca(p);
				}else
					JOptionPane.showMessageDialog(null,"Nenhum Veiculo encontrado para o id: "+id);
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
	private void preencherBusca(Veiculo p) {	
		this.fpv.getNomeField().setText(p.getNome());
		this.fpv.getIdField().setText(p.getId()+"");
		this.fpv.getNumeroChassiField().setText(p.getNumeroChassi());
		this.fpv.getNumeroPortasField().setText(p.getnPorta()+"");
		String [] comb = p.getTipoCombustivel().split(";");
		this.fpv.getGasolinaCheck().setSelected(false);
		this.fpv.getEtanolCheck().setSelected(false);
		this.fpv.getDieselCheck().setSelected(false);
		this.fpv.getBiocombustivelCheck().setSelected(false);
		for (int i = 0;i <comb.length;i++) {
			if(comb[i].equalsIgnoreCase("Gasolina"))
				this.fpv.getGasolinaCheck().setSelected(true);
			else if(comb[i].equalsIgnoreCase("Etanol"))
				this.fpv.getEtanolCheck().setSelected(true);
			else if(comb[i].equalsIgnoreCase("Diesel"))
				this.fpv.getDieselCheck().setSelected(true);
			else if(comb[i].equalsIgnoreCase("Biocombustivel"))
				this.fpv.getBiocombustivelCheck().setSelected(true);
		}
		this.fpv.getTamanhoCombo().setSelectedItem(p.getTamanho());
		this.fpv.getTorqueMotorField().setText(p.getTorqueMotor());
		this.fpv.getNumeroMotorField().setText(p.getNumeroMotor());
		this.fpv.getModeloField().setText(p.getModelo());
		this.fpv.getAnoModeloField().setText(p.getAnoModelo()+"");
		this.fpv.getCorField().setText(p.getCor());
		this.fpv.getFabricanteField().setText(p.getFabricante());
		this.fpv.getAnoFabricacaoField().setText(p.getAnoFabricao()+"");
		this.fpv.getHorasRevisaoField().setText(TratadorDeMascara.converterHoraString(p.getHoraRevisao()));

		this.fpv.getFilialAtualCombo().setSelectedItem(p.getFilialAtual().getNome());
		this.fpv.getCategoriaCombo().setSelectedItem(p.getCategoria().getNome());

	}
	private void carregarAll() {
		if(this.fpv.getBuscarField().getText().replace(" ","").length()<=0) {
			VeiculoDao daoPJ = VeiculoDao.getInstance();
			limparCampos();
			try {
				veiculos = daoPJ.findAll(Veiculo.class);
				if(veiculos!=null) {
					JOptionPane.showMessageDialog(null,"Foram Encontrados: "+veiculos.size()+" Veiculos!");
					this.preencherBusca(veiculos.get(0));
					indiceCorrente = 0;
					this.fpv.getOperacaoLabel().setText("Modo Update");
					this.fpv.getOperacaoLabel().setForeground(Color.blue);
					//this.fpf.getCpfField().setText("");
				}else
					JOptionPane.showMessageDialog(null,"Nenhum Veiculo encontrado!");
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			JOptionPane.showMessageDialog(null,"O Campo de Busca deve estar vazio!");
	}
	private void limparCampos() {
		this.fpv.getOperacaoLabel().setText("Modo Inserção");
		this.fpv.getOperacaoLabel().setForeground(Color.red);
		this.veiculos = null;
		indiceCorrente = 0;
		LimparCampo.limparCamposDialog(fpv.getContentPane().getComponents());
		this.fpv.getFilialTextArea().setText("");
		this.fpv.getCategoriaTextArea().setText("");
		atualizarComboFilial();
		atualizarComboCategoria();
	}
	private void direita() {
		if(veiculos!=null) {
			if(indiceCorrente >= veiculos.size()-1) {
				indiceCorrente = 0;//vai para o primeiro!
			}else
				indiceCorrente++;
			this.preencherBusca(veiculos.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}

	}
	private void esquerda() {
		if(veiculos!=null) {
			if(indiceCorrente==0) {
				indiceCorrente = veiculos.size()-1;
			}else
				indiceCorrente--;
			this.preencherBusca(veiculos.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}
	}
	private void atualizarComboFilial() {
		if(this.fpv.getFilialAtualCombo().getModel().getSize() > 0) {
			this.fpv.getFilialAtualCombo().removeAllItems();
			System.gc();
		}
		FilialDao fd = FilialDao.getInstance();
		try {
			
			List<Filial>lts = fd.findAll(Filial.class);//Criar um native query
			for(int i = 0;i<lts.size();i++) {
				fpv.getFilialAtualCombo().addItem(lts.get(i).getNome());
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar as filiais no combo!");
			e.printStackTrace();
		}	
	}
	private void atualizarComboCategoria() {
		if(this.fpv.getCategoriaCombo().getModel().getSize() > 0) {
			this.fpv.getCategoriaCombo().removeAllItems();
			System.gc();
		}
		CategoriaDao fd = CategoriaDao.getInstance();
		try {
			
			List<Categoria>lts = fd.findAll(Categoria.class);//Criar um native query
			for(int i = 0;i<lts.size();i++) {
				fpv.getCategoriaCombo().addItem(lts.get(i).getNome());
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar as Categorias no combo!");
			e.printStackTrace();
		}	
	}
	private void itemComboFilial() {
		FilialDao fd = FilialDao.getInstance();
		//essa busca eh por nome
		try {
			if(this.fpv.getFilialAtualCombo().getModel().getSize()>0) {
				Object[ ] f = fd.buscaIdPorNome(this.fpv.getFilialAtualCombo().getSelectedItem().toString());//Criar um native query
				if(f==null)
					throw new DaoException("Sem filiais!");
				fpv.getFilialTextArea().setText("");
				Filial fl = fd.findById(Filial.class,Integer.parseInt(f[1]+""));
				this.fpv.getCodFilial().setText(f[1]+"");
				fpv.getFilialTextArea().append("Exp: "+TratadorDeMascara.converterHoraString(fl.getHoraInicioExpediente())+" às "+TratadorDeMascara.converterHoraString(fl.getDataFimExpediente())+"\n");
				fpv.getFilialTextArea().append("Cidade: "+fl.getEndereco().getCidade()+"\n");
				fpv.getFilialTextArea().append("Rua: "+fl.getEndereco().getRua()+"\n");
				fpv.getFilialTextArea().append("Numero: "+fl.getEndereco().getNumero()+"\n");
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar esta filial");
			e.printStackTrace();
		}	
	}
	private void itemComboCategoria() {
		CategoriaDao fd = CategoriaDao.getInstance();
		//essa busca eh por nome
		try {
			if(this.fpv.getCategoriaCombo().getModel().getSize()>0) {
				Object[ ] f = fd.buscarIdeDiscriminadorPorNome(this.fpv.getCategoriaCombo().getSelectedItem().toString());//Criar um native query
				if(f==null)
					throw new DaoException("Sem filiais!");
				else if(f[1].toString().equalsIgnoreCase(Discriminador.CN.getValor())){
					Categoria fl = fd.findById(Categoria.class,Integer.parseInt(f[0]+""));
					this.fpv.getCodCategoriaField().setText(f[0]+"");
					this.fpv.getCategoriaTextArea().setText("");
					this.fpv.getCategoriaTextArea().setText(fl.toString().replace(",","\n").replace("[","\n").replace("]","\n").replace("=",": ").toUpperCase().replace("TRUE","Sim").replace("FALSE","Não"));
				}
				else if(f[1].toString().equalsIgnoreCase(Discriminador.CP.getValor())){
					CategoriaPassageiro fl = CategoriaPassageiroDao.getInstance().findById(CategoriaPassageiro.class,Integer.parseInt(f[0]+""));
					this.fpv.getCodCategoriaField().setText(f[0]+"");
					this.fpv.getCategoriaTextArea().setText(fl.toString().replace(",","\n").replace("[","\n").replace("]","\n").replace("=",": ").toUpperCase().replace("TRUE","Sim").replace("FALSE","Não"));
				}
				else if(f[1].toString().equalsIgnoreCase(Discriminador.CG.getValor())){
					CategoriaCarga fl = CategoriaCargaDao.getInstance().findById(CategoriaCarga.class,Integer.parseInt(f[0]+""));
					this.fpv.getCodCategoriaField().setText(f[0]+"");
					this.fpv.getCategoriaTextArea().setText(fl.toString().replace(",","\n").replace("[","\n").replace("]","\n").replace("=",": ").toUpperCase().replace("TRUE","Sim").replace("FALSE","Não"));
				}
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar esta Categoria");
			e.printStackTrace();
		}	
	}
}
