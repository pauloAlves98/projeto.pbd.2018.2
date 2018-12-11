package br.com.palves.pbd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.view.Menu;

public class ControllerMenu implements ActionListener{
	private Menu menu;
	ControllerAlterarSenha alterarSenha;
	ControllerCadastroReserva cdr;
	ControllerCrudCategoria cdc;
	ControllerCrudConfiguracao cdconf;
	ControllerCrudFilial cdfilial;
	ControllerCrudLocacao cdLocacao;
	ControllerCrudPF cdPF;
	ControllerCrudPJ cdPJ;
	ControllerCrudReserva cdReserva;
	ControllerCrudVeiculo cdVeiculo;
	ControllerResetarSenha cdResetar;
	ControllerCrudReserva cdcReserva;
	ControllerCrudFuncionario ccF;
	public ControllerMenu() {
		menu =new Menu();
		menu.setVisible(true);
		menu.getAlterarSenha().addActionListener(this);
		menu.getReserva().addActionListener(this);
		menu.getCategoria().addActionListener(this);
		menu.getConfiguracao().addActionListener(this);
		menu.getFilial().addActionListener(this);
		menu.getFuncionario().addActionListener(this);
		menu.getLocacao().addActionListener(this);
		menu.getPf().addActionListener(this);
		menu.getPj().addActionListener(this);
		menu.getBtnResetSenha().addActionListener(this);
		menu.getVeiculo().addActionListener(this);
		menu.getLoginButton().addActionListener(this);
		menu.getFuncionario().addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menu.getAlterarSenha()) {
			if(alterarSenha == null) {
				alterarSenha = new ControllerAlterarSenha();
			}
			alterarSenha.fpv.setVisible(true);
		}
		if(e.getSource()==menu.getReserva()) {
			if(cdr == null) {
				cdr = new ControllerCadastroReserva();
			}
			cdr.fpv.setVisible(true);
		}
		if(e.getSource()==menu.getCategoria()) {
			if(cdc == null) {
				cdc = new ControllerCrudCategoria();
			}
			cdc.fpc.setVisible(true);
		}
		
		
		if(e.getSource()==menu.getConfiguracao()) {
			if(cdconf== null) {
				cdconf = new ControllerCrudConfiguracao();
			}
			cdconf.fconfg.setVisible(true);
		}
		if(e.getSource()==menu.getFilial()) {
			if(cdfilial == null) {
				cdfilial = new ControllerCrudFilial();
			}
			cdfilial.fpf.setVisible(true);
		}
		
		if(e.getSource()==menu.getLocacao()) {
			if(cdLocacao == null) {
				cdLocacao = new ControllerCrudLocacao();
			}
			cdLocacao.fpl.setVisible(true);
		}
		if(e.getSource()==menu.getPf()) {
			if(cdPF == null) {
				cdPF = new ControllerCrudPF();
			}
			cdPF.fpf.setVisible(true);
		}
		
		if(e.getSource()==menu.getPj()) {
			if(cdPJ == null) {
				cdPJ = new ControllerCrudPJ();
			}
			cdPJ.fpj.setVisible(true);
		}
		if(e.getSource()==menu.getReserva()) {
			if(cdReserva == null) {
				cdReserva = new ControllerCrudReserva ();
			}
			cdReserva.fpv.setVisible(true);
		}
		if(e.getSource()==menu.getVeiculo()) {
			if(cdVeiculo == null) {
				cdVeiculo = new ControllerCrudVeiculo();
			}
			cdVeiculo.fpv.setVisible(true);
		}
		if(e.getSource()==menu.getBtnResetSenha()) {
			if(cdResetar == null) {
				cdResetar = new ControllerResetarSenha();
			}
			cdResetar.fpv.setVisible(true);
		}
		if(e.getSource()==menu.getLoginButton()) {
			if(cdcReserva==null) {
				cdcReserva = new ControllerCrudReserva();
			}
			cdcReserva.fpv.setVisible(true);
		}
		if(e.getSource()==menu.getFuncionario()) {
			if(ccF==null) {
				ccF = new ControllerCrudFuncionario();
			}
			ccF.fpf.setVisible(true);
		}
	}
	
}
