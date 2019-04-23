/**
 * 
 */
package br.com.michael.loja.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	
	@RequestMapping(method=RequestMethod.GET, value="cadastro")
	public ModelAndView form(Produto produto){
		ModelAndView view = new ModelAndView("produto/form");
		view.addObject("tipos", LivroTipo.values());
		return view;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="salvar")
	@CacheEvict(value="livros", allEntries=true)
	public ModelAndView save(MultipartFile sumario, @Validated Produto produto, BindingResult bindingResult , RedirectAttributes attributes){
		
		ModelAndView view = null;
		
		if (!sumario.getContentType().equals("image/png")) {
			bindingResult.addError(new ObjectError("not.denied", "Somente arquivos .png"));
		}

		if (bindingResult.hasErrors()) {
			return form(produto);
		}
		
		produtoDAO.save(produto);
		
		String webPath = salvarArquivo.writer(produto, sumario);
		
		produto.setSumarioPath(webPath);
		
		view = new ModelAndView("redirect:/produto/listagem");
		
		//mantem a cada request os objetos na sessao
		attributes.addFlashAttribute("sucesso", "Produto inserido com sucesso");
		
		return view;
	}
	
	/**
	 * 
	 * este método retorna .json ou .xml desde que eu defina a extensão
	 * no final da url ex.: produtos.json - configuração no AppWebConfiguration
	 * 
	 * */
	
	@RequestMapping(method=RequestMethod.GET, value="listagem")
	@Cacheable(value="livros")
	public ModelAndView list(){ 
		
		List<Produto> list = produtoDAO.list()
			.stream().peek(p -> {
				p.getPrecos().sort(Comparator.comparing(pr -> pr.getLivroTipo().toString()));
			})
			.sorted(Comparator.comparing(Produto::getTitulo))
			.collect(Collectors.toList());
		
		ModelAndView view = new ModelAndView("produto/list");
		view.addObject("produtos", list);
		return view;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="json-livros")
	@ResponseBody
	public List<Produto> listJson(){
		
		List<Produto> list = produtoDAO.list()
			.stream().peek(p -> {
				p.getPrecos().sort(Comparator.comparing(pr -> pr.getLivroTipo().toString()));
			})
			.sorted(Comparator.comparing(Produto::getTitulo))
			.collect(Collectors.toList());
		
		return list;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ModelAndView show(@PathVariable("id") Integer id){
		
		ModelAndView view = new ModelAndView("produto/show");
		
		Produto produto = produtoDAO.findById(id);
		view.addObject("produto", produto);
		
		return view;		
	}

}
