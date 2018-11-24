package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

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
	private RoundButton esquerdaButton,direitaButton;
	private JLabel lblCategoriaNormal;
	private JLabel lblTipoDeCmbio;
	private JLabel arLabel;
	private JComboBox cambioCombo;
	private JRadioButton simDvdRadio,simRadioRadio,simMp3Radio,simCameraReRadio,naoCameraReRadio,
	naoMp3Radio,naoRadioRadio,naoDvdRadio, naoArRadio;
	private ButtonGroup bAr,bDvd,bRadio,bMp3,bCamera,bDirecao,bCinto,bControle,bLigaLeve,bCategoria;
	private JPanel panelCG,panelCP,panelCN ;
	private JComboBox airBagCombo;
	private RoundButton salvarCPButton;
	private JRadioButton simLigaLeveRadio,simDirecaoRadio,simCintoRadi,simControlePoluicaoRadio,simCintoRadio;
	private RoundButton salvarCGButton;
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
		
		esquerdaButton = new RoundButton("Salvar");
		esquerdaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		esquerdaButton.setText("<");
		esquerdaButton.setForeground(Color.WHITE);
		esquerdaButton.setBackground(new Color(0, 128, 128));
		esquerdaButton.setBounds(751, 332, 43, 42);
		getContentPane().add(esquerdaButton);
		
		direitaButton = new RoundButton("Salvar");
		direitaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		direitaButton.setText(">");
		direitaButton.setForeground(Color.WHITE);
		direitaButton.setBackground(new Color(0, 128, 128));
		direitaButton.setBounds(804, 332, 43, 42);
		getContentPane().add(direitaButton);
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(734, 281, 140, 55);
		getContentPane().add(lblNavegao);
		
		JPanel panelDiv2 = new JPanel();
		panelDiv2.setBackground(new Color(0, 0, 0));
		panelDiv2.setBounds(0, 76, 884, 10);
		getContentPane().add(panelDiv2);
		
		idField = new FieldRedondo();
		idField.setBounds(774, 230, 38, 22);
		getContentPane().add(idField);
		idField.setColumns(10);
		
		panelCN = new JPanel();
		panelCN.setBackground(Color.WHITE);
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
		
		cambioCombo = new JComboBox();
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
		
		simDvdRadio = new JRadioButton("Sim");
		simDvdRadio.setSelected(true);
		simDvdRadio.setOpaque(false);
		simDvdRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simDvdRadio.setBackground(Color.WHITE);
		simDvdRadio.setBounds(178, 109, 69, 33);
		panelCN.add(simDvdRadio);
		bDvd.add(simDvdRadio);
		
		simRadioRadio = new JRadioButton("Sim");
		simRadioRadio.setSelected(true);
		simRadioRadio.setOpaque(false);
		simRadioRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simRadioRadio.setBackground(Color.WHITE);
		simRadioRadio.setBounds(178, 171, 69, 33);
		panelCN.add(simRadioRadio);
		bRadio.add(simRadioRadio);
		
		simMp3Radio = new JRadioButton("Sim");
		simMp3Radio.setSelected(true);
		simMp3Radio.setOpaque(false);
		simMp3Radio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simMp3Radio.setBackground(Color.WHITE);
		simMp3Radio.setBounds(178, 227, 69, 33);
		panelCN.add(simMp3Radio);
		bMp3.add(simMp3Radio);
		
		simCameraReRadio = new JRadioButton("Sim");
		simCameraReRadio.setSelected(true);
		simCameraReRadio.setOpaque(false);
		simCameraReRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		simCameraReRadio.setBackground(Color.WHITE);
		simCameraReRadio.setBounds(178, 294, 69, 33);
		panelCN.add(simCameraReRadio);
		bCamera.add(simCameraReRadio);
		
		naoCameraReRadio = new JRadioButton("N\u00E3o");
		naoCameraReRadio.setSelected(true);
		naoCameraReRadio.setOpaque(false);
		naoCameraReRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoCameraReRadio.setBackground(Color.WHITE);
		naoCameraReRadio.setBounds(236, 294, 69, 33);
		panelCN.add(naoCameraReRadio);
		bCamera.add(naoCameraReRadio);
		
		naoMp3Radio = new JRadioButton("N\u00E3o");
		naoMp3Radio.setSelected(true);
		naoMp3Radio.setOpaque(false);
		naoMp3Radio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoMp3Radio.setBackground(Color.WHITE);
		naoMp3Radio.setBounds(236, 227, 69, 33);
		panelCN.add(naoMp3Radio);
		bMp3.add(naoMp3Radio);
		
		naoRadioRadio = new JRadioButton("N\u00E3o");
		naoRadioRadio.setSelected(true);
		naoRadioRadio.setOpaque(false);
		naoRadioRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoRadioRadio.setBackground(Color.WHITE);
		naoRadioRadio.setBounds(236, 171, 69, 33);
		panelCN.add(naoRadioRadio);
		bRadio.add(naoRadioRadio);
		
		naoDvdRadio = new JRadioButton("N\u00E3o");
		naoDvdRadio.setSelected(true);
		naoDvdRadio.setOpaque(false);
		naoDvdRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoDvdRadio.setBackground(Color.WHITE);
		naoDvdRadio.setBounds(236, 109, 69, 33);
		panelCN.add(naoDvdRadio);
		bDvd.add(naoDvdRadio);
		
		naoArRadio = new JRadioButton("N\u00E3o");
		naoArRadio.setSelected(true);
		naoArRadio.setOpaque(false);
		naoArRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		naoArRadio.setBackground(Color.WHITE);
		naoArRadio.setBounds(236, 52, 69, 33);
		panelCN.add(naoArRadio);
		bAr.add(naoArRadio);
		
		panelCP = new JPanel();
		panelCP.setBackground(Color.WHITE);
		panelCP.setLayout(null);
		panelCP.setBounds(288, 87, 195, 418);
		getContentPane().add(panelCP);
		
		JLabel airBagLabel = new JLabel("Tipo de Air-Bag");
		airBagLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		airBagLabel.setBounds(10, 31, 132, 22);
		panelCP.add(airBagLabel);
		
		airBagCombo = new JComboBox();
		airBagCombo.setModel(new DefaultComboBoxModel(new String[] {"Simples-Dianteira", "Duplo-Dianteira", "Total"}));
		airBagCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		airBagCombo.setEditable(true);
		airBagCombo.setBackground(Color.WHITE);
		airBagCombo.setBounds(10, 52, 150, 33);
		panelCP.add(airBagCombo);
		
		salvarCPButton = new RoundButton("Salvar");
		salvarCPButton.setBounds(10, 365, 89, 42);
		panelCP.add(salvarCPButton);
		
		JLabel lblCategoriaPassageiro = new JLabel("CATEGORIA  CP");
		lblCategoriaPassageiro.setForeground(Color.BLACK);
		lblCategoriaPassageiro.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		lblCategoriaPassageiro.setBounds(10, 0, 188, 42);
		panelCP.add(lblCategoriaPassageiro);
		
		simLigaLeveRadio = new JRadioButton("Sim");
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
		
		simDirecaoRadio = new JRadioButton("Sim");
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
		
		simCintoRadio = new JRadioButton("Sim");
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
		
		simControlePoluicaoRadio = new JRadioButton("Sim");
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
		
		panelCG = new JPanel();
		panelCG.setBackground(Color.WHITE);
		panelCG.setLayout(null);
		panelCG.setBounds(483, 87, 209, 418);
		getContentPane().add(panelCG);
		
		salvarCGButton = new RoundButton("Salvar");
		salvarCGButton.setBounds(10, 365, 89, 42);
		panelCG.add(salvarCGButton);
		
		JLabel lblCategoriaCg = new JLabel("CATEGORIA  CG");
		lblCategoriaCg.setForeground(Color.BLACK);
		lblCategoriaCg.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
		lblCategoriaCg.setBounds(10, 0, 188, 42);
		panelCG.add(lblCategoriaCg);
		
		FieldRedondo capacidadeCargaField = new FieldRedondo();
		capacidadeCargaField.setColumns(10);
		capacidadeCargaField.setBounds(10, 53, 69, 33);
		panelCG.add(capacidadeCargaField);
		
		JLabel lblCapacidadeDaCarga = new JLabel("Capacidade da Carga");
		lblCapacidadeDaCarga.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCapacidadeDaCarga.setBounds(10, 31, 132, 22);
		panelCG.add(lblCapacidadeDaCarga);
		
		JLabel lblPotnciaDoMotor = new JLabel("Pot\u00EAncia do Motor");
		lblPotnciaDoMotor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPotnciaDoMotor.setBounds(10, 87, 132, 22);
		panelCG.add(lblPotnciaDoMotor);
		
		FieldRedondo potenciaMotorField = new FieldRedondo();
		potenciaMotorField.setColumns(10);
		potenciaMotorField.setBounds(10, 109, 69, 33);
		panelCG.add(potenciaMotorField);
		
		FieldRedondo volumeCombustivelField = new FieldRedondo();
		volumeCombustivelField.setColumns(10);
		volumeCombustivelField.setBounds(10, 168, 69, 33);
		panelCG.add(volumeCombustivelField);
		
		JLabel lblVolumeDoCombustivel = new JLabel("Volume do Combustivel");
		lblVolumeDoCombustivel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVolumeDoCombustivel.setBounds(10, 147, 188, 22);
		panelCG.add(lblVolumeDoCombustivel);
		
		JLabel lblDistnciaEixos = new JLabel("Dist\u00E2ncia Eixos");
		lblDistnciaEixos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDistnciaEixos.setBounds(10, 203, 132, 22);
		panelCG.add(lblDistnciaEixos);
		
		FieldRedondo dostanciaEixosField = new FieldRedondo();
		dostanciaEixosField.setColumns(10);
		dostanciaEixosField.setBounds(10, 222, 69, 33);
		panelCG.add(dostanciaEixosField);
		
		JLabel lblTipoDaEmbreagem = new JLabel("Tipo da Embreagem");
		lblTipoDaEmbreagem.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoDaEmbreagem.setBounds(10, 255, 132, 22);
		panelCG.add(lblTipoDaEmbreagem);
		
		JLabel lblConsumoKml = new JLabel("Consumo Km/L");
		lblConsumoKml.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConsumoKml.setBounds(10, 307, 132, 22);
		panelCG.add(lblConsumoKml);
		
		FieldRedondo consumoKmField = new FieldRedondo();
		consumoKmField.setColumns(10);
		consumoKmField.setBounds(10, 327, 69, 33);
		panelCG.add(consumoKmField);
		
		JComboBox tipoEmbreagemCombo = new JComboBox();
		tipoEmbreagemCombo.setModel(new DefaultComboBoxModel(new String[] {"Manual", "Hidraulica"}));
		tipoEmbreagemCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tipoEmbreagemCombo.setEditable(true);
		tipoEmbreagemCombo.setBackground(Color.WHITE);
		tipoEmbreagemCombo.setBounds(10, 275, 150, 33);
		panelCG.add(tipoEmbreagemCombo);
		
		JLabel tonelada = new JLabel("Toneladas");
		tonelada.setFont(new Font("Tahoma", Font.BOLD, 12));
		tonelada.setBounds(89, 57, 71, 22);
		panelCG.add(tonelada);
		
		bCategoria = new ButtonGroup();
		JRadioButton cnRadio = new JRadioButton("CN");
		cnRadio.setSelected(true);
		cnRadio.setOpaque(false);
		cnRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		cnRadio.setBackground(Color.WHITE);
		cnRadio.setBounds(698, 269, 69, 33);
		getContentPane().add(cnRadio);
		bCategoria.add(cnRadio);
		
		JRadioButton cpRadio = new JRadioButton("CP");
		cpRadio.setSelected(true);
		cpRadio.setOpaque(false);
		cpRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		cpRadio.setBackground(Color.WHITE);
		cpRadio.setBounds(761, 269, 69, 33);
		getContentPane().add(cpRadio);
		bCategoria.add(cpRadio);
		JRadioButton cgRadio = new JRadioButton("CG");
		cgRadio.setSelected(true);
		cgRadio.setOpaque(false);
		cgRadio.setFont(new Font("Tahoma", Font.BOLD, 12));
		cgRadio.setBackground(Color.WHITE);
		cgRadio.setBounds(830, 269, 69, 33);
		bCategoria.add(cgRadio);
		getContentPane().add(cgRadio);
		//setVisible(true);
	}
}
