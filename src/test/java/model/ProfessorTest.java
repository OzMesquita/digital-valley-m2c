package model;

import java.util.ArrayList;
import br.com.n2s.model.*;
import org.junit.Test;

public class ProfessorTest {

	Professor professor = new Professor();
	
	Disciplina POO = new Disciplina();
	Disciplina FUP = new Disciplina();
	Disciplina Matematica = new Disciplina();
	
	ArrayList<Disciplina> disciplinas = new ArrayList<>();
	
	@Test
	public void disicplinasValidas (){
		professor.setDisciplinas(POO);
		professor.setDisciplinas(FUP);
		professor.setDisciplinas(Matematica);;
	}
	    
	@Test(expected=IllegalArgumentException.class)
	public void disicplinasInvalidas (){
		professor.setDisciplinas(null);
	}
	
}
