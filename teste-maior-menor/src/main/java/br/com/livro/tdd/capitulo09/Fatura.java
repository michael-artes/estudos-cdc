package br.com.livro.tdd.capitulo09;

import java.util.ArrayList;
import java.util.List;

public class Fatura {
	
	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();
	private String cliente;
	private double valor;
	private boolean pago;
	
	public Fatura(String string, double d) {
		this.cliente = string;
		this.valor = d;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public boolean isPago() {
		return this.pago;
	}
	
	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	

	public void adicionaPagamento(Pagamento pagamento) {
		
		this.pagamentos.add(pagamento);
		
		double valorTotal = 0;
		
		for(Pagamento p : pagamentos) {
			valorTotal += p.getValor();
		}
		
		if(valorTotal >= this.valor) {
			this.pago = true;
		}
	}	

}
