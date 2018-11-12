package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FormularioCrudCategoria extends JDialog{
	private RoundButton salvarCNButton;
	private FieldRedondo nomeField ;
	private FieldRedondo valorField;
	private FieldRedondo nPassageirosField;
	private FieldRedondo idField;
	private JRadioButton simArRadio;
	private FieldRedondo buscarField;
	private RoundButton irButton;
	private RoundButton allButton;
	private RoundedCornerButton removerButton;
	private RoundButton esquerdaField,direitaField;
	private JLabel lblCategoriaNormal;
	private JLabel lblTipoDeCmbio;
	private JLabel arLabel;
	private ButtonGroup bAr,bDvd,bRadio,bMp3,bCamera,bDirecao,bCinto,bControle,bLigaLeve;
	public FormularioCrudCategoria() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(900,544);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panelDiv1 = new JPanel();
		panelDiv1.setBackground(new Color(0, 204, 153));
		panelDiv1.setBounds(0, 0, 899, 76);
		getContentPane().add(panelDiv1);
		panelDiv1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CATEGORIAS");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		lblNewLabel.setBounds(29, 11, 642, 54);
		panelDiv1.add(lblNewLabel);
		
		 bAr = new ButtonGroup();
		 bDvd = new ButtonGroup();
		 bRadio = new ButtonGroup();
		 bMp3 = new ButtonGroup();
		 bCamera = new ButtonGroup();
		 bDirecao = new ButtonGroup();
		 bCinto = new ButtonGroup();
		 bControle = new ButtonGroup();
		 bLigaLeve = new ButtonGroup();
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(690, 87, 11, 423);
		getContentPane().add(separator);
		
		buscarField = new FieldRedondo();
		buscarField.setColumns(10);
		buscarField.setBounds(732, 133, 51, 33);
		getContentPane().add(buscarField);
		
		JLabel lblBusca = new JLabel("Busca/Remove");
		lblBusca.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblBusca.setBounds(722, 76, 152, 55);
		getContentPane().add(lblBusca);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(702, 141, 29, 14);
		getContentPane().add(lblId);
		
		irButton = new RoundButton("Salvar");
		irButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		irButton.setForeground(Color.WHITE);
		irButton.setBackground(Color.BLUE);
		irButton.setText("Ir");
		irButton.setBounds(787, 128, 43, 42);
		getContentPane().add(irButton);
		
		allButton = new RoundButton("Salvar");
		allButton.setForeground(Color.WHITE);
		allButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		allButton.setText("All");
		allButton.setBackground(Color.BLACK);
		allButton.setBounds(831, 128, 43, 42);
		getContentPane().add(allButton);
		
		removerButton = new RoundedCornerButton("Salvar");
		removerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		removerButton.setText("Remover");
		removerButton.setForeground(Color.WHITE);
		removerButton.setBackground(Color.RED);
		removerButton.setBounds(709, 177, 165, 42);
		getContentPane().add(removerButton);
		
		esquerdaField = new RoundButton("Salvar");
		esquerdaField.setFont(new Font("Tahoma", Font.BOLD, 13));
		esquerdaField.setText("<");
		esquerdaField.setForeground(Color.WHITE);
		esquerdaField.setBackground(new Color(0, 128, 128));
		esquerdaField.setBounds(750, 292, 43, 42);
		getContentPane().add(esquerdaField);
		
		direitaField = new RoundButton("Salvar");
		direitaField.setFont(new Font("Tahoma", Font.BOLD, 13));
		direitaField.setText(">");
		direitaField.setForeground(Color.WHITE);
		direitaField.setBackground(new Color(0, 128, 128));
		direitaField.setBounds(803, 292, 43, 42);
		getContentPane().add(direitaField);
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(744, 245, 140, 55);
		getContentPane().add(lblNavegao);
		
		JPanel panelDiv2 = new JPanel();
		panelDiv2.setBackground(new Color(0, 0, 0));
		panelDiv2.setBounds(0, 76, 884, 10);
		getContentPane().add(panelDiv2);
		
		idField = new FieldRedondo();
		idField.setBounds(774, 230, 38, 22);
		getContentPane().add(idField);
		idField.setColumns(10);
		
		JPanel panelCN = new JPanel();
		panelCN.setBounds(0, 87, 289, 418);
		getContentPane().add(panelCN);
		panelCN.setLayout(null);
		
		nomeField = new FieldRedondo();
		nomeField.setBounds(10, 52, 150, 33);
		panelCN.add(nomeField);
		nomeField.setColumns(10);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setBounds(20, 31, 82, 22);
		panelCN.add(nomeLabel);
		nomeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel loginLabel = new JLabel("N\u00BA Passageiros");
		loginLabel.setBounds(20, 84, 102, 22);
		panelCN.add(loginLabel);
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		nPassageirosField = new FieldRedondo();
		nPassageirosField.setBounds(10, 109, 69, 33);
		panelCN.add(nPassageirosField);
		nPassageirosField.setColumns(10);
		
		valorField = new FieldRedondo();
		valorField.setBounds(10, 166, 86, 33);
		panelCN.add(valorField);
		valorField.setColumns(10);
		
		JLabel senhaLabel = new JLabel("Valor R$");
		senhaLabel.setBounds(20, 144, 82, 22);
		panelCN.add(senhaLabel);
		senhaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		FieldRedondo horaLimpeza = new FieldRedondo();
		horaLimpeza.setBounds(10, 222, 69, 33);
		panelCN.add(horaLimpeza);
		horaLimpeza.setColumns(10);
		
		JLabel lblHorasParaLimpeza = new JLabel("Horas para limpeza");
		lblHorasParaLimpeza.setBounds(20, 200, 132, 22);
		panelCN.add(lblHorasParaLimpeza);
		lblHorasParaLimpeza.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblTipoDeCmbio = new JLabel("Tipo de C\u00E2mbio");
		lblTipoDeCmbio.setBounds(20, 266, 132, 22);
		panelCN.add(lblTipoDeCmbio);
		lblTipoDeCmbio.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JComboBox cambioCombo = new JComboBox();
		cambioCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		cambioCombo.setBounds(10, 295, 150, 33);
		panelCN.add(cambioCombo);
		cambioCombo.setModel(new DefaultComboBoxModel(new String[] {"Automatico", "Manual"}));
		cambioCombo.setEditable(true);
		cambioCombo.setBackground(Color.WHITE);
		
		salvarCNButton = new RoundButton("Salvar");
		salvarCNButton.setBounds(10, 365, 89, 42);
		panelCN.add(salvarCNButton);
		
		lblCategoriaNormal = new JLabel("CATEGORIA CN");
		lblCategoriaNormal.setBounds(10, -1, 286, 42);
		panelCN.add(lblCategoriaNormal);
		lblCategoriaNormal.setForeground(Color.BLACK);
		lblCategoriaNormal.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		simArRadio = new JRadioButton("Sim");
		simArRadio.setOpaque(false);
		simArRadio.setBounds(178, 51, 69, 33);
		panelCN.add(simArRadio);
		simArRadio.setBackground(new Color(255, 255, 255));
		simArRadio.setSelected(true);
		simArRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		bAr.add(simArRadio);
		
		arLabel = new JLabel("Ar Condicionado");
		arLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		arLabel.setBounds(178, 31, 118, 22);
		panelCN.add(arLabel);
		
		JLabel dvdLabel = new JLabel("DVD");
		dvdLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		dvdLabel.setBounds(178, 84, 82, 22);
		panelCN.add(dvdLabel);
		
		JLabel radioLabel = new JLabel("Radio");
		radioLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		radioLabel.setBounds(178, 149, 82, 22);
		panelCN.add(radioLabel);
		
		JLabel mp3Label = new JLabel("MP3");
		mp3Label.setFont(new Font("Tahoma", Font.BOLD, 12));
		mp3Label.setBounds(178, 205, 82, 22);
		panelCN.add(mp3Label);
		
		JLabel cameraLabel = new JLabel("Camera R\u00E9");
		cameraLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cameraLabel.setBounds(178, 266, 82, 22);
		panelCN.add(cameraLabel);
		
		JRadioButton simDvdRadio = new JRadioButton("Sim");
		simDvdRadio.setSelected(true);
		simDvdRadio.setOpaque(false);
		simDvdRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simDvdRadio.setBackground(Color.WHITE);
		simDvdRadio.setBounds(178, 109, 69, 33);
		panelCN.add(simDvdRadio);
		bDvd.add(simDvdRadio);
		
		JRadioButton simRadioRadio = new JRadioButton("Sim");
		simRadioRadio.setSelected(true);
		simRadioRadio.setOpaque(false);
		simRadioRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simRadioRadio.setBackground(Color.WHITE);
		simRadioRadio.setBounds(178, 171, 69, 33);
		panelCN.add(simRadioRadio);
		bRadio.add(simRadioRadio);
		
		JRadioButton simMp3Radio = new JRadioButton("Sim");
		simMp3Radio.setSelected(true);
		simMp3Radio.setOpaque(false);
		simMp3Radio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simMp3Radio.setBackground(Color.WHITE);
		simMp3Radio.setBounds(178, 227, 69, 33);
		panelCN.add(simMp3Radio);
		bMp3.add(simMp3Radio);
		
		JRadioButton simCameraReRadio = new JRadioButton("Sim");
		simCameraReRadio.setSelected(true);
		simCameraReRadio.setOpaque(false);
		simCameraReRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simCameraReRadio.setBackground(Color.WHITE);
		simCameraReRadio.setBounds(178, 294, 69, 33);
		panelCN.add(simCameraReRadio);
		bCamera.add(simCameraReRadio);
		
		JRadioButton naoCameraReRadio = new JRadioButton("N\u00E3o");
		naoCameraReRadio.setSelected(true);
		naoCameraReRadio.setOpaque(false);
		naoCameraReRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoCameraReRadio.setBackground(Color.WHITE);
		naoCameraReRadio.setBounds(236, 294, 69, 33);
		panelCN.add(naoCameraReRadio);
		bCamera.add(naoCameraReRadio);
		
		JRadioButton naoMp3Radio = new JRadioButton("N\u00E3o");
		naoMp3Radio.setSelected(true);
		naoMp3Radio.setOpaque(false);
		naoMp3Radio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoMp3Radio.setBackground(Color.WHITE);
		naoMp3Radio.setBounds(236, 227, 69, 33);
		panelCN.add(naoMp3Radio);
		bMp3.add(naoMp3Radio);
		
		JRadioButton naoRadioRadio = new JRadioButton("N\u00E3o");
		naoRadioRadio.setSelected(true);
		naoRadioRadio.setOpaque(false);
		naoRadioRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoRadioRadio.setBackground(Color.WHITE);
		naoRadioRadio.setBounds(236, 171, 69, 33);
		panelCN.add(naoRadioRadio);
		bRadio.add(naoRadioRadio);
		
		JRadioButton naoDvdRadio = new JRadioButton("N\u00E3o");
		naoDvdRadio.setSelected(true);
		naoDvdRadio.setOpaque(false);
		naoDvdRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoDvdRadio.setBackground(Color.WHITE);
		naoDvdRadio.setBounds(236, 109, 69, 33);
		panelCN.add(naoDvdRadio);
		bDvd.add(naoDvdRadio);
		
		JRadioButton naoArRadio = new JRadioButton("N\u00E3o");
		naoArRadio.setSelected(true);
		naoArRadio.setOpaque(false);
		naoArRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoArRadio.setBackground(Color.WHITE);
		naoArRadio.setBounds(236, 52, 69, 33);
		panelCN.add(naoArRadio);
		bAr.add(naoArRadio);
		
		JPanel panelCP = new JPanel();
		panelCP.setLayout(null);
		panelCP.setBounds(288, 87, 195, 418);
		getContentPane().add(panelCP);
		
		JLabel airBagLabel = new JLabel("Tipo de Air-Bag");
		airBagLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		airBagLabel.setBounds(10, 31, 132, 22);
		panelCP.add(airBagLabel);
		
		JComboBox airBagCombo = new JComboBox();
		airBagCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		airBagCombo.setEditable(true);
		airBagCombo.setBackground(Color.WHITE);
		airBagCombo.setBounds(10, 52, 150, 33);
		panelCP.add(airBagCombo);
		
		RoundButton salvarCPButton = new RoundButton("Salvar");
		salvarCPButton.setBounds(10, 365, 89, 42);
		panelCP.add(salvarCPButton);
		
		JLabel lblCategoriaPassageiro = new JLabel("CATEGORIA  CP");
		lblCategoriaPassageiro.setForeground(Color.BLACK);
		lblCategoriaPassageiro.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		lblCategoriaPassageiro.setBounds(10, 0, 188, 42);
		panelCP.add(lblCategoriaPassageiro);
		
		JRadioButton simLigaLeveRadio = new JRadioButton("Sim");
		simLigaLeveRadio.setSelected(true);
		simLigaLeveRadio.setOpaque(false);
		simLigaLeveRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simLigaLeveRadio.setBackground(Color.WHITE);
		simLigaLeveRadio.setBounds(10, 227, 69, 33);
		panelCP.add(simLigaLeveRadio);
		bLigaLeve.add(simLigaLeveRadio);
		
		JRadioButton naoLigaLeveRadio = new JRadioButton("N\u00E3o");
		naoLigaLeveRadio.setSelected(true);
		naoLigaLeveRadio.setOpaque(false);
		naoLigaLeveRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoLigaLeveRadio.setBackground(Color.WHITE);
		naoLigaLeveRadio.setBounds(73, 227, 69, 33);
		panelCP.add(naoLigaLeveRadio);
		bLigaLeve.add(naoLigaLeveRadio);
		
		JLabel direcaoAssistidaLabel = new JLabel("Dire\u00E7\u00E3o Assistida");
		direcaoAssistidaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		direcaoAssistidaLabel.setBounds(10, 84, 118, 22);
		panelCP.add(direcaoAssistidaLabel);
		
		JRadioButton simDirecaoRadio = new JRadioButton("Sim");
		simDirecaoRadio.setSelected(true);
		simDirecaoRadio.setOpaque(false);
		simDirecaoRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simDirecaoRadio.setBackground(Color.WHITE);
		simDirecaoRadio.setBounds(10, 109, 69, 33);
		panelCP.add(simDirecaoRadio);
		bDirecao.add(simDirecaoRadio);
		
		JRadioButton naoDirecaoRadio = new JRadioButton("N\u00E3o");
		naoDirecaoRadio.setSelected(true);
		naoDirecaoRadio.setOpaque(false);
		naoDirecaoRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoDirecaoRadio.setBackground(Color.WHITE);
		naoDirecaoRadio.setBounds(73, 109, 69, 33);
		panelCP.add(naoDirecaoRadio);
		bDirecao.add(naoDirecaoRadio);
		
		JLabel lblCintoDeSeguraca = new JLabel("Cinto de Seguran\u00E7a Traseiro");
		lblCintoDeSeguraca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCintoDeSeguraca.setBounds(10, 149, 188, 22);
		panelCP.add(lblCintoDeSeguraca);
		
		JRadioButton simCintoRadio = new JRadioButton("Sim");
		simCintoRadio.setSelected(true);
		simCintoRadio.setOpaque(false);
		simCintoRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simCintoRadio.setBackground(Color.WHITE);
		simCintoRadio.setBounds(10, 171, 69, 33);
		panelCP.add(simCintoRadio);
		bCinto.add(simCintoRadio);
		
		JRadioButton naoCintoRadio = new JRadioButton("N\u00E3o");
		naoCintoRadio.setSelected(true);
		naoCintoRadio.setOpaque(false);
		naoCintoRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoCintoRadio.setBackground(Color.WHITE);
		naoCintoRadio.setBounds(73, 171, 69, 33);
		panelCP.add(naoCintoRadio);
		bCinto.add(naoCintoRadio);
		
		JLabel lblRodaDeLiga = new JLabel("Roda de Liga Leve");
		lblRodaDeLiga.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRodaDeLiga.setBounds(10, 205, 188, 22);
		panelCP.add(lblRodaDeLiga);
		
		JLabel controlePouluicaoLabel = new JLabel("Controle de Polui\u00E7\u00E3o do Ar");
		controlePouluicaoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		controlePouluicaoLabel.setBounds(10, 266, 188, 22);
		panelCP.add(controlePouluicaoLabel);
		
		JRadioButton simControlePoluicaoRadio = new JRadioButton("Sim");
		simControlePoluicaoRadio.setSelected(true);
		simControlePoluicaoRadio.setOpaque(false);
		simControlePoluicaoRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simControlePoluicaoRadio.setBackground(Color.WHITE);
		simControlePoluicaoRadio.setBounds(10, 294, 69, 33);
		panelCP.add(simControlePoluicaoRadio);
		bControle.add(simControlePoluicaoRadio);
		
		JRadioButton naoControlePoluicaoRadio = new JRadioButton("N\u00E3o");
		naoControlePoluicaoRadio.setSelected(true);
		naoControlePoluicaoRadio.setOpaque(false);
		naoControlePoluicaoRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoControlePoluicaoRadio.setBackground(Color.WHITE);
		naoControlePoluicaoRadio.setBounds(73, 294, 69, 33);
		panelCP.add(naoControlePoluicaoRadio);
		bControle.add(naoControlePoluicaoRadio);
		//setVisible(true);
	}
}
