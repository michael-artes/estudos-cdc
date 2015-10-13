package br.com.livro.tdd.capitulo05;


public class MaiorPreco {

	public double encontra(CarrinhoDeCompras carrinho) {
		
		if(carrinho.getItens().size() == 0) return 0;
		
		double maior = carrinho.getItens().get(0).getValorTotal();
		
		for(Item item : carrinho.getItens()) {
			
			double valorTotal = item.getValorTotal();
			
			if(maior < valorTotal) {
				maior = valorTotal;
			}
		}
		
		return maior;
	}
	

}
