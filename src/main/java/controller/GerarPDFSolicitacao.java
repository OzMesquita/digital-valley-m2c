package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactoryM2C;
import model.Solicitacao;
import model.Usuario;
import util.Constantes;
import util.Facade;
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
		if (request.getParameter("id") != null) {			
			try {				
				//pegando dados
				Integer solicitacaoId = Integer.parseInt(request.getParameter("id"));
				Solicitacao solicitacao = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorId(solicitacaoId);
				//gravando arquivo
				String localArquivo = Constantes.getTEMP_PDF_SOLICITACAO() + "Usuario_" + ((Usuario) request.getSession().getAttribute("usuario")).getPessoa().getId() + "_Solicitacao_" + solicitacaoId + "_Tempo_" +  LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss")) +".pdf";
				FacadeSolicitacoes.gerarPDFDaSolicitacao(solicitacao, localArquivo);				
				String mime = request.getServletContext().getMimeType(localArquivo);
				if (mime == null) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				} else {
					File file = new File(localArquivo);
					FileInputStream in = new FileInputStream(file);
					// copiar conteudo
					OutputStream out = response.getOutputStream();
					byte[] buf = new byte[1024];
					for (int count = 0; (count = in.read(buf)) >= 0;) {
						out.write(buf, 0, count);
					}
					out.close();
					in.close();
					//retornando arquivo
					response.setContentType(mime);
					response.setContentLength((int) file.length());
					//excluindo arquivo
					File relatorioPDF = new File(localArquivo);
		    		file.delete();		    				    		
				}
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);			
			}
		}else {
			response.setStatus(500);
			response.getWriter().write("Erro: O parâmetro ID da solicitação não foi informado.");			
		}
	}

}
