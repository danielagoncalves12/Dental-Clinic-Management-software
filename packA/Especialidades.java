package packA;

public enum Especialidades {

	PERIODONTOLOGIA(1), ORTODONTIA(2), ODONTOPEDIATRIA(3), OUTRO(4);

	private final int valor;
	
	Especialidades(int opcao){
		valor = opcao;
	}		
		

public static String explicacao(Especialidades opcao){
	
	if(opcao == Especialidades.PERIODONTOLOGIA) {
		
		return "Ci�ncia que estuda e trata as doen�as do sistema de implanta��o e suporte dos dentes.";
	}
	else if(opcao == Especialidades.ORTODONTIA) {
		
		return "Especialidade odontol�gica que corrige a posi��o dos "
				+ "dentes e dos ossos maxilares posicionados de forma inadequada.";
	}
	else if(opcao == Especialidades.ODONTOPEDIATRIA) {
		
		return "Especializa��o da odontologia que cuida de crian�as e adolescentes.";
	}
	else {
		return "Pequenas cirurgias.";
	}
}

}