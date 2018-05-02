package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.DAOFactoryM2C;
import model.Professor;
import util.Constantes;

public class AssociarDisciplinaProfessor extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idProfessor = Integer.valueOf(request.getParameter("idProfessor"));
		String[] disciplinasSelecionadas = request.getParameterValues("disciplina");
		String pagina = "associar-disc-prof.jsp?erroAssociar=1";
		HttpSession session = request.getSession();
		try {
		Professor professor = DAOFactory.criarProfessorDAO().buscar(idProfessor);
		if (professor != null) {
			pagina = "associar-disc-prof.jsp";
			if (disciplinasSelecionadas.length > 0) {
				for (String disc : disciplinasSelecionadas) {
					DAOFactoryM2C.criarDisciplinaDAO().associarDiscProf(Integer.valueOf(disc), idProfessor);
				}
				pagina = "associar-disc-prof.jsp?sucessoAssociar=1";
			}else {
				throw new IllegalArgumentException("Nenhuma disciplina selecionada");
			}
		}else {
			throw new IllegalArgumentException("Professor não encontrado");
		}
		
		

		} catch (Exception e) {
			session.setAttribute(Constantes.getSessionMsg(), e.getMessage());
			e.printStackTrace();
		}
		response.sendRedirect(pagina);
		
	}

}
