package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import model.Aluno;
import model.Professor;
import util.Constantes;

public class SalvarSolicitacaoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("inputName");
		String matricula = request.getParameter("inputMatricula");
		String nomeProfessor = request.getParameter("inputProfessor");
		String nomeDisciplina = request.getParameter("inputDisciplina");
		String dataPova = request.getParameter("inputDataProva");
		String justificativa = request.getParameter("justificativa");
		HttpSession session = request.getSession();
		try {
			Aluno aluno = DAOFactory.criarAlunoDAO().buscarPorMatricula(matricula);
			if(aluno != null){
				Professor professor = new Professor();// CHAMAR O PROFESSOR_DAO QUANDO ESTIVER PRONTO
				professor.setNome(nomeProfessor);
				if(professor != null){
					
					
				}else{
					session.setAttribute(Constantes.getSessionMsg(), "Professor Inválido");
				}
			}else{
				session.setAttribute(Constantes.getSessionMsg(), "Aluno inválido");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
				
	}

}
