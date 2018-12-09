package br.com.palves.pbd.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import br.com.palves.pbd.app.App;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
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
	private JTable reservaTable;
	private JTextField filialEntregaIdField;
	private JTextField veiculoIdField;
	private JTextField motoristaIdField;
	private JTextField motoristaCombo;
	private FieldRedondo horaRealizacaoField;
	private JDateChooser entregaDateChooser;
	private FieldRedondo horaEntregaField ;
	private JRadioButton kmLivreRadio;
	private JTextField idFilialLocatariaField;
	private JComboBox filialLocatariaCombo;
	private JComboBox filialEntregaCombo;
	private JTextArea filialEntregaTextArea;
	private JTextArea filialLocatariaTextArea;
	private JComboBox veiculoCombo;
	private JTextArea veiculoTextArea;
	private RoundButton buscarMotoristaButton;
	private RoundButton outroVeiculoButton;
	private RoundedCornerButton limparButton;
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
		
		JLabel reservaLabel = new JLabel("Reservas");
		reservaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		reservaLabel.setBounds(21, 21, 177, 22);
		panelVeiculo.add(reservaLabel);
		JDateChooser calendar  = new JDateChooser();
		calendar.setBounds(10, 45, 177, 33);
		panelVeiculo.add(calendar);
		
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
		salvarButton.setBounds(10, 394, 74, 75);
		panelVeiculo.add(salvarButton);
	
		JScrollPane scrollPaneReserva = new JScrollPane();
		scrollPaneReserva.setBounds(10, 79, 629, 106);
		panelVeiculo.add(scrollPaneReserva);
		scrollPaneReserva.setBorder(null);
		reservaTable = new JTable()
		{
	        public Component prepareRenderer(TableCellRenderer renderer,
	                                         int rowIndex, int vColIndex) 
	        {
	            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
	            if (c instanceof JComponent && c!=null) 
	            {
	                // Minha adaptação, pq quando um objeto da tabela eh do tipo Integer, por exemplo, não se converte para 
	                //String com um simples cast, como citado no fonte desse exemplo.
	                Object o = getValueAt(rowIndex, vColIndex); 
	               //Fim adaptacao.
	                JComponent jc = (JComponent)c;
	                jc.setToolTipText(o.toString());
	            }
	            return c;
	        }
	 };
		//table.getColumnModel().getColumn(3).setCellRenderer( new RendererOfTable() );
		reservaTable.setModel(new DefaultTableModel(
				new Object[][] {
					{"123","j", "E", "João pereira da Silva","1234567890111","E"}
				},
				new String[] {
					"<html><table><tr><td height=50><b>ID</b></td></tr></table></html>", "Data Hora", "Cod Cliente","Cliente","CPF","Situacao"
				}
			));
		reservaTable.getTableHeader().setBorder(new LineBorder(Color.BLACK,1,true));
		reservaTable.getTableHeader().setBackground(Color.BLACK);
		reservaTable.getTableHeader().setForeground(Color.WHITE);
		reservaTable.setRowHeight(40);
		reservaTable.getColumnModel().getColumn(0).setWidth(20);
		reservaTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPaneReserva.setViewportView(reservaTable);
		
		JLabel lblDatahoraRealizao = new JLabel("Data/Hora Realiza\u00E7\u00E3o");
		lblDatahoraRealizao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDatahoraRealizao.setBounds(10, 185, 177, 22);
		panelVeiculo.add(lblDatahoraRealizao);
		
		JDateChooser RealizacaoDataChooser = new JDateChooser();
		RealizacaoDataChooser.setBounds(10, 207, 89, 33);
		panelVeiculo.add(RealizacaoDataChooser);
		
		horaRealizacaoField = new FieldRedondo();
		horaRealizacaoField.setColumns(10);
		horaRealizacaoField.setBounds(103, 207, 105, 33);
		panelVeiculo.add(horaRealizacaoField);
		horaRealizacaoField.setToolTipText("Preenchido quando Conclui a locação");
		
		JLabel lblDatahoraPrevistaPara = new JLabel("Data/Hora Prevista Para entrega");
		lblDatahoraPrevistaPara.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDatahoraPrevistaPara.setBounds(10, 265, 211, 22);
		panelVeiculo.add(lblDatahoraPrevistaPara);
		
		entregaDateChooser = new JDateChooser();
		entregaDateChooser.setBounds(10, 288, 89, 33);
		panelVeiculo.add(entregaDateChooser);
		
		horaEntregaField = new FieldRedondo();
		horaEntregaField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		horaEntregaField.setColumns(10);
		horaEntregaField.setBounds(103, 288, 105, 33);
		panelVeiculo.add(horaEntregaField);
		
		JLabel lblModalidadeDeLocao = new JLabel("Modalidade de Loca\u00E7\u00E3o");
		lblModalidadeDeLocao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModalidadeDeLocao.setBounds(10, 332, 211, 22);
		panelVeiculo.add(lblModalidadeDeLocao);
		
		kmLivreRadio = new JRadioButton("Km Livre");
		kmLivreRadio.setSelected(true);
		kmLivreRadio.setFont(new Font("Tahoma", Font.BOLD, 11));
		kmLivreRadio.setForeground(Color.BLACK);
		kmLivreRadio.setBounds(10, 352, 95, 23);
		panelVeiculo.add(kmLivreRadio);
		kmLivreRadio.setOpaque(false);
		
		JRadioButton rdbtnKmControle = new JRadioButton("Km Controle");
		rdbtnKmControle.setOpaque(false);
		rdbtnKmControle.setForeground(Color.BLACK);
		rdbtnKmControle.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnKmControle.setBounds(90, 352, 109, 23);
		panelVeiculo.add(rdbtnKmControle);
		
		idFilialLocatariaField = new JTextField();
		idFilialLocatariaField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		idFilialLocatariaField.setColumns(10);
		idFilialLocatariaField.setBounds(215, 207, 48, 33);
		panelVeiculo.add(idFilialLocatariaField);
		
		JLabel lblFilialLocataria = new JLabel("Filial Locataria");
		lblFilialLocataria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFilialLocataria.setBounds(215, 185, 211, 22);
		panelVeiculo.add(lblFilialLocataria);
		
		filialLocatariaCombo = new JComboBox();
		filialLocatariaCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		filialLocatariaCombo.setBackground(Color.WHITE);
		filialLocatariaCombo.setBounds(260, 207, 150, 33);
		panelVeiculo.add(filialLocatariaCombo);
		
		JLabel lblFilialEntrega = new JLabel("Filial Entrega");
		lblFilialEntrega.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFilialEntrega.setBounds(215, 332, 211, 22);
		panelVeiculo.add(lblFilialEntrega);
		
		filialEntregaIdField = new JTextField();
		filialEntregaIdField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		filialEntregaIdField.setColumns(10);
		filialEntregaIdField.setBounds(215, 358, 48, 33);
		panelVeiculo.add(filialEntregaIdField);
		
		filialEntregaCombo = new JComboBox();
		filialEntregaCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		filialEntregaCombo.setBackground(Color.WHITE);
		filialEntregaCombo.setBounds(260, 358, 150, 33);
		panelVeiculo.add(filialEntregaCombo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(215, 240, 195, 81);
		panelVeiculo.add(scrollPane_1);
		//scrollPane_1.setBorder(null);
		scrollPane_1.setOpaque(false);
		
		filialLocatariaTextArea = new JTextArea();
		filialLocatariaTextArea.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(filialLocatariaTextArea);
		filialLocatariaTextArea.setLineWrap(true);
		filialLocatariaTextArea.setFont(new Font("Monospaced", Font.BOLD, 12));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(215, 394, 195, 81);
		panelVeiculo.add(scrollPane_2);
		
		filialEntregaTextArea = new JTextArea();
		filialEntregaTextArea.setLineWrap(true);
		filialEntregaTextArea.setFont(new Font("Monospaced", Font.BOLD, 12));
		scrollPane_2.setViewportView(filialEntregaTextArea);
		
		veiculoCombo = new JComboBox();
		veiculoCombo.setFont(new Font("Tahoma", Font.BOLD, 12));
		veiculoCombo.setBackground(Color.WHITE);
		veiculoCombo.setBounds(449, 207, 190, 33);
		panelVeiculo.add(veiculoCombo);
		
		veiculoIdField = new JTextField();
		veiculoIdField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		veiculoIdField.setColumns(10);
		veiculoIdField.setBounds(413, 207, 48, 33);
		panelVeiculo.add(veiculoIdField);
		
		JLabel lblVeiculos = new JLabel("Veiculo");
		lblVeiculos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVeiculos.setBounds(413, 185, 211, 22);
		panelVeiculo.add(lblVeiculos);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(413, 240, 226, 81);
		panelVeiculo.add(scrollPane_3);
		
		veiculoTextArea = new JTextArea();
		veiculoTextArea.setLineWrap(true);
		veiculoTextArea.setFont(new Font("Monospaced", Font.BOLD, 12));
		veiculoTextArea.setBackground(Color.WHITE);
		scrollPane_3.setViewportView(veiculoTextArea);
		
		JLabel lblMotorista = new JLabel("Motorista");
		lblMotorista.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMotorista.setBounds(413, 362, 211, 22);
		panelVeiculo.add(lblMotorista);
		
		motoristaIdField = new JTextField();
		motoristaIdField.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		motoristaIdField.setColumns(10);
		motoristaIdField.setBounds(413, 383, 48, 33);
		panelVeiculo.add(motoristaIdField);
		
		motoristaCombo = new JTextField();
		motoristaCombo.setToolTipText("Preenchido quando Conclui a loca\u00E7\u00E3o");
		motoristaCombo.setColumns(10);
		motoristaCombo.setBounds(464, 383, 175, 33);
		panelVeiculo.add(motoristaCombo);
		
		buscarMotoristaButton = new RoundButton("Salvar");
		buscarMotoristaButton.setText("Buscar");
		buscarMotoristaButton.setBounds(464, 427, 89, 42);
		panelVeiculo.add(buscarMotoristaButton);
		
		outroVeiculoButton = new RoundButton("Salvar");
		outroVeiculoButton.setText("Outro");
		outroVeiculoButton.setBounds(464, 328, 89, 42);
		panelVeiculo.add(outroVeiculoButton);
		ButtonGroup b = new ButtonGroup();
		
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
		removerButton.setBounds(681, 177, 92, 42);
		getContentPane().add(removerButton);
		
		esquerdaButton = new RoundButton("Salvar");
		esquerdaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		esquerdaButton.setText("<");
		esquerdaButton.setForeground(Color.WHITE);
		esquerdaButton.setBackground(new Color(0, 128, 128));
		esquerdaButton.setBounds(675, 324, 43, 42);
		getContentPane().add(esquerdaButton);
		
		direitaButton = new RoundButton("Salvar");
		direitaButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		direitaButton.setText(">");
		direitaButton.setForeground(Color.WHITE);
		direitaButton.setBackground(new Color(0, 128, 128));
		direitaButton.setBounds(730, 324, 43, 42);
		getContentPane().add(direitaButton);
		
		JLabel lblNavegao = new JLabel("Navega\u00E7\u00E3o");
		lblNavegao.setFont(new Font("Humanst521 BT", Font.BOLD, 20));
		lblNavegao.setBounds(675, 275, 130, 55);
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
	public static void main(String[] args) {
		//App.lookWindows();
		new FormularioCrudLocacao();
	}
}
