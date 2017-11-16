package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Aluno;
import model.Professor;
import model.Solicitacao;

public class JDBCSolicitacaoDAO extends JDBCDAO implements SolicitacaoDAO{

	@Override
	public void cadastrar(Solicitacao solicitacao) {
		super.open();
		try {
			String SQL = "INSERT INTO \"ctrl-acesso\".solicitacao(data_solicitacao, data_prova, justificativa, id_aluno,id_professor, id_disciplina, tipo) VALUES" + "( ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = super.getConnection().prepareStatement(SQL);

			ps.setDate(1, Date.valueOf(solicitacao.getDataSolicitacao()));
			ps.setDate(2, Date.valueOf(solicitacao.getDataSolicitacao()));
			ps.setString(3, solicitacao.getJustificativa());
			ps.setInt(4, solicitacao.getAluno().getId());
			ps.setInt(5, solicitacao.getProfessor().getId());
			ps.setInt(6, solicitacao.getDisciplina().getId());
			ps.setString(7, solicitacao.getTipoSolicitacao().valorSolicitacao);

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao cadastrar solicitação, erro: " + e.getMessage());
		} finally {
			super.close();
		}

	}


	@Override
	public Solicitacao buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Solicitacao> buscarPorAluno(Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Solicitacao> buscarPorProfessor(Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Solicitacao> listar(int inicio, int fim) {
		// TODO Auto-generated method stub
		return null;
	}

}
