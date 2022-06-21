package br.com.loja.security;

import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.loja.service.ImplemantacaoUserDetailService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true , securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener{

	@Autowired
	private ImplemantacaoUserDetailService implementacaoUserDatailService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
					.disable().authorizeRequests().antMatchers("/").permitAll()
					.antMatchers("/index").permitAll()
					.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
					
					/*No logout redireciona para o index*/
					.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
					
					/*mapeia o logout do sistema*/
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					
					/*Filtra as requisiçoes para o login de JWT*/
					
					.and().addFilterAfter(new JWTLoginFilter("/login", authenticationManager()),
							UsernamePasswordAuthenticationFilter.class)
					
					.addFilterBefore(new JWTApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
					
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(implementacaoUserDatailService).passwordEncoder(new BCryptPasswordEncoder());
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET,"/salvarAcesso","/deletarAcesso")
		.antMatchers(HttpMethod.POST,"/salvarAcesso","/deletarAcesso");
		/*Iginorando URL para poder realizar testes*/
		
	}
}
