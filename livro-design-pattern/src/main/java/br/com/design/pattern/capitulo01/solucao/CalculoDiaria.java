package br.com.design.pattern.capitulo01.solucao;

import br.com.design.pattern.capitulo01.Veiculo;

public class CalculoDiaria implements CalculoValor {
	
	@Override
	public double calcularValor(int l, Veiculo veiculo) {
		return veiculo.getValorDiaria() * Math.ceil(l / veiculo.getHora());
	}

}
