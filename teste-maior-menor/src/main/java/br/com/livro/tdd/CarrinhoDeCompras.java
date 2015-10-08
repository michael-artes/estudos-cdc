package br.com.livro.tdd;

import java.util.ArrayList;
import java.util.List;


public class CarrinhoDeCompras {
	
	private List<Produto> produtos = new ArrayList<Produto>(0);

	public List<Produto> getProdutos() {
		return produtos;
	}
	
	
	public void adiciona(Produto produto){
		getProdutos().add(produto);
	}
	
}
