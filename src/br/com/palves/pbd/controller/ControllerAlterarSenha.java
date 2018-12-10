package br.com.palves.pbd.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.PessoaJuridica;
import br.com.palves.pbd.model.complemento.Corrente;
import br.com.palves.pbd.model.complemento.EncriptaDecriptaApacheCodec;
import br.com.palves.pbd.model.dao.PessoaFisicaDao;
import br.com.palves.pbd.model.dao.PessoaJuridicaDao;
import br.com.palves.pbd.view.AlterarSenhaDialog;

public class ControllerAlterarSenha {
	private AlterarSenhaDialog fpv = new AlterarSenhaDialog(); 
	public ControllerAlterarSenha() {
		this.fpv.setVisible(true);
		this.fpv.getSalvarButton().addActionListener(ActionEvent ->alterarSenha());
		this.fpv.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				if(k.getKeyCode()==KeyEvent.VK_F5) {
					if(Corrente.usuarioFisico!=null) {
						fpv.getClienteLabel().setText(Corrente.usuarioFisico.getNome());
					}
					if(Corrente.usuarioJuridico!=null) {
						fpv.getClienteLabel().setText(Corrente.usuarioJuridico.getNome());
					}
				}
			}
		});

	}
	private void alterarSenha() {
		fpv.requestFocusInWindow();
		if(Corrente.usuarioFisico == null && Corrente.usuarioJuridico==null) {
			JOptionPane.showMessageDialog(null,"Eh preciso estar Logado para executar essa Funcao!");
		}else if(Corrente.usuarioFisico != null){
			PessoaFisicaDao pd = PessoaFisicaDao.getInstance();
			if(Corrente.usuarioFisico.getSenha().equals(EncriptaDecriptaApacheCodec.codificaBase64Encoder(new String(fpv.getAtualField().getPassword()).length()<=0?"":new String(fpv.getAtualField().getPassword())))) {
				System.out.println("Senha do pw:"+new String(fpv.getAtualField().getPassword()));
				try {
					Corrente.usuarioFisico.setSenha(EncriptaDecriptaApacheCodec.codificaBase64Encoder(new String(fpv.getNovaSenhaField().getPassword()).length()<=0?"":new String(fpv.getNovaSenhaField().getPassword())));
					System.out.println("Senha do pf:"+Corrente.usuarioFisico.getSenha());
					PessoaFisica p=pd.persistOrMerge(Corrente.usuarioFisico);
					Corrente.usuarioFisico = p;
					JOptionPane.showMessageDialog(null,"Senha Alterada!");
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
				JOptionPane.showMessageDialog(null,"A senha atual esta incorreta!");
		}else {
			PessoaJuridicaDao pd = PessoaJuridicaDao.getInstance();
			if(Corrente.usuarioJuridico.getSenha().equals(EncriptaDecriptaApacheCodec.codificaBase64Encoder(new String(fpv.getAtualField().getPassword()).length()<=0?"":new String(fpv.getAtualField().getPassword())))) {
				Corrente.usuarioJuridico.setSenha(EncriptaDecriptaApacheCodec.codificaBase64Encoder(new String(fpv.getNovaSenhaField().getPassword())));
				try {
					PessoaJuridica p = pd.persistOrMerge(Corrente.usuarioJuridico);
					Corrente.usuarioJuridico =p;
					JOptionPane.showMessageDialog(null,"Senha Alterada!");
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				JOptionPane.showMessageDialog(null,"A senha atual esta incorreta!");
			}
		}	
		fpv.requestFocusInWindow();
	}

}
