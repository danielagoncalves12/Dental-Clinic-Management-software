package packB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import packA.Consulta;
import packA.Dados;
import packA.Médico;
import packA.Paciente;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.Toolkit;

public class Principal_Consulta extends JFrame {

	private JPanel Painel;
	private JTable table;
	private JTextField escreverPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal_Consulta frame = new Principal_Consulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public Principal_Consulta() throws IOException, ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daniela\\Desktop\\LEIM\\2Semestre\\MOP\\Java\\MoP\\imagens\\icon.png"));
		
		// Recebe os dados vindos dos ficheiros	
		Dados.lerFicheiroParaObjeto();
			
		// Arraylist com os pacientes
		ArrayList<Paciente> listaPacientes = Paciente.totalPacientes;
		ArrayList<Médico> listaMedicos = Médico.totalMedicos; 

		
		setTitle("Menu - Marca\u00E7\u00E3o de Consultas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 340);
		Painel = new JPanel();
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Painel);
		Painel.setLayout(null);
		
		
		JPanel Borda = new JPanel();
		Borda.setBounds(415, 11, 377, 279);
		Painel.add(Borda);
		
		TitledBorder borda = BorderFactory.createTitledBorder("  Marcação da Consulta  ");
		borda.setTitleColor(Color.black);
		Borda.setBorder(borda);
		Borda.setLayout(null);
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setBounds(21, 33, 64, 14);
		Borda.add(lblPaciente);
		
		escreverPaciente = new JTextField();
		escreverPaciente.setEditable(false);
		escreverPaciente.setBounds(81, 30, 268, 20);
		Borda.add(escreverPaciente);
		escreverPaciente.setColumns(10);
		
		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(21, 68, 94, 14);
		Borda.add(lblEspecialidade);
		
		JComboBox cboxEspecialidade = new JComboBox();
		cboxEspecialidade.setModel(new DefaultComboBoxModel(new String[] {"Periodontologia", "Ortodontia", "Odontopediatria", "Outro"}));
		cboxEspecialidade.setBounds(111, 64, 238, 22);
		Borda.add(cboxEspecialidade);
		
		
		JLabel lblMedico = new JLabel("M\u00E9dico:");
		lblMedico.setBounds(21, 106, 76, 14);
		Borda.add(lblMedico);
		
