/**
 * 
 */
package dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import model.Aluno;
import model.Professor;
import model.Solicitacao;

/**
 * @author Matheus Diógenes
 *
 */
public class JPAHSolicitacaoDAOTest {

	@Test
	public void cadastrar() {
		SolicitacaoDAO jpaHSolicitacaoDAO = DAOFactoryM2C.criarSolicitacaoDAO();
		Aluno aluno = DAOFactory.criarAlunoDAO().listar().get(0);
		Professor professor = DAOFactory.criarProfessorDAO().listar().get(0);
		LocalDate dataProva = LocalDate.of(2017, 10, 30);
		LocalDate dataAtual = LocalDate.now();		
		String justificativa = "Justificativa da solicitação";
		Solicitacao solicitacao = new Solicitacao(aluno, professor, dataProva, dataAtual, justificativa);
		jpaHSolicitacaoDAO.cadastrar(solicitacao);
		List<Solicitacao> solicitacoes = jpaHSolicitacaoDAO.buscarPorAluno(aluno);
		boolean find = false;
		for (Solicitacao solicitacao2 : solicitacoes) {
			if (solicitacao2.getDataProva().equals(solicitacao.getDataSolicitacao())) {
				find = true;
				break;
			}
		}
		assertTrue(find);
	}

}
