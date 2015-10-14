package br.com.livro.tdd.capitulo05;

public class CarrinhoDeComprasBuilder {
	
	public CarrinhoDeCompras carrinho;
	
	private double[] valores;
	private String[] nomes;
	private int[] quantidade;
 	
	public CarrinhoDeComprasBuilder() {
		this.carrinho = new CarrinhoDeCompras();
	}
	
	public CarrinhoDeComprasBuilder montaItens() {
		
		for (int i = 0; i < quantidade.length; i++) {
			carrinho.adiciona(new Item(nomes[i], quantidade[i], valores[i]));
		}
		
		return this;
	}
	
	public CarrinhoDeCompras cria() {
		return carrinho;
	}

	public void setValores(double... valores) {
		this.valores = valores;
	}

	public void setNomes(String... nomes) {
		this.nomes = nomes;
	}

	public void setQuantidade(int... quantidade) {
		this.quantidade = quantidade;
	}
	
}
