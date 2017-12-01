/**
 * 
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;
import model.EnumSolicitacao;
import model.Professor;
import model.Solicitacao;
import util.Constantes;

/**
 * @author Matheus Diógenes
 *
 */
public class JDBCDisciplinaDAO extends JDBCDAO implements DisciplinaDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.DisciplinaDAO#cadastrar(model.Disciplina)
	 */
	@Override
	public void cadastrar(Disciplina disciplina) {
		super.open();
		try {
			String SQL = "INSERT INTO \"" + Constantes.getPUBLIC_DATABASE_SCHEMA()
					+ "\".disciplina(nome, id_curso, id_professor) VALUES" + "( ?, ?, ?)";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setString(1, disciplina.getNome());
			ps.setInt(2, disciplina.getCurso().getId());
			ps.setInt(3, disciplina.getProfessor().getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao cadastrar disciplina, erro: " + e.getMessage());
		} finally {
			super.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.DisciplinaDAO#listar()
	 */
	@Override
	public List<Disciplina> listar(int inicio, int fim) {
		try {
			super.open();
			List<Disciplina> disciplinas = new ArrayList<Disciplina>();
			String SQL = "SELECT * FROM \"" + Constantes.getPUBLIC_DATABASE_SCHEMA() + "\".disciplina LIMIT ? OFFSET ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, fim - inicio);
			ps.setInt(2, inicio);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Disciplina disciplina = new Disciplina();
				disciplina.setCurso(DAOFactory.criarCursoDAO().buscar(rs.getInt("id_curso")));
				disciplina.setProfessor(DAOFactory.criarProfessorDAO().buscar(rs.getInt("id_professor")));
				disciplina.setId(rs.getInt("id_disciplina"));
				disciplina.setNome(rs.getString("nome"));
			}
			ps.close();
			rs.close();
			return disciplinas;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar disciplinas em JDBCDisciplinaDAO", e);

		} finally {
			super.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.DisciplinaDAO#editar(model.Disciplina)
	 */
	@Override
	public void editar(Disciplina disciplina) {
		super.open();
		try {
			String SQL = "UPDATE \"" + Constantes.getPUBLIC_DATABASE_SCHEMA()
					+ "\".disciplina SET nome = ?, id_curso = ?, id_professor = ? WHERE id_disciplina = ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setString(1, disciplina.getNome());
			ps.setInt(2, disciplina.getCurso().getId());
			ps.setInt(3, disciplina.getProfessor().getId());
			ps.setInt(4, disciplina.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao editar disciplina, erro: " + e.getMessage());
		} finally {
			super.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.DisciplinaDAO#deletar(model.Disciplina)
	 */
	@Override
	public void deletar(Disciplina disciplina) {
		super.open();
		try {
			String SQL = "DELETE \"" + Constantes.getPUBLIC_DATABASE_SCHEMA() + "\".disciplina WHERE id_disciplina = ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, disciplina.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao deletar disciplina, erro: " + e.getMessage());
		} finally {
			super.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.DisciplinaDAO#getById(int)
	 */
	@Override
	public Disciplina getById(int id) {
		super.open();
		try {
			String SQL = "SELECT * FROM \"" + Constantes.getPUBLIC_DATABASE_SCHEMA()
					+ "\".disciplina AS d WHERE d.id_disciplina = ?";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Disciplina disciplina = null;
			if (rs.next()) {
				disciplina = new Disciplina();
				disciplina.setCurso(DAOFactory.criarCursoDAO().buscar(rs.getInt("id_curso")));
				disciplina.setProfessor(DAOFactory.criarProfessorDAO().buscar(rs.getInt("id_professor")));
				disciplina.setId(rs.getInt("id_disciplina"));
				disciplina.setNome(rs.getString("nome"));
				ps.close();
				rs.close();
			}
			return disciplina;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar solcitacoes em JDBCSolicitacaoDAO", e);
		} finally {
			super.close();
		}
	}

	@Override
	public List<Disciplina> buscarPorProfessor(int idProfessor) {
		super.open();
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		try {
			String SQL = "SELECT nome, d.id_disciplina FROM " + Constantes.getPUBLIC_DATABASE_SCHEMA()
					+ ".professor_disciplina AS pd, "+ Constantes.getPUBLIC_DATABASE_SCHEMA()
					+ ".disciplina AS d WHERE pd.id_professor = ? AND pd.id_disciplina = d.id_disciplina";
			PreparedStatement ps = super.getConnection().prepareStatement(SQL);
			ps.setInt(1, idProfessor);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Disciplina disciplina = new Disciplina();
				disciplina.setNome(rs.getString("nome"));
				disciplina.setId(rs.getInt("id_disciplina"));
				disciplinas.add(disciplina);
			}
			ps.close();
			rs.close();
			return disciplinas;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar disciplinas em JDBCDisciplinaDAO", e);

		} finally {
			super.close();
		}
	}
	
	
	
}
