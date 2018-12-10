package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.com.palves.pbd.model.complemento.TratadorDeMascara;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FormularioCrudFuncionario extends JDialog{
	private RoundButton salvarButton;
	private FieldRedondo nomeField ;
	private FieldRedondo senhaField;
	private FieldRedondo loginField;
	private FieldRedondo idField;
	private FieldFormattedRedondo cpfField;
	private JRadioButton sexoMradio,sexoFradio;
	private FieldRedondo buscarField;
	private RoundButton irButton;
	private RoundButton allButton;
	private RoundedCornerButton removerButton;
	private RoundButton esquerdaButton,direitaButton;
	private JComboBox filialCombo;
	private FieldRedondo codFilialField;
	private JLabel operacaoLabel;
	private JTextField idEnd;
	private JLabel lblCargo;
	private JComboBox cargoBox;
	private FieldRedondo salarioField;
	private RoundedCornerButton limparButton;
	public FormularioCrudFuncionario() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(700,544);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FUNCION\u00C1RIO");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		lblNewLabel.setBounds(29, 11, 642, 54);
		panel.add(lblNewLabel);
		
		JPanel panelPessoa = new JPanel();
		panelPessoa.setBackground(new Color(255, 255, 255));
		panelPessoa.setBounds(0, 90, 493, 84);
		getContentPane().add(panelPessoa);
		panelPessoa.setLayout(null);
		
		nomeField = new FieldRedondo();
		nomeField.setBounds(10, 46, 150, 33);
		panelPessoa.add(nomeField);
		nomeField.setColumns(10);
		
		senhaField = new FieldRedondo();
		senhaField.setColumns(10);
		senhaField.setBounds(320, 46, 173, 33);
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
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(20, 0, 38, 22);
		panelPessoa.add(idField);
		
		JPanel panelPf = new JPanel();
		panelPf.setBackground(new Color(255, 255, 255));
		panelPf.setBounds(0, 173, 493, 130);
		getContentPane().add(panelPf);
		panelPf.setLayout(null);
		
		cpfField = new FieldFormattedRedondo();
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
		sexoMradio.setBounds(206, 30, 40, 33);
		panelPf.add(sexoMradio);
		
		sexoFradio = new JRadioButton("F");
		sexoFradio.setBackground(new Color(255, 255, 255));
		sexoFradio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sexoFradio.setBounds(246, 29, 40, 33);
		panelPf.add(sexoFradio);
		
		b.add(sexoMradio);
		b.add(sexoFradio);
		
		JLabel sexoLabel = new JLabel("Sexo");
		sexoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		sexoLabel.setBounds(218, 0, 82, 22);
		panelPf.add(sexoLabel);
		
		filialCombo = new JComboBox();
		filialCombo.setBounds(340, 27, 153, 32);
		panelPf.add(filialCombo);
		
		codFilialField = new FieldRedondo();
		codFilialField.setBackground(Color.WHITE);
		codFilialField.setEditable(false);
		codFilialField.setColumns(10);
		codFilialField.setBounds(287, 26, 51, 33);
		panelPf.add(codFilialField);
		
		JLabel lblCod = new JLabel("Cod");
		lblCod.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCod.setBounds(298, 0, 50, 22);
		panelPf.add(lblCod);
		
		JLabel lblFilial = new JLabel("Filial");
		lblFilial.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFilial.setBounds(340, 0, 56, 22);
		panelPf.add(lblFilial);
		
		lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCargo.setBounds(18, 65, 82, 22);
		panelPf.add(lblCargo);
		
		cargoBox = new JComboBox();
		cargoBox.setBackground(Color.WHITE);
		cargoBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		cargoBox.setModel(new DefaultComboBoxModel(new String[] {"ATENDENTE", "ADMINISTRADOR"}));
		cargoBox.setBounds(10, 87, 150, 32);
		panelPf.add(cargoBox);
		
		JLabel lblSalario = new JLabel("Salario");
		lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalario.setBounds(182, 65, 82, 22);
		panelPf.add(lblSalario);
		
		salarioField = new FieldRedondo();
		salarioField.setColumns(10);
		salarioField.setBackground(Color.WHITE);
		salarioField.setBounds(172, 87, 114, 33);
		panelPf.add(salarioField);
		
		salvarButton = new RoundButton("Salvar");
		salvarButton.setForeground(Color.WHITE);
		salvarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		salvarButton.setBounds(4, 313, 89, 68);
		salvarButton.setText("Salvar");
		salvarButton.setBackground(new Color(0, 64, 93));
		//salvarButton.setBounds(4, 313, 89, 42);
		getContentPane().add(salvarButton);
		
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
		
		idEnd  = new JTextField();
		idEnd.setBounds(-60, 292, 43, 42);
		idEnd.setVisible(false);
		panelPessoa.add(idEnd);
		
		operacaoLabel = new JLabel("Modo Inser\u00E7\u00E3o");
		operacaoLabel.setForeground(new Color(255, 0, 0));
		operacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		operacaoLabel.setBounds(221, -1, 203, 22);
		panelPessoa.add(operacaoLabel);
		
		limparButton = new RoundedCornerButton("Salvar");
		limparButton.setText("Limpar");
		limparButton.setForeground(Color.WHITE);
		limparButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		limparButton.setBackground(new Color(60, 179, 113));
		limparButton.setBounds(509, 218, 165, 42);
		getContentPane().add(limparButton);
		
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(544, 248, 140, 55);
		getContentPane().add(lblNavegao);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		
		TratadorDeMascara.aplicarMascaraCPF(cpfField);
		//setVisible(true);
	}
	public RoundButton getSalvarButton() {
		return salvarButton;
	}
	public FieldRedondo getNomeField() {
		return nomeField;
	}
	public FieldRedondo getSenhaField() {
		return senhaField;
	}
	public FieldRedondo getLoginField() {
		return loginField;
	}
	public FieldRedondo getIdField() {
		return idField;
	}
	public FieldFormattedRedondo getCpfField() {
		return cpfField;
	}
	public JRadioButton getSexoMradio() {
		return sexoMradio;
	}
	public JRadioButton getSexoFradio() {
		return sexoFradio;
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
	public JComboBox getFilialCombo() {
		return filialCombo;
	}
	public FieldRedondo getCodFilialField() {
		return codFilialField;
	}
	public JLabel getOperacaoLabel() {
		return operacaoLabel;
	}
	public JTextField getIdEnd() {
		return idEnd;
	}
	public JLabel getLblCargo() {
		return lblCargo;
	}
	public JComboBox getCargoBox() {
		return cargoBox;
	}
	public FieldRedondo getSalarioField() {
		return salarioField;
	}
	public RoundedCornerButton getLimparButton() {
		return limparButton;
	}
	
}
