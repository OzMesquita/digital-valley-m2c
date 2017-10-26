package model;

import org.junit.Test;

public class DisciplinaTest {

	Disciplina disciplina = new Disciplina();
	Curso curso = new Curso();
	Professor professor = new Professor();
	
	@Test
	public void testeValido(){
		disciplina.setCurso(curso);
		disciplina.setProfessor(professor);
	}

	@Test(expected=IllegalArgumentException.class)
	public void cursoInvalido(){
		disciplina.setCurso(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void professorInvalido(){
		disciplina.setProfessor(null);
	}
}