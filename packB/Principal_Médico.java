package packB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import packA.Dados;
import packA.Médico;
import packA.Paciente;

import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Principal_Médico extends JFrame {

	private JPanel Painel;
	private JTable table;
	private JTextField caixaPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal_Médico frame = new Principal_Médico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public Principal_Médico() throws ClassNotFoundException, SQLException, IOException {
		
		// Recebe os dados vindos dos ficheiros	
		Dados.lerFicheiroParaObjeto();
		
		
		// Arraylist com os médicos
		ArrayList<Médico> lista = Médico.totalMedicos;

		
		// Painel Principal	
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagens\\icon.png"));
		setTitle("Menu - M\u00E9dicos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 429);
		Painel = new JPanel();
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Painel);
		Painel.setLayout(null);
		
		// Menu (Barra)
		
		JMenuBar menuBarra = new JMenuBar();
		menuBarra.setBounds(0, 0, 1039, 22);
		Painel.add(menuBarra);
		
		
		// Botão Adicionar Paciente		
		
		JButton btnAdicionarMedico = new JButton("Adicionar M\u00E9dico");
		btnAdicionarMedico.setForeground(Color.BLACK);
		btnAdicionarMedico.setBackground(Color.WHITE);
		btnAdicionarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Adicionar_Médico().setVisible(true);
			}
		});
		
		// Botão Terminar (Fechar Janela)
		
		JButton btnTerminar = new JButton("Terminar");
		btnTerminar.setForeground(Color.BLACK);
		btnTerminar.setBackground(Color.WHITE);
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
				
		
		// Adicionar os Botões ao Menu
		
		menuBarra.add(btnAdicionarMedico);
		menuBarra.add(btnTerminar);
		
		
		// Painel com Scroll
		
		JLabel lblNewLabel = new JLabel("                                                                                                                                              ");
		menuBarra.add(lblNewLabel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 44, 650, 326);
		Painel.add(scrollPane);
		
		
		// Criação da Tabela para receber os Medicos
		
		table = new JTable();
		scrollPane.setViewportView(table);


		// Label's
		
		JPanel PainelPerfil = new JPanel();
		PainelPerfil.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PainelPerfil.setToolTipText("Perfil do Médico");
		PainelPerfil.setBackground(UIManager.getColor("Button.highlight"));
		PainelPerfil.setBounds(700, 44, 317, 326);
		Painel.add(PainelPerfil);
		PainelPerfil.setLayout(null);
		
		JLabel Perfil = new JLabel("Perfil do M\u00E9dico:");
		Perfil.setFont(new Font("Arial", Font.BOLD, 12));
		Perfil.setBounds(10, 11, 167, 26);
		PainelPerfil.add(Perfil);
		
		JLabel escreverNome = new JLabel("");
		escreverNome.setForeground(SystemColor.controlDkShadow);
		escreverNome.setBounds(54, 48, 242, 14);
		PainelPerfil.add(escreverNome);
		
		JLabel Nome = new JLabel("Nome: ");
		Nome.setBounds(10, 48, 46, 14);
		PainelPerfil.add(Nome);
		
		JLabel Localidade = new JLabel("Localidade:");
		Localidade.setBounds(10, 73, 71, 14);
		PainelPerfil.add(Localidade);
		
		JLabel Especialidades = new JLabel("Especialidades:");
		Especialidades.setBounds(10, 98, 109, 14);
		PainelPerfil.add(Especialidades);
		
		JLabel Telefone = new JLabel("Telefone: ");
		Telefone.setBounds(10, 123, 56, 14);
		PainelPerfil.add(Telefone);
		
		JLabel NIF = new JLabel("NIF: ");
		NIF.setBounds(10, 148, 46, 14);
		PainelPerfil.add(NIF);
		
		JLabel Idade = new JLabel("Idade: ");
		Idade.setBounds(10, 173, 46, 14);
		PainelPerfil.add(Idade);
		
		JLabel Fotografia = new JLabel("Sem Fotografia");
		Fotografia.setToolTipText("Fotografia");
		Fotografia.setHorizontalAlignment(SwingConstants.CENTER);
		Fotografia.setBounds(208, 199, 99, 116);
		PainelPerfil.add(Fotografia);
		
		JLabel escreverLocalidade = new JLabel("");
		escreverLocalidade.setForeground(SystemColor.controlDkShadow);
		escreverLocalidade.setBounds(80, 73, 227, 14);
		PainelPerfil.add(escreverLocalidade);
		
		JLabel escreverEspecialidades = new JLabel("");
		escreverEspecialidades.setForeground(SystemColor.controlDkShadow);
		escreverEspecialidades.setBounds(105, 98, 202, 14);
		PainelPerfil.add(escreverEspecialidades);
		
		JLabel escreverTelefone = new JLabel("");
		escreverTelefone.setForeground(SystemColor.controlDkShadow);
		escreverTelefone.setBounds(65, 123, 242, 14);
		PainelPerfil.add(escreverTelefone);
		
		JLabel escreverNIF = new JLabel("");
		escreverNIF.setForeground(SystemColor.controlDkShadow);
		escreverNIF.setBounds(35, 148, 272, 14);
		PainelPerfil.add(escreverNIF);
		
		JLabel escreverIdade = new JLabel("");
		escreverIdade.setForeground(SystemColor.controlDkShadow);
		escreverIdade.setBounds(48, 173, 46, 14);
		PainelPerfil.add(escreverIdade);
		
		
		// Modelo para a Tabela com celulas não editaveis
		
		class ModeloNaoEditavel extends DefaultTableModel {

			ModeloNaoEditavel(Object[][] data, String[] columnNames) { super(data, columnNames); }

		    @Override
		    public boolean isCellEditable(int row, int column) { return false; }
		}
		
		table.setModel(new ModeloNaoEditavel(

			new Object[][] { },
			new String[] { "Nome", "Localidade", "Especialidades", "Telefone", "NIF", "Idade", "Anos de Experiência" }));
		
		
		DefaultTableModel tabela = (DefaultTableModel)table.getModel();	
		
		
		Border borda = BorderFactory.createLineBorder(Color.black);
		Fotografia.setBorder(borda);
		
		JLabel lblAnosDeExperincia = new JLabel("Anos de Experi\u00EAncia: ");
		lblAnosDeExperincia.setBounds(10, 198, 144, 14);
		PainelPerfil.add(lblAnosDeExperincia);
		
		JLabel escreverAnosExperiencia = new JLabel("");
		escreverAnosExperiencia.setForeground(SystemColor.controlDkShadow);
		escreverAnosExperiencia.setBounds(133, 198, 58, 14);
		PainelPerfil.add(escreverAnosExperiencia);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(84);
		table.getColumnModel().getColumn(4).setPreferredWidth(99);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);	
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
						
						
						// Botão Atualizar lista (após inserir um médico novo)
						
						JButton btnAtualizar = new JButton("Atualizar Lista");
						menuBarra.add(btnAtualizar);
						btnAtualizar.setForeground(Color.BLACK);
						btnAtualizar.setBackground(Color.WHITE);
						btnAtualizar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
														
								try {
									tabela.setRowCount(0);
									tabela.fireTableDataChanged();
									Dados.lerFicheiroParaObjeto();
									
									StringBuilder especialidades = new StringBuilder();
									
									for (int i = 0; i < lista.size(); i++) {
											
										for (int j = 0; j < lista.get(i).especialidades.length; j++) {
											
											especialidades.append(lista.get(i).especialidades[j]);
											if(j != lista.get(i).especialidades.length-1)
												especialidades.append(", "); 
											}
											
										tabela.addRow(new Object [] {
																					
											lista.get(i).getNome(), 
											lista.get(i).getLocalidade(), 
											especialidades.toString(),
											lista.get(i).getTelefone(), 
											lista.get(i).getNif(),
											lista.get(i).getIdade(),
											lista.get(i).getAnosExperiencia()});
										
											especialidades.setLength(0);
									
									}						
								} catch (IOException e) {
									
									e.printStackTrace();
								}
							}		
						});
		
				
				// Caixa de texto de introdução do NIF para a realização de uma pesquisa
				
						caixaPesquisa = new JTextField();
						caixaPesquisa.setHorizontalAlignment(SwingConstants.CENTER);
						menuBarra.add(caixaPesquisa);
						caixaPesquisa.setColumns(2);
				
				
				// Botão Pesquisar pelo NIF
				
				JButton btnPesquisar = new JButton("Pesquisar NIF");
				btnPesquisar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
									
						String pesquisaNIF = caixaPesquisa.getText().toString();
						boolean encontrou = false;
						
						for (int i = 0; i < lista.size(); i++)
							if (Integer.toString(lista.get(i).getNif()).equals(pesquisaNIF)) 
							{			
								table.setRowSelectionInterval(i, i);
								perfilMedico(lista, table, escreverNome, escreverLocalidade, escreverEspecialidades, escreverTelefone, escreverNIF, escreverIdade, Fotografia, escreverAnosExperiencia);
								encontrou = true;
							}				
						if (encontrou == false) {
							table.clearSelection();
							JOptionPane.showMessageDialog(Painel, "O paciente com o NIF " + pesquisaNIF + " não foi encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);		
						}
					}
				});
				btnPesquisar.setForeground(Color.BLACK);
				btnPesquisar.setBackground(Color.WHITE);
				menuBarra.add(btnPesquisar);
		
		// Preencher a tabela com os médicos
		
		StringBuilder especialidades = new StringBuilder();
		for (int i = 0; i < lista.size(); i++) {
				
			for (int j = 0; j < lista.get(i).especialidades.length; j++) {
				
				especialidades.append(lista.get(i).especialidades[j]);
				if(j != lista.get(i).especialidades.length-1)
					especialidades.append(", "); 
				}
				
			tabela.addRow(new Object [] {
														
				lista.get(i).getNome(), 
				lista.get(i).getLocalidade(), 
				especialidades.toString(),
				lista.get(i).getTelefone(), 
				lista.get(i).getNif(),
				lista.get(i).getIdade(),
				lista.get(i).getAnosExperiencia()});
			
				especialidades.setLength(0);		
		}			
		
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    					
		    	perfilMedico(lista, table, escreverNome, escreverLocalidade, escreverEspecialidades, escreverTelefone, escreverNIF, escreverIdade, Fotografia, escreverAnosExperiencia);
		    }
		});

	}
	
	/**  Método para preencher o Perfil do Médico no painel do lado direito
	 *   Caso não haja fotografia (visto que é optional) é apresentado
	 *   "Sem Fotografia" nas labels.
	 * **/
	
	public void perfilMedico(ArrayList<Médico> lista, JTable tabela, JLabel nome, JLabel localidade, JLabel sistemaSaude, JLabel telefone, JLabel nif, JLabel idade, JLabel Fotografia, JLabel anos) {
		
		String dataNome, dataLocalidade, dataEspecialidades, dataTelefone, dataNif, dataIdade, dataFotografia = "", dataAnos;
		
		// table.getValueAt( row index, column index)
		
		dataNome = (String) tabela.getValueAt(tabela.getSelectedRow(), 0);
		nome.setText(dataNome);
		
		dataLocalidade = (String) tabela.getValueAt(tabela.getSelectedRow(), 1);
		localidade.setText(dataLocalidade);
		
		dataEspecialidades = (String) tabela.getValueAt(tabela.getSelectedRow(), 2);
		sistemaSaude.setText(dataEspecialidades);
		
		dataTelefone = (String) tabela.getValueAt(tabela.getSelectedRow(), 3).toString();
		telefone.setText(dataTelefone);
		
		dataNif = (String) tabela.getValueAt(tabela.getSelectedRow(), 4).toString();
		nif.setText(dataNif);
		
		dataIdade = (String) tabela.getValueAt(tabela.getSelectedRow(),  5).toString();
		idade.setText(dataIdade);
		
		dataAnos = (String) tabela.getValueAt(tabela.getSelectedRow(),  6).toString();
		anos.setText(dataAnos);
		
		// Fotografia
		
		for (int i = 0; i < lista.size(); i++)
			if (Integer.toString(lista.get(i).getNif()).equals(dataNif)) 
				dataFotografia = lista.get(i).getFotografia(); 

		if (!(dataFotografia.equals(""))) {
			
			ImageIcon iconFoto  = new ImageIcon(dataFotografia);
			Image imgFoto       = iconFoto.getImage().getScaledInstance(Fotografia.getWidth(), Fotografia.getHeight(), iconFoto.getImage().SCALE_SMOOTH);

			Fotografia.setText("");
			Fotografia.setIcon(new ImageIcon(imgFoto));
			Fotografia.setHorizontalTextPosition(JLabel.CENTER);			
		}
		else
		{
			Fotografia.setText("Sem Fotografia");
			Fotografia.setIcon(null);	
		}
	}
}
