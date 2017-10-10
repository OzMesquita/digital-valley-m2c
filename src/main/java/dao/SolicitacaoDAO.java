package dao;

import br.com.n2s.model.Aluno;
import br.com.n2s.model.EnumSolicitacao;
import br.com.n2s.model.Professor;
import br.com.n2s.model.Solicitacao;

public interface SolicitacaoDAO {
	
	public void cadastrar(Solicitacao solicitacao);
	public void buscarPorId(int id);
	public void buscarPorAluno(Aluno aluno);
	public void buscarPorProfessor(Professor professor);
	public void buscarPorTipo(EnumSolicitacao tipo);
		
}
