package br.com.loja_v_ment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.loja_v_ment.controller.AcessoController;
import br.com.loja_v_ment.model.Acesso;
import br.com.loja_v_ment.repository.AcessoRepository;
import br.com.loja_v_ment.service.AcessoService;

@SpringBootTest(classes = LojaVirtualMentApplication.class)
class LojaVirtualMentApplicationTests {
	
	
	@Autowired
	private AcessoController acessoController;
	@Test
	void contextLoads() {
	}

	@Test
	public void testCadastroAcesso() {
		
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN");
		
		acessoController.salvarAcesso(acesso);
	}
	
}
