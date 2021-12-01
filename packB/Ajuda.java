package packB;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packA.Especialidades;

import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextPane;

public class Ajuda extends JFrame {

	private JPanel Painel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajuda frame = new Ajuda();
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
	public Ajuda() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Daniela\\Desktop\\LEIM\\2Semestre\\MOP\\Java\\MoP\\imagens\\icon.png"));
		setTitle("Menu - Ajuda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 360);
		Painel = new JPanel();
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Painel);
		Painel.setLayout(null);
		
		JLabel Ajuda = new JLabel("Ajuda");
		Ajuda.setHorizontalAlignment(SwingConstants.CENTER);
		Ajuda.setVerticalAlignment(SwingConstants.TOP);
		Ajuda.setBounds(10, 11, 291, 14);
		Painel.add(Ajuda);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(10, 36, 291, 234);
		Painel.add(panel);
		panel.setLayout(null);
		
		JLabel lblPeri = new JLabel("Periodontologia:");
		lblPeri.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPeri.setBounds(10, 11, 107, 14);
		panel.add(lblPeri);
		
		JLabel lblOrto = new JLabel("Ortodontia:");
		lblOrto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrto.setBounds(10, 71, 107, 14);
		panel.add(lblOrto);
		
		JLabel lblOdonto = new JLabel("Odontopediatria:");
		lblOdonto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOdonto.setBounds(10, 140, 121, 19);
		panel.add(lblOdonto);
		
		JTextPane expPeri = new JTextPane();
		expPeri.setEditable(false);
		expPeri.setText(Especialidades.explicacao(Especialidades.PERIODONTOLOGIA));
		expPeri.setBounds(10, 26, 271, 34);
		panel.add(expPeri);
		
		JTextPane expPeri_1 = new JTextPane();
		expPeri_1.setText(Especialidades.explicacao(Especialidades.ORTODONTIA));
		expPeri_1.setEditable(false);
		expPeri_1.setBounds(10, 86, 271, 55);
		panel.add(expPeri_1);
		
		JTextPane expPeri_2 = new JTextPane();
		expPeri_2.setText(Especialidades.explicacao(Especialidades.ODONTOPEDIATRIA));
		expPeri_2.setEditable(false);
		expPeri_2.setBounds(10, 156, 271, 34);
		panel.add(expPeri_2);
		
		JLabel lblOutro = new JLabel("Outro:");
		lblOutro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOutro.setBounds(10, 189, 121, 19);
		panel.add(lblOutro);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(Especialidades.explicacao(Especialidades.OUTRO));
		textPane.setBounds(10, 203, 252, 31);
		panel.add(textPane);
		
		JLabel lblModelaoEProgramao = new JLabel("Modela\u00E7\u00E3o e Programa\u00E7\u00E3o 2020/2021");
		lblModelaoEProgramao.setBounds(10, 295, 182, 25);
		Painel.add(lblModelaoEProgramao);
		lblModelaoEProgramao.setForeground(SystemColor.desktop);
		lblModelaoEProgramao.setFont(new Font("Arial", Font.PLAIN, 11));
		
		JLabel lblNome = new JLabel("Daniela Gon\u00E7alves A48579");
		lblNome.setBounds(10, 283, 131, 13);
		Painel.add(lblNome);
		lblNome.setForeground(SystemColor.desktop);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 11));
	}
}
