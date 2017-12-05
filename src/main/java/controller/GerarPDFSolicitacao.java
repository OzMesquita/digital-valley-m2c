package controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactoryM2C;
import model.Solicitacao;
import model.Usuario;
import util.Constantes;
import util.FacadeSolicitacoes;

/**
 * Servlet implementation class GerarPDFSolicitacao
 */
public class GerarPDFSolicitacao extends HttpServlet {
	private static final long serialVersionUID = 1L;    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer solicitacaoId = (Integer) request.getAttribute("id");
		if (solicitacaoId != null) {
			Solicitacao solicitacao = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorId(solicitacaoId);
			String localArquivo = Constantes.getTEMP_PDF_SOLICITACAO() + "Usuario_" + ((Usuario) request.getSession().getAttribute("usuario")).getPessoa().getId() + "_Solicitacao_" + solicitacaoId + "_Tempo_" + LocalDateTime.now() +".pdf";
			FacadeSolicitacoes.gerarPDFDaSolicitacao(solicitacao, localArquivo);
		}else {
			response.setStatus(500);
			response.getWriter().write("Erro: O parâmetro ID da solicitação não foi informado.");
		}
	}

}
