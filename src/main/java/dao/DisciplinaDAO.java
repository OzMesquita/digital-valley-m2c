/**
 * 
 */
package dao;

import java.util.List;

import model.Disciplina;

/**
 * @author N2S-PC01
 *
 */
public interface DisciplinaDAO {
	public void cadastrar(Disciplina disciplina);
	public List<Disciplina> listar(int inicio, int fim);
	public List<Disciplina> listar();
	public void editar(Disciplina disciplina);
	public void deletar(Disciplina disciplina);
	public Disciplina getById(int id);
	public List<Disciplina> buscarPorProfessor(int idProfessor);
	public List<Disciplina> buscarPorCurso(int idCurso);
	public void associarDiscProf(int idDisciplina, int idProfessor);
}
