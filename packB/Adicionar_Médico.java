package packB;

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import packA.Médico;
import packA.Paciente;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Adicionar_Médico extends JFrame {
	
	
	private final String PATHFOTOGRAFIA = "imagens/fotografias/";
	
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textNIF;
	private JTextField textLocalidade;

	String pathFotografia = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adicionar_Médico frame = new Adicionar_Médico();
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
	public Adicionar_Médico() {
		setResizable(false);

		setIconImage(Toolkit.getDefaultToolkit().getImage("imagens\\icon.png"));
		setTitle("Adicionar Médico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 338);
		JPanel Painel = new JPanel();
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Painel);
		Painel.setLayout(null);
		
		JPanel Borda = new JPanel();
		Borda.setBounds(10, 11, 609, 279);
		Painel.add(Borda);
		
		TitledBorder borda = BorderFactory.createTitledBorder("  Adicionar novo médico  ");
		borda.setTitleColor(Color.black);
		Borda.setBorder(borda);
		Borda.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome do médico: ");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(22, 34, 134, 14);
		Borda.add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone: ");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefone.setBounds(22, 59, 104, 14);
		Borda.add(lblTelefone);
		
		JLabel lblNIF = new JLabel("NIF: ");
		lblNIF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNIF.setBounds(22, 84, 46, 14);
		Borda.add(lblNIF);
		
		JLabel lblLocalidade = new JLabel("Localidade: ");
		lblLocalidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLocalidade.setBounds(22, 109, 104, 14);
		Borda.add(lblLocalidade);
		
		JLabel lblIdade = new JLabel("Idade: ");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdade.setBounds(22, 134, 82, 14);
		Borda.add(lblIdade);
		
		JLabel lblSistemaSaude = new JLabel("Especialidades: ");
		lblSistemaSaude.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSistemaSaude.setBounds(22, 159, 118, 14);
		Borda.add(lblSistemaSaude);
		
		textNome = new JTextField();
		textNome.setBounds(146, 32, 252, 20);
		Borda.add(textNome);
		textNome.setColumns(10);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(146, 57, 86, 20);
		Borda.add(textTelefone);
		textTelefone.setColumns(10);
		
		textNIF = new JTextField();
		textNIF.setBounds(146, 82, 86, 20);
		Borda.add(textNIF);
		textNIF.setColumns(10);
		
		textLocalidade = new JTextField();
		textLocalidade.setBounds(146, 107, 252, 20);
		Borda.add(textLocalidade);
		textLocalidade.setColumns(10);
		
		JSpinner spinnerIdade = new JSpinner();
		spinnerIdade.setModel(new SpinnerNumberModel(0, 0, 120, 1));
		spinnerIdade.setBounds(146, 132, 47, 20);
		Borda.add(spinnerIdade);
		
		JLabel lblAnosDeExperincia = new JLabel("Anos de experi\u00EAncia: ");
		lblAnosDeExperincia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnosDeExperincia.setBounds(22, 184, 134, 14);
		Borda.add(lblAnosDeExperincia);
		
		JSpinner spinnerAnos = new JSpinner();
		spinnerAnos.setModel(new SpinnerNumberModel(0, 0, 60, 1));
		spinnerAnos.setBounds(147, 182, 47, 20);
		Borda.add(spinnerAnos);
		
		
		
		
		List<JCheckBox> buttons = new ArrayList<>();

		JCheckBox checkBox1 = new JCheckBox("Periodontologia");
		checkBox1.setBounds(146, 156, 125, 23);
		buttons.add(checkBox1);
		Borda.add(checkBox1);
		
		JCheckBox checkBox2 = new JCheckBox("Ortodontia");
		checkBox2.setBounds(273, 156, 104, 23);
		buttons.add(checkBox2);
		Borda.add(checkBox2);
		
		JCheckBox checkBox3 = new JCheckBox("Odontopediatria");
		checkBox3.setBounds(378, 156, 134, 23);
		buttons.add(checkBox3);
		Borda.add(checkBox3);
		
		JCheckBox checkBox4 = new JCheckBox("Outro");
		checkBox4.setBounds(508, 156, 75, 23);
		buttons.add(checkBox4);
		Borda.add(checkBox4);
		
		
		
		// 
		
		JLabel Fotografia = new JLabel("Sem fotografia");
		Fotografia.setHorizontalAlignment(SwingConstants.CENTER);
		Fotografia.setBounds(465, 18, 118, 99);
		Borda.add(Fotografia);
		
		JButton btnUploadImagem = new JButton("Upload Fotografia");
		btnUploadImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				JFileChooser file = new JFileChooser(PATHFOTOGRAFIA);				
				int result = file.showOpenDialog(Painel);
			        
				if (result == JFileChooser.APPROVE_OPTION) {
						
					File imagem = file.getSelectedFile();
					String path = imagem.getAbsolutePath();
					pathFotografia = path;
						
					// Ajustar o tamanho
					ImageIcon icon = new ImageIcon(path);
					Image img = icon.getImage().getScaledInstance(Fotografia.getWidth(), Fotografia.getHeight(), icon.getImage().SCALE_SMOOTH);
						
					Fotografia.setText("");
					Fotografia.setIcon(new ImageIcon(img));
					Fotografia.setHorizontalTextPosition(JLabel.CENTER);				
					
				}
			}
		});
		
		
		btnUploadImagem.setFont(new Font("Arial", Font.PLAIN, 9));
		btnUploadImagem.setBounds(465, 125, 118, 23);
		Borda.add(btnUploadImagem);
	
		
		Border bordaImagem = BorderFactory.createLineBorder(Color.black);
		Fotografia.setBorder(bordaImagem);

		
		// // // // // //
		
		JButton adicionarConsulta = new JButton("Adicionar Médico");
		adicionarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<String> esp = new ArrayList<String>();
				
				for (int i = 0; i < buttons.size(); i++) 
				    if (buttons.get(i).isSelected())
				        esp.add(buttons.get(i).getText());
				

				if (!(textNome.getText().isEmpty() || textTelefone.getText().isEmpty() || 
						textNIF.getText().isEmpty() || textLocalidade.getText().isEmpty())) {	
					
					if (verNumero(textTelefone.getText()) && verNumero(textNIF.getText())) {
						
						if (textTelefone.getText().length() == 9 && textNIF.getText().length() == 9) {
							
							if (!(esp.size() == 0)) { 
							
								int telefone = Integer.parseInt(textTelefone.getText());
								int nif      = Integer.parseInt(textNIF.getText());
								String nome  = textNome.getText();
								String local = textLocalidade.getText();
								int idade    = (Integer)spinnerIdade.getValue();
								String[] especialidades = esp.toArray(new String[0]);
								
								int anosExperiencia = (Integer)spinnerAnos.getValue();
				
								// Imagens
								String fotografia = pathFotografia;
								
								int resposta = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer adicionar o Médico " + nome + "?", "Aviso", JOptionPane.YES_NO_OPTION);
								
								if (resposta == JOptionPane.YES_OPTION)
								{
									Médico med = new Médico(nome, nif, idade, local, telefone, fotografia, especialidades, anosExperiencia);
									dispose();
								}
							} 
							else {
								JOptionPane.showMessageDialog(Painel, "Deve ser adicionada pelo menos uma especialidade.","Erro", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(Painel, "Os campos Telefone e NIF devem conter 9 números.","Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(Painel, "Os campos Telefone e NIF devem conter valores numéricos.","Erro", JOptionPane.ERROR_MESSAGE);
					}
				}		
				else 
				{
					JOptionPane.showMessageDialog(Painel, "Todos os campos devem ser preenchidos.","Erro", JOptionPane.ERROR_MESSAGE);		
				}
			}
		});

		adicionarConsulta.setBounds(22, 233, 155, 23);
		Borda.add(adicionarConsulta);		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(197, 233, 92, 23);
		Borda.add(btnCancelar);
		
	}
	
	public static boolean verNumero(String nome) {
	    boolean ret = true;
	    try {
	        Integer.parseInt(nome);

	    }catch (NumberFormatException e) {
	        ret = false;
	    }
	    return ret;
	}
}
