package br.com.michael.loja.models;

import java.math.BigDecimal;

public class Pagamento {
	
	private BigDecimal value;
	
	public Pagamento(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}
}
