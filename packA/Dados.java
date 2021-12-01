package packA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dados {

	/**
	 * Gera um ficheiro com o nome do paciente ou medico
	 * em que guarda o objeto na respetiva diretoria
	*/
	public void escreverObjetoNoFicheiro(Object obj) {

		if (obj instanceof Paciente)
			try {			
				Paciente paciente = (Paciente) obj;
				final String filepathpacientes = "src\\tps\\tp4\\packA\\dados\\pacientes\\paciente_" 
												  + paciente.getNif() + ".txt";
				
				File tempFile = new File(filepathpacientes);
				if (!tempFile.exists())
				{
					FileOutputStream ficheiroSaida = new FileOutputStream(filepathpacientes);
					ObjectOutputStream objetoSaida = new ObjectOutputStream(ficheiroSaida);
					objetoSaida.writeObject(obj);
					
					ficheiroSaida.close();
					objetoSaida.close();
					
					System.out.println("Paciente " + paciente.getNome() + " adicionado com sucesso.");
				}
			} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if (obj instanceof Médico)
			try {
				
				Médico medico = (Médico) obj;
				final String filepathmedicos = "src\\tps\\tp4\\packA\\dados\\medicos\\medico_"
											   + medico.getNif() + ".txt";

				File tempFile = new File(filepathmedicos);
				if (!tempFile.exists())
				{
					FileOutputStream ficheiroSaida = new FileOutputStream(filepathmedicos);
					ObjectOutputStream objetoSaida = new ObjectOutputStream(ficheiroSaida);
					objetoSaida.writeObject(obj);
					
					ficheiroSaida.close();
					objetoSaida.close();
					
					System.out.println("Médico " + medico.getNome() + " adicionado com sucesso.");
				}
			} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Le todos os ficheiros guardados com pacientes e medicos
	 * e recebe os novamente no software
	*/	
	public static void lerFicheiroParaObjeto() throws IOException {
	
		Paciente.totalPacientes.clear();
		Médico.totalMedicos.clear();
		File pathPacientes = new File("src\\tps\\tp4\\packA\\dados\\pacientes");
		File pathMedicos   = new File("src\\tps\\tp4\\packA\\dados\\medicos");
		
		// Listar todos os ficheiros e diretorias
		File pacienteList[] = pathPacientes.listFiles();		
		File medicosList[]  = pathMedicos.listFiles();
		
		for(File ficheiro : pacienteList) {
			
			FileInputStream ficheiroEntrada = new FileInputStream(ficheiro);
            ObjectInputStream objetoEntrada = new ObjectInputStream(ficheiroEntrada);
            
            try { 
				Paciente pac = (Paciente) objetoEntrada.readObject();
				Paciente.totalPacientes.add(pac);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            objetoEntrada.close();
		}
		
		for(File ficheiro : medicosList) {
			
			FileInputStream ficheiroEntrada = new FileInputStream(ficheiro);
            ObjectInputStream objetoEntrada = new ObjectInputStream(ficheiroEntrada);
            
            try { 
				Médico med = (Médico) objetoEntrada.readObject();
				Médico.totalMedicos.add(med);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            objetoEntrada.close();
		}
	
	}	
	
	/**
	 * Conecta o software à base de dados
	 * preenche a ArrayList consultasExistentes
	 * com todas as consultas presentes na tabela das consultas
	*/
	public static void receberConsultasBD() throws ClassNotFoundException, SQLException {
		
		Paciente.consultasExistentes.clear();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/"
											+ "clinica_dentaria_48579", "root", "");
		Statement st = c.createStatement();

		ResultSet query = st.executeQuery("SELECT * FROM consultas");
		
		String id, paciente, medico, data, especialidade;
		while (query.next()) {
			
			id            = query.getString("id");
			paciente      = query.getString("nome_paciente");
			medico        = query.getString("nome_medico");
			data          = query.getString("horas");
			especialidade = query.getString("especialidade");
			
			ArrayList<String> array = new ArrayList<String>();
			array.add(id);
			array.add(paciente);
			array.add(medico);
			array.add(data);
			array.add(especialidade);

			Paciente.consultasExistentes.add(array);	
		}
	}
}
