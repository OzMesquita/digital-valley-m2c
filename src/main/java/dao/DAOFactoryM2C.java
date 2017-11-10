/**
 * 
 */
package dao;

/**
 * @author Matheus Diógenes
 *
 */
public class DAOFactoryM2C {
	
	private DAOFactoryM2C () {
		//
	}
	
	public static SolicitacaoDAO criarSolicitacaoDAO() {
		return new JPAHSolicitacaoDAO();
	}
}
