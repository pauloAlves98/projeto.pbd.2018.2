package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.toedter.calendar.JDateChooser;
/**
 * @author P Alves
 * */
public class FormularioCrudLocacao extends JDialog{
	private RoundButton salvarButton;
	private FieldRedondo idField;
	private FieldRedondo buscarField;
	private RoundButton irButton;
	private RoundButton allButton;
	private RoundedCornerButton removerButton;
	private RoundButton esquerdaButton,direitaButton;
	private JTextField filialEntregaIdField;
	private JTextField veiculoIdField;
	private JComboBox motoristaCombo;
	private JTextField idMotoristaField;
	private FieldFormattedRedondo horaRealizacaoField;
	private JDateChooser entregaDateChooser;
	private FieldFormattedRedondo horaEntregaField ;
	private JRadioButton kmLivreRadio;
	private JTextField idFilialLocatariaField;
	private JComboBox filialLocatariaCombo;
	private JComboBox filialEntregaCombo;
	private JTextArea filialEntregaTextArea;
	private JTextArea filialLocatariaTextArea;
	private JComboBox veiculoCombo;
	private JTextArea veiculoTextArea;
	private RoundedCornerButton limparButton;
	private JDateChooser RealizacaoDataChooser;
	private JRadioButton rdbtnKmControle;
	private JTextField clienteField;
	private JComboBox clienteCombo;
	 private JLabel operacaoLabel;
	public  FormularioCrudLocacao() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setSize(825,606);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JGradientePanel(new Color(0, 64, 93),Color.BLACK);
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(0, 0, 809, 76);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel Locação = new JLabel("LOCA\u00C7\u00C3O");
		Locação.setForeground(new Color(255, 255, 255));
		Locação.setFont(new Font("Bradley Hand ITC", Font.BOLD, 38));
		Locação.setBounds(29, 11, 642, 54);
		panel.add(Locação);
		
		JPanel panelVeiculo = new JPanel();
		panelVeiculo.setBackground(new Color(255, 255, 255));
		panelVeiculo.setBounds(0, 87, 649, 480);
		getContentPane().add(panelVeiculo);
		panelVeiculo.setLayout(null);
		
		idField = new FieldRedondo();
		idField.setColumns(10);
		idField.setBounds(20, 0, 38, 22);
		panelVeiculo.add(idField);
		
		salvarButton = new RoundButton("Salvar");
		salvarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		//salvarButton.setBounds(-48, 366, 150, 42);
		salvarButton.setBackground(new Color(0, 64, 93));
		//salvarButton.setInitialColor();
		//salvarButton.setInitialColor(Color.black);
		salvarButton.setForeground(Color.WHITE);
		//salvarButton.setBounds(10, 427, 95, 42);
		salvarButton.setBounds(10, 287, 74, 75);
		panelVeiculo.add(salvarButton);
		
		JLabel lblDatahoraRealizao = new JLabel("Data/Hora Realiza\u00E7\u00E3o");
		lblDatahoraRealizao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDatahoraRealizao.setBounds(10, 33, 177, 22);
		panelVeiculo.add(lblDatahoraRealizao);
		
		 RealizacaoDataChooser = new JDateChooser();
		RealizacaoDataChooser.setBounds(10, 59, 89, 33);
		panelVeiculo.add(RealizacaoDataChooser);
		
		horaRealizacaoField = new FieldFormattedRedondo();
		horaRealizacaoField.setColumns(10);
		horaRealizacaoField.setBounds(103, 59, 105, 33);
		panelVeiculo.add(horaRealizacaoField);
		horaRealizacaoField.setToolTipText("Preenchido quando Conclui a locação");
		
		JLabel lblDatahoraPrevistaPara = new JLabel("Data/Hora Prevista Para entrega");
		lblDatahoraPrevistaPara.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDatahoraPrevistaPara.setBounds(10, 103, 211, 22);
		panelVeiculo.add(lblDatahoraPrevistaPara);
		
