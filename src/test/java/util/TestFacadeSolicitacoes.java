package util;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.type.LocalDateTimeType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import dao.DAOFactory;
import model.Aluno;
import model.Disciplina;
import model.EnumSolicitacao;
import model.Professor;
import model.Solicitacao;

public class TestFacadeSolicitacoes {
	
	@Ignore
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
		s.setDataEHoraProva("10/09/2017");
		s.setDisciplina(d);
		s.setJustificativa("Teste da justificatica"); 
		s.setTipoSolicitacao(EnumSolicitacao.RECORRECAO);
		util.FacadeSolicitacoes.enviarEmailSolicitacao(s);
		
	}
	@Test
	public void testVerificarDias() {
		Assert.assertTrue(util.FacadeSolicitacoes.verificarDias(LocalDate.of(2017, 11, 20)));
	}
	@Ignore
	@Test
	public void testLocalDataTimeToString() {
		LocalDateTime ld = LocalDateTime.now();
		System.out.println(FacadeSolicitacoes.converterLocalDateTimeToString(ld));
	}
	
	@Test
	public void testStringToLocalTime() {
		String hora = "16:59";
		System.out.println(FacadeSolicitacoes.converterStringToLocalTime(hora));
		System.out.println(FacadeSolicitacoes.converterStringToLocalTime(hora).getClass());
	}
}

