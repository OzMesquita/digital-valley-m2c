package model;

public enum EnumSolicitacao {

	SEGUNDA_CHAMADA("SEGUNDA_CHAMADA"), RECORRECAO("RECORRECAO");
	
	public String valorSolicitacao;
	
	private EnumSolicitacao(String valor) {
		valorSolicitacao=valor;
	}
	
	public String getValorSolicitacao() {
		return valorSolicitacao;
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
