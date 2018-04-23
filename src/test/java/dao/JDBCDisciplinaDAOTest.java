package dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.Disciplina;

public class JDBCDisciplinaDAOTest {
	
	@Test
	public void testBuscarPorCurso() {
		List<Disciplina> disciplinas = DAOFactoryM2C.criarDisciplinaDAO().buscarPorCurso(1);
		Assert.assertTrue(disciplinas.size() >0);
		for(Disciplina d:disciplinas) {
			System.out.println("Nome: "+d.getNome());
		}
		
		
	}

}