		entregaDateChooser = new JDateChooser();
		entregaDateChooser.setBounds(10, 136, 89, 33);
		panelVeiculo.add(entregaDateChooser);
		
		horaEntregaField = new FieldFormattedRedondo();
		horaEntregaField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		horaEntregaField.setColumns(10);
		horaEntregaField.setBounds(103, 136, 105, 33);
		panelVeiculo.add(horaEntregaField);
		
		JLabel lblModalidadeDeLocao = new JLabel("Modalidade de Loca\u00E7\u00E3o");
		lblModalidadeDeLocao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModalidadeDeLocao.setBounds(10, 180, 211, 22);
		panelVeiculo.add(lblModalidadeDeLocao);
		
		kmLivreRadio = new JRadioButton("Km Livre");
		kmLivreRadio.setSelected(true);
		kmLivreRadio.setFont(new Font("Tahoma", Font.BOLD, 11));
		kmLivreRadio.setForeground(Color.BLACK);
		kmLivreRadio.setBounds(0, 209, 95, 23);
		panelVeiculo.add(kmLivreRadio);
		kmLivreRadio.setOpaque(false);
		
		rdbtnKmControle = new JRadioButton("Km Controle");
		rdbtnKmControle.setOpaque(false);
		rdbtnKmControle.setForeground(Color.BLACK);
		rdbtnKmControle.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnKmControle.setBounds(83, 209, 109, 23);
		panelVeiculo.add(rdbtnKmControle);
		
		idFilialLocatariaField = new JTextField();
		idFilialLocatariaField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		idFilialLocatariaField.setColumns(10);
		idFilialLocatariaField.setBounds(218, 59, 48, 33);
		panelVeiculo.add(idFilialLocatariaField);
		
		JLabel lblFilialLocataria = new JLabel("Filial Locataria");
		lblFilialLocataria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFilialLocataria.setBounds(215, 33, 211, 22);
		panelVeiculo.add(lblFilialLocataria);
		
		filialLocatariaCombo = new JComboBox();
		filialLocatariaCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		filialLocatariaCombo.setBackground(Color.WHITE);
		filialLocatariaCombo.setBounds(260, 59, 150, 33);
		panelVeiculo.add(filialLocatariaCombo);
		
		JLabel lblFilialEntrega = new JLabel("Filial Entrega");
		lblFilialEntrega.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFilialEntrega.setBounds(218, 257, 211, 22);
		panelVeiculo.add(lblFilialEntrega);
		
		filialEntregaIdField = new JTextField();
		filialEntregaIdField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		filialEntregaIdField.setColumns(10);
		filialEntregaIdField.setBounds(215, 279, 48, 33);
		panelVeiculo.add(filialEntregaIdField);
		
