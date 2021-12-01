package packA;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Médico extends Pessoa implements Serializable {

	private static final long serialVersionUID = 8626577997723258982L;
	public static ArrayList<Médico> totalMedicos = new ArrayList<Médico>();
	public String[] especialidades = new String[10];
	private int anosExperiencia;
	
	/**
	 * Construtor
	*/
	public Médico(String nome, int nif, int idade, String localidade, int telefone, String fotografia,
			String[] especialidades, int anosExperiencia) {
		
		super(nome, nif, idade, localidade, telefone, fotografia);
		
		if (anosExperiencia < 0)
			throw new IllegalArgumentException("Numero de anos de experiencia tem que ser positivo.");
		
		if (especialidades.length == 0)
			throw new IllegalArgumentException("É necessário adicionar uma especialidade.");
			
		this.especialidades = especialidades;
		this.anosExperiencia = anosExperiencia;
		//totalMedicos.add(this);
		
		Dados dadosMedico = new Dados();
		dadosMedico.escreverObjetoNoFicheiro(this);
	}
	
	/**
	 * Devolve os anos de experiencia
	*/
	public int getAnosExperiencia() {
		return this.anosExperiencia;
	}
	
	/**
	 * Verifica se o médico em questão 
	 * tem a especialidade recebida
	 */
	public boolean temEspecialidade(String nome) { 
		
		for (String n : especialidades)
			if (n.equals(nome))
				return true;
		
		return false;
	}
	
	/**
	 * Verifica se o médico tem 
	 * disponibilidade na data escolhida
	 */
	@Override
	public boolean verificarDisponibilidade(Calendar data) {
		
		// Se a data não for entre as 8h e 20h
		if (data.get(Calendar.HOUR_OF_DAY) > 20 && data.get(Calendar.HOUR_OF_DAY) < 8)
			return false;
		
		// Se já houver uma consulta para este médico marcada na mesma hora
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");

		for (ArrayList<String> con : Paciente.consultasExistentes)	
			if (con.get(2).equals(this.getNome()) && con.get(3).equals(formatter.format(data.getTime())))
				return false;	
		
		// Se chegar até aqui então o médico tem disponibilidade
		return true;
	}
	
	/**
	 * Representacao em String
	*/
	@Override
	public String toString() {

		return this.getNome();	
	}
}
