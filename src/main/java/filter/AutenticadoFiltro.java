package filter;


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
import dao.UsuarioDAO;
import model.Pessoa;
import dao.DAOFactory;
import util.Facade;

public class AutenticadoFiltro implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getServletPath();
		if (path.endsWith("/autentica")){
			chain.doFilter(request, response);
		}else{
			HttpSession session = ((HttpServletRequest) request).getSession();
			if(request.getParameter("token") != null && request.getParameter("id") != null){
				String token = request.getParameter("token");
				int id = Integer.parseInt(request.getParameter("id"));
				Pessoa user = Facade.buscarPessoaPorId(id);
				if (token.equals(user.getUsuario().getTokenUsuario()) && id == user.getId() && !token.equals("null")) {
					UsuarioDAO userDAO = DAOFactory.criarUsuarioDAO();
					session.setAttribute("usuario", user.getUsuario());
					chain.doFilter(request, response);
				}else {
					((HttpServletResponse) response).sendRedirect("/Controle_de_Acesso/");
				}
			}else if(session.getAttribute("usuario")!= null && DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Pessoa)session.getAttribute("usuario")).getId())!=null && ((Pessoa)session.getAttribute("usuario")).getUsuario().getTokenUsuario().equals(DAOFactory.criarUsuarioDAO().buscarTokenTemp(((Pessoa)session.getAttribute("usuario")).getId()))){
				chain.doFilter(request, response);
			}else {
				((HttpServletResponse) response).sendRedirect("/Controle_de_Acesso/");
			}
		}				
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
