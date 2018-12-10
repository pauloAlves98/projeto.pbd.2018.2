package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class FormularioCrudReserva extends JDialog {
	private RoundButton salvarButton;
	private FieldRedondo idField;
	private FieldRedondo buscarField;
	private RoundButton irButton;
	private RoundButton allButton;
	private RoundedCornerButton removerButton;
	private RoundButton esquerdaButton,direitaButton;
	private JTextField codCategoriaField;
	private JFormattedTextField horaField;
	private JComboBox situacaoCombo;
	private FieldRedondo kLivreField,kControleField;
	private JTextField codFilial;
	private JComboBox categoriaCombo;
	private JTextArea categoriaTextArea;
	private RoundedCornerButton limparButton;
	private JLabel operacaoLabel;
	private JComboBox filialAtualCombo ;
	private JTextArea filialTextArea;
	private JDateChooser dataRetirada;
	private FieldRedondo clienteField;
	public  FormularioCrudReserva() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(700,570);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RESERVA");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		lblNewLabel.setBounds(29, 11, 642, 54);
		panel.add(lblNewLabel);
		
		JPanel panelVeiculo = new JPanel();
		panelVeiculo.setBackground(new Color(255, 255, 255));
		panelVeiculo.setBounds(2, 87, 502, 500);
		getContentPane().add(panelVeiculo);
		panelVeiculo.setLayout(null);
		
		idField = new FieldRedondo();
		idField.setColumns(10);
		idField.setBounds(54, 0, 38, 22);
		panelVeiculo.add(idField);
		
		
		dataRetirada = new JDateChooser();
		dataRetirada.setBounds(10, 56, 123, 33);
		panelVeiculo.add(dataRetirada);
		horaField = new JFormattedTextField();
		horaField.setColumns(10);
		horaField.setBounds(132, 56, 67, 33);
		panelVeiculo.add(horaField);
		
		JLabel lblNmeroDePortas = new JLabel("Data/Hora retirada");
		lblNmeroDePortas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNmeroDePortas.setBounds(10, 33, 133, 22);
		panelVeiculo.add(lblNmeroDePortas);
		
		JLabel lblTamanho = new JLabel("Situa\u00E7\u00E3o");
		lblTamanho.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTamanho.setBounds(239, 299, 133, 22);
		panelVeiculo.add(lblTamanho);
		
		situacaoCombo = new JComboBox();
		situacaoCombo.setEditable(true);
		situacaoCombo.setModel(new DefaultComboBoxModel(new String[] {"EM ESPERA", "CANCELADA", "EFETUADA"}));
		situacaoCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		situacaoCombo.setBackground(Color.WHITE);
		situacaoCombo.setBounds(242, 323, 260, 33);
		panelVeiculo.add(situacaoCombo);
		
		JLabel lblNmeroDoMotor = new JLabel("R$");
		lblNmeroDoMotor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNmeroDoMotor.setBounds(102, 328, 123, 22);
		panelVeiculo.add(lblNmeroDoMotor);
		
		kLivreField = new FieldRedondo();
		kLivreField.setEditable(false);
		kLivreField.setColumns(10);
		kLivreField.setBounds(129, 324, 96, 33);
		panelVeiculo.add(kLivreField);
		
		JLabel Cod = new JLabel("Cod");
		Cod.setFont(new Font("Tahoma", Font.BOLD, 12));
		Cod.setBounds(10, -1, 123, 22);
		panelVeiculo.add(Cod);
		
		JLabel lblAnoDoModelo = new JLabel("Km Controle");
		lblAnoDoModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnoDoModelo.setBounds(20, 299, 123, 22);
		panelVeiculo.add(lblAnoDoModelo);
		
		kControleField = new FieldRedondo();
		kControleField.setEditable(false);
		kControleField.setColumns(10);
		kControleField.setBounds(10, 324, 82, 33);
		panelVeiculo.add(kControleField);
		
		JLabel lblFilialAtual = new JLabel("Filial Retirada");
		lblFilialAtual.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFilialAtual.setBounds(368, 33, 123, 22);
		panelVeiculo.add(lblFilialAtual);
		
		codFilial = new JTextField();
		codFilial.setBackground(Color.WHITE);
		codFilial.setEditable(false);
		codFilial.setColumns(10);
		codFilial.setBounds(242, 56, 38, 33);
		panelVeiculo.add(codFilial);
		
		filialAtualCombo = new JComboBox();
		filialAtualCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		filialAtualCombo.setEditable(true);
		filialAtualCombo.setBackground(Color.WHITE);
		filialAtualCombo.setBounds(280, 56, 222, 33);
		panelVeiculo.add(filialAtualCombo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 88, 260, 200);
		panelVeiculo.add(scrollPane);
		
		filialTextArea = new JTextArea();
		filialTextArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		filialTextArea.setLineWrap(true);
		scrollPane.setViewportView(filialTextArea);
		
		codCategoriaField = new JTextField();
		codCategoriaField.setEditable(false);
		codCategoriaField.setColumns(10);
		codCategoriaField.setBackground(Color.WHITE);
		codCategoriaField.setBounds(10, 121, 38, 33);
		panelVeiculo.add(codCategoriaField);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategoria.setBounds(10, 100, 123, 22);
		panelVeiculo.add(lblCategoria);
		
	    categoriaCombo = new JComboBox();
		categoriaCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		categoriaCombo.setEditable(true);
		categoriaCombo.setBackground(Color.WHITE);
		categoriaCombo.setBounds(49, 121, 183, 33);
		panelVeiculo.add(categoriaCombo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 154, 222, 134);
		panelVeiculo.add(scrollPane_1);
		
		categoriaTextArea = new JTextArea();
		categoriaTextArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		categoriaTextArea.setLineWrap(true);
		scrollPane_1.setViewportView(categoriaTextArea);
		
		salvarButton = new RoundButton("Salvar");
		salvarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		//salvarButton.setBounds(-48, 366, 150, 42);
		salvarButton.setBackground(new Color(0, 64, 93));
		//salvarButton.setInitialColor();
		//salvarButton.setInitialColor(Color.black);
		salvarButton.setForeground(Color.WHITE);
		salvarButton.setBounds(10, 361, 82, 68);
		panelVeiculo.add(salvarButton);
		ButtonGroup b = new ButtonGroup();
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(506, 87, 2, 500);
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
		esquerdaButton.setBounds(534, 330, 43, 42);
		getContentPane().add(esquerdaButton);
		
		direitaButton = new RoundButton("Salvar");
		direitaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		direitaButton.setText(">");
		direitaButton.setForeground(Color.WHITE);
		direitaButton.setBackground(new Color(0, 128, 128));
		direitaButton.setBounds(595, 330, 43, 42);
		getContentPane().add(direitaButton);
		
		limparButton = new RoundedCornerButton("Salvar");
		limparButton.setText("Limpar");
		limparButton.setForeground(Color.WHITE);
		limparButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		limparButton.setBackground(new Color(60,179,113));
		limparButton.setBounds(509, 218, 165, 42);
		getContentPane().add(limparButton);
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(534, 271, 140, 55);
		getContentPane().add(lblNavegao);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		
		operacaoLabel = new JLabel("Modo Inser\u00E7\u00E3o");
		operacaoLabel.setForeground(new Color(255, 0, 0));
		operacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		operacaoLabel.setBounds(221, -1, 203, 22);
		panelVeiculo.add(operacaoLabel);
		
		JLabel lblH = new JLabel(new ImageIcon("res//relogio.png"));
		lblH.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblH.setBounds(198, 56, 38, 33);
		panelVeiculo.add(lblH);
		
		JLabel lblKmLivre = new JLabel("Km Livre");
		lblKmLivre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKmLivre.setBounds(146, 299, 123, 22);
		panelVeiculo.add(lblKmLivre);
		
	    clienteField = new FieldRedondo();
		clienteField.setEditable(false);
		clienteField.setColumns(10);
		clienteField.setBounds(239, 379, 263, 33);
		panelVeiculo.add(clienteField);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCliente.setBounds(252, 356, 133, 22);
		panelVeiculo.add(lblCliente);
		
		//setVisible(true);
	}
	public RoundButton getSalvarButton() {
		return salvarButton;
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
	public JTextField getCodCategoriaField() {
		return codCategoriaField;
	}
	public JFormattedTextField getHoraField() {
		return horaField;
	}
	public JComboBox getSituacaoCombo() {
		return situacaoCombo;
	}
	public FieldRedondo getkLivreField() {
		return kLivreField;
	}
	public FieldRedondo getkControleField() {
		return kControleField;
	}
	public JTextField getCodFilial() {
		return codFilial;
	}
	public JComboBox getCategoriaCombo() {
		return categoriaCombo;
	}
	public JTextArea getCategoriaTextArea() {
		return categoriaTextArea;
	}
	public RoundedCornerButton getLimparButton() {
		return limparButton;
	}
	public JLabel getOperacaoLabel() {
		return operacaoLabel;
	}
	public JComboBox getFilialAtualCombo() {
		return filialAtualCombo;
	}
	public JTextArea getFilialTextArea() {
		return filialTextArea;
	}
	public JDateChooser getDataRetirada() {
		return dataRetirada;
	}
	public FieldRedondo getClienteField() {
		return clienteField;
	}
	
}
