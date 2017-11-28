import java.time.LocalDate;

import dao.DAOFactory;
import model.EnumSolicitacao;
import model.Solicitacao;
import util.FacadeSolicitacoes;

/**
 * 
 */

/**
 * @author Matheus Diógenes
 *
 */
public class Main {
	public static void main(String[] args) {
		FacadeSolicitacoes.gerarPDFDaSolicitacao(new Solicitacao(DAOFactory.criarAlunoDAO().listar().get(0), DAOFactory.criarProfessorDAO().listar().get(0), LocalDate.of(2017, 11, 26), LocalDate.now(), "justo", EnumSolicitacao.SEGUNDA_CHAMADA));
	}
}
