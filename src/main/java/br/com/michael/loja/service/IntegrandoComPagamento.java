package br.com.michael.loja.service;

import java.math.BigDecimal;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.michael.loja.models.Pagamento;

public class IntegrandoComPagamento implements Runnable {
	
	private DeferredResult<ModelAndView> result;
	private BigDecimal total;
	private RestTemplate restTemplate;
	
	private String uriPagamento = "http://book-payment.herokuapp.com/payment";

	public IntegrandoComPagamento(DeferredResult<ModelAndView> result, BigDecimal total, RestTemplate restTemplate) {
		super();
		this.result = result;
		this.total = total;
		this.restTemplate = restTemplate;
	}

	@Override
	public void run() {
		
		ModelAndView view = new ModelAndView("/pagamento/retorno");
		
		try {
			
			String response = restTemplate.postForObject(uriPagamento, new Pagamento(total), String.class);
			
			view.addObject("sucesso", true);
			System.out.println(response);
			
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			view.addObject("sucesso", false);
		}
		
		result.setResult(view);
	}

}
