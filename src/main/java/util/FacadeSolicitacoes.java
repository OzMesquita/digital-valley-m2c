package util;


import model.Servidor;
import model.Aluno;
import model.Disciplina;
import model.Email;

public class FacadeSolicitacoes {
	private FacadeSolicitacoes() {
		//
	}

	public static void enviarEmailSolicitacaoSegundaChamada(Aluno aluno, Servidor professor, Disciplina disciplina) {
		if (aluno != null && professor !=null) {
			
			Email e = new Email();
			e.sendEmail("Solicitação de Segunda chamada", "O Aluno "+aluno.getNome()+" solicitou a Segunda Chamada da prova de "+disciplina.getId()
					, professor.getEmail(), "Usuário Controle de Acesso");
		} else {
			throw new IllegalArgumentException("Erro ao enviar email");
		}
		

	}
}
