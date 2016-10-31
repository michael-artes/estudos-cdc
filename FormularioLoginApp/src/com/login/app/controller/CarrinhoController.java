package com.login.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.login.app.model.Produto;

public class CarrinhoController {

	private static List<Produto> produtos = new ArrayList<Produto>();

	public static List<Produto> getProdutos() {
		return produtos;
	}
	
	
	public void addProdutos(List<Produto> listProdutos){
		listProdutos.forEach(produtos::add);
	}
	
	
}
