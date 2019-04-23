package br.com.loginapp.converter;

import com.login.app.model.Produto;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItensProperty {
	
	private SimpleStringProperty produto;
	private SimpleDoubleProperty preco;
	private SimpleStringProperty pathImg;
	
	public ItensProperty(String produto, double preco, String pathImg) {
		this.produto = new SimpleStringProperty(produto);
		this.preco = new SimpleDoubleProperty(preco);
		this.pathImg = new SimpleStringProperty(pathImg);
	}

	
	public String getProduto() {
		return produto.get();
	}

	public void setProduto(String produto) {
		this.produto.set(produto);
	}

	public double getPreco() {
		return preco.get();
	}

	public void setPreco(double preco) {
		this.preco.set(preco);
	}


	public String getPathImg() {
		return this.pathImg.get();
	}

	public void setPathImg(String pathImg) {
		this.pathImg.set(pathImg);
	}
	
	
	public Produto getProdutoPojo(){
		return new Produto(getProduto(), getPreco(), getPathImg());
	}

}
