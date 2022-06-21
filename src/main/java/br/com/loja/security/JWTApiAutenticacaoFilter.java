package br.com.loja.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/*Filtro ond todas as requisiçoes serao capturadas para autenticar*/
public class JWTApiAutenticacaoFilter extends GenericFilter {

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/*Estabelece a autencicaçao do user*/
		
	    AuthenticateAction authenticateAction = (AuthenticateAction) new JWTTokenAutenticacaoService()
	    		  .getAuthentication((HttpServletRequest)request, (HttpServletResponse)response);
	    
	    SecurityContextHolder.getContext().setAuthentication((Authentication) authenticateAction);
	    
	    chain.doFilter(request, response);
	}

}
