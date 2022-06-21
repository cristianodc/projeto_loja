package br.com.loja.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//import br.com.loja.ApplicationContext;
import br.com.loja.ApplicationContextLoad;
import br.com.loja.model.Usuario;
import br.com.loja.repository.UsuarioRepository;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/*Responsavel por Criar  a autenticaçao e retornar tambem a autenticaçao JWT	*/
@Service
@Component
public class JWTTokenAutenticacaoService {

	/*Validade do tokem*/
	private static final long EXPIRATION_TIME= 959990000;
	
	/*Chave de sena para juntar com  o tokem*/
	private static final String SECRET= "clkasf6354";
	
	/*Prefixo do token*/
	private static final String TOKEN_PREFIX= "Bearer";
	
	/*Um cabeçalho*/
	private static final String HEADER_STRING= "Authorization";
	
	public void addAuthentication(HttpServletResponse response, String username) throws Exception
		{
			/*Montagin do TOKEN*/
			String JWT = Jwts.builder()
					     .setSubject(username)/*Add user*/
					     .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))/*Add tempo de expiraçao*/
					     .signWith(SignatureAlgorithm.HS512, SECRET).compact();
			
			String token = TOKEN_PREFIX + " " + JWT;
			
			/*Retorna o cabeçalho */
			response.addHeader(HEADER_STRING, token);
			
			liberacaoCors(response);
			
			/*Usado para ver o Postmam para o teste*/
			response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
			
		}
	
	/*Retorna ao usuario validade com o tokem ou caso não seja valido retorna null*/
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) 
		{
			String token = request.getHeader(HEADER_STRING);
			if(token != null)
				{
					String tokenlimpo = token.replace(TOKEN_PREFIX, "").trim();
					
					/*Faz a validacao do tokem do usuario na requisiçao e obtem o user*/
					String user = Jwts.parser()
								.setSigningKey(SECRET)
								.parseClaimsJws(tokenlimpo)
								.getBody().getSubject();
					
					/*
					 * Se o ususario existir devemos carrega - lo do banco de dados*/
					if(user != null)
						{
							Usuario usuario = ApplicationContextLoad.getAplicationContext().getBean(UsuarioRepository.class).findUserByLogin(user);
							
							if(usuario != null)
								{
									return new UsernamePasswordAuthenticationToken(usuario.getUsername()
																					, usuario.getPassword()
																					, usuario.getAuthorities());
								}
						}
					
				}
			liberacaoCors(response);
			return null;
			
		
		}
	/*Liberacão de Cors conta erro no navegador*/
	private void liberacaoCors(HttpServletResponse response) 
		{
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
		
	}
}
