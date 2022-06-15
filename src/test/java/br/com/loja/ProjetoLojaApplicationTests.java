package br.com.loja;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.loja.controller.AcessoControler;
import br.com.loja.model.Acesso;
import br.com.loja.repository.AcessoRepository;
import br.com.loja.service.AcessoService;
import junit.framework.TestCase;

@SpringBootTest(classes =ProjetoLojaApplication.class )
class ProjetoLojaApplicationTests extends TestCase {

	@Autowired
	private AcessoControler acessoControler;
	
	@Autowired
	private AcessoRepository acessoRepository;
	@Test
	public void testCadastroAcesso() {
		
		Acesso acesso   = new Acesso();
		
		acesso.setDescricao("ROLE_ADMIN");
		assertEquals(true, acesso.getId() == null);
		
		acesso = acessoControler.salvarAcesso(acesso).getBody();
		
		assertEquals("ROLE_ADMIN", acesso.getDescricao());
		
		/*Teste de carregamento*/
		
		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
		
		assertEquals(acesso2.getId(), acesso.getId());
		
		/*Teste de delete*/
		
		acessoRepository.deleteById(acesso2.getId());
		
		acessoRepository.flush();/*Roda  o SQL no banco*/
		
		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);/*Retorne null se não encontrar o objeto*/
		
		assertEquals(true, acesso3 == null);
		
		
		/*Teste de query*/
		
		Acesso acesso4 = new Acesso();
		acesso4.setDescricao("ROLE_ALUNO");
		acesso4 = acessoControler.salvarAcesso(acesso4).getBody();
		
		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim().toUpperCase());
		
		assertEquals(1, acessos.size());
		
		acessoRepository.deleteById(acesso4.getId());
		
		
	}

}
