package br.com.livro.tdd.capitulo09;

public class Pagamento {
	
	private double valor;
	private MeioDePagamento boleto;

	public Pagamento(double valor, MeioDePagamento boleto) {
		this.valor = valor;
		this.boleto = boleto;
	}

	public double getValor() {
		return valor;
	}

}
