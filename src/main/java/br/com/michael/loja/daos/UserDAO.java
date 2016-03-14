package br.com.michael.loja.daos;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.michael.loja.models.User;

public interface UserDAO extends UserDetailsService{
	
	public void salvarUsuario(User user);
	
	public void salvarUserRole(User user);

	public void atualizarUsuario(User user) ;
	
}