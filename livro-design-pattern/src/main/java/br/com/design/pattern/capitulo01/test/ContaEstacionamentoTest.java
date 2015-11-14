package br.com.design.pattern.capitulo01.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.design.pattern.capitulo01.ContaEstacionamento;
import br.com.design.pattern.capitulo01.Passeio;
import br.com.design.pattern.capitulo01.Veiculo;
import br.com.design.pattern.capitulo01.solucao.CalculoDiaria;
import br.com.design.pattern.capitulo01.solucao.ContaEstacionamentoComStrategy;

public class ContaEstacionamentoTest {
	
	
	@Test
	public void testarCalculoDeVeiculoPasseioSemOPadrao(){
		
		ContaEstacionamento estacionamento = new ContaEstacionamento(10);
		Veiculo v = new Passeio(2, 0, 0);
		
		estacionamento.setVeiculo(v);

		System.out.println(estacionamento.valorConta());
		Assert.assertTrue(true);
	}
	
	@Test
	public void testarCalculoDeVeiculoPasseioComOpadrao(){
		
		Veiculo v = new Passeio(2, 0, 0);
		v.setValorDiaria(23.5);
		
		ContaEstacionamentoComStrategy estacionamento = new ContaEstacionamentoComStrategy(new CalculoDiaria()).setVeiculo(v);
		
		estacionamento.setVeiculo(v);
		
		System.out.println(estacionamento.calcularValor());
		Assert.assertTrue(true);
	}

}
