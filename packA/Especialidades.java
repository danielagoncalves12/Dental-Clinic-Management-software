package packA;

public enum Especialidades {

	PERIODONTOLOGIA(1), ORTODONTIA(2), ODONTOPEDIATRIA(3), OUTRO(4);

	private final int valor;
	
	Especialidades(int opcao){
		valor = opcao;
	}		
		

public static String explicacao(Especialidades opcao){
	
	if(opcao == Especialidades.PERIODONTOLOGIA) {
		
		return "Ciência que estuda e trata as doenças do sistema de implantação e suporte dos dentes.";
	}
	else if(opcao == Especialidades.ORTODONTIA) {
		
		return "Especialidade odontológica que corrige a posição dos "
				+ "dentes e dos ossos maxilares posicionados de forma inadequada.";
	}
	else if(opcao == Especialidades.ODONTOPEDIATRIA) {
		
		return "Especialização da odontologia que cuida de crianças e adolescentes.";
	}
	else {
		return "Pequenas cirurgias.";
	}
}

}