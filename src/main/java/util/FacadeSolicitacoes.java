package util;


import model.Solicitacao;
import model.Email;

public class FacadeSolicitacoes {
	private FacadeSolicitacoes() {
		//
	}

	public static void enviarEmailSolicitacaoSegundaChamada(Solicitacao solicitacao) {
		if (solicitacao.getAluno() != null) {
			
			Email e = new Email();
			e.sendEmail("Solicitação de Segunda chamada", "O Aluno "+ solicitacao.getAluno().getNome()+" solicitou a Segunda Chamada da prova de "+solicitacao.getDisciplina().getNome()
					, solicitacao.getProfessor().getEmail(), "Usuário Controle de Acesso");
		} else {
			throw new IllegalArgumentException("Erro aluno não pode ser vazio");
		}
		

	}
}
