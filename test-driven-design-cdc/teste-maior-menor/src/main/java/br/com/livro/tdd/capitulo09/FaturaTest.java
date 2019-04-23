package br.com.livro.tdd.capitulo09;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FaturaTest {
	
	ProcessadorDeBoletos processador;
	
	@Before
	public void initProcessador(){
		processador = new ProcessadorDeBoletos();
	}
	
	@Test
	public void deveMarcarFaturaComoPagaCasoBoletoUnicoPagueTudo(){
		
		Fatura fatura = new Fatura("Cliente", 150.0);
		
		Boleto b1 = new Boleto(150.0);
		
		List<Boleto> boletos = Arrays.asList(b1);
		
		processador.processa(boletos, fatura);
		
		assertTrue(fatura.isPago());
		
	}

}
