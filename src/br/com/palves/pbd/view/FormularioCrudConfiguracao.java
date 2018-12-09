package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author p
 *
 */
public class FormularioCrudConfiguracao extends JDialog{

	private RoundButton salvarButton;
	private FieldRedondo valorKcontroleField ;
	private FieldRedondo valorKlivreField;
	private FieldRedondo idField;
	private FieldRedondo buscarField;
	private RoundButton irButton;
	private RoundButton allButton;
	private RoundedCornerButton removerButton;
	private RoundButton esquerdaButton,direitaButton;
	private FieldRedondo taxaHigField;
	private FieldRedondo taxaCombField;
	private FieldRedondo taxaDevField;
	private RoundedCornerButton limparButton;
	private JLabel operacaoLabel;
	public  FormularioCrudConfiguracao () {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(700,544);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel configLabel = new JLabel("Configura\u00E7\u00E3o");
		configLabel.setForeground(new Color(255, 255, 255));
		configLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		configLabel.setBounds(29, 11, 642, 54);
		panel.add(configLabel);

		JPanel panelVeiculo = new JPanel();
		panelVeiculo.setBackground(new Color(255, 255, 255));
		panelVeiculo.setBounds(2, 87, 502, 418);
		getContentPane().add(panelVeiculo);
		panelVeiculo.setLayout(null);

		valorKcontroleField = new FieldRedondo();
		valorKcontroleField.setBounds(10, 46, 82, 33);
		panelVeiculo.add(valorKcontroleField);
		valorKcontroleField.setColumns(10);

		valorKlivreField = new FieldRedondo();
		valorKlivreField.setColumns(10);
		valorKlivreField.setBounds(10, 102, 82, 33);
		panelVeiculo.add(valorKlivreField);

		JLabel valorKcontroleLabel = new JLabel("Valor da Di\u00E1ria Km Controle R$");
		valorKcontroleLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		valorKcontroleLabel.setBounds(21, 21, 240, 22);
		panelVeiculo.add(valorKcontroleLabel);

		JLabel valorKlivreLabel = new JLabel("Valor da Di\u00E1ria Km Livre R$");
		valorKlivreLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		valorKlivreLabel.setBounds(20, 80, 184, 22);
		panelVeiculo.add(valorKlivreLabel);

		idField = new FieldRedondo();
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(20, 0, 38, 22);
		panelVeiculo.add(idField);

		taxaHigField = new FieldRedondo();
		taxaHigField.setColumns(10);
		taxaHigField.setBounds(10, 158, 82, 33);
		panelVeiculo.add(taxaHigField);

		JLabel taxaHigLabel = new JLabel("Taxa de Higiene");
		taxaHigLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		taxaHigLabel.setBounds(20, 136, 133, 22);
		panelVeiculo.add(taxaHigLabel);

		taxaCombField = new FieldRedondo();
		taxaCombField.setColumns(10);
		taxaCombField.setBounds(10, 214, 82, 33);
		panelVeiculo.add(taxaCombField);

		JLabel taxaCombLabel = new JLabel("Taxa de Comb\u00FAstivel");
		taxaCombLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		taxaCombLabel.setBounds(21, 192, 183, 22);
		panelVeiculo.add(taxaCombLabel);

		JLabel lblCor = new JLabel("Taxa de Devolu\u00E7\u00E3o");
		lblCor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCor.setBounds(21, 243, 132, 22);
		panelVeiculo.add(lblCor);

		taxaDevField = new FieldRedondo();
		taxaDevField.setColumns(10);
		taxaDevField.setBounds(10, 265, 82, 33);
		panelVeiculo.add(taxaDevField);

		ButtonGroup b = new ButtonGroup();

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

		salvarButton = new RoundButton("Salvar");
		salvarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		salvarButton.setBounds(21, 309, 68, 68);
		panelVeiculo.add(salvarButton);
		salvarButton.setText("Salvar");
		salvarButton.setBackground(new Color(0, 64, 93));
		//salvarButton.setInitialColor();
		//salvarButton.setInitialColor(Color.black);
		salvarButton.setForeground(Color.WHITE);

		irButton = new RoundButton("Salvar");
		irButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		irButton.setForeground(Color.WHITE);
		irButton.setBackground(new Color(25,25,112));
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
		removerButton.setBackground(new Color(178,34,34));
		removerButton.setBounds(509, 173, 165, 42);
		getContentPane().add(removerButton);

		esquerdaButton = new RoundButton("Salvar");
		esquerdaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		esquerdaButton.setText("<");
		esquerdaButton.setForeground(Color.WHITE);
		esquerdaButton.setBackground(new Color(0, 128, 128));
		esquerdaButton.setBounds(544, 308, 43, 42);
		getContentPane().add(esquerdaButton);

		direitaButton = new RoundButton("Salvar");
		direitaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		direitaButton.setText(">");
		direitaButton.setForeground(Color.WHITE);
		direitaButton.setBackground(new Color(0, 128, 128));
		direitaButton.setBounds(595, 308, 43, 42);
		getContentPane().add(direitaButton);

		limparButton = new RoundedCornerButton("Salvar");
		limparButton.setText("Limpar");
		limparButton.setForeground(Color.WHITE);
		limparButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		limparButton.setBackground(new Color(60,179,113));
		limparButton.setBounds(509, 218, 165, 42);
		getContentPane().add(limparButton);
		
		operacaoLabel = new JLabel("Modo Inser\u00E7\u00E3o");
		operacaoLabel.setForeground(new Color(255, 0, 0));
		operacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		operacaoLabel.setBounds(221, -1, 203, 22);
		panelVeiculo.add(operacaoLabel);
		
				JSeparator separator = new JSeparator();
				separator.setBounds(475, 0, 2, 423);
				panelVeiculo.add(separator);
				separator.setOrientation(SwingConstants.VERTICAL);
				separator.setBackground(Color.DARK_GRAY);

		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(534, 254, 140, 55);
		getContentPane().add(lblNavegao);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		//setVisible(true);
	}
	public RoundButton getSalvarButton() {
		return salvarButton;
	}
	public FieldRedondo getValorKcontroleField() {
		return valorKcontroleField;
	}
	public FieldRedondo getValorKlivreField() {
		return valorKlivreField;
	}
	public FieldRedondo getIdField() {
		return idField;
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
	public FieldRedondo getTaxaHigField() {
		return taxaHigField;
	}
	public FieldRedondo getTaxaCombField() {
		return taxaCombField;
	}
	public FieldRedondo getTaxaDevField() {
		return taxaDevField;
	}
	public RoundedCornerButton getLimparButton() {
		return limparButton;
	}
	public JLabel getOperacaoLabel() {
		return operacaoLabel;
	}

}