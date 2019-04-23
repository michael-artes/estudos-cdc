package br.com.design.pattern.capitulo02.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.design.pattern.capitulo02.ApresentacaoCarrinho;
import br.com.design.pattern.capitulo02.HttpServlerRequest;

public class ApresentacaoCarrinhoTest {
	
	
	@Test
	public void testarApresentacaoCarrinhoNull(){
		ApresentacaoCarrinho apresentacaoCarrinho = new ApresentacaoCarrinho();
		Double valor = apresentacaoCarrinho.colocarInformacoesCarrinho(new HttpServlerRequest(false));
		
		Assert.assertEquals(valor, 1.35, 0.1);
	}
	
	@Test
	public void testarApresentacaoCarrinhoNaoNull(){
		ApresentacaoCarrinho apresentacaoCarrinho = new ApresentacaoCarrinho();
		Double valor = apresentacaoCarrinho.colocarInformacoesCarrinho(new HttpServlerRequest(true));
		
		Assert.assertEquals(valor, 150.35, 0.1);
	}

}
