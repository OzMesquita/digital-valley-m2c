package controller;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
import util.FacadeSolicitacoes;

public class SalvarSolicitacaoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("inputName");
		String matricula = request.getParameter("matricula");
		String nomeProfessor = request.getParameter("inputProfessor");
		String siape = request.getParameter("siape");
		String nomeDisciplina = request.getParameter("inputDisciplina");
		String dataProva = request.getParameter("inputDataProva");
		LocalDate data = util.Facade.converterStringParaLocalDate(dataProva);
		LocalTime lt = LocalTime.of(0, 0);
		System.out.println("matri "+matricula);
		System.out.println("nome: "+nome);
		System.out.println(nomeProfessor);
		String justificativa = request.getParameter("justificativa");
		String tipoS = request.getParameter("tipoS");
		String tipoR = request.getParameter("tipoR");
		String tipoSolicitacao = "";
		String pagina = "homeSolicitacao.jsp?erroSalvar=1";
		if(tipoS != null && tipoS.equals("Segunda Chamada")) {
			tipoSolicitacao = tipoS;
			
		}else if(tipoR != null && tipoR.equals("Recorrecao")) {
			tipoSolicitacao = tipoR;
			lt = FacadeSolicitacoes.converterStringToLocalTime(request.getParameter("inputHoraProva"));
					
		}
		
		LocalDateTime ldt = LocalDateTime.of(data, lt);
		HttpSession session = request.getSession();
		try {
			System.out.println(matricula);
			Aluno aluno = DAOFactory.criarAlunoDAO().buscarPorMatricula(matricula);
			System.out.println(aluno.getNome());
			Professor professor = DAOFactory.criarProfessorDAO().buscarPorSiape("4785698");
			Disciplina disciplina = new Disciplina(); // CHAMAR DISCIPLINA DAO QUANDO ESTIVER PRONTO
			disciplina.setNome(nomeDisciplina);
			disciplina.setProfessor(professor);
			disciplina.setId(1);

			if (util.FacadeSolicitacoes.verificarDias(data)) {
				Solicitacao solicitacao = new Solicitacao();
				solicitacao.setAluno(aluno);
				solicitacao.setDataEHoraProva(FacadeSolicitacoes.converterLocalDateTimeToString(ldt));
				solicitacao.setDisciplina(disciplina);
				solicitacao.setDataSolicitacao(LocalDate.now());
				solicitacao.setJustificativa(justificativa);
				solicitacao.setProfessor(professor);
				solicitacao.setTipoSolicitacao(tipoSolicitacao);
				util.FacadeSolicitacoes.salvarSolicitacao(solicitacao);
				pagina = "homeSolicitacao.jsp?sucessoSalvar=1";
				
			} else {
				session.setAttribute(Constantes.getSessionMsg(), "Prazo de solicitação expirado");
				pagina = "homeSolicitacao.jsp?erroPrazo=1";
			}

		} catch (Exception e) {
			session.setAttribute(Constantes.getSessionMsg(), "Erro ao pedir solcitacao :"+e.getMessage());
		}
		
		response.sendRedirect(pagina);

	}

}
