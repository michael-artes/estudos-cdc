/**
 * 
 */
package br.com.michael.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mmoreira
 *
 */

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(){
		System.out.println("Carregando produtos...");
		return "hello-world";
	}
	
}
