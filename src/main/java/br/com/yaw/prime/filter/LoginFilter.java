package br.com.yaw.prime.filter;

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

import br.com.yaw.prime.model.Usuario;

public class LoginFilter implements Filter {

	private final static String FILTER_APPLIED = "_security_filter_applied";
	
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hresp = (HttpServletResponse) response;
		HttpSession session = hreq.getSession(false);
		hreq.getPathInfo();
		String paginaAtual = new String(hreq.getRequestURL());
		Usuario user = null;
		
		if (session != null){ 
			user = (Usuario) session.getAttribute("login"); 
		} 

		
		if((request.getAttribute(FILTER_APPLIED) == null) && paginaAtual != null && (paginaAtual.endsWith("login.xhtml"))){
			request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
			
			if ((user != null)) {
				//DESCOMENTAR PARA FUNCIONAR NO LOCALHOST
				//hresp.sendRedirect("/supermercado/faces/filtrarMercadorias.xhtml"); 
				hresp.sendRedirect("/faces/filtrarMercadorias.xhtml");
				return;
			}
		}else if((request.getAttribute(FILTER_APPLIED) == null) && paginaAtual != null) {
			request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
			
			if ((user == null)) {
				//DESCOMENTAR PARA FUNCIONAR NO LOCALHOST
				//hresp.sendRedirect("/supermercado/faces/login.xhtml"); 
				hresp.sendRedirect("/faces/login.xhtml");
				return;
			}
		}
		// deliver request to next filter
		chain.doFilter(request, response);
	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
