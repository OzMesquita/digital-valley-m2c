package model;

public enum EnumSolicitacao {

	SEGUNDA_CHAMADA("Segunda Chamada"), 
	RECORRECAO("Recorreção");
	
	
	public String tipoSolicitacao;
	
	EnumSolicitacao(String tipo) {
		tipoSolicitacao = tipo;
	}
	
	public static EnumSolicitacao getByString(String value){
		switch (value) {
		case "SEGUNDA_CHAMADA":
			return EnumSolicitacao.SEGUNDA_CHAMADA;
				
		case "RECORRECAO":
			return EnumSolicitacao.RECORRECAO;		

		default:
			return null;			
		}
	}
	
}
