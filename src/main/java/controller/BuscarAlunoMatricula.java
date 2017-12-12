package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DAOFactory;
import model.Aluno;

public class BuscarAlunoMatricula extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		Aluno aluno = DAOFactory.criarAlunoDAO().buscarPorMatricula(request.getParameter("matricula"));
		aluno.setUsuario(null);
		String result = gson.toJson(aluno);
		System.out.println(result);
		response.setContentType("application/json");
		response.getWriter().print(result);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
