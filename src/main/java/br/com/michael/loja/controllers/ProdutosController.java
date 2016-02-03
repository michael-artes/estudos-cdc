/**
 * 
 */
package br.com.michael.loja.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.michael.loja.daos.ProdutoDAO;
import br.com.michael.loja.models.Produto;

/**
 * @author mmoreira
 *
 */

@Controller
@Transactional
public class ProdutosController {
	
	@Autowired
	ProdutoDAO produtoDAO; 
	
	@RequestMapping("/produto/form")
	public String form(){
		return "produto/form";
	}
	
	@RequestMapping("/produto/cadastro")
	public String save(Produto produto){
		produtoDAO.save(produto);
		return "produto/ok";
	}

}
