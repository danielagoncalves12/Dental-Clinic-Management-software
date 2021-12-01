package packB;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packA.Consulta;
import packA.Dados;
import packA.Paciente;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Consultas_Marcadas extends JFrame {

	private JPanel Painel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consultas_Marcadas frame = new Consultas_Marcadas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Consultas_Marcadas() {
		
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daniela\\Desktop\\LEIM\\2Semestre\\MOP\\Java\\MoP\\imagens\\icon.png"));
		setTitle("Consultas marcadas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 290);
		Painel = new JPanel();
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Painel);
		Painel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 39, 583, 196);
		Painel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num. Consulta", "Paciente", "M\u00E9dico", "Data/horas", "Especialidade"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(122);
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 625, 22);
		Painel.add(menuBar);
		
		JButton btnEliminarConsulta = new JButton("Apagar Consulta");
		btnEliminarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (table.getSelectedRow() != -1) {
				
					int resposta = JOptionPane.showConfirmDialog(null, "Tem a certeza que pertende eliminar a consulta selecionada?", "Aviso", JOptionPane.YES_NO_OPTION);	
					if (resposta == JOptionPane.YES_OPTION)
					{
						String id = (String) table.getValueAt(table.getSelectedRow(), 0);
						Consulta.eliminarConsulta(id);
						
						try {
							Dados.receberConsultasBD();
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
						
						ArrayList<ArrayList<String>> consultas = Paciente.consultasExistentes;
						DefaultTableModel tabela = (DefaultTableModel)table.getModel();	
						tabela.setRowCount(0);
						
						// Atualizar
						for (int i = 0; i < consultas.size(); i++){
							tabela.addRow(new Object [] {
									
								consultas.get(i).get(0), 
								consultas.get(i).get(1), 
								consultas.get(i).get(2),
								consultas.get(i).get(3),
								consultas.get(i).get(4)});
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(Painel, "Nenhuma linha selecionada.","Erro", JOptionPane.ERROR_MESSAGE);	
				}	
			}
		});
		menuBar.add(btnEliminarConsulta);
		
		JButton btnTerminar = new JButton("Terminar");
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		menuBar.add(btnTerminar);
		
		try {
			Dados.receberConsultasBD();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<ArrayList<String>> consultas = Paciente.consultasExistentes;
		DefaultTableModel tabela = (DefaultTableModel)table.getModel();	
		
		for (int i = 0; i < consultas.size(); i++){
			tabela.addRow(new Object [] {
					
				consultas.get(i).get(0), 
				consultas.get(i).get(1), 
				consultas.get(i).get(2),
				consultas.get(i).get(3),
				consultas.get(i).get(4)});
		}
		
	}
}
