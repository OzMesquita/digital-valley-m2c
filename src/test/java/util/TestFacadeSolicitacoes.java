package util;


import org.junit.Test;

import dao.DAOFactory;
import model.Aluno;
import model.Disciplina;
import model.EnumSolicitacao;
import model.Professor;
import model.Solicitacao;

public class TestFacadeSolicitacoes {
	
	@Test
	public void testEnviarEmailSegundaChamada(){
		Aluno a = DAOFactory.criarAlunoDAO().buscar(73);
		Professor p = new Professor();
		p.setNome("Professor teste");
		p.setEmail("deyvisonbill01@gmail.com");
		Disciplina d = new Disciplina();
		d.setNome("Disciplina teste");
		Solicitacao s = new Solicitacao();
		s.setAluno(a);
		s.setProfessor(p);
		s.setDataProva("10/09/2017");
		s.setDisciplina(d);
		s.setJustificativa("Teste da justificatica");
		s.setTipoSolicitacao(EnumSolicitacao.RECORRECAO);
		util.FacadeSolicitacoes.enviarEmailSolicitacao(s);
		
	}
	
}

