package br.com.palves.pbd.controller;

import java.awt.Color;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;
import br.com.palves.pbd.model.bin.Configuracao;
import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.Reserva;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.CategoriaCargaDao;
import br.com.palves.pbd.model.dao.CategoriaDao;
import br.com.palves.pbd.model.dao.CategoriaPassageiroDao;
import br.com.palves.pbd.model.dao.ConfiguracaoDao;
import br.com.palves.pbd.model.dao.FilialDao;
import br.com.palves.pbd.model.dao.ReservaDao;
import br.com.palves.pbd.view.FormularioCrudReserva;

public class ControllerCrudReserva {
	private FormularioCrudReserva fpv =  new FormularioCrudReserva() ;
	private List<Reserva> reservas  = null;
	private int indiceCorrente;
	public ControllerCrudReserva() {
		this.fpv.setVisible(true);
		this.fpv.getSalvarButton().addActionListener(ActionEvent -> salvarPJ());	
		TratadorDeMascara.soNumero(this.fpv.getBuscarField());
		TratadorDeMascara.soNumero(this.fpv.getIdField());
		TratadorDeMascara.mascaraHora(this.fpv.getHoraField());
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
		this.fpv.getSituacaoCombo().setEnabled(false);
		this.carregarConfiguracaoes();
	}
	private void salvarPJ()
	{
		ReservaDao daoPJ = ReservaDao.getInstance();
		try {
			this.validacoesDeNull();
			Reserva pessoaJ = new Reserva();
			this.preencherCampos(pessoaJ);
			daoPJ.persistOrMerge(pessoaJ);
			JOptionPane.showMessageDialog(null,(this.fpv.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
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
		if(this.fpv.getDataRetirada().getDate()==null)
			throw new ValidacaoException("A Data não pode ser nula!");
		if(this.fpv.getHoraField().getText().replace(":","").replace(" ", "").length()<4)
			throw new ValidacaoException("Hora Inválida!");
		if(this.fpv.getCodFilial().getText().length()<=0)
			throw new ValidacaoException("Eh necessario uma filial");
		if(this.fpv.getCodCategoriaField().getText().length()<=0)
			throw new ValidacaoException("Eh nescessario uma Categoria");
		FilialDao f = FilialDao.getInstance();
		try {
			Filial fl=f.findById(Filial.class,Integer.parseInt(this.fpv.getCodFilial().getText()));
			String horaInicio = TratadorDeMascara.converterHoraString(fl.getHoraInicioExpediente()).replace(":","");
			String horaFim = TratadorDeMascara.converterHoraString(fl.getHoraFimExpediente()).replace(":","");
			int hi = Integer.parseInt(horaInicio);
			int hf = Integer.parseInt(horaFim);
			int ha = Integer.parseInt(fpv.getHoraField().getText().replace(":",""));
			if(ha>= hi && ha<=hf)
				System.out.println();
			else
				throw new ValidacaoException("A hora de retirada tem que estar dentro do expediente!");

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ValidacaoException("Filial invalida!");
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ValidacaoException("Filial Não existe no banco!");

		}
	}
	private void preencherCampos(Reserva reserva) throws DaoException {
		try {
			String idField = this.fpv.getIdField().getText();
			Date horaRetirada = TratadorDeMascara.unirDataHora(this.fpv.getDataRetirada().getDate(),this.fpv.getHoraField().getText().length()<=0?"00:00":this.fpv.getHoraField().getText().replace(" ",""));
			String situacao = this.fpv.getSituacaoCombo().getSelectedItem().toString();
			double valorKlivre = Double.parseDouble(this.fpv.getkLivreField().getText().replace(",","."));
			double valorkControle = Double.parseDouble(this.fpv.getkControleField().getText().replace(",","."));
			int  idFilial = Integer.parseInt(this.fpv.getCodFilial().getText());
			int idCategoria = Integer.parseInt(this.fpv.getCodCategoriaField().getText());
			Date realizacao = new Date();
			if(idField.trim().length()>0) {//então eh update
				ReservaDao r = ReservaDao.getInstance();
				Reserva rs = r.findById(Reserva.class,Integer.parseInt(idField));
				realizacao = rs.getDataHoraReserva();
				Pessoa p = new PessoaFisica();
				valorKlivre = rs.getValorDiariaKlivre();
				valorkControle = rs.getValorDiariaKcontrole();
				//rs = null;
				reserva.setPessoa(rs.getPessoa());
				reserva.setId(Integer.parseInt(idField));
				//reserva.setDataHoraReserva(rs.getDataHoraReserva());
			}else {
				Pessoa p = new PessoaFisica();
				//reserva.setPessoa(p);
			}
			Filial f = new Filial();
			f.setId(idFilial);
			Categoria cat = new Categoria();
			cat.setId(idCategoria);
			reserva.setFilial(f);
			reserva.setCategoria(cat);
			reserva.setDataHoraRetirada(horaRetirada);
			reserva.setDataHoraReserva(realizacao);
			reserva.setValorDiariaKcontrole(valorkControle);
			reserva.setValorDiariaKlivre(valorKlivre);
			reserva.setSituacao(situacao);
		}catch(java.lang.NumberFormatException r) {
			throw new DaoException("Existem Caracteres Inválidos!");
		}
	}
	private void carregarPJ() {
		ReservaDao daoPJ = ReservaDao.getInstance();
		if(this.fpv.getBuscarField().getText().trim().length()<=0)
			JOptionPane.showMessageDialog(null, "Digite um ID para Busca!");
		else {
			try {
				int id = Integer.parseInt(this.fpv.getBuscarField().getText().trim());
				Reserva p = daoPJ.findById(Reserva.class,id);
				if(p!=null) {

					limparCampos();
					this.fpv.getSituacaoCombo().setEnabled(true);
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
	private void preencherBusca(Reserva p) {	
		this.fpv.getDataRetirada().setDate(p.getDataHoraRetirada());
		this.fpv.getHoraField().setText(TratadorDeMascara.converterHoraString(p.getDataHoraRetirada()));
		this.fpv.getSituacaoCombo().setSelectedItem(p.getSituacao());
		this.fpv.getkControleField().setText(p.getValorDiariaKcontrole()+"");
		this.fpv.getkLivreField().setText(p.getValorDiariaKlivre()+"");
		this.fpv.getClienteField().setText(p.getPessoa()!=null?p.getPessoa().getNome():"");
		this.fpv.getIdField().setText(p.getId()+"");
		this.fpv.getFilialAtualCombo().setSelectedItem(p.getFilial().getNome());
		this.fpv.getCategoriaCombo().setSelectedItem(p.getCategoria().getNome());

	}
	private void carregarAll() {
		if(this.fpv.getBuscarField().getText().replace(" ","").length()<=0) {
			ReservaDao daoPJ = ReservaDao.getInstance();
			limparCampos();
			try {
				reservas = daoPJ.findAll(Reserva.class);
				if(reservas!=null) {
					this.fpv.getSituacaoCombo().setEnabled(true);
					JOptionPane.showMessageDialog(null,"Foram Encontrados: "+reservas.size()+" Reservas!");
					this.preencherBusca(reservas.get(0));
					indiceCorrente = 0;
					this.fpv.getOperacaoLabel().setText("Modo Update");
					this.fpv.getOperacaoLabel().setForeground(Color.blue);
					//this.fpf.getCpfField().setText("");
				}else
					JOptionPane.showMessageDialog(null,"Nenhuma Reserva encontrada!");
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
		this.reservas = null;
		indiceCorrente = 0;
		LimparCampo.limparCamposDialog(fpv.getContentPane().getComponents());
		this.fpv.getFilialTextArea().setText("");
		this.fpv.getCategoriaTextArea().setText("");
		atualizarComboFilial();
		atualizarComboCategoria();
		this.fpv.getSituacaoCombo().setEnabled(false);
		this.fpv.getSituacaoCombo().setSelectedItem("EM ESPERA");
		this.carregarConfiguracaoes();
	}
	private void direita() {
		if(reservas!=null) {
			if(indiceCorrente >= reservas.size()-1) {
				indiceCorrente = 0;//vai para o primeiro!
			}else
				indiceCorrente++;
			this.preencherBusca(reservas.get(indiceCorrente));
			System.out.println(indiceCorrente);
		}

	}
	private void esquerda() {
		if(reservas!=null) {
			if(indiceCorrente==0) {
				indiceCorrente = reservas.size()-1;
			}else
				indiceCorrente--;
			this.preencherBusca(reservas.get(indiceCorrente));
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
			JOptionPane.showMessageDialog(null,"Não foi possivel atualizar esta Reserva!");
			e.printStackTrace();
		}	
	}
	public  void carregarConfiguracaoes() {
		ConfiguracaoDao c =  ConfiguracaoDao.getInstance();
		Configuracao cf;
		try {
			cf = c.buscarUltimo();
			fpv.getkControleField().setText(cf ==null?"0":cf.getDiariaKcontrole()+"");
			fpv.getkLivreField().setText(cf ==null?"0":cf.getDiariaKlivre()+"");
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
