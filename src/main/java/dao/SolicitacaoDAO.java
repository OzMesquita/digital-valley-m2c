package dao;

import java.util.List;

import model.Aluno;
import model.EnumSolicitacao;
import model.Professor;
import model.Solicitacao;

public interface SolicitacaoDAO {
		
	public void cadastrar(Solicitacao solicitacao);
	
	public Solicitacao buscarPorId(int id);
	
	public List<Solicitacao> buscarPorAluno(Aluno aluno);
	
	public List<Solicitacao> buscarPorProfessor(Professor professor);
	
	public List<Solicitacao> buscarPorTipo(EnumSolicitacao tipo);
		
}