		JLabel lblEscolherData = new JLabel("Escolher data:");
		lblEscolherData.setBounds(21, 141, 115, 14);
		Borda.add(lblEscolherData);



		
		JLabel lblNewLabel = new JLabel("Escolha o paciente:");
		lblNewLabel.setBounds(10, 24, 151, 14);
		Painel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 395, 241);
		Painel.add(scrollPane);
		
		class ModeloNaoEditavel extends DefaultTableModel {

			ModeloNaoEditavel(Object[][] data, String[] columnNames) { super(data, columnNames); }

		    @Override
		    public boolean isCellEditable(int row, int column) { return false; }
		}
		
		table = new JTable();		
		table.setModel(new ModeloNaoEditavel(

				new Object[][] { },
				new String[] { "Nome", "NIF" }));

		
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		scrollPane.setViewportView(table);
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		

		for (int i = 0; i < listaPacientes.size(); i++){
			modelo.addRow(new Object [] {
					
			listaPacientes.get(i).getNome(), 
			listaPacientes.get(i).getNif()});
		}
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    		
		    	String nome = (String) modelo.getValueAt(table.getSelectedRow(), 0);
		    	escreverPaciente.setText(nome);
		    }
		});
	    
	    
	    
	    JComboBox<Médico> cboxMedico = new JComboBox();
	    cboxMedico.setBounds(111, 102, 238, 22);
	    Borda.add(cboxMedico);
	    preencherMedicos(listaMedicos, cboxEspecialidade, cboxMedico);
	    cboxMedico.setSelectedIndex(0);
	    
	    
		
		cboxEspecialidade.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				
		    	preencherMedicos(listaMedicos, cboxEspecialidade, cboxMedico);
		    }
		});
	

		Properties p = new Properties();
		p.put("text.today", "Hoje");
		p.put("text.month", "Mês");
		p.put("text.year", "Ano");

		JDatePickerImpl datePicker;

	    UtilDateModel modeloCal = new UtilDateModel();
	    JDatePanelImpl datePanel = new JDatePanelImpl(modeloCal, p);
	    datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
	    SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
	    springLayout.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 10, SpringLayout.WEST, datePicker);
	    datePicker.setBounds(100,137,249,30);
	    
	    modeloCal.setSelected(true);
	    Borda.add(datePicker);

	    
	    JComboBox cboxHoras = new JComboBox();
	    cboxHoras.setSelectedIndex(-1);
	    cboxHoras.setBounds(111, 178, 238, 22);
	    Borda.add(cboxHoras);
	   
	    String[] listaCcbox = new String[] {"08h-09h", "09h-10h", "10h-11h", "11h-12h", "12h-13h", "13h-14h", 
	    									"14h-15h", "15h-16h", "16h-17h", "17h-18h", "18h-19h", "19h-20h"};

	    Hashtable<String, Integer> dic = new Hashtable<String, Integer>();
	    dic.put(listaCcbox[0], 8);
	    dic.put(listaCcbox[1], 9);
	    dic.put(listaCcbox[2], 10);
	    dic.put(listaCcbox[3], 11);
	    dic.put(listaCcbox[4], 12);
	    dic.put(listaCcbox[5], 13);
	    dic.put(listaCcbox[6], 14);
	    dic.put(listaCcbox[7], 15);
	    dic.put(listaCcbox[8], 16);
	    dic.put(listaCcbox[9], 17);
	    dic.put(listaCcbox[10], 18);
	    dic.put(listaCcbox[11], 19);
	    
	    
	    for (int i = 0; i < listaCcbox.length; i++) {
	    	
	    	int hora = dic.get(listaCcbox[i]);    	
	    	
	    	Calendar calNovo = Calendar.getInstance();
	    	
	    	calNovo.setTime(modeloCal.getValue());
	    	calNovo.set(Calendar.HOUR_OF_DAY, hora);
	    	calNovo.set(Calendar.MINUTE, 0);
	    	calNovo.set(Calendar.SECOND, 0);    	

	    	Médico med = (Médico) cboxMedico.getSelectedItem();
	    	
	    	if (med.verificarDisponibilidade(calNovo))
	    		cboxHoras.addItem(listaCcbox[i]);	    	
	    }
	    
	    
	    Calendar cal = Calendar.getInstance();
	    cboxHoras.setSelectedIndex(0);
	    
	    
    	
    	
	    datePicker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {         	
        	        	
            	cboxHoras.removeAllItems();
            	for (int i = 0; i < listaCcbox.length; i++) {
        	    	
        	    	int hora = dic.get(listaCcbox[i]);    	
        	    	
        	    	Calendar calNovo = Calendar.getInstance();
        	    	
        	    	calNovo.setTime(modeloCal.getValue());
        	    	calNovo.set(Calendar.HOUR_OF_DAY, hora);
        	    	calNovo.set(Calendar.MINUTE, 0);
        	    	calNovo.set(Calendar.SECOND, 0);    	
 	
        	    	Médico med = (Médico) cboxMedico.getSelectedItem();
        	    		
        	    	if (med.verificarDisponibilidade(calNovo))
        	    		cboxHoras.addItem(listaCcbox[i]);	
        	    }
            	
            	cal.setTime(modeloCal.getValue());
            }
	    });
		    
	    cboxHoras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  

            }
	    });
	    
	    JButton btnAdicionarConsulta = new JButton("Adicionar Consulta");
	    btnAdicionarConsulta.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		
	    		if (cboxMedico.getItemCount() != 0) {
	    			
	    			if (!(escreverPaciente.getText().isEmpty())) {

		               	Date data = modeloCal.getValue();    
		             	
		               	cal.setTime(data);
		            	cal.set(Calendar.HOUR_OF_DAY, dic.get(cboxHoras.getSelectedItem().toString()));
		            	cal.set(Calendar.MINUTE, 0);
		            	cal.set(Calendar.SECOND, 0);
	
		            	
	            		String nome = (String)table.getValueAt(table.getSelectedRow(), 0);
		            	int nif = (int)table.getValueAt(table.getSelectedRow(), 1);
		            	
		            	Paciente pac = Paciente.encontrarPaciente(nome, nif);
		            	
	    				if (pac.verificarDisponibilidade(cal)) {
			            		        		
				    		Médico med = (Médico) cboxMedico.getSelectedItem();
				    		
				    		if (med.verificarDisponibilidade(cal)) {
				    		
					    		String esp = cboxEspecialidade.getSelectedItem().toString();
					    		
					    		int resposta = JOptionPane.showConfirmDialog(null, "Tem a certeza que pertence marcar a consulta?", "Aviso", JOptionPane.YES_NO_OPTION);
								if (resposta == JOptionPane.YES_OPTION)
								{  		
						    		try {
										Consulta consulta = new Consulta(pac, med, cal, esp);
										JOptionPane.showMessageDialog(Painel, "Consulta marcada com sucesso.","Marcação de Consultas", JOptionPane.INFORMATION_MESSAGE);
									} catch (ClassNotFoundException | SQLException e) {
										e.printStackTrace();
									}
								}
				    		}
				    		else {
				    			JOptionPane.showMessageDialog(Painel, "Médico não tem disponibilidade na data escolhida.","Erro", JOptionPane.ERROR_MESSAGE);
				    		}
	    				}
	    				else {
	    					JOptionPane.showMessageDialog(Painel, "Paciente já tem consulta na data marcada.","Erro", JOptionPane.ERROR_MESSAGE);
	    				}
			    		
	    			} else {
	    				JOptionPane.showMessageDialog(Painel, "Deve selecionar um paciente.","Erro", JOptionPane.ERROR_MESSAGE);	
	    			}
	    		}
	    		else {
	    			JOptionPane.showMessageDialog(Painel, "Nenhum médico disponivel.","Erro", JOptionPane.ERROR_MESSAGE);	
	    		}
	    	}
	    });
	    btnAdicionarConsulta.setBounds(26, 232, 153, 23);
	    Borda.add(btnAdicionarConsulta);
	    
	    JLabel lblNewLabel_1 = new JLabel("Escolher hora:");
	    lblNewLabel_1.setBounds(21, 182, 94, 14);
	    Borda.add(lblNewLabel_1);
	    
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		dispose();
	    	}
	    });
	    btnCancelar.setBounds(213, 232, 115, 23);
	    Borda.add(btnCancelar);
	    
	    JButton btnConsultasMarcadas = new JButton("Consultas marcadas");
	    btnConsultasMarcadas.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		new Consultas_Marcadas().setVisible(true);
	    	}
	    });
	    btnConsultasMarcadas.setBounds(232, 20, 173, 23);
	    Painel.add(btnConsultasMarcadas);
    
	}

	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }
	        return "";
	    }
	}
	
	
	public void preencherMedicos(ArrayList<Médico> listaMedicos, JComboBox cboxEspecialidade, JComboBox cboxMedico) {
		
    	ArrayList<Médico> medicosDisponiveis = new ArrayList<Médico>();
 
    	cboxMedico.removeAllItems();
		for(int indice = 0; indice < listaMedicos.size(); indice++) 
			if (listaMedicos.get(indice).temEspecialidade(cboxEspecialidade.getSelectedItem().toString()))
				medicosDisponiveis.add(listaMedicos.get(indice));
		
		for (Médico med : medicosDisponiveis)
			   cboxMedico.addItem(med); 
		        
		
	}
}
