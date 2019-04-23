package br.com.michael.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.michael.loja.models.Carrinho;
import br.com.michael.loja.service.IntegrandoComPagamento;

@Controller
@RequestMapping("/pagamento")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {
	
	
	@Autowired
	private Carrinho carrinho;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	/**
	 * 
	 * retorn sincrono
	 * 
	 * o tomcat espera o retorno do metodo para 
	 * processar a próxima thread
	 * 
	 * */
	/*@RequestMapping(method=RequestMethod.POST, value="finalizar-compra")
	public String finalizarCompra(){
		
		BigDecimal total = carrinho.getTotal();
		
		String uriPagamento = "http://book-payment.herokuapp.com/payment";
		
		//integracao pagamento
		
		try {
			
			String response = restTemplate.postForObject(uriPagamento, new Pagamento(total), String.class);
			
			System.out.println(response);
			
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return "redirect:/pagamento/erro";
		}
		
		
		return "redirect:/pagamento/sucesso";
	}*/	
	
	
	/**
	 * 
	 * retorn assincrono
	 * 
	 * faz com que o tomcat processe outras
	 * threads enquanto espera o retorno do pagamento deste metodo
	 * para aí sim realizar o redirect
	 * 
	 * */
	
/*	@RequestMapping(method=RequestMethod.POST, value="finalizar-compra")
	public Callable<String> finalizarCompra(){
		
		return () -> {
			
			BigDecimal total = carrinho.getTotal();
			
			String uriPagamento = "http://book-payment.herokuapp.com/payment";
			
			//integracao pagamento
			
			try {
				
				String response = restTemplate.postForObject(uriPagamento, new Pagamento(total), String.class);
				
				System.out.println(response);
				
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				return "redirect:/pagamento/erro";
			}
			
			
			return "redirect:/pagamento/sucesso";			
			
		};
	}*/
	
	/**
	 * 
	 * retorn assincrono
	 * 
	 * faz com que o tomcat processe outras
	 * threads enquanto espera o retorno do pagamento deste metodo
	 * para aí sim realizar o redirect
	 * 
	 * 
	 * DefferedResult -> retorno postergado
	 * retorn DeferredResult -> maior controle das threads
	 * 
	 * */
	@RequestMapping(method=RequestMethod.POST, value="finalizar-compra")
	public DeferredResult<ModelAndView> finalizarCompra(){
		
		DeferredResult<ModelAndView> result = new DeferredResult<ModelAndView>();
		
		IntegrandoComPagamento integracao = new IntegrandoComPagamento(result, carrinho.getTotal(), restTemplate);
		
		Thread thread = new Thread(integracao);
		thread.start();
		
		return result;
	}
	

}
