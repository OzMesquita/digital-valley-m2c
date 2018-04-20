package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DAOFactoryM2C;
import model.Disciplina;

public class AutoCompleteDisc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// pergar disciplina através de parametro idProfessor
		int param = Integer.parseInt(request.getParameter("idProfessor"));
		ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) DAOFactoryM2C.criarDisciplinaDAO().buscarPorProfessor(param);
		String result = "";
		if (disciplinas.size() > 0) {
			result = "[";
			for (int i = 0; i < disciplinas.size()-1; i++) {
				result += "{\"value\": \"" + disciplinas.get(i).getNome() + "\", \"data\": \""+ disciplinas.get(i).getId() + "\"},";
			}
			result += "{\"value\": \"" + disciplinas.get(disciplinas.size()-1).getNome() + "\", \"data\": \""+ disciplinas.get(disciplinas.size()-1).getId() + "\"}";
			result += "]";
		}
		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
