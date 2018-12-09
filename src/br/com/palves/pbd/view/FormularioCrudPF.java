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

import com.toedter.calendar.JDateChooser;

import br.com.palves.pbd.model.complemento.TratadorDeMascara;

public class FormularioCrudPF extends JDialog{
	private RoundButton salvarButton;
	private FieldRedondo nomeField ;
	private FieldRedondo senhaField;
	private FieldRedondo loginField;
	private FieldRedondo idField;
	private FieldFormattedRedondo cpfField;
	private JRadioButton sexoMradio,sexoFradio;
	private JDateChooser dataNascimentoField; 
	private FieldRedondo nHabilitacaoField ;
	private JDateChooser dataVencHabField;
	private FieldRedondo ruaField;
	private FieldRedondo numeroField;
	private FieldRedondo bairroField;
	private FieldRedondo cepField;
	private FieldRedondo ufField;
	private FieldRedondo cidadeField;
	private FieldRedondo buscarField;
	private RoundButton irButton;
	private RoundButton allButton;
	private JTextField idEnd;
	private RoundedCornerButton removerButton;
	private RoundButton esquerdaButton,direitaButton;
	private RoundedCornerButton limparButton;
	private JLabel operacaoLabel;
	public FormularioCrudPF() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(700,544);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
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
		idEnd  = new JTextField();
		idEnd.setVisible(false);
		panelPessoa.add(idEnd);
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
		
		operacaoLabel = new JLabel("Modo Inser\u00E7\u00E3o");
		operacaoLabel.setForeground(new Color(255, 0, 0));
		operacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		operacaoLabel.setBounds(221, -1, 203, 22);
		panelPessoa.add(operacaoLabel);

		JPanel panelPf = new JPanel();
		panelPf.setBackground(new Color(255, 255, 255));
		panelPf.setBounds(0, 173, 484, 129);
		getContentPane().add(panelPf);
		panelPf.setLayout(null);

		cpfField = new FieldFormattedRedondo();
		cpfField.setHorizontalAlignment(SwingConstants.CENTER);
		cpfField.setBounds(10, 25, 150, 33);
		cpfField.setColumns(10);
		TratadorDeMascara.aplicarMascaraCPF(cpfField);
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

		dataNascimentoField = new JDateChooser();
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

		dataVencHabField = new JDateChooser();
		dataVencHabField.setBounds(324, 87, 150, 33);
		panelPf.add(dataVencHabField);

		JLabel lblDataVencimentoHabilitao = new JLabel("Data de  Venc. Habilita\u00E7\u00E3o");
		lblDataVencimentoHabilitao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataVencimentoHabilitao.setBounds(306, 64, 178, 22);
		panelPf.add(lblDataVencimentoHabilitao);

		JPanel enderecoPanel = new JPanel();
		enderecoPanel.setBackground(Color.WHITE);
		enderecoPanel.setBounds(0, 302, 484, 203);
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
		
		salvarButton = new RoundButton("Salvar");
		salvarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		salvarButton.setBounds(20, 128, 68, 68);
		enderecoPanel.add(salvarButton);
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
		limparButton.setText("Limpar");
		limparButton.setForeground(Color.WHITE);
		limparButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		limparButton.setBackground(new Color(60,179,113));
		limparButton.setBounds(509, 218, 165, 42);
		getContentPane().add(limparButton);
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(534, 247, 140, 55);
		getContentPane().add(lblNavegao);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		TratadorDeMascara.soNumero(numeroField);
		TratadorDeMascara.soNumero(nHabilitacaoField);

