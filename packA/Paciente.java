package packA;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class Paciente extends Pessoa implements Serializable {

	private static final long serialVersionUID = -3894898175845661724L;
	private String sistemaSaude;
	private String raioX;
	
	// Consultas de um certo paciente
	public ArrayList<Consulta> consultas = new ArrayList<Consulta>();
	
	// Total Pacientes existentes
	public static ArrayList<Paciente> totalPacientes = new ArrayList<Paciente>();
	
	// Consultas guardadas em base de dados (recebe os dados vindo da base de dados)
	public static ArrayList<ArrayList<String>> consultasExistentes = new ArrayList<ArrayList<String>>();
	
	/**
	 * Constructor
	 */
	public Paciente(String nome, int nif, int idade, String localidade, int telefone, String fotografia,
			String sistemaSaude, String raioX) {
		
		super(nome, nif, idade, localidade, telefone, fotografia);

		if (sistemaSaude == null) sistemaSaude = "";
		if (raioX == null) raioX = "";
		
		this.sistemaSaude = sistemaSaude;
		this.raioX = raioX;
		//
		Dados dadosPaciente = new Dados();
		dadosPaciente.escreverObjetoNoFicheiro(this);
	}
	
	/**
	 * Devolve o sistema de saude do paciente
	 */
	public String getSistemaSaude() {
		
		return this.sistemaSaude;
	}

	public String getRaioX() {
		
		return this.raioX;
	}
	
	/**
	 * Verifica se o paciente existe,
	 * pesquisando o nome do mesmo.
	 */
	public static Paciente encontrarPaciente(String nome, int nif) {
		
		try {
			Dados.receberConsultasBD();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		for (Paciente pac : totalPacientes)
			if (pac.getNome().equals(nome) && pac.getNif() == nif)
				return pac;
		
		return null;
	}
	
	
	/**
	 * Encontra uma consulta especifica do Paciente
	 * procurando a partir da data e do medico da consulta
	 */	
	public String findConsulta(Calendar data, Médico med) throws ClassNotFoundException, SQLException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		
		ArrayList<ArrayList<String>> consultas = findConsultasPaciente();
		for (ArrayList<String> con : consultas)
			if (con.get(1).equals(med.getNome()) && con.get(2).equals(formatter.format(data.getTime())))
				return con.toString();
		
		return "Não foi encontrada nenhuma consulta com os dados fornecidos para o paciente " + this.getNome() + ".";
	}
	
	/**
	 * Encontra todas as consultas marcadas do Paciente
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */	
	public ArrayList<ArrayList<String>> findConsultasPaciente() throws ClassNotFoundException, SQLException {
		
		ArrayList<ArrayList<String>> consultas = new ArrayList<ArrayList<String>>();
		
		Dados.receberConsultasBD();
		for (ArrayList<String> con : consultasExistentes)
			if (con.get(0).equals(this.getNome()))
				consultas.add(con);
				
		return consultas;	
	}

	/**
	 * Verifica se a consulta que está 
	 * a ser adicionada já existe ou não
	*/
	public static boolean verificarConsultaRepetida(String nomePaciente, String nomeMedico, String data) {
		
		for (int i = 0; i < consultasExistentes.size(); i++) {
			ArrayList<String> consulta = consultasExistentes.get(i);

			if (consulta.get(0).equals(nomePaciente) && consulta.get(1).equals(nomeMedico) && consulta.get(2).equals(data)) 
				return true;
		}
		return false;
	}

	/**
	 * Verificar disponibilidade do Paciente
	 * (para não sobrepor consultas)
	 */
	@Override
	public boolean verificarDisponibilidade(Calendar data) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");

		for (int i = 0; i < consultasExistentes.size(); i++)
			if (consultasExistentes.get(i).get(1).equals(this.getNome()))
				if (consultasExistentes.get(i).get(3).equals(formatter.format(data.getTime())))
					return false;	
		return true;	
	}
	
	/**
	 * Marcar consulta a um paciente
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void adicionarConsulta(Médico med, Calendar data, String esp) throws ClassNotFoundException, SQLException {

		Consulta c = new Consulta(this, med, data, esp);
		Dados.receberConsultasBD();
	}
	

	/**
	 * Desmarcar consulta de um paciente
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private boolean removerConsulta(Médico med, Calendar data) throws ClassNotFoundException, SQLException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		
		for (ArrayList<String> con : consultasExistentes)
			if (con.get(1).equals(med.getNome()) && con.get(2).equals(formatter.format(data.getTime()))) {
				
				// Eliminar o registo da consulta desatualizada
				Consulta.eliminarConsulta(con.get(0));	
				Dados.receberConsultasBD();
				return true;
			}
		return false;
	}

	/**
	 * Representacao em String do objeto Paciente
	 */
	public String toString() {
		
		return "Paciente " + this.getNome() + " com numero de telemovel: " + this.getTelefone() + ", com consultas: \n" + consultas + "\n";	
	}
	
	/**
	 * Testes
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		// Ligacao a base de dados, recebe as consultas feitas anteriormente
		Dados.receberConsultasBD();
		Dados.lerFicheiroParaObjeto();

		// Médicos
		Médico med1 = new Médico("Dra. Matilde Santos", 121212123, 54, "Lisboa", 435345346, "", new String[] {"Periodontologia"}, 10);
		Médico med2 = new Médico("Dr. João Soares", 925647456, 46, "Sintra", 123642743, "", new String[] {"Periodontologia", "Ortodontia"}, 15);
		Médico med3 = new Médico("Dr. Rui Pinto", 121234668, 60, "Porto", 123575436, "", new String[] {"Odontopediatria"}, 26);
		
		// Pacientes
		Paciente pac1 = new Paciente("Daniela Gonçalves", 123456789, 20, "Lisboa", 987654321, "", "ADSE", "");
		Paciente pac2 = new Paciente("Beatriz Gonçalves", 543223525, 16, "Lisboa", 435436542, "", "MEDICARE", "");
		Paciente pac3 = new Paciente("Joana Cordeiro", 126584265, 12, "Loures", 234753873, "", "ADSE", "");

		// Verificar se os pacientes e medicos estão guardados
		//System.out.println(totalPacientes);
		//System.out.println(Médico.totalMedicos);
		
		// Horarios (escolhidos em um calendário)
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2021, 10, 07, 12, 30);	

		Calendar cal2 = Calendar.getInstance();
		cal2.set(2021, 9, 19, 12, 30);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(2021, 6, 21, 15, 00);
		
		/** **************************** PARTE 1 ***************************** */
		/*
		// Sucesso - Adicionar consulta ao pac1
		try {
			System.out.println("\n Adicionar consulta ao pac1.");
			pac1.adicionarConsulta(med1, cal1, "Periodontologia"); 		
			System.out.println(consultasExistentes.toString());
		} 
		catch (IllegalArgumentException ex) {
			ex.printStackTrace(System.out);
		}
		
		// Sucesso - Adicionar consulta ao pac2	
		try {
			System.out.println("\n Adicionar consulta ao pac2.");
			pac2.adicionarConsulta(med2, cal3, "Ortodontia"); 
			System.out.println(consultasExistentes.toString());
		} 
		catch (IllegalArgumentException ex) {
			ex.printStackTrace(System.out);
		}
		
		// Sucesso - Adicionar consulta ao pac3
		try {
			System.out.println("\n Adicionar consulta ao pac3.");
			pac3.adicionarConsulta(med3, cal1, "Odontopediatria");
			System.out.println(consultasExistentes.toString());
		}
		catch (IllegalArgumentException ex) {
			ex.printStackTrace(System.out);
		}

		// Erro - Tentar adicionar consulta em um Domingo ao pac1
		try {
			System.out.println("\n Adicionar uma consulta num Domingo.");
			pac1.adicionarConsulta(med2, cal2, "Periodontologia");  
			System.out.println(consultasExistentes.toString());
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace(System.out);
		}	

		// Erro - Tentar adicionar consulta repetida / hora ocupada 
		try {
			System.out.println("\n Adicionar uma consulta repetida e em uma hora ocupada.");
		    pac1.adicionarConsulta(med1, cal1, "Periodontologia");  
			System.out.println(consultasExistentes.toString());
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace(System.out);
		}	
	
		// Erro - Tentar adicionar consulta numa hora ocupada do paciente
		try {			
			System.out.println("\n Adicionar consulta numa hora ocupada do paciente. (Sobreposicao de consultas)");
			pac2.adicionarConsulta(med2, cal3, "Ortodontia");
			System.out.println(consultasExistentes.toString());
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace(System.out);
		}	
		
		// Erro - Adicionar uma consulta de uma especialidade que o médico nao tem
		try {			
			System.out.println("\n O médico não tem a especialidade pedida.");
			pac3.adicionarConsulta(med2, cal3, "Odontopediatria");
			System.out.println(consultasExistentes.toString());
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace(System.out);
		}*/
		
		/** **************************** PARTE 2 ***************************** */
		
		/*
		// Verificar se medico esta ocupado
		System.out.println("\nMédico med1 tem disponiblidade na data cal1. -> " + med1.verificarDisponibilidade(cal1));
		
		// Alterar a consulta de pac1, de med1 para med3, cal1 para cal3
		try {			
			System.out.println("\nAlterar a consulta de pac1, Medico med1 passa para med3, cal1 para cal3.");
			pac1.alterarConsulta(med1, cal1, med3, cal3, "Odontopediatria");
			System.out.println(consultasExistentes.toString());
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace(System.out);
		}	
		
		// Verificar se medico esta ocupado
		System.out.println("\nMédico med1 tem disponiblidade na data cal1. -> " + med1.verificarDisponibilidade(cal1));
		*/
		
		/** **************************** PARTE 3 ***************************** */
		
		/*
		// Eliminar a consulta do pac2
		try {			
			System.out.println("\nApagar a consulta de pac2, com o med2 e cal3 -> " + pac2.removerConsulta(med2, cal3) + "\n");		
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace(System.out);
		}
		
		// Obter informacoes sobre uma especialidade
		System.out.println(Especialidades.explicacao(Especialidades.PERIODONTOLOGIA));
		System.out.println(Especialidades.explicacao(Especialidades.ORTODONTIA));
		
		// Obter as consultas totais dos pacientes
		System.out.println("\nConsultas do pac1: " + pac1.findConsultasPaciente());
		System.out.println("Consultas do pac2: " + pac2.findConsultasPaciente());
		System.out.println("Consultas do pac3: " + pac3.findConsultasPaciente());
		*/
		
		
		// Ligacao a base de dados, recebe as consultas feitas anteriormente
		Dados.receberConsultasBD();	
		

		/*System.out.println("\n Ações Efetuadas aos Pacientes:");
		
		Collections.sort(totalPacientes, new ordenarPorNome());
		for (int i = 0; i < totalPacientes.size(); i++)
			System.out.println("\n" + totalPacientes.get(i));
		*/
		System.out.println("\n Total consultas marcadas: \n");
		
		if(consultasExistentes.size() == 0) System.out.println("   Não há consultas marcadas.");
		
		for (int i = 0; i < consultasExistentes.size(); i++) {			
			System.out.println(consultasExistentes.get(i).toString());
		}
	}
}	

class ordenarPorNome implements Comparator<Paciente> {

	// Ordenar os nomes dos pacientes
    public int compare(Paciente pacA, Paciente pacB)
    {
        return pacA.getNome().compareTo(pacB.getNome());
    } 
}



