package br.com.michael.loja.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import javax.servlet.Filter;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.michael.loja.conf.AppWebConfiguration;
import br.com.michael.loja.conf.JPAConfiguration;
import br.com.michael.loja.conf.SecurityConfiguration;
import br.com.michael.loja.confg.DataSourceConfigurationTest;
import br.com.michael.loja.models.Produto;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
		AppWebConfiguration.class,
		JPAConfiguration.class, 
		SecurityConfiguration.class,
		DataSourceConfigurationTest.class 
		})
@ActiveProfiles("dev")
public class ProdutoControllerTest {
	
	
	@Autowired
	private WebApplicationContext web;
	
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	private MockMvc mvc;
	
	
	@Before
	public void setup(){
		this.mvc = MockMvcBuilders.webAppContextSetup(this.web)
				.addFilter(springSecurityFilterChain).build();
	}
	
	
	
	@Test
	@Transactional
	public void mostrarLivrosNaHome() throws Exception{
		
		ResultMatcher resultMatcher = new ResultMatcher(){

			@SuppressWarnings("unchecked")
			@Override
			public void match(MvcResult result) throws Exception { //posso implementar a minha propria
				ModelAndView modelAndView = result.getModelAndView();
				List<Produto> prods = (List<Produto>) modelAndView.getModel().get("produtos");
				Assert.assertEquals(5, prods.size());
			}
		};
		
		
		//verifica o retorno forwad para jsp
		//verifica se tem um objeto com a key produtos enviado para jsp
		
		this.mvc.perform(get("/produto/listagem"))
		.andExpect(resultMatcher)
		.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
		.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/produto/list.jsp"));
	}


	
	@Test
	public void testeAcessoNegadoCompradorCadastroProdutos() throws Exception{
		
		//TODO - verificar para validar a autenticacao!
		RequestPostProcessor postProcessor = SecurityMockMvcRequestPostProcessors
														.user("comprador@gmail.com")
														.password("102030")
														.roles("COMPRADOR");
		
		this.mvc.perform(get("/produto/cadastro")
						.with(postProcessor))
						.andExpect(MockMvcResultMatchers.status().is(403));
	}



}
