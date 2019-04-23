package br.com.design.pattern.capitulo01.solucao;

import br.com.design.pattern.capitulo01.Veiculo;

public class ContaEstacionamentoComStrategy {
	
	private CalculoValor calculoValor;
	private Veiculo veiculo;

	
	public ContaEstacionamentoComStrategy(CalculoValor calculoValor) {
		this.calculoValor = calculoValor;
	}
	
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	
	public ContaEstacionamentoComStrategy setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
		return this;
	}


	public double calcularValor(){
		return calculoValor.calcularValor(15 - 2, veiculo);
	}

	
}
