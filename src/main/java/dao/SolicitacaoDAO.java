package dao;

import model.Aluno;
import model.Professor;

public interface SolicitacaoDAO {
	
	public void cadastrar(Solicitacao solicitacao);
	
	public void buscarPorId(int id);
	
	public void buscarPorAluno(Aluno aluno);
	
	public void buscarPorProfessor(Professor professor);
	
	public void buscarPorTipo(EnumSolicitacao tipo);
		
}
