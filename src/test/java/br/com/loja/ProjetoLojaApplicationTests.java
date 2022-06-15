package br.com.loja;

import static org.mockito.Mockito.mock;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.ContentTypeOptionsConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	/*Injeçao de dependencia  esse objeto que pega as informaçoes do projeto*/
	@Autowired
	private WebApplicationContext wac;
	
	/*Teste end-point salvar
	@Test
	public void testeRestApiCadastroAcesso() throws JsonProcessingException, Exception 
		{
			/*Objetos responsaveis por trazer as informaçoes e fazerem os testes requisiçoes e trazer os resultados  
			DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
			MockMvc mockMvc = builder.build();
			
			Acesso acesso = new Acesso();
			
			acesso.setDescricao("ROLE_VENDEDOR");
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			ResultActions retornoApi = mockMvc
										.perform(MockMvcRequestBuilders.post("/salvarAcesso")
									    .content(objectMapper.writeValueAsString(acesso))
									    .accept(org.springframework.http.MediaType.APPLICATION_JSON)
									    .contentType(org.springframework.http.MediaType.APPLICATION_JSON));
			
			System.out.println("RETORNO DA API" + retornoApi.andReturn().getResponse().getContentAsString());
			
			/*converter o retorno JSON em objeto
			
			Acesso objRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(),
											 Acesso.class);
			
			assertEquals(acesso.getDescricao(), objRetorno.getDescricao());
		}
	*/
	@Test
	public void testeApiRestDeletarAcesso() throws JsonProcessingException, Exception 
		{
		/*Objetos responsaveis por trazer as informaçoes e fazerem os testes requisiçoes e trazer os resultados  */
			DefaultMockMvcBuilder builder= MockMvcBuilders.webAppContextSetup(wac);
			MockMvc mockMvc=  builder.build();
			
			Acesso acesso = new Acesso();
			acesso.setDescricao("ROLE_DELETE");
			
			acessoRepository.save(acesso);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/deletarAcesso")
										 .content(objectMapper.writeValueAsString(acesso))
										 .accept(org.springframework.http.MediaType.APPLICATION_JSON)
									     .contentType(org.springframework.http.MediaType.APPLICATION_JSON));
			
			System.out.println("Retorno API" + retornoApi.andReturn().getResponse().getContentAsString());
			System.out.println("Status de Retorno: " + retornoApi.andReturn().getResponse().getStatus());
			
			assertEquals("Acesso Removido", retornoApi.andReturn().getResponse().getContentAsString());
			assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		}
	
	@Test
	public void testeApiRestDeletarAcessoPorId() throws JsonProcessingException, Exception 
		{
		/*Objetos responsaveis por trazer as informaçoes e fazerem os testes requisiçoes e trazer os resultados  */
			DefaultMockMvcBuilder builder= MockMvcBuilders.webAppContextSetup(wac);
			MockMvc mockMvc=  builder.build();
			
			Acesso acesso = new Acesso();
			acesso.setDescricao("ROLE_DELETE");
			
			acessoRepository.save(acesso);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/deletarAcessoPorId/"+acesso.getId())
										 .content(objectMapper.writeValueAsString(acesso))
										 .accept(org.springframework.http.MediaType.APPLICATION_JSON)
									     .contentType(org.springframework.http.MediaType.APPLICATION_JSON));
			
			System.out.println("Retorno API" + retornoApi.andReturn().getResponse().getContentAsString());
			System.out.println("Status de Retorno: " + retornoApi.andReturn().getResponse().getStatus());
			
			assertEquals("Acesso Removido", retornoApi.andReturn().getResponse().getContentAsString());
			assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		}
	@Test
	public void testeApiRestOberAssecoPorId() throws JsonProcessingException, Exception 
		{
		/*Objetos responsaveis por trazer as informaçoes e fazerem os testes requisiçoes e trazer os resultados  */
			DefaultMockMvcBuilder builder= MockMvcBuilders.webAppContextSetup(wac);
			MockMvc mockMvc=  builder.build();
			
			Acesso acesso = new Acesso();
			acesso.setDescricao("ROLE_OBTER_ACESSO_ID");			
			acessoRepository.save(acesso);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/obterAcesso/"+acesso.getId())
										 .content(objectMapper.writeValueAsString(acesso))
										 .accept(org.springframework.http.MediaType.APPLICATION_JSON)
									     .contentType(org.springframework.http.MediaType.APPLICATION_JSON));
			
			Acesso acessoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
			assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());
		}
	
	@Test
	public void testRestApiBuscarDesc() throws JsonProcessingException, Exception 
		{
			DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
			MockMvc mocMvc= builder.build();
			
			Acesso acesso = new Acesso();
			
			acesso.setDescricao("XXX");
			
			acessoRepository.save(acesso);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			ResultActions retornoApi  = mocMvc
										.perform(MockMvcRequestBuilders.get("/obterAcessoPorDesc/XXX")
										 .content(objectMapper.writeValueAsString(acesso))
										 .accept(org.springframework.http.MediaType.APPLICATION_JSON)
										  .contentType(org.springframework.http.MediaType.APPLICATION_JSON));
			
			assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
			
			List<Acesso> retornoApiList = objectMapper.readValue(retornoApi.andReturn()
													   .getResponse()
													   	.getContentAsString(),
													     new TypeReference<List<Acesso>>() {});
			
			assertEquals(1, retornoApiList.size());
			assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());
			
			acessoRepository.deleteById(acesso.getId());
			
		}
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
