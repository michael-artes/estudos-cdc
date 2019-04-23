package br.com.livro.tdd.capitulo05;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MaiorPrecoTest {
	
	
	private CarrinhoDeComprasBuilder carrinhoBuilder;
	
	@Before
	public void inicializa() {
		this.carrinhoBuilder = new CarrinhoDeComprasBuilder();
	}
	
	@Test
	public void deveRetornarZeroSeCarrinhoVazio() {
		
		assertEquals(0.0, carrinhoBuilder.cria().maiorValor(), 0.0001);
	}
	
	@Test
	public void deveRetornarValorDoItemSeCarrinhoCom1Elemento() {
		
		carrinhoBuilder.setNomes("Geladeira");
		carrinhoBuilder.setQuantidade(1);
		carrinhoBuilder.setValores(900.00);
		
		assertEquals(900.0, carrinhoBuilder.montaItens().cria().maiorValor(), 0.0001);
	}
	
	@Test
	public void deveRetornarMaiorValorSeCarrinhoContemMuitosElementos() {
		
		carrinhoBuilder.setNomes("Geladeira", "Fogão", "Máquina de Lavar");
		carrinhoBuilder.setQuantidade(1, 2, 1);
		carrinhoBuilder.setValores(900.0, 1500.0, 750.0);
		
		assertEquals(3000.0, carrinhoBuilder.montaItens().cria().maiorValor(), 0.0001);
	}

}
