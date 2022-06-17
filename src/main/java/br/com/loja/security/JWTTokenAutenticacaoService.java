package br.com.loja.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
			
			/*Usado para ver o Postmam para o teste*/
			response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
			
		}
}
