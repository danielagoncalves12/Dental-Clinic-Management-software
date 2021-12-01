package packA;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Pessoa implements Serializable{


	private static final long serialVersionUID = 1339958344716827571L;

	// Declarar as variaveis
	private String nome, localidade, fotografia;
	private int nif;

	private int idade;

	private int telefone;
	
	public abstract String toString();	
	public abstract boolean verificarDisponibilidade(Calendar data);
	
	
	/**
	 * Constructor
	 */
	public Pessoa(String nome, int nif, int idade, String localidade, int telefone, String fotografia) {
	
		// O nome da pessoa tem que conter pelo menos uma letra
		if (nome == null)
			throw new IllegalArgumentException("Nome inválido. Insira pelo menos uma caracter.");
		
		// O nif tem que conter 9 caracteres
		if (String.valueOf(nif).length() != 9)
			throw new IllegalArgumentException("NIF inválido. Insira NIF com 9 números");
			
		// O numero de telefone tem que conter 9 caracteres
		if (String.valueOf(telefone).length() != 9)
			throw new IllegalArgumentException("Numéro telemóvel inválido. Insira com 9 caracteres");
			
		// A idade tem que estar entre 0 e 120
		if (idade < 0 && idade > 120)
			throw new IllegalArgumentException("Idade incorreta.");
		
		if (localidade == null) localidade = "";
		if (fotografia == null) fotografia = "";
			
		this.nome = nome;
		this.nif = nif;
		this.idade = idade;
		this.localidade = localidade;
		this.telefone = telefone;
		this.fotografia = fotografia;
	}

	public String getNome() {
		
		return nome;	
	}
	
	public int getTelefone() {
		
		return telefone;
	}
	
	public int getNif() {
		
		return nif;
	}
	
	public String cargo() {
		
		if (this instanceof Médico)
			return "Médico";
		
		else
			return "Paciente";
	}
	
	public String getLocalidade() {
		
		return localidade;
		
	}
	
	public String getFotografia() {
		
		return this.fotografia;
	}
	
	public int getIdade() {
		
		return idade;
	}
}