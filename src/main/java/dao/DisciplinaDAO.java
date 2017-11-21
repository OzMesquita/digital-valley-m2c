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
	public List<Disciplina> listar();
	public void editar(Disciplina disciplina);
	public void deletar(Disciplina disciplina);
	public void getById(int id);
}