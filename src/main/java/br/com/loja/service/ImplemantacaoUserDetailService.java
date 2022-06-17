package br.com.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.loja.model.Usuario;
import br.com.loja.repository.UsuarioRepository;

public class ImplemantacaoUserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findUserByLogin(username);
		
		if(usuario == null) 
			{
				throw new UsernameNotFoundException("USUÁRIO NÃO FOI ENCONTRADO");
			}
			
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
	}

}
