package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.DAOFactoryM2C;
import model.Solicitacao;
import util.Constantes;

public class DetalhesSolicitacaoController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("id");
		HttpSession session = request.getSession();
		int id = -1;
		if(codigo != null && !codigo.equals("")) {
			id = Integer.valueOf(codigo);
		}else {
			
		}
		
		
		try {
			
			Solicitacao solicitacao = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorId(id);
			if (solicitacao != null) {
				solicitacao.getAluno().setUsuario(null);
				solicitacao.getProfessor().setUsuario(null);
				
				Gson gson = new Gson();
				String json = gson.toJson(solicitacao);
				System.out.println(json);
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
				
			}else {
				session.setAttribute(Constantes.getSessionMsg(), "Erro ao tentar visualizar detalhes");
			}
					
			
		} catch (Exception e) {
			session.setAttribute(Constantes.getSessionMsg(), e.getMessage());
		}
		
		//DAOFactoryM2C.criarSolicitacaoDAO().
	}

}
