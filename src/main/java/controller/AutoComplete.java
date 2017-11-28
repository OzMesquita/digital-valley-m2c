package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

import dao.DAOFactory;
import model.Professor;

public class AutoComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Professor> professores = (ArrayList<Professor>)DAOFactory.criarProfessorDAO().listar();  

		String result = "{\"query\": \"Unit\", \"suggestions\": [";
		for(int i=0;i<professores.size()-1;i++){
			result+="{\"value\": \""+professores.get(i).getNome()+"\", \"data\": \""+professores.get(i).getImagem()+"\"},";
		}
		result+="{\"value\": \""+professores.get(professores.size()-1).getNome()+"\", \"data\": \""+professores.get(professores.size()-1).getEmail()+"\"}";
		result+= "]}";
		
		System.out.println(result);
		
		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
