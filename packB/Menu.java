package packB;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Painel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagens\\icon.png"));
		
		setResizable(false);
		setTitle("Menu principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 418);
		Painel = new JPanel();
		Painel.setBackground(Color.WHITE);
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Painel);
		Painel.setLayout(null);
		
		JPanel Centro = new JPanel();
		Centro.setBounds(10, 33, 476, 334);
		Painel.add(Centro);
		Centro.setLayout(null);
		
		Border borda = BorderFactory.createLineBorder(Color.black);
		
		JLabel lblImagemMedicos = new JLabel("");

		lblImagemMedicos.setIcon(new ImageIcon("imagens/dentista.png"));
		lblImagemMedicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagemMedicos.setBounds(25, 23, 189, 136);
		//lblImagemMedicos.setBorder(borda);
		Centro.add(lblImagemMedicos);
		
		// Botão Medico
		
		JButton btnMedico = new JButton("M\u00E9dicos");
		btnMedico.setBackground(Color.WHITE);
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Principal_Médico().setVisible(true);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnMedico.setForeground(Color.BLACK);
		btnMedico.setBounds(25, 129, 190, 30);
		Centro.add(btnMedico);
		
		
		// Botão Pacientes
		
		JButton btnPacientes = new JButton("Pacientes");
		btnPacientes.setBackground(Color.WHITE);
		btnPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Principal_Paciente().setVisible(true);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnPacientes.setForeground(Color.BLACK);
		btnPacientes.setBounds(265, 129, 189, 30);
		Centro.add(btnPacientes);
		
		// Botão Consultas
		
		JButton btnConsultas = new JButton("Marca\u00E7\u00E3o de Consultas");
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new Principal_Consulta().setVisible(true);
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}

			}
		});
		

		
		btnConsultas.setBackground(Color.WHITE);
		btnConsultas.setForeground(Color.BLACK);
		btnConsultas.setBounds(25, 285, 190, 30);
		Centro.add(btnConsultas);
		
		JLabel lblImagemPacientes = new JLabel("");
		lblImagemPacientes.setIcon(new ImageIcon("imagens/paciente.png"));
		lblImagemPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagemPacientes.setBounds(265, 23, 189, 136);
		Centro.add(lblImagemPacientes);
		
		JLabel lblImagemConsultas = new JLabel("");
		lblImagemConsultas.setIcon(new ImageIcon("imagens/consultas.png"));
		lblImagemConsultas.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagemConsultas.setBounds(25, 179, 189, 136);
		Centro.add(lblImagemConsultas);
		
		JButton btnSair = new JButton("Terminar Sess\u00E3o");
		btnSair.setForeground(Color.BLACK);
		btnSair.setBackground(Color.WHITE);
		btnSair.setBounds(264, 285, 190, 30);
		Centro.add(btnSair);
		
		JLabel lbloutralabel = new JLabel("");
		lbloutralabel.setIcon(new ImageIcon("imagens/sair.png"));
		lbloutralabel.setHorizontalAlignment(SwingConstants.CENTER);
		lbloutralabel.setBounds(265, 179, 189, 136);
		Centro.add(lbloutralabel);
		
		JMenuBar BarraMenu = new JMenuBar();
		BarraMenu.setBounds(0, 0, 617, 22);
		Painel.add(BarraMenu);
		
		JMenu Opcoes = new JMenu("Op\u00E7\u00F5es");
		BarraMenu.add(Opcoes);
		
		// Opções do Menu
		
		JMenuItem opcaoAjuda = new JMenuItem("Ajuda");
		opcaoAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ajuda().setVisible(true);
			}
		});
		Opcoes.add(opcaoAjuda);
		
		JMenuItem opcaoSair = new JMenuItem("Sair");
		opcaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		Opcoes.add(opcaoSair);
		
		// Fechar
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				new Login().setVisible(true);
				dispose();

			}
		});
	}
}
