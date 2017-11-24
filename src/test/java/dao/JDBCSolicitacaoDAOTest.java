package dao;

import java.time.LocalDate;

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
		Disciplina disciplina = DAOFactoryM2C.criarDisciplinaDAO().listar(0, 20).get(0);
		Professor professor = disciplina.getProfessor();
		E
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
		SolicitacaoDAO soliDAO = DAOFactoryM2C.criarSolicitacaoDAO();
		soliDAO.cadastrar(solicitacao);	
		
	}
}