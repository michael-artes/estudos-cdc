package br.com.livro.tdd.capitulo03;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConversorDeNumeroRomanoTest {
	
	@Test
	public void deveEntenderOSimboloI() {
		ConversorDeNumeroRomano romano = new ConversorDeNumeroRomano();
		int numero = romano.converte("I");
		assertEquals(1, numero);
	}

}
