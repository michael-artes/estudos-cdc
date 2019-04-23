package capitulo11;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Subscription {
	
	private BigDecimal valorMes;
	private LocalDateTime inicio;
	private Optional<LocalDateTime> fim;
	private Customer cliente;
	
	public Subscription(BigDecimal valorMes, LocalDateTime inicio, Customer cliente) {
		this.valorMes = valorMes;
		this.inicio = inicio;
		this.fim = Optional.empty();
		this.cliente = cliente;
	}
	
	public Subscription(BigDecimal valorMes, LocalDateTime inicio, LocalDateTime fim, Customer cliente) {
		this.valorMes = valorMes;
		this.inicio = inicio;
		this.fim = Optional.of(fim);
		this.cliente = cliente;
	}

	/**
	 * @return the valorMes
	 */
	public BigDecimal getValorMes() {
		return valorMes;
	}

	/**
	 * @return the inicio
	 */
	public LocalDateTime getInicio() {
		return inicio;
	}

	/**
	 * @return the fim
	 */
	public Optional<LocalDateTime> getFim() {
		return fim;
	}

	/**
	 * @return the cliente
	 */
	public Customer getCliente() {
		return cliente;
	}
	
	
	public BigDecimal getTotalAssinatura(){
		return getValorMes().multiply(new BigDecimal(ChronoUnit.MONTHS.between(getInicio(),getFim().orElse(LocalDateTime.now()))));
	}

}
