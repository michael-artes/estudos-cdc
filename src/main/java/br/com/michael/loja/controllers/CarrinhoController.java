package br.com.michael.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.michael.loja.daos.ProdutoDAO;
import br.com.michael.loja.models.Carrinho;
import br.com.michael.loja.models.CarrinhoItens;
import br.com.michael.loja.models.LivroTipo;
import br.com.michael.loja.models.Produto;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private Carrinho carrinho;
	
	
	@RequestMapping(method=RequestMethod.POST, value="adicionar")
	public ModelAndView adicionar(Integer produtoId, LivroTipo livroTipo){
		
		CarrinhoItens item = criarItem(produtoId, livroTipo);
		carrinho.adicionar(item);
		
		return new ModelAndView("redirect:/produto/listagem");
	}


	private CarrinhoItens criarItem(Integer produtoId, LivroTipo livroTipo) {

		Produto produto = produtoDAO.findById(produtoId);
		CarrinhoItens item = new CarrinhoItens(produto, livroTipo);
		
		return item;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="carrinho")
	public String items(){
		return "carrinho/carrinho";
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="excluir")
	public String excluir(Integer idProduto, LivroTipo livroTipo){
		carrinho.remover(criarItem(idProduto, livroTipo));
		return "redirect:/carrinho/carrinho";
	}

}
