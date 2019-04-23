package br.com.design.pattern.capitulo02;

public class CarrinhoNull extends Carrinho {
	
	@Override
	public String getTamanho() {
		return "Indefinido";
	}
	
	@Override
	public double getValor() {
		return 1.35;
	}

}
