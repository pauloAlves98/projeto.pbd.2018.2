package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ResetSenha extends JDialog {
	private JTextField idEnd;
	private JFormattedTextField cpfField;
	private JButton salvarButton;
	private JRadioButton cpfRadio;
	private JRadioButton cnpjRadio;
	public ResetSenha() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(484,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("RESETAR SENHA");
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
		
		cpfField = new JFormattedTextField();
		cpfField.setColumns(10);
		cpfField.setBounds(160, 125, 150, 42);
		panelPessoa.add(cpfField);
		
		JLabel lblSenhaAtual = new JLabel("CPF");
		lblSenhaAtual.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenhaAtual.setBounds(160, 71, 55, 22);
		panelPessoa.add(lblSenhaAtual);

		ButtonGroup b = new ButtonGroup();

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		
		salvarButton = new JButton("Salvar");
		salvarButton.setHorizontalAlignment(SwingConstants.CENTER);
		salvarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		salvarButton.setBounds(160, 178, 150, 55);
		panelPessoa.add(salvarButton);
		salvarButton.setText("Resetar Senha");
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
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCnpj.setBounds(160, 92, 55, 22);
		panelPessoa.add(lblCnpj);
		
		cpfRadio = new JRadioButton("");
		cpfRadio.setOpaque(false);
		cpfRadio.setSelected(true);
		cpfRadio.setBounds(188, 71, 109, 23);
		panelPessoa.add(cpfRadio);
		
		cnpjRadio = new JRadioButton("");
		cnpjRadio.setSelected(true);
		cnpjRadio.setOpaque(false);
		cnpjRadio.setBounds(188, 93, 109, 23);
		panelPessoa.add(cnpjRadio);

		ButtonGroup bt = new ButtonGroup();
		bt.add(cpfRadio);
		bt.add(cnpjRadio);
		//setModal(true);
		//setVisible(true);
	}

	public JRadioButton getCpfRadio() {
		return cpfRadio;
	}

	public JRadioButton getCnpjRadio() {
		return cnpjRadio;
	}

	public JTextField getIdEnd() {
		return idEnd;
	}

	public JFormattedTextField getCpfField() {
		return cpfField;
	}

	public JButton getSalvarButton() {
		return salvarButton;
	}
}
