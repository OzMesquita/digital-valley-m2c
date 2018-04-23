package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.DAOFactoryM2C;
import dao.JDBCDisciplinaDAO;
import model.Disciplina;
import model.Professor;

public class AssociarDisciplinaProfessor extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idProfessor = Integer.valueOf(request.getParameter("idProfessor"));
		int idCurso = Integer.valueOf(request.getParameter("idCurso"));
		String[] disciplinasSelecionadas = request.getParameterValues("disciplina");
		
		Professor professor = DAOFactory.criarProfessorDAO().buscar(idProfessor);
		if(disciplinasSelecionadas.length>0) {
		Disciplina d;
		for(String disc: disciplinasSelecionadas) {
			
			DAOFactoryM2C.criarDisciplinaDAO().associarDiscProf(Integer.valueOf(disc), idProfessor);;
			
		}
		}
		
		
	}

}
