package br.com.livro.tdd.capitulo04;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculadoraDeSalarioTest {
	
	@Test
	public void	deveCalcularSalarioParaDesenvolvedoresComSalarioAbaixoDoLimite() {
		
		Funcionario desenvolvedor = new Funcionario("Mauricio", 1500.0, Cargo.DESENVOLVEDOR);
		
		assertEquals(1500.0 * 0.9, desenvolvedor.getCargo().getRegraDeCalculo().calcula(desenvolvedor), 0.00001);
	}
	
	@Test
	public void deveCalcularSalarioParaDesenvolvedoresComSalarioAcimaDoLimite() {
		
		Funcionario desenvolvedor = new Funcionario("Mauricio", 4000.0, Cargo.DESENVOLVEDOR);
		
		assertEquals(4000.0 * 0.8, desenvolvedor.getCargo().getRegraDeCalculo().calcula(desenvolvedor), 0.00001);
	}
	
	@Test
	public void deveCalcularSalarioParaDBAsComSalarioAbaixoDoLimite() {
		
		Funcionario dba = new Funcionario("Mauricio", 500.0, Cargo.DBA);
		
		assertEquals(500.0 * 0.85, dba.getCargo().getRegraDeCalculo().calcula(dba), 0.00001);
	}	

}
