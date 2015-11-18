package br.com.design.pattern.capitulo02;

public class CookieFactory {

	public static Carrinho criarCarrinho(HttpServlerRequest rq) {
		
		Carrinho carrinho = new Carrinho();
		carrinho.setTamanho("Torre Aifel");
		carrinho.setValor(150.35);
		
		if (!rq.isTrue()) {
			carrinho = new CarrinhoNull();
		}
		
		return carrinho;
	}

}
