package util;

import model.Solicitacao;


import java.time.LocalDate;

import dao.DAOFactoryM2C;
import dao.SolicitacaoDAO;
import model.Email;
import model.EnumSolicitacao;

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
		}else {
			throw new NullPointerException("Solicitação nula.");
		}

	}
	
	public static boolean verificarDias(LocalDate dataProva){
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
		SolicitacaoDAO sDAO = DAOFactoryM2C.criarJDBCSolicitacaoDAO();
		sDAO.cadastrar(solicitacao);
		
	}
}
