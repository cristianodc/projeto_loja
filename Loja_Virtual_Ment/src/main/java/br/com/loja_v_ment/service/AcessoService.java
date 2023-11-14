package br.com.loja_v_ment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja_v_ment.model.Acesso;
import br.com.loja_v_ment.repository.AcessoRepository;

@Service
public class AcessoService {
	@Autowired
	private AcessoRepository acessoRepository;
	
	public Acesso save(Acesso acesso) 
		{
			return acessoRepository.save(acesso);
		}
}
