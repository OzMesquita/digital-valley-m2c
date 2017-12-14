package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import model.Aluno;
import model.EnumCargo;
import model.EnumNivel;
import model.Servidor;
import model.Solicitacao;
import model.Usuario;
import util.Constantes;
import util.FacadeSolicitacoes;

public class ListarSolicitacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		HttpSession session = request.getSession();
		try {
			int fim = 10;
			int inicio = 0;
			if (usuario.getPessoa() instanceof Servidor) {
				if (((Servidor) usuario.getPessoa()).getCargo().equals(EnumCargo.SECRETARIO)
						|| usuario.getNivel().equals(EnumNivel.ADMINISTRADOR)) {
					if (request.getParameter("tipoBusca") != null
							&& request.getParameter("tipoBusca").equals("listarPorAluno")) {
						solicitacoes = FacadeSolicitacoes.buscarPorAluno(
								DAOFactory.criarAlunoDAO().buscarPorMatricula(request.getParameter("inputMatricula")),
								inicio, fim);
					} else if (request.getParameter("tipoBusca") != null
							&& request.getParameter("tipoBusca").equals("listarPorProfessor")) {
						solicitacoes = FacadeSolicitacoes.buscarPorProfessor(
								DAOFactory.criarProfessorDAO().buscarPorSiape(request.getParameter("inputSiape")),
								inicio, fim);
					} else if (request.getParameter("pagina") != null && request.getParameter("pagina") != null) {
						solicitacoes = FacadeSolicitacoes.listar((Integer.parseInt(request.getParameter("pagina"))* Constantes.getNumberOfRowsPerPage()) - Constantes.getNumberOfRowsPerPage(),Integer.parseInt(request.getParameter("pagina"))* Constantes.getNumberOfRowsPerPage());
					} else {
						solicitacoes = FacadeSolicitacoes.listar(0, 10);
					}
				}
			} else if (usuario.getPessoa() instanceof Aluno) {
				solicitacoes = FacadeSolicitacoes.buscarPorAluno((Aluno) usuario.getPessoa(), inicio, fim);
			}
			request.setAttribute("solicitacoes", solicitacoes);
			request.getRequestDispatcher("listar_solicitacoes.jsp").forward(request, response);
		} catch (Exception e) {
			session.setAttribute(Constantes.getSessionMsg(), e.getMessage());
			request.setAttribute("solicitacoes", solicitacoes);
			request.getRequestDispatcher("listar_solicitacoes.jsp").forward(request, response);
		}
	}
}
