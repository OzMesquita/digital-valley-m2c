/**
 * 
 */
package dao;

import java.util.List;

import model.Disciplina;
import model.Professor;

/**
 * @author N2S-PC01
 *
 */
public interface DisciplinaDAO {
	public void cadastrar(Disciplina disciplina);
	public List<Disciplina> listar(int inicio, int fim);
	public void editar(Disciplina disciplina);
	public void deletar(Disciplina disciplina);
	public Disciplina getById(int id);
	public List<Disciplina> buscarPorProfessor(Professor professor, int inicio, int fim);
}
