package br.com.michael.loja.service;

import java.math.BigDecimal;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.michael.loja.models.Pagamento;
import br.com.michael.loja.models.User;


public class IntegrandoComPagamento implements Runnable {
	
	private DeferredResult<ModelAndView> result;
	private BigDecimal total;
	private RestTemplate restTemplate;
	private MailSender mailSender;
	private User user;
	
	private String uriPagamento = "http://book-payment.herokuapp.com/payment";

	public IntegrandoComPagamento(DeferredResult<ModelAndView> result, BigDecimal total, RestTemplate restTemplate, MailSender mailSender, User user) {
		super();
		this.result = result;
		this.total = total;
		this.restTemplate = restTemplate;
		this.mailSender = mailSender;
		this.user = user;
	}

	@Override
	public void run() {
		
		ModelAndView view = new ModelAndView("/pagamento/retorno");
		
		try {
			
			String response = restTemplate.postForObject(uriPagamento, new Pagamento(total), String.class);
			
			//necessario configurar senha e user
//			sendMail();
			
			view.addObject("sucesso", true);
			System.out.println(response);
			
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			view.addObject("sucesso", false);
		}
		
		result.setResult(view);
	}
	
	
	
	private void sendMail(){
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("mail-from");
//		email.setTo(user.getUsername());
		email.setTo("mail-to");
		email.setSubject("Nova compra");
		email.setText("corpo do email");
		
		System.out.println("Enviando Email....");
		
		mailSender.send(email);
		
	}

}