		//setModal(true);
		//setVisible(true);
	}
	
	public RoundButton getSalvarButton() {
		return salvarButton;
	}
	public void setSalvarButton(RoundButton salvarButton) {
		this.salvarButton = salvarButton;
	}
	public FieldRedondo getNomeField() {
		return nomeField;
	}
	public void setNomeField(FieldRedondo nomeField) {
		this.nomeField = nomeField;
	}
	public FieldRedondo getSenhaField() {
		return senhaField;
	}
	public void setSenhaField(FieldRedondo senhaField) {
		this.senhaField = senhaField;
	}
	public FieldRedondo getLoginField() {
		return loginField;
	}
	public void setLoginField(FieldRedondo loginField) {
		this.loginField = loginField;
	}
	public FieldRedondo getIdField() {
		return idField;
	}
	public void setIdField(FieldRedondo idField) {
		this.idField = idField;
	}
	public FieldFormattedRedondo getCpfField() {
		return cpfField;
	}
	public void setCpfField(FieldFormattedRedondo cpfField) {
		this.cpfField = cpfField;
	}
	public JRadioButton getSexoMradio() {
		return sexoMradio;
	}
	public void setSexoMradio(JRadioButton sexoMradio) {
		this.sexoMradio = sexoMradio;
	}
	public JRadioButton getSexoFradio() {
		return sexoFradio;
	}
	public void setSexoFradio(JRadioButton sexoFradio) {
		this.sexoFradio = sexoFradio;
	}
	public JDateChooser getDataNascimentoField() {
		return dataNascimentoField;
	}
	public void setDataNascimentoField(JDateChooser dataNascimentoField) {
		this.dataNascimentoField = dataNascimentoField;
	}
	public FieldRedondo getnHabilitacaoField() {
		return nHabilitacaoField;
	}
	public void setnHabilitacaoField(FieldRedondo nHabilitacaoField) {
		this.nHabilitacaoField = nHabilitacaoField;
	}
	public JDateChooser getDataVencHabField() {
		return dataVencHabField;
	}
	public void setDataVencHabField(JDateChooser dataVencHabField) {
		this.dataVencHabField = dataVencHabField;
	}
	public FieldRedondo getRuaField() {
		return ruaField;
	}
	public void setRuaField(FieldRedondo ruaField) {
		this.ruaField = ruaField;
	}
	public FieldRedondo getNumeroField() {
		return numeroField;
	}
	public void setNumeroField(FieldRedondo numeroField) {
		this.numeroField = numeroField;
	}
	public FieldRedondo getBairroField() {
		return bairroField;
	}
	public void setBairroField(FieldRedondo bairroField) {
		this.bairroField = bairroField;
	}
	public FieldRedondo getCepField() {
		return cepField;
	}
	public void setCepField(FieldRedondo cepField) {
		this.cepField = cepField;
	}
	public FieldRedondo getUfField() {
		return ufField;
	}
	public void setUfField(FieldRedondo ufField) {
		this.ufField = ufField;
	}
	public FieldRedondo getCidadeField() {
		return cidadeField;
	}
	public void setCidadeField(FieldRedondo cidadeField) {
		this.cidadeField = cidadeField;
	}
	public FieldRedondo getBuscarField() {
		return buscarField;
	}
	public void setBuscarField(FieldRedondo buscarField) {
		this.buscarField = buscarField;
	}
	public RoundButton getIrButton() {
		return irButton;
	}
	public void setIrButton(RoundButton irButton) {
		this.irButton = irButton;
	}
	public RoundButton getAllButton() {
		return allButton;
	}
	public void setAllButton(RoundButton allButton) {
		this.allButton = allButton;
	}
	public RoundedCornerButton getRemoverButton() {
		return removerButton;
	}
	public void setRemoverButton(RoundedCornerButton removerButton) {
		this.removerButton = removerButton;
	}
	public RoundButton getEsquerdaButton() {
		return esquerdaButton;
	}
	public void setEsquerdaButton(RoundButton esquerdaButton) {
		this.esquerdaButton = esquerdaButton;
	}
	public RoundButton getDireitaButton() {
		return direitaButton;
	}
	public void setDireitaButton(RoundButton direitaButton) {
		this.direitaButton = direitaButton;
	}

	public JTextField getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(JTextField idEnd) {
		this.idEnd = idEnd;
	}

	public RoundedCornerButton getLimparButton() {
		return limparButton;
	}

	public void setLimparButton(RoundedCornerButton limparButton) {
		this.limparButton = limparButton;
	}

	public JLabel getOperacaoLabel() {
		return operacaoLabel;
	}

	public void setOperacaoLabel(JLabel operacaoLabel) {
		this.operacaoLabel = operacaoLabel;
	}
}
