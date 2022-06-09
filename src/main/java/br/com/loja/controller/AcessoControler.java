package br.com.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.loja.model.Acesso;
import br.com.loja.service.AcessoService;

@Controller
public class AcessoControler {
	
	@Autowired
	private AcessoService acessoService;
	@PostMapping("/salvarAcesso")	
	public Acesso save(Acesso acesso) 
		{
			return acessoService.save(acesso);
		}
	
}
