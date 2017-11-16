package dao;

public class DAOFactoryM2C {
	
	private DAOFactoryM2C () {
		//
	}
	
	public static JDBCSolicitacaoDAO criarJDBCSolicitacaoDAO() {
		return new JDBCSolicitacaoDAO();
	}
}
