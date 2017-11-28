package dao;

import static org.junit.Assert.*;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;

import model.Aluno;
import model.Disciplina;
import model.EnumSolicitacao;
import model.Professor;
import model.Solicitacao;

public class JDBCSolicitacaoDAOTest {
		
	@Test
	public void cadastrar(){		
		LocalDate dataSolicitacao = LocalDate.of(2017, 11, 20);
		LocalDate dataProva = LocalDate.of(2017, 11, 19);
		String justificativa = "Justificativa";
		Aluno aluno = DAOFactory.criarAlunoDAO().listar().get(0);
		DisciplinaDAO disciplinaDao = DAOFactoryM2C.criarDisciplinaDAO();
		Disciplina disciplina = disciplinaDao.listar(0, 20).get(0);
		Professor professor = disciplina.getProfessor();
		EnumSolicitacao enumSolicitacao = EnumSolicitacao.RECORRECAO;
		
		Solicitacao solicitacao = new Solicitacao();
		solicitacao.setDataProva(dataProva);
		solicitacao.setDataSolicitacao(dataSolicitacao);
		solicitacao.setAluno(aluno);
		solicitacao.setProfessor(professor);
		solicitacao.setDisciplina(disciplina);
		solicitacao.setJustificativa(justificativa);
		solicitacao.setTipoSolicitacao(enumSolicitacao);						
		SolicitacaoDAO soliDAO = DAOFactoryM2C.criarSolicitacaoDAO();
		soliDAO.cadastrar(solicitacao);	
		
		Solicitacao solicitacaoDoBanco = soliDAO.buscarPorTipo(enumSolicitacao, 0, 1).get(0);		
		assertTrue ("Verificações ", solicitacaoDoBanco.getAluno().getId() == solicitacao.getAluno().getId() && solicitacaoDoBanco.getDataProva().equals(solicitacao.getDataProva()) &&
				solicitacaoDoBanco.getDataSolicitacao().equals(solicitacao.getDataSolicitacao()) && solicitacaoDoBanco.getDisciplina().getId() == solicitacao.getDisciplina().getId() &&
				solicitacaoDoBanco.getId() == solicitacao.getId() && solicitacaoDoBanco.getJustificativa().equals(solicitacao.getJustificativa()) && 
				solicitacaoDoBanco.getProfessor().getId() == solicitacao.getProfessor().getId() && solicitacaoDoBanco.getTipoSolicitacao().equals(solicitacao.getTipoSolicitacao()));
	}
}