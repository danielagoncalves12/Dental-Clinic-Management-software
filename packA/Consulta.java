package packA;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Consulta {
	
	private M�dico m�dico;
	private Calendar data;
	private String especialidade;
	private Paciente paciente;
	private Orcamento orcamento;
	public int numConsulta;
	
	// Dados Base de dados
	private static String dbName     = "jdbc:mysql://localhost:3307/clinica_dentaria_48579";
	private static String driver     = "com.mysql.cj.jdbc.Driver";
	private static String userName   = "root";
	private static String dbpassword = "";
	
	public static ArrayList<Consulta> totalConsultas = new ArrayList<Consulta>();

	/**
	 * Construtor
	*/
	public Consulta (Paciente paciente, M�dico m�dico, Calendar data, String especialidade) throws ClassNotFoundException, SQLException {

		// Verifica se o m�dico tem a especialidade pedida
		if (!(verificarEspecialidade(m�dico, especialidade)))
			throw new IllegalArgumentException("M�dico n�o tem a especialidade escolhida.");
		
		// Verifica se o paciente tem disponibilidade na data pedida
		if (!(paciente.verificarDisponibilidade(data)))
			throw new IllegalArgumentException("Paciente j� tem consulta marcada nesta data e hora escolhida.");
		
		// Verifica se o medico tem disponibilidade na data pedida
		if (!(m�dico.verificarDisponibilidade(data)))
			throw new IllegalArgumentException("M�dico n�o tem disponibilidade na data e hora escolhida.");	
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		boolean repetida = Paciente.verificarConsultaRepetida(paciente.getNome(), m�dico.getNome(), formatter.format(data.getTime()));
		
		// Se for marcada uma consulta igual
		if (repetida)
			throw new IllegalArgumentException("Consulta igual j� est� marcada.");	
		
		// Data atual
		Calendar dataHoje = Calendar.getInstance();

		
		this.numConsulta   = numeroConsulta();
		this.paciente      = paciente;
		this.m�dico        = m�dico;
		this.data          = data;
		this.especialidade = especialidade;

		// Se nao for repetida gera um novo orcamento
		if (!repetida)
			this.orcamento = criarOrcamento();
		
		// Guardar consulta na Base de dados
		// Se nao existe, guarda a nova consulta
		if (!verificarSeExiste()) {		
			guardarDados();
			totalConsultas.add(this);
		}	
		// Atualizar o software de acordo 
		// com as altera��es na base de dados
		Dados.receberConsultasBD();
	}	
	
	/**
	 * Retorna o numero da proxima consulta a ser marcada
	 * (Encontra o numero da ultima consulta
	 * marcada guardada na base de dados.)
	 */
	private int numeroConsulta() {
		
		int num = 0;
		
		try {
			Class.forName(driver);
			Connection c = DriverManager.getConnection(dbName, userName, dbpassword);
			Statement st = c.createStatement();

			ResultSet query = st.executeQuery("SELECT id FROM consultas ORDER BY id DESC LIMIT 1");	
			
			// Obter o ultimo id criado
			if (query.next()) 
				num = query.getInt("id");
			
			st.close();
			c.close();
			}
		catch (Exception e) {
			System.out.println(e);	
		}
		return num+1;
	}
	
	/**
	 * Guarda os dados da consulta nas respetivas
	 * colunas da tabela 'consultas' da base de dados.
	 */
	private void guardarDados() {
		
		try {
			Class.forName(driver);
			Connection c = DriverManager.getConnection(dbName, userName, dbpassword);
			Statement st = c.createStatement();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String query = "INSERT INTO consultas (nome_paciente, nome_medico, horas, especialidade, path_orcamento)"
					+ " VALUES('" + this.paciente.getNome() + "','" + this.m�dico.getNome() + "','" 
					+ formatter.format(this.data.getTime()) + "','" + this.especialidade + "','" + this.orcamento + "')";		
			
			// Executa a query INSERT INTO
			st.executeUpdate(query);
			st.close();
			c.close();
			}
		catch (Exception e) {
			System.out.println(e);	
		}
	}
	
	/**
	 * Verifica se a consulta escolhida ja existe
	 * na tabela 'consultas' da base de dados.
	 */
	private boolean verificarSeExiste(){
		
		try {
			Class.forName(driver);
			Connection c = DriverManager.getConnection(dbName, userName, dbpassword);
			Statement st = c.createStatement();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			ResultSet query = st.executeQuery("SELECT * FROM consultas WHERE nome_paciente = '" 
							+ this.paciente.getNome() + "'AND nome_medico ='" 
					        + this.m�dico.getNome() + "'AND horas = '" 
							+ formatter.format(this.data.getTime()) + "'");		
			
			if (query.next()) 
				return true;
			
			st.close();
			c.close();
			}
		catch (Exception e) {
			System.out.println(e);	
		}
		//System.out.println("nao existe");
		return false;
	}
	
	/**
	 * Elimina a consulta pedida
	 * (removendo da base de dados)
	*/
	public static void eliminarConsulta(String id) {
	
		try {
			Class.forName(driver);
			Connection c = DriverManager.getConnection(dbName, userName, dbpassword);
			Statement st = c.createStatement();

			String query = "DELETE FROM consultas WHERE id = '" + id + "'";

			// Executa a query DELETE
			st.executeUpdate(query);
			
			// Elimina o antigo or�amento
			File file = new File("src\\tps\\tp4\\packA\\orcamentos\\orcamentos_clientes\\orcamento_num_" + id + ".docx");
			file.delete();
			
			st.close();
			c.close();
			}
		catch (Exception e) {
			System.out.println(e);	
		}
	}
	
	/**
	 * Verifica se o m�dico tem 
	 * a especialidade pedida
	*/
	private boolean verificarEspecialidade (M�dico m�dico, String especialidade) {
		
		if (m�dico.temEspecialidade(especialidade))
			return true;
		return false;
	}
	
	/**
	 * Tenta criar um or�amento para a consulta
	*/
	private Orcamento criarOrcamento() {
		
		try {
			Orcamento orc = new Orcamento(this);
			return orc;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Devolve o paciente da consulta
	*/
	public Paciente getPaciente() {
		
		return paciente;	
	}
	
	/**
	 * Devolve a data
	*/
	public Calendar getData() {
		
		return data;
	}

	/**
	 * Devolve o Medico da consulta
	*/
	public M�dico getMedico() {
		
		return m�dico;
	}

	/**
	 * Devolve a especialidade
	*/
	public String getEspecialidade() {

		return especialidade;
	}
	
	/**
	 * Representacao em String
	*/
	public String toString() {
		
		Calendar cal = getData();
		String data = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + 
		cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
		
		return "M�dico: " + m�dico.getNome() + ", Data: " + data + ", Especialidade: " + especialidade + ", " + orcamento;
	}


}
