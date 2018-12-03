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

import br.com.palves.pbd.model.complemento.TratadorDeMascara;

public class FormularioCrudPJ extends JDialog{
	private RoundButton salvarButton;
	private FieldRedondo nomeField ;
	private FieldRedondo senhaField;
	private FieldRedondo loginField;
	private FieldRedondo idField;
	private FieldFormattedRedondo cnpjField;
	private FieldRedondo incEstadualField; 
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
	private RoundButton esquerdaButton,direitaButton;
	private RoundedCornerButton limparButton;
	private JTextField idEnd;
	private JLabel operacaoLabel;
	public FormularioCrudPJ() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(700,544);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 701, 76);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PESSOA JURIDICA");
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
		
		idEnd  = new JTextField();
		idEnd.setBounds(-100, 0, 38, 22);
		panelPessoa.add(idEnd);
		
		JPanel panelPj = new JPanel();
		panelPj.setBackground(new Color(255, 255, 255));
		panelPj.setBounds(0, 173, 484, 64);
		getContentPane().add(panelPj);
		panelPj.setLayout(null);
		
		
		cnpjField = new FieldFormattedRedondo();
		cnpjField.setHorizontalAlignment(SwingConstants.CENTER);
		cnpjField.setBounds(10, 25, 220, 33);
		cnpjField.setColumns(10);
		panelPj.add(cnpjField);
		
		JLabel cnpjLabel = new JLabel("CNPJ");
		cnpjLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		cnpjLabel.setBounds(22, 0, 82, 22);
		panelPj.add(cnpjLabel);
		
		ButtonGroup b = new ButtonGroup();
		
		incEstadualField = new FieldRedondo();
		incEstadualField.setColumns(10);
		incEstadualField.setBounds(265, 25, 209, 33);
		panelPj.add(incEstadualField);
		
		JLabel insc = new JLabel("Insc. Estadual");
		insc.setFont(new Font("Tahoma", Font.BOLD, 12));
		insc.setBounds(307, 0, 149, 22);
		panelPj.add(insc);
		
		JPanel enderecoPanel = new JPanel();
		enderecoPanel.setBackground(Color.WHITE);
		enderecoPanel.setBounds(0, 236, 484, 139);
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
		salvarButton.setBounds(10, 386, 89, 42);
		getContentPane().add(salvarButton);
		
		autoButton = new RoundButton("Salvar");
		autoButton.setText("Auto");
		autoButton.setBounds(109, 386, 89, 42);
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
		limparButton.setBackground(new Color(60, 179, 113));
		limparButton.setBounds(509, 218, 165, 42);
		getContentPane().add(limparButton);
		
		operacaoLabel = new JLabel("Modo Inser\u00E7\u00E3o");
		operacaoLabel.setForeground(new Color(255, 0, 0));
		operacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		operacaoLabel.setBounds(221, -1, 203, 22);
		panelPessoa.add(operacaoLabel);
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(545, 246, 140, 55);
		getContentPane().add(lblNavegao);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 684, 10);
		getContentPane().add(panel_1);
		
		TratadorDeMascara.aplicarMascaraCnpj(cnpjField);
		//setVisible(true);
	}
	
	public JLabel getOperacaoLabel() {
		return operacaoLabel;
	}

	public void setOperacaoLabel(JLabel operacaoLabel) {
		this.operacaoLabel = operacaoLabel;
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
	public FieldFormattedRedondo getCnpjField() {
		return cnpjField;
	}
	public void setCnpjField(FieldFormattedRedondo cnpjField) {
		this.cnpjField = cnpjField;
	}
	public FieldRedondo getIncEstadualField() {
		return incEstadualField;
	}
	public void setIncEstadualField(FieldRedondo incEstadualField) {
		this.incEstadualField = incEstadualField;
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
	public RoundButton getAutoButton() {
		return autoButton;
	}
	public void setAutoButton(RoundButton autoButton) {
		this.autoButton = autoButton;
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
	public RoundedCornerButton getLimparButton() {
		return limparButton;
	}
	public void setLimparButton(RoundedCornerButton limparButton) {
		this.limparButton = limparButton;
	}
	public JTextField getIdEnd() {
		return idEnd;
	}
	public void setIdEnd(JTextField idEnd) {
		this.idEnd = idEnd;
	}
	
}
