package dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import model.Disciplina;

public class JDBCDisciplinaDAOTest {
	@Ignore
	@Test
	public void testBuscarPorCurso() {
		List<Disciplina> disciplinas = DAOFactoryM2C.criarDisciplinaDAO().buscarPorCurso(1);
		Assert.assertTrue(disciplinas.size() >0);
		for(Disciplina d:disciplinas) {
			System.out.println("Nome: "+d.getNome());
		}
		
		
	}
	
	@Test
	public void testAssociarDiscProf() {
		DAOFactoryM2C.criarDisciplinaDAO().associarDiscProf(1, 82);
	}
	
	@Test
	public void testListarTodas() {
		List<Disciplina> disciplinas = DAOFactoryM2C.criarDisciplinaDAO().listar();
		
		for(Disciplina d:disciplinas) {
			System.out.println("Nome: "+d.getNome());
		}
		Assert.assertTrue(disciplinas.size() >0);
	}

}
