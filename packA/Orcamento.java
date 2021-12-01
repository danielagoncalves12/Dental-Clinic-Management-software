package packA;

import java.text.DecimalFormat;
import java.util.Calendar;
import com.aspose.words.Document;

public class Orcamento
{
	private String nomeFicheiro = null;
	
	public Orcamento(Consulta con) throws Exception {

		this.nomeFicheiro = criarOrcamento(con);
	}
	
	private String criarOrcamento(Consulta con) throws Exception {
		
		/* replace(java.lang.String oldValue, java.lang.String newValue, boolean isMatchCase, boolean isMatchWholeWord) */
		
		String pathFicheiro = null;
		int num = con.numConsulta;
		
		// Orcamento base (Template)
		Document doc = new Document("src\\tps\\tp4\\packA\\orcamentos\\orcamento_base.docx");

		// Numero Fatura
		doc.getRange().replace("_faturaNumero_", "" + num, true, true);
					
		// Dados do Paciente
		Paciente pac = con.getPaciente();
					
		String nomePac = pac.getNome();
		String locPac = pac.getLocalidade();
		int telPac = pac.getTelefone();
		int nifPac = pac.getNif();
					
		doc.getRange().replace("_Nome_", nomePac, true, true);
		doc.getRange().replace("_Localidade_", locPac, true, true);
		doc.getRange().replace("_Telefone_", ""+telPac, true, true);
		doc.getRange().replace("_NIF_", ""+nifPac, true, true);
					
		// Dados do Medico
		Médico med = con.getMedico();
					
		String nomeMed = med.getNome();
		String locMed = med.getLocalidade();
		int telMed = med.getTelefone();
		int nifMed = med.getNif();
					
	    doc.getRange().replace("_NomeMed_", nomeMed, true, true);
		doc.getRange().replace("_LocalidadeMed_", locMed, true, true);
		doc.getRange().replace("_TelefoneMed_", ""+telMed, true, true);
		doc.getRange().replace("_NIFMed_", ""+nifMed, true, true);
					
		// Dados da Consulta
		String especialidade = con.getEspecialidade();	
		doc.getRange().replace("_Especialidade_", especialidade, true, true);
			
		// Data
		Calendar cal = con.getData();
		String data = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + 
		cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
					
		doc.getRange().replace("_Data_", data, true, true);
					
		// Calculos Preço
		float precoBase  = calcularPreco(especialidade);
		float desconto   = calcularDesconto(precoBase, pac.getSistemaSaude());
		float IVA        = calcularIVA(precoBase);
		float precoTotal = calcularPrecoTotal(precoBase, IVA, desconto);
					
		// Selecionar apenas 2 decimais para o valor total				
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
					
		doc.getRange().replace("_PrecoBase_", ""+precoBase, true, true);
		doc.getRange().replace("_Desconto_", ""+desconto, true, true);
		doc.getRange().replace("_IVA_", ""+IVA, true, true);
		doc.getRange().replace("_PrecoTotal_", ""+df.format(precoTotal), true, true);
					
		
	    // Guardar o novo documento alterado
		String nomeFicheiro = "orcamento_num_" + num + ".docx";
		pathFicheiro = "src\\tps\\tp4\\packA\\orcamentos\\orcamentos_clientes\\" + nomeFicheiro;	
		doc.save(pathFicheiro);

		return nomeFicheiro;
	}
	

	/**
	 * Calcula preco de uma consulta de
	 * acordo com a especialidade escolhida
	 */
	private float calcularPreco(String esp) {
		
		float preco = (float) (esp.equals("Periodontologia") ? 60.0 :
	  		   esp.equals("Ortodontia")      ? 45.0 :
	  		   esp.equals("Odontopediatria") ? 60.0 : 30.0);	  
		
		return preco;
	}
	
	/**
	 * Calcula o desconto de uma consulta 
	 * proveniente do sistema de saude
	 */
	private float calcularDesconto(float precoBase, String sistemaSaude) {
		
		float desconto = 0.0f;
		
		if      (sistemaSaude.equals("ADSE"))     desconto = precoBase * 0.5f;
		else if (sistemaSaude.equals("MEDICARE")) desconto = precoBase * 0.25f;
		
		return desconto;		
	}
	
	/**
	 * Calcula o valor do IVA (23%)
	 */
	private float calcularIVA(float precoBase) {
		
		return precoBase * 0.23f;
	}
	
	/**
	 * Calcula o preço total da consulta
	 */
	private float calcularPrecoTotal(float pb, float IVA, float desconto) {

		return pb + IVA - desconto;	
	}
	
	/**
	 * Representacao do nome do ficheiro gerado em String
	 */
	public String toString() {
		
		return nomeFicheiro;
		
	}

    public static void main(String args[]) throws Exception
    {

    }
}