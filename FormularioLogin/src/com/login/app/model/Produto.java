package com.login.app.model;

public class Produto {
	
	private String produto;
	private double preco;
	private String pathImg;
	
	public Produto(String produto, double preco, String pathImg) {
		this.produto = produto;
		this.preco = preco;
		this.pathImg = pathImg;
	}


	public String getProduto() {
		return produto;
	}


	public void setProduto(String produto) {
		this.produto = produto;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	public String getPathImg() {
		return pathImg;
	}


	public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}
	
	
}
