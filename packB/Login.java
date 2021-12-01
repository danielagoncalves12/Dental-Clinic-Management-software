package packB;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeutilizador;
	private JPasswordField palavrapasse;
	
	private String dbName     = "clinica_dentaria_48579";
	private String driver     = "com.mysql.cj.jdbc.Driver";
	private String userName   = "root";
	private String dbpassword = "";
	private String url		  = "jdbc:mysql://localhost:3307/";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagens\\icon.png"));
		
		setTitle("Cl\u00EDnica Dent\u00E1ria");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel painel = new JPanel();
		painel.setBackground(UIManager.getColor("FormattedTextField.background"));
		contentPane.add(painel, BorderLayout.CENTER);
		
		
		// Criação do JButton do Login
		JButton botao_login = new JButton("Log in");
		botao_login.setBackground(new Color(131,111,255));
		botao_login.setFont(new Font("Tahoma", Font.BOLD, 12));
		botao_login.setForeground(Color.WHITE);
		
		// Quando o JButton for clicado
		botao_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nomeUtilizador, palavraPasse;
				
				nomeUtilizador = nomeutilizador.getText();
				palavraPasse = String.valueOf(palavrapasse.getPassword());
				
				String pass = "";
				String nomeCompleto = "";
				
				if (!palavraPasse.equals("") && !nomeUtilizador.equals(""))				
					try {
						
						// Conexão à base de Dados com a query
						Class.forName(driver);
						Connection c = DriverManager.getConnection(url+dbName, userName, dbpassword);
						Statement st = c.createStatement();
	
						ResultSet query = st.executeQuery("SELECT * from registos WHERE nome_utilizador = '" + nomeUtilizador + "'");	
	
						// Obter os dados 
						if (query.next()) {
							pass = query.getString("password");
							nomeCompleto = query.getString("nome_completo");
						}
						
						// Caso nao encontre uma password significa que nao foi encontrado o utilizador
						if (pass.equals(""))
							JOptionPane.showMessageDialog(painel, "Nome de utilizador introduzido não existe.", "Erro", JOptionPane.ERROR_MESSAGE);		
						
						// Caso a password esteja incorreta
						if (!(pass.equals(palavraPasse)) && !pass.equals(""))
							JOptionPane.showMessageDialog(painel, "Palavra-passe incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);			
	
						// Caso a password esteja correta
						if (pass.equals(palavraPasse)) {
							JOptionPane.showMessageDialog(painel, "Bem vindo(a) " + nomeCompleto + "!", "Sucesso", JOptionPane.PLAIN_MESSAGE);	
							setVisible(false); // Ocultar o JFrame
							dispose(); // Destruir
							new Menu().setVisible(true); // Novo ecra
						}
						// Fechar a conexão
						st.close();
						c.close();
						}
					catch (Exception e) {
						System.out.println(e);
					}
				else
					JOptionPane.showMessageDialog(painel, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);	
				
			}
		});
		
		// Label Login
		
		JLabel label_login = new JLabel("Login");
		label_login.setForeground(UIManager.getColor("FormattedTextField.inactiveForeground"));
		label_login.setHorizontalAlignment(SwingConstants.CENTER);
		label_login.setFont(new Font("Arial", Font.BOLD, 32));
		
		// Imagem background na esquerda
		
		JLabel Imagem = new JLabel("");
		Imagem.setHorizontalAlignment(SwingConstants.CENTER);
		Imagem.setIcon(new ImageIcon("imagens/background.jpg"));
		
		nomeutilizador = new JTextField();
		
		palavrapasse = new JPasswordField();
		
		JLabel label_nomeutilizador = new JLabel("Nome de utilizador:");
		label_nomeutilizador.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel label_palavrapasse = new JLabel("Palavra-passe:");
		label_palavrapasse.setFont(new Font("Arial", Font.PLAIN, 14));

		
		
		GroupLayout gl_painel = new GroupLayout(painel);
		gl_painel.setHorizontalGroup(
			gl_painel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painel.createSequentialGroup()
					.addComponent(Imagem, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(gl_painel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 11, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_painel.createParallelGroup(Alignment.TRAILING)
								.addComponent(palavrapasse, Alignment.LEADING, 258, 258, 258)
								.addGroup(gl_painel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(nomeutilizador, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
									.addComponent(label_nomeutilizador, Alignment.LEADING))
								.addComponent(label_palavrapasse, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(botao_login, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_painel.createSequentialGroup()
							.addComponent(label_login, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 170, GroupLayout.PREFERRED_SIZE)))
					.addGap(23))
		);
		gl_painel.setVerticalGroup(
			gl_painel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painel.createSequentialGroup()
					.addGap(51)
					.addComponent(label_login)
					.addGap(50)
					.addComponent(label_nomeutilizador)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nomeutilizador, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(label_palavrapasse, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(palavrapasse, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
					.addComponent(botao_login)
					.addGap(70))
				.addComponent(Imagem, GroupLayout.PREFERRED_SIZE, 441, Short.MAX_VALUE)
		);
		painel.setLayout(gl_painel);
	}
}
