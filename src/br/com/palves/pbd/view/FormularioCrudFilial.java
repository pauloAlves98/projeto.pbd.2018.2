package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FormularioCrudFilial extends JDialog {
	private RoundButton salvarButton;
	private FieldRedondo nomeField ;
	private FieldRedondo idField;
	private FieldRedondo ruaField;
	private FieldRedondo numeroField;
	private FieldRedondo bairroField;
	private FieldRedondo cepField;
	private FieldRedondo ufField;
	private FieldRedondo cidadeField;
	private FieldRedondo buscarField;
	private RoundButton irButton;
	private RoundButton allButton;
	private RoundedCornerButton removerButton;
	private RoundButton esquerdaButton,direitaButton;
	private RoundedCornerButton limparButton;
	private JLabel operacaoLabel;
	private JTextField idEnd;
	private JComboBox horaFimCombo;
	private JComboBox horaInicioCombo ;
	public FormularioCrudFilial() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(700,544);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
		panel.setBackground(new Color(51, 153, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FILIAL");
		lblNewLabel.setBackground(new Color(204, 51, 51));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		lblNewLabel.setBounds(29, 11, 642, 54);
		panel.add(lblNewLabel);
		
		JPanel panelPessoa = new JPanel();
		panelPessoa.setBackground(new Color(255, 255, 255));
		panelPessoa.setBounds(0, 90, 484, 84);
		getContentPane().add(panelPessoa);
		panelPessoa.setLayout(null);
		
		nomeField = new FieldRedondo();
		nomeField.setBounds(73, 51, 401, 33);
		panelPessoa.add(nomeField);
		nomeField.setColumns(10);
		
		JLabel idLabel = new JLabel("Cod");
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		idLabel.setBounds(21, 21, 40, 22);
		panelPessoa.add(idLabel);
		
		idField = new FieldRedondo();
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(10, 51, 53, 33);
		panelPessoa.add(idField);
		
		JLabel label = new JLabel("Nome");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(84, 21, 82, 22);
		panelPessoa.add(label);
		
		ButtonGroup b = new ButtonGroup();
		
		JPanel enderecoPanel = new JPanel();
		enderecoPanel.setBackground(Color.WHITE);
		enderecoPanel.setBounds(0, 173, 484, 212);
		getContentPane().add(enderecoPanel);
		enderecoPanel.setLayout(null);
		
		ruaField = new FieldRedondo();
		ruaField.setColumns(10);
		ruaField.setBounds(10, 24, 181, 33);
		enderecoPanel.add(ruaField);
		
		JLabel ruaLabel = new JLabel("Rua");
		ruaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		ruaLabel.setBounds(20, 0, 140, 22);
		enderecoPanel.add(ruaLabel);
		
		numeroField = new FieldRedondo();
		numeroField.setColumns(10);
		numeroField.setBounds(400, 24, 74, 33);
		enderecoPanel.add(numeroField);
		
		JLabel numeroLabel = new JLabel("N\u00BA");
		numeroLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		numeroLabel.setBounds(412, 0, 50, 22);
		enderecoPanel.add(numeroLabel);
		
		bairroField = new FieldRedondo();
		bairroField.setColumns(10);
		bairroField.setBounds(201, 24, 181, 33);
		enderecoPanel.add(bairroField);
		
		JLabel bairroLabel = new JLabel("Bairro");
		bairroLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		bairroLabel.setBounds(212, 0, 50, 22);
		enderecoPanel.add(bairroLabel);
		
	    cepField = new FieldRedondo();
		cepField.setColumns(10);
		cepField.setBounds(10, 91, 181, 33);
		enderecoPanel.add(cepField);
		
		JLabel cepLabel = new JLabel("Cep");
		cepLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cepLabel.setBounds(20, 68, 140, 22);
		enderecoPanel.add(cepLabel);
		
		ufField = new FieldRedondo();
		ufField.setColumns(10);
		ufField.setBounds(400, 91, 74, 33);
		enderecoPanel.add(ufField);
		
		JLabel ufLabel = new JLabel("UF");
		ufLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		ufLabel.setBounds(410, 68, 50, 22);
		enderecoPanel.add(ufLabel);
		
		cidadeField = new FieldRedondo();
		cidadeField.setColumns(10);
		cidadeField.setBounds(201, 91, 181, 33);
		enderecoPanel.add(cidadeField);
		
		JLabel cidadeLabel = new JLabel("Cidade");
		cidadeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cidadeLabel.setBounds(211, 68, 140, 22);
		enderecoPanel.add(cidadeLabel);
		
		horaInicioCombo = new JComboBox();
		horaInicioCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		horaInicioCombo.setForeground(Color.WHITE);
		horaInicioCombo.setModel(new DefaultComboBoxModel(new String[] {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"}));
		horaInicioCombo.setBackground(new Color(0, 64, 93));
		horaInicioCombo.setBounds(20, 162, 171, 39);
		enderecoPanel.add(horaInicioCombo);
		
		horaFimCombo = new JComboBox();
		horaFimCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		horaFimCombo.setForeground(Color.WHITE);
		horaFimCombo.setModel(new DefaultComboBoxModel(new String[] {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"}));
		horaFimCombo.setBackground(new Color(0, 64, 93));
		horaFimCombo.setBounds(211, 162, 171, 39);
		enderecoPanel.add(horaFimCombo);
		
		JLabel lblHoraInicio = new JLabel("Hora Inicio Expediente");
		lblHoraInicio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHoraInicio.setBounds(20, 135, 140, 22);
		enderecoPanel.add(lblHoraInicio);
		
		JLabel lblHoraFim = new JLabel("Hora Fim Expediente");
		lblHoraFim.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHoraFim.setBounds(211, 135, 140, 22);
		enderecoPanel.add(lblHoraFim);
		
		salvarButton = new RoundButton("Salvar");
		salvarButton.setBounds(412, 154, 62, 55);
		enderecoPanel.add(salvarButton);
		salvarButton.setText("Salvar");
		salvarButton.setBackground(new Color(0, 64, 93));
		//salvarButton.setInitialColor();
		//salvarButton.setInitialColor(Color.black);
		salvarButton.setForeground(Color.WHITE);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(494, 85, 18, 423);
		getContentPane().add(separator);
		
		buscarField = new FieldRedondo();
		buscarField.setColumns(10);
		buscarField.setBounds(534, 133, 51, 33);
		getContentPane().add(buscarField);
		
		JLabel lblBusca = new JLabel("Busca/Remove");
		lblBusca.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblBusca.setBounds(522, 77, 152, 55);
		getContentPane().add(lblBusca);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(512, 141, 29, 14);
		getContentPane().add(lblId);
		
		irButton = new RoundButton("Salvar");
		irButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		irButton.setForeground(Color.WHITE);
		irButton.setBackground(Color.BLUE);
		irButton.setText("Ir");
		irButton.setBounds(590, 128, 43, 42);
		getContentPane().add(irButton);
		
		allButton = new RoundButton("Salvar");
		allButton.setForeground(Color.WHITE);
		allButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		allButton.setText("All");
		allButton.setBackground(Color.BLACK);
		allButton.setBounds(635, 128, 43, 42);
		getContentPane().add(allButton);
		
		removerButton = new RoundedCornerButton("Salvar");
		removerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		removerButton.setText("Remover");
		removerButton.setForeground(Color.WHITE);
		removerButton.setBackground(Color.RED);
		removerButton.setBounds(509, 173, 165, 42);
		getContentPane().add(removerButton);
		
		esquerdaButton = new RoundButton("Salvar");
		esquerdaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		esquerdaButton.setText("<");
		esquerdaButton.setForeground(Color.WHITE);
		esquerdaButton.setBackground(new Color(0, 128, 128));
		esquerdaButton.setBounds(542, 292, 43, 42);
		getContentPane().add(esquerdaButton);
		
		direitaButton = new RoundButton("Salvar");
		direitaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		direitaButton.setText(">");
		direitaButton.setForeground(Color.WHITE);
		direitaButton.setBackground(new Color(0, 128, 128));
		direitaButton.setBounds(604, 292, 43, 42);
		getContentPane().add(direitaButton);
		
		limparButton = new RoundedCornerButton("Salvar");
		limparButton.setBackground(new Color(52, 175, 35));
		limparButton.setText("Limpar");
		limparButton.setForeground(Color.WHITE);
		limparButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		limparButton.setBackground(new Color(60, 179, 113));
		limparButton.setBounds(509, 218, 165, 42);
		getContentPane().add(limparButton);
		
		operacaoLabel = new JLabel("Modo Inser\u00E7\u00E3o");
		operacaoLabel.setForeground(new Color(255, 0, 0));
		operacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		operacaoLabel.setBounds(221, -1, 203, 22);
		panelPessoa.add(operacaoLabel);
		
		idEnd  = new JTextField();
		idEnd.setBounds(-60, 292, 43, 42);
		idEnd.setVisible(false);
		panelPessoa.add(idEnd);
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(534, 249, 140, 55);
		getContentPane().add(lblNavegao);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		
		panel.setBackground(new Color(0, 64, 93));
		//setVisible(true);
	}
	public RoundButton getSalvarButton() {
		return salvarButton;
	}
	public FieldRedondo getNomeField() {
		return nomeField;
	}
	public FieldRedondo getIdField() {
		return idField;
	}
	public FieldRedondo getRuaField() {
		return ruaField;
	}
	public FieldRedondo getNumeroField() {
		return numeroField;
	}
	public FieldRedondo getBairroField() {
		return bairroField;
	}
	public FieldRedondo getCepField() {
		return cepField;
	}
	public FieldRedondo getUfField() {
		return ufField;
	}
	public FieldRedondo getCidadeField() {
		return cidadeField;
	}
	public FieldRedondo getBuscarField() {
		return buscarField;
	}
	public RoundButton getIrButton() {
		return irButton;
	}
	public RoundButton getAllButton() {
		return allButton;
	}
	public RoundedCornerButton getRemoverButton() {
		return removerButton;
	}
	public RoundButton getEsquerdaButton() {
		return esquerdaButton;
	}
	public RoundButton getDireitaButton() {
		return direitaButton;
	}
	public RoundedCornerButton getLimparButton() {
		return limparButton;
	}
	public JLabel getOperacaoLabel() {
		return operacaoLabel;
	}
	public JTextField getIdEnd() {
		return idEnd;
	}
	public JComboBox getHoraFimCombo() {
		return horaFimCombo;
	}
	public JComboBox getHoraInicioCombo() {
		return horaInicioCombo;
	}
	
}
