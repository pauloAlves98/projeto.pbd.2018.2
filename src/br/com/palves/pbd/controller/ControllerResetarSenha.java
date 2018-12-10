package br.com.palves.pbd.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.complemento.TratadorDeMascara;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.view.ResetSenha;

public class ControllerResetarSenha {
	public static  ResetSenha fpv = new ResetSenha(); 
	public ControllerResetarSenha() {
		//this.fpv.setVisible(true);
		this.fpv.setModal(true);
		this.fpv.getSalvarButton().addActionListener(ActionEvent ->alterarSenha());
		TratadorDeMascara.aplicarMascaraCPF(this.fpv.getCpfField());
		this.fpv.getCpfRadio().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				fpv.getCpfField().setValue(null);
				if(fpv.getCpfRadio().isSelected()) {
					fpv.getCpfField().setFormatterFactory(new  DefaultFormatterFactory(TratadorDeMascara.aplicarMascaraCpf2()));
				}else if(fpv.getCnpjRadio().isSelected())
						fpv.getCpfField().setFormatterFactory(new  DefaultFormatterFactory(TratadorDeMascara.aplicarMascaraCnpj2()));
				
			}
			
		});
		this.fpv.getCnpjRadio().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				fpv.getCpfField().setText("");
				fpv.getCpfField().setValue(null);
				if(fpv.getCpfRadio().isSelected()) {
					fpv.getCpfField().setFormatterFactory(new  DefaultFormatterFactory(TratadorDeMascara.aplicarMascaraCpf2()));
				}else if(fpv.getCnpjRadio().isSelected())
						fpv.getCpfField().setFormatterFactory(new  DefaultFormatterFactory(TratadorDeMascara.aplicarMascaraCnpj2()));
				
			}	
		});
//		this.fpv.addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent k) {
//				if(k.getKeyCode()==KeyEvent.VK_F5) {
//					if(Corrente.usuarioFisico!=null) {
//						fpv.getClienteLabel().setText(Corrente.usuarioFisico.getNome());
//					}
//					if(Corrente.usuarioJuridico!=null) {
//						fpv.getClienteLabel().setText(Corrente.usuarioJuridico.getNome());
//					}
//				}
//			}
//		});

	}
	private void alterarSenha() {
		fpv.requestFocusInWindow();
		if(Corrente.funcionario==null) {
			JOptionPane.showMessageDialog(null,"Eh preciso estar Logado para executar essa Funcao!");
		}else if(Corrente.funcionario.getCargo().equalsIgnoreCase("GERENTE") ){
			if(fpv.getCpfField().getText().replace(".","").replace("-","").replace(" ","").replace("/", "").length()<=0) {
				JOptionPane.showMessageDialog(null,"Campo Invalido!");
			}else {
				if(fpv.getCpfRadio().isSelected()) {
					PessoaFisicaDao pd = PessoaFisicaDao.getInstance();
					PessoaFisica p;
					try {
						p = pd.buscarPorCpf(fpv.getCpfField().getText());
						if(p!=null) {
							
							p.setSenha(EncriptaDecriptaApacheCodec.codificaBase64Encoder("123456"));
							System.out.println(p.getSenha());
							System.out.println(p.getCpf());
							
							pd.persistOrMerge(p);
							JOptionPane.showMessageDialog(null,"Alterado Para senha Padrão!!");
						}else
							JOptionPane.showMessageDialog(null,"Cpf não encontrado!");
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(fpv.getCnpjRadio().isSelected()){
					PessoaJuridicaDao pd = PessoaJuridicaDao.getInstance();
					PessoaJuridica p;
					try {
						p = pd.buscarPorCnpj(fpv.getCpfField().getText());
						if(p!=null) {
							p.setSenha(EncriptaDecriptaApacheCodec.codificaBase64Encoder("1234"));
							pd.persistOrMerge(p);
							JOptionPane.showMessageDialog(null,"Alterado Para senha Padrão!!");
						}else
							JOptionPane.showMessageDialog(null,"Cnpj não encontrado!");
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}else
			JOptionPane.showMessageDialog(null,"Só o Gerente pode executar esta Funcao");
		fpv.requestFocusInWindow();
	}
}
