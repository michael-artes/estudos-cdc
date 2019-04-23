package com.login.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.login.app.model.Produto;

public class VitrineController {
	
	private static List<Produto> produtos = new ArrayList<Produto>();
	
	private String[] sProdutos = {"Livro: Java8","Livro: JavaFX","Livro: Redes","Livro: Linux"};
	private String[] sPathImg = {"file:resources/img-1.png", "file:resources/img-2.png", 
			"file:resources/img-3.png", "file:resources/img-4.png"};

	public static List<Produto> getProdutos() {
		return produtos;
	}
	
	
	public void addProdutos(List<Produto> listProdutos){
		listProdutos.forEach(produtos::add);
	}
	
	
	
	public void mockProdutos(int qtdProdutos){
		
		for (int i = 0; i <= qtdProdutos; i++) {
			double preco = 50.34 + (i * 1.0);
			produtos.add(new Produto(sProdutos[i], preco,sPathImg[i]));
		}
	}

}
