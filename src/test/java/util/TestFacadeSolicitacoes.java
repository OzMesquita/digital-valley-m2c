package util;


import java.time.LocalDate;
import java.time.LocalDateTime;

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
	
	@Test
	public void testEnviarEmailSegundaChamada(){
		Solicitacao s = FacadeSolicitacoes.listar(0, 20).get(0);
		s.getProfessor().setEmail("deyvisonbill01@gmail.com");
		System.out.println(s.getId());
		util.FacadeSolicitacoes.enviarEmailSolicitacao(s, "");
		
	}
	
	@Ignore
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

