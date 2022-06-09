package br.com.loja;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.loja.controller.AcessoControler;
import br.com.loja.model.Acesso;
import br.com.loja.repository.AcessoRepository;
import br.com.loja.service.AcessoService;

@SpringBootTest(classes =ProjetoLojaApplication.class )
class ProjetoLojaApplicationTests {

	@Autowired
	private AcessoControler acessoControler;
	@Test
	public void testCadastroAcesso() {
		
		Acesso acesso   = new Acesso();
		acesso.setDescricao("ROLE_USO");
		acessoControler.save(acesso);
	}

}
