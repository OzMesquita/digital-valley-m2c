package dao;

import java.time.LocalDate;

import org.junit.Test;

import model.Disciplina;
import model.EnumSolicitacao;
import model.Solicitacao;

public class JDBCSolicitacaoDAOTest {
		

	
	@Test
	public void cadastrar(){
		
		Solicitacao solicitacao = new Solicitacao();
		Disciplina disc = new Disciplina();
		disc.setId(10);
		
		
		solicitacao.setDataProva("11/11/2017");
		solicitacao.setDataSolicitacao(LocalDate.now());
		solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(1));
		solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(5));
		solicitacao.setDisciplina(disc);
		solicitacao.setJustificativa("asnhasjaskjdamns djsadnma dlasbnd ");
		solicitacao.setTipoSolicitacao(EnumSolicitacao.SEGUNDA_CHAMADA);
		
		SolicitacaoDAO soliDAO = DAOFactoryM2C.criarJDBCSolicitacaoDAO();
		soliDAO.cadastrar(solicitacao);	
		
	}
}