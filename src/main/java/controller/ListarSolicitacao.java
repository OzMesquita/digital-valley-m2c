package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Dispatch;

import org.apache.http.HttpRequest;

import dao.DAOFactory;
import dao.DAOFactoryM2C;
import model.Aluno;
import model.EnumCargo;
import model.EnumNivel;
import model.Servidor;
import model.Solicitacao;
import model.Usuario;

public class ListarSolicitacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		if(usuario.getPessoa() instanceof Servidor){
			if(((Servidor) usuario.getPessoa()).getCargo().equals(EnumCargo.SECRETARIO)|| usuario.getNivel().equals(EnumNivel.ADMINISTRADOR)){
				if(request.getParameter("tipoBusca").equals("listarPorAluno")){
					solicitacoes = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorAluno(DAOFactory.criarAlunoDAO().buscarPorMatricula(request.getParameter("inputMatricula")));
				}else if(request.getParameter("tipoBusca").equals("listarPorProfessor")){
					solicitacoes = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorProfessor(DAOFactory.criarProfessorDAO().buscarPorSiape(request.getParameter("inputSiape")));
				}else{
					solicitacoes = DAOFactoryM2C.criarSolicitacaoDAO().listar(Integer.parseInt(request.getParameter("inicioPag")), Integer.parseInt(request.getParameter("inicioPag")));
				}
			}
		}else if(usuario.getPessoa() instanceof Aluno){
			solicitacoes = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorAluno((Aluno)usuario.getPessoa());
		}
		request.setAttribute("solicitacoes", solicitacoes);
		request.getRequestDispatcher("listar_solicitacoes.jsp").forward(request, response);
	}
}
