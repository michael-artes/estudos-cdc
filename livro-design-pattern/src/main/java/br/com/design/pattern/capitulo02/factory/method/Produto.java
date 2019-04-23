package br.com.design.pattern.capitulo02.factory.method;

public class Produto {

	private int id;
	
	public Produto(int id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return "Produto ID: " + id;
	}


	public double getPreco() {
		return 150.00;
	}
}
