package br.com.michael.loja.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Preco {
	
	
	@Column(scale = 2)
	private BigDecimal valor;
	private LivroTipo livroTipo;
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public LivroTipo getLivroTipo() {
		return livroTipo;
	}
	public void setLivroTipo(LivroTipo livroTipo) {
		this.livroTipo = livroTipo;
	}
	
	
	@Override
	public String toString() {
		return "Preco [valor=" + valor + ", livroTipo=" + livroTipo + "]";
	}
	

}
