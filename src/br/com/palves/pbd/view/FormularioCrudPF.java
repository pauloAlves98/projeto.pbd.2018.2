package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class FormularioCrudPF extends JDialog{
	private RoundButton salvarButton;
	private FieldRedondo nomeField ;
	private FieldRedondo senhaField;
	private FieldRedondo loginField;
	private FieldRedondo idField;
	private FieldRedondo cpfField;
	private JRadioButton sexoMradio,sexoFradio;
	private FieldRedondo dataNascimentoField; 
	private FieldRedondo nHabilitacaoField ;
	private FieldRedondo dataVencHabField;
	private FieldRedondo ruaField;
	private FieldRedondo numeroField;
	private FieldRedondo bairroField;
	private FieldRedondo cepField;
	private FieldRedondo ufField;
	private FieldRedondo cidadeField;
	private RoundButton autoButton;
	private FieldRedondo buscarField;
	private RoundButton irButton;
	private RoundButton allButton;
	private RoundedCornerButton removerButton;
	private RoundButton esquerdaField,direitaField;
	public FormularioCrudPF() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(700,544);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PESSOA F\u00CDSICA");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		lblNewLabel.setBounds(29, 11, 642, 54);
		panel.add(lblNewLabel);
		
		JPanel panelPessoa = new JPanel();
		panelPessoa.setBackground(new Color(255, 255, 255));
		panelPessoa.setBounds(0, 90, 484, 84);
		getContentPane().add(panelPessoa);
		panelPessoa.setLayout(null);
		
		nomeField = new FieldRedondo();
		nomeField.setBounds(10, 46, 150, 33);
		panelPessoa.add(nomeField);
		nomeField.setColumns(10);
		
		senhaField = new FieldRedondo();
		senhaField.setColumns(10);
		senhaField.setBounds(320, 46, 150, 33);
		panelPessoa.add(senhaField);
		
		loginField = new FieldRedondo();
		loginField.setColumns(10);
		loginField.setBounds(165, 46, 150, 33);
		panelPessoa.add(loginField);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		nomeLabel.setBounds(21, 21, 82, 22);
		panelPessoa.add(nomeLabel);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginLabel.setBounds(178, 21, 82, 22);
		panelPessoa.add(loginLabel);
		
		JLabel senhaLabel = new JLabel("Senha");
		senhaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		senhaLabel.setBounds(329, 21, 82, 22);
		panelPessoa.add(senhaLabel);
		
		idField = new FieldRedondo();
		idField.setColumns(10);
		idField.setBounds(20, 0, 38, 22);
		panelPessoa.add(idField);
		
		JPanel panelPf = new JPanel();
		panelPf.setBackground(new Color(255, 255, 255));
		panelPf.setBounds(0, 173, 484, 129);
		getContentPane().add(panelPf);
		panelPf.setLayout(null);
		
		cpfField = new FieldRedondo();
		cpfField.setHorizontalAlignment(SwingConstants.CENTER);
		cpfField.setBounds(10, 25, 150, 33);
		cpfField.setColumns(10);
		panelPf.add(cpfField);
		
		JLabel cpfLabel = new JLabel("CPF");
		cpfLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cpfLabel.setBounds(22, 0, 82, 22);
		panelPf.add(cpfLabel);
		
		ButtonGroup b = new ButtonGroup();
		sexoMradio = new JRadioButton("M");
		sexoMradio.setBackground(new Color(255, 255, 255));
		sexoMradio.setSelected(true);
		sexoMradio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sexoMradio.setBounds(205, 59, 40, 33);
		panelPf.add(sexoMradio);
		
		sexoFradio = new JRadioButton("F");
		sexoFradio.setBackground(new Color(255, 255, 255));
		sexoFradio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sexoFradio.setBounds(248, 59, 40, 33);
		panelPf.add(sexoFradio);
		
		b.add(sexoMradio);
		b.add(sexoFradio);
		
		JLabel sexoLabel = new JLabel("Sexo");
		sexoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		sexoLabel.setBounds(219, 29, 82, 22);
		panelPf.add(sexoLabel);
		
		dataNascimentoField = new FieldRedondo();
		dataNascimentoField.setColumns(10);
		dataNascimentoField.setBounds(324, 25, 150, 33);
		panelPf.add(dataNascimentoField);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataDeNascimento.setBounds(335, 0, 149, 22);
		panelPf.add(lblDataDeNascimento);
		
		nHabilitacaoField = new FieldRedondo();
		nHabilitacaoField.setHorizontalAlignment(SwingConstants.CENTER);
		nHabilitacaoField.setColumns(10);
		nHabilitacaoField.setBounds(10, 87, 150, 33);
		panelPf.add(nHabilitacaoField);
		
		JLabel lblNHabilitao = new JLabel("N\u00BA Habilita\u00E7\u00E3o");
		lblNHabilitao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNHabilitao.setBounds(20, 64, 140, 22);
		panelPf.add(lblNHabilitao);
		
		dataVencHabField = new FieldRedondo();
		dataVencHabField.setHorizontalAlignment(SwingConstants.CENTER);
		dataVencHabField.setColumns(10);
		dataVencHabField.setBounds(324, 87, 150, 33);
		panelPf.add(dataVencHabField);
		
		JLabel lblDataVencimentoHabilitao = new JLabel("Data de  Venc. Habilita\u00E7\u00E3o");
		lblDataVencimentoHabilitao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataVencimentoHabilitao.setBounds(306, 64, 178, 22);
		panelPf.add(lblDataVencimentoHabilitao);
		
		JPanel enderecoPanel = new JPanel();
		enderecoPanel.setBackground(Color.WHITE);
		enderecoPanel.setBounds(0, 302, 484, 139);
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
		
		salvarButton = new RoundButton("Salvar");
		salvarButton.setBounds(10, 452, 89, 42);
		getContentPane().add(salvarButton);
		
		autoButton = new RoundButton("Salvar");
		autoButton.setText("Auto");
		autoButton.setBounds(109, 452, 89, 42);
		getContentPane().add(autoButton);
		
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
		
		esquerdaField = new RoundButton("Salvar");
		esquerdaField.setFont(new Font("Tahoma", Font.BOLD, 13));
		esquerdaField.setText("<");
		esquerdaField.setForeground(Color.WHITE);
		esquerdaField.setBackground(new Color(0, 128, 128));
		esquerdaField.setBounds(542, 292, 43, 42);
		getContentPane().add(esquerdaField);
		
		direitaField = new RoundButton("Salvar");
		direitaField.setFont(new Font("Tahoma", Font.BOLD, 13));
		direitaField.setText(">");
		direitaField.setForeground(Color.WHITE);
		direitaField.setBackground(new Color(0, 128, 128));
		direitaField.setBounds(604, 292, 43, 42);
		getContentPane().add(direitaField);
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(534, 236, 140, 55);
		getContentPane().add(lblNavegao);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		//setVisible(true);
	}
}
