package br.com.michael.loja.daos.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.michael.loja.conf.JPAConfiguration;
import br.com.michael.loja.confg.DataSourceConfigurationTest;
import br.com.michael.loja.daos.ProdutoDAO;
import br.com.michael.loja.models.LivroTipo;
import br.com.michael.loja.models.Produto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfigurationTest.class, JPAConfiguration.class, ProdutoDAO.class})
@ActiveProfiles("dev")
public class ProdutoDAOTest {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Test
	public void testeListProdutos(){

		List<Produto> list = produtoDAO.list();
		
		Assert.assertEquals(5, list.size());
		
		BigDecimal soma = produtoDAO.somaPrecosPorTipo(LivroTipo.COMBO);
		Assert.assertEquals(209.7, soma.doubleValue(), 0.0);
	}
	
	
	@Transactional
	@Test
	public void testSalvarProduto(){
		
		Produto produto = new Produto();
		
		produto.setDescricao("teste");
		produto.setLancementoData(Calendar.getInstance());
		produto.setPaginas(3215);
		produto.setTitulo("titulo livro");
		
		produtoDAO.save(produto);
		
		System.out.println(produto.getId());
		
		Assert.assertTrue(true);
		
	}
	
}
