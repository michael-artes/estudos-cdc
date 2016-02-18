package br.com.michael.loja.models;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class Carrinho {

	private Map<CarrinhoItens, Integer> items = new LinkedHashMap<CarrinhoItens, Integer>();

	public void adicionar(CarrinhoItens item) {
		items.put(item, getQuantidade(item) + 1);
	}

	public Integer getQuantidade(CarrinhoItens item) {
		if (!items.containsKey(item)) {
			items.put(item, 0);
		}
		return items.get(item);
	}

	public Integer getQuantidade() {
		return items.values().stream()
				.reduce(0, (next, accumulator) -> next + accumulator);
	}

	public Collection<CarrinhoItens> getList() {
		return items.keySet();
	}

	public BigDecimal getTotal(CarrinhoItens item) {
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
		//TODO change to reduce?
		for(CarrinhoItens item : items.keySet()){
			total = total.add(getTotal(item));
		}
		return total;
	}

	public void remover(CarrinhoItens carrinhoItens) {
		items.remove(carrinhoItens);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}	
	
}
