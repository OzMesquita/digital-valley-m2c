package model;

public enum EnumSolicitacao {

	SEGUNDA_CHAMADA, 
	RECORRECAO;
	
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
