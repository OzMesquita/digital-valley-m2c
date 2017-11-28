package util;

import model.Solicitacao;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.DAOFactoryM2C;
import dao.SolicitacaoDAO;
import model.Aluno;
import model.Email;
import model.EnumSolicitacao;
import model.Professor;

public class FacadeSolicitacoes {
	private FacadeSolicitacoes() {
		//
	}

	public static void enviarEmailSolicitacao(Solicitacao solicitacao) {
		if (solicitacao != null) {
			String msg = "";
			if (solicitacao.getTipoSolicitacao() != null) {
				if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.SEGUNDA_CHAMADA)) {
					msg = "O Aluno " + solicitacao.getAluno().getNome() + " solicitou a Segunda Chamada da prova de "
							+ solicitacao.getDisciplina().getNome() + " realizada na data de: "
							+ solicitacao.getDataProva() + " com a justificativa de : \""
							+ solicitacao.getJustificativa() + "\"";
				} else if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
					msg = "O Aluno " + solicitacao.getAluno().getNome() + " solicitou a Recorreção da prova de "
							+ solicitacao.getDisciplina().getNome() + " realizada na data de: "
							+ solicitacao.getDataProva() + " com a justificativa de : \""
							+ solicitacao.getJustificativa() + "\"";
				}
			} else {
				throw new IllegalArgumentException("Tipo de solicitação não informado corretamente, valor informado: "
						+ solicitacao.getTipoSolicitacao());

			}
			if (!msg.equals("")) {
				Email e = new Email();
				e.sendEmail("Solicitação de Segunda chamada", msg, solicitacao.getProfessor().getEmail(),
						"Usuário Controle de Acesso");
			} else {
				throw new IllegalArgumentException("Erro ao enviar o email");
			}
		} else {
			throw new NullPointerException("Solicitação nula.");
		}

	}

	public static boolean verificarDias(LocalDate dataProva) {
		LocalDate hoje = LocalDate.now();
		if (hoje.getDayOfWeek().name().equals("TUESDAY")) {
			hoje = hoje.minusDays(2);
		} else if (hoje.getDayOfWeek().name().equals("MONDAY")) {
			hoje = hoje.minusDays(2);
		} else if (hoje.getDayOfWeek().name().equals("SUNDAY")) {
			hoje = hoje.minusDays(1);
		}

		if (dataProva.plusDays(4).isAfter(hoje)) {
			return true;
		} else {
			return false;
		}

	}

	public static void salvarSolicitacao(Solicitacao solicitacao) {
		SolicitacaoDAO sDAO = DAOFactoryM2C.criarSolicitacaoDAO();
		sDAO.cadastrar(solicitacao);
	}

	public static Solicitacao buscarPorId(int id) {
		Solicitacao solicitacao = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorId(id);
		solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(solicitacao.getAluno().getId()));
		solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(solicitacao.getProfessor().getId()));
		solicitacao.getDisciplina().setProfessor(solicitacao.getProfessor());
		solicitacao.getDisciplina().setNome("criarDAO");
		return solicitacao;
	}	

	public static ArrayList<Solicitacao> buscarPorAluno(Aluno aluno, int inicio, int fim) {
		ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) DAOFactoryM2C.criarSolicitacaoDAO()
				.buscarPorAluno(aluno, inicio, fim);
		for (Solicitacao solicitacao : solicitacoes) {
			solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(solicitacao.getProfessor().getId()));
			solicitacao.getDisciplina().setProfessor(solicitacao.getProfessor());
			solicitacao.getDisciplina().setNome("criarDAO");
		}
		return solicitacoes;
	}

	public static ArrayList<Solicitacao> buscarPorProfessor(Professor professor, int inicio, int fim) {
		ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) DAOFactoryM2C.criarSolicitacaoDAO()
				.buscarPorProfessor(professor, inicio, fim);
		for (Solicitacao solicitacao : solicitacoes) {
			solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(solicitacao.getAluno().getId()));
			solicitacao.getDisciplina().setProfessor(professor);
			solicitacao.getDisciplina().setNome("criarDAO");
		}
		return solicitacoes;
	}
	
	public static ArrayList<Solicitacao> listar(int inicio, int fim) {
		ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) DAOFactoryM2C.criarSolicitacaoDAO()
				.listar(inicio, fim);
		for (Solicitacao solicitacao : solicitacoes) {
			solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(solicitacao.getAluno().getId()));
			solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(solicitacao.getProfessor().getId()));
			solicitacao.getDisciplina().setProfessor(solicitacao.getProfessor());
			solicitacao.getDisciplina().setNome("criarDAO");
		}
		return solicitacoes;
	}


}
