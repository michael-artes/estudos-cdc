package br.com.michael.loja.models;

import java.math.BigDecimal;
import java.util.Arrays;

public class CarrinhoItens {

	private Produto produto;
	private LivroTipo livroTipo;
	private Integer produtoId;
	
	public static CarrinhoItens zeroed(){
		Produto Produto = new Produto();
		Preco price = new Preco();
		LivroTipo combo = LivroTipo.COMBO;
		price.setLivroTipo(combo);
		price.setValor(BigDecimal.ZERO);
		Produto.setPrecos(Arrays.asList(price));
		return new CarrinhoItens(Produto, combo);
	}

	public CarrinhoItens(Produto Produto, LivroTipo LivroTipo) {
		this.produto = Produto;
		this.livroTipo = LivroTipo;
		this.produtoId = Produto.getId();
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public LivroTipo getLivroTipo() {
		return livroTipo;
	}
	
	public BigDecimal getPreco(){
		/** Navegue também até a classe Produto, para descobrir como é a implementação do método priceFor **/
		return produto.priceFor(livroTipo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((livroTipo == null) ? 0 : livroTipo.hashCode());
		result = prime * result
				+ ((produtoId == null) ? 0 : produtoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItens other = (CarrinhoItens) obj;
		if (livroTipo != other.livroTipo)
			return false;
		if (produtoId == null) {
			if (other.livroTipo != null)
				return false;
		} else if (!produtoId.equals(other.getProduto().getId()))
			return false;
		return true;
	}

	public BigDecimal getTotal(Integer quantity) {
		return getPreco().multiply(new BigDecimal(quantity));
	}

}
