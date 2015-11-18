package br.com.design.pattern.capitulo02;

public class ApresentacaoCarrinho {
	
	public Double colocarInformacoesCarrinho(HttpServlerRequest rq){
		
		Carrinho c = CookieFactory.criarCarrinho(rq);
		
		System.out.println("----------------------------------");
		System.out.println("Tamanho: " + c.getTamanho());
		System.out.println("Valor: " + c.getValor());
		
		return c.getValor();
	}

}
