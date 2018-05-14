package util;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
		Solicitacao solicitacao = FacadeSolicitacoes.listar(0, 20).get(0);
		solicitacao.getProfessor().setEmail("matheusdiogenesandrade@gmail.com");
		String nomeArquivo = solicitacao.getId() + "_"
				+ solicitacao.getTipoSolicitacaoToString() + "_"
				+ solicitacao.getDataEHoraProva().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss")) + ".pdf";
		String localArquivo = Constantes.getTEMP_PDF_SOLICITACAO() + nomeArquivo; 
		FacadeSolicitacoes.gerarPDFDaSolicitacao(solicitacao, localArquivo);
		FacadeSolicitacoes.enviarEmailSolicitacao(solicitacao, localArquivo, nomeArquivo);
		util.FacadeSolicitacoes.enviarEmailSolicitacao(solicitacao, localArquivo, nomeArquivo);
		
	}

	@Ignore
	@Test
	public void testVerificarDias() {
		Assert.assertTrue(util.FacadeSolicitacoes.verificarDias(LocalDate.of(2018, 04, 17)));
	}
	
	@Ignore
	@Test
	public void testLocalDataTimeToString() {
		LocalDateTime ld = LocalDateTime.now();
		System.out.println(FacadeSolicitacoes.converterLocalDateTimeToString(ld));
	}
	
	@Ignore
	@Test
	public void testStringToLocalTime() {
		String hora = "16:59";
		System.out.println(FacadeSolicitacoes.converterStringToLocalTime(hora));
		System.out.println(FacadeSolicitacoes.converterStringToLocalTime(hora).getClass());
	}
	
	@Test
	public void testBuscarDiscPorCursoEProfDif() {
		List<Disciplina> disciplinasLivres =  FacadeSolicitacoes.buscarDiscPorCursoEProfDif(1, 5);
		for(Disciplina d: disciplinasLivres) {
			System.out.println("Nome: "+ d.getNome());
			System.out.println("Curso: "+d.getCurso().getNome());
			System.out.println("Professor: "+d.getProfessor().getNome());
		}
	}
}

