package br.com.design.pattern.capitulo02.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.design.pattern.capitulo02.factory.method.GeradorArquivo;
import br.com.design.pattern.capitulo02.factory.method.ServicoProduto;

public class GeradorArquivoTest {
	
	@Test
	public void geradorArquivoProduto(){
		
		ServicoProduto s = new ServicoProduto(new GeradorArquivo());
		
		Assert.assertEquals(150.00, s.getPrecoProduto(100), 0.1);
		Assert.assertEquals("Produto ID: 150", s.gravarEmArquivo(150, "Produto Carro").toString());
		
	}

}
