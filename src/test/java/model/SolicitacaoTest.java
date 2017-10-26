package model;

import org.junit.Test;
import java.time.LocalDate;

public class SolicitacaoTest{

	Solicitacao solicitacao = new Solicitacao();
	
	
	Aluno aluno = new Aluno();
	Professor professor = new Professor();
	Disciplina disciplina = new Disciplina();
	String justificativa = ("texto texto");
	LocalDate dataProva = LocalDate.of(2017, 9, 13);
	LocalDate dataSolicitacao = LocalDate.now();
	
	
	@Test(expected=IllegalArgumentException.class)
	public void alunoInvalido(){		
		solicitacao.setAluno(null);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void professorInvalido(){		
		solicitacao.setProfessor(null);		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void disciplinaInvalida(){	
		solicitacao.setDisciplina(null);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void justificativaVazia(){		
		solicitacao.setJustificativa("");		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void justificativaNula(){		
		solicitacao.setJustificativa(null);		
	}
	
	@Test(expected=RuntimeException.class)
	public void dataProvaInvalida(){		
		solicitacao.setDataProva("28-10-17");
	}


	@Test(expected=IllegalArgumentException.class)
	public void dataSolicitacaoInvalida(){		
		solicitacao.setDataSolicitacao(null);
	}
	
		
}
