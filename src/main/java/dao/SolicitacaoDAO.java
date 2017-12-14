package dao;

import java.util.List;

import model.Aluno;
import model.EnumSolicitacao;
import model.Professor;
import model.Solicitacao;

public interface SolicitacaoDAO {
		
	public void cadastrar(Solicitacao solicitacao);
	
	public Solicitacao buscarPorId(int id);
	
	public List<Solicitacao> buscarPorAluno(Aluno aluno, int inicio, int fim);
	
	public List<Solicitacao> buscarPorProfessor(Professor professor, int inicio, int fim);
		
	public List<Solicitacao> listar(int inicio, int fim);
	
	public List<Solicitacao> buscarPorTipo(EnumSolicitacao tipo, int inicio, int fim);
	
	public int buscarQntdDeSolicitacoes();
}
