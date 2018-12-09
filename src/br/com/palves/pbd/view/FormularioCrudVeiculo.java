package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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

public class FormularioCrudVeiculo extends JDialog{
	private RoundButton salvarButton;
	private FieldRedondo nomeField ;
	private FieldRedondo torqueMotorField;
	private FieldRedondo numeroChassiField;
	private FieldRedondo idField;
	private FieldRedondo buscarField;
	private RoundButton irButton;
	private RoundButton allButton;
	private RoundedCornerButton removerButton;
	private RoundButton esquerdaButton,direitaButton;
	private JTextField codCategoriaField;
	private FieldRedondo numeroPortasField;
	private JCheckBox gasolinaCheck,etanolCheck,dieselCheck,biocombustivelCheck;
	private JComboBox tamanhoCombo;
	private FieldRedondo numeroMotorField, modeloField,anoModeloField;
	private FieldRedondo corField, fabricanteField,anoFabricacaoField,horasRevisaoField;
	private JTextField codFilial;
	private JComboBox categoriaCombo;
	private JTextArea categoriaTextArea;
	private RoundedCornerButton limparButton;
	private JLabel operacaoLabel;
	private JComboBox filialAtualCombo ;
	private JTextArea filialTextArea;
	public  FormularioCrudVeiculo() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(700,570);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VE\u00CDCULO");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		lblNewLabel.setBounds(29, 11, 642, 54);
		panel.add(lblNewLabel);
		
		JPanel panelVeiculo = new JPanel();
		panelVeiculo.setBackground(new Color(255, 255, 255));
		panelVeiculo.setBounds(2, 87, 502, 500);
		getContentPane().add(panelVeiculo);
		panelVeiculo.setLayout(null);
		
		nomeField = new FieldRedondo();
		nomeField.setBounds(10, 46, 150, 33);
		panelVeiculo.add(nomeField);
		nomeField.setColumns(10);
		
		torqueMotorField = new FieldRedondo();
		torqueMotorField.setColumns(10);
		torqueMotorField.setBounds(210, 46, 106, 33);
		panelVeiculo.add(torqueMotorField);
		
		numeroChassiField = new FieldRedondo();
		numeroChassiField.setColumns(10);
		numeroChassiField.setBounds(10, 102, 150, 33);
		panelVeiculo.add(numeroChassiField);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		nomeLabel.setBounds(21, 21, 82, 22);
		panelVeiculo.add(nomeLabel);
		
		JLabel loginLabel = new JLabel("N\u00FAmero do Chassi");
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginLabel.setBounds(20, 80, 133, 22);
		panelVeiculo.add(loginLabel);
		
		JLabel senhaLabel = new JLabel("Torque do Motor");
		senhaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		senhaLabel.setBounds(222, 21, 123, 22);
		panelVeiculo.add(senhaLabel);
		
		idField = new FieldRedondo();
		idField.setColumns(10);
		idField.setBounds(20, 0, 38, 22);
		panelVeiculo.add(idField);
		
		numeroPortasField = new FieldRedondo();
		numeroPortasField.setColumns(10);
		numeroPortasField.setBounds(10, 158, 67, 33);
		panelVeiculo.add(numeroPortasField);
		
		JLabel lblNmeroDePortas = new JLabel("N\u00FAmero de Portas");
		lblNmeroDePortas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNmeroDePortas.setBounds(20, 136, 133, 22);
		panelVeiculo.add(lblNmeroDePortas);
		
		JLabel lblTipoDeCombustvel = new JLabel("Tipo de Combust\u00EDvel");
		lblTipoDeCombustvel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoDeCombustvel.setBounds(20, 192, 133, 22);
		panelVeiculo.add(lblTipoDeCombustvel);
		
		gasolinaCheck = new JCheckBox("Gasolina");
		gasolinaCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gasolinaCheck.setBounds(30, 213, 97, 23);
		panelVeiculo.add(gasolinaCheck);
		gasolinaCheck.setOpaque(false);
		
		etanolCheck = new JCheckBox("Etanol");
		etanolCheck.setOpaque(false);
		etanolCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		etanolCheck.setBounds(30, 233, 97, 23);
		panelVeiculo.add(etanolCheck);
		
		dieselCheck = new JCheckBox("Diesel");
		dieselCheck.setOpaque(false);
		dieselCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dieselCheck.setBounds(30, 254, 97, 23);
		panelVeiculo.add(dieselCheck);
		
		biocombustivelCheck = new JCheckBox("Biocombustivel");
		biocombustivelCheck.setOpaque(false);
		biocombustivelCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		biocombustivelCheck.setBounds(30, 272, 123, 23);
		panelVeiculo.add(biocombustivelCheck);
		
		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTamanho.setBounds(21, 298, 133, 22);
		panelVeiculo.add(lblTamanho);
		
		tamanhoCombo = new JComboBox();
		tamanhoCombo.setEditable(true);
		tamanhoCombo.setModel(new DefaultComboBoxModel(new String[] {"Pequeno", "M\u00E9dio", "Grande"}));
		tamanhoCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tamanhoCombo.setBackground(Color.WHITE);
		tamanhoCombo.setBounds(15, 322, 145, 33);
		panelVeiculo.add(tamanhoCombo);
		
		numeroMotorField = new FieldRedondo();
		numeroMotorField.setColumns(10);
		numeroMotorField.setBounds(210, 102, 106, 33);
		panelVeiculo.add(numeroMotorField);
		
		JLabel lblNmeroDoMotor = new JLabel("N\u00FAmero do Motor");
		lblNmeroDoMotor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNmeroDoMotor.setBounds(220, 80, 123, 22);
		panelVeiculo.add(lblNmeroDoMotor);
		
