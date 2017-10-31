package model;

public enum EnumSolicitacao {

	SEGUNDACHAMADA(1), RECORRECAO(2);
	
	public int valorSolicitacao;
	
	private EnumSolicitacao(int valor) {
		valorSolicitacao=valor;
	}
	
	public int getValorSolicitacao() {
		return valorSolicitacao;
	}
	
	public static EnumSolicitacao getByString(String value){
		switch (value) {
		case "Segunda chamada":
			return EnumSolicitacao.SEGUNDACHAMADA;
				
		case "Recorreção":
			return EnumSolicitacao.RECORRECAO;		

		default:
			return null;			
		}
	}
	
}
