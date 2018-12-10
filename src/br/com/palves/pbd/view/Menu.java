package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Menu extends JFrame {
	private JTextField idEnd;
	private JButton loginButton;
	private 	JButton alterarSenha;
	private JButton categoria;
	private JButton configuracao;
	private JButton filial;
	private JButton funcionario;
	private JButton locacao;
	private JButton pf;
	private JButton pj;
	private JButton veiculo;
	private JButton reserva;
	private JButton btnResetSenha;
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(484,690);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("MENU");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		lblNewLabel.setBounds(29, 11, 642, 54);
		panel.add(lblNewLabel);

		JPanel panelPessoa = new JPanel();
		idEnd  = new JTextField();
		idEnd.setVisible(false);
		panelPessoa.add(idEnd);
		panelPessoa.setBackground(new Color(255, 255, 255));
		panelPessoa.setBounds(0, 90, 484, 550);
		getContentPane().add(panelPessoa);
		panelPessoa.setLayout(null);
		
		JLabel lblSenhaAtual = new JLabel("Veiculos Paje\u00FA");
		lblSenhaAtual.setFont(new Font("Bradley Hand ITC", Font.BOLD, 32));
		lblSenhaAtual.setBounds(182, 265, 292, 71);
		panelPessoa.add(lblSenhaAtual);

		ButtonGroup b = new ButtonGroup();

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		
		loginButton = new JButton("Salvar");
		loginButton.setHorizontalAlignment(SwingConstants.CENTER);
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		loginButton.setBounds(10, 18, 143, 40);
		panelPessoa.add(loginButton);
		loginButton.setText("Crud Reserva");
		loginButton.setBackground(new Color(0, 64, 93));
		//salvarButton.setInitialColor();
		//salvarButton.setInitialColor(Color.black);
		loginButton.setForeground(Color.WHITE);
		
		alterarSenha = new JButton("Alterar senha");
		alterarSenha.setHorizontalAlignment(SwingConstants.CENTER);
		alterarSenha.setForeground(Color.WHITE);
		alterarSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		alterarSenha.setBackground(new Color(0, 64, 93));
		alterarSenha.setBounds(10, 62, 143, 40);
		panelPessoa.add(alterarSenha);
		
		categoria = new JButton("Crud Categoria");
		categoria.setHorizontalAlignment(SwingConstants.CENTER);
		categoria.setForeground(Color.WHITE);
		categoria.setFont(new Font("Tahoma", Font.BOLD, 11));
		categoria.setBackground(new Color(0, 64, 93));
		categoria.setBounds(10, 109, 143, 40);
		panelPessoa.add(categoria);
		
		configuracao = new JButton("Crud Configuracao");
		configuracao.setHorizontalAlignment(SwingConstants.CENTER);
		configuracao.setForeground(Color.WHITE);
		configuracao.setFont(new Font("Tahoma", Font.BOLD, 11));
		configuracao.setBackground(new Color(0, 64, 93));
		configuracao.setBounds(10, 152, 143, 40);
		panelPessoa.add(configuracao);
		
		filial = new JButton("Crud Filial");
		filial.setHorizontalAlignment(SwingConstants.CENTER);
		filial.setForeground(Color.WHITE);
		filial.setFont(new Font("Tahoma", Font.BOLD, 11));
		filial.setBackground(new Color(0, 64, 93));
		filial.setBounds(10, 197, 143, 40);
		panelPessoa.add(filial);
		
		funcionario = new JButton("Crud Funcionario");
		funcionario.setHorizontalAlignment(SwingConstants.CENTER);
		funcionario.setForeground(Color.WHITE);
		funcionario.setFont(new Font("Tahoma", Font.BOLD, 11));
		funcionario.setBackground(new Color(0, 64, 93));
		funcionario.setBounds(10, 241, 143, 40);
		panelPessoa.add(funcionario);
		
		locacao = new JButton("Crud Locacao");
		locacao.setHorizontalAlignment(SwingConstants.CENTER);
		locacao.setForeground(Color.WHITE);
		locacao.setFont(new Font("Tahoma", Font.BOLD, 11));
		locacao.setBackground(new Color(0, 64, 93));
		locacao.setBounds(10, 284, 143, 40);
		panelPessoa.add(locacao);
		
		pf = new JButton("Crud PF");
		pf.setHorizontalAlignment(SwingConstants.CENTER);
		pf.setForeground(Color.WHITE);
		pf.setFont(new Font("Tahoma", Font.BOLD, 11));
		pf.setBackground(new Color(0, 64, 93));
		pf.setBounds(10, 326, 143, 40);
		panelPessoa.add(pf);
		
		pj = new JButton("Crud PJ");
		pj.setHorizontalAlignment(SwingConstants.CENTER);
		pj.setForeground(Color.WHITE);
		pj.setFont(new Font("Tahoma", Font.BOLD, 11));
		pj.setBackground(new Color(0, 64, 93));
		pj.setBounds(10, 370, 143, 40);
		panelPessoa.add(pj);
		
		veiculo = new JButton("Crud Veiculo");
		veiculo.setHorizontalAlignment(SwingConstants.CENTER);
		veiculo.setForeground(Color.WHITE);
		veiculo.setFont(new Font("Tahoma", Font.BOLD, 11));
		veiculo.setBackground(new Color(0, 64, 93));
		veiculo.setBounds(10, 413, 143, 40);
		panelPessoa.add(veiculo);
		
		 reserva = new JButton("CadastroReserva");
		reserva.setHorizontalAlignment(SwingConstants.CENTER);
		reserva.setForeground(Color.WHITE);
		reserva.setFont(new Font("Tahoma", Font.BOLD, 11));
		reserva.setBackground(new Color(0, 64, 93));
		reserva.setBounds(10, 457, 143, 40);
		panelPessoa.add(reserva);
		
		btnResetSenha = new JButton("Reset Senha");
		btnResetSenha.setHorizontalAlignment(SwingConstants.CENTER);
		btnResetSenha.setForeground(Color.WHITE);
		btnResetSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnResetSenha.setBackground(new Color(0, 64, 93));
		btnResetSenha.setBounds(10, 499, 143, 40);
		panelPessoa.add(btnResetSenha);

		//setModal(true);
		//setVisible(true);
	}
	public JTextField getIdEnd() {
		return idEnd;
	}
	public void setIdEnd(JTextField idEnd) {
		this.idEnd = idEnd;
	}
	public JButton getLoginButton() {
		return loginButton;
	}
	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public JButton getAlterarSenha() {
		return alterarSenha;
	}
	public void setAlterarSenha(JButton alterarSenha) {
		this.alterarSenha = alterarSenha;
	}
	public JButton getCategoria() {
		return categoria;
	}
	public void setCategoria(JButton categoria) {
		this.categoria = categoria;
	}
	public JButton getConfiguracao() {
		return configuracao;
	}
	public void setConfiguracao(JButton configuracao) {
		this.configuracao = configuracao;
	}
	public JButton getFilial() {
		return filial;
	}
	public void setFilial(JButton filial) {
		this.filial = filial;
	}
	public JButton getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(JButton funcionario) {
		this.funcionario = funcionario;
	}
	public JButton getLocacao() {
		return locacao;
	}
	public void setLocacao(JButton locacao) {
		this.locacao = locacao;
	}
	public JButton getPf() {
		return pf;
	}
	public void setPf(JButton pf) {
		this.pf = pf;
	}
	public JButton getPj() {
		return pj;
	}
	public void setPj(JButton pj) {
		this.pj = pj;
	}
	public JButton getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(JButton veiculo) {
		this.veiculo = veiculo;
	}
	public JButton getReserva() {
		return reserva;
	}
	public void setReserva(JButton reserva) {
		this.reserva = reserva;
	}
	public JButton getBtnResetSenha() {
		return btnResetSenha;
	}
	public void setBtnResetSenha(JButton btnResetSenha) {
		this.btnResetSenha = btnResetSenha;
	}
	
}
