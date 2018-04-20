package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.DAOFactoryM2C;
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
		HttpSession session = request.getSession();
		String pagina = "homeSolicitacao.jsp?erroSalvar=1";

		try {
		
		String matricula = request.getParameter("matricula");
		int idProfessor = Integer.valueOf(request.getParameter("valueIdProfessor"));
		String idDisciplina = request.getParameter("inputDisciplina");
		String dataProva = request.getParameter("inputDataProva");
		LocalDate data = util.Facade.converterStringParaLocalDate(dataProva);
		LocalDate dataEntrega = data;
		LocalTime lt = LocalTime.of(0, 0);
		String justificativa = request.getParameter("justificativa");
		String tipoS = request.getParameter("tipoS");
		String tipoR = request.getParameter("tipoR");
		String tipoSolicitacao = "";
		boolean prazoValido = false;

		if (tipoS != null && tipoS.equals("Segunda Chamada")) {
			tipoSolicitacao = tipoS;
			prazoValido = util.FacadeSolicitacoes.verificarDias(data);
			

		} else if (tipoR != null && tipoR.equals("Recorrecao")) {
			tipoSolicitacao = tipoR;
			lt = FacadeSolicitacoes.converterStringToLocalTime(request.getParameter("inputHoraProva"));
			dataEntrega = util.Facade.converterStringParaLocalDate(request.getParameter("inputEntregaDataProva"));
			prazoValido = util.FacadeSolicitacoes.verificarDias(dataEntrega);

		}

		LocalDateTime ldt = LocalDateTime.of(data, lt);
		Aluno aluno = DAOFactory.criarAlunoDAO().buscarPorMatricula(matricula);
		Professor professor = DAOFactory.criarProfessorDAO().buscar(idProfessor);
		Disciplina disciplina = DAOFactoryM2C.criarDisciplinaDAO().getById(Integer.valueOf(idDisciplina));

		if (prazoValido) {
			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setAluno(aluno);
			solicitacao.setDataEHoraProva(FacadeSolicitacoes.converterLocalDateTimeToString(ldt));
			solicitacao.setDataDivulgacaoResultadoProva(dataEntrega);
			solicitacao.setDisciplina(disciplina);
			solicitacao.setDataSolicitacao(LocalDate.now());
			solicitacao.setJustificativa(justificativa);
			solicitacao.setProfessor(professor);
			solicitacao.setTipoSolicitacao(tipoSolicitacao);
			util.FacadeSolicitacoes.salvarSolicitacao(solicitacao);

			String nomeArquivo = solicitacao.getId() + "_"
					+ solicitacao.getTipoSolicitacaoToString() + "_"
					+ solicitacao.getDataEHoraProva().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss")) + ".pdf";
			String localArquivo = Constantes.getTEMP_PDF_SOLICITACAO() + nomeArquivo; 
			FacadeSolicitacoes.gerarPDFDaSolicitacao(solicitacao, localArquivo);
			FacadeSolicitacoes.enviarEmailSolicitacao(solicitacao, localArquivo, nomeArquivo);
			File file = new File(localArquivo);
			file.delete();
			pagina = "homeSolicitacao.jsp?sucessoSalvar=1";

			session.setAttribute(Constantes.getSESSION_MSG_SUCCESS(), "Solicitação enviada com sucesso!");	
			
		} else {
			session.setAttribute(Constantes.getSessionMsg(), "Prazo de solicitação inválido !<br> A solicitação deve ser realizada até 3 dias após a realização da prova");
			pagina = "homeSolicitacao.jsp?erroPrazo=1";
		}
		}catch (Exception e) {
			session.setAttribute(Constantes.getSessionMsg(), "Erro ao pedir solicitação, "+e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect(pagina);

	}

}
