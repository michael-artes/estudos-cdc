/**
 * 
 */
package br.com.michael.loja.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.michael.loja.daos.ProdutoDAO;
import br.com.michael.loja.models.LivroTipo;
import br.com.michael.loja.models.Produto;
import br.com.michael.loja.service.SalvarArquivo;

/**
 * @author mmoreira
 *
 */

@Controller
@Transactional
@RequestMapping("/produto")
public class ProdutosController {
	
	@Autowired
	ProdutoDAO produtoDAO;

	@Autowired
	private SalvarArquivo salvarArquivo;
	
	
	//Fala pro spring qual classe ira utilizar validacoes
	//Nao precisa pois agora esta sendo utilizado bean validator
	/*@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ProdutoValidator());
	}*/
	
	
	@RequestMapping(value="cadastro", method=RequestMethod.GET)
	public ModelAndView form(Produto produto){
		ModelAndView view = new ModelAndView("/produto/form");
		view.addObject("tipos", LivroTipo.values());
		return view;
	}
	
	@RequestMapping(value="salvar", method=RequestMethod.POST)
	public ModelAndView save(MultipartFile sumario, @Validated Produto produto, BindingResult bindingResult ,RedirectAttributes attributes){
		
		ModelAndView view = null;
		
		if (bindingResult.hasErrors()) {
			return form(produto);
		}
		

		String webPath = salvarArquivo.writer("upload-arquivos", sumario);
		
		produto.setSumarioPath(webPath);
		produtoDAO.save(produto);
		
		view = new ModelAndView("redirect:listagem");
		
		//mantem a cada request os objetos na sessao
		attributes.addFlashAttribute("sucesso", "Produto inserido com sucesso");
		
		return view;
	}
	
	@RequestMapping(value="listagem", method=RequestMethod.GET)
	public ModelAndView list(){
		
		List<Produto> list = produtoDAO.list()
			.stream().peek(p -> {
				p.getPrecos().sort(Comparator.comparing(pr -> pr.getLivroTipo().toString()));
			})
			.sorted(Comparator.comparing(Produto::getTitulo))
			.collect(Collectors.toList());
		
		ModelAndView view = new ModelAndView("/produto/list");
		view.addObject("produtos", list);
		return view;
	}

}
