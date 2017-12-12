package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DAOFactoryM2C;
import model.Solicitacao;

public class DetalhesSolicitacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("id");
		response.setCharacterEncoding("UTF-8");
		if (codigo != null && !codigo.equals("")) {
			int id = Integer.valueOf(codigo);
			try {
				Solicitacao solicitacao = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorId(id);
				if (solicitacao != null) {
					solicitacao.getAluno().setUsuario(null);
					solicitacao.getProfessor().setUsuario(null);
					Gson gson = new Gson();
					String json = gson.toJson(solicitacao);
					response.setContentType("application/json");					
					response.getWriter().write(json);
				}else {
					response.setStatus(500);
					response.getWriter().write("Solicitação não encontrada");
				}
			} catch (Exception e) {
				response.setStatus(500);
				response.getWriter().write(e.getMessage());
			}
		}else {
			response.setStatus(500);
			response.getWriter().write("Campo código não encontrado");
		}
	}

}
