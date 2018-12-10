package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AlterarSenhaDialog extends JDialog{
	private JPasswordField novaSenhaField;
	private JTextField idEnd;
	private JPasswordField senhaAtual;
	private JButton salvarButton;
	private  JLabel clienteLabel;
	
	public AlterarSenhaDialog() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(484,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ALTERAR SENHA");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		lblNewLabel.setBounds(29, 11, 642, 54);
		panel.add(lblNewLabel);

		JPanel panelPessoa = new JPanel();
		idEnd  = new JTextField();
		idEnd.setVisible(false);
		panelPessoa.add(idEnd);
		panelPessoa.setBackground(new Color(255, 255, 255));
		panelPessoa.setBounds(0, 90, 484, 335);
		getContentPane().add(panelPessoa);
		panelPessoa.setLayout(null);

		novaSenhaField = new JPasswordField();
		novaSenhaField.setColumns(10);
		novaSenhaField.setBounds(160, 167, 150, 42);
		panelPessoa.add(novaSenhaField);

		clienteLabel = new JLabel("Cliente");
		clienteLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		clienteLabel.setBounds(135, 28, 367, 22);
		panelPessoa.add(clienteLabel);

		JLabel senhaLabel = new JLabel("Nova Senha");
		senhaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		senhaLabel.setBounds(160, 145, 82, 22);
		panelPessoa.add(senhaLabel);
		
		JLabel label = new JLabel(new ImageIcon("res//user2.png"));
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(75, 11, 50, 50);
		panelPessoa.add(label);
		
		senhaAtual = new JPasswordField();
		senhaAtual.setColumns(10);
		senhaAtual.setBounds(160, 104, 150, 42);
		panelPessoa.add(senhaAtual);
		
		JLabel lblSenhaAtual = new JLabel("Senha Atual");
		lblSenhaAtual.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenhaAtual.setBounds(160, 78, 82, 22);
		panelPessoa.add(lblSenhaAtual);

		ButtonGroup b = new ButtonGroup();

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		
		salvarButton = new JButton("Salvar");
		salvarButton.setHorizontalAlignment(SwingConstants.CENTER);
		salvarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		salvarButton.setBounds(160, 220, 150, 55);
		panelPessoa.add(salvarButton);
		salvarButton.setText("Alterar senha");
		salvarButton.setBackground(new Color(0, 64, 93));
		//salvarButton.setInitialColor();
		//salvarButton.setInitialColor(Color.black);
		salvarButton.setForeground(Color.WHITE);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBounds(75, 65, 343, 2);
		panelPessoa.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.controlShadow);
		separator_1.setBounds(75, 302, 343, 2);
		panelPessoa.add(separator_1);

		//setModal(true);
		//setVisible(true);
	}

	public JPasswordField getNovaSenhaField() {
		return novaSenhaField;
	}

	public void setNovaSenhaField(JPasswordField novaSenhaField) {
		this.novaSenhaField = novaSenhaField;
	}

	public JTextField getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(JTextField idEnd) {
		this.idEnd = idEnd;
	}

	public JPasswordField getAtualField() {
		return senhaAtual;
	}

	public void setAtualField(JPasswordField passwordField) {
		this.senhaAtual = passwordField;
	}

	public JButton getSalvarButton() {
		return salvarButton;
	}

	public void setSalvarButton(JButton salvarButton) {
		this.salvarButton = salvarButton;
	}

	public JLabel getClienteLabel() {
		return clienteLabel;
	}

	public void setClienteLabel(JLabel clienteLabel) {
		this.clienteLabel = clienteLabel;
	}
	
}
