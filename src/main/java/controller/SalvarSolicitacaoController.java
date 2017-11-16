package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import model.Aluno;
import model.Disciplina;
import model.Professor;
import model.Solicitacao;
import util.Constantes;

public class SalvarSolicitacaoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String nome = request.getParameter("inputName");
		String matricula = request.getParameter("inputMatricula");
		String nomeProfessor = request.getParameter("inputProfessor");
		String nomeDisciplina = request.getParameter("inputDisciplina");
		String dataProva = request.getParameter("inputDataProva");
		LocalDate data = util.Facade.converterStringParaLocalDate(dataProva);
		String justificativa = request.getParameter("justificativa");
		String tipoS = request.getParameter("tipoS");
		String tipoR = request.getParameter("tipoR");
		String tipoSolicitacao = "";
		String pagina = "homeSolicitacao.jsp";
		if(tipoS.equals("Segunda Chamada")) {
			tipoSolicitacao = tipoS;
		}else if(tipoR.equals("Recorrecao")) {
			tipoSolicitacao = tipoR;
		}
		HttpSession session = request.getSession();
		try {
			Aluno aluno = DAOFactory.criarAlunoDAO().buscarPorMatricula(matricula);

			Professor professor = DAOFactory.criarProfessorDAO().buscarPorSiape("");
			professor.setNome(nomeProfessor);

			Disciplina disciplina = new Disciplina(); // CHAMAR DISCIPLINA DAO QUANDO ESTIVER PRONTO
			disciplina.setNome(nomeDisciplina);

			if (util.FacadeSolicitacoes.verificarDias(data)) {
				Solicitacao solicitacao = new Solicitacao();
				solicitacao.setAluno(aluno);
				solicitacao.setDataProva(data);
				solicitacao.setDisciplina(disciplina);
				solicitacao.setDataSolicitacao(LocalDate.now());
				solicitacao.setJustificativa(justificativa);
				solicitacao.setProfessor(professor);
				solicitacao.setTipoSolicitacao(tipoSolicitacao);
				util.FacadeSolicitacoes.salvarSolicitacao(solicitacao);
				pagina += "";
				
			} else {
				session.setAttribute(Constantes.getSessionMsg(), "Prazo de solicitação expirado");
			}

		} catch (Exception e) {
			session.setAttribute(Constantes.getSessionMsg(), "Erro ao pedir solcitacao :"+e.getMessage());
		}
		
		response.sendRedirect("homeSolicitacao.jsp");

	}

}
