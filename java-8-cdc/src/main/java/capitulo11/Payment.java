package capitulo11;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class Payment {
	
	private List<Produto> produtos;
	private LocalDateTime dateTime;
	private Customer customer;
	
	
	public Payment(List<Produto> produtos, LocalDateTime dateTime, Customer customer) {
		this.produtos = Collections.unmodifiableList(produtos);
		this.dateTime = dateTime;
		this.customer = customer;
	}
	
	
	public Payment() {
	}


	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "Payment ["
				+ "produtos=" + produtos + 
				", dateTime=" + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + 
				", customer=" + customer + "]";
	}
	
}
