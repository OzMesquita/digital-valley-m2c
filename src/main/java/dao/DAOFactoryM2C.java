package dao;

public class DAOFactoryM2C {
	
	private DAOFactoryM2C () {
		//
	}
	
	public static SolicitacaoDAO criarSolicitacaoDAO() {
		return new JDBCSolicitacaoDAO();
	}
	
	public static DisciplinaDAO criarDisciplinaDAO() {
		return new JDBCDisciplinaDAO();
	}
	
}
