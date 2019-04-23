/**
 * 
 */
package br.com.michael.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.michael.loja.enuns.EnumRoles;
import br.com.michael.loja.models.Role;
import br.com.michael.loja.models.User;
import br.com.michael.loja.service.UserService;

/**
 * @author mmoreira
 *
 */

@Controller
@RequestMapping("/user")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView criarUser(@Validated User user, BindingResult bindingResult, RedirectAttributes attributes){
		
		ModelAndView view = new ModelAndView("redirect:/login");
		
		if (bindingResult.hasErrors()) {
			return view;
		}
		
		userService.processaInfosUser(user);
		
		attributes.addFlashAttribute("userCreate", true);
		
		return view;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/active", method = RequestMethod.GET)
	public ModelAndView activeUser(String email, RedirectAttributes attributes){
		
		
		User user = userService.getUSerByEmail(email);
		user.setAtivo(true);
		user.setEnumRoles(EnumRoles.valueOf(((List<Role>) user.getAuthorities()).get(0).getAuthority()));
		
		userService.atualizarUsuario(user);
		
		ModelAndView view = new ModelAndView("redirect:/login");
		attributes.addFlashAttribute("userAtivado", true);
		
		return view;
	}	
	
}
