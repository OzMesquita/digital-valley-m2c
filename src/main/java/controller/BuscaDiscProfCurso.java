package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DAOFactory;
import model.Curso;
import model.Disciplina;
import model.Professor;
import util.FacadeSolicitacoes;

public class BuscaDiscProfCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet1");
		String idProfessor = request.getParameter("idProfessor");
		String idCurso = request.getParameter("idCurso");
		response.setCharacterEncoding("UTF-8");
		if (idProfessor != null && !idProfessor.equals("")) {
			System.out.println("Servlet2");
			if (idCurso != null && !idCurso.equals("")) {
				System.out.println("Servlet3");
				int idProfes = Integer.valueOf(idProfessor);
				int idC = Integer.valueOf(idCurso);
				try {
					Professor professor = DAOFactory.criarProfessorDAO().buscar(idProfes);
					Curso curso = DAOFactory.criarCursoDAO().buscar(idC);
					
					if (professor != null  && curso != null ) {
				
						System.out.println("Servlet4");
						List<Disciplina> disciplinas = FacadeSolicitacoes.buscarDiscPorCursoEProfDif(idC, idProfes);
						
						professor.getUsuario().setPessoa(null);						
						Gson gson = new Gson();
						String json = gson.toJson(disciplinas);
						response.setContentType("application/json");
						response.getWriter().write(json);
					} else {
						System.out.println("Servlet5");
						response.setStatus(500);
						response.getWriter().write("Professor ou curso não encontrado");
					}
				} catch (Exception e) {
					response.setStatus(500);
					response.getWriter().write(e.getMessage());
				}
			} else {
				response.setStatus(500);
				response.getWriter().write("Código do Curso não encontrado");
			}
		} else {
			response.setStatus(500);
			response.getWriter().write("Código do Professor não encontrado");
		}
	}

}
