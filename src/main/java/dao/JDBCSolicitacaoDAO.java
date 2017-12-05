package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.EnumSolicitacao;
import model.Professor;
import model.Solicitacao;
import util.Constantes;

public class JDBCSolicitacaoDAO extends JDBCDAO implements SolicitacaoDAO {

	@Override
	public void cadastrar(Solicitacao solicitacao) {
		super.open();
		try {
			String SQL = "INSERT INTO \"" + Constantes.getTHIS_APP_DATABASE_SCHEMA()
					+ "\".solicitacao(data_solicitacao, data_hora_prova, justificativa, id_aluno,id_professor, id_disciplina, tipo, data_divulgacao_resultado) VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = super.getConnection().prepareStatement(SQL);

			ps.setDate(1, Date.valueOf(solicitacao.getDataSolicitacao()));
			ps.setTimestamp(2, Timestamp.valueOf(solicitacao.getDataEHoraProva()));
			ps.setString(3, solicitacao.getJustificativa());
			ps.setInt(4, solicitacao.getAluno().getId());
			ps.setInt(5, solicitacao.getProfessor().getId());
			ps.setInt(6, solicitacao.getDisciplina().getId());
			ps.setString(7, solicitacao.getTipoSolicitacao().toString());
			ps.setDate(8, Date.valueOf(solicitacao.getDataDivulgacaoResultadoProva()));
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
		super.open();
		try {
			String SQL = "SELECT * FROM \"" + Constantes.getTHIS_APP_DATABASE_SCHEMA()
					+ "\".solicitacao AS s WHERE s.id_solicitacao = ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Solicitacao solicitacao = new Solicitacao();
				solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(rs.getInt("id_aluno")));
				solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(rs.getInt("id_professor")));
				solicitacao.setDisciplina(DAOFactoryM2C.criarDisciplinaDAO().getById(rs.getInt("id_disciplina")));
				solicitacao.setDataEHoraProva(rs.getString("data_hora_prova"));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getString("data_solicitacao")));
				solicitacao.setId(rs.getInt("id_solicitacao"));
				solicitacao.setJustificativa(rs.getString("justificativa"));
				solicitacao.setTipoSolicitacao(EnumSolicitacao.getByString(rs.getString("tipo")));
				if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
					solicitacao.setDataDivulgacaoResultadoProva(LocalDate.parse(rs.getString("data_divulgacao_resultado")));
				}								
				ps.close();
				rs.close();
				return solicitacao;
			} else {
				ps.close();
				rs.close();
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar solcitacoes em JDBCSolicitacaoDAO", e);

		} finally {
			super.close();
		}

	}

	@Override
	public List<Solicitacao> buscarPorAluno(Aluno aluno, int inicio, int fim) {
		super.open();
		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			String SQL = "SELECT * FROM \"" + Constantes.getTHIS_APP_DATABASE_SCHEMA()
					+ "\".solicitacao AS s WHERE s.id_aluno = ? LIMIT ? OFFSET ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, aluno.getId());
			ps.setInt(2, fim - inicio);
			ps.setInt(3, inicio);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Solicitacao solicitacao = new Solicitacao();
				solicitacao.setAluno(aluno);
				solicitacao.setDataEHoraProva(rs.getString("data_hora_prova"));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getString("data_solicitacao")));
				solicitacao.setId(rs.getInt("id_solicitacao"));
				solicitacao.setJustificativa(rs.getString("justificativa"));
				solicitacao.setTipoSolicitacao(EnumSolicitacao.getByString(rs.getString("tipo")));
				solicitacao.setDisciplina(DAOFactoryM2C.criarDisciplinaDAO().getById(rs.getInt("id_disciplina")));
				solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(rs.getInt("id_professor")));
				if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
					solicitacao.setDataDivulgacaoResultadoProva(LocalDate.parse(rs.getString("data_divulgacao_resultado")));
				}								
				solicitacoes.add(solicitacao);

			}
			ps.close();
			rs.close();
			return solicitacoes;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar solcitacoes em JDBCSolicitacaoDAO", e);

		} finally {
			super.close();
		}

	}

	@Override
	public List<Solicitacao> buscarPorProfessor(Professor professor, int inicio, int fim) {
		super.open();
		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			String SQL = "SELECT * FROM \"" + Constantes.getTHIS_APP_DATABASE_SCHEMA()
					+ "\".solicitacao AS s WHERE s.id_professor = ? LIMIT ? OFFSET ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, professor.getId());
			ps.setInt(2, fim - inicio);
			ps.setInt(3, inicio);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Solicitacao solicitacao = new Solicitacao();
				solicitacao.setProfessor(professor);
				solicitacao.setDataEHoraProva(rs.getString("data_hora_prova"));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getString("data_solicitacao")));
				solicitacao.setId(rs.getInt("id_solicitacao"));
				solicitacao.setJustificativa(rs.getString("justificativa"));
				solicitacao.setTipoSolicitacao(EnumSolicitacao.getByString(rs.getString("tipo")));
				solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(rs.getInt("id_aluno")));
				solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(rs.getInt("id_professor")));
				solicitacao.setDisciplina(DAOFactoryM2C.criarDisciplinaDAO().getById(rs.getInt("id_disciplina")));
				if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
					solicitacao.setDataDivulgacaoResultadoProva(LocalDate.parse(rs.getString("data_divulgacao_resultado")));
				}								
				solicitacoes.add(solicitacao);
			}
			ps.close();
			rs.close();
			return solicitacoes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar solcitacoes em JDBCSolicitacaoDAO", e);
		} finally {
			super.close();
		}
	}

	@Override
	public List<Solicitacao> listar(int inicio, int fim) {
		super.open();
		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			String SQL = "SELECT * FROM \"" + Constantes.getTHIS_APP_DATABASE_SCHEMA()
					+ "\".solicitacao AS s ORDER BY data_solicitacao DESC LIMIT ? OFFSET ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, fim - inicio);
			ps.setInt(2, inicio);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Solicitacao solicitacao = new Solicitacao();
				solicitacao.setDataEHoraProva(LocalDateTime.parse(rs.getString("data_hora_prova")));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getString("data_solicitacao")));
				solicitacao.setId(rs.getInt("id_solicitacao"));
				solicitacao.setJustificativa(rs.getString("justificativa"));
				solicitacao.setTipoSolicitacao(EnumSolicitacao.getByString(rs.getString("tipo")));
				solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(rs.getInt("id_aluno")));
				solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(rs.getInt("id_professor")));
				solicitacao.setDisciplina(DAOFactoryM2C.criarDisciplinaDAO().getById(rs.getInt("id_disciplina")));
				if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
					solicitacao.setDataDivulgacaoResultadoProva(LocalDate.parse(rs.getString("data_divulgacao_resultado")));
				}								
				solicitacoes.add(solicitacao);
			}
			ps.close();
			rs.close();
			return solicitacoes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar solcitacoes em JDBCSolicitacaoDAO", e);

		} finally {
			super.close();
		}

	}

	@Override
	public List<Solicitacao> buscarPorTipo(EnumSolicitacao tipo, int inicio, int fim) {
		super.open();
		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			String SQL = "SELECT * FROM \"" + Constantes.getTHIS_APP_DATABASE_SCHEMA()
					+ "\".solicitacao AS s WHERE s.tipo = ? ORDER BY data_solicitacao DESC LIMIT ? OFFSET ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setString(1, tipo.toString());
			ps.setInt(2, fim - inicio);
			ps.setInt(3, inicio);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Solicitacao solicitacao = new Solicitacao();
				solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(rs.getInt("id_aluno")));
				solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(rs.getInt("id_professor")));
				solicitacao.setDisciplina(DAOFactoryM2C.criarDisciplinaDAO().getById(rs.getInt("id_disciplina")));
				solicitacao.setDataEHoraProva(rs.getString("data_hora_prova"));
				solicitacao.setDataSolicitacao(LocalDate.parse(rs.getString("data_solicitacao")));
				solicitacao.setId(rs.getInt("id_solicitacao"));
				solicitacao.setJustificativa(rs.getString("justificativa"));
				solicitacao.setTipoSolicitacao(EnumSolicitacao.getByString(rs.getString("tipo")));
				if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
					solicitacao.setDataDivulgacaoResultadoProva(LocalDate.parse(rs.getString("data_divulgacao_resultado")));
				}								
				solicitacoes.add(solicitacao);
			}
			ps.close();
			rs.close();
			return solicitacoes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao buscar por tipo solcitacoes em JDBCSolicitacaoDAO", e);

		} finally {
			super.close();
		}
	}

}
