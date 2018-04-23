package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EnumCargo;
import model.EnumNivel;
import model.Servidor;
import model.Usuario;

public class FiltroAssociarDisc implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = (HttpSession) (((HttpServletRequest)request).getSession());
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		boolean isSecretario = false;
		if(usuario.getPessoa() instanceof Servidor) {
			Servidor servidor = (Servidor) usuario.getPessoa();
			if(servidor.getCargo().equals(EnumCargo.SECRETARIO)) {
				isSecretario = true;
			}
		}
		
		if (usuario.getNivel().equals(EnumNivel.ADMINISTRADOR) || isSecretario) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(util.Constantes.getAppUrl()+"/view/homeSolicitacao.jsp?erroPermissao=1");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
