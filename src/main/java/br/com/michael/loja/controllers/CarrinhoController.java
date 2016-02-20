package br.com.michael.loja.controllers;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView adicionar(Integer produtoId, LivroTipo livroTipo, RedirectAttributes attributes){
		
		ModelAndView view = new ModelAndView("redirect:/produto/listagem");
		
		if (Objects.isNull(livroTipo)) {
			
			attributes.addFlashAttribute("invalidLivroTipo", "Necessario selecionar um tipo de livro");
			view.setViewName("redirect:/produto/" + produtoId);
			
			return view;
			
		}
		
		CarrinhoItens item = criarItem(produtoId, livroTipo);
		carrinho.adicionar(item);
		
		return view;
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

	
	@RequestMapping(method=RequestMethod.POST, value="atualizar")
	public ModelAndView atualizar(Integer idProduto, Integer qtdProdutoId){
		
		ModelAndView view = new ModelAndView("/carrinho/carrinho");
		
		if (qtdProdutoId < 0) {
			view.addObject("invalidQuantidade", "A quantidade não pode ser menor que zero");
		}

		Optional<CarrinhoItens> itemCarrinho = carrinho.getList()
			.stream()
			.filter(item -> item.getProduto().getId().equals(idProduto))
			.findFirst();
		
		carrinho.atualizar(itemCarrinho.get(), qtdProdutoId);
		
		return view;
	}
	
	
}
