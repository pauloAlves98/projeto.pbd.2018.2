package br.com.palves.pbd.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.palves.pbd.enums.Discriminador;
import br.com.palves.pbd.enums.StatusEnum;
import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.exception.ValidacaoException;
import br.com.palves.pbd.model.bin.Categoria;
import br.com.palves.pbd.model.bin.CategoriaCarga;
import br.com.palves.pbd.model.bin.CategoriaPassageiro;
import br.com.palves.pbd.model.complemento.LimparCampo;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.CategoriaCargaDao;
import br.com.palves.pbd.model.dao.CategoriaDao;
import br.com.palves.pbd.model.dao.CategoriaPassageiroDao;
import br.com.palves.pbd.view.FormularioCrudCategoria;

public class ControllerCrudCategoria {
	private static FormularioCrudCategoria fpc =  new FormularioCrudCategoria() ;
	private List<Categoria> categorias  = null;
	private List<CategoriaPassageiro> categoriasCP  = null;
	private List<CategoriaCarga> categoriasCG  = null;
	private static int indiceCorrente;
	
	public ControllerCrudCategoria() {
		this.fpc.setVisible(true);
		this.fpc.getSalvarButton().addActionListener(ActionEvent -> salvar());	
		TratadorDeMascara.soNumero(fpc.getBuscarField());
		TratadorDeMascara.soNumero(fpc.getIdField());		
		TratadorDeMascara.soNumero(fpc.getnPassageirosField());
		//TratadorDeMascara.soNumero(fpc.getDostanciaEixosField());
		TratadorDeMascara.soNumero(fpc.getPotenciaMotorField());
		TratadorDeMascara.soNumero(fpc.getVolumeCombustivelField());
		fpc.getIdField().setEditable(false);
		this.fpc.getIrButton().addActionListener(ActionEvent -> carregar());//Busca por ID!
		this.fpc.getLimparButton().addActionListener(ActionEvent -> limparCampos());
		this.fpc.getAllButton().addActionListener(ActionEvent -> carregarAll());
		
		Navega n =  new Navega();
		this.fpc.getDireitaButton().addActionListener(ActionEvent ->{
			if(this.fpc.getCpRadio().isSelected())
				n.direita(categoriasCP);
			else if(this.fpc.getCgRadio().isSelected())
				n.direita(categoriasCG);
			else
				n.direita(categorias);
		});
		fpc.getEsquerdaButton().addActionListener(ActionEvent ->{
			if(this.fpc.getCpRadio().isSelected())
				n.esquerda(categoriasCP);
			else if(this.fpc.getCgRadio().isSelected())
				n.esquerda(categoriasCG);
			else
				n.esquerda(categorias);
		});
		
		//Eventos de em radio!
		fpc.getCpRadio().addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				limparCampos();
				if(fpc.getCpRadio().isSelected()) {
					habilita(fpc.getPanelCP());
					desabilita(fpc.getPanelCG());
				}
			}
		});
		fpc.getCgRadio().addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				limparCampos();
				if(fpc.getCgRadio().isSelected()) {
					habilita(fpc.getPanelCG());
					desabilita(fpc.getPanelCP());
				}
			}
		});
		fpc.getCnRadio().addItemListener(new ItemListener(){//Carga Normal Radio
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				limparCampos();
				if(fpc.getCnRadio().isSelected()) {
					desabilita(fpc.getPanelCP());//Desabilita os components
					desabilita(fpc.getPanelCG());
				}
			}
		});
		fpc.getCpRadio().setSelected(true);
	}
	private void salvar()
	{
		boolean cpb = false,cnb=false,cgb=false;
		if(this.fpc.getCpRadio().isSelected())
			cpb = true;
		else if(this.fpc.getCgRadio().isSelected())
			cgb = true;
		else
			cnb = true;
		try {
			this.validacoesDeNull();
			if(cpb) {
				CategoriaPassageiroDao cPdao = CategoriaPassageiroDao.getInstance();
				CategoriaPassageiro cp = new CategoriaPassageiro();
				this.preencherCampos(cp);
				cPdao.persistOrMerge(cp);
				JOptionPane.showMessageDialog(null,cp.getNome()+(this.fpc.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
				this.limparCampos();
			}
			else if(cgb) {
				CategoriaCargaDao cGDao =  CategoriaCargaDao.getInstance();
				CategoriaCarga cp = new CategoriaCarga();
				this.preencherCampos(cp);
				cGDao.persistOrMerge(cp);
				JOptionPane.showMessageDialog(null,cp.getNome()+(this.fpc.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
				this.limparCampos();
			}
			else {
				CategoriaDao cnDao = CategoriaDao.getInstance();
				Categoria cp = new Categoria();
				this.preencherCampos(cp);
				cnDao.persistOrMerge(cp);
				JOptionPane.showMessageDialog(null,cp.getNome()+(this.fpc.getOperacaoLabel().getText().equalsIgnoreCase("Modo Inserção")?" Inserido":" Atualizado")+" com sucesso!");
				this.limparCampos();
			}
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
		//		if(this.fpc.getSenhaField().getText().length()<6 || this.fpc.getSenhaField().getText().length()>11 )
		//			throw new ValidacaoException("A senha deve Conter entre 6 e 11 caracteres!");
		if(this.fpc.getNomeField().getText().length()<=0)
			throw new ValidacaoException("O nome não pode ser nulo!");
		//		if(this.fpc.getLoginField().getText().length()<=0)
		//			throw new ValidacaoException("O Login não pode ser nulo!");
		//		if(this.fpc.getCnpjField().getText().replace(" ","").trim().length()<=4) {
		//			throw new ValidacaoException("O CNPJ não pode ser nulo!");
		//		}
	}
	private void preencherCampos(Categoria categoria) throws DaoException {
		try {
			String  nome = this.fpc.getNomeField().getText();
			int  numeroPassageiros = fpc.getnPassageirosField().getText().replace(" ","").length()<=0?0:Integer.parseInt(this.fpc.getnPassageirosField().getText().replace(" ",""));
			Double valor =  this.fpc.getValorField().getText().replace(" ","").replace(",",".").length()<=0?0:Double.parseDouble(this.fpc.getValorField().getText().replace(" ","").replace(",","."));
			String horalimpeza = this.fpc.getHoraLimpeza().getText();
			String tipocambio = this.fpc.getCambioCombo().getSelectedItem().toString();
			String idField = this.fpc.getIdField().getText();
			boolean  arcondicionado = this.fpc.getSimArRadio().isSelected();
			boolean  dvd = this.fpc.getSimDvdRadio().isSelected();
			boolean radio=  this.fpc.getSimRadioRadio().isSelected();
			boolean mp3 = this.fpc.getSimMp3Radio().isSelected();
			boolean camerare = this.fpc.getSimCameraReRadio().isSelected();
			//CP
			String tipoAirBag = this.fpc.getAirBagCombo().getSelectedItem().toString();
			boolean dirAssistida = this.fpc.getSimDirecaoRadio().isSelected();
			boolean cintoSeguranca = this.fpc.getSimCintoRadio().isSelected();
			boolean rodaLiga = this.fpc.getSimLigaLeveRadio().isSelected();
			boolean poluicaoAr = this.fpc.getSimControlePoluicaoRadio().isSelected();
			//CG
			float capacidadeC = this.fpc.getCapacidadeCargaField().getText().replace(" ","").replace(",",".").length()<=0?0:Float.parseFloat(this.fpc.getCapacidadeCargaField().getText().replace(" ","").replace(",","."));
			int distanciaEixo = this.fpc.getDostanciaEixosField().getText().replace(" ","").length()<=0?0:Integer.parseInt(this.fpc.getDostanciaEixosField().getText().replace(" ",""));
			int potenciaMotor = this.fpc.getPotenciaMotorField().getText().replace(" ","").length()<=0?0:Integer.parseInt(this.fpc.getPotenciaMotorField().getText().replace(" ",""));
			int volumeCombustivel = this.fpc.getVolumeCombustivelField().getText().replace(" ","").length()<=0?0:Integer.parseInt(this.fpc.getVolumeCombustivelField().getText().replace(" ",""));
			String tipoEmbreagem = this.fpc.getTipoEmbreagemCombo().getSelectedItem().toString();
			String consumo = this.fpc.getConsumoKmField().getText();
			//
			categoria.setNome(nome);
			categoria.setnPassageiro(numeroPassageiros);
			categoria.setValor(valor);
			categoria.setHoraLimpeza(horalimpeza);
			categoria.setTipoCambio(tipocambio);
			categoria.setArCondicionado(arcondicionado);
			categoria.setDvd(dvd);
			categoria.setRadio(radio);
			categoria.setMp3(mp3);
			categoria.setCameraRe(camerare);
			categoria.setDiscriminador(Discriminador.CN.getValor());//Importante
			categoria.setSituacao(StatusEnum.ATIVO.getValor());//tem que ser um campo
			if(idField.trim().length()>0) {//então eh update
				categoria.setId(Integer.parseInt(idField));
			}
			if(categoria instanceof CategoriaPassageiro) {
				((CategoriaPassageiro) (categoria)).setTipoAirBag(tipoAirBag);
				((CategoriaPassageiro) (categoria)).setDirecaoAssistida(dirAssistida);
				((CategoriaPassageiro) (categoria)).setCintoSeguancaTraseiro(cintoSeguranca);
				((CategoriaPassageiro) (categoria)).setRodaLigaLeve(rodaLiga);
				((CategoriaPassageiro) (categoria)).setControlePoluicaoAr(poluicaoAr);
				categoria.setDiscriminador(Discriminador.CP.getValor());
			}else if(categoria instanceof CategoriaCarga) {
				((CategoriaCarga) (categoria)).setCapacidadeCarga(capacidadeC);
				((CategoriaCarga) (categoria)).setDistanciaEixo(distanciaEixo);
				((CategoriaCarga) (categoria)).setPotenciaMotor(potenciaMotor);
				((CategoriaCarga) (categoria)).setVolumeCombustivel(volumeCombustivel);
				((CategoriaCarga) (categoria)).setTipoEmbreagem(tipoEmbreagem);
				((CategoriaCarga) (categoria)).setConsumoKm(consumo);
				categoria.setDiscriminador(Discriminador.CG.getValor());
			}
		}catch(java.lang.NumberFormatException e) {
			throw new DaoException("Existem campos com caracteres iválidos!");
		}
	}
	private void carregar() {
		boolean cpb = false,cnb=false,cgb=false;
		if(this.fpc.getCpRadio().isSelected())
			cpb = true;
		else if(this.fpc.getCgRadio().isSelected())
			cgb = true;
		else
			cnb = true;
		if(this.fpc.getBuscarField().getText().trim().length()<=0)
			JOptionPane.showMessageDialog(null, "Digite um ID para Busca!");
		else {
			try {
				if(cpb) {
					CategoriaPassageiroDao daoPJ = CategoriaPassageiroDao.getInstance();
					int id = Integer.parseInt(this.fpc.getBuscarField().getText().trim());
					CategoriaPassageiro p = daoPJ.findById(CategoriaPassageiro.class,id);
					if(p!=null) {
						limparCampos();
						this.fpc.getOperacaoLabel().setText("Modo Update");
						this.fpc.getOperacaoLabel().setForeground(Color.blue);
						//this.fpc.getCnpjField().setText("");
						preencherBusca(p);
					}else
						JOptionPane.showMessageDialog(null,"Nenhuma Categoria encontrada para o id: "+id);
				}
				else if(cgb) {
					CategoriaCargaDao daoPJ = CategoriaCargaDao.getInstance();
					int id = Integer.parseInt(this.fpc.getBuscarField().getText().trim());
					CategoriaCarga p = daoPJ.findById(CategoriaCarga.class,id);
					if(p!=null) {
						limparCampos();
						this.fpc.getOperacaoLabel().setText("Modo Update");
						this.fpc.getOperacaoLabel().setForeground(Color.blue);
						//this.fpc.getCnpjField().setText("");
						preencherBusca(p);
					}else
						JOptionPane.showMessageDialog(null,"Nenhuma Categoria encontrada para o id: "+id);
				}
				else {
					CategoriaDao daoPJ = CategoriaDao.getInstance();
					int id = Integer.parseInt(this.fpc.getBuscarField().getText().trim());
					Categoria p = daoPJ.findById(Categoria.class,id);
					if(p!=null) {
						limparCampos();
						this.fpc.getOperacaoLabel().setText("Modo Update");
						this.fpc.getOperacaoLabel().setForeground(Color.blue);
						//this.fpc.getCnpjField().setText("");
						preencherBusca(p);
					}else
						JOptionPane.showMessageDialog(null,"Nenhuma Categoria encontrada para o id: "+id);
				}

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
	private static void preencherBusca(Categoria p) {	
		fpc.getNomeField().setText(p.getNome());
		fpc.getnPassageirosField().setText(p.getnPassageiro()+"");
		fpc.getValorField().setText(p.getnPassageiro()+"");
		fpc.getHoraLimpeza().setText(p.getHoraLimpeza()+"");
		fpc.getCambioCombo().setSelectedItem(p.getTipoCambio());
		fpc.getIdField().setText(p.getId()+"");
		if(p.isArCondicionado())
			fpc.getSimArRadio().setSelected(true);
		else
			fpc.getNaoArRadio().setSelected(true);
		if(p.isDvd())
			fpc.getSimDvdRadio().setSelected(true);
		else
			fpc.getNaoDvdRadio().setSelected(true);
		if(p.isRadio())
			fpc.getSimRadioRadio().setSelected(true);
		else
			fpc.getNaoRadioRadio().setSelected(true);
		if(p.isMp3())
			fpc.getSimMp3Radio().setSelected(true);
		else
			fpc.getNaoMp3Radio().setSelected(true);
		if(p.isCameraRe())
			fpc.getSimCameraReRadio().setSelected(true);
		else
			fpc.getNaoCameraReRadio().setSelected(true);
		if(p instanceof CategoriaPassageiro) {
			
			fpc.getAirBagCombo().setSelectedItem(((CategoriaPassageiro) (p)).getTipoAirBag());
			if(((CategoriaPassageiro) (p)).isDirecaoAssistida())
				fpc.getSimDirecaoRadio().setSelected(true);
			else
				fpc.getNaoDirecaoRadio().setSelected(true);
			if(((CategoriaPassageiro) (p)).isCintoSeguancaTraseiro())
				fpc.getSimCintoRadio().setSelected(true);
			else
				fpc.getNaoCintoRadio().setSelected(true);
			if(((CategoriaPassageiro) (p)).isRodaLigaLeve())
				fpc.getSimLigaLeveRadio().setSelected(true);
			else
				fpc.getNaoLigaLeveRadio().setSelected(true);
			if(((CategoriaPassageiro) (p)).isControlePoluicaoAr())
				fpc.getSimControlePoluicaoRadio().setSelected(true);
			else
				fpc.getNaoControlePoluicaoRadio().setSelected(true);
		}else if(p instanceof CategoriaCarga) {
			fpc.getCapacidadeCargaField().setText(((CategoriaCarga) (p)).getCapacidadeCarga()+"");
			fpc.getDostanciaEixosField().setText(((CategoriaCarga) (p)).getDistanciaEixo()+"");
			fpc.getPotenciaMotorField().setText(((CategoriaCarga) (p)).getPotenciaMotor()+"");
			fpc.getVolumeCombustivelField().setText(	((CategoriaCarga) (p)).getVolumeCombustivel()+"");;
			fpc.getTipoEmbreagemCombo().setSelectedItem(	((CategoriaCarga) (p)).getTipoEmbreagem());
			fpc.getConsumoKmField().setText(	((CategoriaCarga) (p)).getConsumoKm());
		}

	}
	private void carregarAll() {
		if(this.fpc.getBuscarField().getText().replace(" ","").length()<=0) {
			limparCampos();
			try {
				boolean cpb = false,cnb=false,cgb=false;
				if(this.fpc.getCpRadio().isSelected())
					cpb = true;
				else if(this.fpc.getCgRadio().isSelected())
					cgb = true;
				else
					cnb = true;
				if(cpb) {
					CategoriaPassageiroDao daoPJ = CategoriaPassageiroDao.getInstance();
					categoriasCP = daoPJ.findAll(CategoriaPassageiro.class);
					if(categoriasCP!=null) {
						JOptionPane.showMessageDialog(null,"Foram Encontrados: "+categoriasCP.size()+" Categorias!");
						this.preencherBusca(categoriasCP.get(0));
						indiceCorrente = 0;
						this.fpc.getOperacaoLabel().setText("Modo Update");
						this.fpc.getOperacaoLabel().setForeground(Color.blue);
						//this.fpf.getCpfField().setText("");
					}else
						JOptionPane.showMessageDialog(null,"Nenhuma Categoria encontrado!");
				}
				else if(cgb) {
					CategoriaCargaDao daoPJ = CategoriaCargaDao.getInstance();
					categoriasCG = daoPJ.findAll(CategoriaCarga.class);
					if(categoriasCG!=null) {
						JOptionPane.showMessageDialog(null,"Foram Encontrados: "+categoriasCG.size()+" Categorias!");
						this.preencherBusca(categoriasCG.get(0));
						indiceCorrente = 0;
						this.fpc.getOperacaoLabel().setText("Modo Update");
						this.fpc.getOperacaoLabel().setForeground(Color.blue);
						//this.fpf.getCpfField().setText("");
					}else
						JOptionPane.showMessageDialog(null,"Nenhuma Categoria encontrado!");
				}
				else {
					CategoriaDao daoPJ = CategoriaDao.getInstance();
					categorias = daoPJ.buscarTodosPorDiscriminador(Discriminador.CN.getValor());
					if(categorias!=null) {
						JOptionPane.showMessageDialog(null,"Foram Encontrados: "+categorias.size()+" Categorias!");
						this.preencherBusca(categorias.get(0));
						indiceCorrente = 0;
						this.fpc.getOperacaoLabel().setText("Modo Update");
						this.fpc.getOperacaoLabel().setForeground(Color.blue);
						//this.fpf.getCpfField().setText("");
					}else
						JOptionPane.showMessageDialog(null,"Nenhuma Categoria encontrado!");
				}

			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			JOptionPane.showMessageDialog(null,"O Campo de Busca deve estar vazio!");
	}
	private void limparCampos() {
		this.fpc.getOperacaoLabel().setText("Modo Inserção");
		this.fpc.getOperacaoLabel().setForeground(Color.red);
		this.categorias = null;
		this.categoriasCG = null;
		this.categoriasCP=null;
		indiceCorrente = 0;
		LimparCampo.limparCamposDialog(fpc.getContentPane().getComponents());
	}
	//	private void direita(List<Categoria>categoria) {
	//		if(categoria!=null) {
	//			if(indiceCorrente >= categoria.size()-1) {
	//				indiceCorrente = 0;//vai para o primeiro!
	//			}else
	//				indiceCorrente++;
	//			this.preencherBusca(categoria.get(indiceCorrente));
	//			System.out.println(indiceCorrente);
	//		}
	//
	//	}
	//	private void esquerda(List<T> categoria) {
	//		if(categoria!=null) {
	//			if(indiceCorrente==0) {
	//				indiceCorrente = categoria.size()-1;
	//			}else
	//				indiceCorrente--;
	//			this.preencherBusca(categoria.get(indiceCorrente));
	//			System.out.println(indiceCorrente);
	//		}
	//	}
	private void desabilita(JPanel pnl) {
		pnl.setBackground(Color.DARK_GRAY);
		Component[] c = pnl.getComponents(); //Pega todos os componentes adicionados no panel
		for (int i = 0; i < c.length; i++) {  
			c[i].setEnabled(false);  
		}  
	}
	private void habilita(JPanel pnl) {
		pnl.setBackground(Color.white);
		Component[] c = pnl.getComponents(); //Pega todos os componentes adicionados no panel
		for (int i = 0; i < c.length; i++) {  
			c[i].setEnabled(true);  
		}  
	}
	private static class Navega<T>{
		public void esquerda(List<T> categoria) {
			if(categoria!=null) {
				if(indiceCorrente==0) {
					indiceCorrente = categoria.size()-1;
				}else
					indiceCorrente--;
				preencherBusca((Categoria) categoria.get(indiceCorrente));
				System.out.println(indiceCorrente);
			}
		}
		private void direita(List<Categoria>categoria) {
			if(categoria!=null) {
				if(indiceCorrente >= categoria.size()-1) {
					indiceCorrente = 0;//vai para o primeiro!
				}else
					indiceCorrente++;
				preencherBusca(categoria.get(indiceCorrente));
				System.out.println(indiceCorrente);
			}
		}
	}
}
