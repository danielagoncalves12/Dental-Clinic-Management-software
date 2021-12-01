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

public class Principal_Paciente extends JFrame {

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
					Principal_Paciente frame = new Principal_Paciente();
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
	public Principal_Paciente() throws ClassNotFoundException, SQLException, IOException {
		
		// Recebe os dados vindos dos ficheiros	
		Dados.lerFicheiroParaObjeto();
		
		
		// Arraylist com os pacientes
		ArrayList<Paciente> lista = Paciente.totalPacientes;

		
		// Painel Principal	
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagens\\icon.png"));
		setTitle("Menu - Pacientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 429);
		Painel = new JPanel();
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Painel);
		Painel.setLayout(null);
		
		// Menu (Barra)
		
		JMenuBar menuBarra = new JMenuBar();
		menuBarra.setBounds(0, 0, 1029, 22);
		Painel.add(menuBarra);
		
		
		// Botão Adicionar Paciente		
		
		JButton btnAdicionarPaciente = new JButton("Adicionar Paciente");
		btnAdicionarPaciente.setForeground(Color.BLACK);
		btnAdicionarPaciente.setBackground(Color.WHITE);
		btnAdicionarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Adicionar_Paciente().setVisible(true);
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
		
		menuBarra.add(btnAdicionarPaciente);
		menuBarra.add(btnTerminar);
		
		
		// Painel com Scroll
		
		JLabel lblNewLabel = new JLabel("                                                                                                                                        ");
		menuBarra.add(lblNewLabel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 44, 650, 326);
		Painel.add(scrollPane);
		
		
		// Criação da Tabela para receber os Pacientes
		
		table = new JTable();
		scrollPane.setViewportView(table);


		// Label's
		
		JPanel PainelPerfil = new JPanel();
		PainelPerfil.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PainelPerfil.setToolTipText("Perfil do Paciente");
		PainelPerfil.setBackground(UIManager.getColor("Button.highlight"));
		PainelPerfil.setBounds(700, 44, 317, 326);
		Painel.add(PainelPerfil);
		PainelPerfil.setLayout(null);
		
		JLabel Perfil = new JLabel("Perfil do Paciente:");
		Perfil.setFont(new Font("Arial", Font.BOLD, 12));
		Perfil.setBounds(10, 11, 167, 26);
		PainelPerfil.add(Perfil);
		
		JLabel Nome = new JLabel("Nome: ");
		Nome.setBounds(10, 48, 46, 14);
		PainelPerfil.add(Nome);
		
		JLabel Localidade = new JLabel("Localidade:");
		Localidade.setBounds(10, 73, 71, 14);
		PainelPerfil.add(Localidade);
		
		JLabel SistemaSaude = new JLabel("Sistema de Sa\u00FAde: ");
		SistemaSaude.setBounds(10, 98, 109, 14);
		PainelPerfil.add(SistemaSaude);
		
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
		
		JLabel RaioX = new JLabel("Sem Raio-X");
		RaioX.setToolTipText("Raio-X");
		RaioX.setHorizontalAlignment(SwingConstants.CENTER);
		RaioX.setBounds(10, 198, 188, 117);
		PainelPerfil.add(RaioX);
		
		JLabel escreverNome = new JLabel("");
		escreverNome.setForeground(SystemColor.controlDkShadow);
		escreverNome.setBounds(50, 48, 257, 14);
		PainelPerfil.add(escreverNome);
		
		JLabel escreverLocalidade = new JLabel("");
		escreverLocalidade.setForeground(SystemColor.controlDkShadow);
		escreverLocalidade.setBounds(80, 73, 227, 14);
		PainelPerfil.add(escreverLocalidade);
		
		JLabel escreverSistemaSaude = new JLabel("");
		escreverSistemaSaude.setForeground(SystemColor.controlDkShadow);
		escreverSistemaSaude.setBounds(118, 98, 189, 14);
		PainelPerfil.add(escreverSistemaSaude);
		
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
			new String[] { "Nome", "Localidade", "Sistema Sa\u00FAde", "Telefone", "NIF", "Idade" }));
		
		
		DefaultTableModel tabela = (DefaultTableModel)table.getModel();	
		
		
		Border borda = BorderFactory.createLineBorder(Color.black);
		Fotografia.setBorder(borda);
		RaioX.setBorder(borda);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(101);
		table.getColumnModel().getColumn(1).setPreferredWidth(211);
		table.getColumnModel().getColumn(2).setPreferredWidth(97);
		table.getColumnModel().getColumn(3).setPreferredWidth(84);
		table.getColumnModel().getColumn(4).setPreferredWidth(99);
		table.getColumnModel().getColumn(5).setPreferredWidth(54);	
		
		
		// Botão Atualizar lista (após inserir um paciente novo)
		
		JButton btnAtualizar = new JButton("Atualizar Lista");
		btnAtualizar.setForeground(Color.BLACK);
		btnAtualizar.setBackground(Color.WHITE);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
							
				
				// Atualiza o ArrayList com as informacoes dos ficheiros
				try {
					tabela.setRowCount(0);
					tabela.fireTableDataChanged();
					
					Dados.lerFicheiroParaObjeto();

					for (int i = 0; i < lista.size(); i++){
						tabela.addRow(new Object [] {
																
							lista.get(i).getNome(), 
							lista.get(i).getLocalidade(), 
							lista.get(i).getSistemaSaude(),
							lista.get(i).getTelefone(), 
							lista.get(i).getNif(),
							lista.get(i).getIdade()});
					}					
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
		});
		menuBarra.add(btnAtualizar);
				
		
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
						perfilPaciente(lista, table, escreverNome, escreverLocalidade, escreverSistemaSaude, escreverTelefone, escreverNIF, escreverIdade, Fotografia, RaioX);
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

		
		// Preencher a tabela com os pacientes
		
		for (int i = 0; i < lista.size(); i++){
			tabela.addRow(new Object [] {
					
					lista.get(i).getNome(), 
					lista.get(i).getLocalidade(), 
					lista.get(i).getSistemaSaude(),
					lista.get(i).getTelefone(), 
					lista.get(i).getNif(),
					lista.get(i).getIdade()});
		}
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    					
				perfilPaciente(lista, table, escreverNome, escreverLocalidade, escreverSistemaSaude, escreverTelefone, escreverNIF, escreverIdade, Fotografia, RaioX);
		    }
		});

	}
	
	/**  Método para preencher o Perfil do Paciente no painel do lado direito
	 *   Caso não haja fotografia/raioX (visto que é optional) é apresentado
	 *   "Sem Fotografia" e "Sem Raio-X" nas labels.
	 * **/
	
	public void perfilPaciente(ArrayList<Paciente> lista, JTable tabela, JLabel nome, JLabel localidade, JLabel sistemaSaude, JLabel telefone, JLabel nif, JLabel idade, JLabel Fotografia, JLabel RaioX) {
		
		String dataNome, dataLocalidade, dataSistemaSaude, dataTelefone, dataNif, dataIdade, dataFotografia = "", dataRaioX = "";
		
		// table.getValueAt( row index, column index)
		
		dataNome = (String) tabela.getValueAt(tabela.getSelectedRow(), 0);
		nome.setText(dataNome);
		
		dataLocalidade = (String) tabela.getValueAt(tabela.getSelectedRow(), 1);
		localidade.setText(dataLocalidade);
		
		dataSistemaSaude = (String) tabela.getValueAt(tabela.getSelectedRow(), 2);
		sistemaSaude.setText(dataSistemaSaude);
		
		dataTelefone = (String) tabela.getValueAt(tabela.getSelectedRow(), 3).toString();
		telefone.setText(dataTelefone);
		
		dataNif = (String) tabela.getValueAt(tabela.getSelectedRow(), 4).toString();
		nif.setText(dataNif);
		
		dataIdade = (String) tabela.getValueAt(tabela.getSelectedRow(),  5).toString();
		idade.setText(dataIdade);
		
		// Fotografia e Raio-X
		
		for (int i = 0; i < lista.size(); i++)
			if (Integer.toString(lista.get(i).getNif()).equals(dataNif)) 
			{	
				dataFotografia = lista.get(i).getFotografia(); 
				dataRaioX      = lista.get(i).getRaioX();
			}
		
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
		
		
		if (!(dataRaioX.equals("")))
		{
			ImageIcon iconRaioX = new ImageIcon(dataRaioX);
			Image imgRaio       = iconRaioX.getImage().getScaledInstance(RaioX.getWidth(), RaioX.getHeight(), iconRaioX.getImage().SCALE_SMOOTH);
		
			RaioX.setText("");
			RaioX.setIcon(new ImageIcon(imgRaio));
			RaioX.setHorizontalTextPosition(JLabel.CENTER);		
		}	
		else 
		{
			RaioX.setText("Sem Raio-X");
			RaioX.setIcon(null);
		}
	}
}
