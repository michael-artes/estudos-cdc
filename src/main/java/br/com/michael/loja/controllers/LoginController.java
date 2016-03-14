/**
 * 
 */
package br.com.michael.loja.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.michael.loja.models.User;

/**
 * @author mmoreira
 *
 */

@Controller
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class LoginController {
	

	@RequestMapping("/login")
	public ModelAndView login(User user){
		ModelAndView view = new ModelAndView("auth/login-form");
		return view;
	}
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest rq, HttpServletResponse rs){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (Objects.nonNull(authentication)) {
			new SecurityContextLogoutHandler().logout(rq, rs, authentication);
		}
		
		return "redirect:/login?logout";
	}
	
}