		filialEntregaCombo = new JComboBox();
		filialEntregaCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		filialEntregaCombo.setBackground(Color.WHITE);
		filialEntregaCombo.setBounds(260, 279, 150, 33);
		panelVeiculo.add(filialEntregaCombo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(218, 92, 195, 164);
		panelVeiculo.add(scrollPane_1);
		//scrollPane_1.setBorder(null);
		scrollPane_1.setOpaque(false);
		
		filialLocatariaTextArea = new JTextArea();
		filialLocatariaTextArea.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(filialLocatariaTextArea);
		filialLocatariaTextArea.setLineWrap(true);
		filialLocatariaTextArea.setFont(new Font("Monospaced", Font.BOLD, 12));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(215, 312, 195, 157);
		panelVeiculo.add(scrollPane_2);
		
		filialEntregaTextArea = new JTextArea();
		filialEntregaTextArea.setLineWrap(true);
		filialEntregaTextArea.setFont(new Font("Monospaced", Font.BOLD, 12));
		scrollPane_2.setViewportView(filialEntregaTextArea);
		
		veiculoCombo = new JComboBox();
		veiculoCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		veiculoCombo.setBackground(Color.WHITE);
		veiculoCombo.setBounds(449, 59, 190, 33);
		panelVeiculo.add(veiculoCombo);
		
		veiculoIdField = new JTextField();
		veiculoIdField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		veiculoIdField.setColumns(10);
		veiculoIdField.setBounds(413, 59, 48, 33);
		panelVeiculo.add(veiculoIdField);
		
		JLabel lblVeiculos = new JLabel("Veiculo");
		lblVeiculos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVeiculos.setBounds(413, 33, 211, 22);
		panelVeiculo.add(lblVeiculos);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(413, 92, 226, 164);
		panelVeiculo.add(scrollPane_3);
		
		veiculoTextArea = new JTextArea();
		veiculoTextArea.setLineWrap(true);
		veiculoTextArea.setFont(new Font("Monospaced", Font.BOLD, 12));
		veiculoTextArea.setBackground(Color.WHITE);
		scrollPane_3.setViewportView(veiculoTextArea);
		
		JLabel lblMotorista = new JLabel("Motorista");
		lblMotorista.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMotorista.setBounds(413, 258, 211, 22);
		panelVeiculo.add(lblMotorista);
		
		motoristaCombo = new JComboBox();
		motoristaCombo.setBackground(Color.WHITE);
		motoristaCombo.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		motoristaCombo.setBounds(467, 279, 157, 33);
		panelVeiculo.add(motoristaCombo);
		
		idMotoristaField = new JTextField();
		idMotoristaField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		idMotoristaField.setColumns(10);
		idMotoristaField.setBounds(413, 279, 57, 33);
		panelVeiculo.add(idMotoristaField);
		
		clienteCombo = new JComboBox();
		clienteCombo.setBackground(Color.WHITE);
		clienteCombo.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		clienteCombo.setBounds(467, 350, 157, 33);
		panelVeiculo.add(clienteCombo);
		
		clienteField = new JTextField();
		clienteField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		clienteField.setColumns(10);
		clienteField.setBounds(413, 350, 57, 33);
		panelVeiculo.add(clienteField);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCliente.setBounds(413, 317, 211, 22);
		panelVeiculo.add(lblCliente);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(650, 87, 15, 480);
		getContentPane().add(separator);
		
		buscarField = new FieldRedondo();
		buscarField.setColumns(10);
		buscarField.setBounds(663, 133, 41, 33);
		getContentPane().add(buscarField);
		
		JLabel lblBusca = new JLabel("Busca/Remove");
		lblBusca.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblBusca.setBounds(653, 79, 156, 55);
		getContentPane().add(lblBusca);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(675, 117, 29, 14);
		getContentPane().add(lblId);
		
		irButton = new RoundButton("Salvar");
		irButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		irButton.setForeground(Color.WHITE);
		irButton.setBackground(Color.BLUE);
		irButton.setText("Ir");
		irButton.setBounds(713, 128, 43, 42);
		getContentPane().add(irButton);
		
		allButton = new RoundButton("Salvar");
		allButton.setForeground(Color.WHITE);
		allButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		allButton.setText("All");
		allButton.setBackground(Color.BLACK);
		allButton.setBounds(756, 128, 43, 42);
		getContentPane().add(allButton);
		
		removerButton = new RoundedCornerButton("Salvar");
		removerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		removerButton.setText("Remover");
		removerButton.setForeground(Color.WHITE);
		removerButton.setBackground(Color.RED);
		removerButton.setBounds(690, 177, 92, 42);
		getContentPane().add(removerButton);
		
		esquerdaButton = new RoundButton("Salvar");
		esquerdaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		esquerdaButton.setText("<");
		esquerdaButton.setForeground(Color.WHITE);
		esquerdaButton.setBackground(new Color(0, 128, 128));
		esquerdaButton.setBounds(690, 324, 43, 42);
		getContentPane().add(esquerdaButton);
		
		direitaButton = new RoundButton("Salvar");
		direitaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		direitaButton.setText(">");
		direitaButton.setForeground(Color.WHITE);
		direitaButton.setBackground(new Color(0, 128, 128));
		direitaButton.setBounds(756, 324, 43, 42);
		getContentPane().add(direitaButton);
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(679, 272, 130, 55);
		getContentPane().add(lblNavegao);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 76, 809, 10);
		getContentPane().add(panel_1);
		setVisible(false);
		
