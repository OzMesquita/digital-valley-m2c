package dao;

public class DAOFactoryM2C {
	
	private DAOFactoryM2C () {
		//
	}
	
	public static SolicitacaoDAO criarJDBCSolicitacaoDAO() {
		return new JDBCSolicitacaoDAO();
	}
	
	public static SolicitacaoDAO criarJPAHSolicitacaoDAO() {
		return new JPAHSolicitacaoDAO();
	}
}
