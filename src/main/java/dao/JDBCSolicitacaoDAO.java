package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Disciplina;
import model.EnumSolicitacao;
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
		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			String SQL = "SELECT * FROM \"ctrl-acesso\".solicitacao AS s WHERE s.id_aluno = ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, aluno.getId());
			ResultSet rs = ps.executeQuery();
			
			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setAluno(aluno);
			Professor professor = new Professor();
			Disciplina disciplina = new Disciplina();
			while (rs.next()) {
				solicitacao.setDataProva(LocalDate.parse(rs.getString("data_prova")));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getString("data_solicitacao")));
				solicitacao.setId(rs.getInt("id_solicitacao"));
				solicitacao.setJustificativa(rs.getString("justificativa"));
				solicitacao.setTipoSolicitacao(EnumSolicitacao.getByString(rs.getString("tipo")));
				solicitacao.setDisciplina(disciplina);
				solicitacao.getDisciplina().setId(rs.getInt("id_disciplina"));
				solicitacao.setProfessor(professor);
				solicitacao.getProfessor().setId(rs.getInt("id_professor"));
				solicitacoes.add(solicitacao);
				
			}
			ps.close();
			rs.close();	
			return solicitacoes;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar solcitacoes em JDBCSolicitacaoDAO", e);

		}finally {
			super.close();
		}

	}

	@Override
	public List<Solicitacao> buscarPorProfessor(Professor professor) {
		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			String SQL = "SELECT * FROM \"ctrl-acesso\".solicitacao AS s WHERE s.id_professor = ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, professor.getId());
			ResultSet rs = ps.executeQuery();
			
			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setProfessor(professor);;
			Aluno aluno = new Aluno();
			Disciplina disciplina = new Disciplina();
			while (rs.next()) {
				solicitacao.setDataProva(LocalDate.parse(rs.getString("data_prova")));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getString("data_solicitacao")));
				solicitacao.setId(rs.getInt("id_solicitacao"));
				solicitacao.setJustificativa(rs.getString("justificativa"));
				solicitacao.setTipoSolicitacao(EnumSolicitacao.getByString(rs.getString("tipo")));
				solicitacao.setDisciplina(disciplina);
				solicitacao.getDisciplina().setId(rs.getInt("id_disciplina"));
				solicitacao.setAluno(aluno);
				solicitacao.getAluno().setId(rs.getInt("id_aluno"));
				solicitacoes.add(solicitacao);
				
			}
			ps.close();
			rs.close();	
			return solicitacoes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar solcitacoes em JDBCSolicitacaoDAO", e);

		}finally {
			super.close();
		}

	}


	@Override
	public List<Solicitacao> listar(int inicio, int fim) {
		// TODO Auto-generated method stub
		return null;
	}

}