		modeloField = new FieldRedondo();
		modeloField.setColumns(10);
		modeloField.setBounds(210, 158, 106, 33);
		panelVeiculo.add(modeloField);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModelo.setBounds(222, 136, 123, 22);
		panelVeiculo.add(lblModelo);
		
		JLabel lblAnoDoModelo = new JLabel("Ano do Modelo");
		lblAnoDoModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnoDoModelo.setBounds(222, 192, 123, 22);
		panelVeiculo.add(lblAnoDoModelo);
		
		anoModeloField = new FieldRedondo();
		anoModeloField.setColumns(10);
		anoModeloField.setBounds(210, 214, 82, 33);
		panelVeiculo.add(anoModeloField);
		
		JLabel lblCor = new JLabel("Cor");
		lblCor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCor.setBounds(222, 246, 123, 22);
		panelVeiculo.add(lblCor);
		
		corField = new FieldRedondo();
		corField.setColumns(10);
		corField.setBounds(210, 268, 82, 33);
		panelVeiculo.add(corField);
		
		JLabel lblFabricante = new JLabel("Fabricante");
		lblFabricante.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFabricante.setBounds(220, 298, 123, 22);
		panelVeiculo.add(lblFabricante);
		
		fabricanteField = new FieldRedondo();
		fabricanteField.setColumns(10);
		fabricanteField.setBounds(210, 322, 106, 33);
		panelVeiculo.add(fabricanteField);
		
		anoFabricacaoField = new FieldRedondo();
		anoFabricacaoField.setColumns(10);
		anoFabricacaoField.setBounds(371, 46, 90, 33);
		panelVeiculo.add(anoFabricacaoField);
		
		JLabel lblAnoDaFabricao = new JLabel("Ano da Fabrica\u00E7\u00E3o");
		lblAnoDaFabricao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnoDaFabricao.setBounds(361, 21, 123, 22);
		panelVeiculo.add(lblAnoDaFabricao);
		
		JLabel lblHorasParaReviso = new JLabel("Horas Para Revis\u00E3o");
		lblHorasParaReviso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHorasParaReviso.setBounds(361, 80, 123, 22);
		panelVeiculo.add(lblHorasParaReviso);
		
		horasRevisaoField = new FieldRedondo();
		horasRevisaoField.setColumns(10);
		horasRevisaoField.setBounds(368, 102, 90, 33);
		panelVeiculo.add(horasRevisaoField);
		
		JLabel lblFilialAtual = new JLabel("Filial Atual");
		lblFilialAtual.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFilialAtual.setBounds(361, 136, 123, 22);
		panelVeiculo.add(lblFilialAtual);
		
		codFilial = new JTextField();
		codFilial.setBackground(Color.WHITE);
		codFilial.setEditable(false);
		codFilial.setColumns(10);
		codFilial.setBounds(326, 158, 38, 33);
		panelVeiculo.add(codFilial);
		
		filialAtualCombo = new JComboBox();
		filialAtualCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		filialAtualCombo.setEditable(true);
		filialAtualCombo.setBackground(Color.WHITE);
		filialAtualCombo.setBounds(368, 158, 134, 33);
		panelVeiculo.add(filialAtualCombo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(326, 192, 176, 54);
		panelVeiculo.add(scrollPane);
		
		filialTextArea = new JTextArea();
		filialTextArea.setLineWrap(true);
		scrollPane.setViewportView(filialTextArea);
		
		codCategoriaField = new JTextField();
		codCategoriaField.setEditable(false);
		codCategoriaField.setColumns(10);
		codCategoriaField.setBackground(Color.WHITE);
		codCategoriaField.setBounds(326, 264, 38, 33);
		panelVeiculo.add(codCategoriaField);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategoria.setBounds(326, 246, 123, 22);
		panelVeiculo.add(lblCategoria);
		
	    categoriaCombo = new JComboBox();
		categoriaCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		categoriaCombo.setEditable(true);
		categoriaCombo.setBackground(Color.WHITE);
		categoriaCombo.setBounds(368, 264, 134, 33);
		panelVeiculo.add(categoriaCombo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(326, 298, 176, 115);
		panelVeiculo.add(scrollPane_1);
		
		categoriaTextArea = new JTextArea();
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
		
		//setVisible(true);
	}
	public RoundButton getSalvarButton() {
		return salvarButton;
	}
	public FieldRedondo getNomeField() {
		return nomeField;
	}
	public FieldRedondo getTorqueMotorField() {
		return torqueMotorField;
	}
	public FieldRedondo getNumeroChassiField() {
		return numeroChassiField;
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
	public FieldRedondo getNumeroPortasField() {
		return numeroPortasField;
	}
	public JCheckBox getGasolinaCheck() {
		return gasolinaCheck;
	}
	public JCheckBox getEtanolCheck() {
		return etanolCheck;
	}
	public JCheckBox getDieselCheck() {
		return dieselCheck;
	}
	public JCheckBox getBiocombustivelCheck() {
		return biocombustivelCheck;
	}
	public JComboBox getTamanhoCombo() {
		return tamanhoCombo;
	}
	public FieldRedondo getNumeroMotorField() {
		return numeroMotorField;
	}
	public FieldRedondo getModeloField() {
		return modeloField;
	}
	public FieldRedondo getAnoModeloField() {
		return anoModeloField;
	}
	public FieldRedondo getCorField() {
		return corField;
	}
	public FieldRedondo getFabricanteField() {
		return fabricanteField;
	}
	public FieldRedondo getAnoFabricacaoField() {
		return anoFabricacaoField;
	}
	public FieldRedondo getHorasRevisaoField() {
		return horasRevisaoField;
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
	
}