		limparButton = new RoundedCornerButton("Salvar");
		limparButton.setText("Limpar");
		limparButton.setForeground(Color.WHITE);
		limparButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		limparButton.setBackground(new Color(60,179,113));
		limparButton.setBounds(675, 222, 124, 42);
		getContentPane().add(limparButton);
		operacaoLabel = new JLabel("Modo Inser\u00E7\u00E3o");
		operacaoLabel.setForeground(new Color(255, 0, 0));
		operacaoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		operacaoLabel.setBounds(221, -1, 203, 22);
		panelVeiculo.add(operacaoLabel);
		ButtonGroup b = new ButtonGroup();
		b.add(kmLivreRadio);
		b.add(rdbtnKmControle);
	}
	class CellRendererToolTip extends DefaultTableCellRenderer {  
	    // Mantém todos os tooltips com suas linhas.   
	    private Map<Long, String> tooltip = new HashMap<Long, String>();  
	      
	    // Mantém a linha atual que este objeto está renderizando.   
	    private int row;  
	      
	    // Busca qual é a linha atual.   
	    public Component getTableCellRendererComponent(JTable table, Object value,  
	            boolean isSelected, boolean hasFocus, int row, int column) {  
	        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
	          
	        this.row = row;  
	          
	        return c;   
	    }  
	      
	    // Retorna o tooltip baseado no map.   
	    public String getToolTipText() {  
	        return tooltip.get(new Long(row));  
	    }  
	      
	    // Adiciona um tooltip pela linha.   
	    public void addToolTip(int row, String text) {  
	        tooltip.put(new Long(row), text);  
	    }  
	}  
	class RendererOfTable extends DefaultTableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column )
        {
        	 JTextField tf;
        	if(value!=null) {
            tf = new JTextField( value.toString() );
            tf.setOpaque( true );
            tf.setForeground( Color.BLACK );
            tf.setToolTipText( value.toString() );
            tf.setBorder(null);
            System.out.println("Enn");
        	}else
        	    tf = new JTextField();
        	
            return tf;
        }
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
	public JTextField getFilialEntregaIdField() {
		return filialEntregaIdField;
	}
	public JTextField getVeiculoIdField() {
		return veiculoIdField;
	}
	public FieldFormattedRedondo getHoraRealizacaoField() {
		return horaRealizacaoField;
	}
	public JDateChooser getEntregaDateChooser() {
		return entregaDateChooser;
	}
	public FieldFormattedRedondo getHoraEntregaField() {
		return horaEntregaField;
	}
	public JRadioButton getKmLivreRadio() {
		return kmLivreRadio;
	}
	public JTextField getIdFilialLocatariaField() {
		return idFilialLocatariaField;
	}
	public JComboBox getFilialLocatariaCombo() {
		return filialLocatariaCombo;
	}
	public JComboBox getFilialEntregaCombo() {
		return filialEntregaCombo;
	}
	public JTextArea getFilialEntregaTextArea() {
		return filialEntregaTextArea;
	}
	public JTextArea getFilialLocatariaTextArea() {
		return filialLocatariaTextArea;
	}
	public JComboBox getVeiculoCombo() {
		return veiculoCombo;
	}
	public JTextArea getVeiculoTextArea() {
		return veiculoTextArea;
	}
	public RoundedCornerButton getLimparButton() {
		return limparButton;
	}
	public JDateChooser getRealizacaoDataChooser() {
		return RealizacaoDataChooser;
	}
	public JRadioButton getRdbtnKmControle() {
		return rdbtnKmControle;
	}
	public JTextField getIdMotoristaField() {
		return idMotoristaField;
	}
	public JTextField getClienteField() {
		return clienteField;
	}
	public JComboBox getClienteCombo() {
		return clienteCombo;
	}
	public JLabel getOperacaoLabel() {
		return operacaoLabel;
	}
	public JComboBox getMotoristaCombo() {
		return motoristaCombo;
	}
	
}
